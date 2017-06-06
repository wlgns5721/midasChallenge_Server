package midas;

import java.text.DecimalFormat;
import java.util.Vector;

class RoomManager {
	private Vector<Room> roomVector;
	private int roomIDCount;      //새로 생성할 방에 할당할 ID
	public RoomManager() {
		roomVector = new Vector<Room>();
		roomIDCount = 0;
	}
	public String MakeRoomInfo() {
		StringBuilder sb = new StringBuilder("");
		DecimalFormat df = new DecimalFormat("00000");
		String numberOfRoom = df.format(roomVector.size());
		sb.append(numberOfRoom);
			 
		for(int i=0; i<roomVector.size(); i++) {
			Room room = roomVector.elementAt(i);
			df = new DecimalFormat("000");
			
			String roomNameLength = df.format(room.GetRoomName().getBytes().length);
			df = new DecimalFormat("00000");
			String roomIDLength = df.format(Integer.toString(room.GetRoomID()).length());
			
			//방이름길이(000) + 방 id 길이(00000) + 방 이름 + 방 id + 인원(0) + 인원제한(0)
			 
			String roomInfo = roomNameLength + roomIDLength + room.GetRoomName() + room.GetRoomID() + 
					Integer.toString(room.GetCurrentPerson()) + Integer.toString(room.GetLimitPerson());
			sb.append(roomInfo);
		}
		
		//최종적으로 방 갯수(00000) + 방이름길이(000) + 방 id 길이(00000) + 방 이름 + 방 id + 인원(0) + 인원제한(0)
		//형태의 String을 만든다.
		return sb.toString();
		 
	}
	
	public Room GetRoomElement(int index) {
		return roomVector.elementAt(index);
	}
	
	public int GetRoomVectorSize() {
		return roomVector.size();
	}
	
	public void AddRoom(Room room) {
		roomVector.add(room);
	}
	
	public int AllocateID() {
		roomIDCount++;
		return roomIDCount;
	}
	
	public void DeleteRoom(int i) {
		roomVector.remove(i);
	}
}
