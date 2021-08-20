package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilidades.ConstantesTipoDocumento;

public class GestionarPasajero extends JPanel 
{
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JPanelCompletarDatos criteriosBusqueda;
	
	private JLabel lCriterios;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JComboBox<String> cbTipoDoc;
	private JFormattedTextField ftfNumDoc;
	
	public GestionarPasajero(JFrame ventana, JPanel panelPadre)
	{
		this.ventana = ventana;
		this.panelPadre = panelPadre;
	
		criteriosBusqueda = new JPanelCompletarDatos();
		this.criteriosBusqueda();
		criteriosBusqueda.armar();
		
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armar();
	}
	
	private void armar()
	{
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 20, 0, 20);
		lCriterios = new JLabel("Criterios de búsqueda");
		lCriterios.setFont(new Font(lCriterios.getFont().toString(), Font.BOLD, 14));
		this.add(lCriterios, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 20, 20, 20);
		criteriosBusqueda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(criteriosBusqueda, gbc);
	
	
	}
	
	private void criteriosBusqueda()
	{
		criteriosBusqueda
			.addEtiqueta("Nombre(s)")
			.addEtiqueta("Apellido(s)")
			.addEtiqueta("Tipo de documento")
			.addEtiqueta("Número de documento");
		
		
		tfNombre = new JTextField();
		tfApellido = new JTextField();
		
		cbTipoDoc = new JComboBox<String>();
		for (String tipoDoc : ConstantesTipoDocumento.getAll())
			cbTipoDoc.addItem(tipoDoc);
		cbTipoDoc.setSelectedItem(ConstantesTipoDocumento.DNI);
		
		NumberFormat formatoNumeros = NumberFormat.getNumberInstance();
		formatoNumeros.setGroupingUsed(false);
		ftfNumDoc = new JFormattedTextField(formatoNumeros);
		
		
		criteriosBusqueda
			.addComponente(tfNombre)
			.addComponente(tfApellido)
			.addComponente(cbTipoDoc)
			.addComponente(ftfNumDoc);
	
		
		criteriosBusqueda
			.addBoton("Siguiente", e -> {});
	}
}
