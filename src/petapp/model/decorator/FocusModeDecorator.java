package petapp.model.decorator;

import petapp.model.pet.Pet;
import petapp.model.task.Task;

public class FocusModeDecorator extends PetDecorator
{

    private boolean focusActive;

    public FocusModeDecorator(Pet innerPet)
    {
        super(innerPet);
    }

    public void setFocusActive(boolean focusActive)
    {
        this.focusActive = focusActive;
    }

    public boolean isFocusActive()
    {
        return focusActive;
    }

    @Override
    public void onTick()
    {
        if (focusActive)
        {
            // Higher stakes: time hurts more in focus mode
            innerPet.onTick();
            innerPet.onTick();
        } else
        {
            innerPet.onTick();
        }
    }

    @Override
    public void applyTask(Task task)
    {
        // Always apply the task once through the normal pipeline
        super.applyTask(task);

        // If focus mode is active, apply one extra task effect as a doubling bonus
        if (focusActive)
        {
            innerPet.applyTask(task);
        }
    }
}

