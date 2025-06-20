# Fomage

FomageはKotlinで開発されたMongoDBベースのアプリケーションです。非同期処理とコルーチンを活用したモダンなKotlinアプリケーションです。

## 📋 目次

- [概要](#概要)
- [機能](#機能)
- [技術スタック](#技術スタック)
- [セットアップ](#セットアップ)
- [使用方法](#使用方法)
- [開発](#開発)
- [アーキテクチャ](#アーキテクチャ)
- [Docker](#docker)
- [ライセンス](#ライセンス)
- [ドキュメント](#ドキュメント)

## 概要

Fomageは、MongoDBを使用したデータ処理と管理を行うKotlinアプリケーションです。コルーチンを使用した非同期処理により、効率的なデータ操作を実現します。

## 機能

- MongoDBとの非同期接続・操作
- 環境変数による設定管理
- ログ出力機能
- コルーチンを使用した非同期処理

## 技術スタック

- **言語**: Kotlin 2.1.0
- **ビルドツール**: Gradle (Kotlin DSL)
- **データベース**: MongoDB 5.3.1
- **非同期処理**: Kotlin Coroutines 1.9.0
- **ログ**: Logback 1.5.17
- **設定管理**: dotenv-kotlin 6.5.1

## セットアップ

### 前提条件

- Java 17以上
- Gradle 8.0以上
- MongoDB（ローカルまたはリモート）

### インストール手順

1. リポジトリをクローン
```bash
git clone <repository-url>
cd fomage
```

2. 依存関係をインストール
```bash
./gradlew build
```

3. 環境変数を設定
```bash
cp env.example .env
# .envファイルを編集して必要な設定を行ってください
```

## 使用方法

### 基本的な実行

```bash
./gradlew run
```

### JARファイルの作成と実行

```bash
# JARファイルを作成
./gradlew shadowJar

# 作成されたJARファイルを実行
java -jar build/libs/fomage-all.jar
```

### Dockerでの実行

```bash
# Dockerイメージをビルド
docker build -t kigawa01/fomage .

# コンテナを実行
docker run kigawa01/fomage
```

## 開発

### プロジェクト構造

```
fomage/
├── build.gradle.kts          # メインのビルド設定
├── buildSrc/                 # カスタムGradleプラグイン
├── gradle/                   # Gradle設定
│   └── libs.versions.toml    # 依存関係のバージョン管理
├── src/
│   └── main/
│       └── kotlin/
│           └── net/kigawa/fomage/
│               └── Main.kt   # メインエントリーポイント
└── README.md
```

### 開発環境のセットアップ

1. IDEでプロジェクトを開く（IntelliJ IDEA推奨）
2. Gradleプロジェクトとしてインポート
3. Kotlinプラグインが有効になっていることを確認

### テストの実行

```bash
./gradlew test
```

### コードフォーマット

```bash
./gradlew ktlintFormat
```

## アーキテクチャ

### 主要コンポーネント

- **Main.kt**: アプリケーションのエントリーポイント
- **MongoDB Driver**: データベース接続と操作
- **Coroutines**: 非同期処理の実装
- **Logback**: ログ出力の管理

### 依存関係

- **mongodb-driver-kotlin-coroutine**: MongoDBとの非同期通信
- **bson-kotlinx**: BSONデータの処理
- **dotenv-kotlin**: 環境変数の管理
- **kotlinx-coroutines-core**: 非同期処理
- **logback-classic**: ログ出力

## Docker

### Dockerイメージ

```
kigawa01/fomage
```

### Dockerfileの作成

プロジェクトルートに以下のDockerfileを作成することを推奨します：

```dockerfile
FROM openjdk:17-jre-slim

WORKDIR /app

COPY build/libs/fomage-all.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
```

## 設定

### 環境変数

以下の環境変数を設定してください：

- `MONGODB_URI`: MongoDB接続文字列
- `MONGODB_DATABASE`: 使用するデータベース名
- `LOG_LEVEL`: ログレベル（DEBUG, INFO, WARN, ERROR）

### .envファイルの例

```env
MONGODB_URI=mongodb://localhost:27017
MONGODB_DATABASE=fomage
LOG_LEVEL=INFO
```

## ライセンス

このプロジェクトはMITライセンスの下で公開されています。詳細は[LICENSE](LICENSE)ファイルを参照してください。

## 貢献

1. このリポジトリをフォーク
2. 機能ブランチを作成 (`git checkout -b feature/amazing-feature`)
3. 変更をコミット (`git commit -m 'Add some amazing feature'`)
4. ブランチにプッシュ (`git push origin feature/amazing-feature`)
5. プルリクエストを作成

## サポート

問題や質問がある場合は、GitHubのIssuesページで報告してください。

## ドキュメント

詳細なドキュメントは [docs/](docs/) ディレクトリにあります：

- **[📚 ドキュメント一覧](docs/README.md)** - すべてのドキュメントへのナビゲーション
- **[🚀 クイックスタートガイド](docs/QUICKSTART.md)** - 5分で始めるセットアップガイド
- **[👨‍💻 開発ガイド](docs/DEVELOPMENT.md)** - 詳細な開発環境セットアップとコーディング規約
- **[📖 API ドキュメント](docs/API.md)** - RESTful APIの完全な仕様書
- **[🏗️ システム設計](docs/ARCHITECTURE.md)** - アーキテクチャと技術スタックの詳細
- **[🚢 Docker ガイド](docs/DOCKER.md)** - コンテナ化とデプロイメント
- **[📊 監視・ログ](docs/MONITORING.md)** - ログ管理とメトリクス収集
- **[🔒 セキュリティガイド](docs/SECURITY.md)** - セキュリティベストプラクティス
- **[🐛 トラブルシューティング](docs/TROUBLESHOOTING.md)** - よくある問題と解決方法
