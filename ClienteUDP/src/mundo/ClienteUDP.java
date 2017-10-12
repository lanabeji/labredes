package mundo;

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
		
		new ClienteUDP(2);

		
	}

}
