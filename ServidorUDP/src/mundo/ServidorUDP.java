package mundo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {

	public static void main(String[] args) throws Exception {

		DatagramSocket socketServidor = new DatagramSocket(9999);
		
		byte[] recibirDatos = new byte[1024];
		byte[] enviarDatos = new byte[1024];
		
		while(true){
			
			DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
			
			socketServidor.receive(recibirPaquete);
			
			String frase = new String(recibirPaquete.getData());
			
			InetAddress DireccionIP = recibirPaquete.getAddress();
			
			int puerto = recibirPaquete.getPort();
			
			String fraseMayuscula = frase.toUpperCase();
			
			enviarDatos = fraseMayuscula.getBytes();
			
			DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length,DireccionIP,puerto);
			
			socketServidor.send(enviarPaquete);
		}
	}

}
