# account-management

This is a sample application requested from ANZ Wholesale Engineering. The APIs enables user to GET, POST and DELETE Accounts and GET, POST Transactions.

## Development server

To run project, go to AccountManagementApplication.java and run as SpringBoot Applciation. Upon server start  up, new Accounts and Transactions are inserted using data.sql located in src/main/resources.

APIs can be accessed via http://localhost:8080
Example:
GET localhost:8080/jpa/accounts/
POST localhost:8080/jpa/accounts/
GET localhost:8080/jpa/accounts/10001/transactions

## Swagger Documentation

To view Swagger Documentation to see all APIs, go to http://localhost:8080/swagger-ui.html#/.
