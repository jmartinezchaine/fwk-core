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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import uy.com.catuy.framework.core.exception.BusinessException;
import uy.com.catuy.framework.core.exception.InfrastructureException;

/**
 * Realiza las pruebas para el framework de excepciones con Hibernate.
 * 
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801678 $)
 * 
 * @version $LastChangedRevision: 264 $ $LastChangedDate: 2009-12-03 14:41:58 -0200 (Thu, 03 Dec 2009) $
 */
public class HibernateExceptionTest {

	private static ClassPathXmlApplicationContext ctx;

	private static Logger log_ = Logger.getLogger(HibernateExceptionTest.class);

	private SessionFactory sessionFactory_;

	@BeforeClass
	public static void inicializar() {
		try {
			String[] paths = { "/application-context-hibernate.xml" };
			ctx = new ClassPathXmlApplicationContext();
			ctx.setConfigLocations(paths);
			ctx.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Before
	public void inicializarSesion() {
		sessionFactory_ = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sessionFactory_.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory_,
				new SessionHolder(session));
		log_.info("Se inicializa Sesion.");
	}

	@After
	public void cerrarSesion() {
		sessionFactory_.getCurrentSession().close();
		TransactionSynchronizationManager.unbindResource(sessionFactory_);
		log_.info("Se finaliza Sesion.");
	}

	@Test
	public void pruebaHibernate() {
		log_.info("Comienza el test de Hibernate");
		BusinessException be = new BusinessException("GE", 1);
		log_.info("Excepcion de Negocio: " + be.getMessage());
		InfrastructureException ie = new InfrastructureException("GE", 1);
		log_.info("Excepcion de Infraestructura: " + ie.getMessage());
	}

	@Test
	public void pruebaCache() {
		log_.info("Comienza el test de Hibernate");
		BusinessException be = new BusinessException("GE", 1);
		log_.info("Excepcion de Negocio: " + be.getMessage());
		InfrastructureException ie = new InfrastructureException("GE", 1);
		log_.info("Excepcion de Infraestructura: " + ie.getMessage());
		ie = new InfrastructureException("GE", 2);
		log_.info("Excepcion de Infraestructura: " + ie.getMessage());
	}
	
	@Test
	public void pruebaMensajeGenerico() {
		log_.info("Comienza el test de Hibernate");
		BusinessException be = new BusinessException("HX", 1);
		log_.info("Excepcion de Negocio: " + be.getMessage());
	}
	
}
