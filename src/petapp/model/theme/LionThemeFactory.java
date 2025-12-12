package petapp.model.theme;

import petapp.model.pet.BasePet;
import petapp.model.pet.Pet;

public class LionThemeFactory implements ThemeFactory
{

    @Override
    public Pet createPet()
    {
        return new BasePet();
    }

    @Override
    public PetSprite createPetSprite()
    {
        return new PetSprite("LION");
    }
}
