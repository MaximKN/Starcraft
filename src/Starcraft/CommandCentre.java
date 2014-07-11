package Starcraft;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

/*
 * 	This class is responsible for buildings, units and upgrades. 
 */
public class CommandCentre {

	public static final int MAX_SUPPLY = 200;
	// Number of minerals required for buildings
	public static final int NEXUS_MIN = 400;
	public static final int PYLON_MIN = 100;
	public static final int ASSIMILATOR_MIN = 75;
	public static final int GATEWAY_MIN = 150;
	public static final int CYB_CORE_MIN = 150;
	public static final int ROB_FAC_MIN = 200;
	public static final int STARGATE_MIN = 150;
	public static final int FORGE_MIN = 150;
	public static final int TWILIGHT_MIN = 150;
	public static final int TEMP_ARC_MIN = 150;
	public static final int DARK_SH_MIN = 100;
	public static final int ROB_BAY_MIN = 200;
	public static final int FLEET_BEACON_MIN = 300;
	public static final int PHOTON_CAN_MIN = 150;
	
	// Number of gas required for buildings
	public static final int ROB_FAC_GAS = 100;
	public static final int STARGATE_GAS = 150;
	public static final int TWILIGHT_GAS = 100;
	public static final int TEMP_ARC_GAS = 200;
	public static final int DARK_SH_GAS = 250;
	public static final int ROB_BAY_GAS = 200;
	public static final int FLEET_BEACON_GAS = 200;
	
	// Number of minerals for units
	public static final int PROBE_MIN = 50;
	public static final int ZEALOT_MIN = 100;
	public static final int STALKER_MIN = 120;
	public static final int SENTRY_MIN = 50;
	public static final int OBSERVER_MIN = 25;
	public static final int IMMORTAL_MIN = 250;
	public static final int PHOENIX_MIN = 150;
	public static final int VOID_RAY_MIN = 250;
	public static final int ORACLE_MIN = 150;
	public static final int WARP_PRISM_MIN = 200;
	public static final int COLOSSUS_MIN = 300;
	public static final int TEMPEST_MIN = 300;
	public static final int HIGH_TEMPLAR_MIN = 50;
	public static final int DARK_TEMPLAR_MIN = 125;
	public static final int CARRIER_MIN = 350;
	public static final int INTERCEPTOR_MIN = 25;
	public static final int MOTHERSHIP_CORE_MIN = 100;
	public static final int MOTHERSHIP_MIN = 300;
	
	// Number of minerals for units
	public static final int STALKER_GAS = 50;
	public static final int SENTRY_GAS = 100;
	public static final int OBSERVER_GAS = 75;
	public static final int IMMORTAL_GAS = 100;
	public static final int PHOENIX_GAS = 100;
	public static final int VOID_RAY_GAS = 150;
	public static final int ORACLE_GAS = 150;
	public static final int COLOSSUS_GAS = 200;
	public static final int TEMPEST_GAS = 200;
	public static final int HIGH_TEMPLAR_GAS = 150;
	public static final int DARK_TEMPLAR_GAS = 125;
	public static final int CARRIER_GAS = 250;
	public static final int MOTHERSHIP_CORE_GAS = 100;
	public static final int MOTHERSHIP_GAS = 300;
	
	// Booleans needed to check structure
	private boolean hadNexus;
	private boolean hadPylon;
	private boolean hadGateway;
	private boolean hadCyberneticsCore;
	private boolean hadRoboticsFacility;
	private boolean hadStargate;
	private boolean hadForge;
	
	// Booleans needed to check units
	private boolean hadRoboticsBay;
	private boolean hadFleetBeacon;
	private boolean hadTemplarArchives;
	private boolean hadDarkShrine;
	private boolean hadTwilightCouncil; 
	private boolean hadMothershipCore;
	private boolean hadCarrier;
	
	// Research booleans
	private boolean hadWarpGateResearch;
	private boolean hadWarpGate;
	private boolean canConstUnit;
	private boolean warpIn;
	private int numOfSupply;
	
	// Upgrade booleans 
	private boolean hadGWeapon1;
	private boolean hadGWeapon2;
	private boolean hadGArmor1;
	private boolean hadGArmor2;
	private boolean hadShield1;
	private boolean hadShield2;
	private boolean hadAirWeapon1;
	private boolean hadAirWeapon2;
	private boolean hadAirArmor1;
	private boolean hadAirArmor2;
	
