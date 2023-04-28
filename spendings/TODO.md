**DONE! Регистрируем траты**

POST /v1/putspending
USER-ID: userID

{
"category": "entertaiment",
"amount": 200
}

--------
**DONE! Запрос существующих категорий для пользователя**

GET /v1/categories
USER-ID: userID

["entertaiment", "sport", "food"]

--------

**DONE! Запрос статистики трат за месяц / год по конкретному пользователю**

@RequestParam

if null - return all
if year - return full year

GET /v1/statistic?year=2023&month=JAN
USER-ID: userID

{
"entertaiment": 5000,
"sport": 10,
"food": 60000
}

-----

**TODO: Add Slf4j**

------

**TODO: Add DB (Postgre, H2?)**

------

2 options for date:

1. if empty - add by server
2. if provided - save to DB