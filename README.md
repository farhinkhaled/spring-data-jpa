# Spring Data JPA

- **JDBC** stands for Java Database Connectivity
	- for connecting database to a Java application **JDBC** is required.
- Connecting to **PostgreSQL** requires postgres driver.
- Autoconfiguration - minimal config 
- **JPA** stands for Java Persistence API

<br>

## Connecting Database 

### Traditional Method
```java
@Configuration  
public class DatabaseConfig {  
    @Bean  
    public DataSource dataSource(){  
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();  
    driverManagerDataSource.setDriverClassName("org.postgresql.Driver");  
    driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/your_db_name");  
    driverManagerDataSource.setUsername("your_username");  
    driverManagerDataSource.setPassword("your_password");  
    
    return driverManagerDataSource;  
    }  
  
    @Bean // while creating this @Bean it requires a data source  
    public JdbcTemplate jdbcTemplate(DataSource dataSource){  
        return new JdbcTemplate(dataSource);  
    }  
}
```

<br><br>

**Constructor-Based Dependency Injection in Spring**
```java
@Service
public class StudentService {
    private JdbcTemplate jdbcTemplate;

    public StudentService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
```

> When the object of the **StudentService** class  is going to get created, the constructor of this class will require a **JdbcTemplate** and it will get **JdbcTemplate** from the ``@Configuration`` where the **JdbcTemplate** has been created. Here ``@Service`` will provide a singleton object which itself requires a singleton object as well.
<br>

```java
@Configuration  
public class DatabaseConfig {  
    @Bean  
    public DataSource dataSource(){  
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();  
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");  
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/learning_practice");  
        driverManagerDataSource.setUsername("postgres");  
        driverManagerDataSource.setPassword("7098");  
        
        return driverManagerDataSource;  
    }  
  
    @Bean // while creating this @Bean it requires a data source  
    public JdbcTemplate jdbcTemplate(DataSource dataSource){  
        return new JdbcTemplate(dataSource);  
    }  
}
```

<br><br>

**Query for Object**

``queryForObject`` -> when we need a specific type of data from query. 
parameters (query, typeofdata, based one what). It can only return one field not multiple.

```java
public Student getStudentById(Integer id){
    String sql = "select name from students where id = ?";
    jdbcTemplate.queryForObject(sql, String.class, id);
}
```

```java
public Student getStudentById(Integer id){
    String sql = "select * from students where id = ?";
    
    // the middle parameter is rowMapper function
    
    return jdbcTemplate.queryForObject(sql, (result, tableClms) -> {
        Student student = new Student();

        student.setId(result.getLong("id"));
        student.setName(result.getString("name"));
        student.setGender(result.getString("gender"));

        return student;
    }, id);
}
```

<br>


### How JPARepository works

``StudentRepository`` is extending ``JPARepository`` and ``JPARepository`` itself is an interface that means all methods of this class are abstract and **Hibernate** provides the implementation of these abstract methods. Since ``StudentRepository`` is extending ``JPARepository``, ``StudentRepository`` itself is a ``JPARepository`` and **Hibernate** provides implementation for all ``JPARepository``.

``JpaRepository<obj_of_class, data_type_of_primary_key>``

<br>

## Important Links
- [Derived Query Methods in Spring Data JPA Repositories](https://www.baeldung.com/spring-data-derived-queries)
- [Spring Data JPA @Query](https://www.baeldung.com/spring-data-jpa-query)
