/*
 * BusinessException.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 *
 * Fecha de creaci�n: Sep 27, 2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.exception;

import java.util.List;

import uy.com.catuy.framework.core.exception.dao.ExceptionDao;
import uy.com.catuy.framework.core.exception.dao.ExceptionDaoFactory;


/**
 * Excepcion lanzada cuando no se cumple una condicion necesaria a nivel de negocio.
 *
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801678 $)
 *
 * @version $LastChangedRevision: 22 $ $LastChangedDate: 2009-09-28 01:12:35 -0300 (Mon, 28 Sep 2009) $
 */
public class BusinessException extends AbstractException {

	/**
	 * UID para la serializacion.
	 */
	private static final long serialVersionUID = -1709482535634911843L;

	/**
	 * Constructor para la clase BusinessException.
	 *
	 * @param msg mensaje de la excepcion
	 */
	public BusinessException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor para la clase BusinessException.
	 *
	 * @param msg mensaje de la excepcion
	 * @param cause causa de la excepcion
	 */
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	/**
	 * Constructor para la clase BusinessException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 */
	public BusinessException(String modulo, int idMensaje) {
		super(modulo, idMensaje);
	}
	
	/**
	 * Constructor para la clase BusinessException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 * @param cause causa de la excepcion
	 */
	public BusinessException(String modulo, int idMensaje, Throwable cause) {
		super(modulo, idMensaje, cause);
	}
	
	/**
	 * Constructor para la clase BusinessException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 * @param parametros lista de parametros a reemplazar en el mensaje de la excepcion
	 */
	public BusinessException(String modulo, int idMensaje,
			List<String> parametros) {
		super(modulo, idMensaje, parametros);
	}
	
	/**
	 * Constructor para la clase BusinessException.
	 *
	 * @param modulo codigo del modulo funcional al que corresponde la excepcion
	 * @param idMensaje numero del mensaje al que corresponde la excepcion
	 * @param parametros lista de parametros a reemplazar en el mensaje de la excepcion
	 * @param cause causa de la excepcion
	 */
	public BusinessException(String modulo, int idMensaje,
			List<String> parametros, Throwable cause) {
		super(modulo, idMensaje, parametros, cause);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ExceptionDao getExceptionDao() {
		return ExceptionDaoFactory.getBusinessExceptionDao();
	}

}
