package components.playerarea;

import javax.swing.*;

/**
 * 
 * @author mosek
 *
 */
public class ControllableTimer implements Runnable {
	
	/**
	 * 
	 */
    private volatile boolean running = false;
    
    /**
     * 
     */
    private Runnable updateTask; 
    
    /**
     * 
     */
    private long startTime;
 
    /**
     * 
     * @param updateTask
     */
    public ControllableTimer(Runnable updateTask) { 
        this.updateTask = updateTask;
    }
    
    /**
     * 
     * @return
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * 
     */
    public void start() {
        if (!running) {
            running = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * 
     */
    public void stop() {
        running = false;
    }
    
    /**
     * 
     */
    public void reset() {
        stop();
        SwingUtilities.invokeLater(() -> {
            updateTask.run();
        });
    }
    
    /**
     * 
     * @return
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * 
     */
    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while (running) {
            long elapsed = System.currentTimeMillis() - startTime;
            SwingUtilities.invokeLater(updateTask);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

}




