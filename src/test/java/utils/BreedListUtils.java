package utils;

import java.util.*;
import config.DogApiEndpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BreedListUtils {

    public static Response getBreedsListAll() {
        return given()
                .when()
                    .get(DogApiEndpoints.GET_BREEDS_LIST_ALL)
                .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/get-all-breeds-schema.json"))
                    .extract()
                    .response();
    }

    public static String getRandomBreed() {
        Map<String, Object> message =  given()
                                        .when()
                                            .get(DogApiEndpoints.GET_BREEDS_LIST_ALL)
                                        .then()
                                            .statusCode(200)
                                            .extract()
                                            .path("message");

        // Converte as chaves em lista
        List<String> breeds = new ArrayList<>(message.keySet());

        // Retorna uma chave aleatória
        return breeds.get(new Random().nextInt(breeds.size()));
    }

}
