// SimplePet.java  (Base concrete Pet using State pattern)
public class SimplePet implements Pet {

    private final String themeName;
    private int happiness = 70;  // 0–100
    private PetState state = new NeutralState();

    public SimplePet(String themeName) {
        this.themeName = themeName;
    }

    // package private helpers for states
    void changeHappiness(int delta) {
        happiness += delta;
        if (happiness < 0) happiness = 0;
        if (happiness > 100) happiness = 100;
    }

    void setState(PetState newState) {
        this.state = newState;
    }

    int getHappinessValue() {
        return happiness;
    }

    @Override
    public void tick() {
        state.handleTick(this);
    }

    @Override
    public void onTaskCompleted() {
        state.handleTask(this);
    }

    @Override
    public String getMoodLabel() {
        if (happiness >= 80) return "Happy " + themeName;
        if (happiness >= 50) return "Neutral " + themeName;
        if (happiness >= 25) return "Bored " + themeName;
        return "Stressed " + themeName;
    }

    @Override
    public String getStateName() {
        return state.getName();
    }

    @Override
    public int getHappiness() {
        return happiness;
    }
}
