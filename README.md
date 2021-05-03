# SpringAppDemo
Необходимо реализовать REST API, которое взаимодействует с файловым хранилищем AWS S3 и предоставляет возможность получать доступ к файлам и истории загрузок.
Сущности:
User
Account
AccountStatus
Event
File FileStatus (enum ACTIVE, BANNED, DELETED) User -> List<File> files + List<Events> + Account account
 
Взаимодействие с S3 должно быть реализовано с помощью AWS SDK.
Уровни доступа:
ADMIN - полный доступ к приложению
MODERATOR - добавление и удаление файлов
USER - только чтение всех данных кроме User + Account
 
Технологии: Java, MySQL, Spring (IoC, Data, Sercurity), AWS SDK, MySQL, Travis, Docker, JUnit, Mockito, Gradle.
