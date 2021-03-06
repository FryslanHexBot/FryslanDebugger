package FryslanDebugger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import org.hexbot.api.methods.Game;
import org.hexbot.api.methods.GameObjects;
import org.hexbot.api.methods.GroundItems;
import org.hexbot.api.methods.Inventory;
import org.hexbot.api.methods.Npcs;
import org.hexbot.api.methods.Players;
import org.hexbot.api.methods.Settings;
import org.hexbot.api.wrapper.GameObject;
import org.hexbot.api.wrapper.GroundItem;
import org.hexbot.api.wrapper.Item;
import org.hexbot.api.wrapper.Npc;
import org.hexbot.api.wrapper.Player;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.util.Date;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -784851261083480352L;
	private JPanel contentPane;
	private int NpcId;
	private int GoId;
	private int GiId;
	private String PlId;
	private int InvId;
	private JTextField txtAreaName;
	private JTextField txtPathName;
	private JTextField textField;
	public String[] SaveNPC;
	
	
	
	
	
	
	public static int[] oldSettings;
	public static int[] newSettings;

	public GUI() {
		setAlwaysOnTop(true);
		//GUI Settings
		setTitle("Fryslan Debugger");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//Tabbed Pannel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 424, 321);
		contentPane.add(tabbedPane);
		
				JPanel panel_5 = new JPanel();
				tabbedPane.addTab("Options", null, panel_5, null);
				panel_5.setLayout(null);
				
						final JCheckBox CKNPC = new JCheckBox("NPCs");
						CKNPC.setBounds(10, 29, 97, 23);
						panel_5.add(CKNPC);
						
								JLabel lblDataToLoad = new JLabel("Data To Load : ");
								lblDataToLoad.setFont(new Font("Tahoma", Font.ITALIC, 12));
								lblDataToLoad.setBounds(10, 11, 409, 14);
								panel_5.add(lblDataToLoad);
								
										final JCheckBox CKGO = new JCheckBox("GameObjects");
										CKGO.setBounds(151, 29, 97, 23);
										panel_5.add(CKGO);
										
												final JCheckBox CKGI = new JCheckBox("GroundItems");
												CKGI.setBounds(151, 55, 97, 23);
												panel_5.add(CKGI);
												
														final JCheckBox CKINV = new JCheckBox("Inventory");
														CKINV.setBounds(298, 29, 97, 23);
														panel_5.add(CKINV);
														
																final JCheckBox CKPL = new JCheckBox("Players");
																CKPL.setBounds(10, 55, 97, 23);
																panel_5.add(CKPL);
																
																		JSeparator separator_2 = new JSeparator();
																		separator_2.setBounds(0, 100, 419, 2);
																		panel_5.add(separator_2);
																		
																				JLabel lblOnlyCheckThe = new JLabel(
																						"Only Check The Necessary Data To Load For The Best Result.");
																				lblOnlyCheckThe.setBounds(13, 81, 399, 14);
																				panel_5.add(lblOnlyCheckThe);
		
		Panel panel = new Panel();
		tabbedPane.addTab("Settings Explorer", null, panel, null);
		panel.setLayout(null);
		
		final TextArea SettingsArea = new TextArea();
		SettingsArea.setBounds(141, 10, 278, 228);
		panel.add(SettingsArea);
		
		final List SettingsList = new List();
		SettingsList.setBounds(0, 10, 135, 257);
		panel.add(SettingsList);
		for(int i = 0;i < Settings.getAll().length;i++){
			long a = Settings.get(i);
		SettingsList.add("Setting "+i+ " : "+a);
		SettingsList.repaint();
		}
		
		
		JButton btnNewButton = new JButton("Reload");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try{
					Date myDate=new Date(); 
					
					
					
				if(SettingsList.getItemCount() == 0){
					for(int i = 0;i < Settings.getAll().length;i++){
						long a = Settings.get(i);
					SettingsList.add("Setting "+i+ " : "+a);
						
					}
				}else if(SettingsList.getItemCount() > 0){
				
				for(int h = 0; h < SettingsList.getItemCount();h++){
				if(SettingsList.getItem(h) != null){
					
					int i = Integer.parseInt(SettingsList.getItem(h).split(" ")[1].toString());
						long a = Settings.get(i);
						String b = String.valueOf(a);
					
					if(SettingsList.getItem(h).toString().split(" ")[3].contentEquals(b)){
							System.out.println("No Changes in Setting "+i);
						
					}else if(!SettingsList.getItem(h).toString().split(" ")[3].contentEquals(b)){
						
						
						SettingsArea.append(myDate.getHours()+ ":"+myDate.getMinutes()+ ":"+myDate.getSeconds()+"   "+SettingsList.getItem(h).toString() + "  ->  "+"Setting "+i+ " : "+a+"\n");
						SettingsList.delItem(h);
						SettingsList.add("Setting "+i+ " : "+a, h);
						
					}
					
				}
				}
				}
				
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(284, 244, 135, 23);
		panel.add(btnNewButton);
		

		JButton button_2 = new JButton("Clear Log");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingsArea.setText("");
			}
		});
		button_2.setBounds(141, 244, 135, 23);
		panel.add(button_2);
		
		//NPC Pannel
		Panel NpcPannel = new Panel();
		tabbedPane.addTab("NPCs", null, NpcPannel, null);
		NpcPannel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(47, 334, 175, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		final TextArea NPCTA = new TextArea();
		NPCTA.setBounds(172, 10, 247, 246);
		NpcPannel.add(NPCTA);

		final List NPCList = new List();
		NPCList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selnpc = NPCList.getSelectedItem().toString()
						.split(" ");
				NpcId = Integer.parseInt(selnpc[0]);
				Npc a = Npcs.get(NpcId);
				if (a != null && NpcId > 0) {
					
					FryslanDebuggerMain.TargetID = a.getLocation();
					NPCTA.setText("Name : " + a.getName() + "\n" + "ID : "
							+ a.getId() + "\n" + "Animation : "
							+ a.getAnimation() + "\n"
							+ "HP : "
							+ a.getHp()
							+ "\n"
							+ "Max HP : "
							+ a.getMaxHp()
							+ "\n"
							+
							 "HP Percent : "+a.getHpPercent()+"\n"+
							"Combat Level : " + a.getLevel() + "\n"
							+ "Is onScreen : " + a.isOnScreen() + "\n"
							+ "Location : " + a.getLocation() + "\n"
							+ "Distance To : "
							+ a.getDistanceTo(Players.getLocal().getLocation())
							+ "\n" + "Text Spoken : " + a.getText() + "\n"
							+ "Is Moving : " + a.isMoving() + "\n");
				} else {
					NPCTA.setText(" ");
				}

			}
		});
		NPCList.setBounds(10, 10, 156, 246);
		NpcPannel.add(NPCList);
		
		
		//GameObject Pannel

		Panel GoPannel = new Panel();
		tabbedPane.addTab("GameObjects", null, GoPannel, null);
		GoPannel.setLayout(null);

		final TextArea GoTa = new TextArea();
		GoTa.setBounds(172, 10, 247, 246);
		GoPannel.add(GoTa);

		final List GOList = new List();
		GOList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selgo = GOList.getSelectedItem().toString().split(" ");
				GoId = Integer.parseInt(selgo[0]);
				GameObject a = GameObjects.getNearest(GoId);
				if (a != null && GoId > 0) {

					FryslanDebuggerMain.TargetID = a.getLocation();
					GoTa.setText("Name : " + a.getName() + "\n" + "ID : "
							+ a.getId() + "\n" + "Is onScreen : "
							+ a.isOnScreen() + "\n" + "Location : "
							+ a.getLocation() + "\n");
				} else {
					GoTa.setText(" ");
				}

			}
		});
		GOList.setBounds(10, 10, 156, 246);
		GoPannel.add(GOList);

		Panel GItems = new Panel();
		tabbedPane.addTab("GroundItems", null, GItems, null);
		GItems.setLayout(null);
		
		
		//GroundItem Pannel

		final TextArea GITA = new TextArea();
		GITA.setBounds(172, 10, 247, 246);
		GItems.add(GITA);

		final List GIList = new List();
		GIList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] selgi = GIList.getSelectedItem().toString().split(" ");
				GiId = Integer.parseInt(selgi[0]);
				GroundItem a = GroundItems.get(GiId);
				if (a != null && GiId > 0) {

					FryslanDebuggerMain.TargetID = a.getLocation();
					GITA.setText("Name : " + a.getName() + "\n" + "ID : "
							+ a.getId() + "\n" + "Is onScreen : "
							+ a.isOnScreen() + "\n" + "Location : "
							+ a.getLocation() + "\n" + "StackSize : "
							+ a.getStackSize());
				} else {
					GITA.setText(" ");
				}

			}
		});
		GIList.setBounds(10, 10, 156, 246);
		GItems.add(GIList);
		
		
		//Player Pannel

		Panel PlayerPannel = new Panel();
		tabbedPane.addTab("Players", null, PlayerPannel, null);
		PlayerPannel.setLayout(null);


		final TextArea PLTA = new TextArea();
		PLTA.setBounds(172, 10, 247, 246);
		PlayerPannel.add(PLTA);

		final List PlayerList = new List();
		PlayerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selpl = PlayerList.getSelectedItem().toString().split(" ");
				PlId = selpl[0];
				Player a = Players.getNearest(PlId);
				if (a != null) {

					FryslanDebuggerMain.TargetID = a.getLocation();

					PLTA.setText("Name : " + a.getName() + "\n"
							+ "Animation : " + a.getAnimation() + "\n"
							+ "HP : " + a.getHp() + "\n"
							+ "Max HP : "
							+ a.getMaxHp()
							+ "\n"
							+
							//"HP Percent : "+a.getHpPercent()+"\n"+
							"Prayer Icon : " + a.getPrayerIcon() + "\n"
							+ "Skull Icon : " + a.getSkullIcon() + "\n"
							+ "Is onScreen : " + a.isOnScreen() + "\n"
							+ "Location : " + a.getLocation() + "\n");
				} else {
					PLTA.setText(" ");
				}

			}
		});
		PlayerList.setBounds(10, 10, 156, 246);
		PlayerPannel.add(PlayerList);
		
		//Ineventory Pannel

		JPanel InventoryPannel = new JPanel();
		tabbedPane.addTab("Inventory", null, InventoryPannel, null);
		InventoryPannel.setLayout(null);

		final TextArea INVTA = new TextArea();
		INVTA.setBounds(172, 10, 247, 246);
		InventoryPannel.add(INVTA);

		final List InventoryList = new List();
		InventoryList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String[] selinv = InventoryList.getSelectedItem().toString()
						.split(" ");
				InvId = Integer.parseInt(selinv[0]);
				Item a = Inventory.getItem(InvId);
				if (a != null && InvId > 0) {

					INVTA.setText("Name : " + a.getName() + "\n" + "ID : "
							+ a.getId() + "\n" + "StackSize : "
							+ a.getStackSize());

				} else {
					INVTA.setText(" ");
				}

			}
		});
		InventoryList.setBounds(10, 10, 156, 246);
		InventoryPannel.add(InventoryList);

		// Path\Area Creater Pannel

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Path/Area Creator", null, panel_3, null);
		panel_3.setLayout(null);

		txtAreaName = new JTextField();
		txtAreaName.setText("Area Name");
		txtAreaName.setBounds(320, 159, 86, 20);
		panel_3.add(txtAreaName);
		txtAreaName.setColumns(10);

		JSeparator separator = new JSeparator();
		separator.setBounds(311, 130, 108, 2);
		panel_3.add(separator);

		txtPathName = new JTextField();
		txtPathName.setText("Path Name");
		txtPathName.setBounds(320, 28, 86, 20);
		panel_3.add(txtPathName);
		txtPathName.setColumns(10);

		final TextArea textArea = new TextArea();
		textArea.setBounds(10, 10, 304, 246);
		panel_3.add(textArea);

		JButton btnStartTile = new JButton("Start Tile[]");
		btnStartTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtPathName.getText().isEmpty()
						&& !txtPathName.getText().toString()
								.contentEquals("Path Name")) {
					textArea.append("//This TilePath Is Created Using Fryslan Debugger.\n\nTile[] "
							+ txtPathName.getText().toString()
							+ " = new Tile[] { \nnew Tile"
							+ CurrLocation() + " ,");

				} else {

					textArea.append("//This TilePath Is Created Using Fryslan Debugger.\n\nTile[] "
							+ "HEXArea"
							+ " = new Tile[] { \nnew Tile"
							+ CurrLocation() + " ,");
				}

			}
		});
		btnStartTile.setBounds(320, 54, 89, 23);
		panel_3.add(btnStartTile);

		JButton btnAddTile = new JButton("Add Tile");
		btnAddTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nnew Tile"
						+ CurrLocation() + " ,");
			}
		});
		btnAddTile.setBounds(320, 79, 89, 23);
		panel_3.add(btnAddTile);

		JButton btnEndTile = new JButton("End Tile");
		btnEndTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nnew Tile" + CurrLocation()
						+ " };\n\n");
			}
		});
		btnEndTile.setBounds(320, 103, 89, 23);
		panel_3.add(btnEndTile);

		JButton btnStartArea = new JButton("Start Area");
		btnStartArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtAreaName.getText().isEmpty()
						&& !txtAreaName.getText().toString()
								.contentEquals("Area Name")) {
					textArea.append("//This Area Is Created Using Fryslan Debugger.\n\nArea "
							+ txtAreaName.getText().toString()
							+ " = new Area(new Tile[] { \nnew Tile"
							+ CurrLocation() + " ,");

				} else {
					textArea.append("//This Area Is Created Using Fryslan Debugger.\n\nArea "
							+ "HEXArea"
							+ " = new Area(new Tile[] { \nnew Tile"
							+ CurrLocation() + " ,");
				}
			}
		});
		btnStartArea.setBounds(320, 186, 89, 23);
		panel_3.add(btnStartArea);

		JButton button_3 = new JButton("Add Tile");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nnew Tile"
						+ CurrLocation() + " ,");
			}
		});
		button_3.setBounds(320, 211, 89, 23);
		panel_3.add(button_3);

		JButton button_4 = new JButton("End Tile");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nnew Tile" + CurrLocation()
						+ " });\n\n");
			}
		});
		button_4.setBounds(320, 235, 89, 23);
		panel_3.add(button_4);

		JLabel lblAreaCreater = new JLabel("Area Creator");
		lblAreaCreater.setBounds(320, 143, 89, 14);
		panel_3.add(lblAreaCreater);

		JLabel lblPathCreater = new JLabel("Path Creator");
		lblPathCreater.setBounds(320, 10, 89, 14);
		panel_3.add(lblPathCreater);
		
		JButton button = new JButton("Load Data");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NPCList.removeAll();
				GOList.removeAll();
				GIList.removeAll();
				PlayerList.removeAll();
				

				Npc[] allNPC = Npcs.getLoaded();
				if (CKNPC.isSelected() == true) {
					for (Npc n : allNPC) {
						if (n != null
								&& !n.getName().toLowerCase().contains("null")
								&& textField.getText().toString().isEmpty()) {
							NPCList.add(n.getId() + " " + n.getName());
						} else if (!textField.getText().toString().isEmpty()) {
							if (n != null
									&& !n.getName().toLowerCase()
											.contains("null")
									&& n.getName()
											.toLowerCase()
											.contains(
													textField.getText()
															.toString()
															.toLowerCase())) {
								NPCList.add(n.getId() + " " + n.getName());
							}
						}
					}
				}

				java.util.List<GameObject> allGO = GameObjects.getLoaded();
				if (CKGO.isSelected() == true) {
					for (GameObject g : allGO) {
						if (g != null
								&& !g.getName().toLowerCase().contains("null")
								&& textField.getText().toString().isEmpty()) {
							GOList.add(g.getId() + " " + g.getName());
						} else if (!textField.getText().toString().isEmpty()) {
							if (g != null
									&& !g.getName().toLowerCase()
											.contains("null")
									&& g.getName()
											.toLowerCase()
											.contains(
													textField.getText()
															.toString()
															.toLowerCase())) {
								GOList.add(g.getId() + " " + g.getName());
							}
						}
					}
				}

				Player[] allPL = Players.getLoaded();
				if (CKPL.isSelected() == true) {
					for (Player p : allPL) {
						if (p != null
								&& !p.getName().toLowerCase().contains("null")
								&& textField.getText().toString().isEmpty()) {
							PlayerList.add(p.getName() + " ");
						} else if (!textField.getText().toString().isEmpty()) {
							if (p != null
									&& !p.getName().toLowerCase()
											.contains("null")
									&& p.getName()
											.toLowerCase()
											.contains(
													textField.getText()
															.toString()
															.toLowerCase())) {
								PlayerList.add(p.getName() + " ");
							}
						}
					}
				}

				GroundItem[] allGI = GroundItems.getAll();
				if (CKGI.isSelected() == true) {
					for (GroundItem gi : allGI) {
						if (gi != null
								&& gi.getDefinition() != null
								&& !gi.getDefinition().getName().toLowerCase()
										.contains("null")
								&& textField.getText().toString().isEmpty()) {
							GIList.add(gi.getId() + " "
									+ gi.getDefinition().getName());
						} else if (!textField.getText().toString().isEmpty()) {
							if (gi != null
									&& !gi.getName().toLowerCase()
											.contains("null")
									&& gi.getName()
											.toLowerCase()
											.contains(
													textField.getText()
															.toString()
															.toLowerCase())) {
								GIList.add(gi.getId() + " "
										+ gi.getDefinition().getName());
							}
						}
					}
				}

				Item[] allII = Inventory.getAll();
				if (CKINV.isSelected() == true) {
					for (Item ii : allII) {
						if (ii != null
								&& ii.getDefinition() != null
								&& !ii.getDefinition().getName().toLowerCase()
										.contains("null")
								&& textField.getText().toString().isEmpty()) {
							InventoryList.add(ii.getId() + " "
									+ ii.getDefinition().getName());
						} else if (!textField.getText().toString().isEmpty()) {
							if (ii != null
									&& !ii.getName().toLowerCase()
											.contains("null")
									&& ii.getName()
											.toLowerCase()
											.contains(
													textField.getText()
															.toString()
															.toLowerCase())) {
								InventoryList.add(ii.getId() + " "
										+ ii.getDefinition().getName());
							}
						}
					}
				}

			}
		});
		button.setBounds(9, 360, 213, 22);
		contentPane.add(button);

		JButton button_1 = new JButton("Close");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(228, 360, 200, 22);
		contentPane.add(button_1);

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setBounds(9, 337, 46, 14);
		contentPane.add(lblSearch);

		JButton btnClear = new JButton("Clear All");
		btnClear.setBounds(228, 333, 200, 23);
		contentPane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(" ");
				NPCList.removeAll();
				GOList.removeAll();
				GIList.removeAll();
				PlayerList.removeAll();
				InventoryList.removeAll();
			}
		});
	}
	
	public static String CurrLocation(){
		return "("+Players.getLocal().getLocation().getX()+" ,"+Players.getLocal().getLocation().getY()+" ,"+Game.getPlane()+")";
		
	}
}
