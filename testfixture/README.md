# [Spring] 멀티모듈 구조에서, 다른 모듈의 테스트 클래스 사용하기

> Gradle Test Fixture 사용해서 테스트 중복코드를 줄일 수 있어요.
>

멀티모듈 구조에서 다른 모듈의 Test 폴더를 사용할 수 없으니, 중복되는 클래스가 각각의 모듈의 Test 폴더에 포함되었어요.

저는, Gralde 멀티모듈 구조에서, 의존을 하는 모듈의 Test 폴더까지 사용하고 싶었어요.
이때 사용할 수 있는 것이 Gradle Test Fixture입니다 ㅎㅎ

## TestFixutre 적용

먼저 test fixture를 사용할 모듈에서 testfixture 의존성을 넣어요. (domain-cvi/build.gradle)

```java
plugins{
    id 'java-library'    //**new**!!
    id 'java-test-fixtures' //**new**!!
    id 'maven-publish' //**new**!!
}

bootJar { enabled = false }
jar { enabled = true }

dependencies {
    implementation project(path:':common-cvi', configuration: 'default')
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'mysql:mysql-connector-java'
    implementation 'org.hibernate.validator:hibernate-validator:6.2.0.Final'

    //migration
    implementation 'org.flywaydb:flyway-core:7.11.2'

    //testFixture
    testFixturesImplementation 'org.springframework.boot:spring-boot-starter-test'  //new!!
}
...
```

`testFixturesImplementation`에 의존성이 추가된 것을 유의해주세요 (testFixture에서 사용할 라이브러리를 지정해줘야 합니다. )

![https://user-images.githubusercontent.com/48986787/137915781-0029546d-c7b5-42bc-95d4-ee34a117640a.png](https://user-images.githubusercontent.com/48986787/137915781-0029546d-c7b5-42bc-95d4-ee34a117640a.png)

testfixtures 폴더를 생성한 후, 멀티모듈 구조에 맞게 클래스를 생성해요.
(com/cvi/CustomParameterizeTest)

이제 이를 사용할 모듈에서 아래와 같이 의존성을 추가해주면 끝! (app-cvi/build.gradle)

```java
version = '0.0.1-SNAPSHOT'

dependencies {
    implementation project(path:':common-cvi', configuration: 'default')
    implementation project(path:':domain-cvi', configuration: 'default')
    testImplementation(testFixtures(project(":domain-cvi")))    **//new!!
...**
```

일줄 알았더니...

![https://user-images.githubusercontent.com/48986787/137917455-ad051913-5b94-443c-b72d-b62c94ef2b9f.png](https://user-images.githubusercontent.com/48986787/137917455-ad051913-5b94-443c-b72d-b62c94ef2b9f.png)

```java
Could not determine the dependencies of task ':app-cvi-api:test'.
> Could not resolve all task dependencies for configuration ':app-cvi-api:testRuntimeClasspath'.
   > Could not resolve project :domain-cvi.
     Required by:
         project :app-cvi-api
         project :app-cvi-api > project :domain-cvi-oauth-service
         project :app-cvi-api > project :domain-cvi-publicdata-service
         project :app-cvi-api > project :domain-cvi
      > Module 'com.backjoongwon:domain-cvi' has been rejected:
           Cannot select module with conflict on capability 'com.backjoongwon:domain-cvi:0.0.1-SNAPSHOT' also provided by [com.backjoongwon:domain-cvi:0.0.1-SNAPSHOT(default), com.backjoongwon:domain-cvi:0.0.1-SNAPSHOT(runtimeElements)]
   > Could not resolve project :domain-cvi.
     Required by:
         project :app-cvi-api
      > Module 'com.backjoongwon:domain-cvi' has been rejected:
           Cannot select module with conflict on capability 'com.backjoongwon:domain-cvi:0.0.1-SNAPSHOT' also provided by [com.backjoongwon:domain-cvi:0.0.1-SNAPSHOT(default), com.backjoongwon:domain-cvi:0.0.1-SNAPSHOT(runtimeElements)]

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.
```

위와 같은 예외가 발생하네요...

## 예외의 이유

멀티모듈 구조를 완전히 잘못잡고 있었어요.
이전까지는

```java
implementation project(path:':domain-cvi', configuration: 'default')
```

이렇게 다른 멀티모듈의 의존성을 끌어오고 있었는데, 이는 하위 의존성을 모두 다 가져오게 돼요.
즉, app-cvi-api 에서 많은 모듈을 의존하고 있는데,

![https://user-images.githubusercontent.com/48986787/133215721-f0a9a634-ee3c-46d2-ac95-e073f12bd46f.png](https://user-images.githubusercontent.com/48986787/133215721-f0a9a634-ee3c-46d2-ac95-e073f12bd46f.png)

여기서, app-cvi-api가 의존하고 있는 모듈들 또한 domain-cvi를 의존하며, 의존성이 엄청나게 겹쳤던 것이에요!!!

전체 모듈의 모듈 관련 의존성을

```java
# app-api-cvi
dependencies {
    implementation(project(":common-cvi"))
    implementation(project(":domain-cvi"))
    implementation(project(":domain-cvi-oauth-service"))
    implementation(project(":domain-cvi-publicdata-service"))
    implementation(project(":domain-cvi-scheduler"))
    implementation(project(":domain-cvi-aws-s3-service"))
```

혹은

```java
# domain-cvi
dependencies {
    compile(project(":common-cvi"))
    compile 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'mysql:mysql-connector-java'
    implementation 'org.hibernate.validator:hibernate-validator:6.2.0.Final'
```

이렇게 바꿨어요.

## 정리

그렇다면, 차이점이 뭘까요?
클라이언트 모듈 `A`(app-api-cvi), 의존 모듈 `B`(domain-cvi)라 정할게요.
`A` 모듈에서 `B` 모듈을 implementation으로 의존했다 가정하면(implementation(project(":domain-cvi"))),`B 모듈만 의존`한다는 것이에요.
만약 `C`라는 모듈이 `A`를 의존하려 하려 한다면, `B` 모듈을 가져오지 못해요.
즉, implementation이라 선언된 것은, 모듈 내에서만 의존해서 쓰고, 자신을 의존하려는 모듈에는 제공하지 않아요.

`B` 모듈 내의 의존성 (jpa)은 comile이라 선언되어 있어요. 이는, `A` 모듈이 `B` 모듈을 의존할 때, `B` 모듈은 jpa의존성도 제공해주겠다는 말이에요. (`A` 모듈은 `B` 모듈을 의존하며 JPA의존성도 사용할 수 있다. )

정말 어려웠어요... 지금까지 대충 의존성을 주입하려 했었어요(반성합니다 ㅠㅠ)
compile과 implementation의 차이를 알아가게 된 계기라 생각해요.
testFixure의 자세한 내용은 꼭 `공식문서`를 통해 확인해주세요 :)

## Refer

[https://docs.gradle.org/6.8.3/userguide/java_testing.html#sec:java_test_fixtures](https://docs.gradle.org/6.8.3/userguide/java_testing.html#sec:java_test_fixtures)

[https://bottom-to-top.tistory.com/58](https://bottom-to-top.tistory.com/58)