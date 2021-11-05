# WorkSchedulerServer
 Server app and backend for the Work Schedule

## Connecting to Your Database
You will need to add an application.properties file to src/main/resources:

```properties
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=YOUR_DATABASE_URL?currentSchema=work_scheduler
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABSE_PASSWORD
spring.jpa.hibernate.ddl-auto=validate
```
