package Blockchain;

import java.util.Objects;

import Blockchain.Block;
import resources.SHA256Hash;

public class Block {
	private int id;
	private long nonce;
	private String data;
	private String timeStamp;
	private String hash;
	private String previousHash;
	public Block(int id,String data,String previousHash, String timeStamp) {
		this.id = id;
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = timeStamp;
	}
	public Block(Block block) {
		this.id = block.getId();
		this.nonce = block.getNonce();
		this.timeStamp = block.getTimeStamp();
		this.data = block.getData();
		this.previousHash = block.getPreviousHash();
		this.hash = block.getHash();
	}
	public String concatBlock() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(this.nonce);
		sb.append(this.timeStamp);
		sb.append(this.data);
		sb.append(this.previousHash);
		return sb.toString();
	}
	public static boolean isValidHash(String hash,int prefix) {
		String prefixString = new String(new char[prefix]).replace('\0', '0');
		if (hash == null || hash.length() == 0) {
			return false;
		}
		if(hash.substring(0,prefix).equals(prefixString)) return true;
		else return false;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Block)) {
			return false;
		}
		Block block = (Block) obj;
		return nonce == block.getNonce() && Objects.equals(data, block.data)
				&& Objects.equals(hash, block.hash) && Objects.equals(previousHash, block.previousHash);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\tid: " + getId() + "\n");
		sb.append("\tnonce: " + getNonce() + "\n");
		sb.append("\ttimeStamp: " + getTimeStamp() + "\n");
		sb.append("\tdata: " + getData() + "\n");
		sb.append("\tprevious: " + getPreviousHash() + "\n");
		sb.append("\thash: " + getHash() + "\n},\n");
		return sb.toString();
	}
	public String getHash() {
		return SHA256Hash.hash(this.concatBlock());
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getNonce() {
		return nonce;
	}
	public void setNonce(long nonce) {
		this.nonce = nonce;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	
}
