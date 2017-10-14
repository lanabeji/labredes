package mundo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.sql.Timestamp;

public class ServidorUDP {

	public static void main(String[] args) throws Exception {

		inicio();
	}
	
	public static void inicio() throws Exception{
		
		DatagramSocket socketServidor = new DatagramSocket(9999);

		byte[] recibirDatos = new byte[1024];
		byte[] enviarDatos = new byte[1024];

		String prueba = "./data/prueba.txt";
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(prueba)),true);
		
		comenzarComunicacion(socketServidor, recibirDatos, enviarDatos, pw);
	}
	
	public static void comenzarComunicacion(DatagramSocket socketServidor, byte[] recibirDatos, byte[] enviarDatos,PrintWriter pw) throws Exception{
		
		while(true){

			DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
			

			socketServidor.receive(recibirPaquete);

			String frase = new String(recibirPaquete.getData());

			InetAddress DireccionIP = recibirPaquete.getAddress();

			int puerto = recibirPaquete.getPort();

			String[] fraseN = frase.split(";");

			String fraseNumero = fraseN[0];

			Timestamp marcaTiempoPaquete = Timestamp.valueOf(fraseN[1]);
			//System.out.println("Marca tiempo paquete: "+marcaTiempoPaquete);
			long miliSegundos = marcaTiempoPaquete.getTime();

			Timestamp marcaTiempoActual = new Timestamp(System.currentTimeMillis());
			//System.out.println("Marca tiempo actual: "+marcaTiempoActual);
			long mseg = marcaTiempoActual.getTime();

			String paraEnviar = fraseNumero+" : "+ (mseg - miliSegundos) +"ms";

			enviarDatos = paraEnviar.getBytes();

			pw.println(paraEnviar);

			DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length,DireccionIP,puerto);

			socketServidor.send(enviarPaquete);
		}
		
		
	}

}
