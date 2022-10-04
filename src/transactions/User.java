package transactions;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Blockchain.Blockchain;
import resources.RSAKeyPairGenerator;

public class User {
	private String name;
	private PrivateKey privateAddress;
	private PublicKey publicAddress;
	private Blockchain blockchain;
	private ArrayList<User> peers;
	private ServerSocket serverSocket;
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
	private String address;
	private int port;
	private boolean listening;
	
	public Blockchain getBlockchain() {
		return blockchain;
	}
	public void setBlockchain(Blockchain blockchain) {
		this.blockchain = blockchain;
	}
	public ArrayList<User> getPeers() {
		return peers;
	}
	public void setPeers(ArrayList<User> peers) {
		this.peers = peers;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User(String name) throws NoSuchAlgorithmException {
		this.name = name;
		RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
		this.publicAddress = generator.getPublicKey();
		this.privateAddress = generator.getPrivateKey();
	}
	public byte[] sign(Transaction transaction)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, this.privateAddress);

		return cipher.doFinal(transaction.getHashedMessage().getBytes());
	}
	public boolean verify(Transaction transaction)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException {
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, transaction.getSender().getPublicAddress());
		String decrypted_hash = new String(cipher.doFinal(transaction.getSignature()));
		return decrypted_hash.equals(transaction.getHashedMessage());
	}
	public String getName() {
		return name;
	}
	public PublicKey getPublicAddress() {
		return publicAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void startHost(int port) {
		executor.execute(()->
		{
			try {
				serverSocket =  new ServerSocket(port);
				listening = true;
				while (listening) {
					new UserServerThread(this, serverSocket.accept()).start();
				}
				serverSocket.close();
			} catch (Exception e) {
			}
		});
		// broadcast :
	}
	public void stopHost() {
		listening = false;
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// ham de phan giai cu phap cua file json lay cmd va msg
	
	// ham de nghi gui/nhan tat ca block
	
	// ham de nghi gui/ nhan block;
	
	// ham gui/ nhan transaction
}
