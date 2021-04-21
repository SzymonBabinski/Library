# Library Project
# 1. Description
This application provides several endpoints explained below. It serves the relevant information based on data read from a JSON file and runs on localhost:8080. Port can be specified in application.properties file.
# 2. Building
To build this application use following command:

mvn clean package 

# 3. Running
After building the application run following command to start it:

java -Ddatasource=<JsonFileName> -jar <JarName.jar>
  
e.g. java -Ddatasource=books.json -jar library-0.0.1-SNAPSHOT.jar
 
 If no datasource is specified application takes default file books.json from resources directory.
 
  # 4. Testing
  To run test use command below:
  
  mvn test
  
  # 5. Endpoints
| Endpoint | Description  |
| ------------- |:-------------:|
| /books/{isbn}      | allows reading details about book |
| /books/categories/{category}      |   Lists all books that are assigned to the requested category    |
| /authors/ratings |  Lists all authors and their rating in descending order of the average rating of their books     |
| /books/volumes/{pageCount} |  Lists first book which number of pages is greater than specified value     |
| /books/pages/{pagesPerHour}/hours/{hoursDaily} | Lists the best rated books which can be read in a month. |
  
  # 6. Technologies
  - Spring
  - JUnit4, Mockito, RestAssured
  - h2 Database
  - Jackson
  
  