/*
 * MultipleBusinessException.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 *
 * Fecha de creaci�n: Sep 27, 2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.exception;

import java.util.List;

/**
 * Excepcion que encapsula varios mensajes de error.
 *
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801678 $)
 *
 * @version $LastChangedRevision: 22 $ $LastChangedDate: 2009-09-28 01:12:35 -0300 (Mon, 28 Sep 2009) $
 */
public class MultipleBusinessException extends BusinessException {

	/**
	 * UID para la serializacion.
	 */
	private static final long serialVersionUID = 6733994502898529599L;
	
	private List<String> mensajes;
	
	/**
	 * Constructor para la clase MultipleBusinessException.
	 *
	 * @param mensajes lista de mensajes de error
	 */
	public MultipleBusinessException(List<String> mensajes) {
		super("");
		
		this.mensajes = mensajes;
	}
	
	/**
	 * Retorna la lista de mensajes de error encapsulados en la excepcion.
	 *
	 * @return lista de mensajes de error
	 */
	public List<String> getMensajes() {
		return mensajes;
	}
	
}
