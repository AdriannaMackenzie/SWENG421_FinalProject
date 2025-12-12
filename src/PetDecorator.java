// PetDecorator.java  (Decorator base)
public abstract class PetDecorator implements Pet {
    protected final Pet inner;
    protected PetDecorator(Pet inner) { this.inner = inner; }

    @Override public void tick() { inner.tick(); }
    @Override public void onTaskCompleted() { inner.onTaskCompleted(); }
    @Override public String getMoodLabel() { return inner.getMoodLabel(); }
    @Override public String getStateName() { return inner.getStateName(); }
    @Override public int getHappiness() { return inner.getHappiness(); }
}
