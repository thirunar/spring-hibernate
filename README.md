### Integration Spring with JPA
The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE platform. 

The Java Persistence API (JPA) is a Java application programming interface specification that describes the management of relational data in applications using Java Platform, Standard Edition and Java Platform, Enterprise Edition.

This project will explain you about integrating the Spring framework with JPA. As a part of this, it will also explain the use of Liquibase libraries for creating the tables in the database. Liquibase is an open source database-independent library for tracking, managing and applying database schema changes.

### How to run the project

` mvn spring-boot:run`

### Intializing the Spring project
Use the [link](https://start.spring.io/) to bootstrap your spring application. 

### Adding the dependencies
Following are the dependencies which are needed for the project. Please add the dependencies in the pom.xml

`

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>${postgresql.version}</version>
		</dependency>

		<dependency>
			<groupId>org.liquibase</groupId>
			<artifactId>liquibase-core</artifactId>
			<version>${liquibase.version}</version>
		</dependency>

		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.4</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
`
This project uses postgresql as the database so adding the dependency for it. 

### Setup Liquibase
I going consider the College and department relationship for the demonstration. I will be having two tables named `college, department`. The college table will have the following fields
* id
* name

The department table will have the following fields:
* id
* name
* college_id
`college_id` is the foreign key which references the `id` column in the college table.

With this assumption, the first step in setting up liquibase is creating a database called `college` in postgres DB. This can be done using the command: `createdb college`

The following properties has to be added in application.properties

`

    spring.datasource.url=jdbc:postgresql://localhost:5432/college
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    liquibase.change-log=classpath:liquibase/changesets.xml

`

Create a file called `changesets.xml` under `resources/liquibase` and add the following lines:

`

    <?xml version="1.0" encoding="UTF-8"?>
    <databaseChangeLog
            xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
             http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.3.xsd">

        <changeSet id="201603050433" author="Thiru">
            <createTable tableName="COLLEGE">
                <column name="ID" type="BIGINT" autoIncrement="true">
                    <constraints primaryKey="true" nullable="false"/>
                </column>
                <column name="NAME" type="varchar(255)"/>
            </createTable>

            <createTable tableName="DEPARTMENT">
                <column name="ID" type="BIGINT" autoIncrement="true">
                    <constraints primaryKey="true" nullable="false"/>
                </column>
                <column name="NAME" type="varchar(255)"/>
                <column name="COLLEGE_ID" type="BIGINT"/>
            </createTable>
            <addForeignKeyConstraint baseTableName="DEPARTMENT" baseColumnNames="COLLEGE_ID"
                                     constraintName="DEPARTMENT_COLLEGE_ID_FK"
                                     referencedTableName="COLLEGE"
                                     referencedColumnNames="ID"/>

        </changeSet>

    </databaseChangeLog>

`

Run `mvn clean install` to add these tables to the database.

### Creating the domain models
Create two class called College and Department and add the corresponding field variable. 

College Class:

`

    @Entity
    @Table(name = "COLLEGE")
    public class College {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "name")
        private String name;

        @OneToMany(mappedBy = "college", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
        private List<Department> departments;

        public College() {
        }

        public College(int id) {
            this.id = id;
        }

        public College(String name, List<Department> departments) {
            this.name = name;
            this.departments = departments;
        }


        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            return new EqualsBuilder().reflectionEquals(this, o);
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).reflectionHashCode(this);
        }

        public List<Department> getDepartments() {
            return departments;
        }
    }

`

Note that, this class is annotated with `Entity and Table` and maps to the `college` table. College will have many departments in it. So, there is a one to many mapping for the list of departments fields. `@GeneratedValue` will help you in auto increment the `id` field.

Department Class:

`

    @Entity
    @Table(name = "DEPARTMENT")
    public class Department {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "NAME")
        private String name;

        @ManyToOne
        @JoinColumn(name = "COLLEGE_ID", referencedColumnName = "ID")
        private College college;

        public Department() {
        }

        public Department(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            return new EqualsBuilder().reflectionEquals(this, o);
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).reflectionHashCode(this);
        }

    }

`

Since, a `department` will belong to a college, there is many to one mapping for the field `college`. The field is also annotated with `JoinColumn` for specifying based on which join has to done.

### Adding and retrieving data to the database

A controller called `CollegeController` has been creating for serving the purpose. This basically calls the service and the service in turn calls the `CollegeRepository` where the logic of adding and retrieving the data resides. The controller basically exposes `GET` and `POST` APIs.

CollegeController.java:
`

    @Controller
    @RequestMapping("/college")
    public class CollegeController {

        @Autowired
        private CollegeService collegeService;

        @ResponseBody
        @RequestMapping(method = RequestMethod.GET, produces = "application/json")
        public College getCollege(@RequestParam long id) {
            return collegeService.getCollege(id);
        }

        @ResponseBody
        @RequestMapping(method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
        public String save(@RequestBody College college) {
            collegeService.save(college);
            return "Successfully saved";
        }
    }

`

The `getCollege` method used to get the college and its department based on the College id. The `save` posts the data to the database. 

### Repository Layer
This layer interacts with the database and helps in adding and retrieving the data from the database. The class called `CollegeRespository` solves this purpose. This uses `EntityManager` for saving and fetcing the data.

`

    @Repository
    public class CollegeRepository {

        @PersistenceContext
        private EntityManager entityManager;

        @Autowired
        private EntityManagerFactory entityManagerFactory;

        @Transactional
        public void save(College college) {
            entityManager.persist(college);
        }

        public College getCollege(long id) {
            return entityManager.find(College.class, id);
        }

        public List<Department> getDepartmentsForTheCollege(long id) {
            College college = getCollege(id);
            return college.getDepartments();
        }
    }
`

The class is annotated with `Repository`. The `save` method is made `Transactional`. The `getCollege` method fetches the college with its departments and returns the data.

### References:
* http://www.baeldung.com/liquibase-refactor-schema-of-java-app
* https://en.wikipedia.org/wiki/Java_Persistence_API
