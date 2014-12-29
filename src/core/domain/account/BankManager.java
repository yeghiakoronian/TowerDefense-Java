package core.domain.account;

import core.contract.AccountConstants;
/**
 * <b>for implementing this class we used singleton pattern for managing our players' moneys balance</b>
 * @author Team5
 *
 */
public class BankManager implements java.io.Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = -54897009666121452L;
	
	
	private static BankManager instance = null;
	public static long balance;
	public static long currentBalance;
	
	// Exists only to defeat instantiation.
	public BankManager() {
		
	}
	/**
	 * <b>it will be make a instance from this class if there is not any object of this class in memory</b>
	 * <code>
	 * if(instance == null) {
	 *		instance = new BankManager();
	 *		balance = AccountConstants.DEFAULT_BALANCE;
	 *		currentBalance = 0;
	 *	}
	 *  return instance;
	 * </code>
	 * 
	 * @return BankManager 
	 */
	public static BankManager getInstance() {
		if(instance == null) {
			instance = new BankManager();
			balance = AccountConstants.DEFAULT_BALANCE;
			currentBalance = 0;
		}
		return instance;
	}
	/**
	 * <b>this getter can get the total money that player can spend during game</b>
	 * @return long,
	 */	
	public long getBalance() {
		return balance;
	}
	
	public void setBalance(long value) {
		balance = value;
	}
	/**
	 * <b>this method can add the extra money that player can add to the their amount of money</b>
	 * @param balance money added to user's balance
	 */
	public void addBalance(long balance) {
		BankManager.balance += balance;
	}
	/**
	 * <b>this method can get the current amount of moneys that player spend until now</b>
	 * @return long
	 */
	public long getCurrentBalance() {
		return currentBalance;
	}
	/**
	 * <b>this method can get the new tower cost or other cost and added to the amount of
	 *  money that were spent until now</b>
	 * @param currentBalance as long that represents a tower cost 
	 */
	public void setCurrentBalance(long currentBalance) {
		BankManager.currentBalance += currentBalance;
	}
	/**
	 * this method make the total amount that have been used by player to zero
	 */
	public void resetCurrentBalance() {
		BankManager.currentBalance = 0;
		
	}
}