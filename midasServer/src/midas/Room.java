package midas;

import java.util.Vector;

class Room {
	private String roomName;
	private int limitPerson;
	private int currentPerson;
	private int roomID;
	private Vector<User> EnteredUserVector;
	private PanelInformation panelInformation;
	private boolean isCreateFileFlag = false;
	private String fileName = "";
	public Room(String _roomName, int _roomID, int _currentPerson, int _limitPerson ) {
		roomName = _roomName;
		limitPerson = _limitPerson;
		currentPerson = _currentPerson;
		roomID = _roomID;
		EnteredUserVector = new Vector<User>();
		panelInformation = new PanelInformation("");
	}
	
	public void SetRoomName(String _roomName) {
		roomName = _roomName;
	}
	
	public String GetRoomName() {
		return roomName;
	}
	
	public void SetLimitPerson(int _limitPerson) {
		limitPerson = _limitPerson;
	}
	
	public int GetLimitPerson() {
		return limitPerson;
	}
	
	public void SetCurrentPerson(int _currentPerson) {
		currentPerson = _currentPerson;
	}
	
	public int GetCurrentPerson() {
		return currentPerson;
	}
	
	public void SetRoomID(int _roomID) {
		roomID = _roomID;
	}
	
	public int GetRoomID() {
		return roomID;
	}
	
	public void EnterThisRoom(User user) {
		EnteredUserVector.add(user);
		currentPerson++;
	}
	
	public void ExitThisRoom(int i) {
		EnteredUserVector.remove(i);
		currentPerson--;
		
	}
	
	public User GetEnteredUser(int i) {
		return EnteredUserVector.elementAt(i);
	}
	
	public boolean isEmpty() {
		return EnteredUserVector.isEmpty();
	}
	
	public PanelInformation GetPanelInfo() {
		return panelInformation;
	}
	
	public void SetPanelInfo(PanelInformation info) {
		panelInformation = info;
	}
	
	public boolean IsCreateFile() {
		return isCreateFileFlag;
	}
	
	public void SetCreateFileFlag(boolean _flag) {
		isCreateFileFlag = _flag;
	}
	
	public void SetFileName(String name) {
		fileName = name;
	}
	
	public String GetFileName() {
		return fileName;
	}
	
}
