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
