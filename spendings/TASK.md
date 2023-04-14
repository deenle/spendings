**Регистрируем траты**

POST /v1/putspending
X-USER-ID: asdfghjkl

{
"category": "entertaiment",
"amount": 200
}

--------

**Запрос существующих категорий для пользователя**

GET /v1/categories
X-USER-ID: asdfghjk

["entertaiment", "sport", "food"]

--------

**Запрос статистики трат за месяц по конкретному пользователю**

GET /v1/statistic?year=2023&month=JAN
X-USER-ID: asdfghjk

{
"entertaiment": 5000,
"sport": 10,
"food": 60000
}