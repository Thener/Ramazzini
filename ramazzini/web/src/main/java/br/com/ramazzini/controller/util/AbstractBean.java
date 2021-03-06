package br.com.ramazzini.controller.util;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.enterprise.context.Conversation;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.relatorio.FileService;
import br.com.ramazzini.service.util.Cliente;
import br.com.ramazzini.util.ControleAcesso;

public abstract class AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject private Conversation conversation;
	@Inject private HttpSession session;
	    
    private ControleAcesso controleAcesso = new ControleAcesso();
    
	private static ResourceBundle bundle;
    
    private String uriRequisicao;
    
    private FileService fileService;
    
    private boolean somenteLeitura = Boolean.FALSE;
    
    protected Cliente cliente = Cliente.getInstance();
    
	public ControleAcesso getControleAcesso() {
		return controleAcesso;
	}

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}
	
	public String getUriRequisicao() {
		return uriRequisicao;
	}

	public void setUriRequisicao(String uriRequisicao) {
		this.uriRequisicao = uriRequisicao;
	}	
	
    public String getFormattedTime(Date time, String format) {  
        
    	if (time == null) {  
            return null;  
        }  
  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);  
        return simpleDateFormat.format(time);  
    }	
    
    private static ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "msgs");
		}
		return bundle;
	}
    
    protected File getFileRelatorio(final String... relativePaths) {
		HttpServletRequest request = getRequest();
		fileService = new FileService(request.getServletContext().getRealPath("/WEB-INF/jasper/"));
		return fileService.getFile(relativePaths);
	}

	protected HttpServletRequest getRequest() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request;
	}
	
	protected String getCaminhoLogo() {
		return getRequest().getServletContext().getRealPath("/resources/img/cliente")+ "\\";
	}
    
    protected static String getValorChaveMsg(String chave) {
		try {
			return getBundle().getString(chave);	
		} catch(Exception e) {
			return chave;
		}		
	}
    
    protected static String getValorChaveMsg(String chave, String... parameters) {
		
    	int i = 0;
		String parametro;
		String mensagem = getValorChaveMsg(chave);
		for (String p : parameters) {
			parametro = "{"+i+"}";
			mensagem = mensagem.replace(parametro, getValorChaveMsg(p));
			i++;
		}
		return mensagem;
	}    
    
	public void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}
	
	public String getConversationId() {
		beginConversation();
		return conversation.getId();
	}
	
	public Usuario getUsuarioLogado() {
		return (Usuario) session.getAttribute("usuarioLogado");
	}	
}