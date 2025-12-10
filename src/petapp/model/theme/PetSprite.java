package petapp.model.theme;

public class PetSprite
{

    private final Object imageAssets;

    public PetSprite(Object imageAssets)
    {
        this.imageAssets = imageAssets;
    }

    public Object getImage()
    {
        return imageAssets;
    }
}

