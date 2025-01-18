# <div id="top">運賃算出システム用リポジトリ</div>

## 使用技術一覧

<!-- シールド一覧 -->
<!-- 該当するプロジェクトの中から任意のものを選ぶ-->
<p style="display: inline">
  <!-- バックエンドのフレームワーク一覧 -->
  <img src="https://img.shields.io/badge/-JSP+Servlet-092E20.svg?logo=java&style=for-the-badge">
  <!-- バックエンドの技術一覧 -->
  <img src="https://img.shields.io/badge/-css-007396.svg?logo=css&style=for-the-badge">
  <!-- バックエンドの言語一覧 -->
  <img src="https://img.shields.io/badge/-Java-007396.svg?logo=java&style=for-the-badge">
  <!-- ミドルウェア一覧 -->
  <img src="https://img.shields.io/badge/-Tomcat-269539.svg?logo=tomcat&style=for-the-badge">
  <img src="https://img.shields.io/badge/-MariaDB-4479A1.svg?logo=mariadb&style=for-the-badge&logoColor=white">
</p>

## 目次

1. [プロジェクトについて](#プロジェクトについて)
2. [環境](#環境)
3. [ディレクトリ構成](#ディレクトリ構成)
4. [開発環境構築](#開発環境構築)

<!-- プロジェクト名を記載 -->

## プロジェクト名

fare-calc-sysプロジェクト

<!-- プロジェクトについて -->

## プロジェクトについて

このプロジェクトは、鉄道の運賃の計算するシステムを開発するものである。

最もシンプルな線形路線（単一路線）からはじめて、乗り入れのある複数路線に拡張する。

また、別立てとして環状路線を対象とし、複数路線と環状路線を連結した路線に拡張することで完了とするものである。

<p align="right">(<a href="#top">トップへ</a>)</p>

## 環境

<!-- 言語、フレームワーク、ミドルウェア、インフラの一覧とバージョンを記載 -->

| 言語・フレームワーク  | バージョン |
| ------------------- | --------- |
| Java SE             | 17        |
| Apache Tomcat       | 9.0       |
| MariaDB             | 10.4.21   |
| XAMPP               | 8.0.11    |
| Pleiades All in One | 2023-12   |

Apache Tomcat は Pleiades All in One にバンドルされたもの、MariaDB は XAMM にバンドルされたものをそれぞれ利用する。

<p align="right">(<a href="#top">トップへ</a>)</p>

## ディレクトリ構成

<!-- Treeコマンドを使ってディレクトリ構成を記載 -->

❯ tree
<pre style="font-family: 'courier new';">
.
├─.settings
├─src
│  └─main
│      ├─webapp.......JSP群を格納
│      │  ├─WEB-INF
│      │  │  └─lib....データベース関連やJSTLなどのjarファイル群を格納
│      │  └─META-INF
│      └─java
│          ├─app......アプリケーションのJavaソースコード群
│          └─test.....JUnitテストケース群
├─build
│  └─classes
│      ├─app
│      └─test
└─docs................開発用ドキュメント群
    ├─db..............データベース関連設計書群
    │  ├─scripts......データベース構築用SQLスクリプト群
    │  └─erd..........データベース定義書群
    └─dev
        └─uml.........UMLダイアグラム群

</pre>
<p align="right">(<a href="#top">トップへ</a>)</p>

## 開発環境構築

### データベース構築
	1. カレントディレクトリを %root%\docs\db\script ディレクトリに変更する。

	2. 以下のコマンドにて mysql コマンドラインクライアントを起動する。

			$> mysql -u root -p root

	3. 以下のコマンドにてデータベースを構築する。

			mariaDB[faredb]> source create_db.ddl
			mariaDB[faredb]> source create_tables.ddl
			mariaDB[faredb]> source insert.dml

<!-- コンテナの作成方法、パッケージのインストール方法など、開発環境構築に必要な情報を記載 -->

### 動作確認

http://127.0.0.1:8080/fare-calc-sys/test にアクセスできることによってデータベースの構築を成功とする。

### 環境変数の一覧

| 変数名                 | 役割                    | 設定値  | 
| ---------------------- | -----------------------| -------------|
| MYSQL_ROOT_PASSWORD    | MySQL のルートパスワード | root        |
| MYSQL_DATABASE         | MySQL のデータベース名   | faredb      |
| MYSQL_USER             | MySQL のユーザ名        | fareusr      |
| MYSQL_PASSWORD         | MySQL のパスワード      | himitu       |
| MYSQL_HOST             | MySQL のホスト名        | localhost    |
| MYSQL_PORT             | MySQL のポート番号      | 3306         |

<p align="right">(<a href="#top">トップへ</a>)</p>
