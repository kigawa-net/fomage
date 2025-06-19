# API ドキュメント

このドキュメントは、FomageアプリケーションのAPI仕様を説明します。

## 目次

- [概要](#概要)
- [エンドポイント一覧](#エンドポイント一覧)
- [データモデル](#データモデル)
- [認証](#認証)
- [エラーハンドリング](#エラーハンドリング)
- [レート制限](#レート制限)
- [サンプルコード](#サンプルコード)

## 概要

Fomage APIは、MongoDBを使用したデータ管理システムのRESTful APIです。Kotlin Coroutinesを使用した非同期処理により、高パフォーマンスなデータ操作を提供します。

### ベースURL

```
http://localhost:8080/api/v1
```

### サポートするHTTPメソッド

- `GET`: データの取得
- `POST`: データの作成
- `PUT`: データの更新
- `DELETE`: データの削除

### レスポンス形式

すべてのAPIレスポンスはJSON形式で返されます。

```json
{
  "success": true,
  "data": {},
  "message": "Operation completed successfully",
  "timestamp": "2024-01-01T00:00:00Z"
}
```

## エンドポイント一覧

### ヘルスチェック

#### GET /health

アプリケーションの健全性を確認します。

**レスポンス**

```json
{
  "status": "UP",
  "timestamp": "2024-01-01T00:00:00Z",
  "version": "1.0.0"
}
```

### ユーザー管理

#### GET /users

ユーザー一覧を取得します。

**クエリパラメータ**

| パラメータ | 型 | 必須 | 説明 |
|-----------|----|------|------|
| page | integer | 否 | ページ番号（デフォルト: 1） |
| size | integer | 否 | ページサイズ（デフォルト: 20） |
| sort | string | 否 | ソート項目（例: name,createdAt） |
| order | string | 否 | ソート順序（asc, desc） |

**レスポンス**

```json
{
  "success": true,
  "data": {
    "users": [
      {
        "id": "507f1f77bcf86cd799439011",
        "name": "John Doe",
        "email": "john@example.com",
        "createdAt": "2024-01-01T00:00:00Z",
        "updatedAt": "2024-01-01T00:00:00Z"
      }
    ],
    "pagination": {
      "page": 1,
      "size": 20,
      "total": 100,
      "totalPages": 5
    }
  }
}
```

#### GET /users/{id}

指定されたIDのユーザーを取得します。

**パスパラメータ**

| パラメータ | 型 | 必須 | 説明 |
|-----------|----|------|------|
| id | string | 是 | ユーザーID |

**レスポンス**

```json
{
  "success": true,
  "data": {
    "id": "507f1f77bcf86cd799439011",
    "name": "John Doe",
    "email": "john@example.com",
    "createdAt": "2024-01-01T00:00:00Z",
    "updatedAt": "2024-01-01T00:00:00Z"
  }
}
```

#### POST /users

新しいユーザーを作成します。

**リクエストボディ**

```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securepassword123"
}
```

**レスポンス**

```json
{
  "success": true,
  "data": {
    "id": "507f1f77bcf86cd799439011",
    "name": "John Doe",
    "email": "john@example.com",
    "createdAt": "2024-01-01T00:00:00Z",
    "updatedAt": "2024-01-01T00:00:00Z"
  },
  "message": "User created successfully"
}
```

#### PUT /users/{id}

指定されたIDのユーザーを更新します。

**パスパラメータ**

| パラメータ | 型 | 必須 | 説明 |
|-----------|----|------|------|
| id | string | 是 | ユーザーID |

**リクエストボディ**

```json
{
  "name": "John Smith",
  "email": "johnsmith@example.com"
}
```

**レスポンス**

```json
{
  "success": true,
  "data": {
    "id": "507f1f77bcf86cd799439011",
    "name": "John Smith",
    "email": "johnsmith@example.com",
    "createdAt": "2024-01-01T00:00:00Z",
    "updatedAt": "2024-01-01T12:00:00Z"
  },
  "message": "User updated successfully"
}
```

#### DELETE /users/{id}

指定されたIDのユーザーを削除します。

**パスパラメータ**

| パラメータ | 型 | 必須 | 説明 |
|-----------|----|------|------|
| id | string | 是 | ユーザーID |

**レスポンス**

```json
{
  "success": true,
  "message": "User deleted successfully"
}
```

### データ管理

#### GET /data

データ一覧を取得します。

**クエリパラメータ**

| パラメータ | 型 | 必須 | 説明 |
|-----------|----|------|------|
| type | string | 否 | データタイプでフィルタ |
| userId | string | 否 | ユーザーIDでフィルタ |
| page | integer | 否 | ページ番号 |
| size | integer | 否 | ページサイズ |

**レスポンス**

```json
{
  "success": true,
  "data": {
    "items": [
      {
        "id": "507f1f77bcf86cd799439012",
        "type": "document",
        "content": "Sample content",
        "userId": "507f1f77bcf86cd799439011",
        "createdAt": "2024-01-01T00:00:00Z"
      }
    ],
    "pagination": {
      "page": 1,
      "size": 20,
      "total": 50,
      "totalPages": 3
    }
  }
}
```

#### POST /data

新しいデータを作成します。

**リクエストボディ**

```json
{
  "type": "document",
  "content": "New document content",
  "metadata": {
    "title": "Sample Document",
    "tags": ["sample", "document"]
  }
}
```

**レスポンス**

```json
{
  "success": true,
  "data": {
    "id": "507f1f77bcf86cd799439012",
    "type": "document",
    "content": "New document content",
    "metadata": {
      "title": "Sample Document",
      "tags": ["sample", "document"]
    },
    "createdAt": "2024-01-01T00:00:00Z"
  },
  "message": "Data created successfully"
}
```

## データモデル

### User

```json
{
  "id": "string (ObjectId)",
  "name": "string",
  "email": "string",
  "password": "string (hashed)",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

### Data

```json
{
  "id": "string (ObjectId)",
  "type": "string",
  "content": "string",
  "metadata": "object",
  "userId": "string (ObjectId)",
  "createdAt": "datetime",
  "updatedAt": "datetime"
}
```

### Pagination

```json
{
  "page": "integer",
  "size": "integer",
  "total": "integer",
  "totalPages": "integer"
}
```

### Error

```json
{
  "success": false,
  "error": {
    "code": "string",
    "message": "string",
    "details": "object (optional)"
  },
  "timestamp": "datetime"
}
```

## 認証

### JWT認証

APIはJWT（JSON Web Token）を使用した認証をサポートします。

#### 認証ヘッダー

```
Authorization: Bearer <token>
```

#### ログイン

**POST /auth/login**

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**レスポンス**

```json
{
  "success": true,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "refreshToken": "refresh_token_here",
    "expiresIn": 3600
  }
}
```

#### トークン更新

**POST /auth/refresh**

```json
{
  "refreshToken": "refresh_token_here"
}
```

## エラーハンドリング

### HTTPステータスコード

| コード | 説明 |
|--------|------|
| 200 | 成功 |
| 201 | 作成成功 |
| 400 | リクエストエラー |
| 401 | 認証エラー |
| 403 | 権限エラー |
| 404 | リソースが見つかりません |
| 422 | バリデーションエラー |
| 500 | サーバーエラー |

### エラーコード

| コード | 説明 |
|--------|------|
| VALIDATION_ERROR | バリデーションエラー |
| USER_NOT_FOUND | ユーザーが見つかりません |
| EMAIL_ALREADY_EXISTS | メールアドレスが既に存在します |
| INVALID_CREDENTIALS | 認証情報が無効です |
| TOKEN_EXPIRED | トークンが期限切れです |
| INSUFFICIENT_PERMISSIONS | 権限が不足しています |

### エラーレスポンス例

```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Validation failed",
    "details": {
      "email": ["Email is required"],
      "password": ["Password must be at least 8 characters"]
    }
  },
  "timestamp": "2024-01-01T00:00:00Z"
}
```

## レート制限

APIはレート制限を実装しており、以下の制限があります：

- **認証済みユーザー**: 1000リクエスト/時間
- **未認証ユーザー**: 100リクエスト/時間

### レート制限ヘッダー

```
X-RateLimit-Limit: 1000
X-RateLimit-Remaining: 999
X-RateLimit-Reset: 1640995200
```

## サンプルコード

### Kotlin (Ktor Client)

```kotlin
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class FomageApiClient(private val baseUrl: String) {
    private val client = HttpClient()
    
    suspend fun getUsers(page: Int = 1, size: Int = 20): UsersResponse {
        val response = client.get("$baseUrl/users") {
            parameter("page", page)
            parameter("size", size)
        }
        return Json.decodeFromString(response.bodyAsText())
    }
    
    suspend fun createUser(user: CreateUserRequest): UserResponse {
        val response = client.post("$baseUrl/users") {
            contentType(ContentType.Application.Json)
            setBody(user)
        }
        return Json.decodeFromString(response.bodyAsText())
    }
}
```

### JavaScript (Fetch API)

```javascript
class FomageApiClient {
    constructor(baseUrl, token = null) {
        this.baseUrl = baseUrl;
        this.token = token;
    }
    
    async getUsers(page = 1, size = 20) {
        const response = await fetch(
            `${this.baseUrl}/users?page=${page}&size=${size}`,
            {
                headers: this.getHeaders()
            }
        );
        return response.json();
    }
    
    async createUser(userData) {
        const response = await fetch(`${this.baseUrl}/users`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(userData)
        });
        return response.json();
    }
    
    getHeaders() {
        const headers = {
            'Content-Type': 'application/json'
        };
        if (this.token) {
            headers['Authorization'] = `Bearer ${this.token}`;
        }
        return headers;
    }
}
```

### cURL

```bash
# ユーザー一覧を取得
curl -X GET "http://localhost:8080/api/v1/users?page=1&size=20" \
  -H "Authorization: Bearer your_token_here"

# ユーザーを作成
curl -X POST "http://localhost:8080/api/v1/users" \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer your_token_here" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securepassword123"
  }'
```

## 変更履歴

### v1.0.0 (2024-01-01)
- 初回リリース
- 基本的なCRUD操作
- JWT認証
- レート制限

## サポート

APIに関する質問や問題がある場合は、以下の方法でサポートを受けることができます：

- GitHub Issues: [プロジェクトのIssuesページ](https://github.com/your-repo/issues)
- メール: api-support@example.com
- ドキュメント: [API ドキュメント](https://docs.example.com/api) 