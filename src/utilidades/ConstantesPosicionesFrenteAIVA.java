package utilidades;

import java.util.LinkedList;
import java.util.List;

public class ConstantesPosicionesFrenteAIVA 
{
	public static String RESPONSABLE_INSCRIPTO 		= "Responsable inscripto";
	public static String RESPONSABLE_NO_INSCRIPTO 	= "Responsable no inscripto";
	public static String NO_RESPONSABLE 			= "No responsable";
	public static String SUJETO_EXENTO				= "Sujeto exento";
	public static String CONSUMIDOR_FINAL			= "Consumidor final";
	public static String RESPONSABLE_MONOTRIBUTO 	= "Responsable monotributo";
	public static String SUJETO_NO_CATEGORIZADO 	= "Sujeto no categorizado";
	public static String PROVEEDOR_DEL_EXTERIOR 	= "Proveedor del exterior";
	public static String CLIENTE_DEL_EXTERIOR 		= "Cliente del exterior";
	public static String LIBERADO 					= "Liberado";
	public static String RESPONSABLE_INSCRIPTO_AGENTE_DE_PERCEPCION = "Responsable inscripto - agente de percepción";
	public static String PEQUENIO_CONTRIBUYENTE_EVENTUAL 			= "Pequeño contribuyente eventual";
	public static String MONOTRIBUTISTA_SOCIAL 						= "Monotributista social";
	public static String PEQUENIO_CONTRIBUYENTE_EVENTUAL_SOCIAL 	= "Pequeño contribuyente eventual social";

	
	private ConstantesPosicionesFrenteAIVA() {}
	
	public static List<String> getAll()
	{
		List<String> posicionesFrenteAIVA = new LinkedList<String>();
		
		posicionesFrenteAIVA.add(RESPONSABLE_INSCRIPTO);
		posicionesFrenteAIVA.add(RESPONSABLE_NO_INSCRIPTO);
		posicionesFrenteAIVA.add(NO_RESPONSABLE);
		posicionesFrenteAIVA.add(SUJETO_EXENTO);
		posicionesFrenteAIVA.add(CONSUMIDOR_FINAL);
		posicionesFrenteAIVA.add(RESPONSABLE_MONOTRIBUTO);
		posicionesFrenteAIVA.add(SUJETO_NO_CATEGORIZADO);
		posicionesFrenteAIVA.add(PROVEEDOR_DEL_EXTERIOR);
		posicionesFrenteAIVA.add(CLIENTE_DEL_EXTERIOR);
		posicionesFrenteAIVA.add(LIBERADO);
		posicionesFrenteAIVA.add(RESPONSABLE_INSCRIPTO_AGENTE_DE_PERCEPCION);
		posicionesFrenteAIVA.add(PEQUENIO_CONTRIBUYENTE_EVENTUAL);
		posicionesFrenteAIVA.add(MONOTRIBUTISTA_SOCIAL);
		posicionesFrenteAIVA.add(PEQUENIO_CONTRIBUYENTE_EVENTUAL_SOCIAL);
		
		return posicionesFrenteAIVA;
	}
}
