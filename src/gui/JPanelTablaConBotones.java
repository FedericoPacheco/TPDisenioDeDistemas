package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class JPanelTablaConBotones extends JPanel
{
	public static int MAX_FILAS = 10;
	
	private GridBagConstraints gbc;
	private JTable tbl;
	private ModeloTablaConBotones mt;
	private JButton bAtras;
	private JButton bAdelante;
	private Object[][] datos;
	private int pisoInclusivo;
	private int techoExclusivo;
	private int pagActual;
	private int pagsTotales;
	private List<JButton> botonesAdicionales;
	
	public JPanelTablaConBotones()
	{
		pisoInclusivo = 0;
		techoExclusivo = MAX_FILAS;
		mt = new ModeloTablaConBotones();
		bAdelante = new JButton(">");
		bAtras = new JButton("<");	
		botonesAdicionales = new LinkedList<JButton>();
		
		datos = null;
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
	}
	
	public void armar()
	{
		if (datos != null)
			mt.setDatos(this.recuperarDatos());
		tbl = new JTable(mt);
		this.arreglarColumnas();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = mt.getAllColumnas().size();
		gbc.weightx = 1.0;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.add(new JScrollPane(tbl), gbc);
		

		gbc.gridx = mt.getAllColumnas().size() / 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(10, 10, 10, 5);
		this.add(bAtras, gbc);
		bAtras.addActionListener(e ->
		{
			if (pisoInclusivo + MAX_FILAS >= datos.length)
				techoExclusivo -= datos.length - pisoInclusivo;
			else
				techoExclusivo -= MAX_FILAS;
			pisoInclusivo -= MAX_FILAS;
			
			bAdelante.setEnabled(true);
			if (pisoInclusivo == 0)
				bAtras.setEnabled(false);
			
			if (datos != null)
			{
				mt.setDatos(this.recuperarDatos());
				mt.fireTableDataChanged();
			}
		});
		
		
		gbc.gridx = mt.getAllColumnas().size() / 2 + 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(10, 0, 10, 0);
		pagActual = pisoInclusivo / MAX_FILAS + 1;
		pagsTotales = datos.length / MAX_FILAS + ((datos.length % MAX_FILAS == 0)? 0 : 1);
		this.add(new JLabel("Pág. " + pagActual  + " / " + pagsTotales), gbc);
		
		
		gbc.gridx = mt.getAllColumnas().size() / 2 + 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.insets = new Insets(10, 5, 10, 5);
		this.add(bAdelante, gbc);
		bAdelante.addActionListener(e ->
		{
			pisoInclusivo += MAX_FILAS;
			bAtras.setEnabled(true);
			if (pisoInclusivo + MAX_FILAS >= datos.length)
			{
				techoExclusivo += datos.length - pisoInclusivo;
				bAdelante.setEnabled(false);
			}
			else
				techoExclusivo += MAX_FILAS;
			
			if (datos != null)
			{
				mt.setDatos(this.recuperarDatos());
				mt.fireTableDataChanged();
			}
		});
		
		
		int j = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.EAST;
		gbc.insets = new Insets(5, 5, 5, 5);
		for (JButton b : botonesAdicionales)
		{
			gbc.gridx = mt.getAllColumnas().size() - 1 - j;
			this.add(b, gbc);
			
			j++;
		}		
	}
	
	private Object[][] recuperarDatos()
	{	
		Object[][] subconjuntoDatos = new Object[MAX_FILAS][mt.getAllColumnas().size()];
		for (int i = pisoInclusivo, k = 0; i < techoExclusivo; i++, k++)
			for (int j = 0; j < mt.getAllColumnas().size(); j++)
				subconjuntoDatos[k][j] = datos[i][j];
		
		return subconjuntoDatos;	
	}
	
	private void arreglarColumnas()
	{
		DefaultTableCellRenderer empezarEnLaIzquierda = new DefaultTableCellRenderer();
		empezarEnLaIzquierda.setHorizontalAlignment(JLabel.LEFT);
		int i = 0;
		int anchoTotal = 0;
		int anchoColumna;
		for (String columna : mt.getAllColumnas())
		{
			anchoColumna = columna.length() * 8;
			
			tbl.getColumnModel().getColumn(i).setCellRenderer(empezarEnLaIzquierda);
			tbl.getColumnModel().getColumn(i).setPreferredWidth(anchoColumna);
		
			anchoTotal += anchoColumna;
			i++;
		}
		tbl.setPreferredScrollableViewportSize(new Dimension(anchoTotal, (int) ((MAX_FILAS + 1) * 14.60)));
	
		// Los numeros magicos son ajustes hechos "a ojo"
	}
	
	public JPanelTablaConBotones addColumna(String nombreColumna)
	{
		mt.addColumna(nombreColumna);
		return this;
	}
	
	public JPanelTablaConBotones setDatos(Object[][] datos)
	{
		this.datos = datos;
		bAtras.setEnabled(false);
		if (datos.length / MAX_FILAS > 0)
			bAdelante.setEnabled(true);
		else
			bAdelante.setEnabled(false);
		
		return this;
	}
	
	public JPanelTablaConBotones addBoton(String nombre, ActionListener accion)
	{
		JButton b = new JButton(nombre);
		b.addActionListener(accion);
		botonesAdicionales.add(b);
		return this;
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	private class ModeloTablaConBotones extends AbstractTableModel
	{
		private List<String> nombresColumnas;
		private Object[][] datos;
		
		public ModeloTablaConBotones()
		{
			super();
			nombresColumnas = new LinkedList<String>();
			datos = null;
		}
		
		public int getColumnCount() 							{ return nombresColumnas.size(); 		}
		public int getRowCount() 								{ return datos.length; 					}
		public String getColumnName(int col) 					{ return nombresColumnas.get(col);		}
		public Object getValueAt(int row, int col) 				{ return datos[row][col]; 				}
		public Class<? extends Object> getColumnClass(int c) 	{ return getValueAt(0, c).getClass(); 	}
		public boolean isCellEditable(int row, int col) 		{ return false;							}
		public void setValueAt(Object value, int row, int col) 	
		{
		    datos[row][col] = value;
		    fireTableCellUpdated(row, col);
		}
		
		public ModeloTablaConBotones addColumna(String nombreColumna) 
		{
			nombresColumnas.add(nombreColumna);
			return this;
		}
		
		public List<String> getAllColumnas() {
			return nombresColumnas;
		}

		public ModeloTablaConBotones setDatos(Object[][] datos) 
		{
			this.datos = datos;
			return this;
		}
	}
}
