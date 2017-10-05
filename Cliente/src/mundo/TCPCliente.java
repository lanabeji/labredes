package mundo;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class TCPCliente {

	private ArrayList archivosDisponibles;
	
	public static void main(String argv[]) throws Exception{
		

		try
        {
			Socket socketCliente = new Socket("localhost", 9999);
            Comunicacion comunicacion = new Comunicacion( socketCliente);
        }
        catch(Exception e )
        {
            String mensaje = "No se pudo establecer la conexi�n con el servidor.";
            if( e.getMessage( ).equals( "connect timed out" ) )
            {
                mensaje += " El servidor no est� disponible. \n Int�ntelo de nuevo m�s tarde, o con otra direcci�n IP.";
            }
            throw new Exception( mensaje );
        }
		
	}
	
	
	public void modificarArchivosDisponibles(ArrayList arts){
		this.archivosDisponibles=arts;
	}
	
	public String darEstado(){
		return "";
	}
	
}
