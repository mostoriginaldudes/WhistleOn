# WhistleOn

![휘슬온2021_logo_resize](https://user-images.githubusercontent.com/46016511/108075736-0bd41180-70ae-11eb-8c35-e49b26a67ebd.png)

### 팀 노션

* **서비스에 대한 더욱 자세한 내용은 아래 링크를 꼭! 참조해주세요**

* [휘슬온 팀 노션](https://www.notion.so/eff44d91cd91412287078fe8c7b8ec92)

### 프로젝트 소개

* **팀원 소개**
  
  ⚽️ 강상우 (Backend Developer)

  ⚽️ 유대상 (Frontend Developer)


* **서비스 소개**
  <pre>🎯 축구 경기 매칭 플랫폼</pre>


* **기술 스택**
  
  * **Backend**
    * *language:* Java11
    * *Framework:* Spring Boot2.4
    * *DB:* MariaDB
    * *Server:* AWS ec2
    * *Search Engine:* Elastic Search
    * *Test:* JUnit5
  
  <br>

  * **Frontend**
    * *language:* JavaScript
    * *Framework*: React.js
    * *StateManagement*: React-Redux
    * *CSS Precompiler*: Sass(SCSS)
    * *Bundler*: Webpack5
    * *Test*: Jest
  

### 개발 규칙

1. **커밋 메시지**

- 영문만을 사용
- 동사 사용하여 동작 설명
- 명사 사용하여 상세정보 설명
  
 <pre>
    # 변경사항의 동작을 아래의 <span style="color: red;">동사(Verb)</span>로 설명

    ⚽️ Add 파일, 폴더 또는 소스코드를 추가하는 경우
    👋🏻 Remove 파일, 폴더 또는 소스코드를 삭제하는 경우
    🚗 Replace 파일 또는 폴더를 교체하는 경우
    🟡 Rename 파일 또는 폴더의 이름을 변경하는 경우
    🚖 Move 파일, 폴더 또는 소스코드를 이동하는 경우
    🍀 Implement 특정한 모듈이나 라이브러리 등을 사용하여 구현하는 경우
    🚥 Update 코드를 수정할 경우
    📕 Revise 문서를 개정하는 경우
    ⛑ Refactor 성능 개선 또는 중복 제거 등의 소스코드 개선이 발생한 경우
    ⭐️ Simplify 복잡한 코드를 단순화하거나 Refactor보다 약한 수정의 경우
    🚑 Fix 버그, 에러, 오타 등을 수정한 경우
    🎯 Verify 검증 코드를 넣는 경우

    # 변경 내역의 상세정보를 <span style="color: red;">명사(Noun)</span>로 설명

    e.g. 
      Fix typo 오타 수정
      Remove unsused variable 사용하지 않는 변수 제거
 </pre>

2. **브랜치**

    <pre>
        Whistle On
          ├─main
          ├─release
          ├─hotfix
          └─develop
              ├─frontend
              │ ├─frontend_feature/function1
              │ └─frontend_fix/function1
              │
              └─backend
                ├─backend_feature/function1
                └─backend_fix/function2
    </pre>

3. **코딩 컨벤션**

     * **Frontend**
  
       * `.js`

         * *파일명:* kebab-case

         * *변수:* camelCase

         * *함수:* camelCase

       * `.jsx`

         * *파일명:* PascalCase

         * *컴포넌트명:* PascalCase

         * *변수명:* camelCase

         * *함수명:* camelCase

       * `.scss`

         * *파일명:* kebab-case

         * *모듈명:* _prefix
         
         * *클래스명:* BEM

         * *변수명:* kebab-case

         * *함수명:* kebab-case

         * *믹스인명:* kebab-case

       * `.test.js`

         * *파일명:* kebab-case

         * *변수명:* camelCase

         * *함수명:* camelCase

       * `.png | .jpg | .jpeg | .svg`

          * *파일명:* kebab-case



     * **Backend**
     
       * Function / Variable 네이밍 **camelCase** 로 작성

         ```java
         // ex
         String userName = "WhistleOn";
         public void getUserName() {
           return;
         } 
         ```

       * Package 네이밍 소문자 작성

         `ex) com.hala.whistleon.controller`
      
       * Class 네이밍 **PascalCase** 로 작성

         ```java
         // ex
         public Class WhistleOn{
           //..
         }
         ```

       * 배열, 리스트 등의 시퀀스 사용 시 복수형 사용
         
         ```java
         List<User> users = new ArrayList<>();
         ```

       * forEach 사용 시, 원소는 단수형 사용

         ```java
         for(int user : users){
           // ...
         } 
         ```
       
       * 인스턴스 네이밍은, 클래스 이름을 그대로하여 **camelClass** 형태로 작성

         ```java
         WhistleOn whistleOn = new WhistleOn();
         ```
       
       * Enum(열거형)과 상수 네이밍 **UPPER_CASE_SNAKE_CASE** 로 작성

         ```java
         // ex
         public static final String USER_NAME = "WhistleON";
         ```
   
       * DB 테이블 **snake_case** 로 작성

         ```SQL
         CREATE TABLE `whistle_on`(
           ...
         )
         ```

       * 그외 변수 네이밍 규칙 (직관적으로 이해가 되는 변수 이름만 사용)

        ```
        인덱스 번호 -> idx
        카운트 변수 -> cnt
        ```

        

