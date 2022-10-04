package transactions;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import resources.SHA256Hash;

public class Transaction {
	private User receiver;
	private User sender;
	private double amount;
	private String hashedMessage;// ?
	private byte[] signature;
	public Transaction(User sender, User receiver, double amount)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
		this.hashedMessage = SHA256Hash.hash(this.toString());
		this.signature = sender.sign(this);
	}
//	//
//	public static ArrayList<Transaction> bunchOfTransactions() throws NoSuchAlgorithmException, InvalidKeyException,
//			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
//		for (int i = 0; i < 3; i++) {
//			User sender = new User("User" + Math.round(100 * Math.random()));
//			User receiver = new User("User" + Math.round(100 * Math.random()));
//			double amount = 1000 * Math.random();
//			transactions.add(new Transaction(sender, receiver, amount));
//		}
//		return transactions;
//	}
	public User getSender() {
		return sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public double getAmount() {
		return amount;
	}
	public String getHashedMessage() {
		return hashedMessage;
	}
	public byte[] getSignature() {
		return signature;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ " + this.getSender().getName() + ": " + this.getAmount() + " -> " + this.getReceiver().getName()
				+ " ]");
		return sb.toString();
	}
	public void isValid() {
		//
	}
}
