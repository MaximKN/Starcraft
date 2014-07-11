package Starcraft;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintStream;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Optimizer extends JFrame{

	private static final long serialVersionUID = 1L;
	private CommandCentre commandCentre;
	private ArrayList<Integer> taskGeneration = new ArrayList<Integer>();
	private int[] taskFromUser;
	private JTextArea mainOutput;
	private Thread mainClock = new Thread(){
		public void run(){
			commandCentre = new CommandCentre();
			System.out.println("	Supply	Task		Starting time");
		}
	};
	private Thread solutionFinder = new Thread(){
		public void run(){
			findTask();
			findSolution();
		}
	};
	public Optimizer(int[] array) throws InterruptedException{
		// Set frame
		this.setTitle("Prims Optimizer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(540, 650);
		this.setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);	    
		
		// Redirect output
		this.mainOutput = new JTextArea();
		PrintStream printStream = new PrintStream(new CustomOutputStream(mainOutput));
		System.setOut(printStream);
		
		// Solution finder
		this.taskFromUser = array;
		this.taskFromUser[0]++; // Already have 1 Nexus 
		mainClock.start();
		Thread.sleep(30); // delay to start a clock
		solutionFinder.start();

		JScrollPane scroll = new JScrollPane (mainOutput, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainOutput.setLineWrap(true);
		mainOutput.setEditable(false); 
		mainOutput.setVisible(true);
		this.add(scroll);
		this.setResizable(false);
		this.setVisible(true);
	}
	public void findTask(){
		if (taskFromUser[0] > 1){
			taskGeneration.add(0);
		}
		for (int i = 1; i < taskFromUser.length; i++){
			if (taskFromUser[i] != 0){
				taskGeneration.add(i);
			}
		}
	}
	public void findSolution(){
		while (true){
			if (isTaskComplited() == false){
				Collections.shuffle(taskGeneration);
				build(taskGeneration.get(0));
			}
			else
			{
				TimeConverter t = new TimeConverter(commandCentre.getCounter());
				System.out.println("FINISHED!!!");
				System.out.print("Time required: ");
				t.printTime();
				mainClock.interrupt();
				break;
			}
		}
	}
	public void build(int index){
	/*	try {
			Thread.sleep((long) (200 + (Math.random() * 200))); // Realistic time delay
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		// Build Pylon if no supply
		if (commandCentre.getNumOfSupply() == 0 || commandCentre.getNumOfSupply() == 1){
			if (commandCentre.buildPylon() == true)
			{
				this.taskFromUser[1]++;
			}
		}
		switch (index){	
			case 0: 
				if (commandCentre.buildNexus() == true) 
				{	
					if (this.taskFromUser[0] == this.commandCentre.getNumOfBuild()[0]){
						taskGeneration.remove(0);
					}
				}break;
			case 1:
				if (commandCentre.buildPylon() == true)
				{
					if (this.taskFromUser[1] == this.commandCentre.getNumOfBuild()[1]){
						taskGeneration.remove(0);
					}
				} break;
			case 2:
				if (commandCentre.buildAssimilator() == true)
				{
					if (this.taskFromUser[2] == this.commandCentre.getNumOfBuild()[2]){
						taskGeneration.remove(0);
					}
				} break;
			case 3:
				if (commandCentre.buildGateway() == true)
				{
					if (this.taskFromUser[3] == this.commandCentre.getNumOfBuild()[3]){
						taskGeneration.remove(0);
					}
				} break;
			case 4:
				if (commandCentre.buildCyberneticsCore() == true)
				{
					if (this.taskFromUser[4] == this.commandCentre.getNumOfBuild()[4]){
						taskGeneration.remove(0);
					}
				} break;
			case 5:
				if (commandCentre.buildRoboticsFacility() == true)
				{
					if (this.taskFromUser[5] == this.commandCentre.getNumOfBuild()[5]){
						taskGeneration.remove(0);
					}
				} break;
			case 6:
				if (commandCentre.buildStargate() == true)
				{
					if (this.taskFromUser[6] == this.commandCentre.getNumOfBuild()[6]){
						taskGeneration.remove(0);
					}
				} break;
			case 7:
				if (commandCentre.buildForge() == true)
				{
					if (this.taskFromUser[7] == this.commandCentre.getNumOfBuild()[7]){
						taskGeneration.remove(0);
					}
				} break;
			case 8:
				if (commandCentre.buildTwilightCouncil() == true)
				{
					if (this.taskFromUser[8] == this.commandCentre.getNumOfBuild()[8]){
						taskGeneration.remove(0);
					}
				} break;
			case 9:
				if (commandCentre.buildTemplarArchives() == true)
				{
					if (this.taskFromUser[9] == this.commandCentre.getNumOfBuild()[9]){
						taskGeneration.remove(0);
					}
				} break;
			case 10:
				if (commandCentre.buildDarkShrine() == true)
				{
					if (this.taskFromUser[10] == this.commandCentre.getNumOfBuild()[10]){
						taskGeneration.remove(0);
					}
				} break;
			case 11:
				if (commandCentre.buildRoboticsBay() == true)
				{
					if (this.taskFromUser[11] == this.commandCentre.getNumOfBuild()[11]){
						taskGeneration.remove(0);
					}
				} break;
			case 12:
				if (commandCentre.buildFleetBeacon() == true)
				{
					if (this.taskFromUser[12] == this.commandCentre.getNumOfBuild()[12]){
						taskGeneration.remove(0);
					}
				} break;
			case 13:
				if (commandCentre.buildPhotonCannon() == true)
				{
					if (this.taskFromUser[13] == this.commandCentre.getNumOfBuild()[13]){
						taskGeneration.remove(0);
					}
				} break;
			case 14:
				// Always try to start research warp gate
				if (commandCentre.researchWarpGate() == true){
					if (this.taskFromUser[14] == this.commandCentre.getNumOfBuild()[14]){
						taskGeneration.remove(0);
					}
				}break;
				
			case 15:
				if (commandCentre.createProbe() == true)
				{
					if (this.taskFromUser[15] == this.commandCentre.getNumOfBuild()[15]){
						taskGeneration.remove(0);
					}
				} break;
			case 16:
				if (commandCentre.createZealot() == true)
				{
					if (this.taskFromUser[16] == this.commandCentre.getNumOfBuild()[16]){
						taskGeneration.remove(0);
					}
				} break;
			case 17:
				if (commandCentre.createStalker() == true)
				{
					if (this.taskFromUser[17] == this.commandCentre.getNumOfBuild()[17]){
						taskGeneration.remove(0);
					}
				} break;
			case 18:
				if (commandCentre.createSentry() == true)
				{
					if (this.taskFromUser[18] == this.commandCentre.getNumOfBuild()[18]){
						taskGeneration.remove(0);
					}
				} break;
			case 19:
				if (commandCentre.createObserver() == true)
				{
					if (this.taskFromUser[19] == this.commandCentre.getNumOfBuild()[19]){
						taskGeneration.remove(0);
					}
				} break;
			case 20:
				if (commandCentre.getNumOfSupply() == 3 || commandCentre.getNumOfSupply() == 2){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createImmortal() == true)
				{
					if (this.taskFromUser[20] == this.commandCentre.getNumOfBuild()[20]){
						taskGeneration.remove(0);
					}
				} break;
			case 21:
				if (commandCentre.createPhoenix() == true)
				{
					if (this.taskFromUser[21] == this.commandCentre.getNumOfBuild()[21]){
						taskGeneration.remove(0);
					}
				} break;
			case 22:
				if (commandCentre.getNumOfSupply() == 2){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createVoidRay() == true)
				{
					if (this.taskFromUser[22] == this.commandCentre.getNumOfBuild()[22]){
						taskGeneration.remove(0);
					}
				} break;
			case 23:
				if (commandCentre.getNumOfSupply() == 2){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createOracle() == true)
				{
					if (this.taskFromUser[23] == this.commandCentre.getNumOfBuild()[23]){
						taskGeneration.remove(0);
					}
				} break;
			case 24:
				if (commandCentre.createWarpPrism() == true)
				{
					if (this.taskFromUser[24] == this.commandCentre.getNumOfBuild()[24]){
						taskGeneration.remove(0);
					}
				} break;
			case 25:
				if (commandCentre.getNumOfSupply() <= 5){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createColossus() == true)
				{
					if (this.taskFromUser[25] == this.commandCentre.getNumOfBuild()[25]){
						taskGeneration.remove(0);
					}
				} break;
			case 26:
				if (commandCentre.getNumOfSupply() <= 4){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createTempest() == true)
				{
					if (this.taskFromUser[26] == this.commandCentre.getNumOfBuild()[26]){
						taskGeneration.remove(0);
					}
				} break;
			case 27:
				if (commandCentre.createHighTemplar() == true)
				{
					if (this.taskFromUser[27] == this.commandCentre.getNumOfBuild()[27]){
						taskGeneration.remove(0);
					}
				} break;
			case 28:
				if (commandCentre.createDarkTemplar() == true)
				{
					if (this.taskFromUser[28] == this.commandCentre.getNumOfBuild()[28]){
						taskGeneration.remove(0);
					}
				} break;
			case 29:
				if (commandCentre.getNumOfSupply() <= 4){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createArchon() == true)
				{
					if (this.taskFromUser[29] == this.commandCentre.getNumOfBuild()[29]){
						taskGeneration.remove(0);
					}
				} break;
			case 30:
				if (commandCentre.getNumOfSupply() <= 5){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createCarrier() == true)
				{
					if (this.taskFromUser[30] == this.commandCentre.getNumOfBuild()[30]){
						taskGeneration.remove(0);
					}
				} break;
			case 31:
				if (commandCentre.getNumOfSupply() <= 6){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createInterceptor() == true)
				{
					if (this.taskFromUser[31] == this.commandCentre.getNumOfBuild()[31]){
						taskGeneration.remove(0);
					}
				} break;
			case 32:
				if (commandCentre.createMothershipCore() == true)
				{
					if (this.taskFromUser[32] == this.commandCentre.getNumOfBuild()[32]){
						taskGeneration.remove(0);
					}
				} break;
			case 33:
				if (commandCentre.getNumOfSupply() <= 8){
					if (commandCentre.buildPylon() == true)
					{
						this.taskFromUser[1]++;
					}
				}
				if (commandCentre.createMothership() == true)
				{
					if (this.taskFromUser[33] == this.commandCentre.getNumOfBuild()[33]){
						taskGeneration.remove(0);
					}
				} break;
			case 34:
				if (commandCentre.upgGroundWeapons1() == true)
				{
					if (this.taskFromUser[34] == this.commandCentre.getNumOfBuild()[34]){
						taskGeneration.remove(0);
					}
				} break;
			case 35:
				if (commandCentre.upgGroundWeapons2() == true)
				{
					if (this.taskFromUser[35] == this.commandCentre.getNumOfBuild()[35]){
						taskGeneration.remove(0);
					}
				} break;
			case 36:
				if (commandCentre.upgGroundWeapons3() == true)
				{
					if (this.taskFromUser[36] == this.commandCentre.getNumOfBuild()[36]){
						taskGeneration.remove(0);
					}
				} break;
			case 37:
				if (commandCentre.upgGroundArmor1() == true)
				{
					if (this.taskFromUser[37] == this.commandCentre.getNumOfBuild()[37]){
						taskGeneration.remove(0);
					}
				} break;
			case 38:
				if (commandCentre.upgGroundArmor2() == true)
				{
					if (this.taskFromUser[38] == this.commandCentre.getNumOfBuild()[38]){
						taskGeneration.remove(0);
					}
				} break;
			case 39:
				if (commandCentre.upgGroundArmor3() == true)
				{
					if (this.taskFromUser[39] == this.commandCentre.getNumOfBuild()[39]){
						taskGeneration.remove(0);
					}
				} break;
			case 40:
				if (commandCentre.upgShields1() == true)
				{
					if (this.taskFromUser[40] == this.commandCentre.getNumOfBuild()[40]){
						taskGeneration.remove(0);
					}
				} break;
			case 41:
				if (commandCentre.upgShields2() == true)
				{
					if (this.taskFromUser[41] == this.commandCentre.getNumOfBuild()[41]){
						taskGeneration.remove(0);
					}
				} break;
			case 42:
				if (commandCentre.upgShields3() == true)
				{
					if (this.taskFromUser[42] == this.commandCentre.getNumOfBuild()[42]){
						taskGeneration.remove(0);
					}
				} break;
			case 43:
				if (commandCentre.upgAirWeapons1() == true)
				{
					if (this.taskFromUser[43] == this.commandCentre.getNumOfBuild()[43]){
						taskGeneration.remove(0);
					}
				} break;
			case 44:
				if (commandCentre.upgAirWeapons2() == true)
				{
					if (this.taskFromUser[44] == this.commandCentre.getNumOfBuild()[44]){
						taskGeneration.remove(0);
					}
				} break;
			case 45:
				if (commandCentre.upgAirWeapons3() == true)
				{
					if (this.taskFromUser[45] == this.commandCentre.getNumOfBuild()[45]){
						taskGeneration.remove(0);
					}
				} break;
			case 46:
				if (commandCentre.upgAirArmor1() == true)
				{
					if (this.taskFromUser[46] == this.commandCentre.getNumOfBuild()[46]){
						taskGeneration.remove(0);
					}
				} break;
			case 47:
				if (commandCentre.upgAirArmor2() == true)
				{
					if (this.taskFromUser[47] == this.commandCentre.getNumOfBuild()[47]){
						taskGeneration.remove(0);
					}
				} break;
			case 48:
				if (commandCentre.upgAirArmor3() == true)
				{
					if (this.taskFromUser[48] == this.commandCentre.getNumOfBuild()[48]){
						taskGeneration.remove(0);
					}
				} break;
			case 49:
				if (commandCentre.upgCharge() == true)
				{
					if (this.taskFromUser[49] == this.commandCentre.getNumOfBuild()[49]){
						taskGeneration.remove(0);
					}
				} break;
			case 50:
				if (commandCentre.upgGrBoosters() == true)
				{
					if (this.taskFromUser[50] == this.commandCentre.getNumOfBuild()[50]){
						taskGeneration.remove(0);
					}
				} break;
			case 51:
				if (commandCentre.upgGrDrive() == true)
				{
					if (this.taskFromUser[51] == this.commandCentre.getNumOfBuild()[51]){
						taskGeneration.remove(0);
					}
				} break;
			case 52:
				if (commandCentre.upgPulseCrystal() == true)
				{
					if (this.taskFromUser[52] == this.commandCentre.getNumOfBuild()[52]){
						taskGeneration.remove(0);
					}
				} break;
			case 53:
				if (commandCentre.upgThermalLance() == true)
				{
					if (this.taskFromUser[53] == this.commandCentre.getNumOfBuild()[53]){
						taskGeneration.remove(0);
					}
				} break;
			case 54:
				if (commandCentre.upgPsionicStorm() == true)
				{
					if (this.taskFromUser[54] == this.commandCentre.getNumOfBuild()[54]){
						taskGeneration.remove(0);
					}
				} break;
			case 55:
				if (commandCentre.upgBlink() == true)
				{
					if (this.taskFromUser[55] == this.commandCentre.getNumOfBuild()[55]){
						taskGeneration.remove(0);
					}
				} break;
			case 56:
				if (commandCentre.upgGravitonCatapult() == true)
				{
					if (this.taskFromUser[56] == this.commandCentre.getNumOfBuild()[56]){
						taskGeneration.remove(0);
					}
				} break;
		}
	}
	public boolean isTaskComplited(){
		//System.out.println(this.commandCentre.getNumOfBuild());
		if (Arrays.equals(this.taskFromUser, this.commandCentre.getNumOfBuild()) == true){
			return true;
		}
		return false;
	}
}
