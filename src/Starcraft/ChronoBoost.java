package Starcraft;

/*
 * One on the extensions was to implement Chrono Boost. 
 * This is used to speed up building time if there is enough energy in Nexus 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ChronoBoost {
	private static final int MAX_ENERGY = 100;
	private int energy;
	private Timer timer;
    private int counterCB = 0; // the duration
    private int delay = 100; // every 1 millisecond
    
	public ChronoBoost(){
		ActionListener action = new ActionListener()
        {   
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	if (energy < MAX_ENERGY){
            		energy++;
            	}
                counterCB++;
            }
        };
        timer = new Timer(delay, action);
        timer.setInitialDelay(0);
        timer.start();
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getCounterCB() {
		return counterCB;
	}
	public void setCounterCB(int counterCB) {
		this.counterCB = counterCB;
	}
}
