package specs;

import config.BaseTestConfig;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static utils.BreedListUtils.*;
import static utils.ImagesByBreedUtils.*;

@Epic("Dog API")
@Feature("List Images By Breed")
public class ImagesByBreedTest extends BaseTestConfig {

    private final Faker faker = new Faker();

    @Test
    @Story("List all images of a breed")
    @Description("Validates whether a breed has registered images")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldDisplayImagesOfASpecificBreed() {
        getImagesByBreed(getRandomBreed());
    }

    @Test
    @Story("Doesn't list images of a breed not found")
    @Description("Validate if the breeds not found have images")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNotDisplayImagesOfABreedNotFound() {
        getListImagesOfBreedNotFound(faker.lorem().characters(10,30));
    }

    @Test
    @Story("Doesn't list a non-existent breed")
    @Description("Validate if a non-existent breed has images")
    @Severity(SeverityLevel.NORMAL)
    public void shouldNotDisplayImagesOfAnUnspecifiedBreed() {
        getListImagesOfBreedNotFound("");
    }

}
