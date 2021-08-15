package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.commons.validator.routines.EmailValidator;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import utilidades.ConstantesPosicionesFrenteAIVA;
import utilidades.ConstantesTipoDocumento;
import utilidades.DateLabelFormatter;

@SuppressWarnings("serial")
public class AltaPasajero extends JPanel
{
	private JFrame ventana;
	private GridBagConstraints gbc;
	private JPanelAgregarDatos datosBasicos;
	private JPanelAgregarDatos direccion;
	private JPanelAgregarDatos otrosDatos;
	
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JComboBox<String> cbTipoDoc;
	private JFormattedTextField ftfNumDoc;
	private JDatePickerImpl selectorFechaNac;
	private JFormattedTextField ftfTel;
	private JTextField tfEmail;
	private JTextField tfNacionalidad;
	private JTextField tfCalle;
	private JFormattedTextField ftfNumCalle;
	private JTextField tfDepartamento;
	private JTextField tfPiso;
	private JTextField tfCodPostal;
	private JComboBox<String> cbPais;
	private JComboBox<String> cbProvincia;
	private JComboBox<String> cbLocalidad;
	private JFormattedTextField ftfCuit;
	private JComboBox<String> cbIva;
	private JTextField tfOcupacion;
	private NumberFormat formatoNumeros;
	
	private static final String NOMBRE_STR = "Nombre(s) (*)"; 
	private static final String APELLIDO_STR = "Apellido(s) (*)";
	private static final String TIPO_DOC_STR = "Tipo de documento (*)";
	private static final String NUM_DOC_STR = "Número de documento (*)";
	private static final String FECHA_NAC_STR = "Fecha de nacimiento (*)";
	private static final String TEL_STR = "Teléfono (*)";
	private static final String EMAIL_STR = "Email";
	private static final String NAC_STR = "Nacionalidad (*)";
	private static final String CALLE_STR = "Calle (*)";
	private static final String NUM_CALLE_STR = "Número (*)";
	private static final String DPTO_STR = "Departamento (*)";
	private static final String PISO_STR = "Piso (*)";
	private static final String COD_POSTAL_STR = "Código postal (*)";
	private static final String PAIS_STR = "País (*)";
	private static final String PROV_STR = "Provincia (*)";
	private static final String LOC_STR = "Localidad (*)";
	private static final String CUIT_STR = "CUIT";
	private static final String IVA_STR = "Posición frente a IVA (*)";
	private static final String OCUPACION_STR = "Ocupación (*)";
	
	public AltaPasajero(JFrame ventana, JPanel panelPadre)
	{
		this.ventana = ventana;
		ventana.setTitle("Dar de alta pasajero");
		
		// https://stackoverflow.com/questions/31708163/numberformat-text-field-without-commas
		formatoNumeros = NumberFormat.getNumberInstance();
		formatoNumeros.setGroupingUsed(false);
		
		datosBasicos = new JPanelAgregarDatos();
		this.datosBasicos();
		datosBasicos.armar();
		
		direccion = new JPanelAgregarDatos();
		this.direccion();
		direccion.armar();
		
		otrosDatos = new JPanelAgregarDatos();
		this.otrosDatos();
		otrosDatos.armar();
		
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.armar();
	}
	
