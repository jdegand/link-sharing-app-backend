# Link Sharing App Backend

This is a Spring Boot backend designed to work with this [frontend](https://github.com/jdegand/link-sharing-app-frontend).

## Built With

- Spring Boot 3
- Java 17
- Maven
- H2
- JWT

## Thoughts

- The link sharing app frontend doesn't need user roles for route access.  
- User roles could be useful to add extra functionality.  Maybe, a user could click a button and be able to add more links than a `free` or default user.  
- I don't want to add too many properties to `UserInfo`.  It could be better to create an intermediate entity called `Profile` and save the `Link` and `UserInfo` IDs and extra properties inside it.  But it doesn't really matter since I am going to send only DTOs back to the frontend.
- Authentication takes a lot of work.  You wouldn't save passwords in your entity, and you would probably need a separate spring boot server that just handles login.  Then you have to handle cross origin issues.  
- I added basic image upload functionality.  At first, I saved the photos in the file system rather than storing them in the database. 
- In the frontend, I used `PrimeNg` and its file input component.  The file input component is shown in the documentation as standalone and not as part of a form group.  You could use `ControlValueAccessor` to get the file input inside a form group.  I refactored the frontend to send `formData` so I didn't have the issue of including the file input inside the reactive form group.   
- I was able to make the file input component part of the profile form by storing the image data as a `lob` in the database.  You have to convert the base64 string in the frontend to display the image. 
- The `JJWT` library has deprecated a few methods frequently used in `11.5` implementations.  So I looked into using OAuth 2 Resource server and its included JWT nimbus package.  There are some useful resources included from that research.

## Continued Development

- application-local.properties file with JWT secret
- Logging / Actuator
- Exception Handling
- UserPrincipalServiceImpl -> necessary?
- javadoc
- `SecurityConfig` route permissions
- JJWT implementation is changing in the near future (recommendation is to wait until `1.0` release).
- Extend CRUD functionality
- Problem when user logs in for the second time -> the refresh token still exists and this causes a conflict issue -> need to reset refresh token to null beforehand

## Useful Resources

- [Vecteezy](https://www.vecteezy.com/free-vector/default-profile-picture) - Default Profile Picture Vectors by Vecteezy
- [YouTube](https://www.youtube.com/watch?v=jQrExUrNbQE) - Spring Security Crash Course | JWT Authentication and Authorization in Spring Boot 3.1 
- [Baeldung](https://www.baeldung.com/spring-boot-h2-database) - h2 database
- [Blog](https://www.danvega.dev/blog/spring-security-jwt) - spring security jwt
- [Github](https://github.com/spring-projects/spring-security/issues/13446) - spring security oauth2 impl
- [Reddit](https://www.reddit.com/r/SpringBoot/comments/18kkyqo/jwt_with_spring_security_resource_server_or_with/) - jwt spring security resource server or jjwt?
- [Spring Docs](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html) - oauth2 resource server
- [Stack Overflow](https://stackoverflow.com/questions/60265755/spring-security-for-web-service-without-roles-and-authorities) - spring security for web service without roles and authorities
- [Dev.to](https://dev.to/mittalyashu/best-way-to-store-array-type-data-inside-database-3m09) - best way to store array type data inside database
- [Stack Overflow](https://stackoverflow.com/questions/72040490/how-to-save-array-of-object-in-sprig-boot-with-crudrepository) - how to save array of object in sprig boot with crudrepository
- [Baeldung](https://www.baeldung.com/spring-boot-properties-env-variables) - spring boot properties env variables
- [Stack Overflow](https://stackoverflow.com/questions/59089072/spring-boot-rest-to-read-json-array-payload) - spring boot rest to read json array payload
- [Stack Overflow](https://stackoverflow.com/questions/61895276/post-array-in-requestbody-to-spring-controller-as-list-of-objects) - post array in request body to spring controller as list of objects
- [YouTube](https://www.youtube.com/watch?v=4N2WghOYihs) - 36. Create Simple JSON Arrays Payload Using Java List
- [Rest API Project](https://restapiproject.com/create-restapi-to-save-json-array-list-in-database-using-jpa-and-spring-boot/) - create rest api to save json array list in database using jpa and spring boot
- [Medium](https://medium.com/shoutloudz/spring-boot-upload-and-download-images-using-jpa-b1c9ef174dc0) - spring boot upload and download images using jpa
- [JPA Buddy](https://jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/) - lombok and jpa what may go wrong
- [Stack Overflow](https://stackoverflow.com/questions/57557763/how-to-send-array-of-objects-in-spring-boot-post-request) - how to send array of objects in spring boot post request
- [Hello Koding](https://hellokoding.com/jpa-many-to-many-extra-columns-relationship-mapping-example-with-spring-boot-hsql/) - jpa many to many extra columns relationship mapping example with spring boot hsql
- [Medium](https://medium.com/@kkarththi15/saving-images-locally-in-a-spring-boot-web-application-01405a988bc7) - saving images locally in a spring boot web application
- [Medium](https://medium.com/@miguelangelperezdiaz444/the-hidden-dangers-of-lombok-annotations-in-your-java-code-what-you-need-to-know-8acdce2d6b89) - the hidden dangers of lombok annotations in your java code what you need to know
- [Stack Overflow](https://stackoverflow.com/questions/31159075/how-to-find-out-the-currently-logged-in-user-in-spring-boot) - how to find out the currently logged in user in spring boot
- [Stack Overflow](https://stackoverflow.com/questions/67951256/spring-boot-make-sure-data-belongs-to-current-logged-in-user) - spring boot make sure data belongs to current logged in user
- [Stack Overflow](https://stackoverflow.com/questions/72230836/how-to-get-an-object-of-current-user-in-spring-bootjpa) - how to get an object of current user in springboot jpa
- [Stack Overflow](https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java) - how to get the current working directory in java
- [Stack Overflow](https://stackoverflow.com/questions/48303350/persisting-an-entity-via-a-logged-in-user-in-spring) - persisting an entity via a logged in user in spring
- [YouTube](https://www.youtube.com/watch?v=MlKT8IOTfcw&list=PLGXpHMFOMTTbCC4t6WSoKfVnUxHmyGXKJ&index=17) - Spring Security 17 Security Context Holder
- [Baeldung](https://www.baeldung.com/get-user-in-spring-security) - get user in spring security
- [Stack Overflow](https://stackoverflow.com/questions/32052076/how-to-get-the-current-logged-in-user-object-from-spring-security) - how to get the current logged in user object from spring security
- [Stack Overflow](https://stackoverflow.com/questions/51456096/spring-boot-rest-crud-how-to-post-an-entitiy-with-a-one-to-one-relationship) - spring boot rest crud how to post an entity with a one to one relationship
- [Stack Exchange](https://softwareengineering.stackexchange.com/questions/423121/best-practices-for-retrieving-data-scattered-over-multiple-tables) - best practices for retrieving data scattered over multiple tables
- [Stack Overflow](https://stackoverflow.com/questions/38168985/jpa-onetoone-relation-automatic-creation) - jpa one to one relation automatic creation
- [Stack Overflow](https://stackoverflow.com/questions/10687529/onetoone-bidirectional-mapping-with-joincolumn/10687818#10687818) - OneToOne bidirectional mapping with join column
- [Spring Java](https://springjava.com/spring-data-jpa/one-to-one-unidirectional-mapping-in-spring-boot-jpa) - one to one unidirectional mapping in spring boot jpa
- [Stack Overflow](https://stackoverflow.com/questions/64543266/mocking-authenticationprincipal-for-a-unit-test) - mocking authnetication principal for a unit test
- [Stack Overflow](https://stackoverflow.com/questions/20603638/what-is-the-use-of-annotations-id-and-generatedvaluestrategy-generationtype) - generation types
- [CodeRanch](https://coderanch.com/t/695720/databases/int-Integer-JPA-entity-Id) - int vs Integer in entities
- [YouTube](https://www.youtube.com/watch?v=k29A07LCRY8) - Full Stack Mastery: Let's Build E-Commerce Project with Spring Boot, Angular & MySQL 
- [YouTube](https://www.youtube.com/watch?v=pqahN8UDQOU&list=PLgYFT7gUQL8E6DmEySCcSdNvQlKVYfEd7&index=6) - Creating Signup API, WebSecurity & Admin Account | E-Commerce Project Spring Boot + Angular | Part 6
- [Stack Overflow](https://stackoverflow.com/questions/73295132/how-do-i-bind-a-primeng-file-upload-component-to-my-angular-form-control) - how do I bind a primeng file upload component to my angular form control
- [Stack Blitz](https://stackblitz.com/edit/jhcz9a?file=src%2Fapp%2FFileUploadControlValueAccessor.directive.ts) - FileUploadControlValueAccessor
- [YouTube](https://www.youtube.com/watch?v=MlKT8IOTfcw&t=91s) - Spring Security 17 Security Context Holder
- [YouTube](https://www.youtube.com/watch?v=lIxLNx4ciEo) - Spring Boot tutorials | Spring Data JPA - One-to-One Mapping with Spring Data JPA
- [Baeldung](https://www.baeldung.com/jpa-return-multiple-entities) - JPA return multiple entities
- [Baeldung](https://www.baeldung.com/jpa-one-to-one) - JPA one to one
- [Stack Overflow](https://stackoverflow.com/questions/60115021/how-to-deal-with-one-to-one-json-loop) - how to deal with one to one json loop
- [Baeldung](https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion) - jackson bidirectional relationships and infinite recursion
- [Stack Overflow](https://stackoverflow.com/questions/49579531/java-util-nosuchelementexception-no-value-present-error-optional-get-in-jun) - java util no such element exception no value present error optional get
- [Stack Overflow](https://stackoverflow.com/questions/22191386/securitycontextholder-getcontext-getauthentication-returning-null) - security context holder returning null
- [YouTube](https://www.youtube.com/watch?v=vOWcbY7sjGM) - Image Uploading || Profile Picture uploading using Postman & MySQL in Spring Boot
- [Stack Overflow](https://stackoverflow.com/questions/29511133/what-is-the-significance-of-javax-persistence-lob-annotation-in-jpa) - @Lob
- [Stack Overflow](https://stackoverflow.com/questions/33115446/authorization-in-spring-security-based-on-path-variables) - authorization in spring security based on path variables