// NeutralState.java
public class NeutralState implements PetState {
    @Override
    public void handleTick(SimplePet pet) {
        pet.changeHappiness(-2);
        int h = pet.getHappinessValue();
        if (h >= 85) pet.setState(new HappyState());
        else if (h < 50) pet.setState(new BoredState());
    }

    @Override
    public void handleTask(SimplePet pet) {
        pet.changeHappiness(+4);
    }

    @Override
    public String getName() {
        return "Neutral";
    }
}
