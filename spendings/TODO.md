**Регистрируем траты**

POST /v1/putspending
X-USER-ID: userID

{
"category": "entertaiment",
"amount": 200
}

--------

**Запрос существующих категорий для пользователя**

GET /v1/categories
X-USER-ID: userID

["entertaiment", "sport", "food"]

--------

**Запрос статистики трат за месяц по конкретному пользователю**

GET /v1/statistic?year=2023&month=JAN
X-USER-ID: userID

{
"entertaiment": 5000,
"sport": 10,
"food": 60000
}