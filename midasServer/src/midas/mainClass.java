package midas;

public class mainClass {
	public static void main(String[] args) {
		Server s = new Server();
		s.ExecuteServer();
		s.ServerTask();   //서버의 작업을 담당
	}
}
