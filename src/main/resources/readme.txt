шифрование, на момент добавление в базу
дешифровывать на момент копирования в буффер


строку в буффер
*очистка базы при сборке
*отправка в телеграм


questions:
    ViewServicesManager->TableService->TableView. TableService -> (DataManager/ViewServicesManager)
 *инициализация БД, как происходит.
    -из файла запрос создания или константой в коде (отдельный класс?) static final String
    -какой файл бд должен быть под гитом? чистый файл БД, или "тестовый", проблема в том, что добавив файл под версионный контроль он будет в списке изменнных
    -таблица appdata вид - строка...
 *генерация пароля.
    -добавять ли возможность создавать шаблон пароля
    -как адекватно организовать хранение разрешённых символов для шаблонов база\файл properties
 *таблица с языками - нет
    -внешний вид таблицы
    -доступ к таблице
    -заполнение строк данными таблицы
 *логин или зарос парольной фразы
 *как настраивается сборка
    -сборщик
    -как управлять действиями сборщика в момент сборки (очистка базы, default values)

    springValidation...
    миграция баз flyway (liquiBase)



---------------------------------------
Таблица AppData :
    id?
    cipherType
    cipherWord
    isDarkTheme
    lastUsedOwner
    lastSavePath

---------------------------------------

create table accounts
(
	id INTEGER not null
		constraint accounts_pk
			primary key autoincrement,
	name varchar(90) not null,
	owner INTEGER not null
		references owners
			on update cascade on delete cascade,
	link varchar(500),
	mail varchar(100),
	account varchar(100),
	password varchar(50),
	info varchar(500),
	update_date TIMESTAMP default CURRENT_TIMESTAMP
);

create unique index accounts_id_uindex
	on accounts (id);

---------------------------------------

create table owners
(
	id INTEGER not null
		constraint owners_pk
			primary key autoincrement,
	name varchar(50) not null
);

create unique index owners_id_uindex
	on owners (id);

---------------------------------------



