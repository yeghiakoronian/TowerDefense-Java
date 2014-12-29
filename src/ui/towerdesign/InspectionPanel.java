package ui.towerdesign;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import core.applicationservice.warriorservices.TowerFactory;
import core.applicationservice.warriorservices.TowerMarket;
import core.contract.DefenderConstants;
import core.domain.account.BankManager;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towertype.TowerLevel;
import core.domain.waves.Position;

/**
 * <b>This class is an Observable class</b>
 * @author Team5
 */
public class InspectionPanel extends Observable implements ActionListener {
	private String towertype;
	private BankManager bank;
	private Tower tower;
	@SuppressWarnings("unused")
	private long availFunds;
	private String performedAction;
	private JLabel speedCount;
	private JLabel rangeCount;
	private JLabel powerCount;
	private JLabel valueCount;
	private JLabel sellPriceLable;
	private JLabel sellPriceCount;
	private JLabel levelLabel;
	private JButton upgradeBtn;
	private JButton sellBtn;
	private JPanel panel;
	private JDialog dialog;
	private JComboBox<String> strategyCombo;
	private String shootingStrategy;

	/**
	 * Create the panel.
	 * @param tower the tower that this panel needs to show its information
	 */
	public InspectionPanel(Tower tower) {
		this.bank = BankManager.getInstance();
		availFunds = this.bank.getBalance() - this.bank.getCurrentBalance();

		performedAction = "";
		dialog = new JDialog();
		dialog.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				closeInspector();

			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});

		panel = new JPanel();
		this.tower = tower;
		dialog.getContentPane().setLayout(new FlowLayout());
		dialog.setTitle("Tower Inspection");

		List<Tower> towerList = tower.getTowers();
		TowerFactory f = new TowerFactory();
		this.towertype = f.getDecoratedName(towerList);
		this.bank = BankManager.getInstance();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gridBagLayout);

		JLabel speedLable = new JLabel("Fire Speed");
		speedLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_speedLable = new GridBagConstraints();
		gbc_speedLable.insets = new Insets(0, 0, 5, 5);
		gbc_speedLable.gridx = 1;
		gbc_speedLable.gridy = 1;
		panel.add(speedLable, gbc_speedLable);

		speedCount = new JLabel("");
		GridBagConstraints gbc_speedCount = new GridBagConstraints();
		gbc_speedCount.insets = new Insets(0, 0, 5, 0);
		gbc_speedCount.gridx = 2;
		gbc_speedCount.gridy = 1;
		panel.add(speedCount, gbc_speedCount);

		JLabel powerLable = new JLabel("Fire Power");
		powerLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_powerLable = new GridBagConstraints();
		gbc_powerLable.insets = new Insets(0, 0, 5, 5);
		gbc_powerLable.gridx = 1;
		gbc_powerLable.gridy = 2;
		panel.add(powerLable, gbc_powerLable);

		powerCount = new JLabel("");
		GridBagConstraints gbc_powerCount = new GridBagConstraints();
		gbc_powerCount.insets = new Insets(0, 0, 5, 0);
		gbc_powerCount.gridx = 2;
		gbc_powerCount.gridy = 2;
		panel.add(powerCount, gbc_powerCount);

		JLabel rangeLable = new JLabel("Fire Range");
		rangeLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_rangeLable = new GridBagConstraints();
		gbc_rangeLable.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLable.gridx = 1;
		gbc_rangeLable.gridy = 3;
		panel.add(rangeLable, gbc_rangeLable);

		rangeCount = new JLabel("");
		GridBagConstraints gbc_rangeCount = new GridBagConstraints();
		gbc_rangeCount.insets = new Insets(0, 0, 5, 0);
		gbc_rangeCount.gridx = 2;
		gbc_rangeCount.gridy = 3;
		panel.add(rangeCount, gbc_rangeCount);

		JLabel valueLable = new JLabel("Value       ");
		valueLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_valueLable = new GridBagConstraints();
		gbc_valueLable.insets = new Insets(0, 0, 5, 5);
		gbc_valueLable.gridx = 1;
		gbc_valueLable.gridy = 4;
		panel.add(valueLable, gbc_valueLable);

		valueCount = new JLabel("");
		GridBagConstraints gbc_valueCount = new GridBagConstraints();
		gbc_valueCount.insets = new Insets(0, 0, 5, 0);
		gbc_valueCount.gridx = 2;
		gbc_valueCount.gridy = 4;
		panel.add(valueCount, gbc_valueCount);
		rangeLable.setHorizontalAlignment(SwingConstants.LEFT);
		gbc_rangeLable.insets = new Insets(0, 0, 5, 5);
		gbc_rangeLable.gridx = 1;
		gbc_rangeLable.gridy = 5;

		///// set combo box//////
		strategyCombo = new JComboBox<>();
		DefaultComboBoxModel<String> strategyModel = new DefaultComboBoxModel<>();
		GridBagConstraints gbc_strategy = new GridBagConstraints();
		strategyModel.addElement(DefenderConstants.NearToEnd_Strategy);
		strategyModel.addElement(DefenderConstants.NearToStart_Strategy);
		strategyModel.addElement(DefenderConstants.Strangest_Strategy);
		strategyModel.addElement(DefenderConstants.Weakest_Strategy);
		strategyModel.addElement(DefenderConstants.NearToTower_Strategy);
		strategyCombo.setModel(strategyModel);
		if(tower != null){
			strategyCombo.setSelectedItem(tower.getShootingStrategy());
		}
		strategyCombo.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				shootingStrategy = (String)strategyCombo.getSelectedItem();
				if(tower != null){
					tower.setShootingStrategy(shootingStrategy);
					sendUpdateSignal();
				}
			}
		});

		JLabel strategyLable = new JLabel("Strategy ");
		GridBagConstraints gbc_stratgyLable = new GridBagConstraints();
		gbc_stratgyLable.anchor = GridBagConstraints.WEST;
		gbc_stratgyLable.insets = new Insets(0, 0, 5, 5);
		gbc_stratgyLable.gridx = 1;
		gbc_stratgyLable.gridy = 5;
		panel.add(strategyLable, gbc_stratgyLable); 
		strategyCombo.setModel(strategyModel);
		gbc_strategy.insets = new Insets(0, 0, 5, 0);
		gbc_strategy.gridx = 2;
		gbc_strategy.gridy = 5;
		panel.add(strategyCombo, gbc_strategy);


		upgradeBtn = new JButton("Upgrade");
		upgradeBtn.setSize(30, 20);
		upgradeBtn.addActionListener(this);
		if (tower.getLevel().equals(TowerLevel.three)) {
			this.upgradeBtn.setEnabled(false);
		}

		JLabel label_1 = new JLabel(" ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 6;
		panel.add(label_1, gbc_label_1);

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 7;

		levelLabel = new JLabel("");
		GridBagConstraints gbc_levelLabel = new GridBagConstraints();
		gbc_levelLabel.anchor = GridBagConstraints.WEST;
		gbc_levelLabel.insets = new Insets(0, 0, 5, 5);
		gbc_levelLabel.gridx = 1;
		gbc_levelLabel.gridy = 7;
		panel.add(levelLabel, gbc_levelLabel);
		GridBagConstraints gbc_upgradeBtn = new GridBagConstraints();
		gbc_upgradeBtn.insets = new Insets(0, 0, 5, 0);
		gbc_upgradeBtn.gridx = 2;
		gbc_upgradeBtn.gridy = 7;
		panel.add(upgradeBtn, gbc_upgradeBtn);

		JLabel label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 8;
		panel.add(label, gbc_label);

		sellPriceLable = new JLabel("Sell Price ");
		sellPriceLable.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_sellPriceLable = new GridBagConstraints();
		gbc_sellPriceLable.insets = new Insets(0, 0, 0, 5);
		gbc_sellPriceLable.gridx = 1;
		gbc_sellPriceLable.gridy = 9;
		panel.add(sellPriceLable, gbc_sellPriceLable);

		sellPriceCount = new JLabel("");
		GridBagConstraints gbc_sellPriceCount = new GridBagConstraints();
		gbc_sellPriceCount.gridx = 2;
		gbc_sellPriceCount.gridy = 9;
		panel.add(sellPriceCount, gbc_sellPriceCount);
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
		sellBtn = new JButton("Sell");
		sellBtn.setSize(30, 20);
		sellBtn.addActionListener(this);
		GridBagConstraints gbc_sellBtn = new GridBagConstraints();
		gbc_sellBtn.gridx = 2;
		gbc_sellBtn.gridy = 9;
		panel.add(sellBtn, gbc_sellBtn);
		TowerMarket market = new TowerMarket();
		long sellPrice = market.sellTower(tower);
		sellPriceCount.setText(new Long(sellPrice).toString());

		// end of texboxes'text setting
		dialog.getContentPane().add(panel);
		dialog.setVisible(true);
		dialog.setSize(300, 303);
		dialog.setLocationRelativeTo(null);

	}

	/**
	 * <b>Based on user action, either upgrades a tower or sells it.</b>
	 * @param event ActionEvent passed based on user action.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case "Upgrade":
			upgrade();
			break;
		case "Sell":
			sell();
			break;
		}
	}

	/**
	 * <b>This method is used by the Observer to know what has been updated.</b>
	 * 
	 * @return "Sell" or "Upgrade"
	 */
	public String getPerformedAction() {
		return performedAction;

	}
