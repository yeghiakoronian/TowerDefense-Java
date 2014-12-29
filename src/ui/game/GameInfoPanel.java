package ui.game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.Constants;
import core.contract.AccountConstants;
/**
 * 
 * @author Team5
 * <b>This Class builds the GUI our game , once game is loaded
 * it will show the icons , such as life ,start wave,gold</b>
 */
public class GameInfoPanel extends JPanel {

	private JLabel lblLife;
	private JLabel lblBank;
	private JLabel lblWave;
	private GridBagConstraints gbc_lblWave;
	private int waveNum;

	/**
	 * 
	 * @return label wave
	 */
	public JLabel getLblWave() {
		return lblWave;
	}
	/**
	 * 
	 * @param lblWave label for wave
	 */
	public void setLblWave(JLabel lblWave) {
		this.lblWave = lblWave;
	}

	/**
	 * Create the panel. which is the basic GUI displayed on the screen
	 * Wave Icon ,Life Icon Bank Icon
	 */
	public GameInfoPanel() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file;
		file = new File(classLoader.getResource(Constants.WAVE_ICON).getFile());
		ImageIcon waveIcon = new ImageIcon(file.getPath());

		file = new File(classLoader.getResource(Constants.LIFE_ICON).getFile());
		ImageIcon lifeIcon = new ImageIcon(file.getPath());

		file = new File(classLoader.getResource(Constants.BANK_ICON).getFile());
		ImageIcon bankIcon = new ImageIcon(file.getPath());


		Dimension dim = getPreferredSize();
		dim.height = 63;
		setPreferredSize(new Dimension(273, 75));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblImage = new JLabel(waveIcon);
		GridBagConstraints gbc_lblImage = new GridBagConstraints();
		gbc_lblImage.insets = new Insets(0, 0, 5, 5);
		gbc_lblImage.gridx = 0;
		gbc_lblImage.gridy = 0;
		add(lblImage, gbc_lblImage);

		JLabel lblImage_1 = new JLabel(lifeIcon);
		GridBagConstraints gbc_lblImage_1 = new GridBagConstraints();
		gbc_lblImage_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblImage_1.gridx = 2;
		gbc_lblImage_1.gridy = 0;
		add(lblImage_1, gbc_lblImage_1);

		JLabel lblImage_2 = new JLabel(bankIcon);
		GridBagConstraints gbc_lblImage_2 = new GridBagConstraints();
		gbc_lblImage_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblImage_2.gridx = 4;
		gbc_lblImage_2.gridy = 0;
		add(lblImage_2, gbc_lblImage_2);

		if(waveNum != 0){
			lblWave = new JLabel();
			Integer i  = new Integer(waveNum + 1);
			lblWave.setText(i.toString());
		}else{
			lblWave = new JLabel("Wave");
		}
		gbc_lblWave = new GridBagConstraints();
		gbc_lblWave.insets = new Insets(0, 0, 0, 5);
		gbc_lblWave.gridx = 0;
		gbc_lblWave.gridy = 1;
		add(lblWave, gbc_lblWave);

		lblLife = new JLabel(new Integer(AccountConstants.DEFAULT_LIFE).toString());
		GridBagConstraints gbc_lblLife = new GridBagConstraints();
		gbc_lblLife.insets = new Insets(0, 0, 0, 5);
		gbc_lblLife.gridx = 2;
		gbc_lblLife.gridy = 1;
		add(lblLife, gbc_lblLife);

		lblBank = new JLabel(new Integer(AccountConstants.DEFAULT_BALANCE).toString());
		GridBagConstraints gbc_lblBank = new GridBagConstraints();
		gbc_lblBank.gridx = 4;
		gbc_lblBank.gridy = 1;
		add(lblBank, gbc_lblBank);
	}
	/**
	 * 
	 * @param life set amount of life
	 */
	public void setLife(int life) {
		lblLife.setText(new Integer(life).toString());
		repaint();
	}
	
	/**
	 * 
	 * @return the amount of life left
	 */
	public int getLife() {
		return Integer.parseInt(lblLife.getText());
	}
	
	/**
	 * 
	 * @param availFunds amount of a bank 
	 */
	public void setBank(long availFunds) {
		lblBank.setText(new Long(availFunds).toString());
	}
	/**
	 * 
	 * @return the current amount of a bank
	 */
	public long getBank() {
		return Long.parseLong(lblBank.getText());
	}

	
	/**
	 * 
	 * @param waveNum the wave number 
	 */
	public void setWave(int waveNum) {
		lblWave.setText("Wave: "+ new Integer(waveNum).toString());
		this.waveNum = waveNum;
		repaint();
	}
	
	/**
	 * 
	 * @return wave number
	 */
	public int getWave(){
		return waveNum;
	}



}
