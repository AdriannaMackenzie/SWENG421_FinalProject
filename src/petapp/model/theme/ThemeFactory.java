package petapp.model.theme;

import petapp.model.pet.Pet;

public interface ThemeFactory
{

    Pet createPet();

    PetSprite createPetSprite();
}
