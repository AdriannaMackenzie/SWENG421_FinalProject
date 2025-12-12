// Scheduler.java
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {

    private final int intervalMillis;
    private Pet pet;
    private Timer timer;

    public Scheduler(int intervalMillis) {
        this.intervalMillis = intervalMillis;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void start(Runnable onTickUIUpdate) {
        if (timer != null) timer.cancel();
        timer = new Timer("PetScheduler", true);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (pet != null) {
                    pet.tick();
                    SwingUtilities.invokeLater(onTickUIUpdate);
                }
            }
        }, intervalMillis, intervalMillis);
    }
}
