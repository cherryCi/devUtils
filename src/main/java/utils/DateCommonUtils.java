package utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author cherry.cui
 * @version 1.0
 * @date 2021/2/23 14:18
 */
public class DateCommonUtils {


    /**
     * 校验当前时间是否晚于结束时间
     * @param currentTime
     * @param endTime 00:30
     * @return
     */
    public static boolean validEndTime(Date currentTime, String endTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        String[] endTimeArray = endTime.split(":");
        int endTimeHour = Integer.parseInt(endTimeArray[0]);
        int endTimeMinute = Integer.parseInt(endTimeArray[1]);
        return currentHour >endTimeHour || currentMinute>endTimeMinute;
    }

    /**
     * 获取当前时间是周几
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取本周一零点
     *
     * @return
     */
    public static Date getMondayDate() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayWeek == 1) {
            dayWeek = 8;
        }

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return cal.getTime();
    }
}
