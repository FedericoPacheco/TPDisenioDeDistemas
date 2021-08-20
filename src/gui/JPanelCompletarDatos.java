package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanelCompletarDatos extends JPanel
{
	private List<String> etiquetasStr;
	private Map<String, JLabel> etiquetas;
	private List<JComponent> componentes;
	private List<JButton> botones;
	private GridBagConstraints gbc;
	
	public JPanelCompletarDatos() 
	{
		etiquetasStr = new LinkedList<String>();
		etiquetas = new HashMap<String, JLabel>();
		componentes = new LinkedList<JComponent>();
		botones = new LinkedList<JButton>();
		
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
	}
	
	public void armar()
	{
		JLabel etiqueta;
		int i = 0;
		int j = 0;
		int k = 0;
		for (JComponent c : componentes)
		{
			//System.out.println("i: " + i + "; j: " + j + "; k: " + k);
			
			gbc.gridx = j;
			gbc.gridy = i;
			gbc.gridwidth = 1;
			gbc.weightx = 0.1;
			gbc.fill = GridBagConstraints.WEST;
			gbc.insets = new Insets(5, 5, 5, 5);
			etiqueta = new JLabel(etiquetasStr.get(k));
			etiquetas.put(etiquetasStr.get(k), etiqueta);
			etiqueta.setPreferredSize(new Dimension(150, 26));
			this.add(etiqueta, gbc);
			
			gbc.gridx = j + 1;
			gbc.gridy = i;
			gbc.gridwidth = 2;
			gbc.weightx = 0.4;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.insets = new Insets(5, 5, 5, 5);
			c.setPreferredSize(new Dimension(200, 26));
			this.add(c, gbc);
			
			k++;
			if (j == 3)
			{
				i++;
				j = 0;
			}
			else
				j += 3;
		}
		
		
		j = 0;
		gbc.gridy = i;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.EAST;
		gbc.insets = new Insets(10, 5, 10, 5);
		for (JButton b : botones)
		{
			gbc.gridx = 5 - j;
			this.add(b, gbc);
			
			j++;
		}		
	}
	
	public JPanelCompletarDatos addEtiqueta(String etiqueta) 
	{
		etiquetasStr.add(etiqueta);
		return this;
	}
	
	public JPanelCompletarDatos addComponente(JComponent componente) 
	{
		componentes.add(componente);
		return this;
	}
	
	public JPanelCompletarDatos addBoton(String nombre, ActionListener accion)
	{
		JButton b = new JButton(nombre);
		b.addActionListener(accion);
		botones.add(b);
		return this;
	}
	
	public JPanelCompletarDatos cambiarColorEtiqueta(String etiquetaStr, Color color)
	{
		etiquetas.get(etiquetaStr).setForeground(color);
		return this;
	}
}
