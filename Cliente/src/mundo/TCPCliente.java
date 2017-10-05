package mundo;

import java.io.*;
import java.net.*;

public class TCPCliente {

	public static void main(String argv[]) throws Exception{
		
		String frase;
		String fraseModificada;
		
		BufferedReader entradaDesdeUsuario = new BufferedReader(new InputStreamReader(System.in));
		Socket socketCliente = new Socket("localhost", 9999);
		
		DataOutputStream salidaAServidor = new DataOutputStream(socketCliente.getOutputStream());
		BufferedReader entradaDesdeServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
		
		frase=entradaDesdeUsuario.readLine();
		salidaAServidor.writeBytes(frase+'\n');
		
		fraseModificada=entradaDesdeServidor.readLine();
		System.out.println("del servidor: "+fraseModificada );
		
		socketCliente.close();
		
	}
	
}
