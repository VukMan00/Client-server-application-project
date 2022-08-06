# Client-server-application-project
Client-server application for testing the validity of covid passes 

## Table of contents
* [General info](#general-info)
* [Restrictions](#restrictions)
* [How to run](#run)

## General info
This application was written in Java programing language(eclipse development environment). Purpose of this application is to provide people possibility to generate their covid passes, and also see validation of their covid passes, and for that every client must be registered to the sistem. Application has other basic functionalities, besides validation of covid passes and their creation, such as:
* Registeristration - every client must be register if they want to generate covid passes and test their validation.
* Login - if client is registered, they have opportunity to change their stand on covid vaccine. For example, if client registered in system as person who hasn't taken the second dose of vaccine, in login functionality he can change that and also their stand on third dose. Also client can be registered as person who hasn't been vaccinated and with this functionality you can change that. Also, application has one special client, administrator who can see the list of all registered clients and their vaccination status(clients are printed in text files), number of clients with one dose, two doses and three and also can see vaccination dates of clients with two doses(username, password, jmbg, email, vaccionation status etc.). Username for  administrator is "admin" and password is "admin" and adminstrator is me, Vuk Manojlovic.
* Covid pass check - where you check validation of the covid pass.
* Generate a covid pass - where you can generate your covid pass.

The opportunity to create this project was given by subject at our faculty for those who are interested in client-server communication on TCP streaming socket, how they work, how they connect and how they transfer the data to each other etc. and with that also comes along higher grade.

## Restrictions
* Application is created in 2021 and is base on that year and all vaccination dates must be in 2021.
* Manufacturer of second vaccine must be same as manufacturer of first vaccine. We use manufacturers avaliable in Serbia.
* Vaccination date of second vaccine must be 3 weeks after the first.
* Vaccination date of third vaccine must be 6 months after the second.
* The Covid pass is valid if the client has received the first two doses.

## How to run
For this application to work, the server must first establish a connection with the client. First we ran server class('ServerMultiple') and then client class('MultipleKlijent'). Server can establish multiple connenctions with client by runing client class. Now, in eclipse, we have two consoles, one for server and one that we use, client console and in that console we will input necessary data for server to processed. For example, after the registration in server console we will have printed data of the following client who registered, for the login we will have the data of the one who just logged in. We can always stop application from running by clicking a button 'terminate' - red button. Also with Exit option on the client side, we can close the connection between that client and server.
