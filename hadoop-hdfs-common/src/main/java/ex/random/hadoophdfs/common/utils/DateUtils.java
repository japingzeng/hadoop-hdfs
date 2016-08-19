package ex.random.hadoophdfs.common.utils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
public class DateUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
	
	public static long getServerTime() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 格式化日期,默认返回yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化显示当前日期
	 * @param format
	 * @return
	 */
	public static String format(String format) {
		return format(new Date(), format);
	}

	/**
	 * 格式化显示当前日期
	 * @param date
	 * @param brFlag
	 * @return
	 */
	public static String format(Date date, boolean brFlag) {
	    if(null == date) {
	        return null; 
	    }
		try {
			String dateStr =  format(date, "yyyy-MM-dd HH:mm:ss");
			if(brFlag) {
				return dateStr.substring(0, 10);
			} else {
				return dateStr.substring(11, 19);
			}
		}catch(Exception e){
			LOGGER.error("日期格式化失败.{}", e.getMessage());
		}
		return null;
	}

	/**
	 * 日期格式化
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			if(null!=date){
				return sdf.format(date);
			}
		} catch (Exception e) {
			LOGGER.warn("日期格式化失败", e);
		}
		return null;
	}

	/**
	 * 时间格式化， 传入毫秒
	 * @param time
	 * @return
	 */
	public static String dateFormat(long time) {
		return format(new Date(time), "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 字符串转换日期型
	 * @param strDate
	 * @param format
	 * @return
	 */
	public static Date format(String strDate,String format){
		Date date = null;
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(format==null?"yyyy-MM-dd HH:mm:ss":format);
			date = sdf.parse(strDate);
			return date;
		} catch (Exception e) {
			LOGGER.warn("日期格式化失败", e);
			return date;
		}
		
	}

	/** 
     * 获得指定日期的前后某天 
     *  
     * @param curDay ->基础时间字符串
     * 		  days		->跨越的天数
     * @return 
     * @throws Exception 
     */  
    public static String getOtherDay(String curDay, int days) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(curDay);  
        } catch (Exception e) {
        	LOGGER.error("日期格式化失败.{}", e.getMessage());
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + (days));
        String otherDay = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return otherDay;  
    }
    
    /** 
     * 获得指定日期的前后某天 
     * @param curDay ->基础时间date
     * 		  days		->跨越的天数
     * @return date
     * @throws Exception 
     */  
    public static Date getOtherDay(Date curDay, int days) {
		// 将当前统计时间提前一天
		Calendar c = new GregorianCalendar();
		c.setTime(curDay);
		c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();  
    }
    
    /**
     * 取得每个月的上一天
     * @param date
     * @return
     */
	public static Calendar getDateOfLastMonth(Date date) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return getDateOfLastMonth(c);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid date format(yyyy-MM-dd HH:mm:ss): " + date);
		}
	}

	public static Calendar getDateOfLastMonth(Calendar date) {
		Calendar lastDate = (Calendar) date.clone();
		lastDate.add(Calendar.MONTH, -1);
		return lastDate;
	}


	/**
	 * 凌晨
	 * @param date
	 * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
	 *       1 返回yyyy-MM-dd 23:59:59日期
	 * @return
	 */
	public static Date weeHours(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		//时分秒（毫秒数）
		long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
		//凌晨00:00:00
		cal.setTimeInMillis(cal.getTimeInMillis()-millisecond+1);

		if (flag == 0) {
			return cal.getTime();
		} else if (flag == 1) {
			//凌晨23:59:59
			cal.setTimeInMillis(cal.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);
		}
		return cal.getTime();
	}


	public static void main(String args[]){
		DateUtils.compareDate("2016-06-06 15:55:27", "2016-06-07 15:55:27", "2016-06-07 15:55:25");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = null;
		try {
			Date gpsUTCDate = DateUtils.parseCstDate("Mon Jul 04 17:06:45 CST 2016");
			String formatStr2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(gpsUTCDate);
			dt = sdf.parse(formatStr2);
			System.out.println(sdf.format(dt));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static boolean compareDate(String star,String end, String compare){
		SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			Date sdate = localTime.parse(star);
			Date edate=localTime.parse(end);
			Date ecompare=localTime.parse(compare);
			long time = System.currentTimeMillis();
			System.out.println(time+"##"+sdate.getTime()+"##"+edate.getTime());
			if(ecompare.after(sdate) && ecompare.before(edate)){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	private static SimpleDateFormat cstFormater = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

	public static Date parseCstDate(String cstDate) {
		if(StringUtils.isBlank(cstDate)){
			return null;
		}
		try {
			return cstFormater.parse(cstDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
