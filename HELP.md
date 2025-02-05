# Chinook service

Service for CRUD operations on sample chinook_db

1. Get sqlite Chinook.db (e.g. install DBeaver 24.3.x and accept sample)
2. Execute command to migrate data from sqlite file to Postgres:  
``` 
pgloader sqlite:///<your_path_to_sqlite>/Chinook.db pgsql://<username>:<password>@<pg_host>:<pg_port>/<db_name>
```
3. Start up app with env variables: SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD

