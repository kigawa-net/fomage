# クイックスタートガイド

このガイドでは、Fomageアプリケーションを素早くセットアップして実行する方法を説明します。

## 🚀 5分で始める

### 前提条件

- [Docker](https://docs.docker.com/get-docker/) と [Docker Compose](https://docs.docker.com/compose/install/) がインストールされていること
- Git がインストールされていること

### 手順

1. **リポジトリをクローン**
   ```bash
   git clone <repository-url>
   cd fomage
   ```

2. **環境変数を設定**
   ```bash
   cp env.example .env
   # .envファイルを必要に応じて編集
   ```

3. **Docker Composeで起動**
   ```bash
   docker-compose up -d
   ```

4. **アプリケーションの確認**
   - アプリケーション: http://localhost:8080
   - MongoDB管理UI: http://localhost:8081 (admin/password123)
   - Grafanaダッシュボード: http://localhost:3000 (admin/admin123)

## 📋 詳細なセットアップ

### 方法1: Docker Compose（推奨）

最も簡単な方法です。すべてのサービスが自動的に設定されます。

```bash
# 1. リポジトリをクローン
git clone <repository-url>
cd fomage

# 2. 環境変数を設定
cp env.example .env

# 3. サービスを起動
docker-compose up -d

# 4. ログを確認
docker-compose logs -f fomage-app

# 5. サービスを停止
docker-compose down
```

### 方法2: ローカル開発環境

より詳細な制御が必要な場合や、開発を行う場合に適しています。

#### 必要なツール

- Java 17以上
- Gradle 8.0以上
- MongoDB 5.0以上

#### セットアップ手順

```bash
# 1. リポジトリをクローン
git clone <repository-url>
cd fomage

# 2. 環境変数を設定
cp env.example .env

# 3. MongoDBを起動（Dockerを使用）
docker run -d \
  --name mongodb \
  -p 27017:27017 \
  -e MONGO_INITDB_ROOT_USERNAME=admin \
  -e MONGO_INITDB_ROOT_PASSWORD=password123 \
  mongo:7.0

# 4. アプリケーションをビルド
./gradlew build

# 5. アプリケーションを実行
./gradlew run
```

## 🔧 設定

### 環境変数

主要な環境変数とその説明：

| 変数名 | 説明 | デフォルト値 |
|--------|------|-------------|
| `MONGODB_URI` | MongoDB接続文字列 | `mongodb://localhost:27017` |
| `MONGODB_DATABASE` | データベース名 | `fomage` |
| `SERVER_PORT` | サーバーポート | `8080` |
| `LOG_LEVEL` | ログレベル | `INFO` |

### データベース設定

MongoDBの接続設定を変更する場合：

```bash
# .envファイルで設定
MONGODB_URI=mongodb://username:password@host:port/database
MONGODB_DATABASE=your_database_name
```

## 🧪 テスト

### アプリケーションの動作確認

```bash
# ヘルスチェック
curl http://localhost:8080/health

# アプリケーション情報
curl http://localhost:8080/api/v1/info
```

### テストの実行

```bash
# 全テストを実行
./gradlew test

# 特定のテストを実行
./gradlew test --tests UserServiceTest
```

## 📊 監視とログ

### ログの確認

```bash
# Docker Composeの場合
docker-compose logs -f fomage-app

# ローカル実行の場合
tail -f logs/application.log
```

### メトリクスの確認

- **Prometheus**: http://localhost:9091
- **Grafana**: http://localhost:3000 (admin/admin123)

### MongoDB管理

- **MongoDB Express**: http://localhost:8081 (admin/password123)

## 🐛 トラブルシューティング

### よくある問題

#### 1. ポートが既に使用されている

```bash
# 使用中のポートを確認
lsof -i :8080

# 別のポートを使用
SERVER_PORT=8082
```

#### 2. MongoDB接続エラー

```bash
# MongoDBの状態を確認
docker-compose ps mongodb

# MongoDBログを確認
docker-compose logs mongodb
```

#### 3. メモリ不足エラー

```bash
# JVMヒープサイズを増加
JAVA_OPTS="-Xms1g -Xmx2g" ./gradlew run
```

#### 4. Dockerイメージのビルドエラー

```bash
# Dockerキャッシュをクリア
docker system prune -a

# イメージを再ビルド
docker-compose build --no-cache
```

### ログレベルの変更

デバッグ情報が必要な場合：

```bash
# .envファイルで設定
LOG_LEVEL=DEBUG
```

## 🔄 開発ワークフロー

### 1. 機能開発

```bash
# 新しいブランチを作成
git checkout -b feature/new-feature

# コードを編集
# ...

# テストを実行
./gradlew test

# コミット
git add .
git commit -m "Add new feature"

# プルリクエストを作成
git push origin feature/new-feature
```

### 2. ホットリロード（開発時）

```bash
# ファイル変更を監視して自動再起動
./gradlew run --continuous
```

### 3. プロファイリング

```bash
# JFRを使用したプロファイリング
java -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=profile.jfr -jar build/libs/fomage-all.jar
```

## 📚 次のステップ

1. **[開発ガイド](DEVELOPMENT.md)** - 詳細な開発情報
2. **[API ドキュメント](API.md)** - API仕様の詳細
3. **[アーキテクチャドキュメント](ARCHITECTURE.md)** - システム設計の詳細

## 🆘 サポート

問題が発生した場合：

1. **[トラブルシューティング](#トラブルシューティング)** セクションを確認
2. **[GitHub Issues](https://github.com/your-repo/issues)** で既存の問題を検索
3. 新しいIssueを作成

## 📝 変更履歴

| バージョン | 日付 | 変更内容 |
|-----------|------|----------|
| 1.0.0 | 2024-01-01 | 初回リリース |

---

**注意**: 本番環境で使用する前に、必ずセキュリティ設定を確認し、適切な値に変更してください。 