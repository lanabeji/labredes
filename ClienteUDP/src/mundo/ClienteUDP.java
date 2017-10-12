package mundo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ClienteUDP {

	private ArrayList paquetes;
	
	
	public ClienteUDP(int cant){
		
		paquetes = new ArrayList();
		
		for (int i = 0; i < cant; i++) {
			Objeto actual = new Objeto(i, new Timestamp(System.currentTimeMillis()));
			paquetes.add(actual);
			System.out.println(actual.getFechaCreacion());
		}
	}
	
	public static void main(String args[]) throws Exception{
		
		//new ClienteUDP(2);
		
		BufferedReader entradaDesdeUsuario = new BufferedReader(new InputStreamReader(System.in));
		
		DatagramSocket socketCliente = new DatagramSocket();
		
		InetAddress DireccionIP = InetAddress.getByName("localhost");
		
		byte[] enviarDatos = new byte[1024];
		byte[] recibirDatos = new byte[1024];
		
		String frase = entradaDesdeUsuario.readLine();
		
		enviarDatos = frase.getBytes();
		
		DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, DireccionIP, 9999);
		
		socketCliente.send(enviarPaquete);
		
		DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
		
		socketCliente.receive(recibirPaquete);
		
		String fraseModificada = new String(recibirPaquete.getData());
		
		System.out.println("DEL SERVIDOR: " + fraseModificada);
		
		socketCliente.close();

		
	}

}
