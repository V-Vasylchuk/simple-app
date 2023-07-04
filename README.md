# User Management Application

This is a user management application that allows users to register, 
login, and manage their profiles. It is built using a web framework 
and utilizes a database to store user information. The application 
also includes error handling and validation for user inputs.

## Features

`User Registration:` Users can register by providing their name, email address, and password. 
The registration form performs basic validation on the input fields to ensure data integrity.

`User Login:` Registered users can log in to the application using their credentials. 
The login system securely stores and verifies user passwords to authenticate users.

`User Profile:` Once logged in, users have access to their profile information. 
They can view and update their name, email address and password as needed. 
The application ensures data privacy and security by `Jwt token`.

## Getting Started
* Clone repository from GitHub
* Create an empty database
* In `application.properties` replace the values of the properties with the appropriate values for your database setup
* Build the project using Maven: `mvn clean install`
* Use `Postman` to send http requests;
* Also, I prepared some `Postman request collection` аor your convenience: [LINK](https://www.postman.com/vasylchuk/workspace/colections-for-testing-by-bbc/collection/3329996-685b76e8-7daa-4d40-94ea-3b647c5a8c2e?action=share&creator=3329996)
* Video tutorial how to work with Postman and the collection of requests that I created. [VIDEO](https://komododecks.com/recordings/zbIy5SIKGbrSSEgSmiTf)

## Structure description:
* `config:` classes for configuration of application operation;
* `controller:` controllers for endpoints:
  * _AuthenticationController_ - `POST` `/register` - Register new user by providing their name, email address, and password.
  * _AuthenticationController_ - `POST` `/login` - Authenticate a user by providing their email address and password.
  * _UserController_ - `GET` `/users/me` - Retrieve user information for the authenticated user.
  * _UserController_ - `PUT` `/users/me` - Update the user's profile information for the authenticated user.
* `repository:` - repository with CRUD methods in the database;
* `dto:` - wrapper for model objects to unify the requests and responses in controllers;
* `mapper:` - converts model objects into DTO objects and vice versa;
* `exception:` custom exceptions;
* `model:` Plain Old Java Objects are defining operational data (objects) type and structure;
* `service:` provides business logic, includes mappers for dto;
* `security.jwt:` contains security settings using the JWT authentication;
* `resources:` contains configuration file with MySQL and Hibernate params;

## Used technologies:
* Java 17
* Apache Maven
* Spring Boot
* Spring Security
* Hibernate
* MySQL
* Lombok
## Author
[_Vasyl Vasylchuk_](https://www.linkedin.com/in/vasyl-vasylchuk-632303273/)


