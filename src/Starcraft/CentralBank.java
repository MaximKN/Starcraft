package Starcraft;

/* 
 * This class holds all the information on the current resources 
 * available to the player. It also contains method for assigning
 * unoccupied probes to collecting mineral and gas. 
 */

public class CentralBank {

	private int freeProbes;
	private int mineralProbes;
	private int gasProbes;
	private int currentMinerals;
	private int currentGas;
	private int mineralPatchNumber = 8;
	private int maxProbesOnMinerals = mineralPatchNumber*3;
	private int maxProbesOnEachGas = 3;
	private int gasPlantsAvailable= 0;
	private int startingMinerals = 50;
	private double currentMineralFlow =0;
	private double currentGasFlow = 0;
	private double gasPerProbePerSecond = 38.0/60.0;
	private double mineralsPerProbePerSecond = 41.0/60.0;;
	private double mineralsThirdProbePerSeconds = 20.0/60.0;

	// Assigning probes to resources
	public void assignProbeGas(int probesNumber) {
		if (probesNumber > this.freeProbes) {
			System.out.println("Not enough free Probes");
		} else if (this.gasProbes + probesNumber > this.gasPlantsAvailable*this.maxProbesOnEachGas) {
			System.out.println("Max probes on Gas");
		} else {
			this.freeProbes -= probesNumber;
			this.gasProbes += probesNumber;
			this.recalcGasFlow();
		}
	}
	public void assignProbeMinerals(int probesNumber) {
		if (probesNumber > this.freeProbes) {
			System.out.println("Not enough free Probes");
		} else if (this.mineralProbes + probesNumber > this.maxProbesOnMinerals) {
			System.out.println("Max probes on Minerals");
		} else {
			this.freeProbes -= probesNumber;
			this.mineralProbes += probesNumber;
			this.recalcMineralFlow();
		}
	}

	// Removing probes from gathering resources
	public void removeProbeGas(int probesNumber) {
		if (this.gasProbes<0) {
			 System.out.println("No probes to remove");
		 } else {
				 this.freeProbes += probesNumber;
				 this.gasProbes -=probesNumber;
				 this.recalcGasFlow();
			 }
	}
	public void removeProbeMinerals(int probesNumber) {
		 if (this.mineralProbes<0) {
			 System.out.println("No probes to remove");
		 } else {
				 this.freeProbes += probesNumber;
				 this.mineralProbes -=probesNumber;
				 this.recalcMineralFlow();
			 }
		 }

	// Recalculating resource flow

	public void recalcMineralFlow() {
     if(this.mineralProbes<16){
    	
    	 this.currentMineralFlow = this.mineralProbes*this.mineralsPerProbePerSecond;
    	
     } else {
    	 this.currentMineralFlow = ((this.mineralProbes-16)*this.mineralsThirdProbePerSeconds) +
    			 ( this.mineralProbes*this.mineralsPerProbePerSecond);
     }
	}
	public void recalcGasFlow() {
		this.currentGasFlow = this.gasPerProbePerSecond * this.gasProbes;
	}
	
	// Printing method for resource output
	
	public String printResources() {
		return("|" + this.getCurrentMinerals() + " minerals"
				+ "|" + this.getCurrentGas() + " gas |");
	}
	public  void startBankTimer() {
		this.freeProbes = 6; 
		this.currentGas = 0;
		this.currentMinerals = this.startingMinerals;

		this.assignProbeMinerals(3);
		this.printResources();
	}

	// Adding or removing resources to bank
	public void addMinerals(double quantity){
		this.currentMinerals += quantity;
	}
	public void addGas(double quantity) {
		this.currentGas += quantity;
	}
	public void removeMinerals(int quantity){
		this.currentMinerals -= quantity;
	}
	public void removeGas(int quantity) {
		this.currentGas -= quantity;
	}
	public void unlockNewGas() {
		this.gasPlantsAvailable++; // Increase the number of available gas plants
	}
	
	// Getters
	public int getFreeProbes() {
		return this.freeProbes;
	}
	public int getMineralProbes() {
		return this.mineralProbes;
	}
	public int getGasProbes() {
		return this.gasProbes;
	}
	public int getCurrentMinerals() {
		return this.currentMinerals;
	}
	public int getCurrentGas() {
		return this.currentGas;
	}
	public void addFreeProbe() {
		this.freeProbes++;
	}
	public double getCurrentMineralFlow()
	{
		return currentMineralFlow;
	}
	public double getCurrentGasFlow()
	{
		return currentGasFlow;
	}
}
