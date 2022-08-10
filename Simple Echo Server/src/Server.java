import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
	
	public static int port;
	public static Scanner scan = new Scanner(System.in);
	public static String user;

	public static void main(String[] args) throws IOException {
		
		System.out.print("Host Server On Port : ");
		port = scan.nextInt();
		
		System.out.println("Server Is Up On 'localhost:" + port + "'");
		
		ServerSocket server = new ServerSocket(port);
		Socket client = server.accept();
		
		user = (((InetSocketAddress) client.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
		
		if (user == "127.0.0.1") {
			
			user = "localhost";
			
		}
		
		System.out.println(user + " Connected");
		
		while (true) {
			
			if (client.isClosed()) {
				
				client = server.accept();
				
			} else if (client.isConnected()) {
				
				InputStreamReader isr = new InputStreamReader(client.getInputStream());
				BufferedReader br = new BufferedReader(isr);
					
				if (br.readLine().contains("~quit")) {
					
					try {
						
						client.close();
						System.out.println(user + " Disconnected");
						
					} catch (Exception e) {
						
						System.out.println(user + " Disconnected With An Error");
						
					}
				
				}
				
				if (br.readLine() != null) {
					
					System.out.println(user + " : " + br.readLine());
					
				}
				
			} else {
				
				//null
				
			}
			
		}
		
	}

}
