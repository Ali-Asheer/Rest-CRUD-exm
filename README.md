## Testning i Spring Boot

This project includes examples of three types of testing in a Spring Boot application:  
**1. Unit Test**  
**2. Component/Service Test**  
**3. Integration Test**
 
---

## 1. Unit Test

### Purpose:
To test a ChannelServiceTest class **in isolation**, without loading Spring context or using a real database.

### Example:
1. Should save a new channel successfully.(To test if creates channel and save it )
2. Should return channel by ID when it exists.(To test if a channel should return if its id exists)


## 2. Component/Service Test
### Purpose:
To test a ChannelControllerComponentTest together with the real service layer (@Service), while mocking the repository.

### Example:
1. Should return 201 CREATED when creating a new channel.(To test if creates channel )
2. Should return 200 OK with channel details when channel exists.(To test if a channel should return by id )
3. Should return 404 NOT FOUND when channel does not exist.(To test if a channel not found )


## 3. Integration Test
### Purpose:
To test the full flow from HTTP → Controller → Service → Repository → Database. Test class is (ChannelIntegrationTest)

### Example:
1. Should create a channel and retrieve it successfully via HTTP.(To test if creates channel and save it )


## How is this test isolated from production data?
The annotation @ActiveProfiles("test") tells Spring Boot to use a separate config file.
which was (src/main/resources/application-test.properties). In this configuration we create a new database (my_integration_test_db). And this was a temporary, and it is cleared after the test finishes. And this ensures that no real data is touched.





