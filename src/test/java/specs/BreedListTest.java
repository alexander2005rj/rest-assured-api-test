package specs;

import config.BaseTestConfig;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static utils.BreedListUtils.*;

@Epic("Dog API")
@Feature("List All Breeds")
public class BreedListTest extends BaseTestConfig {

    @Test
    @Story("List all registered breeds")
    @Description("Validates the description of all registered breeds")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldDisplayAllRegisteredBreeds() {
        getBreedsListAll();
    }

}
