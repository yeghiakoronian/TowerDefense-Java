package ui.towerdesign;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.applicationservice.warriorservices.TowerFactory;
import core.domain.warriors.defenders.towers.Tower;
/**
 * thus is tower information panel
 * @author team5
 *
 */
@SuppressWarnings("serial")
public class TowerInfoPanel extends JPanel {
	private JLabel speedCount;
	private JLabel rangeCount;
	private JLabel powerCount;
	private JLabel valueCount;
	private JLabel levelLabel;
	@SuppressWarnings("unused")
	private Tower tower;
	@SuppressWarnings("unused")
	private String towertype;

	/**
	 * Creates the panel.
	 */
	public TowerInfoPanel(){}
	
	/**
	 * <b>Constructs a tower info panel.</b>
	 * @param tower Tower object passed to the constructor.
	 */
	public void showTowerDetail (Tower tower) {

		this.tower = tower;

		List<Tower> towerList = tower.getTowers();
		TowerFactory f = new TowerFactory();
		this.towertype = f.getDecoratedName(towerList);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel speedLable = new JLabel("Fire Speed");
		speedLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_speedLable = new GridBagConstraints();
		gbc_speedLable.insets = new Insets(0, 0, 5, 5);
		gbc_speedLable.gridx = 1;
		gbc_speedLable.gridy = 1;
		add(speedLable, gbc_speedLable);

		speedCount = new JLabel("");
		GridBagConstraints gbc_speedCount = new GridBagConstraints();
		gbc_speedCount.insets = new Insets(0, 0, 5, 0);
		gbc_speedCount.gridx = 2;
		gbc_speedCount.gridy = 1;
		add(speedCount, gbc_speedCount);

		JLabel powerLable = new JLabel("Fire Power");
		powerLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_powerLable = new GridBagConstraints();
		gbc_powerLable.insets = new Insets(0, 0, 5, 5);
		gbc_powerLable.gridx = 1;
		gbc_powerLable.gridy = 2;
		add(powerLable, gbc_powerLable);

		powerCount = new JLabel("");
		GridBagConstraints gbc_powerCount = new GridBagConstraints();
		gbc_powerCount.insets = new Insets(0, 0, 5, 0);
		gbc_powerCount.gridx = 2;
		gbc_powerCount.gridy = 2;
		add(powerCount, gbc_powerCount);

		JLabel rangeLable = new JLabel("Fire Range");
		rangeLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_rangeLable = new GridBagConstraints();
		gbc_rangeLable.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLable.gridx = 1;
		gbc_rangeLable.gridy = 3;
		add(rangeLable, gbc_rangeLable);

		rangeCount = new JLabel("");
		GridBagConstraints gbc_rangeCount = new GridBagConstraints();
		gbc_rangeCount.insets = new Insets(0, 0, 5, 0);
		gbc_rangeCount.gridx = 2;
		gbc_rangeCount.gridy = 3;
		add(rangeCount, gbc_rangeCount);

		JLabel valueLable = new JLabel("Price         ");
		valueLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_valueLable = new GridBagConstraints();
		gbc_valueLable.insets = new Insets(0, 0, 5, 5);
		gbc_valueLable.gridx = 1;
		gbc_valueLable.gridy = 4;
		add(valueLable, gbc_valueLable);

		valueCount = new JLabel("");
		GridBagConstraints gbc_valueCount = new GridBagConstraints();
		gbc_valueCount.insets = new Insets(0, 0, 5, 0);
		gbc_valueCount.gridx = 2;
		gbc_valueCount.gridy = 4;
		add(valueCount, gbc_valueCount);

		JLabel label_1 = new JLabel(" ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 5;
		add(label_1, gbc_label_1);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;

		levelLabel = new JLabel("");
		GridBagConstraints gbc_levelLabel = new GridBagConstraints();
		gbc_levelLabel.insets = new Insets(0, 0, 5, 5);
		gbc_levelLabel.gridx = 1;
		gbc_levelLabel.gridy = 6;
		add(levelLabel, gbc_levelLabel);
		GridBagConstraints gbc_upgradeBtn = new GridBagConstraints();
		gbc_upgradeBtn.insets = new Insets(0, 0, 5, 0);
		gbc_upgradeBtn.gridx = 2;
		gbc_upgradeBtn.gridy = 6;

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 7;
		add(label, gbc_label);

		GridBagConstraints gbc_sellPriceLable = new GridBagConstraints();
		gbc_sellPriceLable.insets = new Insets(0, 0, 5, 5);
		gbc_sellPriceLable.gridx = 1;
		gbc_sellPriceLable.gridy = 8;

		GridBagConstraints gbc_sellPriceCount = new GridBagConstraints();
		gbc_sellPriceCount.insets = new Insets(0, 0, 5, 0);
		gbc_sellPriceCount.gridx = 2;
		gbc_sellPriceCount.gridy = 8;
		List<Tower> towerDetails = tower.getTowers();
		TowerFactory factory = new TowerFactory();

		// set texboxes with current feature informations
		Map<String, Integer> details = factory.getFeaturesCount(towerDetails);
		Iterator<String> keySetIterator = details.keySet().iterator();
		while (keySetIterator.hasNext()) {
			String key = keySetIterator.next();
			switch (key) {
			case "FirePower":
				powerCount.setText((details.get(key)).toString());
				break;
			case "FireRange":
				rangeCount.setText((details.get(key)).toString());
				break;
			case "FireSpeed":
				speedCount.setText((details.get(key)).toString());
				break;
			}
		}
		long value = (tower.cost());
		valueCount.setText(Long.toString(value));
		levelLabel.setText("Level " + tower.getLevel());
		@SuppressWarnings("unused")
		GridBagConstraints gbc_sellBtn = new GridBagConstraints();
setVisible(true);
//setBackground(Color.GREEN);
//setSize(200,200);

	}







}
