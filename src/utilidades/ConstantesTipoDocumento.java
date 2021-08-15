package utilidades;

import java.util.LinkedList;
import java.util.List;

public class ConstantesTipoDocumento 
{
	public static String DNI = "DNI";
	public static String LE = "LE";
	public static String LC = "LC";
	public static String PASAPORTE = "Pasaporte";
	public static String OTRO = "Otro";
	
	private ConstantesTipoDocumento() {}
	
	public static List<String> getAll()
	{
		List<String> tiposDocumento = new LinkedList<String>();
		
		tiposDocumento.add(DNI);
		tiposDocumento.add(LE);
		tiposDocumento.add(LC);
		tiposDocumento.add(PASAPORTE);
		tiposDocumento.add(OTRO);
		
		return tiposDocumento;
	}
}
