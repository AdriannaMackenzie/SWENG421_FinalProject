// StressedState.java
public class StressedState implements PetState {
    @Override
    public void handleTick(SimplePet pet) {
        pet.changeHappiness(-1); // already low
    }

    @Override
    public void handleTask(SimplePet pet) {
        pet.changeHappiness(+8);
        if (pet.getHappinessValue() >= 40) {
            pet.setState(new BoredState());
        }
    }

    @Override
    public String getName() {
        return "Stressed";
    }
}
