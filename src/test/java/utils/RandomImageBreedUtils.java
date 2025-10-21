package utils;

import config.DogApiEndpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RandomImageBreedUtils {

    public static Response getRandomImageBreed() {
        return given()
                .when()
                    .get(DogApiEndpoints.GET_RANDOM_IMAGE_BREED)
                .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/get-random-image-breed-schema.json"))
                    .extract()
                    .response();
    }

}
