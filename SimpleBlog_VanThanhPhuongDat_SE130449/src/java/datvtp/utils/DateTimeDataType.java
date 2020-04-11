package datvtp.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeDataType implements Serializable {
    public static String getTimeNow() {
        Date date = new Date();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStr = spf.format(date);

        return timeStr;
    }
}
