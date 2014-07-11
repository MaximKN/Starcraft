package Starcraft;
import java.awt.event.*;

import javax.swing.*;

public class Clock
{
    private Timer timer;
    private CentralBank bank;
    
    private int counter = 0; // the duration
    private int delay = 100; // every 1 millisecond
    private int numOfMinerals;
    private int numOfGas;
    public void startClock()
    {
    	bank = new CentralBank();
    	bank.startBankTimer();
        
        ActionListener action = new ActionListener()
        {   
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	bank.addMinerals(bank.getCurrentMineralFlow());
            	bank.addGas(bank.getCurrentGasFlow());
            	numOfMinerals = bank.getCurrentMinerals();
            	numOfGas = bank.getCurrentGas();
            	// Just for testing purposes
            	//System.out.print(" Minerals "+numOfMinerals);
            	//System.out.println(" Gas "+numOfGas);
                counter++;
            }
        };
        timer = new Timer(delay, action);
        timer.setInitialDelay(0);
        timer.start();
    }
    public int getCounter()
    {
    	return counter;
    }
	public int getNumOfMinerals() {
		return numOfMinerals;
	}
	public void removeNumOfMinerals(int numOfMinerals) {
		bank.removeMinerals(numOfMinerals);
	}
	public void removeNumOfGas(int numOfGas) {
		bank.removeGas(numOfGas);
	}
	public int getNumOfGas() {
		return numOfGas;
	}
	public void unlockNewGas()
	{
		bank.unlockNewGas();
	}
	public void assignProbesToGas(int probesNum){
		bank.assignProbeGas(probesNum);
	}
	public void addFreeProbe()
	{
		bank.addFreeProbe();
	}
	public int getFreeProbes() {
		return bank.getFreeProbes();
	}
}
