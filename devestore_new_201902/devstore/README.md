# PostgreSQLでの操作手順

## ユーザー関連
初期は１〜３を実行しておく

### 1.ユーザ作成
PostgreSQLの初期設定は下記ユーザ名とパスワードを設定してください
- ユーザ名: postgres
- パスワード: postgres

### 2.開発ユーザ作成
開発ユーザ作成Shell作成

```
$ sh db/init_user.sh
```

### 3.ログイン
ログイン用のShellを実行

```
$ sh db/login.sh
```

## テーブル・データ関連
### DB・テーブル初期化
初期化スクリプトを実行(※実行前に開発ユーザを作成してください)

```
$ sh db/init_development.sh
```

### i系csvのデータをインポート

```
$ sh db/init_csv.sh
```

### dump作成
dump作成

```
$ sh db/dump_development.sh
```
