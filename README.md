# Тестовое задание Back-End

Реализовать веб-приложение на языке Java / Kotlin.
Приложение должно уметь:
1. Получать по REST API массив с интервалами цифр или букв в формате JSON, объединять все
пересекающиеся интервалы и сохранять массив из непересекающихся интервалов в in-memory SQL базу
данных (например, H2).
2. Выдавать по REST API минимальный интервал в формате JSON.


# Сборка

Приложение написано на Java 17

Все зависимости лежат в файле pom.xml, используется сборщик Maven

Фреймворки: 

Lombok

Spring Web

Spring Data JPA

H2 Database


# Конфигурирование

Всё конфигурирование лежит в файле application.properties, ниже приведено его содержимое.


spring.jpa.database=h2

spring.datasource.username=sa

spring.datasource.password=

spring.datasource.driver-class-name=org.h2.Driver

spring.datasource.url=jdbc:h2:tcp://localhost/~/test

Эта строчка будет другой, если приложение развертывается не на локальной машине

spring.jooq.sql-dialect=H2Dialect

spring.jpa.open-in-view=false

spring.jpa.show-sql=true




# Endpoints

> POST /api/v1/intervals/merge?kind=digits
> 
> POST /api/v1/intervals/merge?kind=letters
> 
> GET /api/v1/intervals/min?kind=digits
> 
> GET /api/v1/intervals/min/kind=letters

# Database

Структура БД приведена ниже

![2023-09-24_16-25-00](https://github.com/whatanameitis/cft_task/assets/96914240/1594f565-f11d-4f78-9364-192f6341f19f)


В БД создадим две таблицы


 CREATE TABLE digit_intervals (
 
id IDENTITY PRIMARY KEY,

istart int NOT NULL,

iend int NOT NULL

 );

CREATE TABLE letter_intervals (

id IDENTITY PRIMARY KEY,

lstart char NOT NULL,

iend char NOT NULL

);

**Приложение разворачивалось локально** 

