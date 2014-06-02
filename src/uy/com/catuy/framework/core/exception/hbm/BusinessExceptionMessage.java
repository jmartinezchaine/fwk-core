/*
 * BusinessExceptionMessage.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 *
 * Fecha de creaci�n: Sep 26, 2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.exception.hbm;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representa el mensaje de una excepcion de negocio.
 * 
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801678 $)
 * 
 * @version $LastChangedRevision: 277 $ $LastChangedDate: 2009-12-03 17:15:16 -0200 (Thu, 03 Dec 2009) $
 */
@Entity
@Table(name = "FWT_BUSINESS_EXCEPTION")
public class BusinessExceptionMessage extends AbstractExceptionMessage {

	/**
	 * UID para la serializacion.
	 */
	private static final long serialVersionUID = 5366064566869083787L;

}
