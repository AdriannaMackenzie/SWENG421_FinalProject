// LionThemeFactory.java
public class LionThemeFactory implements ThemeFactory {
    @Override
    public Pet createPet() {
        return new SimplePet("Nittany Lion");
    }
}

