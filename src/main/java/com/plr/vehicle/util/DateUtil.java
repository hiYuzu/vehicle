package com.plr.vehicle.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author plr
 * @version V1.0
 * @date 2020/1/6 13:17
 */
public class DateUtil {

    /**
     * 日志输出标记
     */
    private static final String LOG = "DateUtil";
    /**
     * 声明日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 标准日期时间类型(yyyy-MM-dd HH:mm:ss)
     */
    public static final String DATA_TIME_SECOND = "yyyy-MM-dd HH:mm:ss";

    /**
     * 标准日期时间类型(yyyy-MM-dd)
     */
    public static final String DATA_DAY = "yyyy-MM-dd";

    /**
     * 获取系统时间
     *
     * @param millionSecond ：当前系统时间减去此参数
     * @return
     */
    public static Timestamp GetSystemDateTime(int millionSecond) {
        return new Timestamp(Calendar.getInstance().getTimeInMillis() - millionSecond);
    }

    /**
     * 获取17位时间字符串
     * @return
     */
    public static String GetSystemDateTime()
    {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strTime = fmt.format(new Date());
        return strTime;
    }

    /**
     * 将时间戳转换为标准时间
     * @param timestamp
     * @param format
     * @return
     */
    public static String TimestampToString(Timestamp timestamp, String format) {
        try {
            if (timestamp != null) {
                DateFormat dateFormat = new SimpleDateFormat(format);
                return dateFormat.format(timestamp);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(LOG + ":" + "转换日期失败，失败原因：" + e.getMessage());
            return null;
        }
    }

    /**
     * 将字符串转换成时间戳
     * @param datetime
     * @param format
     * @return
     */
    public static Timestamp StringToTimestamp(String datetime,String format) {
        try {
            if (datetime != null && !datetime.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                Date date = sdf.parse(datetime);
                return new Timestamp(date.getTime());
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(LOG + ":" + "转换日期失败，失败原因：" + e.getMessage());
            return null;
        }
    }

}
