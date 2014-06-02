/*
 * ExceptionMessageId.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 *
 * Fecha de creaci�n: Sep 26, 2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.exception.hbm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Identificador para los mensajes de las excepciones.
 * 
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 800956 $)
 * 
 * @version $LastChangedRevision: 28 $ $LastChangedDate: 2009-10-01 15:27:14 -0300 (Thu, 01 Oct 2009) $
 */
@Embeddable
public class ExceptionMessageId implements Serializable {

	/**
	 * UID para la serializacion.
	 */
	private static final long serialVersionUID = -5924868699574493680L;

	@Column(name = "CVE_MODULO")
	private String modulo;

	@Column(name = "NUM_MENSAJE")
	private int idMensaje;

	/**
	 * Constructor para la clase ExceptionMessageId.
	 */
	public ExceptionMessageId() {

	}

	/**
	 * Constructor para la clase ExceptionMessageId.
	 * 
	 * @param modulo
	 * @param idMensaje
	 */
	public ExceptionMessageId(String modulo, int idMensaje) {
		super();
		this.modulo = modulo;
		this.idMensaje = idMensaje;
	}

	/**
	 * Obtiene el valor del atributo modulo.
	 * 
	 * @return el valor de modulo
	 */
	public String getModulo() {
		return modulo;
	}

	/**
	 * Establece el valor del atributo modulo.
	 * 
	 * @param modulo
	 *            el valor a establecer en modulo
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	/**
	 * Obtiene el valor del atributo idMensaje.
	 * 
	 * @return el valor de idMensaje
	 */
	public int getIdMensaje() {
		return idMensaje;
	}

	/**
	 * Establece el valor del atributo idMensaje.
	 * 
	 * @param idMensaje
	 *            el valor a establecer en idMensaje
	 */
	public void setIdMensaje(int idMensaje) {
		this.idMensaje = idMensaje;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMensaje;
		result = prime * result + ((modulo == null) ? 0 : modulo.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionMessageId other = (ExceptionMessageId) obj;
		if (idMensaje != other.idMensaje)
			return false;
		if (modulo == null) {
			if (other.modulo != null)
				return false;
		} else if (!modulo.equals(other.modulo))
			return false;
		return true;
	}

}
