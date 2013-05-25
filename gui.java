package FryslanDebugger;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import org.hexbot.api.methods.GameObjects;
import org.hexbot.api.methods.GroundItems;
import org.hexbot.api.methods.Npcs;
import org.hexbot.api.methods.Players;
import org.hexbot.api.wrapper.Area;
import org.hexbot.api.wrapper.GameObject;
import org.hexbot.api.wrapper.GroundItem;
import org.hexbot.api.wrapper.Npc;
import org.hexbot.api.wrapper.Player;
import org.hexbot.api.wrapper.Tile;
import org.hexbot.script.Script;

import java.awt.TextArea;
import java.awt.Panel;
import java.awt.List;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class gui extends JFrame {

	private JPanel contentPane;
	int NPCID;
	int GOID;
	int GIID;
	String PLID;
	private JTextField txtAreaName;
	private JTextField txtPathName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public gui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 424, 294);
		contentPane.add(tabbedPane);
		
		Panel panel = new Panel();
		tabbedPane.addTab("NPCs", null, panel, null);
		panel.setLayout(null);
		
		final TextArea NPCTA = new TextArea();
		NPCTA.setBounds(172, 10, 247, 246);
		panel.add(NPCTA);
		
		final List NPCList = new List();
		NPCList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String[] selnpc = NPCList.getSelectedItem().toString().split(" ");
			NPCID = Integer.parseInt(selnpc[0]);
			Npc a = Npcs.get(NPCID);
			if(a!= null && NPCID >0 ){
				NPCTA.setText("Name : "+a.getName()+"\n"+
						"ID : "+a.getId()+"\n"+
						"Animation : "+a.getAnimation()+"\n"+
						"Is onScreen : "+a.isOnScreen()+"\n"+
						"Location : "+a.getLocation()+"\n"+
						"Distance To : "+a.getDistanceTo(Players.getLocal().getLocation())+"\n"+
						"Text Spoken : "+a.getText()+"\n"+
						"Is Moving : "+a.isMoving()+"\n");
			}else{
				NPCTA.setText(" ");
			}
			
			}
		});
		NPCList.setBounds(10, 10, 156, 246);
		panel.add(NPCList);
		
		
		
		Panel panel_1 = new Panel();
		tabbedPane.addTab("GameObjects", null, panel_1, null);
		panel_1.setLayout(null);
		
		final TextArea SOTA = new TextArea();
		SOTA.setBounds(172, 10, 247, 246);
		panel_1.add(SOTA);
		
		final List GOList = new List();
		GOList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selgo = GOList.getSelectedItem().toString().split(" ");
				GOID = Integer.parseInt(selgo[0]);
				GameObject a = GameObjects.getNearest(GOID);
				if(a!= null && GOID >0 ){
					SOTA.setText("Name : "+a.getName()+"\n"+
							"ID : "+a.getId()+"\n"+
							"Is onScreen : "+a.isOnScreen()+"\n"+
							"Location : "+a.getLocation()+"\n");
				}else{
					SOTA.setText(" ");
				}
				
				}
		});
		GOList.setBounds(10, 10, 156, 246);
		panel_1.add(GOList);
		
		
		
		
		
		Panel GItems = new Panel();
		tabbedPane.addTab("GroundItems", null, GItems, null);
		GItems.setLayout(null);
		

		final TextArea GITA = new TextArea();
		GITA.setBounds(172, 10, 247, 246);
		GItems.add(GITA);
		
		
		final List GIList = new List();
		GIList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] selgi = GIList.getSelectedItem().toString().split(" ");
				GIID = Integer.parseInt(selgi[0]);
				GroundItem a = GroundItems.get(GIID);
				if(a!= null && GIID >0 ){
					GITA.setText("Name : "+a.getName()+"\n"+
							"ID : "+a.getId()+"\n"+
							"Is onScreen : "+a.isOnScreen()+"\n"+
							"Location : "+a.getLocation()+"\n"+
							"StackSize : "+a.getStackSize());
				}else{
					GITA.setText(" ");
				}
				
				
			}
		});
		GIList.setBounds(10, 10, 156, 246);
		GItems.add(GIList);
		
		
		Panel panel_2 = new Panel();
		tabbedPane.addTab("Players", null, panel_2, null);
		panel_2.setLayout(null);
		
		final TextArea PLTA = new TextArea();
		PLTA.setBounds(172, 10, 247, 246);
		panel_2.add(PLTA);
		
		final List PLList = new List();
		PLList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] selpl = PLList.getSelectedItem().toString().split(" ");
				PLID = selpl[0];
				Player a = Players.getNearest(PLID);
				if(a!= null){
					PLTA.setText("Name : "+a.getName()+"\n"+
							"Animation : "+a.getAnimation()+"\n"+
							"Prayer Icon : "+a.getPrayerIcon()+"\n"+
							"Skull Icon : "+a.getSkullIcon()+"\n"+
							"Is onScreen : "+a.isOnScreen()+"\n"+
							"Location : "+a.getLocation()+"\n"
							);
				}else{
					PLTA.setText(" ");
				}
				
				
			}
		});
		PLList.setBounds(10, 10, 156, 246);
		panel_2.add(PLList);
		
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
				if(!txtPathName.getText().isEmpty() &&!txtPathName.getText().toString().contentEquals("Path Name")){
					textArea.append("//This TilePath Is Created Using Fryslan Debugger.\n\nTile[] "+txtPathName.getText().toString()+" = new Tile[] { \nnew Tile"+Players.getLocal().getLocation()+" ,");
				}else{
					textArea.append("//This TilePath Is Created Using Fryslan Debugger.\n\nTile[] "+"HEXArea"+" = new Tile[] { \nnew Tile"+Players.getLocal().getLocation()+" ,");
				}
				
				
			}
		});
		btnStartTile.setBounds(320, 54, 89, 23);
		panel_3.add(btnStartTile);
		
		JButton btnAddTile = new JButton("Add Tile");
		btnAddTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\n new Tile"+Players.getLocal().getLocation()+" ,");
			}
		});
		btnAddTile.setBounds(320, 79, 89, 23);
		panel_3.add(btnAddTile);
		
		JButton btnEndTile = new JButton("End Tile");
		btnEndTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nnew Tile"+Players.getLocal().getLocation()+" };\n\n");
			}
		});
		btnEndTile.setBounds(320, 103, 89, 23);
		panel_3.add(btnEndTile);
		
		JButton btnStartArea = new JButton("Start Area");
		btnStartArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtAreaName.getText().isEmpty() &&txtAreaName.getText().toString().contentEquals("Area Name")){
					textArea.append("//This Area Is Created Using Fryslan Debugger.\n\nArea "+txtAreaName.getText().toString()+" = new Area(new Tile[] { \nnew Tile"+Players.getLocal().getLocation()+" ,");
					}else{
						textArea.append("//This Area Is Created Using Fryslan Debugger.\n\nArea "+"HEXArea"+" = new Area(new Tile[] { \nnew Tile"+Players.getLocal().getLocation()+" ,");
					}
			}
		});
		btnStartArea.setBounds(320, 186, 89, 23);
		panel_3.add(btnStartArea);
		
		JButton button_3 = new JButton("Add Tile");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\n new Tile"+Players.getLocal().getLocation()+" ,");
			}
		});
		button_3.setBounds(320, 211, 89, 23);
		panel_3.add(button_3);
		
		JButton button_4 = new JButton("End Tile");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.append("\nnew Tile"+Players.getLocal().getLocation()+" });\n\n");
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
		
		JButton btnClear = new JButton("Clear All");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(" ");
			}
		});
		btnClear.setBounds(321, 242, 89, 23);
		panel_3.add(btnClear);
		
		Button button = new Button("Load Data");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NPCList.removeAll();
				GOList.removeAll();
				GIList.removeAll();
				PLList.removeAll();
				
				Npc[] allNPC = Npcs.getLoaded();
				for(Npc n : allNPC){
					if(n != null && !n.getName().toLowerCase().contains("null")){
						NPCList.add(n.getId()+" "+n.getName());
					}
				}
				
				java.util.List<GameObject> allGO = GameObjects.getLoaded();
				for(GameObject g : allGO){
					if(g != null && !g.getName().toLowerCase().contains("null") ){
						GOList.add(g.getId()+" "+g.getName());
					}
				}
				
				GroundItem[] allGI = GroundItems.getAll();
				for(GroundItem gi : allGI){
					if(gi != null && !gi.toString().isEmpty() && !gi.getName().toLowerCase().contains("null") && gi.getId() > 0){
						GIList.add(gi.getId()+" "+gi.getName());
					}
				}
				
				Player[] allPL = Players.getLoaded();
				for(Player p : allPL){
					if(p != null && !p.getName().toLowerCase().contains("null")){
						PLList.add(p.getName()+" ");
					}
				}
				
				
			}
		});
		button.setBounds(5, 305, 213, 22);
		contentPane.add(button);
		
		Button button_1 = new Button("Close");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(211, 305, 213, 22);
		contentPane.add(button_1);
	}
}
