package petapp.model.decorator;

import petapp.model.pet.Pet;
import petapp.model.task.Task;

public class StreakBonusDecorator extends PetDecorator
{

    private int streakCount;

    public StreakBonusDecorator(Pet innerPet)
    {
        super(innerPet);
    }

    public int getStreakCount()
    {
        return streakCount;
    }

    public void resetStreak()
    {
        streakCount = 0;
    }

  @Override
    public void applyTask(Task task)
    {
        Instant now = Instant.now();

        if (lastTaskTime != null)
        {
            Duration gap = Duration.between(lastTaskTime, now);
            if (gap.compareTo(STREAK_TIMEOUT) > 0)
            {
                // More than 1 hour since last task, streak broken
                streakCount = 0;
            }
        }

        // Update timestamp and increment streak
        lastTaskTime = now;
        streakCount++;

        // Always apply base task effect
        innerPet.applyTask(task);

        // Every 5th task gets a bonus
        if (streakCount % 5 == 0)
        {
            innerPet.applyTask(task);
        }
    }
}