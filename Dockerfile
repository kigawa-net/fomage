# マルチステージビルドを使用して最適化されたイメージを作成

# ビルドステージ
FROM gradle:8.5-jdk17 AS builder

# 作業ディレクトリを設定
WORKDIR /app

# Gradleファイルをコピー
COPY gradle/ gradle/
COPY build.gradle.kts gradle.properties gradlew gradlew.bat settings.gradle.kts ./

# 依存関係をダウンロード（キャッシュを活用）
RUN gradle dependencies --no-daemon

# ソースコードをコピー
COPY src/ src/

# アプリケーションをビルド
RUN gradle shadowJar --no-daemon

# 実行ステージ
FROM openjdk:17-jre-slim

# メタデータを設定
LABEL maintainer="kigawa01"
LABEL version="1.0.0"
LABEL description="Fomage - MongoDB-based Kotlin application"

# セキュリティ: 非rootユーザーを作成
RUN groupadd -r fomage && useradd -r -g fomage fomage

# 作業ディレクトリを設定
WORKDIR /app

# ビルドステージからJARファイルをコピー
COPY --from=builder /app/build/libs/fomage-all.jar app.jar

# ログディレクトリを作成
RUN mkdir -p /app/logs && chown -R fomage:fomage /app

# 非rootユーザーに切り替え
USER fomage

# ヘルスチェックを設定
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1

# ポートを公開
EXPOSE 8080

# JVMオプションを設定
ENV JAVA_OPTS="-Xms512m -Xmx2g -XX:+UseG1GC -XX:+UseContainerSupport"

# アプリケーションを起動
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 