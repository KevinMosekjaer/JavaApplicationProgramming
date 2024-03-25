/*
package components.playerarea;

import java.awt.EventQueue;

public class ControllableTimer extends Thread {

	static final int START = 1;
	    

	static final int STOP = 2;
	    

	static final int RESET = 3;
	    

	static final int TERMINATE=4;
	    
	private int status = START;
	private int elapsed=0;
	    
	//View view=null;
	PlayerArea view = null;

	    public ControllableTimer (PlayerArea remoteView)
	    {
	        view = remoteView;
	    }
	    public synchronized void setStatus(int cmd)
	    {
	        switch (cmd)
	        {
	            case START:
	                status=START;
	                notify();
	                break;
	            case STOP:
	                status=STOP;
	                break;
	            case RESET:
	                elapsed=0;
	                break;
	            case TERMINATE:
	                status=TERMINATE;
	        }
	    }
	    
	    
	    public synchronized int getStatus()
	    {
	        if (status==STOP)
	        {
	            try
	            {
	                wait(); //wait can only be called from a synchronized method.
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	        return status;
	    }
	    
	    public synchronized int getTime()
	    {
	        return elapsed;
	    }
	    
	    
	    public void run()
	    {
	        while (getStatus()!=TERMINATE)
	        {
	            try
	            {
	                sleep(1000); //Waits for 1000 milliseconds
	            }
	            catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	            
	            //Threadsafe code to modify your UI.  Injects operations into the UI thread.
	            EventQueue.invokeLater(new Runnable() {
	                @Override
	                public void run() {
	                    if (view != null) {
	                        view.setTime(++elapsed);
	                    }
	                }
	            });

	        }
	    }
	}
	*/
/*
// ControllableTimer.java
package components.playerarea;

public class ControllableTimer implements Runnable {
    private long startTime;
    private boolean running = false;
    private final PlayerArea view;
    private final int playerNumber;

    public ControllableTimer(PlayerArea view, int playerNumber) {
        this.view = view;
        this.playerNumber = playerNumber;
    }

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            long elapsed = System.currentTimeMillis() - startTime;
            view.updateTurnTimer(elapsed, playerNumber);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
*/

package components.playerarea;

import javax.swing.*;

public class ControllableTimer implements Runnable {
    private volatile boolean running = false;
    private final PlayerArea view;
    private long startTime;

    public ControllableTimer(PlayerArea view) {
        this.view = view;
    }

    public void start() {
        if (!running) {
            running = true;
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        running = false;
    }
    
    public void reset() {
    	startTime = 0;
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        while (running) {
            long elapsed = System.currentTimeMillis() - startTime;
            SwingUtilities.invokeLater(() -> view.updateGameTimer(formatTime(elapsed)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private String formatTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / 1000) / 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}




