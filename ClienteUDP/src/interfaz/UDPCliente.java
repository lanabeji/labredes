package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

import mundo.ClienteUDP;

public class UDPCliente extends JFrame {
	
	private PanelPrincipal panelPrincipal;
	
	
	public UDPCliente(){
		
		setTitle( "UDP" );
		setLayout( new BorderLayout( ) );
		setSize( 460, 430 );
		setResizable( false );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocationRelativeTo( null );
		
		
		panelPrincipal = new PanelPrincipal(this);
		add(panelPrincipal, BorderLayout.NORTH);
		
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
