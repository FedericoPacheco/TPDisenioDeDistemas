package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilidades.ConstantesTipoDocumento;

@SuppressWarnings("serial")
public class GestionarPasajero extends JPanel 
{
	private JFrame ventana;
	private JPanel panelPadre;
	private GridBagConstraints gbc;
	private JPanelCompletarDatos criteriosBusqueda;
	private JPanelTablaConBotones tablaResultados;
	
	private JLabel lCriterios;
	private JLabel lResultados;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JComboBox<String> cbTipoDoc;
	private JFormattedTextField ftfNumDoc;
	private JButton bCancelar;
	
	public GestionarPasajero(JFrame ventana, JPanel panelPadre)
	{
		this.ventana = ventana;
		this.panelPadre = panelPadre;
		ventana.setTitle("Gestionar pasajero");
	
		criteriosBusqueda = new JPanelCompletarDatos();
		this.criteriosBusqueda();
		criteriosBusqueda.armar();
		
		tablaResultados = new JPanelTablaConBotones();
		this.tablaResultados();
		tablaResultados.armar();
		
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
		gbc.insets = new Insets(10, 0, 0, 20);
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
	
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 20, 0, 20);
		lResultados = new JLabel("Resultados de búsqueda");
		lResultados.setFont(new Font(lResultados.getFont().toString(), Font.BOLD, 14));
		this.add(lResultados, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 6;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 20, 0, 20);
		tablaResultados.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(tablaResultados, gbc);
		
		// TODO De nuevo, no puedo hacer que el boton quede bien
		bCancelar = new JButton("Cancelar");
		gbc.gridx = 5;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.EAST;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(bCancelar, gbc);
		bCancelar.addActionListener(e -> 
		{
			System.exit(0);
		}); // TODO
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
			.addBoton("Siguiente", e -> {}); // TODO
	}
	
	private void tablaResultados() 
	{
		tablaResultados
			.addColumna("Nombre(s)")
			.addColumna("Apellido(s)")
			.addColumna("Tipo de documento")
			.addColumna("Número de documento");
	
		// Provisional. TODO Recuperar los datos "bien"
		Object[][] datos = {
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339},
			{"Juan", "Perez", "DNI", 16180339}
		};
		
		tablaResultados.setDatos(datos);	
		
		// El boton no queda bien
		tablaResultados.addBoton("Siguiente", e -> {}); // TODO
	}
}
