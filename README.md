# account-management

This is a sample application requested from ANZ Wholesale Engineering. This project is created using Java 8 and SpringBoot. 
The APIs enables user to GET, POST and DELETE Accounts and GET, POST Transactions.

# account-management-ui

The frontend for this application is created for these APIs can be found on the link below. Please see READ ME on how to use:
https://github.com/carlallanvela/account-management-ui

## Development server

To run project, go to AccountManagementApplication.java and run as SpringBoot Applciation. Upon server start  up, new Accounts and Transactions are inserted using data.sql located in src/main/resources.

APIs can be accessed via http://localhost:8080
Example:
1) GET `localhost:8080/jpa/accounts/`
2) POST `localhost:8080/jpa/accounts/`
`BODY:
{
    "name": "test",
    "transactionDate": "2019-09-11T16:00:00.000+0000",
    "transactions": []
}`
3) GET `localhost:8080/jpa/accounts/10001/transactions`
4) POST `localhost:8080/jpa/accounts/10001/transactions`
`BODY:
{
    "description": "Account 6",
    "postDate": "2019-09-11T16:00:00.000+0000"
}`

## Swagger Documentation

To view Swagger Documentation to see all APIs, go to http://localhost:8080/swagger-ui.html#/.

## Unit Test

To run Unit Test, go to AccountRepositoryTest.java and run as JUnit Test. This tests basic functionality like findOne, findAll and save.
