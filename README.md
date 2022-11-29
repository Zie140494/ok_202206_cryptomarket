# 202206-otuskotlin-cryptomarket
---
Учебный проект курса Kotlin Backend Developer. Криптобиржа -- это площадка, на которой пользователи могут устанавливать ордера для совершения сделок с криптовалютой. Задача площадки -- предоставить предоставить интерфейс для купли\продажи в паре BTC/USD.

# Маркетинг приложения
---
Целевая аудитория - это люди, которые разбираются в криптовалюте, имеющие свой криптокошелек. Хотят быстро обменять криптовалюту на фиатную или наоборот, а также для других операций с криптовалютами.

# Гипотетический портрет пользователя
---
Мужчины или женщины
от 25 до 50 лет
Со среднем, высшим или неоконченным высшим образованием
С уровнем достатка выше среднего и ниже
Не разбирается в торговле на крипторынке

# Описание MVP
---
![plot](src/main/resources/img/phone1.jpg)
![plot](src/main/resources/img/phone2.jpg)
![plot](src/main/resources/img/phone3.jpg)
![plot](src/main/resources/img/phone4.jpg)

# Функции (эндпониты)
    Orders/GetAll (Read)
    Orders/Send (Create)
    Courses/GetCurrent (Read)
# Описание сущности order
    1.WalletNumber
    2.FiatCurrency (USD)
    3.CryptoCurrency (BTC)
    4.Action (BUY/SELL)

# Описание сущности course
    1.Price
    2.Fiatcurrency (USD)
    3.CryptoCurrency (BTC)

# Структура проекта
---
# Транспортные модели, API
-----
# Фреймворки и транспорты
---
# Модули бизнес-логики
---
Хранение, репозитории, базы данных