	private void armar() 
	{
		// https://docs.oracle.com/javase/tutorial/uiswing/components/border.html
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(20, 20, 10, 20);
		datosBasicos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(datosBasicos, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 20, 0, 20);
		this.add(new JLabel("Dirección"), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 20, 5, 20);
		direccion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(direccion, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 6;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(10, 20, 20, 20);
		otrosDatos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(otrosDatos, gbc);
		
		// No logro que el boton quede bien al lado de Cancelar
		JButton bSiguiente = new JButton("Siguiente");
		gbc.gridx = 5;
		gbc.gridy = 4;
		//gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 10, 20, 10);
		this.add(bSiguiente, gbc);
		bSiguiente.addActionListener(e -> 
		{
			if (this.entradaEsCorrecta()) // TODO: guardar en la db
			{
				System.exit(0);
			}
		});
		
		JButton bCancelar = new JButton("Cancelar");
		gbc.gridx = 5;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.EAST;//.LAST_LINE_END;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.EAST;
		gbc.insets = new Insets(0, 10, 20, 20);
		this.add(bCancelar, gbc);
		bCancelar.addActionListener(e -> 
		{
			Object[] opciones = {"No", "Sí"};
			
			if 
			(	
				opciones
				[JOptionPane.showOptionDialog(
					ventana, 
					"¿Desea cancelar el alta de pasajero?", 
					"", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					opciones,
					opciones[0]
				)] == opciones[1]
			)
				System.exit(0);  // TODO: volver al menu anterior
		});
	}

	private void datosBasicos()
	{
		datosBasicos
			.addEtiqueta(NOMBRE_STR)
			.addEtiqueta(APELLIDO_STR)
			.addEtiqueta(TIPO_DOC_STR)
			.addEtiqueta(NUM_DOC_STR)
			.addEtiqueta(FECHA_NAC_STR)
			.addEtiqueta(TEL_STR)
			.addEtiqueta(EMAIL_STR)
			.addEtiqueta(NAC_STR);
		
		
		tfNombre = new JTextField();
		tfApellido = new JTextField();
		
		cbTipoDoc = new JComboBox<String>();
		for (String tipoDoc : ConstantesTipoDocumento.getAll())
			cbTipoDoc.addItem(tipoDoc);
		cbTipoDoc.setSelectedItem(ConstantesTipoDocumento.DNI);
		
		// https://docs.oracle.com/javase/8/docs/technotes/guides/swing/1.4/ftf.html#simpleUses
		// https://stackoverflow.com/a/18084146
		// https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
		ftfNumDoc = new JFormattedTextField(formatoNumeros);
		
		// https://www.codejava.net/java-se/swing/how-to-use-jdatepicker-to-display-calendar-component
		selectorFechaNac = new JDatePickerImpl(
			new JDatePanelImpl(new UtilDateModel(), new Properties()), 
			new DateLabelFormatter()
		);

		ftfTel = new JFormattedTextField(formatoNumeros);
		
		tfEmail = new JTextField();
		tfNacionalidad = new JTextField();
		
		
		datosBasicos
			.addComponente(tfNombre)
			.addComponente(tfApellido)
			.addComponente(cbTipoDoc)
			.addComponente(ftfNumDoc)
			.addComponente(selectorFechaNac)
			.addComponente(ftfTel)
			.addComponente(tfEmail)
			.addComponente(tfNacionalidad);
	}
	
	private void direccion() 
	{
		direccion
			.addEtiqueta(CALLE_STR)
			.addEtiqueta(NUM_CALLE_STR)
			.addEtiqueta(DPTO_STR)
			.addEtiqueta(PISO_STR)
			.addEtiqueta(COD_POSTAL_STR)
			.addEtiqueta(PAIS_STR)
			.addEtiqueta(PROV_STR)
			.addEtiqueta(LOC_STR);
			
		
		tfCalle = new JTextField();
		
		ftfNumCalle = new JFormattedTextField(formatoNumeros);
		
		tfDepartamento = new JTextField();
		tfPiso = new JTextField();
		tfCodPostal = new JTextField();
		
		// TODO: agregar opciones
		cbPais = new JComboBox<String>();
		cbProvincia = new JComboBox<String>();
		cbLocalidad = new JComboBox<String>();
		
		
		direccion
			.addComponente(tfCalle)
			.addComponente(ftfNumCalle)
			.addComponente(tfDepartamento)
			.addComponente(tfPiso)
			.addComponente(tfCodPostal)
			.addComponente(cbPais)
			.addComponente(cbProvincia)
			.addComponente(cbLocalidad);
	}
	
	private void otrosDatos() 
	{
		otrosDatos
			.addEtiqueta(CUIT_STR)
			.addEtiqueta(IVA_STR)
			.addEtiqueta(OCUPACION_STR);
		
		
		ftfCuit = null;
		try {
			ftfCuit = new JFormattedTextField(new MaskFormatter("##-########-##"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cbIva = new JComboBox<String>();
		for (String posicion : ConstantesPosicionesFrenteAIVA.getAll())
			cbIva.addItem(posicion);
		cbIva.setSelectedItem(ConstantesPosicionesFrenteAIVA.CONSUMIDOR_FINAL);
		
		tfOcupacion = new JTextField();
		
		
		otrosDatos
			.addComponente(ftfCuit)
			.addComponente(cbIva)
			.addComponente(tfOcupacion);
	}
	
	private Boolean entradaEsCorrecta() 
	{
		Integer largoCuit = 14;
		Boolean hayCamposIncompletos = false;
		Boolean tipoYNumDocCorrectos = true;
		
		// Se validan los que son obligatorios
		if (tfNombre.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			datosBasicos.cambiarColorEtiqueta(NOMBRE_STR, Color.RED);
		}
		if (tfApellido.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			datosBasicos.cambiarColorEtiqueta(APELLIDO_STR, Color.RED);
		}
		if (ftfNumDoc.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			datosBasicos.cambiarColorEtiqueta(NUM_DOC_STR, Color.RED);
		}
		else
		{
			//tipoYNumeroDocCorrectos = // TODO validar tipo y numero de doc
		}
		if (selectorFechaNac.getModel().getValue() == null)
		{
			hayCamposIncompletos = true;
			datosBasicos.cambiarColorEtiqueta(FECHA_NAC_STR, Color.RED);
		}
		if (ftfTel.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			datosBasicos.cambiarColorEtiqueta(TEL_STR, Color.RED);
		}
		if (tfNacionalidad.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			datosBasicos.cambiarColorEtiqueta(NAC_STR, Color.RED);
		}
		if (tfCalle.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			direccion.cambiarColorEtiqueta(CALLE_STR, Color.RED);
		}
		if (ftfNumCalle.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			direccion.cambiarColorEtiqueta(NUM_CALLE_STR, Color.RED);
		}	
		if (tfDepartamento.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			direccion.cambiarColorEtiqueta(DPTO_STR, Color.RED);
		}
		if (tfPiso.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			direccion.cambiarColorEtiqueta(PISO_STR, Color.RED);
		}
		if (tfCodPostal.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			direccion.cambiarColorEtiqueta(COD_POSTAL_STR, Color.RED);
		}	
		// El cuit no puede ser vacio si se es "responsable inscripto"
		if (cbIva.getSelectedItem().equals(ConstantesPosicionesFrenteAIVA.RESPONSABLE_INSCRIPTO))
		{	if (ftfCuit.getText().length() < largoCuit)
			{
				hayCamposIncompletos = true;
				otrosDatos.cambiarColorEtiqueta(CUIT_STR, Color.RED);
			}
		}		
		if (tfOcupacion.getText().isEmpty())
		{
			hayCamposIncompletos = true;
			otrosDatos.cambiarColorEtiqueta(OCUPACION_STR, Color.RED);
		}
		
		
		
		if (hayCamposIncompletos)
		{
			Object opciones[] = {"Aceptar"};
			JOptionPane.showOptionDialog(
				ventana, 
				"Complete todos los campos marcados como obligatorios (*).", 
				"Error", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.ERROR_MESSAGE, 
				null, 
				opciones,
				opciones[0]
			);
		}
		else if (!tipoYNumDocCorrectos)
		{
			Object[] opciones = {"Aceptar igualmente", "Corregir"};
			if 
			(
				opciones
				[JOptionPane.showOptionDialog(
					ventana, 
					"El tipo y número de documento ya existen en el sistema.", 
					"Cuidado", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE, 
					null, 
					opciones,
					opciones[1]
				)] == opciones[0]
			)
				tipoYNumDocCorrectos = true;
			else
			{	
				datosBasicos.cambiarColorEtiqueta(NUM_DOC_STR, Color.RED);
				datosBasicos.cambiarColorEtiqueta(TIPO_DOC_STR, Color.RED);
			}
		}
		
		if (!hayCamposIncompletos && tipoYNumDocCorrectos)
		{
			if (!tfEmail.getText().isEmpty() && !emailEsCorrecto(tfEmail.getText()))
			{
				Object opciones[] = {"Aceptar"};
				JOptionPane.showOptionDialog(
					ventana, 
					"El email ingresado no es válido.", 
					"Error", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.ERROR_MESSAGE, 
					null, 
					opciones,
					opciones[0]
				);
				datosBasicos.cambiarColorEtiqueta(EMAIL_STR, Color.RED);
				return false;
			}
		}
		
		return !hayCamposIncompletos && tipoYNumDocCorrectos;
	}
	
	private Boolean emailEsCorrecto(String email)
	{
		// https://stackoverflow.com/a/26687649
		// https://mvnrepository.com/artifact/commons-validator/commons-validator/1.7
		Boolean allowLocal = false;
		return EmailValidator.getInstance(allowLocal).isValid(email);
	}
	
	private LocalDate dateALocalDate(Date fecha)
	{
		// https://www.baeldung.com/java-date-to-localdate-and-localdatetime
		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
