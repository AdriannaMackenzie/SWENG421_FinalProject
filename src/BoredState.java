// BoredState.java
public class BoredState implements PetState {
    @Override
    public void handleTick(SimplePet pet) {
        pet.changeHappiness(-3);
        int h = pet.getHappinessValue();
        if (h < 30) pet.setState(new StressedState());
        else if (h >= 60) pet.setState(new NeutralState());
    }

    @Override
    public void handleTask(SimplePet pet) {
        pet.changeHappiness(+6);
    }

    @Override
    public String getName() {
        return "Bored";
    }
}
