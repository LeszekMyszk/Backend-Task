# Backend oriented task (Java)
This is a runnable local server, that exposes some endpoints which take query data from the Narodowy Bank Polski's public APIs and return relevant information from them. User manual: http://api.nbp.pl/

## How to start


- To start the server, run this command:
```
docker-compose up -d
```

## How to use
Consists of three separate endpoints for each operation:
1. Given a date (formatted YYYY-MM-DD) and a currency code (list: https://nbp.pl/en/statistic-and-financial-reporting/rates/table-a/), provide its average exchange rate.
- To query operation, run this command (which should have the value 5.2768 as the returning information):
```
curl http://localhost:8080/exchanges/GBP/2023-01-02
```
2. Given a currency code and the number of last quotations N (N <= 255), provide the max and min average value (every day has a different average).
- To query operation, run this command (which should have the value 5.2768 as the returning information):
```
curl http://localhost:8888/exchanges/GBP/2023-01-02
```
3. Given a currency code and the number of last quotations N (N <= 255), provide the major difference between the buy and ask rate (every day has different rates).
- To query operation, run this command (which should have the value 5.2768 as the returning information):
```
curl http://localhost:8888/exchanges/GBP/2023-01-02
```

## Additional

- Unit/integration tests.
- Docker image of the whole application.
- Swagger UI.
