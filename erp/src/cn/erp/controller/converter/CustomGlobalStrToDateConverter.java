package cn.erp.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomGlobalStrToDateConverter implements org.springframework.core.convert.converter.Converter<String,Long>{

	public Long convert(String arg0) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Long time=null;
		try {
			if(arg0.indexOf("-")>=0){
				
				time=sdf.parse(arg0).getTime();
			}else{
				time=Long.parseLong(arg0);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

}
