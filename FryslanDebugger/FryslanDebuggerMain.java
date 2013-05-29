package FryslanDebugger;

import java.awt.EventQueue;
import java.awt.Graphics;
import org.hexbot.api.listeners.Paintable;
import org.hexbot.api.methods.Players;
import org.hexbot.api.wrapper.Tile;
import org.hexbot.script.Manifest;
import org.hexbot.script.Script;

@Manifest(author = "Fryslan", name = "Fryslan Debugger", description = "Ultimate Dev Tool", version = 1.1)
public class FryslanDebuggerMain extends Script implements Paintable {
	
	public static Tile TargetID;

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
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	@Override
	public void paint(Graphics g) {
		
		Tile myTile = TargetID; 
		if(myTile != null){
		g.fillPolygon(myTile.getBounds().xpoints,myTile.getBounds().ypoints,myTile.getBounds().npoints);
		g.drawLine(Players.getLocal().getLocation().getScreenLocation().x, Players.getLocal().getLocation().getScreenLocation().y, TargetID.getScreenLocation().x,TargetID.getScreenLocation().y);
	}
		
		
	}
		
}
