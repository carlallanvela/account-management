# account-management

This is a sample application requested from ANZ Wholesale Engineering. 

This project is created using Java 8 and SpringBoot. Persistence is implemented using H2 in memory database.

The APIs enables user to GET, POST and DELETE Accounts and GET, POST Transactions under Accounts.

# account-management-ui

The frontend for this application is created for these APIs can be found on the link below. 

The frontend application is created using Angular 8.

Please see READ ME on how to use and deploy:
https://github.com/carlallanvela/account-management-ui

# Live UI in AWS

Deployed a working frontend to AWS S3. It connects to AWS Elastic Beanstalk to access APIs. UI can be accessed via: 
http://account-management-ui.s3-website.us-east-2.amazonaws.com/

# Live Endpoints in AWS

Deployed a working version to AWS Elastic Beanstalk. 

APIs can be accessed via: 
http://account-management-dev.us-west-2.elasticbeanstalk.com

Sample requests:
View Accounts:
`http://account-management-dev.us-west-2.elasticbeanstalk.com/jpa/accounts`

View Transactions:
`http://account-management-dev.us-west-2.elasticbeanstalk.com/jpa/accounts/10002/transactions`

## Development server

To run project, go to AccountManagementApplication.java and run as SpringBoot Applciation. Upon server start  up, new Accounts and Transactions are inserted using data.sql (H2 in memory database) located in src/main/resources.

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

## TODOs
1) Code Clean Up
2) Additional Functionalities
