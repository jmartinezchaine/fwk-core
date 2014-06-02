/*
 * AbstractRuntimeException.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 *
 * Fecha de creaci�n: Sep 27, 2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.exception;

import java.util.List;

import org.springframework.core.NestedRuntimeException;

import uy.com.catuy.framework.core.exception.dao.ExceptionDao;

/**
 * Excepcion abstracta que sirve como base para todas las excepciones runtime manejadas por el Framework.
 *
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801678 $)
 *
 * @version $LastChangedRevision: 236 $ $LastChangedDate: 2009-11-27 18:28:44 -0200 (Fri, 27 Nov 2009) $
 */
public abstract class AbstractRuntimeException extends NestedRuntimeException {

	/**
	 * UID para la serializacion.
	 */
	private static final long serialVersionUID = -2775577961841617756L;

	private static final String SEPARADOR_MENSAJE = "-";
	
	private String mensaje = null;
	
	/**
	 * Constructor para la clase AbstractRuntimeException.
	 *
	 * @param msg mensaje de la excepcion
	 */
	public AbstractRuntimeException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor para la clase AbstractRuntimeException.
	 *
	 * @param msg mensaje de la excepcion
	 * @param cause causa de la excepcion
	 */
	public AbstractRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * Constructor para la clase AbstractRuntimeException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 */
	public AbstractRuntimeException(String modulo, int idMensaje) {
		super(modulo + SEPARADOR_MENSAJE + idMensaje);
		
		mensaje = getExceptionDao().obtenerMensaje(modulo, idMensaje);
	}
	
	/**
	 * Constructor para la clase AbstractRuntimeException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 * @param cause causa de la excepcion
	 */
	public AbstractRuntimeException(String modulo, int idMensaje, Throwable cause) {
		super(modulo + SEPARADOR_MENSAJE + idMensaje, cause);
		
		mensaje = getExceptionDao().obtenerMensaje(modulo, idMensaje);
	}
	
	/**
	 * Constructor para la clase AbstractRuntimeException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 * @param parametros lista de parametros a reemplazar en el mensaje de la excepcion
	 */
	public AbstractRuntimeException(String modulo, int idMensaje, List<String> parametros) {
		super(modulo + SEPARADOR_MENSAJE + idMensaje);
		
		String plantilla = getExceptionDao().obtenerMensaje(modulo, idMensaje);
		
		mensaje = cargarParametros(plantilla, parametros);
	}
	
	/**
	 * Constructor para la clase AbstractRuntimeException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 * @param parametros lista de parametros a reemplazar en el mensaje de la excepcion
	 * @param cause causa de la excepcion
	 */
	public AbstractRuntimeException(String modulo, int idMensaje, List<String> parametros, Throwable cause) {
		super(modulo + SEPARADOR_MENSAJE + idMensaje, cause);
		
		String plantilla = getExceptionDao().obtenerMensaje(modulo, idMensaje);
		
		mensaje = cargarParametros(plantilla, parametros);
	}
	
	/**
	 * Retorna una referencia al DAO utilizado para obtener los mensajes de las excepciones.
	 *
	 * @return referencia al DAO de excepciones
	 */
	protected abstract ExceptionDao getExceptionDao();
	
	/**
	 * Reemplaza los valores de los parametros en un mensaje.
	 *
	 * @param plantilla mensaje parametrizado
	 * @param parametros valores de los parametros a reemplazar
	 * @return mensaje con los valores de los parametros reemplazados
	 */
	private String cargarParametros(String plantilla, List<String> parametros) {
		
		if (parametros == null) {
			throw new IllegalArgumentException("La lista de parametros no puede ser nula");
		}
		
		int nParametros = parametros.size();
		
		String[] paramNombres = new String[nParametros];
		String[] paramValores = parametros.toArray(new String[nParametros]);
		
		for (int i = 0; i < paramNombres.length; i++) {
			paramNombres[i] = "${P" + (i + 1) + "}";
		}
		
		return org.apache.commons.lang3.StringUtils.replaceEach(plantilla, paramNombres, paramValores);
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public String getMessage() {
		
		if (mensaje == null) {
			return super.getMessage();
		} else {
			return mensaje;
		}
		
	}
	
}
