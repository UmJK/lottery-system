# Lottery System Microservice

## Description
This project is a simple lottery system implemented as a REST microservice using Java and Spring Boot. The service allows users to create, amend, and check the status of lottery tickets based on specific game rules.

## Setup Instructions

### Prerequisites
- Java 17
- Maven 3.6 or higher

### Build and Run
1. Clone the repository:
   git clone <repository-url>
   cd lottery-system

2. Build the project:
   mvn clean install

3. Run the application:
   mvn spring-boot:run
   The service will be accessible at http://localhost:8080.

###UnitTesting
mvn test

The tests cover the main functionality of the service, including:

	Creating tickets
	Amending tickets
	Checking the status of tickets
	Handling errors such as invalid ticket IDs

##Endpoints

	POST /ticket: Create a new ticket
	GET /ticket: Retrieve a list of all tickets
	GET /ticket/{id}: Retrieve a specific ticket by ID
	PUT /ticket/{id}: Amend an existing ticket by adding new lines (if status is not checked)
	PUT /status/{id}: Check the status of a ticket

## Swagger using Springdoc OpenAPI
   http://localhost:8080/swagger-ui/
   


##Additional Notes
	This implementation uses an in-memory H2 database for persistence. For production, this can be replaced with a MySQL or PostgreSQL database by updating the application.properties.
	The service is modular and can be easily extended for additional functionality.