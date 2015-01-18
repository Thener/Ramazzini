package br.com.ramazzini.util;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TratarExcecao {

	private Exception e;
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");
	
	public TratarExcecao(Exception e) {
		this.e = e;
	}

	public String getMessage() {
		
		String erro = e.getCause().getCause().toString();
		
		if (erro.contains("org.hibernate.exception.ConstraintViolationException")) {
			return bundle.getString("constraint."+extrairConstraint(erro));
		}
		
		return "";

	}
	
	private String extrairConstraint(String txt) {
		
		//http://www.txt2re.com/
		
	    String re1=".*?";	
	    String re2="(\".*?\")";	

	    Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(txt);
	    if (m.find()) {
	        String string1=m.group(1);
	        String constraint = string1.toString();
	        return constraint.replace("\"", "");
	    }
		
	    return "";
	}

}
