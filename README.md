jersey2-spring4-grizzly2
========================

This repository shows an example of how to integrate Jersey 2.6, Spring 4.0.2, Spring transactions, and Grizzly 2.3.11.

Motivation
----------
I have been working on a project developing a REST appliction using Jersey 2.6. We use an embedded Grizzly 
as the "container" because it is simple and seems reasonably fast.

However, we wanted to use Spring transactions to implement transactions, and that turned out to be less than 
obvious (in the sense that somebody else had prepared a nice example to use.). So I started reading various 
blog posts, mailinglists, stackoverflow etc.

I soon found [1,2,3,4] which did basically what I wanted, but with Jersey 1.17 or with Tomcat instead of Grizzly.

By doing some more digging I discovered [6,7,8] which gave me the inspiration I needed.

The example
-----------
The example requires a Postgres SQL 9.1 database called `test` with a user called `testuser` and password `1234`.

```CREATE USER testuser WITH PASSWORD '1234';```

The database should contain a table called "users" with the following schema:

```
CREATE TABLE users
(
  id integer NOT NULL,
  username character varying(64) NOT NULL,
  name character varying(64) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO testuser;
```

Compile the code:

  ```mvn clean compile```

Create an Eclipse project:

  `mvn eclipse:eclipse`

The example can be executed by running the com.test.Start class.

A server should be now be running on localhost:3388.


The server can be exercised using the following curl command:

  `curl -v =X GET http://localhost:3388/test`

which should return a 200 and the string "test". It should also add an entry to the users table.


The transactions can be tested using this command:

  `curl -v =X GET http://localhost:3388/`

Which should return a 500 internal error. It would try to create an entry in the users table and then throw an
UnsupportedOperationException. Which will make the transaction roll back. There should be no effect on the database.


About the project.
------------------
The code I present here is heavily inspired and founded on the great work done by others primarily [1,2,4]. 
The code take outset in [4] and incoorporates [2] with additions of knowledge from [6]. The setup of Grizzly is 
basically a merge of [2,4,7], where [7] provided the final clue.

I also integrated Tomcat connection pool and made a small adjustment to show that the transactions work. 

Other Resources
---------------
- [1] https://github.com/amacoder/demo-restWS-spring-jersey-tomcat-mybatis/blob/master/pom.xml
- [2] http://www.codingpedia.org/ama/restful-web-services-example-in-java-with-jersey-spring-and-mybatis/
- [3] http://grizzly-nio.net/2013/08/grizzly-httpserver-spring-jersey-serve-static-content-from-a-folder-and-from-a-jar/
- [4] https://github.com/oleksiys/samples/tree/master/jersey1-grizzly2-spring
- [5] https://java.net/projects/grizzly/lists/users/archive/2013-01/message/0
- [6] http://stackoverflow.com/questions/15769415/grizzly-and-servletcontainercontext
- [7] http://alkalinezoo.blogspot.dk/2013/05/rest-testing-with-embedded-grizzly.html
- [8] http://www.byteslounge.com/tutorials/spring-jdbc-transactions-example
