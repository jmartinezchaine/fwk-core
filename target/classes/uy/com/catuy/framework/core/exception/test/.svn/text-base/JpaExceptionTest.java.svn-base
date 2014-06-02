/*
 * HibernateExceptionTest.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 *
 * Fecha de creaci�n: 01/10/2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.exception.test;


import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import uy.com.catuy.framework.core.exception.BusinessException;
import uy.com.catuy.framework.core.exception.InfrastructureException;

/**
 * Realiza las pruebas para el framework de excepciones con JPA.
 * 
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801678 $)
 * 
 * @version $LastChangedRevision: 264 $ $LastChangedDate: 2009-12-03 14:41:58 -0200 (Thu, 03 Dec 2009) $
 */
public class JpaExceptionTest {

	private static Logger log_ = Logger.getLogger(HibernateExceptionTest.class);

	@BeforeClass
	public static void inicializar() {
		String[] paths = { "/application-context-jpa.xml" };
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
		ctx.setConfigLocations(paths);
		ctx.refresh();
		log_.info("Se cargo el contexto Spring.");
	}

	@Test
	public void pruebaJpa() {
		log_.info("Comienza el test de Hibernate");
		BusinessException be = new BusinessException("GE", 1);
		log_.info("Excepcion de Negocio: " + be.getMessage());
		InfrastructureException ie = new InfrastructureException("GE", 1);
		log_.info("Excepcion de Infraestructura: " + ie.getMessage());
	}

	@Test
	public void pruebaMensajeGenerico() {
		log_.info("Comienza el test de Hibernate");
		BusinessException be = new BusinessException("HX", 1);
		log_.info("Excepcion de Negocio: " + be.getMessage());
	}
	
}
