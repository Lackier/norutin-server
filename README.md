Веб-приложение для планирования задач 


user-cases:
- авторизация 
	- логин
	- регистрация
- список задач
	- фильтрация задач
	- управление задачами (добавить\изменить\удалить)
	- управление типами задач (добавить\изменить\удалить)
	- управление статусом задачи
	- выставить приоритет задачи
	- выставить сроки задачи
- календарь для событий и задач
	- фильтрация событий и задач
	- добавить событие по датам (например отпуск, выходные, день рождения)
	- управление типами событий (добавить\изменить\удалить)
	- управление событиями и задачами на календаре (добавить\изменить\удалить)
- команда (in future)
	- создать\удалить команду
	- управление командой (добавить, поменять роль, удалить)
	- доски и календари команды и управление ими

entities:
- список задач (доска)
- задача
- календарь событий
- событие
- приоритетность
- типы задач
- статусы задач (запланирована, в аналитике, в процессе, выполнена)
- типы событий
- периодичность событий (каждый год, месяц, квартал и тд, разовое)
- пользователь
- команда (in future)
- тимэйт (in future)
- роль тимэйта (админ, модератор, обычный, гость) (in future)

Controllers:

- userController
	- signup
	- update
	- delete
- deskController
	- getDesksByUser
	- create
	- update
	- delete
	- getTasksByDesk
- calendarController
	- getCalendarsByUser
	- create
	- update
	- delete
	- getEventsByCalendar
	- getTasksByCalendar
- settingsControllers for desks and calendars
	- taskType CRUD
	- priorityType CRUD
	- taskStatus CRUD
	- periodicyType CRUD
	- eventType CRUD

Used technologies:
- Kotlin
- Spring Framework
- PostgreSQL
- Gradle for build
- Tomcat for server
- Mapstruct to generate mappers

How to run using docker
- change database settings in 'build.gradle.kts' and in 'application.properties'
- run docker-compose.yml or 'docker compose up'

How to deploy to the server
- run docker-compose.yml
- execute following commands locally:
	- docker tag server-app:latest lackier/norutin:server-app
    - docker push lackier/norutin:server-app
- execute following commands remotely on the server:
	- docker pull postgres
	- docker pull lackier/norutin:server-app
	- docker run lackier/norutin:postgres -e POSTGRES_DB=norutin-db POSTGRES_USER=postgres POSTGRES_PASSWORD=postgres
    - docker run -d --name norutin-postgres --network norutin-net -e POSTGRES_DB=norutin-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 lackier/norutin:postgres
    - create database as in instruction below
    - docker run -d --name server-app -p 8080:8080 --network norutin-net lackier/norutin:server-app ./gradlew flywayMigrate bootRun
    - docker run -it --name server-app -p 8080:8080 --network norutin-net lackier/norutin:server-app ./gradlew flywayMigrate bootRun

to create database in running container, do the following:
- docker exec -it norutin-db bash		connect to console of container
- psql -h localhost -U postgres
- CREATE DATABASE "norutin-db";
- \l
- /q
- exit

docker rm -f $(docker ps -aq) 		kill all containers
docker rm <container-id> 			kill one container


scp -r D:\поликек\6\проектирование\norutin-server root@194.58.121.40:/var/norutin-server

cd /var/norutin-server

wget https://download.java.net/openjdk/jdk15/ri/openjdk-15+36_linux-x64_bin.tar.gz
sudo tar -xzf openjdk-15+36_linux-x64_bin.tar.gz -C /opt

chmod +x gradlew
export JAVA_HOME=/opt/jdk-15
export PATH=$JAVA_HOME/bin:$PATH

docker compose build app
docker compose up