# Ecommerce-API-REST-SOAP

>An E-Commerce RESTful and SOAP web service built using JAX-RS and JAX-WS.

# 📃 Documentation
[Postman RESTful API Docs](https://documenter.getpostman.com/view/14309492/Uyxbq9MJ)

[SOAP-UI project]()

# Features
* HATEOAS
* Custom ExceptionMappers
* Content negotiation (support for both XML and JSON payloads and responses)
* SOAP 1.2

# Technologies used
* JAX-RS (Jersey)
* JAX-WS (Metro)
* JSON-B
* JAX-B
* Maven
* Tomcat
* Intellij IDEA Ultimate
* Postman
* SOAP-UI

# Run with Maven
**Maven**

* Change the configuration of Tomcat in `pom.xml`. 
* Deploy the application using the following maven command:
```
mvn clean compile tomcat7:redeploy
```
* REST: change the `{{host}}` variable in the Postman collection environment variables to match the port you chose for your Tomcat deployment
* SOAP: import the project into SOAP UI

**MySQL**
* Create a database schema and provide the username and password in the persistence.xml
* Hibernate will automatically create the tables for you
* Run DatabasePopulator.java to populate the database with some test data

# Contributors
* []()
