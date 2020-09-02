# IMG Arena Tennis Service

## Requires
* Java 11

All other dependencies are configured in the repository.

## Frameworks Used
* Gradle
* Spring Boot
* H2 Database - to provide in memory database

## Build and Execute Tests

> ./gradlew clean build

## Running the Service 

> ./gradlew bootRun

Service will listen on port 8080.

## Test data

To prove the service, 2 test tournaments have been configured in the database. Each tournament has 3 matches. One tournament is scheduled in the future and the other has already passed. 

2 customers with licenses have also been configured. 
* Customer with id 123 has a license for the tournament that has already passed and license for a match in the future tournament.
* Customer with id 456 has a license for the future tournament and a license for match in the tournament that has already occurred.

The Rest API expects a header 'customer-id' to determine the customer to return.

## Notes

Please see FIXME comment around implementation decision where 0 minutes are between the current time and start of the match.

## Repository Structure
The repository structure follows an hexagonal architecture.
* tennis-core - the core service code 
* tennis-inbound-adapters - the REST API and Spring Boot configuration
* tennis-outbound-adapters - the Spring JPA and Liquibase configuration classes.

## Useful curl commands

> curl 'http://localhost:8080/v1/customer/my-matches' -H 'customer-id: 123' -H 'Accept: application/json' -X GET

> curl 'http://localhost:8080/v1/customer/my-matches?summaryType=AvB' -H 'customer-id: 123' -H 'Accept: application/json' -X GET

> curl 'http://localhost:8080/v1/customer/my-matches?summaryType=AvBTime' -H 'customer-id: 123' -H 'Accept: application/json' -X GET

> curl 'http://localhost:8080/v1/customer/my-matches' -H 'customer-id: 456' -H 'Accept: application/json' -X GET

> curl 'http://localhost:8080/v1/customer/my-matches?summaryType=AvB' -H 'customer-id: 456' -H 'Accept: application/json' -X GET

> curl 'http://localhost:8080/v1/customer/my-matches?summaryType=AvBTime' -H 'customer-id: 456' -H 'Accept: application/json' -X GET

