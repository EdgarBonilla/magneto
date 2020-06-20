# magneto
API-DNA FOR INTERVIEWING AT MERCADO LIBRE

# Installing dependencies
* [OpenJDK - Java 11.0.2](https://jdk.java.net/archive/)
* [H2 Database on memory](https://www.h2database.com/html/main.html)

# Execute

## Build & Test
With the below command you are able to build the application and run the unit tests developed

```sh
./gradlew clean build test
```
Then please search and open at whatever browser client the Jacoco code coverage report hosted in the below path within the folder project
```sh
/build/jacocoHtml/index.html
```

## Run Application
Running the application with the below command/task 
```sh
./gradlew bootRun
```
Then open your client REST predilect and hitting the next endpoints associated.

- POST http://localhost:8080/mutant/ 
  Example RequesBody
    {
    "dna": ["AAATC","AGTAC","AGAAC","TAACT","TGAAG"]
    }

- GET http://localhost:8080/stats/

At the same time, you are able to query H2 DataBase opening its web console:

http://localhost:8080/h2-console/login.jsp?jsessionid=e0664f3789201abdc248a5d7bc4aed0f

- JDBC URL: jdbc:h2:mem:test
- UserName: us

The application uses 2 tables named DNA and STAT. Both could be queried on demand for auditing.

## Contact
Edgar Orlando Bonilla Salamanca

#### Email:
edgar.bonilla.s@gmail.com