// StreakBonusDecorator.java  (Concrete Decorator)
public class StreakBonusDecorator extends PetDecorator {

    private int streak = 0;

    public StreakBonusDecorator(Pet inner) {
        super(inner);
    }

    @Override
    public void onTaskCompleted() {
        streak++;
        inner.onTaskCompleted();
        // every 3 in a row -> extra boost
        if (streak >= 3 && inner instanceof SimplePet sp) {
            sp.changeHappiness(+3);
            streak = 0; // reset streak
        }
    }

    @Override
    public void tick() {
        // streak fades over time
        if (streak > 0) streak--;
        inner.tick();
    }
}
