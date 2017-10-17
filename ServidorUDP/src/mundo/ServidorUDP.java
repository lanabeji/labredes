package mundo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
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
		
		comenzarComunicacion(socketServidor, recibirDatos, enviarDatos);
	}
	
	public static void comenzarComunicacion(DatagramSocket socketServidor, byte[] recibirDatos, byte[] enviarDatos) throws Exception{
		
		while(true){
			
			DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
			
			socketServidor.receive(recibirPaquete);
			String frase = new String(recibirPaquete.getData());
			//System.out.println("Frase: "+frase);
			InetAddress DireccionIP = recibirPaquete.getAddress();
			int puerto = recibirPaquete.getPort();
			String[] fraseN = frase.split(";");
			String fraseNumero = fraseN[0];
			
			Timestamp marcaTiempoPaquete = Timestamp.valueOf(fraseN[3]);
			//System.out.println("Marca tiempo paquete: "+marcaTiempoPaquete);
			long miliSegundos = marcaTiempoPaquete.getTime();
			
			Timestamp marcaTiempoActual = new Timestamp(System.currentTimeMillis());
			//System.out.println("Marca tiempo actual: "+marcaTiempoActual);
			long mseg = marcaTiempoActual.getTime();
			
			String paraEnviar = fraseNumero+" : "+ (mseg - miliSegundos) +"ms";
			enviarDatos = paraEnviar.getBytes();
			String arch = "DireccionIP: " + DireccionIP + ", archivo: " + fraseN[2] + ", paquete numero "+ fraseN[0] 
					+ " de un total de " + fraseN[1] + ", tiempo: " + paraEnviar;
						
			DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length,DireccionIP,puerto);
			socketServidor.send(enviarPaquete);
			
			String dirIP = ""+DireccionIP;
			String[] nDirIP = dirIP.split("/");
			String ruta = "./data/"+nDirIP[1]+";"+fraseN[2]+".txt";
			ArrayList ar = new ArrayList<>();
			
			File file = new File(ruta); 
			if(file.exists()){
				BufferedReader br = new BufferedReader(new FileReader(ruta));			
				String line = "";				
				while((line = br.readLine())!=null){
					ar.add(line);
				}
				br.close();
			}
			
			BufferedWriter bw= new BufferedWriter(new FileWriter(ruta));
			if(ar.size()>0){
				bw.write( (String) ar.get(0));
				bw.flush();				
				if(ar.size()>1){
					for(int i=1; i<ar.size();i++){
						bw.newLine();
						bw.write( (String) ar.get(i));
						bw.flush();
					}
				}					
				bw.newLine();
				bw.write(arch);
				bw.flush();
			}
			else{
				bw.write(arch);
				bw.flush();
			}
			bw.close();
			
		}
				
	}
	
}
