package Starcraft;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * The main class that appears when the application launches.
 * It is user-friendly frame that can give advantage to user create their own task 
 */
public class TaskSetter extends JFrame{

	private static final long serialVersionUID = 1L;
	private int[] buildNames = new int[59];
	private JTextArea[] textAreaInputBuild = new JTextArea[15];
	private JTextArea[] textAreaInputUnit = new JTextArea[19];
	private JRadioButton[] buildButtons = new JRadioButton[15];
	private JRadioButton[] unitButtons = new JRadioButton[19];
	private JRadioButton[] upgradeButtons = new JRadioButton[23];
	private JButton startGame; 
	private JLabel background;
	private JLabel buildings;
	private JLabel units;
	private JLabel upgrades;
	private JPanel radioButtonsBuilding;
	private JPanel radioButtonsUnits;
	private JPanel radioButtonsUpgrades;
	private JPanel textAreaBuilding;
	private JPanel textAreaUnits;
	private JPanel textTop;
	private JPanel mainPanel;
	private JPanel gameStarter;
	private Dimension dim; 
    
	public TaskSetter()
	{
		// Create objects
		this.startGame = new JButton();
		this.radioButtonsBuilding = new JPanel();
		this.radioButtonsUnits = new JPanel();
		this.textTop = new JPanel();
		this.gameStarter = new JPanel();
		this.textAreaBuilding = new JPanel();
		this.textAreaUnits = new JPanel();
		this.mainPanel = new JPanel();
		this.radioButtonsUpgrades = new JPanel();
		this.background = new JLabel();
		this.buildings = new JLabel();
		this.units = new JLabel();
		this.upgrades = new JLabel();
		
		background.add(textTop);
		
		this.setAppearance();
		this.setJPanel();
		this.setTopPanel();
		this.setBuildRadioButtons();
		this.setTextInputBuild();
		
		this.background.add(mainPanel);
		this.mainPanel.add(Box.createHorizontalStrut(50));
		this.mainPanel.add(textAreaBuilding);
		this.mainPanel.add(Box.createHorizontalStrut(180));
		this.mainPanel.add(radioButtonsUpgrades);
		this.mainPanel.add(Box.createHorizontalStrut(180));
		
		this.setUnitRadioButtons();
		this.setTextInputUnit();
		this.setUpgradeRadioButtons();
		this.setInputUpgrade();
		this.setButton();
		
		// Update the window
		this.setSize(1299,949);
    	this.setSize(1300,950);
    	this.setResizable(false);
		this.setVisible(true);
	}
	public void setAppearance()
	{
		this.setTitle("Starcraft");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1300, 950);
		this.setLayout(new BorderLayout());
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);	    
		this.add(background);
	}
	public void setTopPanel()
	{
		// set data to JLabel and add to JPanel
		buildings.setText("Buildings");
		units.setText("Upgrades");
		upgrades.setText("Units");
		buildings.setHorizontalAlignment(SwingConstants.CENTER);
		units.setHorizontalAlignment(SwingConstants.CENTER);
		upgrades.setHorizontalAlignment(SwingConstants.CENTER);
		buildings.setPreferredSize(new Dimension(400, 50));
		units.setPreferredSize(new Dimension(400, 50));
		upgrades.setPreferredSize(new Dimension(400, 50));
		buildings.setFont (buildings.getFont ().deriveFont (40.0f));
		units.setFont (units.getFont ().deriveFont (40.0f));
		upgrades.setFont (units.getFont ().deriveFont (40.0f));
		textTop.add(buildings);
		textTop.add(units);
		textTop.add(upgrades);
	}
	public void setJPanel()
	{
		// set Layout to JPanels
		this.background.setLayout(new FlowLayout());
		this.radioButtonsBuilding.setLayout(new BoxLayout(radioButtonsBuilding, BoxLayout.PAGE_AXIS));
		this.radioButtonsUnits.setLayout(new BoxLayout(radioButtonsUnits, BoxLayout.PAGE_AXIS));
		this.radioButtonsUpgrades.setLayout(new BoxLayout(radioButtonsUpgrades, BoxLayout.PAGE_AXIS));
		this.textAreaBuilding.setLayout(new BoxLayout(textAreaBuilding, BoxLayout.PAGE_AXIS));
		this.textAreaUnits.setLayout(new BoxLayout(textAreaUnits, BoxLayout.PAGE_AXIS));
		this.textTop.setLayout(new FlowLayout());
		
		// Make JPanels visible and add to frame
		this.radioButtonsBuilding.setVisible(true);
		this.radioButtonsUnits.setVisible(true);
		this.radioButtonsUpgrades.setVisible(true);
		this.textAreaBuilding.setVisible(true);
		this.textAreaUnits.setVisible(true);
		this.textTop.setVisible(true);
		this.gameStarter.setVisible(true);
	}
	public void setBuildRadioButtons()
	{
		for(int i = 0; i < 15; i++)
		{
			buildButtons[i] = new JRadioButton();
			radioButtonsBuilding.add(buildButtons[i]);
			buildButtons[i].setFont(new Font(("SansSerif"), Font.PLAIN,15));
		}
		buildButtons[0].setText("Nexus");
		buildButtons[1].setText("Pylon ");
		buildButtons[2].setText("Assimilator");
		buildButtons[3].setText("Gateway");
		buildButtons[4].setText("Cybernetics Core");
		buildButtons[5].setText("Robotics Facility");
		buildButtons[6].setText("Stargate");
		buildButtons[7].setText("Forge");
		buildButtons[8].setText("Twilight Council");
		buildButtons[9].setText("Templar Archives");
		buildButtons[10].setText("Dark Shrine");
		buildButtons[11].setText("Robotics Bay");
		buildButtons[12].setText("Fleet Beacon");
		buildButtons[13].setText("Photon Cannon");
		buildButtons[14].setText("Warp Gate");
		mainPanel.add(radioButtonsBuilding);
	}
	public void setUnitRadioButtons()
	{
		for(int i = 0; i < 19; i++)
		{
			unitButtons[i] = new JRadioButton();
			radioButtonsUnits.add(unitButtons[i]);
			unitButtons[i].setFont(new Font(("SansSerif"), Font.PLAIN,15));
		}
		unitButtons[0].setText("Probe");
		unitButtons[1].setText("Zealot ");
		unitButtons[2].setText("Stalker");
		unitButtons[3].setText("Sentry");
		unitButtons[4].setText("Observer");
		unitButtons[5].setText("Immortal");
		unitButtons[6].setText("Phoenix");
		unitButtons[7].setText("Void Ray");
		unitButtons[8].setText("Oracle");
		unitButtons[9].setText("Warp Prism");
		unitButtons[10].setText("Colossus");
		unitButtons[11].setText("Tempest ");
		unitButtons[12].setText("High Templar");
		unitButtons[13].setText("Dark Templar");
		unitButtons[14].setText("Archon ");
		unitButtons[15].setText("Carrier ");
		unitButtons[16].setText("Interceptor ");
		unitButtons[17].setText("Mothership Core ");
		unitButtons[18].setText("Mothership ");
		mainPanel.add(radioButtonsUnits);
	}
	public void setUpgradeRadioButtons()
	{
		for(int i = 0; i < 23; i++)
		{
			upgradeButtons[i] = new JRadioButton();
			radioButtonsUpgrades.add(upgradeButtons[i]);
			upgradeButtons[i].setFont(new Font(("SansSerif"), Font.PLAIN,15));
		}
		upgradeButtons[0].setText("Ground Weapon 1");
		upgradeButtons[1].setText("Ground Weapon 2");
		upgradeButtons[2].setText("Ground Weapon 3");
		upgradeButtons[3].setText("Ground Armor 1");
		upgradeButtons[4].setText("Ground Armor 2");
		upgradeButtons[5].setText("Ground Armor 3");
		upgradeButtons[6].setText("Shields 1");
		upgradeButtons[7].setText("Shields 2");
		upgradeButtons[8].setText("Shields 3");
		upgradeButtons[9].setText("Air Weapons 1");
		upgradeButtons[10].setText("Air Weapons 2");
		upgradeButtons[11].setText("Air Weapons 3");
		upgradeButtons[12].setText("Air Armor 1");
		upgradeButtons[13].setText("Air Armor 2");
		upgradeButtons[14].setText("Air Armor 3");
		upgradeButtons[15].setText("Charge");
		upgradeButtons[16].setText("Gravitic Boosters");
		upgradeButtons[17].setText("Gravitic Drive");
		upgradeButtons[18].setText("Anion Pulse-Crystal");
		upgradeButtons[19].setText("Extended Thermal Lance");
		upgradeButtons[20].setText("Psionic Storm");
		upgradeButtons[21].setText("Blink");
		upgradeButtons[22].setText("Graviton Catapult");
	}
	public void setTextInputBuild()
	{
		// It will appear if radio button is selected
		for (int i = 0; i < 14; i++)
		{
			this.textAreaInputBuild[i] = new JTextArea();
			this.textAreaInputBuild[i].setPreferredSize(new Dimension(20,20));
			//System.out.println(this.textAreaInputBuild[i].getText());
			textAreaBuilding.add(textAreaInputBuild[i]);
			textAreaBuilding.add(Box.createVerticalStrut(3));
			textAreaInputBuild[i].setBorder(BorderFactory.createLineBorder(Color.black));
			textAreaInputBuild[i].setVisible(true);
			textAreaInputBuild[i].setEditable(false);
		}
		
		// ActionListener to radio buttons
		buildButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[0].isSelected() == true)
        		{
            		textAreaInputBuild[0].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[0].setEditable(false);
            	}
            }
        });
		buildButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[1].isSelected() == true)
        		{
            		textAreaInputBuild[1].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[1].setEditable(false);
            	}
            }
        });
		buildButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[2].isSelected() == true)
        		{
            		textAreaInputBuild[2].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[2].setEditable(false);
            	}
            }
        });
		buildButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[3].isSelected() == true)
        		{
            		textAreaInputBuild[3].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[3].setEditable(false);
            	}
            }
        });
		buildButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[4].isSelected() == true)
        		{
            		textAreaInputBuild[4].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[4].setEditable(false);
            	}
            }
        });
		buildButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[5].isSelected() == true)
        		{
            		textAreaInputBuild[5].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[5].setEditable(false);
            	}
            }
        });
		buildButtons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[6].isSelected() == true)
        		{
            		textAreaInputBuild[6].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[6].setEditable(false);
            	}
            }
        });
		buildButtons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[7].isSelected() == true)
        		{
            		textAreaInputBuild[7].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[7].setEditable(false);
            	}
            }
        });
		buildButtons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[8].isSelected() == true)
        		{
            		textAreaInputBuild[8].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[8].setEditable(false);
            	}
            }
        });
		buildButtons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[9].isSelected() == true)
        		{
            		textAreaInputBuild[9].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[9].setEditable(false);
            	}
            }
        });
		buildButtons[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[10].isSelected() == true)
        		{
            		textAreaInputBuild[10].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[10].setEditable(false);
            	}
            }
        });
		buildButtons[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[11].isSelected() == true)
        		{
            		textAreaInputBuild[11].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[11].setEditable(false);
            	}
            }
        });
		buildButtons[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[12].isSelected() == true)
        		{
            		textAreaInputBuild[12].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[12].setEditable(false);
            	}
            }
        });
		buildButtons[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[13].isSelected() == true)
        		{
            		textAreaInputBuild[13].setEditable(true);
        		}
            	else 
            	{
            		textAreaInputBuild[13].setEditable(false);
            	}
            }
        });
		buildButtons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (buildButtons[14].isSelected() == true)
        		{
            		buildNames[14] = 1;
        		}
            	else 
            	{
            		buildNames[14] = 0;
            	}
            }
        });
	}
	public void setInputUpgrade()
	{
		// ActionListener to radio buttons
		upgradeButtons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[0].isSelected() == true)
        		{
            		buildNames[34] = 1;
        		}
            	else 
            	{
            		buildNames[34] = 0;
            	}
            }
        });
		upgradeButtons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[1].isSelected() == true)
        		{
            		buildNames[35] = 1;
        		}
            	else 
            	{
            		buildNames[35] = 0;
            	}
            }
        });
		upgradeButtons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[2].isSelected() == true)
        		{
            		buildNames[36] = 1;
        		}
            	else 
            	{
            		buildNames[36] = 0;
            	}
            }
        });
		upgradeButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[3].isSelected() == true)
        		{
            		buildNames[37] = 1;
        		}
            	else 
            	{
            		buildNames[37] = 0;
            	}
            }
        });
		upgradeButtons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[4].isSelected() == true)
        		{
            		buildNames[38] = 1;
        		}
            	else 
            	{
            		buildNames[38] = 0;
            	}
            }
        });
		upgradeButtons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[5].isSelected() == true)
        		{
            		buildNames[39] = 1;
        		}
            	else 
            	{
            		buildNames[39] = 0;
            	}
            }
        });
		upgradeButtons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[6].isSelected() == true)
        		{
            		buildNames[40] = 1;
        		}
            	else 
            	{
            		buildNames[40] = 0;
            	}
            }
        });
		upgradeButtons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[7].isSelected() == true)
        		{
            		buildNames[41] = 1;
        		}
            	else 
            	{
            		buildNames[41] = 0;
            	}
            }
        });
		upgradeButtons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[8].isSelected() == true)
        		{
            		buildNames[42] = 1;
        		}
            	else 
            	{
            		buildNames[42] = 0;
            	}
            }
        });
		upgradeButtons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[9].isSelected() == true)
        		{
            		buildNames[43] = 1;
        		}
            	else 
            	{
            		buildNames[43] = 0;
            	}
            }
        });
		upgradeButtons[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[10].isSelected() == true)
        		{
            		buildNames[44] = 1;
        		}
            	else 
            	{
            		buildNames[44] = 0;
            	}
            }
        });
		upgradeButtons[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[11].isSelected() == true)
        		{
            		buildNames[45] = 1;
        		}
            	else 
            	{
            		buildNames[45] = 0;
            	}
            }
        });
		upgradeButtons[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[12].isSelected() == true)
        		{
            		buildNames[46] = 1;
        		}
            	else 
            	{
            		buildNames[46] = 0;
            	}
            }
        });
		upgradeButtons[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[13].isSelected() == true)
        		{
            		buildNames[47] = 1;
        		}
            	else 
            	{
            		buildNames[47] = 0;
            	}
            }
        });
		upgradeButtons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[14].isSelected() == true)
        		{
            		buildNames[48] = 1;
        		}
            	else 
            	{
            		buildNames[48] = 0;
            	}
            }
        });
		upgradeButtons[15].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[15].isSelected() == true)
        		{
            		buildNames[49] = 1;
        		}
            	else 
            	{
            		buildNames[49] = 0;
            	}
            }
        });
		upgradeButtons[16].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[16].isSelected() == true)
        		{
            		buildNames[50] = 1;
        		}
            	else 
            	{
            		buildNames[50] = 0;
            	}
            }
        });
		upgradeButtons[17].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[17].isSelected() == true)
        		{
            		buildNames[51] = 1;
        		}
            	else 
            	{
            		buildNames[51] = 0;
            	}
            }
        });
		upgradeButtons[18].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[18].isSelected() == true)
        		{
            		buildNames[52] = 1;
        		}
            	else 
            	{
            		buildNames[52] = 0;
            	}
            }
        });
		upgradeButtons[19].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[19].isSelected() == true)
        		{
            		buildNames[53] = 1;
        		}
            	else 
            	{
            		buildNames[53] = 0;
            	}
            }
        });
		upgradeButtons[20].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[20].isSelected() == true)
        		{
            		buildNames[54] = 1;
        		}
            	else 
            	{
            		buildNames[54] = 0;
            	}
            }
        });
		upgradeButtons[21].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[21].isSelected() == true)
        		{
            		buildNames[55] = 1;
        		}
            	else 
            	{
            		buildNames[55] = 0;
            	}
            }
        });
		upgradeButtons[22].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	if (upgradeButtons[22].isSelected() == true)
        		{
            		buildNames[56] = 1;
        		}
            	else 
            	{
            		buildNames[0] = 0;
            	}
            }
        });
	}
	public void setTextInputUnit()
	{
		// It will appear if radio button is selected
		for (int i = 0; i < 19; i++)
		{
			this.textAreaInputUnit[i] = new JTextArea();
			this.textAreaInputUnit[i].setPreferredSize(new Dimension(20,20));
			this.textAreaUnits.add(textAreaInputUnit[i]);
			this.textAreaUnits.add(Box.createVerticalStrut(3));
			this.textAreaInputUnit[i].setBorder(BorderFactory.createLineBorder(Color.black));
			textAreaInputUnit[i].setVisible(true);
			textAreaInputUnit[i].setEditable(false);
			this.mainPanel.add(textAreaUnits);
		}
		// ActionListener to all radio buttons
				unitButtons[0].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[0].isSelected() == true)
		        		{
		            		textAreaInputUnit[0].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[0].setEditable(false);
		            	}
		            }
		        });
				unitButtons[1].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[1].isSelected() == true)
		        		{
		            		textAreaInputUnit[1].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[1].setEditable(false);
		            	}
		            }
		        });
				unitButtons[2].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[2].isSelected() == true)
		        		{
		            		textAreaInputUnit[2].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[2].setEditable(false);
		            	}
		            }
		        });
				unitButtons[3].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[3].isSelected() == true)
		        		{
		            		textAreaInputUnit[3].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[3].setEditable(false);
		            	}
		            }
		        });
				unitButtons[4].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[4].isSelected() == true)
		        		{
		            		textAreaInputUnit[4].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[4].setEditable(false);
		            	}
		            }
		        });
				unitButtons[5].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[5].isSelected() == true)
		        		{
		            		textAreaInputUnit[5].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[5].setEditable(false);
		            	}
		            }
		        });
				unitButtons[6].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[6].isSelected() == true)
		        		{
		            		textAreaInputUnit[6].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[6].setEditable(false);
		            	}
		            }
		        });
				unitButtons[7].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[7].isSelected() == true)
		        		{
		            		textAreaInputUnit[7].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[7].setEditable(false);
		            	}
		            }
		        });
				unitButtons[8].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[8].isSelected() == true)
		        		{
		            		textAreaInputUnit[8].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[8].setEditable(false);
		            	}
		            }
		        });
				unitButtons[9].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[9].isSelected() == true)
		        		{
		            		textAreaInputUnit[9].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[9].setEditable(false);
		            	}
		            }
		        });
				unitButtons[10].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[10].isSelected() == true)
		        		{
		            		textAreaInputUnit[10].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[10].setEditable(false);
		            	}
		            }
		        });
				unitButtons[11].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[11].isSelected() == true)
		        		{
		            		textAreaInputUnit[11].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[11].setEditable(false);
		            	}
		            }
		        });
				unitButtons[12].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[12].isSelected() == true)
		        		{
		            		textAreaInputUnit[12].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[12].setEditable(false);
		            	}
		            }
		        });
				unitButtons[13].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[13].isSelected() == true)
		        		{
		            		textAreaInputUnit[13].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[13].setEditable(false);
		            	}
		            }
		        });
				unitButtons[14].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[14].isSelected() == true)
		        		{
		            		textAreaInputUnit[14].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[14].setEditable(false);
		            	}
		            }
		        });		
				unitButtons[15].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[15].isSelected() == true)
		        		{
		            		textAreaInputUnit[15].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[15].setEditable(false);
		            	}
		            }
		        });		
				unitButtons[16].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[16].isSelected() == true)
		        		{
		            		textAreaInputUnit[16].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[16].setEditable(false);
		            	}
		            }
		        });	
				unitButtons[17].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[17].isSelected() == true)
		        		{
		            		textAreaInputUnit[17].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[17].setEditable(false);
		            	}
		            }
		        });		
				unitButtons[18].addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent ae) {
		            	if (unitButtons[18].isSelected() == true)
		        		{
		            		textAreaInputUnit[18].setEditable(true);
		        		}
		            	else 
		            	{
		            		textAreaInputUnit[18].setEditable(false);
		            	}
		            }
		        });		
	}
	public void setButton()
	{
		this.startGame.setText("Find optimal solution");
		this.startGame.setPreferredSize(new Dimension(200, 50));
		this.startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	setVisible(false);
            	dispose();
            	parseInput();
            	try {
					new Optimizer(buildNames);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });	
		this.gameStarter.add(startGame);
		this.gameStarter.setPreferredSize(new Dimension(800, 300));
		this.background.add(gameStarter);
	}
	public void parseInput(){
		for (int i = 0; i < 14; i++){
    		if (textAreaInputBuild[i].getText().equals("")){
    			buildNames[i] = 0;
    		}
    		else {
    			buildNames[i] = Integer.parseInt(textAreaInputBuild[i].getText());
    		}
    	}
    	for (int i = 0; i < 19; i++){
    		if (textAreaInputUnit[i].getText().equals("")){
    			buildNames[i+15] = 0;
    		}
    		else {
    			buildNames[i+15] = Integer.parseInt(textAreaInputUnit[i].getText());
    		}
    	}
	}
}
