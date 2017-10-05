package mundo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection extends Thread{

	private Socket socket1;
	private BufferedReader entradaDesdeCliente;
	private DataOutputStream salidaACliente;

	public Connection(Socket canal) throws Exception{

		socket1 = canal;
		entradaDesdeCliente = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
		salidaACliente = new DataOutputStream(socket1.getOutputStream());
		this.start();
	}

	public void run(){

		String mensaje;
		String salida;

			try {
				mensaje = entradaDesdeCliente.readLine();

				if(mensaje.equals("HOLA")){
					salidaACliente.writeBytes("HOLA DESDE SERVIDOR"+'\n');
				}
				else if(mensaje.equals("ENVIAR ARTICULOS")){
					salidaACliente.writeBytes("enviando nombres de los archivos disponibles...");
				}

				else{
					salidaACliente.writeBytes("NO SE"+'\n');
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}



}
