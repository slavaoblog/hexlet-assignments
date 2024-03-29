# OpenAPI

OpenAPI – это спецификация для описания REST API. Эта спецификация не зависит от языка программирования. Каждая часть API описывается в специальном файле в формате JSON или YAML. На основании полученной документации можно генерировать исходный код для библиотек клиентских приложений или текстовую документацию для пользователей.Также различные инструменты публикации могут программно анализировать информацию об API и отображать каждый компонент в удобном интерактивном виде. В этом упражнении мы будем работать со Swagger - инструментом, который позволяет интерактивно просматривать спецификацию и отправлять запросы.

Файл с документацией API можно создать вручную (в любом текстовом редакторе или в специальном [редакторе swagger](https://editor.swagger.io)) или сгенерировать автоматически на основании аннотаций в коде приложения. Мы будем генерировать документ автоматически при помощи специального плагина gradle, используя аннотации.

## Ссылки

* [Интеграция Spring boot 3 и Swagger. Кастомизация пути к документации](https://springdoc.org)
* [Аннотации для документирования API](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#quick-annotation-overview)
* [Плагин springdoc-openapi-gradle-plugin – позволяет получить OpenAPI документацию в формате JSON](https://github.com/springdoc/springdoc-openapi-gradle-plugin)

## build.gradle

## Задачи

* Добавьте в проект необходимую зависимость для интеорации Spring Boot и Swagger-ui. Это позволит сгенерировать и интерактивно просматривать OpenAPI документацию для нашего проекта

* Добавьте gradle плагин *openapi-gradle-plugin*. Этот плагин при помощи springdoc-openapi позволяет получить документацию в виде отдельного JSON файла. Обратите внимание на комментарии в файле, там, где происходит настойка плагина

## src/main/resources/application.yml

## Задачи

* Кастомизируйте путь к документации. Настройте swagger таким образом, чтобы интерактивная документация по API в HTML формате была доступна в приложении по пути */swagger.html*

## src/main/java/exercise/controller/UserController.java

В контроллере вам предстоит самостоятельно написать и задокументировать метод, который обновляет данные пользователя.

## Задачи

* Изучите методы, которые уже написаны в контроллере. Обратите внимание на аннотации, при помощи которых происходит описание API. Обратите внимание на комментарии в коде

* Создайте метод, который обрабатывает PATCH запросы по пути *users/{id}* и обновляет данные пользователя с указанным id. В случае успешного обновления пользователя метод должен вернуть ответ с кодом 200. Если пользователь с указанным id не существует в базе, должен вернуться ответ с кодом 404. Добавьте необходимые аннотации для метода и его параметров, чтобы задокументировать свой API

* Запустите приложение и перейдите по адресу *localhost:5001/swagger.html*. Убедитесь, что страница с документаций открывается. На этой странице сгенерированная документация отображается в интерактивном виде. Изучите эту страницу, раскройте каждый маршрут. Посмотрите, как информация, которую мы добавляли в аннотациях, отражается в документации

При помощи swagger можно не только просматривать документацию, но и выполнять запросы.

* Выполните PATCH запрос по адресу *users/{id}* и попробуйте обновить данные пользователя. Убедитесь, что существующий пользователь успешно обновляется, а при попытке обновить данные пользователя с несуществующим id возвращается код ответа 404.

Установленный gradle плагин позволяет записать сгенерированную документацию в спецификации OpenAPI 3 в виде отдельного файла в формате JSON

* Выполните команду `make api-doc`, которая запустит gradle таску `generateOpenApiDocs` и запишет документацию в спецификации OpenAPI 3 в файл *openapi.json* в директорию *build*

## build/openapi.json

## Задачи

* Изучите получившийся файл. Он представляет собой документацию в спецификации OpenAPI 3 к нашему API

## Подсказки

* Изучите файлы с примерами в директории Examples

* Посмотреть, какие записи будут добавлены в базу при старте приложения, можно в файле *src/main/resources/import.sql*
