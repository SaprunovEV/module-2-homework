# Приложение для учёта студентов

## Как начать работать с приложением

* Для начало работы вам необходима JDK 17 или выше и Docker установленные на вашем компьютере;
* Клонировать репозиторий с gitHub.
```shell
git clone https://github.com/SaprunovEV/module-2-homework.git
```
* перейти в папку проекта
```shell
cd module-2-homework
```

## Как запустить тесты
```shell
./gradlew test
```

## Как установить приложение
### Docker
```shell
./gradlew build
 docker build -t students/list -f docker/Dockerfile .
```
### Java
```shell
./gradlew build
```

## Как запустить приложение
### Docker
#### Для запуска с настройками по умолчанию (С предварительным заполнение базы данных)
```shell
docker run -it --rm students/list
```
#### Для запуска без предварительного заполнения базы данных 
```shell
docker run -it --rm -e APP_INITMODE_ENABLE=false students/list
```
### Java
#### Для запуска с настройками по умолчанию (без предварительного заполнения базы данных)
```shell
java -jar build/libs/module-2-homework-0.0.1-SNAPSHOT.jar
```
#### Для запуска с предварительного заполнения базы данных
```shell
java -jar -DAPP_INITMODE_ENABLED="true" build/libs/module-2-homework-0.0.1-SNAPSHOT.jar
```
