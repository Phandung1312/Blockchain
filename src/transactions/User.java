package transactions;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import resources.RSAKeyPairGenerator;

public class User {
	private String name;
	private PrivateKey privateAddress;
	private PublicKey publicAddress;
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

}