/**
 * method is used to sell and compute  a current balance
 */
	private void sell() {
		String str = sellPriceCount.getText();
		long temp = bank.getCurrentBalance();
		long sellPrice = new Long(str).longValue();
		temp -= sellPrice;
		bank.resetCurrentBalance();
		bank.setCurrentBalance(temp);
		performedAction = "Sell";
		closeInspector();
	}
/**
 * 
 */
	private void upgrade() {
		if (!tower.getLevel().equals(TowerLevel.three)) {
			String str = this.speedCount.getText();
			int speedCount = Integer.parseInt(str) + 1;
			str = rangeCount.getText();
			int rangeCount = Integer.parseInt(str) + 1;
			str = powerCount.getText();
			int powerCount = Integer.parseInt(str) + 1;

			String tempId = tower.Id;
			Position p = tower.getTowerPosition();
			Tower newTower = upgradeLevel(this.tower, speedCount,
					rangeCount, powerCount);
			if (newTower != null) {
				newTower.Id = tempId;
				newTower.setTowerPosition(p);
				this.tower = newTower;
				this.speedCount.setText(new Integer(speedCount++).toString());
				this.rangeCount.setText(new Integer(rangeCount++).toString());
				this.powerCount.setText(new Integer(powerCount++).toString());
				this.valueCount.setText(Long.toString(newTower.cost()));
				TowerMarket market = new TowerMarket();
				this.sellPriceCount.setText(Double.toString(market
						.sellTower(newTower)));
				this.levelLabel.setText("Level "
						+ newTower.getLevel().toString());
				this.sellBtn.setEnabled(false);
				performedAction = "Upgrade";
				sendUpdateSignal();
				if (newTower.getLevel().equals(TowerLevel.three)) {
					this.upgradeBtn.setEnabled(false);
				}
				this.tower = newTower;
			}
		}

		// this.setVisible(false);

	}

	/**
	 * @return The tower that has been acted upon.
	 */
	public Tower getTower() {
		return this.tower;
	}
