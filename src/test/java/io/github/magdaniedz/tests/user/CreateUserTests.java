package io.github.magdaniedz.tests.user;

import io.github.magdaniedz.tests.testbases.SuiteTestBase;
import io.magdaniedz.github.main.pojo.ApiResponse;
import io.magdaniedz.github.main.pojo.user.User;
import io.magdaniedz.github.main.request.configuration.RequestConfigurationBuilder;
import io.magdaniedz.github.main.test.data.UserTestDataGenerator;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateUserTests extends SuiteTestBase {

    private User user;

    @Test
    public void givenUserWhenPostUserThenUserIsCreatedTest() {
        UserTestDataGenerator userTestDataGenerator = new UserTestDataGenerator();
        user = userTestDataGenerator.generateUser();

        ApiResponse apiResponse = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .body(user)
                .when().post("user")
                .then().statusCode(HttpStatus.SC_OK).extract().as(ApiResponse.class);

        ApiResponse expectedApiResponse = new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getId().toString());

        Assertions.assertThat(apiResponse).describedAs("Send User was different than received by API").usingRecursiveComparison().isEqualTo(expectedApiResponse);


    }

    @AfterMethod
    public void cleanUpAfterTest() {
        ApiResponse apiResponse = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("user/{username}", user.getUsername())
                .then().statusCode(HttpStatus.SC_OK).extract().as(ApiResponse.class);


        ApiResponse expectedApiResponse= new ApiResponse();
        expectedApiResponse.setCode(HttpStatus.SC_OK);
        expectedApiResponse.setType("unknown");
        expectedApiResponse.setMessage(user.getUsername());

        Assertions.assertThat(apiResponse).describedAs("API Response was different than expected").usingRecursiveComparison().isEqualTo(expectedApiResponse);

    }
}
