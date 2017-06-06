package midas;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.Vector;


class Server {
	private final int PORT_MAX_NUMBER = 51000;
	private final int PORT_FIRST_SOCKET = 50000;    
	private final int PORT_EXTERNAL_PORT_NUMBER = 52000; 
	private final int NEW_ENTERED_USER = 0;
	private final int ALL_USERS = 1;
	
	private static Vector<User> WaitUserVector;
	private Vector<Integer> UsablePort;
	private int portNumber;
	private int clientIDCount; //클라이언트에게 부여할 id
	private RoomManager roomManager;
	
	
	/**
	 * 서버 초기화
	 */
	void ExecuteServer() {
		UsablePort = new Vector<Integer>();
		
		//사용가능한 포트번호 벡터에 50000~51000을 넣는다.
		for(int i=PORT_FIRST_SOCKET; i<=PORT_MAX_NUMBER; i++)
			UsablePort.add(i);
		
		//client에게 부여할 id는 1부터 시작한다. client가 자신의 id가 0이라고 한다면, 신규 클라이언트
		clientIDCount = 1;
		WaitUserVector = new Vector<User>();
		roomManager = new RoomManager();
		
		ConnectWithClientThread thread = new ConnectWithClientThread();
		thread.start();
	}
	/**
	 * 서버를 열고, 클라이언트의 연결을 처리한다.
	 */
	//client와 연결을 한다. 연결이 되는 동시에 클라이언트로부터 nickname, ip정보를 받는다.
	void ConnectWithClient() {
		while (true) {
		ServerSocket firstServerSocket;  //모든 클라이언트가 첫번째로 접속하는 서버소켓 
		ServerSocket secondServerSocket; //각 클라이언트마다 할당되는 두번째 소켓
		String nickname;
		
		try {
			//사용가능한 포트가 없다면 대기한다.
			while(UsablePort.isEmpty());
			
			//우선 52000포트로 연결이 성립이 된다면 클라이언트에게 통신을 위한 포트 번호를 알려준다.
			firstServerSocket = new ServerSocket(PORT_EXTERNAL_PORT_NUMBER);
			System.out.println("processing connect to client.......");
			Socket firstSocket = firstServerSocket.accept();
			
			//UsablePort의 마지막 값 pop
			portNumber = UsablePort.lastElement();
			UsablePort.remove(UsablePort.size()-1);
			
		  	String portNum_str = Integer.toString(portNumber);
  
        	//각 클라이언트에게 두번째 포트번호를 알려준다.
        	SendData(firstSocket,portNum_str,3);    	
        	System.out.println("allocate new port to client.....port number :"+portNumber);
			
			secondServerSocket = new ServerSocket(portNumber);
			Socket secondSocket = secondServerSocket.accept();
			
			System.out.println("second socket conneted!");
			
			//첫번째 소켓은 연결을 끊는다.
			firstServerSocket.close();
			
			//client로부터 nickname을 전달받는다.
			nickname = ReceiveData(secondSocket,3);
			
            //대기 백터에 저장
			User user = new User(secondSocket,nickname,clientIDCount,portNumber);
        	WaitUserVector.add(user);
        	
        	
        	//새로운 클라이언트에게 새로운 id를 할당한다.
        	String id_str = Integer.toString(clientIDCount);
        	SendData(secondSocket,id_str,5);
        	
        	//client_id값을 증가시켜 다음 클라이언트에게는 다른 아이디를 부여하도록 한다.
        	clientIDCount++;
        
            System.out.println("Success!");
            
            //이제부터 WaitUser로부터 data를 받는다.
            ReceiveFromWaitUsersThread thread = new ReceiveFromWaitUsersThread(secondSocket,user);
            thread.start();
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	/**
	 * 서버에서 처리하는 작업들
	 */
	public void ServerTask() {
		
		
		
		
	}
	
	/**
	 * 대기방에 있는 유저들에게 방 정보를 보낸다.
	 */

	
	/**
	 * 소켓을 통해 Data를 수신하는 함수
	 * @param socket
	 * @param format_length
	 * @return
	 */
	public String ReceiveData(Socket socket, int format_length) {
		try {
			byte arrlen[] = new byte[format_length];
	        InputStream in;
	
			in = socket.getInputStream();
			
			if(in.read(arrlen)==-1)
				return null;
	        int data_len = Integer.parseInt(new String(arrlen));
	        byte textBuffer[] = new byte[data_len];
	        if(in.read(textBuffer)==-1)
	        	return null;
	        String str = new String(textBuffer);
	        return str;  
		} 
		catch (SocketException e) {
			System.out.println("socket closed");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	/**
	 * 소켓을 통해 Data를 보내는 함수
	 * @param socket
	 * @param text
	 * @param format_length
	 */
	public void SendData(Socket socket,String text, int format_length) {
		BufferedWriter out;
		try {
			StringBuilder sb = new StringBuilder("");
			for(int i=0; i<format_length; i++)
				sb.append("0");
			String format = sb.toString();
			DecimalFormat df = new DecimalFormat(format);
			String text_len = df.format(text.getBytes().length);
			String data = text_len + text;
			out = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
			out.write(data);
	        out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * room 관련 처리를 담당하는 Thread
	 * @author Ji Hoon
	 *
	 */
	
	
	class ConnectWithClientThread extends Thread {
		public void run() {
			ConnectWithClient();
		}
	}
	
	class ReceiveFromWaitUsersThread extends Thread {
		private Socket socket;
		private User user;
		
		public ReceiveFromWaitUsersThread(Socket _socket, User _user) {
			socket = _socket;
			user = _user;
		}
		
		public Room GetEnteredRoom() {
			Room enteredRoom = null;
			for(int i=0; i<roomManager.GetRoomVectorSize(); i++) {
				if(user.GetEnteredRoomID()==roomManager.GetRoomElement(i).GetRoomID()) {
					enteredRoom = roomManager.GetRoomElement(i);
					return enteredRoom;
				}
			}
			return null;
		}
		public void run() {
			while(true) {
				String receivedData = ReceiveData(socket,3);
				if(receivedData==null) {
					return;
				}
					
				if(receivedData.contains("makeroom")) {
					MakeRoom(receivedData);
				}
				
				else if(receivedData.contains("requestRoomInfo")) {
					String roomInfo = roomManager.MakeRoomInfo();
					SendData(socket,roomInfo,3);
				}
				
				else if(receivedData.contains("enterroom")) {
					//명령어 뒤에 방id가 나온다.
					EnterRoom(receivedData);	
				}
				
				else if(receivedData.contains("makearrow")) {
					MakeArrow(receivedData);
				}
				
				else if(receivedData.contains("makeclass")) {
					MakeClass(receivedData);
				}
				
				else if(receivedData.contains("exitroom")) {
					ExitRoom(receivedData);
				}
				
				else if(receivedData.contains("createfile")) {
					CreateFile(receivedData);
				}
				
				else if(receivedData.contains("loadfile")) {
					LoadFile();
				}
				
			}
		}
		public void MakeRoom(String receivedData) {
			//ReceivedData = "makeroom" + 방제목 + 인원제한
			String roomName = receivedData.substring(8,receivedData.length()-1);
			int limitPerson = Integer.parseInt(receivedData.substring(receivedData.length()-1));
			Room room = new Room(roomName,roomManager.AllocateID(),0,limitPerson);
			for(int i=0; i<WaitUserVector.size(); i++) {
				if(user.GetUserID()==WaitUserVector.elementAt(i).GetUserID()) {
					user.SetEnteredRoomID(room.GetRoomID());
					room.EnterThisRoom(user);
					roomManager.AddRoom(room);
					WaitUserVector.remove(i);     //대기 리스트에서 지운다.
					break;
				}
			}
		}
		
		public void EnterRoom(String receivedData) {
			int userIndex = -1;
			int roomID = Integer.parseInt(receivedData.substring(11));
			for(int i=0; i<WaitUserVector.size(); i++) {
				//waitUserVector에서 해당 유저를 찾았을 경우 입장하고자 하는 room을 찾는다.
				int temp = WaitUserVector.elementAt(i).GetUserID();
				if(temp==user.GetUserID()) {
					userIndex = i;
					System.out.println("find");
					break;
				}
				else if(i==WaitUserVector.size()-1) {
					System.out.println("enter error1");
					return;
				}
			}
			for(int i=0; i<roomManager.GetRoomVectorSize(); i++) {
				//방을 찾은 후에 해당 방에 입장
				if(roomManager.GetRoomElement(i).GetRoomID()==roomID) {
					Room room = roomManager.GetRoomElement(i);
					
					//만약 꽉 찼을 경우는 들어갈 수 없다.
					if(room.GetCurrentPerson()>=room.GetLimitPerson()) {
						System.out.println("full room");
						SendData(socket,"no",3);
						break;
					}
					if(userIndex==-1) {
						System.out.println("enter error2");
						break;
					}
					System.out.println("welcome");
					SendData(socket,"ok",3);
					user.SetEnteredRoomID(room.GetRoomID());
					roomManager.GetRoomElement(i).EnterThisRoom(user); //해당 방에 입장
					WaitUserVector.remove(userIndex);   //대기유저벡터에서 삭제
					
					if(room.IsCreateFile()==true) {
						//File을 생성했을 경우,
						//이전까지 작업했던 정보들을 새로 들어온 유저에게 전달한다.
						LoadData(room,NEW_ENTERED_USER);
					}
				}
			}
		
		}
		
		public void MakeArrow(String receivedData) {

			//makearrow + 정보 
			ObjectInputStream ois = null;
			ObjectOutputStream oos = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				//여기에 arrow관련 객체를 넣는 부분을 작성한다.
				
				RelationshipArrow arrowObject = (RelationshipArrow)ois.readObject();
				
				//해당 유저와 같은 방에 있는 모든 유저에게 뿌린다.
				Room enteredRoom = GetEnteredRoom();
				if(enteredRoom==null)
					return;
				enteredRoom.GetPanelInfo().addRelationshipArrow(arrowObject);
				
				for(int i=0; i<enteredRoom.GetCurrentPerson(); i++) {
					User sameRoomUser = enteredRoom.GetEnteredUser(i);
					//우선 어떤 object인지 알려준다.
					SendData(sameRoomUser.GetSocket(),"ArrowObject",3);
					//Object를 보낸다.
					oos = new ObjectOutputStream(sameRoomUser.GetSocket().getOutputStream());
					
					//arrow 관련 객체를 같은 방에 있는 모든 유저에게 뿌린다.
					oos.writeObject(arrowObject);
					 
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void MakeClass(String receviedData) {
			ObjectInputStream ois = null;
			ObjectOutputStream oos = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ClassObject classObject = (ClassObject)ois.readObject();
				
				//해당 유저와 같은 방에 있는 모든 유저에게 뿌린다.
				Room enteredRoom = GetEnteredRoom();
				if(enteredRoom==null)
					return;
				enteredRoom.GetPanelInfo().addClassObject(classObject);
				
				for(int i=0; i<enteredRoom.GetCurrentPerson(); i++) {
					User sameRoomUser = enteredRoom.GetEnteredUser(i);
					//우선 어떤 object인지 알려준다.
					SendData(sameRoomUser.GetSocket(),"ClassObject",3);
					//Object를 보낸다.
					oos = new ObjectOutputStream(sameRoomUser.GetSocket().getOutputStream());
					oos.writeObject(classObject);
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void ExitRoom(String receivedData) {
			Room enteredRoom = null;
			int enteredRoomIndex = -1;
			int roomVectorSize = roomManager.GetRoomVectorSize();
			
			for(int i=0; i<roomVectorSize; i++) {
				//방을 찾는다
				if(roomManager.GetRoomElement(i).GetRoomID()==user.GetEnteredRoomID()) {
					enteredRoom = roomManager.GetRoomElement(i);
					enteredRoomIndex = i;
					break;
				}
				else if(roomManager.GetRoomVectorSize()-1==i) {
					System.out.println("exit error");
					return;	
				}
			}
			for(int i=0; i<enteredRoom.GetCurrentPerson(); i++) {
				//방 안의 유저를 찾아서 방을 나가게 한다.
				int userID = enteredRoom.GetEnteredUser(i).GetUserID(); 
				if(userID==user.GetUserID()) {
					enteredRoom.ExitThisRoom(i);
					//모든 유저가 다 나갈 경우 방을 삭제한다.
					if(enteredRoom.isEmpty())
						roomManager.DeleteRoom(enteredRoomIndex);
					System.out.println("User ID : "+user.GetUserID() + " disconnect");
					break;
				}
				else if(i==roomVectorSize-1) {
					System.out.println("can't find user");
				}
			}
			
		}
		
		public void CreateFile(String receivedData) {
			Room enteredRoom = GetEnteredRoom();
			enteredRoom.SetCreateFileFlag(true);
			enteredRoom.SetFileName(receivedData.substring(10));
			for(int i=0; i<enteredRoom.GetCurrentPerson();i++) {
				//새 파일이 열렸음을 알려준다.
				User sameRoomUser = enteredRoom.GetEnteredUser(i);
				SendData(sameRoomUser.GetSocket(),"createfile"+enteredRoom.GetFileName(),3);
				
			}
		}
		/**
		 * 파일을 로드했을 때의 루틴
		 */
		public void LoadFile() {
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				PanelInformation panelInformation  = (PanelInformation)ois.readObject();
				Room room = null; 
				//해당 ID를 가지고 있는 방을 찾는다.
				for(int i=0; i<roomManager.GetRoomVectorSize(); i++) {
					room = roomManager.GetRoomElement(i);
					if(room.GetRoomID()==user.GetEnteredRoomID()) {
						room.SetPanelInfo(panelInformation);
						room.SetCreateFileFlag(true);
						break;
					}
				}
				LoadData(room,ALL_USERS);
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void LoadData(Room room, int flag) {
			//loaddata + 파일 생성 여부 + 클래스다이어그램 수 + 관계 수 + 파일 이름 + 클래스다이어그램 + 관계
			StringBuilder sb = new StringBuilder("");
			sb.append("loaddata");
			if(room.IsCreateFile())
				sb.append("1");
			else
				sb.append("0");
			DecimalFormat df = new DecimalFormat("000");
			String class_num = df.format(room.GetPanelInfo().getClassList().size());
			String arrow_num = df.format(room.GetPanelInfo().getRelationshipArrowList().size());
			sb.append(class_num);
			sb.append(arrow_num);
			sb.append(room.GetFileName());
			int class_count = Integer.parseInt(class_num);
			int arrow_count = Integer.parseInt(arrow_num);
			
			
			
			try {
				if(flag==NEW_ENTERED_USER) {
					SendData(socket,sb.toString(),3);
					
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					for(int s=0; s<class_count; s++) {
						//Object를 보낸다.
						oos.writeObject(room.GetPanelInfo().getClassList().get(s));
					}
					
					for(int s=0; s<arrow_count; s++) {
						//arrow object를 보낸다.
						oos.writeObject(room.GetPanelInfo().getRelationshipArrowList().get(s));
					}
				}
				else if(flag==ALL_USERS) {
					for(int i=0; i<room.GetCurrentPerson();i++) {
						SendData(room.GetEnteredUser(i).GetSocket(),sb.toString(),3);
						
						ObjectOutputStream oos = new ObjectOutputStream(room.GetEnteredUser(i).GetSocket().getOutputStream());
						for(int s=0; s<class_count; s++) {
							//Object를 보낸다.
							oos.writeObject(room.GetPanelInfo().getClassList().get(s));
						}
						
						for(int s=0; s<arrow_count; s++) {
							//arrow object를 보낸다.
							oos.writeObject(room.GetPanelInfo().getRelationshipArrowList().get(s));
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
}


