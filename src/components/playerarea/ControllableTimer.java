package components.playerarea;

import javax.swing.SwingUtilities;

/**
 * Class holding timer
 *
 * @author Kevin Mosekjaer, Daniel Cormier
 */
public class ControllableTimer implements Runnable {

	/**
	 * holds if timer is running
	 */
	private volatile boolean running = false;

	/**
	 * holds update task variable
	 */
	private Runnable updateTask;

	/**
	 * holds start time
	 */
	private long startTime;

	/**
	 * Constructor
	 * @param updateTask u
	 */
	public ControllableTimer(Runnable updateTask) {
		this.updateTask = updateTask;
	}

	/**
	 * Getter for start time
	 * @return time
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * starts thread
	 */
	public void start() {
		if (!running) {
			running = true;
			Thread thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * stops timer
	 */
	public void stop() {
		running = false;
	}

	/**
	 * resets timer
	 */
	public void reset() {
		stop();
		SwingUtilities.invokeLater(() -> {
			updateTask.run();
		});
	}

	/**
	 * Function to check if timer is running
	 * @return running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * Timers main loop
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




