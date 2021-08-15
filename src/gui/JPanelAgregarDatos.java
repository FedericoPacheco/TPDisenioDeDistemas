package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JPanelAgregarDatos extends JPanel
{
	private List<String> etiquetasStr;
	private Map<String, JLabel> etiquetas;
	private List<JComponent> componentes;
	private GridBagConstraints gbc;
	
	public JPanelAgregarDatos() 
	{
		componentes = new LinkedList<JComponent>();
		etiquetasStr = new LinkedList<String>();
		etiquetas = new HashMap<String, JLabel>();
		
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
	}
	
	public JPanelAgregarDatos addEtiqueta(String etiqueta) 
	{
		etiquetasStr.add(etiqueta);
		return this;
	}
	
	public JPanelAgregarDatos addComponente(JComponent componente) 
	{
		componentes.add(componente);
		return this;
	}
	
	public JPanelAgregarDatos cambiarColorEtiqueta(String etiquetaStr, Color color)
	{
		etiquetas.get(etiquetaStr).setForeground(color);
		return this;
	}
}
