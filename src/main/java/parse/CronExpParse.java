package parse;

import constant.CommonConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.core.util.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author cherry
 * @version 1.0
 * @date 2020/10/24 11:06
 */
public class CronExpParse {


    private final static String PATTERN_CHAR = "*";

    private final static String PLACEOLDER = "#";

    /**
     * 生成cron  表达式
     *
     * @param date
     * @param dateFormat
     * @return
     */
    public static String transferCronExp(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }


    /**
     * 将cron 解析成中文文本
     *
     * @param cronExp
     * @return
     */
    public static String translateToChinese(String cronExp) {
        if (cronExp == null || cronExp.length() < 1) {
            return null;
        }
        CronExpression exp = null;
        // 初始化cron表达式解析器
        try {
            exp = new CronExpression(cronExp);
        } catch (ParseException e) {
            return null;
        }
        List<String> cronLists = Arrays.asList(cronExp.split(" "));
        StringBuffer sBuffer = new StringBuffer();
        if (CollectionUtils.isEmpty(cronLists)) {
            return null;
        }
        if (cronLists.size() != 6) {
            return null;
        }

        //解析月

        if (!cronLists.get(4).equals(PATTERN_CHAR)) {
            sBuffer.append(cronLists.get(4)).append("月");
        } else {
            sBuffer.append("每月");
        }
        //解析周
        if (!cronLists.get(5).equals(PATTERN_CHAR) && !cronLists.get(5).equals("?")) {
            char[] tmpArray = cronLists.get(5).toCharArray();
            for (char tmp : tmpArray) {
                switch (tmp) {
                    case '1':
                        sBuffer.append("星期天");
                        break;
                    case '2':
                        sBuffer.append("星期一");
                        break;
                    case '3':
                        sBuffer.append("星期二");
                        break;
                    case '4':
                        sBuffer.append("星期三");
                        break;
                    case '5':
                        sBuffer.append("星期四");
                        break;
                    case '6':
                        sBuffer.append("星期五");
                        break;
                    case '7':
                        sBuffer.append("星期六");
                        break;
                    case '-':
                        sBuffer.append("至");
                        break;
                    default:
                        sBuffer.append(tmp);
                        break;
                }
            }
        }

        //解析日
        if (!cronLists.get(3).equals("?")) {
            if (!cronLists.get(3).equals(PATTERN_CHAR)) {
                sBuffer.append(cronLists.get(3)).append("日");
            } else {
                sBuffer.append("每日");
            }
        }

        //解析时
        if (!cronLists.get(2).equals(PATTERN_CHAR)) {
            sBuffer.append(cronLists.get(2)).append("时");
        } else {
            sBuffer.append("每时");
        }

        //解析分
        if (!cronLists.get(1).equals(PATTERN_CHAR)) {
            sBuffer.append(cronLists.get(1)).append("分");
        } else {
            sBuffer.append("每分");
        }

        //解析秒
        if (!cronLists.get(0).equals(PATTERN_CHAR)) {
            sBuffer.append(cronLists.get(0)).append("秒");
        } else {
            sBuffer.append("每秒");
        }
        return sBuffer.toString();
    }


    public static void main(String[] args) {
        //每日执行的cron表达式
        System.out.println(transferCronExp(Calendar.getInstance().getTime(), CommonConstant.DAY_EXP));

        //每周执行的cron表达式
        System.out.println(transferCronExp(Calendar.getInstance().getTime(), CommonConstant.WEEK_EXP));


        //每月执行的cron表达式
        System.out.println(transferCronExp(Calendar.getInstance().getTime(), CommonConstant.MONTH_EXP));


        System.out.println(translateToChinese("03 15 11 24 * ?"));
    }


}
