package utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateAndTime {
	
	public static Date getDate(String strDate, String format) throws ParseException{
		DateFormat df = new SimpleDateFormat(format);
		Date date = df.parse(strDate);
		return date;
	}

    public static Date getDate(String strDate, String format, TimeZone timeZone) throws ParseException{
        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(timeZone);
        Date date = df.parse(strDate);
        return date;
    }

	public static String getDate(Date date, String format) throws ParseException{
		DateFormat df = new SimpleDateFormat(format);
		String strDate = df.format(date);
		return strDate;
	}
	
	public static Time getTime(String strTime, String format) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long hms = sdf.parse(strTime).getTime();
		return new Time(hms);
	}
	
	public static String getCurrentStrDate(String timezone,String format){
		long timeInMillis = System.currentTimeMillis();
		Calendar cal = new GregorianCalendar(TimeZone.getDefault());
		cal.setTimeInMillis(timeInMillis);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		String date = sdf.format(cal.getTime());
		return date;
	}

    public static boolean validDate(String strDate, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

	public static float diffWithCurrentDate(String format, String date, String time, String timezone) throws ParseException{
		
		long dateMillis = getDate(date,format).getTime();
		long currentMillis = getDate(getCurrentStrDate(timezone,format),format).getTime();
		float result = currentMillis - dateMillis;
		
		if(time.equalsIgnoreCase("sec")){
			result = (result/1000);
		}else if(time.equalsIgnoreCase("min")){
			result = (result/60000);
		}else if(time.equalsIgnoreCase("hour")){
			result = (result/3600000);
		}else if(time.equalsIgnoreCase("days")){
			result = (result/3600000)/24;
		}
		
		return result;		
	}
	
	/**
	 * The difference is the way date2 -date1
	 * @param format
	 * @param date1
	 * @param date2
	 * @param time
	 * @return
	 * @throws java.text.ParseException
	 */
	public static float diffBetweenDates(String format, String date1, String date2, String time) throws ParseException{
		
		long date1Millis = getDate(date1,format).getTime();
		long date2Millis = getDate(date2,format).getTime();
		float result = date2Millis - date1Millis;
		
		if(time.equalsIgnoreCase("sec")){
			result = (result/1000);
		}else if(time.equalsIgnoreCase("min")){
			result = (result/60000);
		}else if(time.equalsIgnoreCase("hour")){
			result = (result/3600000);
		}else if(time.equalsIgnoreCase("days")){
			result = (result/3600000)/24;
		}
		
		return result;		
	}
	
	public static float diffBetweenDates(String format, Date date1, String date2, String time) throws ParseException{
		
		long date1Millis = date1.getTime();
		long date2Millis = getDate(date2,format).getTime();
		float result = date2Millis - date1Millis;
		
		if(time.equalsIgnoreCase("sec")){
			result = (result/1000);
		}else if(time.equalsIgnoreCase("min")){
			result = (result/60000);
		}else if(time.equalsIgnoreCase("hour")){
			result = (result/3600000);
		}else if(time.equalsIgnoreCase("days")){
			result = (result/3600000)/24;
		}
		
		return result;		
	}
	
	public static float diffBetweenDates(String format, Date date1, Date date2, String time) throws ParseException{
		
		long date1Millis = date1.getTime();
		long date2Millis = date2.getTime();
		float result = date2Millis - date1Millis;
		
		if(time.equalsIgnoreCase("sec")){
			result = (result/1000);
		}else if(time.equalsIgnoreCase("min")){
			result = (result/60000);
		}else if(time.equalsIgnoreCase("hour")){
			result = (result/3600000);
		}else if(time.equalsIgnoreCase("days")){
			result = (result/3600000)/24;
		}
		
		return result;		
	}

    /**
     * Funcion que retorna el valor minimo para la fecha especificada, segun el timezone en el formato dado
     * @param date		fecha sobre la cual se quiere el minimo
     * @param timezone	timezone a consultar
     * @param format	formato en el que se quiere el string con la fecha minima
     * @return			long con la fecha minima formateada
     */
    public static String getMinimumDate(Calendar date, String timezone, String format){
        Calendar cal = (Calendar) date.clone();
        cal.setTimeZone(TimeZone.getTimeZone(timezone));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName()));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        return sdf.format(cal.getTime());
    }

    /**
     * Funcion que retorna el valor maximo para la fecha especificada, segun el timezone en el formato dado
     * @param date		fecha sobre la cual se quiere el maximo
     * @param timezone	timezone a consultar
     * @param format	formato en el que se quiere el string con la fecha maxima
     * @return			long con la fecha maxima formateada
     */
    public static String getMaximumDate(Calendar date, String timezone, String format){
        Calendar cal = (Calendar) date.clone();
        cal.setTimeZone(TimeZone.getTimeZone(timezone));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone(TimeZone.getDefault().getDisplayName()));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        return sdf.format(cal.getTime());
    }

    public static Calendar getMinimumDate(Calendar date, String timezone){
        Calendar cal = (Calendar) date.clone();
        cal.setTimeZone(TimeZone.getTimeZone(timezone));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
        return cal;
    }

    public static Calendar getMaximumDate(Calendar date, String timezone){
        Calendar cal = (Calendar) date.clone();
        cal.setTimeZone(TimeZone.getTimeZone(timezone));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        return cal;
    }

    public static TimeZone getTimezoneFromID(String id){
        TimeZone timeZone = null;
        for (String string : TimeZone.getAvailableIDs(TimeZone.getTimeZone(id).getRawOffset())) {
            timeZone = TimeZone.getTimeZone(string);
            break;
        }
        return timeZone;
    }
}
