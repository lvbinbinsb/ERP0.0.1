package cn.erp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class DateTag extends TagSupport{
	private static final long serialVersionUID = 6464168398214506236L;

    private Long value;

    @Override
    public int doStartTag() throws JspException {
       
        try {
          
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(value);
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String s = dateformat.format(c.getTime());
            pageContext.getOut().write(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
