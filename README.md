# 有給管理システム yukyu-man  

有給管理システム yukyu-man  
https://yukyu-man.onrender.com

![alt text](image.png)

Spring Bootによるバックエンドによって開発した、有給管理システムです。


## 使用ライブラリ・ツール

- Spring Boot
- Java
- Apache Maven
- Thymeleaf
- HyperSQL Database
- JPA
- Tailwind CSS
- VS Code
- Git／GitHub
- Font Awesome


## DB設計

### 休暇一覧テーブル

- テーブル論理名：休暇一覧
- テーブル物理名：vacation_list

|No.|カラム論理名|カラム物理名|データ型|PK|NotNull|説明|
|--|--|--|--|--|--|--|
|1|休暇ID|vacation_id|INTEGER|✅|✅|休暇ID|
|2|休暇種別|vacation_type|TEXT||✅|休暇種別（年次・夏季・バースデーなど）|
|3|休暇日数|number_of_days|INTEGER||✅|与えられた休暇の合計日数|
|4|休暇残日数|days_remaining|INTEGER||✅|残っている休暇日数|
|5|休暇取得期限|vacation_deadline|DATE||✅|休暇の有効期限|

### 休暇取得履歴テーブル

- テーブル論理名：休暇取得履歴
- テーブル物理名：use_data

|No.|カラム論理名|カラム物理名|データ型|PK|NotNull|説明|
|--|--|--|--|--|--|--|
|1|休暇取得ID|vacation_get_id|INTEGER|✅|✅|取得した休暇のＩＤ|
|2|休暇取得日|vacation_get_date|DATE||✅|休暇を取得した日付|
|3|休暇種別|vacation_type|TEXT||✅|休暇種別（年次・夏季・バースデーなど）|
|4|休暇区分|vacation_section|TEXT|||休暇種別で年次を選んだ場合、全日・半休|
|5|メモ|vacation_get_note|TEXT|||自由記述メモ|


