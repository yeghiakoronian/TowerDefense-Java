package ui.game;

import javax.swing.JPanel;

import core.applicationservice.warriorservices.TowerFactory;
import core.contract.DefenderConstants;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;

import java.awt.GridBagLayout;

import ui.towerdesign.TowerInfoPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class TowerPanel extends JPanel {

	private TowerSelectionPanel towerSelectionPanel;
	private TowerInfoPanel towerInfoPanel;
	
	/**
	 * Create the panel.
	 */
	public TowerPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		towerInfoPanel = new TowerInfoPanel();
		towerSelectionPanel = new TowerSelectionPanel(towerInfoPanel);
		GridBagConstraints gbc_towerSelectionPanel = new GridBagConstraints();
		gbc_towerSelectionPanel.anchor = GridBagConstraints.WEST;
		gbc_towerSelectionPanel.insets = new Insets(0, 0, 0, 5);
		gbc_towerSelectionPanel.fill = GridBagConstraints.VERTICAL;
		gbc_towerSelectionPanel.gridx = 0;
		gbc_towerSelectionPanel.gridy = 0;
		add(towerSelectionPanel, gbc_towerSelectionPanel);
		
		GridBagConstraints gbc_towerInfoPanel = new GridBagConstraints();
		gbc_towerInfoPanel.fill = GridBagConstraints.BOTH;
		gbc_towerInfoPanel.gridx = 1;
		gbc_towerInfoPanel.gridy = 0;
		add(towerInfoPanel, gbc_towerInfoPanel);

	}

}
