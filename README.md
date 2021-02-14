# WhistleOn

### 프로젝트 소개

* **팀원 소개**
  
  ⚽️ 강상우 (Backend Developer)

  ⚽️ 유대상 (Frontend Developer)


* **서비스 소개**
  <pre>🎯 축구 경기 매칭 플랫폼</pre>


* **기술 스택**
  
  * **Backend**
    * *language:* Java
    * *Framework:* Spring
    * *DB:* MySQL
    * *Server:* AWS
    * *Test:* JUnit
  
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

    Add 파일, 폴더 또는 소스코드를 추가하는 경우
    Remove 파일, 폴더 또는 소스코드를 삭제하는 경우
    Replace 파일 또는 폴더를 교체하는 경우
    Rename 파일 또는 폴더의 이름을 변경하는 경우
    Move 파일, 폴더 또는 소스코드를 이동하는 경우
    Implement 특정한 모듈이나 라이브러리 등을 사용하여 구현하는 경우
    Update 코드를 수정할 경우
    Revise 문서를 개정하는 경우
    Refactor 성능 개선 또는 중복 제거 등의 소스코드 개선이 발생한 경우
    Simplify 복잡한 코드를 단순화하거나 Refactor보다 약한 수정의 경우
    Fix 버그, 에러, 오타 등을 수정한 경우
    Verify 검증 코드를 넣는 경우

    # 변경 내역의 상세정보를 <span style="color: red;">명사(Noun)</span>로 설명

    e.g. 
      Fix typo 오타 수정
      Remove unsused variable 사용하지 않는 변수 제거
 </pre>

2. **브랜치**

    <pre>
      main
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
     
       * `.java`

         * 


       * `.class`

         * 
   


