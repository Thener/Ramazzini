package br.com.ramazzini.util;

import javax.servlet.http.HttpServletRequest;

public class Contexto {  
    
	private static ThreadLocal<Contexto> instance = new ThreadLocal<Contexto>();  
    
	private HttpServletRequest request;  
    
	private Contexto(HttpServletRequest request) {  
         this.request = request;  
    }  
    
	public static Contexto getCurrentInstance() {  
         return instance.get();  
    }  
    
	public static Contexto newInstance(HttpServletRequest request) {  
         Contexto context = new Contexto(request);  
         instance.set(context);  
         return context;  
    }  
    
	public void release() {  
         instance.remove();  
    }  
    
	public HttpServletRequest getRequest() {  
         return request;  
    }  
}
