package net.woolgens.user;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import net.woolgens.user.model.Season;
import net.woolgens.user.model.User;
import net.woolgens.user.repository.UserRepository;
import net.woolgens.user.resource.UserResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
public class UserResourceTest {



    @Inject
    UserRepository repository;
    User user;

    @BeforeEach
    public void setup() {
        user = new User(UUID.randomUUID().toString());
        user.setName("Test");
        user.setStats(new HashMap<>());
        user.getStats().put("playtime", 10000l);
        repository.persist(user);

    }

    @Test
    public void testGetAllEndpoint() {
        given()
                .when().get("/" )
                .then()
                .statusCode(200)
                .body("isEmpty()", is(false));
    }

    @Test
    public void testGetAllSortedEndpoint() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            User user = new User(UUID.randomUUID().toString());
            user.setStats(new HashMap<>());
            user.setSeasons(new HashMap<>());

            Season season = new Season();
            season.setLevel(random.nextInt(100));

            user.getSeasons().put("1", season);

            user.getStats().put("playtime", random.nextLong());
            repository.persist(user);
        }
        given()
                .when().get("/?sorted=seasons.1.level&limit=2" )
                .then()
                .statusCode(200)
                .body("isEmpty()", is(false));
    }

    @Test
    public void testGetAllSmallEndpoint() {
        given()
                .when().get("/?small=true" )
                .then()
                .statusCode(200)
                .body("isEmpty()", is(false));
    }


    @Test
    public void testGetEndpoint() {
        given()
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
                .when().post()
                .then()
                .statusCode(200)
                .body("uuid", is(newUser.getUuid()));
    }

    @Test
    public void testDeleteEndpoint() {
        given()
                .when().delete("/" + user.getUuid())
                .then()
                .statusCode(200);
    }

}