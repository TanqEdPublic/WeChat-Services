# WeChat-Services 
 ***"TanqEd Public" Spring Boot project***

## About Project...

  The idea of the project is to develop Restful back-end application ([Maven](https://maven.apache.org/) & [Spring Boot](https://projects.spring.io/spring-boot/)) that is able to server different client applications, i.e., web browser application written using [Flask](http://flask.pocoo.org/) and desktop application on [JavaFX](http://www.oracle.com/technetwork/java/javase/overview/javafx-overview-2158620.html).
  
  For more details, please go through the **Design Documentation**
  
### Three branches are included:
* master (Server)
* Web-Client (flask client with server)
* JavaFX-Client (desktop/javaFX client with server)

## General Installations:

* [Installing Apache Maven](http://maven.apache.org/install.html)
* [Installing MongoDB Server](https://github.com/TanqEdPublic/WeChat-Services/wiki/MongoDB-installation-for-Windows-10---Mac-OSX) 
* [Installing MySQL Server](https://dev.mysql.com/doc/refman/5.7/en/windows-installation.html)
* [Installing required tools](https://github.com/TanqEdPublic/WeChat-Services/wiki/Required-tools-for-Eclipse-&-IntelliJ-IDEA)

## Feature Demo Video
[YouTube Link](https://www.youtube.com/watch?v=O1MenGMSBkI&feature=youtu.be)

## Simple User Guide

you can only run the Server which is in the Master Branch, make sure databases(Mysql & MonfoDB) works correctly before you run this Server project by using IDEs (such as Eclipse). Also, you can choose to package it to war file running with extra tomcat server or package it to jar file to run by using correct JRE. 

> Tip: **Postman** is a good tool for testing the Server.

At the same time, two clients' projects are provided in another two branches. 

For browser client, [python3.0](https://www.python.org/downloads/) environment is the essencial component.

For desktop client, Javafx is available as fully integrated feature of the JRE and JDK starting from java SE 7

Overall, not only browser clients but also desktop clients can communicate with each other through the Server.

> Tip: We just suggest to set up the server online using AWS/Azure, then the client can communicat with the server by ip address. For the client projects we provided, we used our own AWS virtual mashine to run the server, make sure you changed the valid ip address you have before testing the clients.

### Possible Errors

* MAC OSX Error (MongoDB) --> complains that there is no /data/db folder, click [solution](http://stackoverflow.com/questions/7948789/mongodb-mongod-complains-that-there-is-no-data-db-folder)
* [Not relevant to a project anymore] For InteliJ IDE in order to recognize MySQL exceptions, it is required to install [MySQL JDBC Connector](https://dev.mysql.com/downloads/connector/j/).   


