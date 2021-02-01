# VGSI House API

## How to build and run the solution 

### Prerequisites

The following items should be installed in your system:
* Java 8
* git command line tool(https://help.github.com/articles/set-up-git)
* Prefered IDE:  Spring Tools Suite (STS) or IntelliJ IDEA

### Steps:

1. On the command line

 Create a new directory. CD into that directory you just created. Then run:

```
git init
```

```
git clone https://github.com/Jasper-Joe/HouseAPI
```

2. Inside STS

```
File -> Open Project from File System -> Directory -> Choose directory you created
```

Run the application  by right clicking on HouseAPI  under Package Explorer and <code>Run As -> Configurations -> Click run</code>

3. Navigate to HOUSE API

You can access HOUSE API with:  http://localhost:8080/

4. Run Tests

Open the terminal, CD into HouseAPI directory, then run
```
$ npm install -g newman
```
```
$ newman run TestCases.json
```



## Notes about any improvements you'd like to make but did not have time to make

### Authentication
Authentication is not implemented in the APP, anyone with the URL could get, post and update the data. I could secure the API with Spring Security and JWTs so that only authorized users could access the dataset. 

### Connect Jenkins with Github WebHook
I alread set up the project url and repository url inside Jenkins. Currently this is a private repository, I created a SSH key so that Jenkins can connect to the repository. I could install Jenkins on AWS EC2 instance to get a public IP address for linking it with GitHub WebHook. In that case, whenever there is an update on github, Jenkins will run Postman test script automatically. 

### Add houseOwner model class
Since we only need 3 endpoints related to house resources data, I didn't create a house owner class to store their information. But in reality, it is necessary to create a house owner model with identifier because we need to store more personal information such as email, phone number, SSN, etc. House owners may also have duplicate names and we cannot distinguish them whithout unique IDs. 



## Notes on any API design choices

### Why Spring Boot?
I used Spring Boot Framework to develop this REST API, because it can shorten the code length and provide an easy way to develop Web Applications and it automatically configures the classes based on the requirement while reducing boilerplate code.  

### Architecture
* Presentation Layer: HouseController is built to handle the HTTP request, and to translate the JSON parameter to object.
* Business Layer: HouseResourceService is used to handle all the business logic.
* Persistence Layer: HouseResourceRepository is used to translate business objects from and to database. 
* Database Layer: Perform CRUD operations. 

The client can make the HTTP requests(GET, POST, PUT). The request goes to the controller, and the controller maps the request and pass that request to service layer. I added the service layer(HouseResourceService) which is used to decouple business logical from controllers. In the service layer, it performs all the business logic on the data that is mapped to JPA with HouseResource model class.  

### H2 database
In its default configuration, House API uses an embedded, in-memory database H2 which gets populated at startup with data extracted from CSV file. 

## Notes on the security implications of the implementation

### Close CSV file
Since we need to read data from CSV file when the application starts, we need to close all IO-based streams after reading the file. If we forget to close the stream, the underlying channel will remain open and then we might have resource leak. 

### Authentication
There is no authentication implemented in the APP, so anyone with the URL can get, post and update the data stored in the database. We could secure the API using Spring Security and JWTs so that only authorized users could access the database. 

## Test Cases

* Implemented Unit test with JUNIT, file path: <code>src/test/java/</code>

* Implemented Integration test with Postman, file path: <code>HouseAPI/TestCases.json</code>

* Test Results:
<img src="https://github.com/Jasper-Joe/HouseAPI/blob/master/Demo/Testing.png?raw=true" width="300" height="600" /> 

