// CatThemeFactory.java
public class CatThemeFactory implements ThemeFactory {
    @Override
    public Pet createPet() {
        return new SimplePet("Cat");
    }
}
