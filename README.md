# tradingAccount

Trading Account project created as a Spring boot Rest project

- Endpoints
  - /api/createEvent  - POST call
    
    Request Body 
    {
        "id": 2,
        "account": "ACC",
        "security": "SECXYZ",
        "quantity": 100,
        "operation" : "BUY"
    }
    
  - /api/getAccount  - POST call
    
    Request Body 
    {
        "account": "ACC",
        "security": "SECXYZ"
    }
