package co.edu.uniandes.fuse.api.academico.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

public class UtilsAcademico {
	
	private final static Logger log = Logger.getLogger("");
	private static final SimpleDateFormat FECHA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	private static final DateFormat HORA = new SimpleDateFormat("HH:mm:ss.S");

	public static Date getColumnDate__(Object column) {
	    return column != null ? (Date)column : null;
	  }
	
	public static Date getColumnDate_(Object column) {
		return column != null ? UtilsAcademico.getDate((String) column) : null;
	}

	public static XMLGregorianCalendar getColumnDate(Object column) {
		return column != null ? UtilsAcademico.getDate((Date) column) : null;
	}

	public static String getColumnString(Object column) {
		return column != null ? (String) column : "";
	}

	public static String getColumnBigDecimalToString(Object column) {
		return column != null ? column.toString() : "";
	}

	public static Double getColumnDouble(Object column) {
		return column != null ? (Double) column : null;
	}

	public static Float getColumnFloatFromString(Object column) {
		return column != null ? Float.valueOf((String) column) : null;
	}
	
    public static String getColumnDoubleToString(Object column){
         return column != null ? ((Double)column).toString() : "";
    }

	public static Float getColumnFloatFromBigDecimal(Object column) {
		return column != null ? ((BigDecimal) column).floatValue() : null;
	}

	public static Long getColumnLongFromString(Object column) {
		return column != null ? Long.valueOf((String) column) : null;
	}

	public static Integer getColumnInteger(Object column) {
		return column != null ? (Integer) column : null;
	}

	public static Integer getColumnIntegerFromString(Object column) {
		return column != null ? Integer.valueOf((String) column) : null;
	}

	public static Integer getColumnIntegerFromBigDecimal(Object column) {
		return column != null ? ((BigDecimal) column).intValue() : null;
	}
	
	public static Integer getColumnIntegerFromBigDecimalInteger(Object column) {
		return column != null ? Integer.valueOf(((BigDecimal)column).intValue()) : null;
	}
	
	public static BigDecimal getColumnBigDecimal(Object column){
		return column != null ? ((BigDecimal) column) : new BigDecimal("0");
	}
	
	public static BigDecimal getColumnBigDecimalFromString(String column){
		return column != null ? new BigDecimal(column) : new BigDecimal("0");
	}

	public static Boolean getColumnBoolean(Object column) {
		return column != null ? (Boolean) column : null;
	}

	public static Boolean getColumnBooleanFromString(Object column) {
		return column != null ? Boolean.valueOf((String) column) : null;
	}
	

	public static XMLGregorianCalendar getDate(Date date) {
		try {
			GregorianCalendar gcal = new GregorianCalendar();
			gcal.setTime(date);
			XMLGregorianCalendar xgcal = getDate(gcal);
			return xgcal;
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDate(String date) {
		try {
			return FECHA.parse(date);
		} catch (Exception e) {
			return null;
		}

	}
	
	public static String getHoraString(String hora) {
		String horaFormat = "";
		
		try {
			Date convert = getColumnDate__(hora);
			log.info("verificando convert ---------------::::::::::::>"+ convert.toString());
			horaFormat = HORA.format(convert).toString();
			log.info("verificando horaFormat ---------------:::::::::>"+ horaFormat);
			return horaFormat;
		} catch (Exception e) {
			
		}
		return horaFormat;
	}
	
	public static String getColumnDateToString(Object date) {
		
		String dateR = "";
		try {
			Date result = getColumnDate_(date);
			 dateR = FECHA.format(result.toString());
			return dateR;
		} catch (Exception e) {
		}
		return dateR;
	}

	// public static Date getDate(Object date) {
	// try {
	// System.out.println(FECHA.format(date));
	// return FECHA.parse(FECHA.format(date));
	// } catch (Exception e) {
	// return null;
	// }
	//
	// }

	public static String getDate(Object date) {
		Date fecha = date == null ? null : (Date) date;
		try {
			if (fecha != null) {
				return FECHA.format(date);
			}
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	public static Date getDateFromString(String date) {
		Date dateFromString = null;
		try {
			dateFromString = FECHA.parse(date);
		} catch (Exception e) {
			return null;
		}
		return dateFromString;
	}
	

	public static XMLGregorianCalendar getDateCurrent() {
		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		XMLGregorianCalendar xgcal = getDate(gcal);
		return xgcal;
	}

	private static XMLGregorianCalendar getDate(GregorianCalendar gcal) {
		XMLGregorianCalendar xgcal = null;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		} catch (DatatypeConfigurationException e) {
		}
		return xgcal;
	}

}
