/*
 * HibernateExceptionDaoImpl.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 *
 * Fecha de creaci�n: Sep 27, 2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.exception.dao.impl;

import java.io.IOException;
import java.util.Properties;


import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uy.com.catuy.framework.core.exception.InfrastructureRuntimeException;
import uy.com.catuy.framework.core.exception.dao.ExceptionDao;
import uy.com.catuy.framework.core.exception.hbm.AbstractExceptionMessage;
import uy.com.catuy.framework.core.exception.hbm.ExceptionMessageId;

/**
 * Implementacion Hibernate del DAO de excepciones.
 * 
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801678 $)
 * 
 * @version $LastChangedRevision: 263 $ $LastChangedDate: 2009-12-03 14:39:35 -0200 (Thu, 03 Dec 2009) $
 */
public class HibernateExceptionDaoImpl extends HibernateDaoSupport implements
		ExceptionDao {

	private Class<? extends AbstractExceptionMessage> exceptionMessageClass;

	private Properties properties;

	private String errorMessageKey;

	private boolean appendMessageCode;

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * Constructor para la clase HibernateExceptionDaoImpl.
	 */
	public HibernateExceptionDaoImpl() {
		properties = new Properties();
	}

	/**
	 * Establece el valor del atributo exceptionMessageClass.
	 * 
	 * @param exceptionMessageClass
	 *            el valor a establecer en exceptionMessageClass
	 */
	public void setExceptionMessageClass(
			Class<? extends AbstractExceptionMessage> exceptionMessageClass) {
		this.exceptionMessageClass = exceptionMessageClass;
	}

	/**
	 * Establece la ubicacion del archivo de propiedades con el mensaje de error generico para las
	 * excepciones.
	 * 
	 * @param location
	 *            ubicacion del archivo de propiedades
	 */
	public void setResourceBundleLocation(Resource location) {

		try {
			properties.load(location.getInputStream());
		} catch (IOException e) {
			throw new InfrastructureRuntimeException(e.getLocalizedMessage(), e);
		}

	}

	/**
	 * Establece el nombre de la propiedad con el mensaje de error generico para las excepciones.
	 * 
	 * @param errorMessageKey
	 *            nombre de la propiedad con el mensaje de error
	 */
	public void setErrorMessageKey(String errorMessageKey) {
		this.errorMessageKey = errorMessageKey;
	}

	/**
	 * Establece el valor booleano que indica si debe concatenarse el codigo de error (modulo + nro. mensaje)
	 * a los mensajes de las excepciones.
	 * 
	 * @param appendMessageCode
	 *            el valor a establecer en appendMessageCode
	 */
	public void setAppendMessageCode(boolean appendMessageCode) {
		this.appendMessageCode = appendMessageCode;
	}

	/**
	 * {@inheritDoc}
	 */
	public String obtenerMensaje(String modulo, int idMensaje) {

		if (exceptionMessageClass == null) {
			throw new IllegalStateException(
					Constantes.EXCEPTION_NO_MESSAGE_CLASS);
		}

		try {
			ExceptionMessageId id = new ExceptionMessageId(modulo, idMensaje);

			Object o = getHibernateTemplate().get(exceptionMessageClass, id);

			if (o == null) {
				return obtenerMensajeGenerico(modulo, idMensaje);
			}

			StringBuffer mensaje = new StringBuffer();

			if (appendMessageCode) {
				mensaje.append(modulo);
				mensaje.append(Constantes.SEPARADOR_MODULO_ID);
				mensaje.append(idMensaje);
				mensaje.append(Constantes.SEPARADOR_CODIGO_MENSAJE);
			}

			mensaje.append(((AbstractExceptionMessage) o).getMensaje());

			return mensaje.toString();
		} catch (DataAccessException e) {
			logger.error(e.getMessage(), e);

			return obtenerMensajeGenerico(modulo, idMensaje);
		}

	}

	/**
	 * Obtiene el mensaje por omision para la excepcion en el caso que no sea posible obtenerlo desde la
	 * fuente de datos.
	 * 
	 * @param modulo
	 *            codigo del modulo
	 * @param idMensaje
	 *            identificador del mensaje en el modulo
	 * @return mensaje por omision para la excepcion
	 */
	private String obtenerMensajeGenerico(String modulo, int idMensaje) {
		StringBuffer mensaje = new StringBuffer();

		if (appendMessageCode) {
			mensaje.append(modulo);
			mensaje.append(Constantes.SEPARADOR_MODULO_ID);
			mensaje.append(idMensaje);
			mensaje.append(Constantes.SEPARADOR_CODIGO_MENSAJE);
		}

		String mensajeGenerico = null;

		if (!properties.isEmpty()) {
			String key = Constantes.DEFAULT_GENERIC_MESSAGE_KEY;

			if (errorMessageKey == null) {
				mensajeGenerico = properties.getProperty(key);
			} else {
				mensajeGenerico = properties.getProperty(errorMessageKey);
			}

		}

		if (mensajeGenerico == null) {
			mensaje.append(Constantes.EXCEPTION_NO_MESSAGE_FOUND);
		} else {
			mensaje.append(mensajeGenerico);
		}

		return mensaje.toString();
	}

}
