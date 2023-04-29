package io.github.magdaniedz.tests.user;

import io.github.magdaniedz.tests.testbases.SuiteTestBase;
import io.magdaniedz.github.main.pojo.ApiResponse;
import io.magdaniedz.github.main.pojo.user.User;
import io.magdaniedz.github.main.test.data.UserTestDataGenerator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreateUserTests extends SuiteTestBase {

    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {
        UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator();
        User user = userTestDataGenerator.generateUser();

        ApiResponse apiResponse = given().contentType("application/json")
                .body(user)
                .when().post("user")
                .then().statusCode(200).extract().as(ApiResponse.class);

        assertEquals(apiResponse.getCode(), Integer.valueOf(200), "Code");
        assertEquals(apiResponse.getType(), "unknown", "Type");
        assertEquals(apiResponse.getMessage(), "445", "Message");

    }

}
