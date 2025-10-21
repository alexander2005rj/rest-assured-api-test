package specs;

import config.BaseTestConfig;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static utils.RandomImageBreedUtils.*;

@Epic("Dog API")
@Feature("List Random Image of a Breed")
public class RandomImageBreedTest extends BaseTestConfig {

    @Test
    @Story("List all registered breeds")
    @Description("Validates the description of all registered breeds")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldDisplayAllRegisteredBreeds() {
        getRandomImageBreed();
    }

}
