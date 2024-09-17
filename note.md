
# Spring Boot 初期構築

## 1. Spring Boot プロジェクト作成
### spring initializr
1. spring initializr (https://start.spring.io/) にアクセスする
2. 以下の設定で GENERATE （zip をダウンロード）する
    - 設定内容
        - Project : Maven
        - Language : Java
        - Spring Boot : 3.3.0
        - Project Metadata
            - Group : com.example
            - Artifact : springdemo
            - Name : （※Artifactの内容から自動で設定される）
            - Description : Demo project for Spring Boot
            - Package name : （※GroupとArtifactの内容から自動で設定される）
            - Packaging: Jar
            - Java : 17（※任意）
        - Dependencies
            - Spring Web (フロントとの通信に使用)
            - Thymeleaf (Viewのテンプレートエンジン)
            - Spring Boot Dev Tools (便利系)
            - Lombok (便利系)
3. ダウンロード後、任意のフォルダにファイルを丸ごと移動する
   - Gitで管理する場合は、この段階でクローン済みのフォルダ内に移動する



## 2. VSCode の設定 （初回のみ）
### 【任意】プロファイルの作成
新しくプロファイルを作成する。（例：Java_Springなど）

### 拡張機能のインストール
次の拡張機能をインストールする

- Extension Pack for Java
    - ID: vscjava.vscode-java-pack
- Spring Boot Extension Pack
    - ID: vmware.vscode-boot-dev-pack
- Gradle Extension Pack ※MavenではなくGradleを使用する場合にインストールする
    - ID: richardwillis.vscode-gradle-extension-pack


## 3. 初回実行
### 3-1. 実行のための最低限のファイルの配置
- アプリケーションを実行させるために、次のファイルを指定場所に作成する

#### A. RestControllerを使用する場合
##### A-1. HelloController.java の作成
- 以下の内容のファイルを作成する
- 格納ディレクトリ: `src/main/java/com/example/springdemo/`
- ファイル名: `HelloController.java`

```java
package ※ファイル作成時に自動で設定される;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {
    
    @GetMapping("/")
    public String index() {
        return "Hello Spring Boot!!";
    }
    
}
```

- アプリケーション実行に進む


#### B. テンプレートエンジンを使用する場合
##### B-1. HelloController.java の作成
- 以下の内容のファイルを作成する
- 格納ディレクトリ: `src/main/java/com/example/springdemo/`
- ファイル名: `HelloController.java`

```java
package ※ファイル作成時に自動で設定される;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
}
```

##### B-2. index.html の作成
- 以下の内容のファイルを作成する
- 格納ディレクトリ: `src/main/resources/templates/`
- ファイル名: `index.html`

```html
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>top page</title>
</head>
<body>
    <h1>Hello Page</h1>
    <p>this is sample page.</p>
</body>
</html>
```

- アプリケーション実行に進む


### 3-2. アプリケーション実行
#### 実行手順
1. F5キーを押す
2. VSCode内でターミナルが起動し、ターミナル内に以下のような表示が出て、実行状態となっていることを確認する
  - VSCode の右上に、「再起動」や「停止」などのコントロール用のバーが出ていれば実行状態

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
```

3. ターミナルの表示の下から2行目あたりに `Tomcat started on port 8080 (http) with context path '/'` のような文言があることを確認する
4. WEBブラウザを開き、`http://localhost:8080/` を開く (ターミナルで確認した文言が 8080 でない場合は、URLをその数値に合わせる)
5. ページの内容が表示されること
   - A. RestControllerの場合：returnで返した文字列が表示されればOK
   - B. テンプレートエンジンの場合：作成した `index.html` の内容 （Hello Page ／ this is sample page.） が表示されればOK

**停止する場合**
1. VSCode の右上に表示されているコントロール用のバーの「停止」を押せばOK
   - ターミナル上で `Ctrl + C` でもOK。


### 4. 本番用資材作成（ビルド）





