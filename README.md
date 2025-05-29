
# customs-guarantee-account-perf-tests
Performance test suite for the `CUSTOMS_GUARANTEE_ACCOUNT_FRONTEND - allows the user to download and view guarantee account details`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.


# Pre-requisites

Make sure the following tools:technologies and configurations are in place before running tests:

| **Technology**             | **Description**                                                                           |
|----------------------------|-------------------------------------------------------------------------------------------|
| **Java (JDK 11 or 21)**    | Required to compile and run Scala-based projects                                          |
| **sbt (Scala Build Tool)** | To compile and run the Play application & execute tests                                   |
| **Scala**                  | Required to compile & run the application. Ensure the version matches what's in build.sbt |
| **Service Manager (sm2)**  | See [service manager](https://github.com/hmrc/service-manager) To start services locally                                      |
| **Docker**                 | Makes it easier to run certain software tools locally without having to install them      |
| **Mongo DB**               | Set up [Mongo DB](https://www.mongodb.com/docs/manual/installation/)using Docker                                                        |
| **Git**                    | To clone the repository and manage branches                                               | 


# Start services locally
If you're executing the test scripts locally:
- Ensure all required services are up and running
- Start the service manager to launch all dependencies (Start all services)
- Use the following commands to start services locally

| **Command**                                        | **Description**                                                                                           |
|----------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| `sm2 -start CUSTOMS_FINANCIALS_ALL`                | Start all services                                                                                        |
| `sm2 -start CUSTOMS_GUARANTEE_ACCOUNT_FRONTEND`    | Start a particular service                                                                                |
| `sm2 -start SERVICE_1 SERVICE_2`                   | Start multiple services                                                                                   |
| `sm2 -stop-all`                                    | Stop all services                                                                                         |
| `sm2 -stop CUSTOMS_GUARANTEE_ACCOUNT_FRONTEND`     | Stop a particular service                                                                                 |
| `sm2 -restart CUSTOMS_FINANCIALS_ALL`              | Restart all services                                                                                      |
| `sm2 -restart CUSTOMS_GUARANTEE_ACCOUNT_FRONTEND`  | Restart particular service                                                                                |
| `sm2 -restart SERVICE_NAME -latest`                | When restarting a service or profile,you can specify -latest to check for a new version before restarting |                                              
| `Sm2 -s (or) sm2 -status`                          | Checking the status of services (-s or -status)                                                           |
| `Sm2 -list`                                        | List all available services                                                                               |


# Running the Performance tests

## Before executing the tests, ensure you have:
- Appropriate [drivers installed](#installing-local-driver-binaries) - to run tests against locally installed Browser
- Clone the repository and pull the latest changes
- Required service dependencies are running via Service Manager `sm2 -start CUSTOMS_FINANCIALS_ALL`


## Run the test against local environment

### smoke Test

It might be useful to try the journey with one user to check that everything works fine before running the full performance test
```
sbt -Dperftest.runSmokeTest=true -DrunLocal=true gatling:test 
```
### Entire suite
```
sbt -DrunLocal=true gatling:test 
```

## Run the test against staging environment

### smoke Test

```
sbt -Dperftest.runSmokeTest=true -DrunLocal=false gatling:test
```

### Entire suite

To run a full performance test against staging environment, run the Jenkins job [customs-guarantee-account-performance-tests](https://performance.tools.staging.tax.service.gov.uk/job/CDS/job/Financials/job/customs-guarantee-account-performance-tests/)


## Logging

The template uses [logback.xml](src/test/resources) to configure log levels. The default log level is *WARN*. This can be updated to use a lower level for example *TRACE* to view the requests sent and responses received during the test.


# Source code formatting
### Scalafmt
This repository uses [Scalafmt](https://scalameta.org/scalafmt/), a code formatter for Scala. The formatting rules configured for this repository are defined within [.scalafmt.conf](.scalafmt.conf).
- To apply formatting to this repository using the configured rules in [.scalafmt.conf](.scalafmt.conf) execute `sbt scalafmtAll`
- To check files have been formatted as expected execute ` sbt scalafmtCheckAll scalafmtSbtCheck`
- [Visit the official Scalafmt documentation to view a complete list of tasks which can be run.](https://scalameta.org/scalafmt/docs/installation.html#task-keys)


# Helpful commands
| Command                                       | Description                     |
|-----------------------------------------------|---------------------------------|
| `sbt compile`                                 | Compiles the project            |
| `sbt clean compile`                           | Cleans and compiles the project |


# Useful links
### [MDTP Handbook](ttps://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/index.html#developer-set-up)
### [MDTP Catalogue](https://catalogue.tax.service.gov.uk/teams/CDS%20Financials)
### [Jenkins - customs-guarantee-account-performance-tests](https://performance.tools.staging.tax.service.gov.uk/job/CDS/job/Financials/job/customs-guarantee-account-performance-tests/)
### [Stub Data](https://confluence.tools.tax.service.gov.uk/pages/viewpage.action?pageId=1022821228)
