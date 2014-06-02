/*
 * AbstractExceptionMessage.java
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
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Clase base abstracta para representar los mensajes de las excepciones.
 *
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 800956 $)
 *
 * @version $LastChangedRevision: 28 $ $LastChangedDate: 2009-10-01 15:27:14 -0300 (Thu, 01 Oct 2009) $
 */
@MappedSuperclass
public abstract class AbstractExceptionMessage implements Serializable {

	/**
	 * UID para la serializacion.
	 */
	private static final long serialVersionUID = -4678801527660679728L;

	@Id
	private ExceptionMessageId id;
	
	@Column(name = "DES_MENSAJE")
	private String mensaje;

	/**
	 * Obtiene el valor del atributo id.
	 *
	 * @return el valor de id
	 */
	public ExceptionMessageId getId() {
		return id;
	}

	/**
	 * Establece el valor del atributo id.
	 *
	 * @param id el valor a establecer en id
	 */
	public void setId(ExceptionMessageId id) {
		this.id = id;
	}

	/**
	 * Obtiene el valor del atributo mensaje.
	 *
	 * @return el valor de mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Establece el valor del atributo mensaje.
	 *
	 * @param mensaje el valor a establecer en mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AbstractExceptionMessage other = (AbstractExceptionMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
