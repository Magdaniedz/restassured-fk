package io.magdaniedz.github.main.test.data;

import io.magdaniedz.github.main.pojo.pet.Category;
import io.magdaniedz.github.main.pojo.pet.Pet;
import io.magdaniedz.github.main.pojo.pet.Tag;
import io.magdaniedz.github.main.pojo.user.User;
import io.magdaniedz.github.main.test.data.pet.PetCategory;
import io.magdaniedz.github.main.test.data.pet.PetStatus;
import io.magdaniedz.github.main.test.data.pet.PetTags;

import javax.swing.text.TabableView;
import javax.swing.text.html.HTML;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

public class PetTestDataGenerator extends TestDataGenerator {

    public Pet generatePet() {

        PetCategory petCategory = randomPetCategory();
        PetTags petTags = randomPetTags();
        PetStatus petStatus = randomPetStatus();

        Category category= new Category();
        category.setId(petCategory.getId());
        category.setName(petCategory.getCategoryName());

        Tag tag = new Tag();
        tag.setId(petTags.getId());
        tag.setName(petTags.getTagName());

        Pet pet = new Pet();
        pet.setName(faker().friends().character());
        pet.setId(faker().number().numberBetween(1, 9999));
        pet.setCategory(category);
        pet.setTags(Collections.singletonList(tag));
        pet.setPhotoUrls(Collections.singletonList(faker().internet().url()));
        pet.setStatus(petStatus.getStatus());
        return pet;
    }

    private PetStatus randomPetStatus() {
        int pick = new Random().nextInt(PetStatus.values().length);
        return PetStatus.values()[pick];
    }

    private PetTags randomPetTags() {
        int pick = new Random().nextInt(PetTags.values().length);
        return PetTags.values()[pick];
    }

    private PetCategory randomPetCategory() {
        int pick=new Random().nextInt(PetCategory.values().length);
        return PetCategory.values()[pick];
    }

}
