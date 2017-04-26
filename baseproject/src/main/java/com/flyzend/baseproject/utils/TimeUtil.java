package com.flyzend.baseproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 关于时间信息的工具类
 * <br/>
 * 2015年12月25日-下午2:53:50
 * @author lifei
 */
public class TimeUtil {
    /**
     * 一般的格式化时间样式：年月日 时分秒 eg.: 2016-09-09 13:28:20
     */
    public static final String NORMAL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 只需要年月日的时间格式 eg.: 2016-09-09
     */
    public static final String YMD_TIME_FORMAT = "yyyy-MM-dd";
    /**
     * 
     * @param timePattern 时间正则格式 eg. yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getFormatTimeForNow(String timePattern){
        SimpleDateFormat sdf = new SimpleDateFormat(timePattern, Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间
     *以yyyy-MM-dd HH:mm:ss eg.: 1988-11-08 18:08:08的方式显示
     * @return
     */
    public static String geNowTimeStr() {
        return getFormatTimeForNow(NORMAL_TIME_FORMAT);
    }

    public static String formatMillsTimes(String millsTime, String timeFormat) {
        if (millsTime == null) {
            return "";
        }
        if (timeFormat == null) {
            timeFormat = NORMAL_TIME_FORMAT;
        }
//        int serverTimeLen = millsTime.length();
//        long localSystemTime = System.currentTimeMillis();
//        int localSystemTimeLen = (localSystemTime + "").length();
//        int timeGap = localSystemTimeLen - serverTimeLen;
//        String appendedZero = "";
//        while (timeGap-- > 0) {
//            appendedZero += "0";
//        }
//        millsTime+= appendedZero;
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.getDefault());
        return sdf.format(convetStr2Date(diffLocalMillisTimeAndAppend0(millsTime)));
    }

    /**
     * 以年月日 时分秒 的样式{@linkplain #NORMAL_TIME_FORMAT}格式化
     * @param millsTime
     * @return
     */
    public static String formatMillsTimes(String millsTime) {
        return formatMillsTimes(millsTime, NORMAL_TIME_FORMAT);
    }
    public static Date convetStr2Date(String dateStr) {
        Date theDate = new Date(Long.parseLong(dateStr));
        return theDate;
    }

    /***
     * 将一个毫秒表示的时间字符串与系统本地的毫秒时间字符串比较，如果参数的毫秒时间比本地的毫秒时间字符数短，则补全相差数量的“0”字符
     * 以供本地系统能正确解析完整时间
     * @param maybeShortMillisTime 字符长度可能短于本地的毫秒时间字符串的毫秒时间字符串
     * @return 原字符串后补全"0"的新毫秒时间字符串
     */
    private static String diffLocalMillisTimeAndAppend0(String maybeShortMillisTime) {
        String resultMillisTime = "";
        if (maybeShortMillisTime != null && maybeShortMillisTime.trim().length() > 0) {
            String localMillisTime = System.currentTimeMillis() + "";
            resultMillisTime = maybeShortMillisTime;
            int maybeShortMillisTimeStrLen = maybeShortMillisTime.length();
            int timeStrLenGap = localMillisTime.length() - maybeShortMillisTimeStrLen;
            String appendedZero = "";
            while (timeStrLenGap-- > 0) {
                appendedZero += "0";
            }
            resultMillisTime += appendedZero;
        }
        return resultMillisTime;
    }

    /***
     * 根据一个毫秒时间字符串，获取这个时间的二维信息描述
     * 这里只获取1、该时间距离本地系统当前时间是今天、昨天、然后星期；2、月-日信息
     * 注：如果是服务器给的时间，则本地去获取是否为今天、昨天，有不准的风险，因为当前系统时间用户可以随便调整，导致比较时间基线变化
     * @param theGiveMillisTime 所给的毫秒时间串
     * @return new String[]{"今天","11-25"}
     */
    public static String[] getTime2DDescInfos(String theGiveMillisTime) {
        String[] twoDimensionDesc = {
          "",""
        };
        String standardMillisTime = diffLocalMillisTimeAndAppend0(theGiveMillisTime);
        long standardMillis = 0;
        try {
            standardMillis = Long.parseLong(standardMillisTime);
            Calendar calendar = Calendar.getInstance();//这里拿到的也就是系统当前日历时间
            long nowMillisTime = System.currentTimeMillis();//系统当前毫秒时间：距离(UTC时间)1970-1-1 00:00:00的毫秒数
            //本地系统的时间所在时区与UTC对比偏移的毫秒数，比如，当前系统时区为在中国，则所在时区为8，系统初始时间则为1970-1-1 08:00:00开始计算
            int curTimeZoneRawOffsetMillis = calendar.getTimeZone().getRawOffset();

            //就是系统当前时间 距离所在时区的初始时间 偏移了多少天，说白了就是现在已经过了多少天
            long daysOffsetToday = (nowMillisTime + curTimeZoneRawOffsetMillis) / 86400000;
            //所给出的时间距离 所在时区的初始时间 偏移了多少天，说白了就是所给出的时间已经过了多少天
            long theTimeOffsetDays = (standardMillis + curTimeZoneRawOffsetMillis)/86400000;
            long gapDays = theTimeOffsetDays - daysOffsetToday;
            calendar.setTimeInMillis(standardMillis);//把日历切换到参数所给的时间
            String desc1 = "";
            if (gapDays == 0) {
                desc1 = "今天";
            } else if (gapDays == -1) {
                desc1 = "昨天";
            } else if (gapDays == -2) {
                desc1 = "前天";
            }
            else{
                //拿到周几的信息
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                desc1 = dayOfWeekDesc(dayOfWeek);
            }
            int minute = calendar.get(Calendar.MINUTE);
            String desc2 = calendar.get(Calendar.HOUR_OF_DAY) + ":" + (minute < 10 ? "0" + minute : minute);
            twoDimensionDesc[0] = desc1;
            twoDimensionDesc[1] = desc2;
        } catch (Exception ignored) {

        }
        return twoDimensionDesc;
    }

    /**
     * 得到所给的参数时间与当前系统今天的间隔天数
     * @param theMillisTime 所给的毫秒时间 注该毫秒需要与Java系统毫秒时间位数一致
     * @return 0:所给的时间即为【今天】；1:所给的比今天还多一天，即为【明天】；2：后天；-1：所给的时间比今天少一天即为【昨天】；-2：前天;
     */
    public static long getDiffNowDayNum(String theMillisTime){
        Calendar nowCalendar = Calendar.getInstance();//这里拿到的也就是系统当前日历时间
        //本地系统的时间所在时区与UTC对比偏移的毫秒数，比如，当前系统时区为在中国，则所在时区为8，系统初始时间则为1970-1-1 08:00:00开始计算
        //则偏移的毫秒数应该为 8(时) * 60(分) * 60(秒) * 1000(毫秒)
        int curTimeZoneRawOffsetMillis = nowCalendar.getTimeZone().getRawOffset();
        //就是系统当前时间 距离所在时区的初始时间 偏移了多少天，说白了就是现在已经过了多少天
        long daysOffsetToday = (System.currentTimeMillis() + curTimeZoneRawOffsetMillis) / 86400000;

        long theTimeMilliSecends = Long.parseLong(theMillisTime);
        //计算参数中所给的时间距离所在时区的初始时间 偏移了多少天，说白了就是theMillisTime这个时间已经过了多少天
        long theTimeOffsetDays = (theTimeMilliSecends + curTimeZoneRawOffsetMillis)/86400000;
        return theTimeOffsetDays - daysOffsetToday;//参数时间 的已过天数 - 现在已过的天数;
    }

    private static String dayOfWeekDesc(int dayOfWeek) {
        String desc = "周一";
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                break;
            case Calendar.TUESDAY:
                desc = "周二";
                break;
            case Calendar.WEDNESDAY:
                desc = "周三";
                break;
            case Calendar.THURSDAY:
                desc = "周四";
                break;
            case Calendar.FRIDAY:
                desc = "周五";
                break;
            case Calendar.SATURDAY:
                desc = "周六";
                break;
            case Calendar.SUNDAY:
                desc = "周日";
                break;
        }
        return desc;
    }

    /**
     * 计算出指定的日期的将来或者过去的指定天数。
     *
     * @param date
     *            date 计算的基准日期
     * @param dayNum dayNum 指定的天数
     * @param flag
     *            flag 若flag是true，要计算将来日期，否则，是过去日期
     *
     * @return String compDate 计算后的日期
     */
    public static String compDate(Date date, int dayNum, Boolean flag)
    {
        if (!date.equals(null))
        {
            // 格式化日期Date
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = df.format(date);
            // 获取给定日期的年，月，日
            String strYear = strDate.substring(0, 4);
            int intYear = Integer.valueOf(strYear);
            String strMonth = strDate.substring(5, 7);
            int intMonth = Integer.valueOf(strMonth);
            String strDay = strDate.substring(8, 10);
            int intDay = Integer.valueOf(strDay);
            int compDay;
            // 传入的flag做出对应的将来日期计算
            if (flag)
            {
                // 分出月份来计算
                compDay = intDay + dayNum;
                switch (intMonth)
                {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                        // 计算出的天数大于31时，天数减1，月份加1
                        if (compDay > 31)
                        {
                            compDay = compDay - 31;
                            intMonth += 1;
                        }
                        else if (compDay <= 31)
                        {
                            intDay = compDay;
                        }
                        break;
                    case 12:
                        if (compDay > 31)
                        {
                            compDay = compDay - 31;
                            intMonth = 1;
                            intYear += 1;
                        }
                        else if (compDay <= 31)
                        {
                            intDay = compDay;
                        }
                        break;
                    case 2:
                        // 闰年的二月
                        if ((intYear % 4 == 0 && intYear % 100 != 0)
                                || (intYear % 400 == 0))
                        {
                            if (compDay > 29)
                            {
                                compDay = compDay - 29;
                                intMonth += 1;
                            }
                            else if (compDay <= 29)
                            {
                                intDay = compDay;
                            }
                        }
                        else
                        {
                            if (compDay > 28)
                            {
                                compDay = compDay - 28;
                                intMonth += 1;
                            }
                            else if (compDay <= 28)
                            {
                                intDay = compDay;
                            }
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        // 计算出的天数大于31时，天数减1，月份加1
                        if (compDay > 30)
                        {
                            compDay = compDay - 30;
                            intMonth += 1;
                        }
                        else if (compDay <= 30)
                        {
                            intDay = compDay;
                        }
                        break;
                }

            }
            // 传入的flag做出对应过去的日期计算
            if (!flag)
            {
                // 分出月份来计算
                compDay = intDay - dayNum;
                switch (intMonth)
                {
                    case 1:
                        if (compDay < 0)
                        {
                            compDay = (intDay + 31) - dayNum;
                            intMonth = 12;
                            intYear -= 1;
                        }
                        else if (compDay > 0)
                        {
                            intDay = compDay;
                        }
                        else if (compDay == 0)
                        {
                            intDay = 31;
                            intMonth = 12;
                            intYear -= 1;
                        }
                        break;
                    case 3:
                        // 闰年的二月
                        if (compDay < 0)
                        {
                            if ((intYear % 4 == 0 && intYear % 100 != 0)
                                    || (intYear % 400 == 0))
                            {
                                compDay = (intDay + 29) - dayNum;

                            }
                            else
                            {
                                compDay = (intDay + 28) - dayNum;
                            }
                            intMonth = 2;
                        }
                        else if (compDay > 0)
                        {
                            intDay = compDay;
                        }
                        else if (compDay == 0)
                        {
                            if ((intYear % 4 == 0 && intYear % 100 != 0)
                                    || (intYear % 400 == 0))
                            {
                                intDay = 29;

                            }
                            else
                            {
                                intDay = 28;
                            }
                            intMonth = 2;
                        }
                        break;
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        // 计算出的天数小于0时，天数加上上个月的天数，在计算结果，月份减1
                        if (compDay < 0)
                        {
                            compDay = (intDay + 30) - dayNum;
                            intMonth -= 1;
                        }
                        else if (compDay == 0)
                        {
                            intDay = 30;
                            intMonth -= 1;
                        }
                        else if (compDay > 0)
                        {
                            intDay = compDay;
                        }
                        break;
                    case 2:
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        // 计算出的天数小于0时，天数加上上个月的天数，在计算结果，月份减1
                        if (compDay < 0)
                        {
                            compDay = (intDay + 31) - dayNum;
                            intMonth -= 1;
                        }
                        else if (compDay == 0)
                        {
                            intDay = 31;
                            intMonth -= 1;
                        }
                        else if (compDay > 0)
                        {
                            intDay = compDay;
                        }
                        break;
                }

            }
            // 经过计算后的年月日拼接成字符串
            strYear = String.valueOf(intYear);
            strMonth = String.valueOf(intMonth);
            if (intMonth / 10 == 0)
            {
                strMonth = "0" + strMonth;
            }
            strDay = String.valueOf(intDay);
            if (intDay / 10 == 0)
            {
                strDay = "0" + strDay;
            }
            strDate = strYear + "-" + strMonth + "-" + strDay;
            return strDate;
        }
        return null;
    }

    /**
     * 将未指定格式的日期字符串转化成java.util.Date类型日期对象 <br>
     *
     * @param date
     *            ,待转换的日期字符串
     * @return
     * @throws ParseException
     */
    public Date parseStringToDate(String date) throws ParseException
    {
        Date result = null;
        String parse = date;
        parse = parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
        parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
        parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
        parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");

        SimpleDateFormat format = new SimpleDateFormat(parse);

        result = format.parse(date);

        return result;
    }

    /**
     * 计算两个日期型的时间相差多少时间
     *
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return
     */
    public String twoDateDistance(Date startDate, Date endDate)
    {

        if (startDate == null || endDate == null)
        {
            return null;
        }
        long timeLong = endDate.getTime() - startDate.getTime();
        if (timeLong < 60 * 1000)
            return timeLong / 1000 + "秒前";
        else if (timeLong < 60 * 60 * 1000)
        {
            timeLong = timeLong / 1000 / 60;
            return timeLong + "分钟前";
        }
        else if (timeLong < 60 * 60 * 24 * 1000)
        {
            timeLong = timeLong / 60 / 60 / 1000;
            return timeLong + "小时前";
        }
        else if (timeLong < 60 * 60 * 24 * 1000 * 7)
        {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        }
        else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4)
        {
            timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
            return timeLong + "周前";
        }
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
            return sdf.format(startDate);
        }
    }

    /**
     * 将GMT 时间转换成年月日或者月日（如果是当年则显示月日）
     * 即Tue May 31 17:46:55 +0800 2011   2011-3-11
     * @param timeGMT
     * @return
     */
    public static String formatGMTStr(String timeGMT){
        int currentYear;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy",Locale.US);
        try {
            Date date =sdf.parse(timeGMT);
            Calendar calendar = Calendar.getInstance();
            currentYear = calendar.get(Calendar.YEAR);
            calendar.setTime(date);
            //去年甚至更久以前
            if (calendar.get(Calendar.YEAR) != currentYear){
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(calendar.getTime());
            }else{
                sdf = new SimpleDateFormat("MM-dd");
                return sdf.format(calendar.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
