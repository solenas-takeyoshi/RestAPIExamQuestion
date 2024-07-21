# RestAPIExamQuestion

## 開発環境
* Eclipse IDE for Java Developers (includes Incubating components)
* Version: 2024-03 (4.31.0)　（Pleiades）
* Build id: 20240307-1437

## 動作手順
 (事前準備)XAMPPでmySQLを起動しておく。
1. Eclipse > パッケージエクスプローラ > 実行 > Spring Boot アプリケーションをクリック
2. DOSプロンプトで本ドキュメントまでCDで移動
3. DOSプロンプトで以下を実行
curl -X POST -H "Content-Type: application/json" -d @order.txt http://localhost:8080/host/add
