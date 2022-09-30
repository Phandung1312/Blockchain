package resources;

import java.security.MessageDigest;

public class SHA256Hash {
	private SHA256Hash() {
	}
	public static String hash(String data) {
		String res = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes("UTF-8"));
			return bytesToHex(hash);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	private static String bytesToHex(byte[] hash) {
		StringBuilder sb = new StringBuilder();
		for (byte aByte : hash) {
			sb.append(String.format("%02x", aByte));
		}
		return sb.toString();
	}
}

