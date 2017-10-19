package mundo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class ClienteUDP {

	private ArrayList<Objeto> paquetes;	
	
	public ClienteUDP(String dir, String puerto, int cant) throws Exception{
		
		paquetes = new ArrayList<Objeto>();
		Timestamp marcaTiempoActual = new Timestamp(System.currentTimeMillis());
		long tiempoArchivo = marcaTiempoActual.getTime();
		
		for (int i = 1; i <= cant; i++) {
			Objeto actual = new Objeto(i, cant, tiempoArchivo, new Timestamp(System.currentTimeMillis()));
			paquetes.add(actual);
			System.out.println("En el constructor: "+ i +" "+actual.getFechaCreacion());
		}
		
		DatagramSocket socketCliente = new DatagramSocket();		
		InetAddress DireccionIP = InetAddress.getByName(dir);		
		byte[] enviarDatos = new byte[1024];
		byte[] recibirDatos = new byte[1024];
		
		for (int i = 0; i < this.darPaquetes().size(); i++) {
			
			Objeto actual = this.darPaquetes().get(i);
			int num = actual.getNumSecuencia();
			int tot = actual.getTotal();
			long tiempoArc = actual.getTiempoArchivo();
			Timestamp tim = actual.getFechaCreacion();
			
			String frase = num + ";"+ tot + ";" + tiempoArc + ";"+tim;			
			enviarDatos = frase.getBytes();			
			int port = Integer.parseInt(puerto);		
			DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, DireccionIP, port);			
			socketCliente.send(enviarPaquete);
			
			DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);	
			socketCliente.receive(recibirPaquete);			
			String fraseModificada = new String(recibirPaquete.getData());			
			System.out.println("DEL SERVIDOR: " + fraseModificada);
		}	
		socketCliente.close();
	}
	
	private ArrayList<Objeto> darPaquetes(){
		return paquetes;
	}

}