/**
 * Upgrades a level of towers
 * @param tower that is need to be upgraded 
 * @param speedCount new value for speed
 * @param rangeCount new value for range
 * @param powerCount new value for power
 * @return
 */
	private Tower upgradeLevel(Tower tower, int speedCount,
			int rangeCount, int powerCount) {
		TowerFactory factory = new TowerFactory();
		List<Tower> towerList = tower.getTowers();
		this.towertype = factory.getDecoratedName(towerList);
		long value = tower.cost();
		TowerLevel level = tower.getLevel();
		Tower createdTower = null;
//		if(level == TowerLevel.one)
			createdTower = factory.updateLevel(tower, this.towertype, speedCount,
				rangeCount, powerCount);
//		else 
//			createdTower = factory.getTower(towertype, TowerLevel.three);
			
		long delta = createdTower.cost() - value;
		if (delta < bank.getBalance() - bank.getCurrentBalance()) {
			bank.setCurrentBalance(delta);
			switch (level) {
			case one:
				createdTower.setLevel(TowerLevel.two);
				break;
			case two:
				createdTower.setLevel(TowerLevel.three);
				break;
			default:
				break;
			}
			return createdTower;
		} else {
			JOptionPane.showMessageDialog(new JFrame(),
					"you don't have enough money :(", "Alert",
					JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}

	/**
	 * <b>Closes the inspection panel.</b>
	 */
	public void close() {
		dialog.dispose();
	}
/**
 * 
 */
	private void closeInspector() {
		sendUpdateSignal();
		dialog.dispose();
	}
/**
 * 
 */
	private void sendUpdateSignal() {
		setChanged();
		notifyObservers();

	}

}
