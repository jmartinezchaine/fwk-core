/*
 * DateUtil.java
 *
 * Proyecto: Marco de Desarrollo de Aplicaciones CA&T Uruguay
 * 
 * Fecha de creaci�n: 8/05/2009
 *
 * Autor: Juan Andres Martinez Chaine
 */
package uy.com.catuy.framework.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

/**
 * Utilidades para manejo de fechas (formateo, operaciones, etc.).
 * 
 * @author Juan Andres Martinez Chaine ($LastChangedBy: 801765 $)
 * 
 * @version $LastChangedRevision: 9521 $ $LastChangedDate: 2009-11-13 19:49:08
 *          +0000 (vie, 13 nov 2009) $
 */
public final class DateUtil {

	/**
	 * Locale a utilizar para el formato de las fechas.
	 */
	public static final Locale LOCALE = new Locale("es", "UY");

	// private static final String DEFAULT_TIMEZONE = "America/Uruguay";

	private static final ThreadLocal<TimeZone> tz = new ThreadLocal<TimeZone>() {

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected TimeZone initialValue() {
			return TimeZone.getDefault();
			// return TimeZone.getTimeZone(DEFAULT_TIMEZONE);
		}

	};

	private static final String FECHA_FORMATO_DEFAULT = "dd/MM/yyyy";

	private static final long MS_POR_MINUTO = 60000;

	private static final long MS_POR_HORA = 60 * MS_POR_MINUTO;

	private static final long MS_POR_DIA = 24 * MS_POR_HORA;

	private static final int CTE_10000 = 10000;

	private static final int CTE_999 = 999;

	private static final int CTE_100 = 100;

	private static final int CTE_23 = 23;

	private static final int CTE_59 = 59;

	private static final int CTE_60 = 60;

	private static final int CTE_MINUTOS_FIN_DIA = (CTE_23 * CTE_60) + CTE_59;

	private static final int CTE_SEMANAS_POR_ANIO = 52;

	/**
	 * Enumeraci�n con diferentes tipos de formato predefinidos para fechas y
	 * horas.
	 */
	public enum Formato {
		FECHA_YYYY_MM_DD("yyyyMMdd"), FECHA_YYYY_G_MM_G_DD("yyyy-MM-dd"), FECHA_YYYY_B_MM_B_DD(
				"yyyy/MM/dd"),
		/**
		 * Formato.
		 */
		FECHA_CORTO("dd/MM/yyyy"),
		/**
		 * Formato.
		 */
		FECHA_MEDIO("d 'de' MMMM 'de' yyyy"),
		/**
		 * Formato.
		 */
		FECHA_LARGO("EEEE, d 'de' MMMM 'de' yyyy"),
		/**
		 * Formato.
		 */
		MES_CORTO("MM/yyyy"),
		/**
		 * Formato.
		 */
		MES_LARGO("MMMM yyyy"),
		/**
		 * Formato.
		 */
		HORA_CORTO("HH:mm"),
		/**
		 * Formato.
		 */
		HORA_LARGO("HH 'horas' mm 'minutos'"),
		/**
		 * Formato.
		 */
		COMPLETO_CORTO("dd/MM/yyyy HH:mm"),
		/**
		 * Formato.
		 */
		COMPLETO_CORTO_AM_PM_CON_SEGUNDOS("dd/MM/yyyy hh:mm:ss a"),
		/**
		 * Formato.
		 */
		COMPLETO_MEDIO("d 'de' MMMM 'de' yyyy HH:mm"),
		/**
		 * Formato.
		 */
		COMPLETO_LARGO("EEEE, d 'de' MMMM 'de' yyyy HH:mm"),
		/**
		 * Formato.
		 */
		ANIO_CORTO("yy");

		private String valor;

		/**
		 * Constructor para la clase Formato.
		 * 
		 * @param valor
		 *            tipo de formato
		 */
		private Formato(String valor) {
			this.valor = valor;
		}

	}

