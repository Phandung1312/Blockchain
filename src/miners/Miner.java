package miners;



import Blockchain.Block;

public class Miner {
	private String name;
	private Block block;
	private static int  prefix = 4;// check 4 mã hash đầu tiên
	public Miner(String name) {
		this.name = name;
	}
	public long getNonce(long nonce) {
		long n = nonce;

//		long max_it = Math.round(100 * Math.random());
//		int i = 0;
//		while (i < max_it && !Block.isValidHash(block.getHash(),prefix)) {
//			n++;
//			this.block.setNonce(n);
//			i++;
//		}

		return n;
	}
	public boolean verifyNonce(long nonce) {
		block.setNonce(nonce);
		return Miner.isValiidHash(block.getHash());
	}
	public static boolean isValiidHash(String hash) {
//		String prefixString = new String(new char[prefix]).replace('\0', '0');
//		if (hash == null || hash.length() == 0) {
//			return false;
//		}
//		if(hash.substring(0,prefix).equals(prefixString)) return true;
//		else return false;
		return true;
	}
	public void setBlock(Block block) {
		this.block = block;
	}
	public String getName() {
		return name;
	}
	private static void shareBlock(Block block) {
//		System.out.println("Sharing block with miners...");
//		Iterator<Miner> it = getMiners().iterator();
//		Miner m;
//		while (it.hasNext()) {
//			m = it.next();
//			m.setBlock(new Block(block));
//		}
	}

	private static void findNonce(Block block) throws InterruptedException {
//		long nonce = getMiner().getNonce(0);
//		block.setNonce(nonce);
//		Miner m = null;
//		while (Miner.isValiidHash(block.getHash())) {
//			Iterator<Miner> it = getMiners().iterator();
//			while (it.hasNext() && !Miner.isValiidHash(block.getHash())) {
//				m = it.next();
//				nonce = m.getNonce(nonce);
//				block.setNonce(nonce);
//			}
//		}
//
//		if (m != null) {
//			System.out.println(m.getName() + " has found a nonce!");
//			Thread.sleep(2000);
//			System.out.println("Verifying nonce...");
//			Thread.sleep(1000);
//		}
	}
	private static boolean verifyBlock(long nonce) {
//		int nb = 0;
//		Iterator<Miner> it = getMiners().iterator();
//		while (it.hasNext()) {
//			Miner m = it.next();
//			nb = m.verifyNonce(nonce) ? nb + 1 : nb;
//		}
//		return nb / nbMiners > 1 / 2;
		return true;
	}
	public static void mine(Block block) throws InterruptedException {
//		shareBlock(block);
//		System.out.print("Miners are mining block " + block.getId() + "...");
//		Thread.sleep(4000);
//		while (!verifyBlock(block.getNonce())) {
//			findNonce(block);
//		}
	}
}

