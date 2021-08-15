package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import gui.AltaPasajero;

public class App 
{
	public static void main(String[] args)
	{
		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		ventana.setContentPane(new AltaPasajero(ventana, null));
		
		ventana.pack();
		ventana.setVisible(true);
	}
}