	/**
	 * Enumeraci�n con los campos de una fecha.
	 */
	public enum Campo {
		/**
		 * Formato.
		 */
		DIAS(Calendar.DAY_OF_MONTH),
		/**
		 * Formato.
		 */
		MESES(Calendar.MONTH),
		/**
		 * Formato.
		 */
		ANIOS(Calendar.YEAR),
		/**
		 * Formato.
		 */
		HORAS(Calendar.HOUR_OF_DAY),
		/**
		 * Formato.
		 */
		MINUTOS(Calendar.MINUTE),
		/**
		 * Formato.
		 */
		SEGUNDOS(Calendar.SECOND);

		private int codigo;

		/**
		 * Constructor para la clase Campo.
		 * 
		 * @param codigo
		 *            del formato
		 */
		private Campo(int codigo) {
			this.codigo = codigo;
		}

	}

	/**
	 * Constructor para la clase DateUtil.
	 */
	private DateUtil() {
	}

	/**
	 * Obtiene la zona horaria para las fechas manejadas por el thread actual.
	 * 
	 * @return zona horaria
	 */
	public static TimeZone getTimeZone() {
		return tz.get();
	}

	/**
	 * Establece la zona horaria a utilizar en las fechas manejadas por el
	 * thread actual.
	 * 
	 * @param timeZone
	 *            zona horaria
	 */
	public static void setTimeZone(TimeZone timeZone) {
		tz.set(timeZone);
	}

	/**
	 * Libera la variable con la zona horaria para este thread.
	 */
	public static void removeTimeZone() {
		tz.remove();
	}

	/**
	 * Crea un objeto Date con la fecha del sistema (fecha actual).
	 * 
	 * @return objeto Date con la fecha del sistema
	 */
	public static Date crearDate() {
		return crearCalendar().getTime();
	}

	/**
	 * Crea un objeto Calendar con la fecha del sistema (fecha actual).
	 * 
	 * @return objeto Calendar con la fecha del sistema
	 */
	public static Calendar crearCalendar() {
		return Calendar.getInstance(tz.get(), LOCALE);
	}

	/**
	 * Crea un objeto Date a partir de un String que debe estar en el formato
	 * por omisi�n para fechas ("dd/mm/aaaa").
	 * 
	 * @param fecha
	 *            en formato "dd/mm/aaaa"
	 * @return objeto Date con la fecha indicada
	 * @throws ParseException
	 *             si el formato de la fecha es inv�lido
	 */
	public static Date crearDate(String fecha) throws ParseException {
		return crearDate(fecha, FECHA_FORMATO_DEFAULT);
	}

