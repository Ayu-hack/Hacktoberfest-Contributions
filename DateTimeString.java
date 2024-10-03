import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getCurrentDateTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
}
