package miners;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Blockchain.Block;

public class Miners {
	private static Set<Miner> miners = new HashSet<>();
	private static int nbMiners = 5;

	private Miners() {
	}
	private static void init() {
		for (int i = 0; i < nbMiners; i++) {
			miners.add(new Miner("Miner" + Math.round(1000 * Math.random())));
		}
	}
	private static Miner getMiner() {
		Iterator<Miner> it = getMiners().iterator();
		return it.next();
	}
	private static void shareBlock(Block block) {
		System.out.println("Sharing block with miners...");
		Iterator<Miner> it = getMiners().iterator();
		Miner m;
		while (it.hasNext()) {
			m = it.next();
			m.setBlock(new Block(block));
		}
	}

	private static void findNonce(Block block) throws InterruptedException {
		long nonce = getMiner().getNonce(0);
		block.setNonce(nonce);
		Miner m = null;
		while (Miner.isValiidHash(block.getHash())) {
			Iterator<Miner> it = getMiners().iterator();
			while (it.hasNext() && !Miner.isValiidHash(block.getHash())) {
				m = it.next();
				nonce = m.getNonce(nonce);
				block.setNonce(nonce);
			}
		}

		if (m != null) {
			System.out.println(m.getName() + " has found a nonce!");
			Thread.sleep(2000);
			System.out.println("Verifying nonce...");
			Thread.sleep(1000);
		}
	}
	private static boolean verifyBlock(long nonce) {
		int nb = 0;
		Iterator<Miner> it = getMiners().iterator();
		while (it.hasNext()) {
			Miner m = it.next();
			nb = m.verifyNonce(nonce) ? nb + 1 : nb;
		}
		return nb / nbMiners > 1 / 2;
	}
	public static void mine(Block block) throws InterruptedException {
		shareBlock(block);
		System.out.print("Miners are mining block " + block.getId() + "...");
		Thread.sleep(4000);
		while (!verifyBlock(block.getNonce())) {
			findNonce(block);
		}
	}
	public static Set<Miner> getMiners() {
		if (miners.isEmpty())
			init();

		return miners;
	}

}
