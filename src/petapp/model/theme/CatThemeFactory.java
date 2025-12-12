package petapp.model.theme;

import petapp.model.pet.BasePet;
import petapp.model.pet.Pet;

public class CatThemeFactory implements ThemeFactory
{

    @Override
    public Pet createPet()
    {
        // Can wrap with decorators later
        return new BasePet();
    }

    @Override
    public PetSprite createPetSprite()
    {
        // Placeholder; GUI will later interpret this Object as needed
        return new PetSprite("CAT");
    }
}
