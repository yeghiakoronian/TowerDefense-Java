package ui.game;

import java.awt.event.*;
import java.awt.*;
/**
 * 
 * @author Team5
 *<b> This class is the mouse listener click and motion</b>
 */
public class Handler implements MouseListener, MouseMotionListener{

//	MapPanel mapPanel;
	LayeredMapPanel mapPanel;
	
//	public Handler(MapPanel mapPanel){
	/**
	 * 
	 * @param mapPanel assign the mapPanel to this mapPanel
	 */
	public Handler(LayeredMapPanel mapPanel){
		this.mapPanel = mapPanel;
	}
	
	/**
	 * 
	 */
	public void mouseDragged(MouseEvent e) {
//		Screen.mouse = new Point(e.getX() - ((Frame.size.width-Screen.myWidth)/2),(e.getY())+((Frame.size.height-(Screen.myHeight))-(Frame.size.width- Screen.myWidth)/2));
		
	}

	/**
	 * 
	 */
	public void mouseMoved(MouseEvent e) {
//		Screen.mouse = new Point(e.getX() - ((Frame.size.width-Screen.myWidth)/2),(e.getY())-((Frame.size.height-(Screen.myHeight))-(Frame.size.width- Screen.myWidth)/2));
		
	}

	/**
	 * @param event the xy coordinate of where the click occurred 
	 * according to the click we set that tower to the click location
	 */
	public void mouseClicked(MouseEvent event) {
		int mouseX = event.getX();
		int mouseY = event.getY();
		
		if((mouseX>=mapPanel.getOtherItemsPanel().getMapTopLeft().getX() && mouseX<=mapPanel.getOtherItemsPanel().getMapButtomRight().getX()) &&
				(mouseY>=mapPanel.getOtherItemsPanel().getMapTopLeft().getY() && mouseY<=mapPanel.getOtherItemsPanel().getMapButtomRight().getY())){
			mapPanel.getOtherItemsPanel().setCellLocation(mouseX, mouseY);
			mapPanel.getOtherItemsPanel().towerOperation();
		}
	}

	/**
	 * 
	 */
	public void mousePressed(MouseEvent e) {
//		Screen.store.click(e.getButton());
		
	}

	/**
	 * 
	 */
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
