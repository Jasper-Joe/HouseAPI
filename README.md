# VGSI House API

## How to build and run the solution 

### Prerequisites

The following items should be installed in your system:
    -  Java 8
    -  git command line tool(https://help.github.com/articles/set-up-git)
    -  Prefered IDE: 
        -  Spring Tools Suite (STS)
        -  IntelliJ IDEA

### Steps:

1. On the command line
```
git clone https://github.com/Jasper-Joe/HouseAPI
```
2. Inside STS
Run the application  by right clicking on HouseAPI  under Package Explorer and <code>Run As -> Spring Boot App</code>
You can run it from the command line:
You can access HOUSE API with:  http://localhost:8080/

Integrated Jenkins with github Webhooks
Connect Jenkins to private Github repo

## Notes about any improvements you'd like to make but did not have time to make
Authentication is not implemented in the APP, anyone with the URL could get, post and update the data. I could secure the API with Spring Security and JWTs so that only authorized users could access the dataset. 

## Notes on any API design choices
I used Spring Boot Framework to develop this REST API, because it can shorten the code length and provide an easy way to develop Web Applications and it automatically configures the classes based on the requirement while reducing boilerplate code.  
In its default configuration, House API uses an embedded, in-memory database H2 which gets populated at startup with data extracted from CSV file. 

## Notes on the security implications of the implementation

### Close CSV file
Since we need to read data from CSV file when the application starts, we need to close all IO-based streams after reading the file. If we forget to close the stream, the underlying channel will remain open and then we might have resource leak. 

### Authentication
There is no authentication implemented in the APP, so anyone with the URL can get, post and update the data stored in the database. We could secure the API using Spring Security and JWTs so that only authorized users could access the database. 

