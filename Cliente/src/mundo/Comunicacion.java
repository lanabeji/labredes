package mundo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Comunicacion extends Thread{

	private Socket socketCliente;
//	private TCPCliente clienteTCP;
	private BufferedReader entradaDesdeUsuario;
	private DataOutputStream salidaAServidor;
	private BufferedReader entradaDesdeServidor;

	public Comunicacion(Socket cliente) throws Exception{
		socketCliente=cliente;
	//	clienteTCP = cli;

		entradaDesdeUsuario = new BufferedReader(new InputStreamReader(System.in));

		salidaAServidor = new DataOutputStream(socketCliente.getOutputStream());
		entradaDesdeServidor = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));

	}

	public boolean procesarMensaje( ) throws Exception
	{

		try
		{
			boolean logout = false;
		
			String linea;
			
			linea = entradaDesdeUsuario.readLine();
			salidaAServidor.writeBytes("HOLA"+'\n');
			
			String rta = entradaDesdeServidor.readLine();
			System.out.println("del servidor: "+rta );
			
			if(rta.equals("HOLA DESDE SERVIDOR")){
				salidaAServidor.writeBytes("ENVIAR ARTICULOS");
			}
			
			
			return logout;
		}
		catch( Exception e )
		{
			String mensaje = "Error al leer el mensaje del servidor: " + e.getMessage( ) + ".";
			if( e.getMessage( ).equals( "Connection reset" ) )
			{
				mensaje = "Se perdió la conexión con el servidor.";
			}
			throw new Exception( mensaje );
		}


	}

	public void run(){

		while( true )
		{
			try
			{
				boolean logout = procesarMensaje( );
				if( logout )
				{
					return;
				}
			}
			catch( Exception e )
			{
				e.printStackTrace();
			}

		}
	}
}
