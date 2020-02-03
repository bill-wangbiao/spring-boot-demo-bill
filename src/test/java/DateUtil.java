import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类
 */
public class DateUtil {
	
	// ==格式到年== 
	/**
	 * 日期格式，年份，例如：2004，2008
	 */
	public static final String DATE_FORMAT_YYYY = "yyyy";
	
	
	// ==格式到年月 == 
	/**
	 * 日期格式，年份和月份，例如：200707，200808
	 */
	public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

	/**
	 * 日期格式，年份和月份，例如：200707，2008-08
	 */
	public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

	
	// ==格式到年月日== 
	/**
	 * 日期格式，年月日，例如：050630，080808
	 */
	public static final String DATE_FORMAT_YYMMDD = "yyMMdd";

	/**
	 * 日期格式，年月日，用横杠分开，例如：06-12-25，08-08-08
	 */
	public static final String DATE_FORMAT_YY_MM_DD = "yy-MM-dd";

	/**
	 * 日期格式，年月日，例如：20050630，20080808
	 */
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	
	/**
	 * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
	 */
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	/**
	 * 日期格式，年月日，例如：2016.10.05
	 */
	public static final String DATE_FORMAT_POINTYYYYMMDD = "yyyy.MM.dd";
	
	/**
	 * 日期格式，年月日，例如：2016年10月05日
	 */
	public static final String DATE_TIME_FORMAT_YYYY年MM月DD日 = "yyyy年MM月dd日";
	
	
	// ==格式到年月日 时分 == 
	
	/**
	 * 日期格式，年月日时分，例如：200506301210，200808081210
	 */
	public static final String DATE_FORMAT_YYYYMMDDHHmm = "yyyyMMddHHmm";

	/**
	 * 日期格式，年月日时分，例如：20001230 12:00，20080808 20:08
	 */
	public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";
	
	/**
	 * 日期格式，年月日时分，例如：2000-12-30 12:00，2008-08-08 20:08
	 */
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";
	
	
	// ==格式到年月日 时分秒== 
	/**
	 * 日期格式，年月日时分秒，例如：20001230120000，20080808200808
	 */
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";
	
	/**
	 * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开
	 * 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
	 */
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_BHH_MI_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_TIME_FORMAT_YYYY_MM_DD_BHH_MI = "yyyy-MM-dd HH:mm";
	
	// ==格式到年月日 时分秒 毫秒== 
	/**
	 * 日期格式，年月日时分秒毫秒，例如：20001230120000123，20080808200808456
	 */
	public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";
    
	
	// ==特殊格式==
	/**
	 * 日期格式，月日时分，例如：10-05 12:00
	 */
	public static final String DATE_FORMAT_MMDDHHMI = "MM-dd HH:mm";



	public static final SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");


	/* ************工具方法***************   */
	/**
	 * 获取两个日期相差几个月
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);
		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}


	/** 
     * 获取某日期的年份
     * @param date 
     * @return 
     */
	public static Integer getYear(Date date) {
   	 	Calendar cal = Calendar.getInstance();
   	 	cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}  
	
