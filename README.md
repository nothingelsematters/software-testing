# Software Testing

## Frontend

+ [React framework](https://reactjs.org/) application ([client/](client/))
+ Several routing pages and a service that communicates
  with the [node.js](https://nodejs.org/) backend ([api/](api/))
+ Unit and Component tests
+ [Lighthouse](https://developers.google.com/web/tools/lighthouse/)
  Accessibility, Best Practices, SEO: 100%
+ **Advanced**: Authorization

## Backend

+ [Kotlin](https://kotlinlang.org/) ([Gradle](https://gradle.org/)) +
  [Spring](https://spring.io/) +
  [PostgreqSQL](https://www.postgresql.org/) ([back/](back/))
+ Unit and Component tests
+ [MockK](https://mockk.io/): mocking library for Kotlin
+ [TestContainers](https://testcontainers.org/) database tests
+ [Allure report](https://docs.qameta.io/allure/) integration

## E2E testing

+ [Cypress](https://www.cypress.io/) framework test suites in JavaScript ([client/cypress/](client/cypress/))
+ [Playwright](https://playwright.dev/) framework test suites in TypeScript ([client/test/](client/test/))
+ [Selenide](https://selenide.org/) test suites in Kotlin ([selenide/](selenide/))
  with [Allure report](https://docs.qameta.io/allure/) integration
+ [Selenoid](https://aerokube.com/selenoid/latest/) integration
  > Download and run `./cm selenoid start` providing
  > `selenoid.url=http://localhost:4444/wd/hub` in
  > [properties](selenide/src/test/resources/properties.properties)

## CI/CD

+ [Github Action](https://github.com/features/actions) for linting and
  testing UI and Backend on push
+ **Advanced**: [Heroku](https://heroku.com/) application deploy

## Workshop performance testing

+ [Tinkoff Performance workshop](https://gitlab.com/tinkoffperfworkshop/):
  [Gitlab code repository](https://gitlab.com/tinkoff-performance-workshop-results/software-testing/)
  and [result diagrams and stuff](https://tinkoff-performance-workshop-results.gitlab.io/software-testing/)
