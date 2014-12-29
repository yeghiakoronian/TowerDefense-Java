package ui.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ui.towerdesign.TowerInfoPanel;
import core.applicationservice.warriorservices.TowerFactory;
import core.contract.DefenderConstants;
import core.contract.MapConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
/**
 * creates a panel for selecting towers
 * @author team5
 *
 */
public class TowerSelectionPanel extends JPanel {

	private boolean addTowerFlag;

	private String towertype;
	private JToggleButton modernTowerBtn;
	private JToggleButton ancientTowerBtn;
	private JToggleButton kingTowerBtn;
	private JPanel panel;
	private TowerInfoPanel towerInfoPanel;
	
	/**
	 * Create the panel.
	 * @param towerInfoPanel towerInfoPanel
	 */
	public TowerSelectionPanel(TowerInfoPanel towerInfoPanel) {
		this.towerInfoPanel = towerInfoPanel;
		panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gridBagLayout);
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file;
		file = new File(classLoader.getResource(MapConstants.MODERN_TOWER_IMG).getFile());
		BufferedImage buttonIcon;
		try {
			buttonIcon = ImageIO.read(file);
			modernTowerBtn = new JToggleButton(new ImageIcon(buttonIcon));
			modernTowerBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			modernTowerBtn.setBorder(BorderFactory.createEmptyBorder());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GridBagConstraints gbc_modernTowerBtn = new GridBagConstraints();
		gbc_modernTowerBtn.anchor = GridBagConstraints.WEST;
		gbc_modernTowerBtn.insets = new Insets(0, 0, 5, 0);
		gbc_modernTowerBtn.gridx = 0;
		gbc_modernTowerBtn.gridy = 0;
		add(modernTowerBtn, gbc_modernTowerBtn);
		
		
		
		
		
		
		file = new File(classLoader.getResource(MapConstants.ANCIENT_TOWER_IMG).getFile());
		try {
			buttonIcon = ImageIO.read(file);
			ancientTowerBtn = new JToggleButton(new ImageIcon(buttonIcon));
			ancientTowerBtn.setBorder(BorderFactory.createEmptyBorder());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		JToggleButton ancientTowerBtn = new JToggleButton(DefenderConstants.ANCIENT_TOWER_TYPE);
		GridBagConstraints gbc_ancientTowerBtn = new GridBagConstraints();
		gbc_ancientTowerBtn.anchor = GridBagConstraints.WEST;
		gbc_ancientTowerBtn.insets = new Insets(0, 0, 5, 0);
		gbc_ancientTowerBtn.gridx = 0;
		gbc_ancientTowerBtn.gridy = 1;
		add(ancientTowerBtn, gbc_ancientTowerBtn);
		
		
		
		
		
		
		
		
		file = new File(classLoader.getResource(MapConstants.KING_TOWER_IMG).getFile());
		try {
			buttonIcon = ImageIO.read(file);
			kingTowerBtn = new JToggleButton(new ImageIcon(buttonIcon));
			kingTowerBtn.setBorder(BorderFactory.createEmptyBorder());
		} catch (IOException e) {
			e.printStackTrace();
		}
//		JToggleButton kingTowerBtn = new JToggleButton(DefenderConstants.KING_TOWER_TYPE);
		GridBagConstraints gbc_kingTowerBtn = new GridBagConstraints();
		gbc_kingTowerBtn.anchor = GridBagConstraints.WEST;
		gbc_kingTowerBtn.gridx = 0;
		gbc_kingTowerBtn.gridy = 2;
		add(kingTowerBtn, gbc_kingTowerBtn);
		
		
		
		
		
//		modernTowerBtn.addActionListener(this);
//		ancientTowerBtn.addActionListener(this);
//		kingTowerBtn.addActionListener(this);
		modernTowerBtn.addActionListener(new ActionListener(){
		       public void actionPerformed(ActionEvent e) {
		             tower(DefenderConstants.MODERN_TOWER_TYPE);
		       }});
		       
		ancientTowerBtn.addActionListener(new ActionListener(){
		       public void actionPerformed(ActionEvent e) {
		             tower(DefenderConstants.ANCIENT_TOWER_TYPE);
		       }});
		
		kingTowerBtn.addActionListener(new ActionListener(){
		       public void actionPerformed(ActionEvent e) {
		             tower(DefenderConstants.KING_TOWER_TYPE);
		       }});

	}

	
	
	
	
	
	
	
	
	
	
	
//	public void actionPerformed(ActionEvent event) {
//		String command = event.getActionCommand();
//
//		switch (command) {
//		case DefenderConstants.MODERN_TOWER_TYPE:
//			towertype = DefenderConstants.MODERN_TOWER_TYPE;
//			tower(towertype);
//			break;
//		case DefenderConstants.ANCIENT_TOWER_TYPE:
//			towertype = DefenderConstants.ANCIENT_TOWER_TYPE;
//			tower(towertype);
//			break;
//		case DefenderConstants.KING_TOWER_TYPE:
//			towertype = DefenderConstants.KING_TOWER_TYPE;
//			tower(towertype);
//			break;
//		}
//	}

	/**
	 * creates a tower of a given type
	 * @param towerType
	 */
	private void tower(String towerType) {
		TowerFactory towerFactory = new TowerFactory();
		Tower tower = towerFactory.getTower(towerType, TowerLevel.one);
		towerInfoPanel.removeAll();
		towerInfoPanel.showTowerDetail(tower);
		towerInfoPanel.repaint();
		addTowerFlag = true;
		SelectedTower.setInstance(towerType, tower, addTowerFlag);
//		try {
//			addTowerFlag = true;
//			switch (towertype) {
//			case DefenderConstants.MODERN_TOWER_TYPE:
////				colorToDisplayTower = modernTowerBtn.getBackground();
//				towerInfoPanel = new TowerInfoPanel(tower);
//				break;
//			case DefenderConstants.ANCIENT_TOWER_TYPE:
////				colorToDisplayTower = ancientTowerBtn.getBackground();
//				break;
//			case DefenderConstants.KING_TOWER_TYPE:
////				colorToDisplayTower = kingTowerBtn.getBackground();
//				break;
//			}
////			cellContent = GridCellContentType.TOWER;
//		} catch (java.lang.Exception ex) {
//			JOptionPane.showMessageDialog(null, ex.getMessage());
//		}
	}
	
}
