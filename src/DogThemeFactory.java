// DogThemeFactory.java
public class DogThemeFactory implements ThemeFactory {
    @Override
    public Pet createPet() {
        return new SimplePet("Dog");
    }
}