	/**
	 * 获取某日期的月份
	 * @param date
	 * @return
	 */
	public static Integer getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
   	 	cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取某日期的日数
	 * @param date
	 * @return
	 */
	public static int getDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		 int day=cal.get(Calendar.DATE);//获取日
		 return day;
	}
	
    /**
     * 格式化Date时间
     * @param time Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat){
    	DateFormat dateFormat=new SimpleDateFormat(timeFromat);
    	return dateFormat.format(time);
    }
    
    /**
     * 格式化Timestamp时间
     * @param timestamp Timestamp类型时间
     * @param timeFromat
     * @return 格式化后的字符串
     */
    public static String parseTimestampToStr(Timestamp timestamp,String timeFromat){
    	SimpleDateFormat df = new SimpleDateFormat(timeFromat);
    	return df.format(timestamp);
    }
    
    /**
     * 格式化Date时间
     * @param time Date类型时间
     * @param timeFromat String类型格式
     * @param defaultValue 默认值为当前时间Date
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final Date defaultValue){
    	try{
    		DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        	return dateFormat.format(time);
    	}catch (Exception e){
    		if(defaultValue!=null)
				return parseDateToStr(defaultValue, timeFromat);
			else
				return parseDateToStr(new Date(), timeFromat);
    	}
    }
    
    /**
     * 格式化Date时间
     * @param time Date类型时间
     * @param timeFromat String类型格式
     * @param defaultValue 默认时间值String类型
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat, final String defaultValue){
    	try{
    		DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        	return dateFormat.format(time);
    	}catch (Exception e){
    		return defaultValue;
    	}
    }
    
    /**
     * 格式化String时间
     * @param time String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String time, String timeFromat) {
    	if (time == null || time.equals("")) {
    		return null;
    	}
    	
    	Date date=null;
    	try{
	    	DateFormat dateFormat=new SimpleDateFormat(timeFromat);
	    	date=dateFormat.parse(time);
    	}catch(Exception e){
    		
    	}
    	return date;
    }
    
    /**
     * 格式化String时间
     * @param strTime String类型时间
     * @param timeFromat String类型格式
     * @param defaultValue 异常时返回的默认值
     * @return
     */
    public static Date parseStrToDate(String strTime, String timeFromat,
			Date defaultValue) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(timeFromat);
			return dateFormat.parse(strTime);
		} catch (Exception e) {
			return defaultValue;
		}
	}
    
    /**
     * 当strTime为2008-9时返回为2008-9-1 00:00格式日期时间，无法转换返回null.
     * @param strTime
     * @return
     */
    public static Date strToDate(String strTime) {
    	if(strTime==null || strTime.trim().length()<=0)
    		return null;
    	
		Date date = null;
		List<String> list = new ArrayList<String>(0);
		
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
		list.add(DATE_FORMAT_YYYY_MM_DD);
		//list.add(DATE_FORMAT_YY_MM_DD);
		list.add(DATE_FORMAT_YYYYMMDD);
		list.add(DATE_FORMAT_YYYY_MM);
		list.add(DATE_FORMAT_YYYYMM);
		list.add(DATE_FORMAT_YYYY);
		
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String format = (String) iter.next();
			if(strTime.indexOf("-")>0 && format.indexOf("-")<0)
				continue;
			if(strTime.indexOf("-")<0 && format.indexOf("-")>0)
				continue;
			if(strTime.length()>format.length())
				continue;
			date = parseStrToDate(strTime, format);
			if (date != null)
				break;
		}

		return date;
	}
    
    /**
	 * 解析两个日期之间的所有月份
	 * @param beginDateStr 开始日期，至少精确到yyyy-MM
	 * @param endDateStr 结束日期，至少精确到yyyy-MM
	 * @return yyyy-MM日期集合
	 */  
    public static List<String> getMonthListOfDate(String beginDateStr, String endDateStr) {  
        // 指定要解析的时间格式  
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
        // 返回的月份列表  
        String sRet = "";  
  
        // 定义一些变量  
        Date beginDate = null;  
        Date endDate = null;  
  
        GregorianCalendar beginGC = null;  
        GregorianCalendar endGC = null;  
        List<String> list = new ArrayList<String>();  
  
        try {  
            // 将字符串parse成日期  
            beginDate = f.parse(beginDateStr);  
            endDate = f.parse(endDateStr);  
  
            // 设置日历  
            beginGC = new GregorianCalendar();  
            beginGC.setTime(beginDate);  
  
            endGC = new GregorianCalendar();  
            endGC.setTime(endDate);  
  
            // 直到两个时间相同  
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {  
                sRet = beginGC.get(Calendar.YEAR) + "-"  
                        + (beginGC.get(Calendar.MONTH) + 1);  
                list.add(sRet);  
                // 以月为单位，增加时间  
                beginGC.add(Calendar.MONTH, 1);  
            }  
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
	/**
	 * 解析两个日期之间的所有月份
	 * @param beginDateStr 开始日期，至少精确到yyyy-MM
	 * @param endDateStr 结束日期，至少精确到yyyy-MM
	 * @return yyyy-MM日期集合
	 */
	public static List<String> getMonthListOfDateV2(String minDate, String maxDate) {
		ArrayList<String> result = new ArrayList<String>();
		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();
		min.setTime(DateUtil.parseStrToDate(minDate, "yyyy-MM"));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		max.setTime(DateUtil.parseStrToDate(maxDate, "yyyy-MM"));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			result.add(DateUtil.parseDateToStr((curr.getTime()), "yyyy-MM"));
			curr.add(Calendar.MONTH, 1);
		}
		return result;
	}


	/**
     * 解析两个日期段之间的所有日期
     * @param beginDateStr 开始日期  ，至少精确到yyyy-MM-dd
     * @param endDateStr 结束日期  ，至少精确到yyyy-MM-dd
     * @return yyyy-MM-dd日期集合
     */  
    public static List<String> getDayListOfDate(String beginDateStr, String endDateStr) {  
        // 指定要解析的时间格式  
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");  
  
        // 定义一些变量  
        Date beginDate = null;  
        Date endDate = null;  
  
        Calendar beginGC = null;  
        Calendar endGC = null;  
        List<String> list = new ArrayList<String>();  
  
        try {  
            // 将字符串parse成日期  
            beginDate = f.parse(beginDateStr);  
            endDate = f.parse(endDateStr);  
  
            // 设置日历  
            beginGC = Calendar.getInstance();  
            beginGC.setTime(beginDate);  
  
            endGC = Calendar.getInstance();  
            endGC.setTime(endDate);  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
  
            // 直到两个时间相同  
            while (beginGC.getTime().compareTo(endGC.getTime()) <= 0) {  
  
                list.add(sdf.format(beginGC.getTime()));  
                // 以日为单位，增加时间  
                beginGC.add(Calendar.DAY_OF_MONTH, 1);  
            }  
            return list;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /**
     * 获取当下年份指定前后数量的年份集合
     * @param before 当下年份前年数
     * @param behind 当下年份后年数
     * @return 集合
     */
    public static List<Integer> getYearListOfYears(int before,int behind) {
    	if (before<0 || behind<0) {
			return null;
		}
        List<Integer> list = new ArrayList<Integer>();  
        Calendar c = null;  
        c = Calendar.getInstance();  
        c.setTime(new Date());  
        int currYear = Calendar.getInstance().get(Calendar.YEAR);  
  
        int startYear = currYear - before;  
        int endYear = currYear + behind;  
        for (int i = startYear; i < endYear; i++) {  
            list.add(Integer.valueOf(i));  
        }  
        return list;  
    }
    
    /** 
     * 获取当前日期是一年中第几周 
     * @param date 
     * @return 
     */  
    public static Integer getWeekthOfYear(Date date) {  
        Calendar c = new GregorianCalendar();  
        c.setFirstDayOfWeek(Calendar.MONDAY);  
        c.setMinimalDaysInFirstWeek(7);  
        c.setTime(date);  
  
        return c.get(Calendar.WEEK_OF_YEAR);  
    } 

    /**
	 * 获取某一年各星期的始终时间
	 * 实例：getWeekList(2016)，第52周(从2016-12-26至2017-01-01)
	 * @param 年份
	 * @return
	 */  
    public static HashMap<Integer,String> getWeekTimeOfYear(int year) {  
        HashMap<Integer,String> map = new LinkedHashMap<Integer,String>();  
        Calendar c = new GregorianCalendar();  
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);  
        int count = getWeekthOfYear(c.getTime());  
  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dayOfWeekStart = "";  
        String dayOfWeekEnd = "";  
        for (int i = 1; i <= count; i++) {  
            dayOfWeekStart = sdf.format(getFirstDayOfWeek(year, i));  
            dayOfWeekEnd = sdf.format(getLastDayOfWeek(year, i));  
            map.put(Integer.valueOf(i), "第"+i+"周(从"+dayOfWeekStart + "至" + dayOfWeekEnd+")");  
        }  
        return map;  
  
    }  
      
    /** 
     * 获取某一年的总周数 
     * @param year 
     * @return 
     */  
    public static Integer getWeekCountOfYear(int year){  
        Calendar c = new GregorianCalendar();  
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);  
        int count = getWeekthOfYear(c.getTime());  
        return count;  
    }  
    
    /** 
     * 获取指定日期所在周的第一天 
     * @param date 
     * @return 
     */  
    public static Date getFirstDayOfWeek(Date date) {  
        Calendar c = new GregorianCalendar();  
        c.setFirstDayOfWeek(Calendar.MONDAY);  
        c.setTime(date);  
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday  
        return c.getTime();  
    }  
  
    /** 
     * 获取指定日期所在周的最后一天 
     * @param date 
     * @return 
     */  
    public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}
  
    /** 
     * 获取某年某周的第一天 
     * @param year 目标年份
     * @param week 目标周数
     * @return 
     */  
    public static Date getFirstDayOfWeek(int year, int week) {  
        Calendar c = new GregorianCalendar();  
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, Calendar.JANUARY);  
        c.set(Calendar.DATE, 1);  
  
        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);  
  
        return getFirstDayOfWeek(cal.getTime());  
    }  
  
    /** 
     * 获取某年某周的最后一天 
     * @param year 目标年份
     * @param week 目标周数
     * @return 
     */  
    public static Date getLastDayOfWeek(int year, int week) {  
        Calendar c = new GregorianCalendar();  
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, Calendar.JANUARY);  
        c.set(Calendar.DATE, 1);  
  
        Calendar cal = (GregorianCalendar) c.clone();  
        cal.add(Calendar.DATE, week * 7);  
  
        return getLastDayOfWeek(cal.getTime());  
    }  
      
    /** 
     * 获取某年某月的第一天 
     * @param year 目标年份
     * @param month 目标月份
     * @return 
     */  
    public static Date getFirstDayOfMonth(int year,int month){  
        month = month-1;  
        Calendar   c   =   Calendar.getInstance();     
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, month);  
          
        int day = c.getActualMinimum(c.DAY_OF_MONTH);  
  
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }  
      
    /** 
     * 获取某年某月的最后一天 
     * @param year 目标年份
     * @param month 目标月份
     * @return 
     */  
    public static Date getLastDayOfMonth(int year,int month){  
        month = month-1;  
        Calendar   c   =   Calendar.getInstance();     
        c.set(Calendar.YEAR, year);  
        c.set(Calendar.MONTH, month);  
        int day = c.getActualMaximum(c.DAY_OF_MONTH);  
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 22);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        return c.getTime();
    }  
  
    /** 
     * 获取某个日期为星期几 
     * @param date 
     * @return String "星期*"
     */  
    public static String getDayWeekOfDate1(Date date) {  
    	 String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);

         int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
         if (w < 0)
             w = 0;
         
         return weekDays[w];
    }  
    
    /**
	 * 获得指定日期的星期几数
	 * @param date
	 * @return int 
	 */ 
    public static Integer getDayWeekOfDate2(Date date){  
    	Calendar aCalendar = Calendar.getInstance();  
    	aCalendar.setTime(date);     
    	int weekDay = aCalendar.get(Calendar.DAY_OF_WEEK);     
    	return weekDay;
    }
    
	/**
	 * 验证字符串是否为日期
	 * 验证格式:YYYYMMDD、YYYY_MM_DD、YYYYMMDDHHMISS、YYYYMMDD_HH_MI、YYYY_MM_DD_HH_MI、YYYYMMDDHHMISSSSS、YYYY_MM_DD_HH_MI_SS
	 * @param strTime
	 * @return null时返回false;true为日期，false不为日期
	 */
	public static boolean validateIsDate(String strTime) {
    	if (strTime == null || strTime.trim().length() <= 0)
    		return false;
    	
		Date date = null;
		List<String> list = new ArrayList<String>(0);
		
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
		list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
		list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
		list.add(DATE_FORMAT_YYYY_MM_DD);
		//list.add(DATE_FORMAT_YY_MM_DD);
		list.add(DATE_FORMAT_YYYYMMDD);
		//list.add(DATE_FORMAT_YYYY_MM);
		//list.add(DATE_FORMAT_YYYYMM);
		//list.add(DATE_FORMAT_YYYY);
		
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			String format = (String) iter.next();
			if(strTime.indexOf("-")>0 && format.indexOf("-")<0)
				continue;
			if(strTime.indexOf("-")<0 && format.indexOf("-")>0)
				continue;
			if(strTime.length()>format.length())
				continue;
			date = parseStrToDate(strTime.trim(), format);
			if (date != null)
				break;
		}
		
		if (date != null) {
			System.out.println("生成的日期:"+ DateUtil.parseDateToStr(date, DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS, "--null--"));
			return true;
		}
		return false;
	}
	
	/**
	 * 将指定日期的时分秒格式为零
	 * @param date
	 * @return
	 */
	public static Date formatHhMmSsOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 获得指定时间加减参数后的日期(不计算则输入0) 
	 * @param date 指定日期
	 * @param year 年数，可正可负
	 * @param month 月数，可正可负
	 * @param day 天数，可正可负
	 * @param hour 小时数，可正可负
	 * @param minute 分钟数，可正可负
	 * @param second 秒数，可正可负
	 * @param millisecond 毫秒数，可正可负
	 * @return 计算后的日期
	 */
	public static Date addDate(Date date,int year,int month,int day,int hour,int minute,int second,int millisecond){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, year);//加减年数
		c.add(Calendar.MONTH, month);//加减月数
		c.add(Calendar.DATE, day);//加减天数
		c.add(Calendar.HOUR,hour);//加减小时数
		c.add(Calendar.MINUTE, minute);//加减分钟数
		c.add(Calendar.SECOND, second);//加减秒
		c.add(Calendar.MILLISECOND, millisecond);//加减毫秒数
		
		return c.getTime();
	}
	
	/**
	 * 获得两个日期的时间戳之差
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getDistanceTimestamp(Date startDate,Date endDate){
		long daysBetween=(endDate.getTime()-startDate.getTime()+1000000)/(3600*24*1000);
		return daysBetween;
	}
	
	/**
	 * 判断二个时间是否为同年同月
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Boolean compareIsSameMonth(Date date1,Date date2){
		boolean flag = false;
		int year1  = getYear(date1);
		int year2 = getYear(date2);
		if(year1 == year2){
			int month1 = getMonth(date1);
			int month2 = getMonth(date2);
			if(month1 == month2)flag = true;
		}
		return flag;
	}
	
	 /** 
     * 获得两个时间相差距离多少天多少小时多少分多少秒 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return long[] 返回值为：{天, 时, 分, 秒} 
     */ 
    public static long[] getDistanceTime(Date one, Date two) { 
        long day = 0; 
        long hour = 0; 
        long min = 0; 
        long sec = 0; 
        try { 
           
            long time1 = one.getTime(); 
            long time2 = two.getTime(); 
            long diff ; 
            if(time1<time2) { 
                diff = time2 - time1; 
            } else { 
                diff = time1 - time2; 
            } 
            day = diff / (24 * 60 * 60 * 1000); 
            hour = (diff / (60 * 60 * 1000) - day * 24); 
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60); 
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        long[] times = {day, hour, min, sec}; 
        return times; 
    } 
    
    /** 
     * 两个时间相差距离多少天多少小时多少分多少秒 
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00 
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00 
     * @return String 返回值为：{天, 时, 分, 秒}
     */ 
    public static long[] getDistanceTime(String str1, String str2) { 
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS); 
        Date one; 
        Date two; 
        long day = 0; 
        long hour = 0; 
        long min = 0; 
        long sec = 0; 
        try { 
            one = df.parse(str1); 
            two = df.parse(str2); 
            long time1 = one.getTime(); 
            long time2 = two.getTime(); 
            long diff ; 
            if(time1<time2) { 
                diff = time2 - time1; 
            } else { 
                diff = time1 - time2; 
            } 
            day = diff / (24 * 60 * 60 * 1000); 
            hour = (diff / (60 * 60 * 1000) - day * 24); 
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60); 
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60); 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        long[] times = {day, hour, min, sec}; 
        return times; 
    } 
    
    /** 
     * 两个时间之间相差距离多少天 
     * @param one 时间参数 1： 
     * @param two 时间参数 2： 
     * @return 相差天数 
     */ 
    public static Long getDistanceDays(String str1, String str2) throws Exception{ 
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS); 
        Date one; 
        Date two; 
        long days=0; 
        try { 
            one = df.parse(str1); 
            two = df.parse(str2); 
            long time1 = one.getTime(); 
            long time2 = two.getTime(); 
            long diff ; 
            if(time1<time2) { 
                diff = time2 - time1; 
            } else { 
                diff = time1 - time2; 
            } 
            days = diff / (1000 * 60 * 60 * 24); 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        return days; 
    }

	/**
	 * 两个时间之间相差距离多少天
	 * @param one 时间参数 1：
	 * @param two 时间参数 2：
	 * @return 相差天数
	 */
	public static Long getDistanceDaysForDate(Date date1, Date date2) throws Exception{ ;
		long days=0;
		try {
			long time1 = date1.getTime();
			long time2 = date2.getTime();
			long diff ;
			if(time1<time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return days;
	}
	/**
	 * 获取指定时间的那天 00:00:00.000 的时间
	 * @param date
	 * @return
	 */
	public static Date getDayBeginTime(final Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.set(Calendar.HOUR_OF_DAY, 0);
	        c.set(Calendar.MINUTE, 0);
	        c.set(Calendar.SECOND, 0);
	        c.set(Calendar.MILLISECOND, 0);
	        return c.getTime();
	}


	/**
	 * 获取指定时间的那天 23:59:59.999 的时间
	 * @param date
	 * @return
	 */
	public static Date getDayEndTime(final Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.set(Calendar.HOUR_OF_DAY, 23);
	        c.set(Calendar.MINUTE, 59);
	        c.set(Calendar.SECOND, 59);
	        c.set(Calendar.MILLISECOND, 999);
	        return c.getTime();
	}
   
    


	/**
	 * 距失效天数
	 * 用于两个日期相减  过去时为负数
	 * @param d
	 * @return
	 */
	public static String getTimeDiffer(Date d) throws ParseException {
		String newDate = dateformat.format(d);
		Date newDd = dateformat.parse(newDate);
		//格式化日期
		StringBuffer sb = new StringBuffer("");
		if(newDd.getTime()>System.currentTimeMillis()){
			long l = d.getTime() - System.currentTimeMillis() ;
			long day = l / (24 * 60 * 60 * 1000);
			sb.append(day);
		}else{
			long l = System.currentTimeMillis() - newDd.getTime();
			long day = l / (24 * 60 * 60 * 1000);
			sb.append("-");
			sb.append(day);
		}
		return sb.toString();
	}

	/**
	 * 获取当前月所在的第一天
	 * @return
	 */
	public static String getMonthFirstDay(){
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH,1);
		String first = format.format(c.getTime());
		return first;
	}

	/**
	 * 日期转星期
	 *
	 * @param datetime
	 * @return
	 */
	public static Integer dateToWeek(String datetime) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
//		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance(); // 获得一个日历
		Date datet = null;
		try {
			datet = f.parse(datetime);
			cal.setTime(datet);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
		if (w < 0)
			w = 0;
		return w;
	}
	/**
	 * 计算某年某月有多少天
	 * @author liuyuchen
	 * @param year
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
		if (month > 12 || month < 1) {
			throw new RuntimeException("month is error " + month);
		}
		String bigmonth = "1,3,5,7,8,10,12";
		String smallmonth = "4,6,9,11";
		if (bigmonth.indexOf(month + "") > -1) {
			return 31;
		}
		if (smallmonth.indexOf(month + "") > -1) {
			return 30;
		}
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			return 29;
		} else {
			return 28;
		}
	}

	/**
	 * 指定年月的第一个星期一
	 * @param year
	 * @param month
	 */
	public static String firstMonday(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1); //从0开始
		int day = 1;
		cal.set(Calendar.DAY_OF_MONTH, day);    //从第一天开始找第一个星期一
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
			cal.set(Calendar.DAY_OF_MONTH, ++day);
		}
		Date firstMonday = cal.getTime();
		String dtStr = new SimpleDateFormat("yyyy-MM-dd").format(firstMonday);
