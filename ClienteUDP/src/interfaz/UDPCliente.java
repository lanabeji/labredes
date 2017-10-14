package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import mundo.ClienteUDP;

public class UDPCliente extends JFrame {
	
	private PanelPrincipal panelPrincipal;
	
	
	public UDPCliente(){
		
		setTitle( "UDP" );
		setLayout( new BorderLayout( ) );
		setSize( 460, 330 );
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		
		
		panelPrincipal = new PanelPrincipal(this);
		add(panelPrincipal, BorderLayout.NORTH);
		
	}
	
	public void enviarParametros(String dir, String puerto, String obj){
		
		JFrame frame = new JFrame("JOptionPane showMessageDialog example");
		 JOptionPane.showMessageDialog(frame,
			        "Información enviada",
			        "Backup problem",
			        JOptionPane.INFORMATION_MESSAGE);
		 
		 panelPrincipal.vaciarCampos();
	}
	
	public static void main(String[] args) {
		
		try
		{
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		UDPCliente interfaz = new UDPCliente( );
		interfaz.setVisible( true );
	}

}
