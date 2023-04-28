package io.github.magdaniedz.tests.pet;

import io.magdaniedz.github.main.pojo.Category;
import io.magdaniedz.github.main.pojo.Pet;
import io.magdaniedz.github.main.pojo.Tag;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CreatePetTests {


    @BeforeMethod
    public void SetupConfiguration() {

        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


    @Test
    public void givenPetWhenPostPetThenPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(123);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(123);
        pet.setCategory(category);
        pet.setPhotoUrls(Collections.singletonList(("https://pl.pinterest.com/pin/813673857669477920/")));
        pet.setTags(Collections.singletonList(tag));

        Pet actualPet = given().body(pet).contentType("application/json")
                .when().post("pet")
                .then().statusCode(200).extract().as(Pet.class);

        assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        assertEquals(actualPet.getName(), pet.getName(), "Pet name");


    }
}
