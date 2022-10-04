package Blockchain;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.management.InvalidAttributeValueException;
import transactions.Transaction;
import miners.*;


public class Blockchain {
	private List<Block> blockchain ;
	//
	private List<Transaction> transactions; ///////////////
	public Blockchain() {
		this.blockchain = new ArrayList<>();
	}
	public Blockchain(Blockchain blockchain) {
		for (Block b: blockchain.blockchain) {
			this.blockchain.add(b);
		}
	}
//	private boolean verifyTransactions(ArrayList<Transaction> transactions) throws InvalidKeyException,
//	NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//		int nb = 0;
//		for (int i = 0; i < transactions.size(); i++) {
//			if (transactions.get(i).getReceiver().verify(transactions.get(i))) {
//				nb++;
//			}
//		}
//			return nb == transactions.size();
//	}
	public void addBlock() throws InvalidAttributeValueException, InterruptedException, NoSuchAlgorithmException,
	InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//		System.out.println("Waiting for incoming transactions...");
//		ArrayList<Transaction> transactions = Transaction.bunchOfTransactions();
//		System.out.println("Verifying their validity...");
//		Thread.sleep(2000);
////		if (!verifyTransactions(transactions))
////			return;
//		String data = transactions.toString();
//		
//		if (this.getLastBlock() != null && data.equals(this.getLastBlock().getData())) {
//			throw new InvalidAttributeValueException("ERROR: Block is a duplicate");
//		}
//		int id = this.getLastBlock() == null ? 0 : this.getLastBlock().getId();
//		Block block = new Block(id +1,data,this.getLastBlock().getHash(),LocalDateTime.now().toString());
//		System.out.println("\n\n\n\n##### New block to add #####");
//		System.out.println(block);
//		Miners.mine(block);
//		if (!Miner.isValiidHash(block.getHash())) {
//			System.err.println("Block not valid, not added");
//			return;
//		}
//		System.out.println("Adding block to the blockchain");
//		Thread.sleep(2000);
//		this.blockchain.add(block);
}
	@Override
	public String toString() {
		if(this.blockchain.size() == 0) {
			return "This blockchain is empty";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("\nBlockchain:\n");
		for(Block b : this.blockchain) {
			sb.append(b);
		}
		return sb.toString() + "\n\n";
	}
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Blockchain)) {
			return false;
		}
		Blockchain blockchain = (Blockchain) o;
		return this.getLastBlock().equals(blockchain.getLastBlock());
	}

	public Block getLastBlock() {
		return this.blockchain.get(blockchain.size()-1);
	}
	public void addTransaction(Transaction transaction) {
		
	}
}