//        System.out.println(dtStr);
		return dtStr;
	}

	/**
	 * 指定年月的最后个星期一
	 * @param year
	 * @param month
	 */
	public static String lastMonday(int year, int month){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);  //防止getInstance()返回今天是2月29号，被认为是3月了
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  //每个月的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY){
			cal.set(Calendar.DAY_OF_MONTH, --lastDay);
		}
		Date lastMonday = cal.getTime();
		String dtStr = new SimpleDateFormat("yyyy-MM-dd").format(lastMonday);
//        System.out.println(dtStr);
		return dtStr;
	}

	/**
	 * 返回指定年月的第一天(1号)是星期几
	 * @param year
	 * @param month
	 */
	public static int DayofFirstDay(int year, int month){
		int[] weekDays = {7, 1, 2, 3, 4, 5, 6};
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1); //从0开始
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int day = cal.get(Calendar.DAY_OF_WEEK) - 1 ;
		if(day < 0) day = 0;

//        System.out.println("星期"+weekDays[day]);
		return weekDays[day];
	}

	/**
	 * 获取指定时间的上个月
	 * @param currentDate	指定的时间(格式: yyyy-MM)
	 * @return
	 */
	public static String getLastDate(String currentDate) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(currentDate + "-" + "01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);

		String lastDate = c.get(Calendar.YEAR) + "-"
				+ (c.get(Calendar.MONTH) + 1);

		return lastDate;

	}

	/**
	 * 比较两个日期的前后顺序
	 * @param DATE1		时间1(格式 : yyyy-MM-dd)
	 * @param DATE2		时间2(格式 : yyyy-MM-dd)
	 * @return	1在前2在后 返回-1	2在前1在后 返回1	同一天 返回0
	 */
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 在给定的日期加上或减去指定月份后的日期
	 *
	 * @param sourceDate 原始时间
	 * @param month      要调整的月份，向前为负数，向后为正数
	 * @return
	 */
	public static Date stepMonth(Date sourceDate, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	public static Date stepYEAR(Date sourceDate, int year) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.YEAR, year);
		return c.getTime();
	}

	public static Date stepDAY(Date sourceDate, int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}

	/**
	 * 时间计算器
	 * @param sourceDateStr 初始时间
	 * @param step 更改的值
	 * @param type 时间类型
	 * @return
	 */
	public static String timeCalculator(String sourceDateStr, int step,int type) {
		Date sourceDate = parseStrToDate(sourceDateStr,DATE_TIME_FORMAT_YYYY_MM_DD_BHH_MI_SS);
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(type, step);
		return parseDateToStr(c.getTime(),DATE_TIME_FORMAT_YYYY_MM_DD_BHH_MI_SS);
	}

	public static boolean isWeekend(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return true;
		} else{
			return false;
		}
	}

	public static int isPurchaseDate(Date date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ){
			return 1;
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
			return 2;
		}else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return 3;
		}else{
			return 4;
		}
	}





	public boolean compareDayGreaterThan(Date firstTime,Date secondTime) {
		int result = compareDays(firstTime, secondTime);
		if(result > BigDecimal.ZERO.intValue()){
			return true;
		}
		return false;
	}

	public boolean compareDayLessThan(Date firstTime,Date secondTime) {
		int result = compareDays(firstTime, secondTime);
		if(result < BigDecimal.ZERO.intValue()){
			return true;
		}
		return false;
	}

	public boolean compareDayEquals(Date firstTime,Date secondTime) {
		int result = compareDays(firstTime, secondTime);
		if(result == BigDecimal.ZERO.intValue()){
			return true;
		}
		return false;
	}

	private int compareDays(Date firstTime,Date secondTime) {
		try {
			String xStrTime = org.apache.commons.lang3.time.DateFormatUtils.format(firstTime, DATE_FORMAT_YYYY_MM_DD);
			String yStrTime = org.apache.commons.lang3.time.DateFormatUtils.format(secondTime, DATE_FORMAT_YYYY_MM_DD);
			Date xxTime = DateUtils.parseDate(xStrTime, DATE_FORMAT_YYYY_MM_DD);
			Date yyTime = DateUtils.parseDate(yStrTime, DATE_FORMAT_YYYY_MM_DD);
			return xxTime.compareTo(yyTime);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public final static String ES_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
	public static Date esTimeToDate(String time) throws ParseException {
		if (StringUtils.isEmpty(time))
			return null;

		time = time.replace("Z", " UTC");
		SimpleDateFormat format = new SimpleDateFormat(ES_DATE_FORMAT);
		return format.parse(time);
	}

	public static Date parseFromObj(Object value) {
		SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		SimpleDateFormat gmcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = (String) value;
		Date dateValue;
		try {
			if (dateStr.contains("T")){
				dateStr = dateStr.replace("Z", " UTC");
				dateValue = utcFormat.parse(dateStr);
			}
			else{
				dateValue = gmcFormat.parse(dateStr);
			}
		} catch (ParseException e) {
			dateValue = null;
		}
		return dateValue;
	}

	public static Date obtainStartDayForMonth(String year,String month){
		try {
			String date = year + "-" + month + "-" + "01 00:00:00";
			return DateUtils.parseDate(date,"yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date obtainStartDayForDay(String year,String month,String day){
		try {
			String date = year + "-" + month + "-" +day+" 00:00:00";
			return DateUtils.parseDate(date,"yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			return null;
		}
	}
	public static Date obtainEndtDayForDay(String year,String month,String day){
		try {
			String date = year + "-" + month + "-" +day+" 23:59:59";
			return DateUtils.parseDate(date,"yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			return null;
		}
	}


	public static Date obtainEndDayForMonth(String year,String month){
		Date first = obtainStartDayForMonth(year, month);
		if(ObjectUtils.isEmpty(first)){
			return null;
		}
		Date frontMonth = DateUtils.addMonths(first, NumberUtils.INTEGER_ONE);
		return DateUtils.addSeconds(frontMonth, Math.negateExact(NumberUtils.INTEGER_ONE));
	}

	public static Date defaultParseDate(String date){
		try {
			return DateUtils.parseDate(date,"yyyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


    /**
     * 获取月份起始日期
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getMinMonthDate(Date date) throws ParseException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return DateUtil.parseDateToStr(calendar.getTime(),DateUtil.DATE_FORMAT_YYYY_MM_DD);
    }
    /**
     * 获取月份最后日期
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getMaxMonthDate(Date date) throws ParseException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return DateUtil.parseDateToStr(calendar.getTime(),DateUtil.DATE_FORMAT_YYYY_MM_DD);
    }

	public static String getLastDayOfMonthString(int year,int month) {
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, 0);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;


	}
	public static String getNextFirstOfMonthString(int year,int month) {
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month);
		//获取某月最大天数
		int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;


	}

	public static String getfirstDayOfMonthString(int year,int month) {
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	/**
	 * 功能描述：返回小时
	 *
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分
	 *
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}



	public static Date dateFormat(Date date,String format)  {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String format1 = simpleDateFormat.format(date);
		try {
			return simpleDateFormat.parse(format1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}


	public static void main(String[] args) throws ParseException {
		String lastDayOfMonthString = getLastDayOfMonthString(2020, 2);

		Date date = dateFormat(new Date(), DATE_FORMAT_YYYY_MM_DD);

		System.out.println(new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD).format(date));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startDate = "2018-10-15 00:00:00";
			String ednDate = "2019-11-10 23:59:59";
			Date sdate = DateUtil.parseStrToDate(startDate, "yyyy-MM-dd HH:mm:ss");
			Date edate = DateUtil.parseStrToDate(ednDate, "yyyy-MM-dd HH:mm:ss");
			System.out.println("开始时间：" + sdf.format(sdate));
			System.out.println("结束时间：" + sdf.format(edate));
			int month = getMonth(sdf.parse(startDate), sdf.parse(ednDate));
			System.out.println("两者之间相差：" + month);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
