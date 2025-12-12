package petapp.scheduler;

import petapp.model.pet.Pet;

public class Scheduler
{

    private final Pet pet;
    private final int intervalMillis;

    private Thread worker;
    private boolean running;

    public Scheduler(Pet pet, int intervalMillis)
    {
        this.pet = pet;
        this.intervalMillis = intervalMillis;
    }

    public void start()
    {
        if (running)
        {
            return;
        }
        running = true;

        worker = new Thread(() -> runTickLoop());
        worker.setDaemon(true);
        worker.start();
    }

    public void runTickLoop()
    {
        while (running)
        {
            try
            {
                Thread.sleep(intervalMillis);
                pet.onTick();
            } catch (InterruptedException e)
            {
                // safely end the loop
                running = false;
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop()
    {
        running = false;
        if (worker != null)
        {
            worker.interrupt();
        }
    }
}
