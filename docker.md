Скачивание образа:

docker pull postgres:13.10-alpine

Запуск контейнера из образа:

docker run -e POSTGRES_DB=schedule_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -p 5432:5432 postgres:13.10-alpine

Список образов:

docker image ls