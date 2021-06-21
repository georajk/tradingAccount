# Trading Account API

Created a Trade account API which stores the data in memory and handles Buy/Sell/Cancel Securities.

## Installation
Pull the git project from this git repo and run maven command to buid the jar

```bash
mvn clean install
```

## Usage

1. Run the Spring Boot Application

``` bash
java -jar tradingAccount-0.0.1-SNAPSHOT.jar
```

2. Send a POST request 

    a. to create an Event `   "http://localhost:9080/api/createEvent" `
    
     Request Body 
      ```json
        {
            "id": 2,
            "account": "ACC",
            "security": "SECXYZ",
            "quantity": 100,
            "operation" : "BUY"
        }
    ```
  
    b. to get an Account events fire post call with the below request body ` http://localhost:9080/api/getAccount`
    
        Request Body
      
          {
            "account": "ACC",
            "security": "SECXYZ"
          }

   
   
