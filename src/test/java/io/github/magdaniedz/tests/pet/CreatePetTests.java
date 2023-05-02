package io.github.magdaniedz.tests.pet;

import io.github.magdaniedz.tests.testbases.SuiteTestBase;
import io.magdaniedz.github.main.pojo.ApiResponse;
import io.magdaniedz.github.main.pojo.pet.Pet;
import io.magdaniedz.github.main.request.configuration.RequestConfigurationBuilder;
import io.magdaniedz.github.main.test.data.PetTestDataGenerator;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class CreatePetTests extends SuiteTestBase {

    private Pet actualPet;


    @Test
    public void givenPetWhenPostPetThenPetThenPetIsCreatedTest() {


        Pet pet = new PetTestDataGenerator().generatePet();

        actualPet  = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification()).body(pet)
                .when().post("pet")
                .then().statusCode(HttpStatus.SC_OK).extract().as(Pet.class);

        Assertions.assertThat(actualPet).describedAs("Send Pet was different than received by API").usingRecursiveComparison().isEqualTo(pet);
    }


    @AfterMethod
    public void cleanUpAfterTest(){
        ApiResponse apiResponse = given().spec(RequestConfigurationBuilder.getDefaultRequestSpecification())
                .when().delete("pet/{petId}", actualPet.getId())
                .then().statusCode(HttpStatus.SC_OK).extract().as(ApiResponse.class);

    ApiResponse expectedApiResponse = new ApiResponse();
    expectedApiResponse.setCode(200);
    expectedApiResponse.setType("unknown");
    expectedApiResponse.setMessage(actualPet.getId().toString());

    Assertions.assertThat(apiResponse).describedAs("API Response from system was not as expected").usingRecursiveComparison().isEqualTo(expectedApiResponse);
    }
}
