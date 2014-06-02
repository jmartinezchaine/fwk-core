/*
 * Dao.java Proyecto: Framework Fecha de creaci�n: 14/07/2009 Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Interfaz que define el comportamiento gen�rico para los <i>DAO (Data Access Object).</i>
 * 
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 803309 $)
 * @version $LastChangedRevision: 9197 $ $LastChangedDate: 2009-11-09 11:13:48 +0000 (lun, 09 nov 2009) $
 * @param <T>
 *            elemento generico
 * @param <Key>
 */
public interface Dao<T, Key extends Serializable> {

	/**
	 * Realiza el INSERT en la base de datos.
	 * 
	 *@param obj
	 *@return el elemento ingresado
	 */
	Serializable insertar(T obj);

	/**
	 *Actualiza el objeto a la sesion de Hibernate.
	 * 
	 * @param obj
	 */
	void actualizar(T obj);

	/**
	 *Insreta o Actualiza el objeto a la sesion de Hibernate, seg�n sea el caso.
	 * 
	 * @param obj
	 */
	void insertarActualizar(T obj);

	/**
	 * Elimina el objeto.
	 * 
	 * @param obj
	 */
	void eliminar(T obj);

	/**
	 * Elimina la lista de objetos en la coleccion.
	 * 
	 * @param coleccion
	 */
	void eliminarTodos(Collection<T> coleccion);

	/**
	 * Realiza la inicializaci�n del objeto.
	 * 
	 * @param obj
	 */
	void inicializar(T obj);

	/**
	 * Actualiza los elementos en la colecci�n.
	 * 
	 * @param coleccion
	 */
	void actualizarColeccion(java.util.Collection<T> coleccion);

	/**
	 * Realiza la consulta de todos los elementos en el repositorio de datos.
	 * 
	 * @param clase
	 * @return lista de elementos
	 */
	List<T> consultarTodos();

	/**
	 * Realiza la consutla de todos los elementos activos de un catalogo.
	 * Retorna la lista ordenada por nombre.
	 * 
	 * @return
	 */
	List<T> consultarCatalogoActivo();

	/**
	 * Realiza la consulta de todos los elementos en el repositorio de datos de manera ascendente.
	 * 
	 * @param clase
	 * @param attribute
	 *            columna a la cual se le aplica el orden ascendente
	 * @return lista de elementos
	 */
	List<T> consultarTodosAsc(final String attribute);

	/**
	 * Realiza la b�squeda por la clave primaria de la tabla.
	 * 
	 * @param clase
	 * @param identificador
	 * @return el elemento
	 */
	T buscarPorIdentificador(Key identificador);

	/**
	 * Realiza la b�squeda dado un objeto de ejemplo.
	 * 
	 * @param ejemplo
	 * @param noIncluirPropiedades
	 * @return lista de elementos
	 */
	List<T> buscarPorEjemplo(final T ejemplo, final String... noIncluirPropiedades);
}