	/**
	 * Crea un objeto Date a partir de un String que debe estar en un formato
	 * especificado, seg�n las reglas del SimpleDateFormat.
	 * 
	 * @param fecha
	 *            fecha en el formato correspondiente
	 * @param formato
	 *            formato de la fecha, de acuerdo a SimpleDateFormat
	 * @return objeto Date con la fecha indicada
	 * @throws ParseException
	 *             si el formato de la fecha es inv�lido
	 */
	public static Date crearDate(String fecha, String formato)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formato, LOCALE);

		sdf.setTimeZone(tz.get());

		return sdf.parse(fecha);
	}

	/**
	 * Aplica a una fecha el formato por omisi�n (dd/mm/aaaa).
	 * 
	 * @param fecha
	 *            fecha a la que se le aplicar� el formato
	 * @return fecha con el formato aplicado
	 */
	public static String aplicarFormato(Date fecha) {
		return aplicarFormato(fecha, FECHA_FORMATO_DEFAULT);
	}

	/**
	 * Aplica a una fecha el formato especificado, que debe ser parte de los
	 * formatos predeterminados especificados en la enumeraci�n Formato. La
	 * primera letra del d�a y del mes se pasan a may�scula.
	 * 
	 * @param fecha
	 *            fecha a la que se le aplicar� el formato
	 * @param formato
	 *            formato (de la enumeraci�n Formato) a aplicar
	 * @return fecha con el formato aplicado
	 */
	public static String aplicarFormato(Date fecha, Formato formato) {
		String fechaFormateada = aplicarFormato(fecha, formato.valor);

		String[] fechaSeparada = StringUtils.split(fechaFormateada);

		switch (formato) {
		case FECHA_MEDIO:
			// FECHA_MEDIO("d 'de' MMMM 'de' yyyy")
			fechaSeparada[2] = StringUtils.capitalize(fechaSeparada[2]);
			break;
		case FECHA_LARGO:
			// FECHA_LARGO("EEEE, d 'de' MMMM 'de' yyyy")
			fechaSeparada[0] = StringUtils.capitalize(fechaSeparada[0]);
			fechaSeparada[3] = StringUtils.capitalize(fechaSeparada[3]);
			break;
		case MES_LARGO:
			// MES_LARGO("MMMM yyyy")
			fechaSeparada[0] = StringUtils.capitalize(fechaSeparada[0]);
			break;
		case COMPLETO_MEDIO:
			// COMPLETO_MEDIO("d 'de' MMMM 'de' yyyy HH:mm")
			fechaSeparada[2] = StringUtils.capitalize(fechaSeparada[2]);
			break;
		case COMPLETO_LARGO:
			// COMPLETO_LARGO("EEEE, d 'de' MMMM 'de' yyyy HH:mm")
			fechaSeparada[0] = StringUtils.capitalize(fechaSeparada[0]);
			fechaSeparada[3] = StringUtils.capitalize(fechaSeparada[3]);
			break;
		default:
		}

		fechaFormateada = StringUtils.join(fechaSeparada, ' ');

		return fechaFormateada;
	}

	/**
	 * Aplica a una fecha el formato especificado en un String seg�n las reglas
	 * de la clase SimpleDateFormat.
	 * 
	 * @param fecha
	 *            fecha a la que se le aplicar� el formato
	 * @param formato
	 *            formato a aplicar, de acuerdo a SimpleDateFormat
	 * @return fecha con el formato aplicado
	 */
	public static String aplicarFormato(Date fecha, String formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato, LOCALE);

		sdf.setTimeZone(tz.get());

		return sdf.format(fecha);
	}

	/**
	 * Suma a una fecha una cantidad especificada en uno de los campos de la
	 * fecha (d�as, meses, a�os, horas o minutos).
	 * 
	 * @param fecha
	 *            fecha a la cual se sumar� el valor especificado
	 * @param campo
	 *            campo de la fecha en el cual se sumar� la cantidad indicada
	 * @param cantidad
	 *            cantidad a sumar a la fecha
	 * @return objeto Date obtenido tras efectuar la suma
	 */
	public static Date sumar(Date fecha, Campo campo, int cantidad) {
		GregorianCalendar gc = new GregorianCalendar(tz.get(), LOCALE);

		gc.setTime(fecha);
		gc.add(campo.codigo, cantidad);

		return gc.getTime();
	}

	/**
	 * Calcula el tiempo transcurrido entre dos fechas, el resultado se retorna
	 * en la unidad especificada (d�as, meses, a�os, horas o minutos).
	 * 
	 * @param hasta
	 *            fecha final del intervalo de tiempo
	 * @param desde
	 *            fecha inicial del intervalo de tiempo
	 * @param campo
	 *            unidad en la que se devolver� el resultado
	 * @return diferencia entre las fechas, en la unidad indicada
	 */
	public static long calcularDiferencia(Date hasta, Date desde, Campo campo) {
		long diferencia = 0;

		switch (campo.codigo) {
		case Calendar.MINUTE:
			diferencia = (hasta.getTime() - desde.getTime()) / MS_POR_MINUTO;
			break;
		case Calendar.HOUR_OF_DAY:
			diferencia = (hasta.getTime() - desde.getTime()) / MS_POR_HORA;
			break;
		case Calendar.DAY_OF_MONTH:
			diferencia = (hasta.getTime() - desde.getTime()) / MS_POR_DIA;
			break;
		case Calendar.MONTH:
			int diferenciaCampos = calcularDiferenciaCampos(hasta, desde);

			diferencia = diferenciaCampos / CTE_10000 + diferenciaCampos
					% CTE_10000 / CTE_100;
			break;
		case Calendar.YEAR:
			diferencia = calcularDiferenciaCampos(hasta, desde) / CTE_10000;
			break;
		default:
		}

		return diferencia;
	}

	/**
	 * Retorna el numero de segundos pasados de la hora representada por la
	 * fecha indicada por par�mtro. Utiliza para la interpretaci�n el locale y
	 * time zone configurados en el Utilitario. El valor retornado es entre 0 y
	 * 59.
	 * 
	 * @param fecha
	 * @return El valor retornado es entre 0 y 59.
	 */
	public static int obtenerSegundos(Date fecha) {
		Calendar calendar = crearCalendar();
		calendar.setTime(fecha);

		return calendar.get(Calendar.SECOND);
	}

	/**
	 * Retorna el numero de minutos pasados de la hora representada por la fecha
	 * indicada por par�mtro. Utiliza para la interpretaci�n el locale y time
	 * zone configurados en el Utilitario. El valor retornado es entre 0 y 59.
	 * 
	 * @param fecha
	 * @return El valor retornado es entre 0 y 59.
	 */
	public static int obtenerMinutos(Date fecha) {
		Calendar calendar = crearCalendar();
		calendar.setTime(fecha);

		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * Retorna la hora representada por la fecha indicada por par�metro. El
	 * valor retornado es un num�rico entre 0 y 23. Utiliza para la
	 * interpretaci�n el locale y time zone configurados en el Utilitario.
	 * 
	 * @param fecha
	 * @return El valor retornado es entre 0 y 23.
	 */
	public static int obtenerHora(Date fecha) {
		Calendar calendar = crearCalendar();
		calendar.setTime(fecha);

		return calendar.get(Calendar.HOUR);
	}

	/**
	 * Retorna el numero de minutos transcurridos desde el inicio del d�a de la
	 * fecha. El valor retornado es entre 0 y 1439.
	 * 
	 * @param fecha
	 * @return El valor retornado es entre 0 y 59.
	 */
	public static int calcularMinutosTranscurridosDesdeInicioDia(Date fecha) {
		int horas = obtenerHora(fecha);
		int minutos = obtenerMinutos(fecha);

		return horas * CTE_60 + minutos;
	}

	/**
	 * Calcula la edad en a�os de una persona a partir de su fecha de
	 * nacimiento.
	 * 
	 * @param fechaNacimiento
	 *            fecha de nacimiento
	 * @return edad en a�os
	 */
	public static int calcularEdad(Date fechaNacimiento) {
		int edad = calcularDiferenciaCampos(crearDate(), fechaNacimiento);

		return (edad / CTE_10000);
	}

	/**
	 * Calcula la edad en semanas de una persona a partir de su fecha de
	 * nacimiento.
	 * 
	 * @param fechaNacimiento
	 *            fecha de nacimiento
	 * @return edad en a�os
	 */
	public static int calcularEdadEnSemanas(Date fechaNacimiento) {
		int edad = calcularDiferenciaCampos(DateUtil.crearDate(),
				fechaNacimiento)
				/ CTE_10000;

		return edad * CTE_SEMANAS_POR_ANIO;
	}

	/**
	 * Calcula la edad exacta (en a�os, meses y d�as) de una persona a partir de
	 * su fecha de nacimiento. El resultado se devuelve como un n�mero entero de
	 * la forma aaammdd con la edad exacta. Por ejemplo, para una persona cuya
	 * edad es 26 a�os 9 meses y 23 d�as, el resultado de este m�todo ser�a el
	 * n�mero 260923. Los campos individuales pueden ser obtenidos a partir de
	 * este n�mero n de la siguiente manera:
	 * 
	 * - Edad en a�os: n / 10000 - Meses adicionales: n % 10000 / 100 - D�as
	 * adicionales: n % 100
	 * 
	 * @param fechaNacimiento
	 *            fecha de nacimiento
	 * @return n�mero entero de la forma aaammdd con la edad exacta
	 */
	public static int calcularEdadExacta(Date fechaNacimiento) {
		return calcularDiferenciaCampos(DateUtil.crearDate(), fechaNacimiento);
	}

	/**
	 * 
	 * M�todo auxiliar para calcular la diferencia entre dos fechas teniendo en
	 * cuenta que la cantidad de d�as var�a seg�n el mes.
	 * 
	 * @param hasta
	 *            fecha limite
	 * @param desde
	 *            fecha comienzo
	 * @return la cantidad de dias de la diferencia
	 */
	private static int calcularDiferenciaCampos(Date hasta, Date desde) {
		GregorianCalendar gc = new GregorianCalendar(tz.get(), LOCALE);

		// Obtengo los campos dia, mes y a�o de la fecha hasta
		gc.setTime(hasta);

		int diaHasta = gc.get(Calendar.DAY_OF_MONTH);
		int mesHasta = gc.get(Calendar.MONTH);
		int anioHasta = gc.get(Calendar.YEAR);

		// Obtengo los campos dia, mes y a�o de la fecha desde
		gc.setTime(desde);

		int diaDesde = gc.get(Calendar.DAY_OF_MONTH);
		int mesDesde = gc.get(Calendar.MONTH);
		int anioDesde = gc.get(Calendar.YEAR);

		// Obtengo las diferencias entre los tres campos
		int dias = diaHasta - diaDesde;
		int meses = mesHasta - mesDesde;
		int anios = anioHasta - anioDesde;

		if (dias < 0) {
			meses--;
			gc.setTime(hasta);
			gc.add(Calendar.MONTH, -1);
			dias += gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

		if (meses < 0) {
			anios--;
			meses += 12;
		}

		return (anios * CTE_10000 + meses * CTE_100 + dias);
	}

	/**
	 * Obtiene el objeto Date correspondiente al instante inicial (hora
	 * 00:00:00.000) del d�a especificado. Este m�todo puede ser �til al momento
	 * de especificar un filtro entre una fecha de inicio y una de fin, para
	 * considerar el d�a completo de la fecha de inicio.
	 * 
	 * @param fecha
	 *            fecha original
	 * @return objeto Date con el instante inicial de la fecha especificada
	 */
	public static Date obtenerInicioDia(Date fecha) {
		Calendar c = DateUtil.crearCalendar();

		c.setTime(fecha);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		return c.getTime();
	}

	/**
	 * Obtiene el objeto Date correspondiente al instante final (hora
	 * 23:59:59.999) del d�a especificado. Este m�todo puede ser �til al momento
	 * de especificar un filtro entre una fecha de inicio y una de fin, para
	 * considerar el d�a completo de la fecha de fin.
	 * 
	 * @param fecha
	 *            fecha original
	 * @return objeto Date con el instante final de la fecha especificada
	 */
	public static Date obtenerFinDia(Date fecha) {
		Calendar c = DateUtil.crearCalendar();

		c.setTime(fecha);
		c.set(Calendar.HOUR_OF_DAY, CTE_23);
		c.set(Calendar.MINUTE, CTE_59);
		c.set(Calendar.SECOND, CTE_59);
		c.set(Calendar.MILLISECOND, CTE_999);

		return c.getTime();
	}

	/**
	 * 
	 * Obtiene a partir de una fecha la edad en a�os.
	 * 
	 * @param fechaNacimiento
	 *            indica la fecha de nacimiento
	 * @return la edad en a�os para la fecha indicada
	 */
	public static int obtenerEdadEnAnios(Date fechaNacimiento) {
		return calcularEdadExacta(fechaNacimiento) / CTE_10000;
	}

	/**
	 * Obtiene a partir de una fecha los meses de la edad, por ejemplo si la
	 * persona tiene 30 a�os y 4 meses de vida retornar� 4.
	 * 
	 * @param fechaNacimiento
	 *            indica la fecha de nacimiento
	 * @return los meses para la edad actual
	 */
	public static int obtenerEdadEnMeses(Date fechaNacimiento) {
		return calcularEdadExacta(fechaNacimiento) % CTE_10000 / CTE_100;
	}

	/**
	 * Obtiene a partir de una fecha los d�as de la edad, por ejemplo si la
	 * persona tiene 6 meses y 4 d�as de vida retornar� 4.
	 * 
	 * @param fechaNacimiento
	 *            indica la fecha de nacimiento
	 * @return los d�as para la edad actual
	 */
	public static int obtenerEdadEnDias(Date fechaNacimiento) {
		return calcularEdadExacta(fechaNacimiento) % CTE_100;
	}

	/**
	 * Convierte la fecha de nacimiento en el formato: 'dd a�os dd meses'.
	 * Ejemplo: 27 a�os 6 mes.
	 * 
	 * @param fechaNascimiento
	 *            indica la fecha de nacimiento
	 * @return String conteniendo la edad en formato Mes y a�o. Si la fecha es
	 *         null retorna un string vacio.
	 */
	public static String obtenerEdad(Date fechaNacimiento) {
		StringBuffer edad = new StringBuffer();

		if (fechaNacimiento != null) {
			long meses = obtenerEdadEnMeses(fechaNacimiento);
			long anios = obtenerEdadEnAnios(fechaNacimiento);

			if (anios > 0) {
				edad.append(anios);

				if (anios == 1) {
					edad.append(" a�o ");
				} else {
					edad.append(" a�os ");
				}

			}

			if (meses > 0) {
				edad.append(meses);

				if (meses == 1) {
					edad.append(" mes ");
				} else {
					edad.append(" meses ");
				}

			}

			if (anios == 0) {
				long dias = obtenerEdadEnDias(fechaNacimiento);

				if (dias > 0) {
					edad.append(dias);

					if (dias == 1) {
						edad.append(" d�a");
					} else {
						edad.append(" d�as");
					}

				} else if (meses == 0) {
					edad.append("Reci�n nacido");
				}

			}

		}

		return edad.toString().trim();
	}

	/**
	 * Turnca la Fecha a hasta el campo especificado.
	 * 
	 * @param fecha
	 *            inicial
	 * @param campo
	 *            indica el campo a truncar
	 * @return fecha
	 */
	public static Date truncate(Date fecha, Campo campo) {
		Calendar calendar = DateUtil.crearCalendar();
		calendar.setTime(fecha);

		switch (campo.codigo) {
		case Calendar.YEAR:
			calendar.set(Calendar.MONTH, 0);
		case Calendar.MONTH:
			calendar.set(Calendar.DAY_OF_MONTH, 0);
		case Calendar.DAY_OF_MONTH:
			calendar.set(Calendar.HOUR_OF_DAY, 0);
		case Calendar.HOUR_OF_DAY:
			calendar.set(Calendar.MINUTE, 0);
		case Calendar.MINUTE:
			calendar.set(Calendar.SECOND, 0);
		case Calendar.SECOND:
			calendar.set(Calendar.MILLISECOND, 0);
		default:
			break;
		}

		return calendar.getTime();
	}

	/**
	 * Obtiene el d�a de la semana correspondiente a la fecha recibida. Tiene en
	 * cuenta la numeraci�n default de JAVA y la transforma en la utilizada en
	 * Ejemplo: 15/09/2009 --> retorna
	 * 
	 * @param fecha
	 * @return
	 */
	public static int obtenerNumeroDiaSemana(Date fecha) {
		Calendar calendar = DateUtil.crearCalendar();
		calendar.setTime(fecha);

		int dia = calendar.get(Calendar.DAY_OF_WEEK);

		if (dia == Calendar.SUNDAY) {
			dia = 7;
		} else {
			dia -= 1;
		}

		return dia;
	}

	/**
	 * 
	 * Retorna la cantidad de minutos que transcurren en un d�a.
	 * 
	 * @return cantidadMinutos.
	 */
	public static int obtenerMinutosFinDia() {
		return CTE_MINUTOS_FIN_DIA;
	}

	/**
	 * Obtiene la hora del dia en minutos y la devuelve en formato hh:mm.
	 * 
	 * @param minutos
	 * @return la hora
	 */
	public static String minutosAHora(int minutos) {
		StringBuffer resultado = new StringBuffer();

		String hora = String.valueOf(minutos / CTE_60);
		String minuto = String.valueOf(minutos % CTE_60);

		if (hora.length() == 1) {
			resultado.append("0");
		}

		resultado.append(hora);

		resultado.append(":");

		if (minuto.length() == 1) {
			resultado.append("0");
		}

		resultado.append(minuto);

		return resultado.toString();
	}

	/**
	 * Devuelve true en caso de que el d�a de la fecha corrsponda a fin de
	 * semana. False en otro caso.
	 * 
	 * @param fecha
	 * @return verdadero o falso
	 */
	public static boolean esFinDeSeamana(Date fecha) {
		boolean finDeSemana = false;
		GregorianCalendar gc = new GregorianCalendar(tz.get(), LOCALE);
		gc.setTime(fecha);

		if (gc.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
				|| gc.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			finDeSemana = true;
		}

		return finDeSemana;
	}

	/**
	 * Calcula la fecha en base a el a�o, mes, d�a de la semana y la ocasi�n
	 * (que hace referencia a la semana dentro del mes) Ej.: Segundo Lunes de
	 * Abril de 2009
	 * 
	 * @param dia
	 *            d�a de la semana (1-Lunes ... 7-Domingo)
	 * @param mes
	 *            mes (0-Enero al 11-Diciembre)
	 * @param anio
	 *            a�o
	 * @param ocasi
	 *            �n 1-PRIMER, 2-SEGUNDO, 3-TERCER, 4-CUARTO, 5-ULTIMO
	 * @return
	 */
	public static Date getFechaOcasion(int dia, int mes, int anio, int ocasion) {

		Calendar calendario = crearCalendar();
		calendario.set(Calendar.MONTH, mes);
		calendario.set(Calendar.YEAR, anio);

		if (ocasion >= 1 && ocasion <= 4) {
			int diaMes = 1;
			calendario.set(Calendar.DAY_OF_MONTH, diaMes);
			int diaSemana = obtenerNumeroDiaSemana(calendario.getTime());

			while (diaSemana != dia) {
				diaMes++;
				calendario.set(Calendar.DAY_OF_MONTH, diaMes);
				diaSemana = obtenerNumeroDiaSemana(calendario.getTime());
			}

			Date fecha = sumar(calendario.getTime(), Campo.DIAS,
					7 * (ocasion - 1));
			calendario.setTime(fecha);

		} else if (ocasion == 5) {
			int diaMes = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
			calendario.set(Calendar.DAY_OF_MONTH, diaMes);
			int diaSemana = obtenerNumeroDiaSemana(calendario.getTime());

			while (diaSemana != dia) {
				diaMes--;
				calendario.set(Calendar.DAY_OF_MONTH, diaMes);
				diaSemana = obtenerNumeroDiaSemana(calendario.getTime());
			}
		} else { // Valor de ocasi�n fuera del rango
			throw new IllegalArgumentException(
					"La valos del campo ocasi�n es inv�lida.");
		}

		return calendario.getTime();
	}
}
