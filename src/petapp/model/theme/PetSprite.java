package petapp.model.theme;

public class PetSprite
{

    private final String themeName;

    public PetSprite(String themeName)
    {
        this.themeName = themeName.toLowerCase();
    }

    public String getImageFileForState(String stateName)
    {
        String state = stateName.toLowerCase();
        return themeName + "_" + state + ".png";
    }
}

