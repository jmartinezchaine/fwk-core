/*
 * DaoHibernateBase.java Proyecto: Framework Fecha de creaci�n: 14/07/2009 Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Dao gen�rico el cual provee m�todos comunes para todos los Daos del sistema. Clase abstracta que provee una implementaci�n gen�rica de la interfaz DAO utilizando el framework de <i>Hibernate</i>.
 * Sirve como clase base para todas las clases que implementan las distintas interfaces DAO utilizando Hibernate.
 * 
 * @param <T>
 *            Tipo de objeto base con que se parametriza a la clase gen�rica en cada subclase concreta.
 * @param <Key>
 *            Clase que se utilizar� para el identificador de objeto en cada subclase concreta.
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 803309 $)
 * @version $LastChangedRevision: 9197 $ $LastChangedDate: 2009-11-09 11:13:48 +0000 (lun, 09 nov 2009) $
 */
public abstract class DaoHibernateBase<T, Key extends Serializable> extends HibernateDaoSupport implements Dao<T, Key> {

	private Class<T>	clasePersistente;

	/**
	 * Constructor para la clase DaoHibernateBase.
	 */
	@SuppressWarnings("unchecked")
	public DaoHibernateBase() {
		this.clasePersistente = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	/**
	 * @return class
	 */
	protected Class<T> getClasePersistente() {
		return clasePersistente;
	}

	/**
	 * {@inheritDoc}
	 */
	public void actualizar(T obj) {
		getHibernateTemplate().update(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertarActualizar(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public void actualizarColeccion(Collection<T> coleccion) {
		getHibernateTemplate().saveOrUpdateAll(coleccion);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> consultarTodos() {
		return getHibernateTemplate().loadAll(getClasePersistente());
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> consultarTodosAsc(String attribute) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getClasePersistente());
		criteria.addOrder(Order.asc(attribute));

		List<T> resultados = getHibernateTemplate().findByCriteria(criteria);

		return resultados;
	}

	/**
	 * {@inheritDoc}
	 */
	public void eliminar(T obj) {
		getHibernateTemplate().delete(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public void eliminarTodos(Collection<T> coleccion) {
		getHibernateTemplate().deleteAll(coleccion);
	}

	/**
	 * {@inheritDoc}
	 */
	public void inicializar(T obj) {
		getHibernateTemplate().initialize(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public Serializable insertar(T obj) {
		return this.getHibernateTemplate().save(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarPorEjemplo(T ejemplo, String... noIncluirPropiedades) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ejemplo.getClass());
		Example example = Example.create(ejemplo);
		for (String propiedad : noIncluirPropiedades) {
			example.excludeProperty(propiedad);
		}
		// Excluye las props vacias
		example.excludeZeroes();
		criteria.add(example);
		List<T> resultado = getHibernateTemplate().findByCriteria(criteria);

		return resultado;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T buscarPorIdentificador(Key identificador) {
		return (T) getHibernateTemplate().get(getClasePersistente(), identificador);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> consultarCatalogoActivo() {
		DetachedCriteria criteria = DetachedCriteria.forClass(getClasePersistente());
		criteria.add(Restrictions.eq("activo", Boolean.TRUE));
		criteria.addOrder(Order.asc("nombre"));
		List<T> resultados = getHibernateTemplate().findByCriteria(criteria);

		return resultados;
	}
}
