import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public static String ip;
	public static int port;
	public static Scanner scan = new Scanner(System.in);
	public static Socket client;

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		System.out.print("Specify IP : ");
		ip = scan.nextLine();
		
		System.out.print("Specify Port : ");
		port = scan.nextInt();
		
		try {
			
			client = new Socket(ip, port);
			System.out.println("Successfully Connected To - " + ip + ":" + port);
			
		} catch (Exception e) {
			
			System.out.println("Connection Throttled - IOException - Disconnected");
			
		}
		
		PrintWriter pw = new PrintWriter(client.getOutputStream());
		
		while (true) {
			
			String message = scan.nextLine();
			
			for (int x = 0; x < 4; x++) {
				
				pw.println(message);
				pw.flush();
				
			}
			
			if (message.contains("~quit")) {
				
				pw.close();
				client.close();
				System.exit(0);
				
			}
			
		}

	}

}