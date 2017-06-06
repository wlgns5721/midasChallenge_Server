package midas;

import java.net.Socket;

class User {
	private Socket socket;
	private String nickname;
	private int id;
	private int port;
	private boolean isConnected;
	private int enteredRoomID;
	private int roomID = -1;
	public User(Socket _socket, String _nickname, int _id, int _port) {
		socket = _socket;
		nickname = _nickname;
		id = _id;
		port = _port;
		isConnected = true;
	}
	
	public int GetUserID() {
		return id;
	}
	
	public void SetUserID(int _id) {
		id = _id;
	}
	
	public Socket GetSocket() {
		return socket;
	}
	
	public int GetEnteredRoomID() {
		return enteredRoomID;
	}
	
	public void SetEnteredRoomID(int roomID) {
		enteredRoomID = roomID;
	}
	
	
}