	private Clock clock;
	private int[] numOfBuild = new int[59];
    private ArrayList<ChronoBoost> chronoBoost = new ArrayList<ChronoBoost>();
	private boolean chronoBoostActive;
	private	double accelerator;
	
    public void printTime(){
		TimeConverter t = new TimeConverter(clock.getCounter());
		t.printTime();
	}
	
    // Extension Chrono Boost
	public void useChronoBoost(){
		Timer t = new Timer();
		for (int i = 0 ; i < chronoBoost.size(); i++){
			if (chronoBoost.get(i).getEnergy() >= 25 && this.chronoBoostActive == false){
				this.chronoBoostActive = true;
				accelerator = 0.5;
				t.schedule(new TimerTask() {
	            	@Override
	                public void run() {
	            		chronoBoostActive = false;
	            		accelerator = 1;
	                }
	            }, clock.getCounter() + 20000);
				chronoBoost.get(i).setEnergy(chronoBoost.get(i).getEnergy() - 25);
			}
		}
	}
	public boolean warpInUnit()
	{
		if (hadWarpGate == true && warpIn == true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			warpIn = false;
			return true;
		}
		return false;
	}
	
    // Constructor
	public CommandCentre(){
		for (int i = 0; i < 59; i++){
			this.numOfBuild[i] = 0; // Initial value for all units and buildings
		}
		this.numOfBuild[0] = 1;
		this.hadNexus = true;
		this.canConstUnit = true;
		this.numOfSupply = 4;
		this.clock = new Clock();
		this.clock.startClock();
		chronoBoost.add(new ChronoBoost());
	}
	
