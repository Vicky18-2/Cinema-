package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Tag descriptor for tag library to translate interface
 */
public class LocaleTagDescriptor extends SimpleTagSupport {
    String locale;
    String text;

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        Properties props = new Properties();
        URL path = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        FileInputStream input;
        if (locale != null && locale.equals("eng")) {
            input = new FileInputStream("src/main/resources/engLocale.properties");
        } else {
            input = new FileInputStream("src/main/resources/uaLocale.properties");
        }
        props.load(new InputStreamReader(input));
        String result = props.getProperty(text);
        out.print(result);
    }
}
