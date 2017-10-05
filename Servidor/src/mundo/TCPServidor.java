package mundo;

import java.io.*;
import java.net.*;

public class TCPServidor {
	public static void main(String[] args){
		try{
			System.out.println("**INICIO**");
			TCPServidor tcpSer = new TCPServidor();
			System.out.println("**FIN**");
		}
		catch(Exception e){
			System.out.println("**ERROR**");
		}
	}
	
	public TCPServidor() throws Exception {
		ServerSocket socketAcogida = new ServerSocket(9999);
		
		while(true){
			Socket socketConxion = socketAcogida.accept();
			
			Connection nueva = new Connection(socketConxion);
		}
	}
	
}
