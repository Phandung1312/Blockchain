package miners;

import Blockchain.Block;

public class Miner {
	private String name;
	private Block block;
	private static int  prefix = 4;
	public Miner(String name) {
		this.name = name;
	}
	public long getNonce(long nonce) {
		long n = nonce;

		long max_it = Math.round(100 * Math.random());
		int i = 0;
		while (i < max_it && !Block.isValidHash(block.getHash(),prefix)) {
			n++;
			this.block.setNonce(n);
			i++;
		}

		return n;
	}
	public boolean verifyNonce(long nonce) {
		block.setNonce(nonce);
		return Miner.isValiidHash(block.getHash());
	}
	public static boolean isValiidHash(String hash) {
		String prefixString = new String(new char[prefix]).replace('\0', '0');
		if (hash == null || hash.length() == 0) {
			return false;
		}
		if(hash.substring(0,prefix).equals(prefixString)) return true;
		else return false;
	}
	public void setBlock(Block block) {
		this.block = block;
	}
	public String getName() {
		return name;
	}
}

