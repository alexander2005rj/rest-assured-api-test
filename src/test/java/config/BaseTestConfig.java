package config;

import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.Matchers.lessThan;

@ExtendWith({AllureJunit5.class})
public abstract class BaseTestConfig {

    @BeforeAll
    public static void setup() {

        RestAssured.filters(
                new AllureRestAssured(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://dog.ceo/api")
                .setContentType("application/json")
                .addHeader("Accept", "application/json")
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(lessThan(5000L))
                .build();

    }
}
