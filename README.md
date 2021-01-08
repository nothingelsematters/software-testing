# Software Testing

- [Software Testing](#software-testing)
  - [Frontend](#frontend)
  - [[TODO] Frontend e2e testing](#todo-frontend-e2e-testing)
  - [Backend](#backend)
  - [CI/CD](#cicd)
  - [[TODO] Workshop performance testing](#todo-workshop-performance-testing)
  - [[TODO] Reporting integration (?!)](#todo-reporting-integration-)

## Frontend

+ [React framework](https://reactjs.org/) application ([client/](client/))
+ Several routing pages and a service that communicates with the [node.js](https://nodejs.org/) backend ([api/](api/))
+ Unit and component tests
+ **Advanced**: Authorization \
  *[TODO]*: improve

## [TODO] Frontend e2e testing

+ At least 5 tests using [Cypress](https://cypress.io) and [Playwright](https://playwright.tech) frameworks
+ **Advanced**: Authorization tests

## Backend

+ [Kotlin](https://kotlinlang.org/) + [Spring](https://spring.io/) + [PostgreqsQL](https://www.postgresql.org/) ([back/](back/))
+ Unit and Component tests
+ [MockK](https://mockk.io/): mocking library for Kotlin
+ [TestContainers](https://testcontainers.org/) for one database test
+ Integration with UI
+ *[TODO]* **Advanced**: Authorization tests

## CI/CD

+ [Github Action](https://github.com/features/actions) for testing UI and Backend on push
+ *[TODO]* **Advanced**: Deploy UI+BE application

## [TODO] Workshop performance testing

+ Publish result in the repository
+ **Advanced**: Github Action CI
+ **Bonus**: Allure report generation

## [TODO] Reporting integration (?!)
