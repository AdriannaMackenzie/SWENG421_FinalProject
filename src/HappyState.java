// HappyState.java
public class HappyState implements PetState {
    @Override
    public void handleTick(SimplePet pet) {
        pet.changeHappiness(-2);
        if (pet.getHappinessValue() < 70) {
            pet.setState(new NeutralState());
        }
    }

    @Override
    public void handleTask(SimplePet pet) {
        pet.changeHappiness(+5);
    }

    @Override
    public String getName() {
        return "Happy";
    }
}
