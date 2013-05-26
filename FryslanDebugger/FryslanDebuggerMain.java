package FryslanDebugger;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hexbot.api.listeners.Paintable;
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
import org.hexbot.script.Manifest;
import org.hexbot.script.Script;

@Manifest(author = "Fryslan", name = "Fryslan Debugger", description = "Ultimate Dev Tool", version = 0.1)
public class FryslanDebuggerMain extends Script implements Paintable {


	@Override
	public int loop() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onEnd() {
		log("Thanks For Using Fryslan Debugger!");
		
	}

	@Override
	public void onStart() {
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
	
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		
	}
		
}
