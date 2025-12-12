// PetState.java  (State pattern interface)
public interface PetState {
    void handleTick(SimplePet pet);
    void handleTask(SimplePet pet);
    String getName();
}
