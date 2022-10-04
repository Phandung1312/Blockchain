package transactions;

import java.net.Socket;

public class UserServerThread extends Thread {
	private	User user;
	private Socket socket;
	
	public UserServerThread(User user, Socket socket) {
		this.user = user;
		this.socket = socket;
	}
	@Override
	public void run() {
		//dis, dos, obj
		// swith case cmd
		
	}

	
	
	
}
