TPE PAW (Sprint 2)
==================

Restaurant guide web application.

By:

```
Dario Sneidermanis
Lucas Moscovicz
Alan Pomerantz
```

#To run on Apache Tomcat

1. Create database with name 'paw2', username 'paw' and password 'paw'
2. Run SQL files, `create.sql`, and then `insert.sql`
3. `cd TPE_2013 && mvn clean package`
3. Copy `target/TPE_2013.war` into tomcat webapps folder
4. Open a browser and go to `localhost:8080/TPE_2013`
