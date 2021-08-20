package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import gui.DarDeAltaPasajero;
import gui.GestionarPasajero;

public class App 
{
	public static void main(String[] args)
	{
		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ventana.setContentPane(new DarDeAltaPasajero(ventana, null));
		//ventana.setContentPane(new GestionarPasajero(ventana, null));
		
		ventana.pack();
		ventana.setVisible(true);
	}
}
