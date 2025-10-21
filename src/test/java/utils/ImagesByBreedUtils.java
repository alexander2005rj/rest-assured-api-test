package utils;

import config.DogApiEndpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ImagesByBreedUtils {

    public static Response getImagesByBreed(String breedName) {
        return given()
                    .pathParam("breedName", breedName)
                .when()
                    .get(DogApiEndpoints.GET_SPECIFIC_IMAGES_BY_BREED)
                .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/get-images-by-breed-schema.json"))
                    .extract()
                    .response();
    }

    public static Response getListImagesOfBreedNotFound(String invalidBreedName) {
        return given()
                    .pathParam("breedName", invalidBreedName)
                .when()
                    .get(DogApiEndpoints.GET_SPECIFIC_IMAGES_BY_BREED)
                .then()
                    .statusCode(404)
                    .body(matchesJsonSchemaInClasspath("schemas/breed-not-found-schema.json"))
                    .extract()
                    .response();
    }

}
