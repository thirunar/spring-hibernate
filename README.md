### Integration Spring with JPA
The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE platform. 

The Java Persistence API (JPA) is a Java application programming interface specification that describes the management of relational data in applications using Java Platform, Standard Edition and Java Platform, Enterprise Edition.

This project will explain you about integrating the Spring framework with JPA. As a part of this, it will also explain the use of Liquibase libraries for creating the tables in the database. Liquibase is an open source database-independent library for tracking, managing and applying database schema changes.

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
`spring.datasource.url=jdbc:postgresql://localhost:5432/college`
`spring.datasource.username=postgres`
`spring.datasource.password=postgres`
`liquibase.change-log=classpath:liquibase/changesets.xml`

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

</databaseChangeLog>`

Run `mvn clean install` to add these tables to the database.

### Support or Contact
Having trouble with Pages? Check out our [documentation](https://help.github.com/pages) or [contact support](https://github.com/contact) and we’ll help you sort it out.
