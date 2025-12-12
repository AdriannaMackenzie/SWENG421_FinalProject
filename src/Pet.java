// Pet.java
public interface Pet {
    void tick();                 // called by Scheduler
    void onTaskCompleted();      // positive event
    String getMoodLabel();
    String getStateName();
    int getHappiness();
}
