package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Convert string date to sql.Date
 */
public class ConvertStringToDateSQL {
    public static Date convert(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date langDate = null;
        try {
            langDate = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sql = new java.sql.Date(langDate.getTime());
        return sql;
    }
}
