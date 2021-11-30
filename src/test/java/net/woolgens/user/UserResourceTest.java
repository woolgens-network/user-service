package net.woolgens.user;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import net.woolgens.user.auth.AuthWrapper;
import net.woolgens.user.model.User;
import net.woolgens.user.repository.UserRepository;
import net.woolgens.user.resource.UserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
public class UserResourceTest {

    @Inject
    AuthWrapper authWrapper;

    String token;

    @Inject
    UserRepository repository;
    User user;

    @BeforeEach
    public void setup() {
        user = new User(UUID.randomUUID().toString());
        repository.persist(user);
        if(token == null) {
            token = authWrapper.getBootstrap().getProvider().generateToken("Test", "Admin", 5);
        }
    }


    @Test
    public void testGetEndpoint() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().get("/" + user.getUuid())
                .then()
                .statusCode(200)
                .body("uuid", is(user.getUuid()));
    }

    @Test
    public void testPostEndpoint() {
        String uuid = UUID.randomUUID().toString();
        User newUser = new User(uuid);

        given().body(newUser).contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().post()
                .then()
                .statusCode(200)
                .body("uuid", is(newUser.getUuid()));
    }

    @Test
    public void testDeleteEndpoint() {
        given()
                .header("Authorization", "Bearer " + token)
                .when().delete("/" + user.getUuid())
                .then()
                .statusCode(200);
    }

}