	// Build buildings								
    public boolean buildNexus()
    {
    	if (clock.getNumOfMinerals() >= NEXUS_MIN)
		{ 
			this.clock.removeNumOfMinerals(NEXUS_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Nexus		");
			printTime();
			this.numOfBuild[0]++;
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            		if (numOfSupply + 10 <= MAX_SUPPLY){
            			numOfSupply = numOfSupply + 10;
            		}
            		hadNexus = true;
            		chronoBoost.add(new ChronoBoost());
			return true;
		}return false;
    }
    public boolean buildPylon()
    {
    	if (clock.getNumOfMinerals() >= PYLON_MIN)
		{
    		clock.removeNumOfMinerals(PYLON_MIN);
    		System.out.print("	"+numOfSupply+"	");
			System.out.print("Pylon		");
			printTime();
    		this.numOfBuild[1]++; 
    		try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
	        hadPylon = true;
	        if (numOfSupply + 8 <= MAX_SUPPLY){
	            numOfSupply = numOfSupply + 8;
	        }
			return true;
		}return false;
    }
    public boolean buildAssimilator()
    {
    	if (clock.getNumOfMinerals() >= ASSIMILATOR_MIN)
		{ 
			clock.removeNumOfMinerals(ASSIMILATOR_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Assimilator		");
			printTime();
    		numOfBuild[2]++;
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
	           clock.unlockNewGas();
	           clock.assignProbesToGas(3);
	                
			return true;
		}else 
		{
			return false;
		}
    }
    public boolean buildGateway()
    {
    	if (clock.getNumOfMinerals() >= GATEWAY_MIN && this.hadPylon == true)
		{ 
			clock.removeNumOfMinerals(GATEWAY_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Gateway		");
			printTime();
    		numOfBuild[3]++; 
    		try {
				Thread.sleep(6500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		hadGateway = true;
			return true;
		}return false;
    }
    public boolean buildCyberneticsCore()
    {
    	if (clock.getNumOfMinerals() >= CYB_CORE_MIN && this.hadPylon == true && this.hadGateway == true)
		{ 
			clock.removeNumOfMinerals(CYB_CORE_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Cybernetic Core	");
			printTime();
    		numOfBuild[4]++; 
    		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            hadCyberneticsCore = true;
                
			return true;
		}return false;
    }
    public boolean buildRoboticsFacility()
    {
    	if (clock.getNumOfMinerals() >= ROB_FAC_MIN 
    			&& clock.getNumOfGas() >= ROB_FAC_GAS 
    			&& this.hadCyberneticsCore == true && this.hadPylon == true)
		{ 
			clock.removeNumOfMinerals(ROB_FAC_MIN);
			clock.removeNumOfGas(ROB_FAC_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Robotics Facility	");
			printTime();
    		numOfBuild[5]++; 
    		try {
				Thread.sleep(6500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		hadRoboticsFacility = true;
			return true;
		}return false;
    }
    public boolean buildStargate()
    {
    	if (clock.getNumOfMinerals() >= STARGATE_MIN 
    			&& clock.getNumOfGas() >= STARGATE_GAS 
    			&& this.hadCyberneticsCore == true && this.hadPylon == true)
		{ 
			clock.removeNumOfMinerals(STARGATE_MIN);
			clock.removeNumOfGas(STARGATE_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Stargate		");
			printTime();
    		numOfBuild[6]++; 
    		try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		hadStargate = true;
			return true;
		}return false;
    }
    public boolean buildForge()
    {
    	if (clock.getNumOfMinerals() >= FORGE_MIN && this.hadPylon == true)
		{ 
    		
			clock.removeNumOfMinerals(FORGE_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Forge		");
			printTime();
    		numOfBuild[7]++; 
    		try {
				Thread.sleep(4500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            		hadForge = true;
			return true;
		}return false;
    }
    public boolean buildTwilightCouncil()
    {
    	if (clock.getNumOfMinerals() >= TWILIGHT_MIN 
    			&& clock.getNumOfGas() >= TWILIGHT_GAS 
    			&& this.hadCyberneticsCore == true && this.hadPylon == true)
		{ 
    		
			clock.removeNumOfMinerals(TWILIGHT_MIN);
			clock.removeNumOfGas(TWILIGHT_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Twilight Council	");
			printTime();
    		numOfBuild[8]++; 
    		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			return true;
		}return false;
    }
    public boolean buildTemplarArchives()
    {
    	if (clock.getNumOfMinerals() >= TEMP_ARC_MIN 
    			&& clock.getNumOfGas() >= TEMP_ARC_GAS 
    			&& this.hadTwilightCouncil == true && this.hadPylon == true)
		{ 
    		
			clock.removeNumOfMinerals(TEMP_ARC_MIN);
			clock.removeNumOfGas(TEMP_ARC_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Templar Archives	");
			printTime();
    		numOfBuild[9]++; 
    		try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		hadTemplarArchives = true;
			return true;
		}return false;
    }
    public boolean buildDarkShrine()
    {
    	if (clock.getNumOfMinerals() >= DARK_SH_MIN 
    			&& clock.getNumOfGas() >= DARK_SH_GAS 
    			&& this.hadTwilightCouncil == true && this.hadPylon == true)
		{ 
    		
			clock.removeNumOfMinerals(DARK_SH_MIN);
			clock.removeNumOfGas(DARK_SH_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Dark Shrine		");
			printTime();
    		numOfBuild[10]++; 
    		try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            hadDarkShrine = true;
            return true;
		}return false;
    }
    public boolean buildRoboticsBay()
    {
    	if (clock.getNumOfMinerals() >= ROB_BAY_MIN 
    			&& clock.getNumOfMinerals() >= ROB_BAY_GAS 
    			&& this.hadRoboticsFacility == true && this.hadPylon == true)
		{ 
    		
			clock.removeNumOfMinerals(ROB_BAY_MIN);
			clock.removeNumOfMinerals(ROB_BAY_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Robotics Bay		");
			printTime();
			numOfBuild[11]++; 
			try {
				Thread.sleep(6500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			hadRoboticsBay = true;
			return true;
		}return false;
    }
    public boolean buildFleetBeacon()
    {
    	if (clock.getNumOfMinerals() >= FLEET_BEACON_MIN 
    			&& clock.getNumOfGas() >= FLEET_BEACON_GAS 
    			&& this.hadStargate == true && this.hadPylon == true)
		{ 
    		
			clock.removeNumOfMinerals(FLEET_BEACON_MIN);
			clock.removeNumOfGas(FLEET_BEACON_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Fleet Beacon		");
			printTime();
    		numOfBuild[12]++; 
    		try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		hadFleetBeacon = true;
			return true;
		}return false;
    }
    public boolean buildPhotonCannon()
    {
    	if (this.hadForge == true && clock.getNumOfMinerals() >= 150)
		{ 
    		
			clock.removeNumOfMinerals(150);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Photon Cannon	");
			printTime();
    		numOfBuild[13]++;
    		try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			return true;
		}return false;
    }
    
    public boolean buildWarpGate()
    {
    	if (this.hadGateway == true && this.numOfBuild[3] >= 1 && this.hadWarpGateResearch == true)
		{ 
    		
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Warp Gate		");
			printTime();
    		canConstUnit = false;
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadWarpGate = true;
            		warpIn = true;
			return true;
		}return false;
    }
    
    // Create units
    public boolean createProbe()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= PROBE_MIN 
    			&& this.numOfSupply >= 1 && this.hadNexus == true)
		{ 
    		
			clock.removeNumOfMinerals(PROBE_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Probe		");
			printTime();
    		numOfBuild[15]++; 
    		numOfSupply = numOfSupply - 1;
    		useChronoBoost();
    		
    		if (warpInUnit() == false)
    		{
    			try {
    				Thread.sleep((long) (accelerator*1700));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
	            		clock.addFreeProbe();
    		}
			return true;
		}return false;
    }
    public boolean createZealot()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= ZEALOT_MIN 
    			&& this.numOfSupply >= 2 && this.hadGateway == true)
		{ 
    		//
			clock.removeNumOfMinerals(ZEALOT_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Zealot		");
			printTime();
    		numOfBuild[16]++; 
    		useChronoBoost();
    		numOfSupply = numOfSupply - 2;
    		
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*3800));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		else if (hadWarpGate == true)
    		{
    			try {
    				Thread.sleep((long) (accelerator*2800));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
			return true;
		}return false;
    }
    public boolean createStalker()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= STALKER_MIN 
    			&& clock.getNumOfMinerals() >= STALKER_GAS && this.numOfSupply >= 2 
    			&& this.hadCyberneticsCore == true && this.hadGateway == true)
		{ 
    		
			clock.removeNumOfMinerals(STALKER_MIN);
			clock.removeNumOfGas(STALKER_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Stalker		");
			printTime();
    		numOfBuild[17]++;
    		numOfSupply = numOfSupply - 2;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*4200));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		else if (hadWarpGate == true)
    		{
    			try {
    				Thread.sleep((long) (accelerator*3200));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
			return true;
		}return false;
    }
    public boolean createSentry()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= SENTRY_MIN 
    			&& clock.getNumOfMinerals() >= SENTRY_GAS
    			&& this.numOfSupply >= 1 && this.hadGateway == true)
		{ 
    		
			clock.removeNumOfMinerals(SENTRY_MIN);
			clock.removeNumOfGas(SENTRY_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Sentry		");
			printTime();
    		numOfBuild[18]++; 
    		numOfSupply = numOfSupply - 1;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*3700));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		else if (hadWarpGate == true)
    		{
    			try {
    				Thread.sleep((long) (accelerator*3200));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		return true;
		}return false;
    }
    public boolean createObserver()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= OBSERVER_MIN 
    			&& clock.getNumOfGas() >= OBSERVER_GAS
    			&& this.numOfSupply >= 1 && this.hadRoboticsFacility)
		{ 
    		
			clock.removeNumOfMinerals(OBSERVER_MIN);
			clock.removeNumOfGas(OBSERVER_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Observer		");
			printTime();
    		numOfBuild[19]++; 
    		numOfSupply = numOfSupply - 1;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*4000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		return true;
		}return false;
    }
    public boolean createImmortal()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= IMMORTAL_MIN 
    			&& clock.getNumOfGas() >= IMMORTAL_GAS 
    			&& this.numOfSupply >= 4 && this.hadRoboticsFacility)
		{ 
    		
			clock.removeNumOfMinerals(IMMORTAL_MIN);
			clock.removeNumOfGas(IMMORTAL_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Immortal		");
			printTime();
    		numOfBuild[20]++; 
    		numOfSupply = numOfSupply - 4;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*5500));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
			}return true;
		}return false;
    }
    public boolean createPhoenix()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= PHOENIX_MIN
    			&& clock.getNumOfGas() >= PHOENIX_GAS 
    			&& this.numOfSupply >= 2 && this.hadStargate == true)
		{ 
    		
			clock.removeNumOfMinerals(PHOENIX_MIN);
			clock.removeNumOfGas(PHOENIX_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Phoenix		");
			printTime();
    		numOfBuild[21]++; 
    		numOfSupply = numOfSupply - 2;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*3500));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createVoidRay()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= VOID_RAY_MIN 
    			&& clock.getNumOfGas() >= VOID_RAY_GAS 
    			&& this.numOfSupply >= 3 && this.hadStargate)
		{ 
    		
			clock.removeNumOfMinerals(PROBE_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Void Ray		");
			printTime();
    		numOfBuild[22]++; 
    		numOfSupply = numOfSupply - 3;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*6000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createOracle()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= ORACLE_MIN
    			&& clock.getNumOfGas() >= ORACLE_GAS && this.numOfSupply >= 3 
    			&& this.hadStargate == true)
		{ 
    		
			clock.removeNumOfMinerals(ORACLE_MIN);
			clock.removeNumOfGas(ORACLE_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Oracle		");
			printTime();
    		numOfBuild[23]++; 
    		numOfSupply = numOfSupply - 3;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*5000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createWarpPrism()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= WARP_PRISM_MIN 
    			&& this.numOfSupply >= 2 && this.hadRoboticsFacility == true)
		{ 
    		
			clock.removeNumOfMinerals(WARP_PRISM_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Warp Prism		");
			printTime();
    		numOfBuild[24]++; 
    		numOfSupply = numOfSupply - 2;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*5000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createColossus()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= COLOSSUS_MIN 
    			&& clock.getNumOfGas() >= COLOSSUS_GAS && this.numOfSupply >= 6 
    			&& this.hadRoboticsBay == true && this.hadRoboticsFacility == true)
		{ 
    		
			clock.removeNumOfMinerals(COLOSSUS_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Colossus		");
			printTime();
    		numOfBuild[25]++; 
    		numOfSupply = numOfSupply - 6;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*7500));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createTempest()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= TEMPEST_MIN 
    			&& clock.getNumOfGas() >= TEMPEST_GAS && this.numOfSupply >= 4 
    			&& this.hadFleetBeacon == true && this.hadStargate == true)
		{ 
    		
			clock.removeNumOfMinerals(TEMPEST_MIN);
			clock.removeNumOfGas(TEMPEST_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Tempest		");
			printTime();
    		numOfBuild[26]++; 
    		numOfSupply = numOfSupply - 4;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*6000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createHighTemplar()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= HIGH_TEMPLAR_MIN 
    			&& clock.getNumOfGas() >= HIGH_TEMPLAR_GAS && this.numOfSupply >= 2 
    			&& this.hadTemplarArchives == true && this.hadGateway == true)
		{ 
    		
			clock.removeNumOfMinerals(HIGH_TEMPLAR_MIN);
			clock.removeNumOfGas(HIGH_TEMPLAR_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("High Templar		");
			printTime();
    		numOfBuild[27]++; 
    		numOfSupply = numOfSupply - 2;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*5500));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		else if (hadWarpGate == true)
    		{
    			try {
    				Thread.sleep((long) (accelerator*4500));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		return true;
		}return false;
    }
    public boolean createDarkTemplar()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= DARK_TEMPLAR_MIN 
    			&& clock.getNumOfGas() >= DARK_TEMPLAR_GAS && this.numOfSupply >= 2 
    			&& this.hadDarkShrine == true && this.hadGateway == true)
		{ 
    		
			clock.removeNumOfMinerals(DARK_TEMPLAR_MIN);
			clock.removeNumOfGas(DARK_TEMPLAR_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Dark Templar		");
			printTime();
    		numOfBuild[28]++; 
    		numOfSupply = numOfSupply - 2;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*5500));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		else if (hadWarpGate == true)
    		{
    			try {
    				Thread.sleep((long) (accelerator*4500));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}
    		return true;
		}return false;
    }
    public boolean createArchon()
    {
    	if (canConstUnit && this.numOfSupply >= 4 && (this.numOfBuild[27] >= 1 && this.numOfBuild[28] >= 1))
		{ 
    		
    		System.out.print("	"+numOfSupply+"	");
			System.out.print("Archon		");
			printTime();
    		numOfBuild[29]++; 
    		numOfSupply = numOfSupply - 4;
    		useChronoBoost();
    		try {
				Thread.sleep((long) (accelerator*1200));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}    		return true;
		}return false;
    }
    public boolean createCarrier()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= CARRIER_MIN 
    			&& clock.getNumOfGas() >= DARK_TEMPLAR_GAS && this.numOfSupply >= 6
    			&& this.hadFleetBeacon == true && this.hadStargate == true)
		{ 
    		
			clock.removeNumOfMinerals(CARRIER_MIN);
			clock.removeNumOfGas(CARRIER_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Carrier		");
			printTime();
    		numOfBuild[30]++; 
    		numOfSupply = numOfSupply - 6;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*12000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createInterceptor()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= INTERCEPTOR_MIN 
    			&& clock.getNumOfGas() >= DARK_TEMPLAR_GAS && this.hadCarrier == true)
		{ 
    		
			clock.removeNumOfMinerals(INTERCEPTOR_MIN);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Interceptor		");
			printTime();
    		numOfBuild[31]++; 
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*800));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createMothershipCore()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= MOTHERSHIP_CORE_MIN 
    			&& clock.getNumOfGas() >= MOTHERSHIP_CORE_GAS && this.numOfSupply >= 2 
    			&& this.hadCyberneticsCore == true && this.hadNexus == true)
		{ 
    		
			clock.removeNumOfMinerals(MOTHERSHIP_CORE_MIN);
			clock.removeNumOfGas(MOTHERSHIP_CORE_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Mothership Core	");
			printTime();
    		numOfBuild[32]++; 
    		numOfSupply = numOfSupply - 2;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*3000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    public boolean createMothership()
    {
    	if (canConstUnit && clock.getNumOfMinerals() >= MOTHERSHIP_MIN &&
    			clock.getNumOfGas() >= MOTHERSHIP_GAS && this.numOfSupply >= 8 
    			&& this.hadFleetBeacon == true && this.hadMothershipCore == true)
		{ 
    		
			clock.removeNumOfMinerals(MOTHERSHIP_CORE_MIN);
			clock.removeNumOfGas(MOTHERSHIP_CORE_GAS);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Mothership		");
			printTime();
    		numOfBuild[33]++; 
    		numOfSupply = numOfSupply - 8;
    		useChronoBoost();
    		if (warpInUnit() == false){
    			try {
    				Thread.sleep((long) (accelerator*10000));
    			} catch (InterruptedException e) {
    				
    				e.printStackTrace();
    			}
    		}return true;
		}return false;
    }
    
    // Research 
    public boolean researchWarpGate(){
    	if (clock.getNumOfMinerals() >= 50 && clock.getNumOfMinerals() >= 50 && 
    			this.hadWarpGateResearch == false && this.hadCyberneticsCore == true)
		{ 
    		
			clock.removeNumOfMinerals(50);
			clock.removeNumOfGas(50);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Research Warp Gate Started 	");
			printTime(); 
			canConstUnit = false;
			hadWarpGateResearch = true; 
    		numOfBuild[14]++;
    		try {
				Thread.sleep((long) (accelerator*16000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			return true;
		}return false;
    }
    
    // Upgrades
    public boolean upgGroundWeapons1(){
    	if (clock.getNumOfMinerals() >= 100 && clock.getNumOfMinerals() >= 100 && this.hadForge == true)
		{ 
    		
			clock.removeNumOfMinerals(100);
			clock.removeNumOfGas(100);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Ground Weapons 1	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[34]++; 
			try {
				Thread.sleep((long) (accelerator*16000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadGWeapon1 = true;
                
			return true;
		}return false;
    }
    public boolean upgGroundWeapons2(){
    	if (clock.getNumOfMinerals() >= 150 && clock.getNumOfMinerals() >= 150 && this.hadForge == true && 
    			this.hadGWeapon1 == true && this.hadTwilightCouncil == true )
		{ 
    		
			clock.removeNumOfMinerals(150);
			clock.removeNumOfGas(150);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Ground Weapons 2 	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[35]++; 
			try {
				Thread.sleep((long) (accelerator*16000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadGWeapon2 = true;
                
			return true;
		}return false;
    }
    public boolean upgGroundWeapons3(){
    	if (clock.getNumOfMinerals() >= 200 && clock.getNumOfMinerals() >= 200 && this.hadForge == true && 
    			this.hadGWeapon2 == true && this.hadTwilightCouncil == true )
		{ 
    		
			clock.removeNumOfMinerals(200);
			clock.removeNumOfGas(200);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Ground Weapons 3 	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[36]++; 
			try {
				Thread.sleep((long) (accelerator*22000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
                
			return true;
		}return false;
    }
    public boolean upgGroundArmor1(){
    	if (clock.getNumOfMinerals() >= 100 && clock.getNumOfMinerals() >= 100 && this.hadForge == true)
		{ 
    		
			clock.removeNumOfMinerals(100);
			clock.removeNumOfGas(100);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Ground Armor 1	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[37]++; 
			try {
				Thread.sleep((long) (accelerator*16000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadGArmor1 = true;
			return true;
		}return false;
    }
    public boolean upgGroundArmor2(){
    	if (clock.getNumOfMinerals() >= 150 && clock.getNumOfMinerals() >= 150 
    			&& this.hadForge == true && this.hadGArmor1 == true && this.hadTwilightCouncil == true)
		{ 
    		
			clock.removeNumOfMinerals(150);
			clock.removeNumOfGas(150);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Ground Armor 2	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[38]++; 
			try {
				Thread.sleep((long) (accelerator*19000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadGArmor2 = true;
			return true;
		}return false;
    }
    public boolean upgGroundArmor3(){
    	if (clock.getNumOfMinerals() >= 200 && clock.getNumOfMinerals() >= 200 
    			&& this.hadForge == true && this.hadGArmor2 == true && this.hadTwilightCouncil == true)
		{ 
    		
			clock.removeNumOfMinerals(200);
			clock.removeNumOfGas(200);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Ground Armor 3	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[39]++; 
			try {
				Thread.sleep((long) (accelerator*22000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    }
    public boolean upgShields1(){
    	if (clock.getNumOfMinerals() >= 150 && clock.getNumOfMinerals() >= 150 
    			&& this.hadForge == true)
		{ 
    		
			clock.removeNumOfMinerals(200);
			clock.removeNumOfGas(200);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Shields 1		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[40]++; 
			try {
				Thread.sleep((long) (accelerator*16000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadShield1 = true;
			return true;
		}return false;
    } 
    public boolean upgShields2(){
    	if (clock.getNumOfMinerals() >= 225 && clock.getNumOfMinerals() >= 225 
    			&& this.hadForge == true && this.hadShield1 == true && this.hadTwilightCouncil == true)
		{ 
    		
			clock.removeNumOfMinerals(225);
			clock.removeNumOfGas(225);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Shields 2		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[41]++; 
			try {
				Thread.sleep((long) (accelerator*19000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadShield2 = true;
			return true;
		}return false;
    } 
    public boolean upgShields3(){
    	if (clock.getNumOfMinerals() >= 300 && clock.getNumOfMinerals() >= 300
    			&& this.hadForge == true && this.hadShield2 == true && this.hadTwilightCouncil == true)
		{ 
    		
			clock.removeNumOfMinerals(300);
			clock.removeNumOfGas(300);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Shields 3		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[42]++; 
			try {
				Thread.sleep((long) (accelerator*22000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    } 
    public boolean upgAirWeapons1(){
    	if (clock.getNumOfMinerals() >= 100 && clock.getNumOfMinerals() >= 100
    			&& this.hadCyberneticsCore == true)
		{ 
    		
			clock.removeNumOfMinerals(100);
			clock.removeNumOfGas(100);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Air Weapons 1		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[43]++; 
			try {
				Thread.sleep((long) (accelerator*16000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadAirWeapon1 = true;
			return true;
		}return false;
    } 
    public boolean upgAirWeapons2(){
    	if (clock.getNumOfMinerals() >= 175 && clock.getNumOfMinerals() >= 175
    			&& this.hadCyberneticsCore == true && this.hadFleetBeacon == true 
    			&& this.hadForge == true && this.hadAirWeapon1 == true)
		{ 
    		
			clock.removeNumOfMinerals(175);
			clock.removeNumOfGas(175);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Air Weapons 2 		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[44]++; 
			try {
				Thread.sleep((long) (accelerator*19000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadAirWeapon2 = true;
			return true;
		}return false;
    } 
    public boolean upgAirWeapons3(){
    	if (clock.getNumOfMinerals() >= 250 && clock.getNumOfMinerals() >= 250
    			&& this.hadCyberneticsCore == true && this.hadFleetBeacon == true 
    			&& this.hadForge == true && this.hadAirWeapon2 == true)
		{ 
    		
			clock.removeNumOfMinerals(250);
			clock.removeNumOfGas(250);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Air Weapons 	3	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[45]++; 
			try {
				Thread.sleep((long) (accelerator*22000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    } 
    public boolean upgAirArmor1(){
    	if (clock.getNumOfMinerals() >= 150 && clock.getNumOfMinerals() >= 150
    			&& this.hadCyberneticsCore == true)
		{ 
    		
			clock.removeNumOfMinerals(150);
			clock.removeNumOfGas(150);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Air Armor 1 		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[46]++; 
			try {
				Thread.sleep((long) (accelerator*16000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadAirArmor1 = true;
			return true;
		}return false;
    } 
    public boolean upgAirArmor2(){
    	if (clock.getNumOfMinerals() >= 225 && clock.getNumOfMinerals() >= 225
    			&& this.hadCyberneticsCore == true && this.hadFleetBeacon == true 
    			&& this.hadForge == true && this.hadAirArmor1 == true)
		{ 
    		
			clock.removeNumOfMinerals(250);
			clock.removeNumOfGas(250);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Air Armor 2		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[47]++; 
			try {
				Thread.sleep((long) (accelerator*19000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
            		hadAirArmor2 = true;
			return true;
		}return false;
    } 
    public boolean upgAirArmor3(){
    	if (clock.getNumOfMinerals() >= 300 && clock.getNumOfMinerals() >= 300
    			&& this.hadCyberneticsCore == true && this.hadFleetBeacon == true 
    			&& this.hadForge == true && this.hadAirArmor2 == true)
		{ 
    		
			clock.removeNumOfMinerals(300);
			clock.removeNumOfGas(300);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Air Armor 3		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[48]++; 
			try {
				Thread.sleep((long) (accelerator*19000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    } 
    public boolean upgCharge(){
    	if (clock.getNumOfMinerals() >= 200 && clock.getNumOfMinerals() >= 200
    			&& this.hadTwilightCouncil == true)
		{ 
    		
			clock.removeNumOfMinerals(200);
			clock.removeNumOfGas(200);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Charge		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[49]++; 
			try {
				Thread.sleep((long) (accelerator*14000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    } 
    public boolean upgGrBoosters(){
    	if (clock.getNumOfMinerals() >= 100 && clock.getNumOfMinerals() >= 100
    			&& this.hadRoboticsBay == true)
		{ 
    		
			clock.removeNumOfMinerals(100);
			clock.removeNumOfGas(100);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Gravitic Boosters	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[50]++; 
			try {
				Thread.sleep((long) (accelerator*8000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    } 
    public boolean upgGrDrive(){
    	if (clock.getNumOfMinerals() >= 100 && clock.getNumOfMinerals() >= 100
    			&& this.hadRoboticsBay == true)
		{ 
    		
			clock.removeNumOfMinerals(100);
			clock.removeNumOfGas(100);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Gravitic Drive		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[51]++; 
			try {
				Thread.sleep((long) (accelerator*8000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    } 
    public boolean upgPulseCrystal(){
    	if (clock.getNumOfMinerals() >= 150 && clock.getNumOfMinerals() >= 150
    			&& this.hadFleetBeacon== true)
		{ 
    		
			clock.removeNumOfMinerals(150);
			clock.removeNumOfGas(150);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Pulse Crystal		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[52]++; 
			try {
				Thread.sleep((long) (accelerator*9000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    }    
    public boolean upgThermalLance(){
    	if (clock.getNumOfMinerals() >= 200 && clock.getNumOfMinerals() >= 200
    			&& this.hadRoboticsBay == true)
		{ 
    		
			clock.removeNumOfMinerals(200);
			clock.removeNumOfGas(200);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Extended Thermal Lance	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[53]++; 
			try {
				Thread.sleep((long) (accelerator*14000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    }
    public boolean upgPsionicStorm(){
    	if (clock.getNumOfMinerals() >= 200 && clock.getNumOfMinerals() >= 200
    			&& this.hadTemplarArchives == true)
		{ 
    		
			clock.removeNumOfMinerals(200);
			clock.removeNumOfGas(200);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Psionic Storm		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[54]++; 
			try {
				Thread.sleep((long) (accelerator*11000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    }
    public boolean upgBlink(){
    	if (clock.getNumOfMinerals() >= 150 && clock.getNumOfMinerals() >= 150
    			&& this.hadTwilightCouncil == true)
		{ 
    		
			clock.removeNumOfMinerals(150);
			clock.removeNumOfGas(150);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Blink		");
			printTime(); 
			canConstUnit = false;
			numOfBuild[55]++; 
			try {
				Thread.sleep((long) (accelerator*17000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    }
    public boolean upgGravitonCatapult(){
    	if (clock.getNumOfMinerals() >= 150 && clock.getNumOfMinerals() >= 150
    			&& this.hadFleetBeacon == true)
		{ 
    		
			clock.removeNumOfMinerals(150);
			clock.removeNumOfGas(150);
			System.out.print("	"+numOfSupply+"	");
			System.out.print("Graviton Catapult	");
			printTime(); 
			canConstUnit = false;
			numOfBuild[56]++; 
			try {
				Thread.sleep((long) (accelerator*8000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            		canConstUnit = true;
			return true;
		}return false;
    }

    // Getter
    public int[] getNumOfBuild(){
    	return numOfBuild;
    }
    public int getNumOfSupply(){
    	return numOfSupply;
    }
    public int getCounter()
    {
    	return clock.getCounter();
    }
    public boolean isWarpGateResearch(){
    	return this.hadWarpGateResearch;
    }
}
