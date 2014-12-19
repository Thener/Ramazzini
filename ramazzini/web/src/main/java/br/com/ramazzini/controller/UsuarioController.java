package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.UsuarioService;

@Named
@ConversationScoped
public class UsuarioController implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private FacesContext facesContext; 
    
    @Inject
    private UsuarioService usuarioService;   
    
    private Usuario usuarioNovo;
    
    @Inject @Named("usuarioLogado") 
    private Usuario usuarioLogado;
    
    private List<Usuario> usuarios;

    
    @Produces
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Usuario usuario) {
    	usuarios = usuarioService.recuperarTodos("nome");
    }

    @PostConstruct
    public void init() {
    	usuarios = usuarioService.recuperarTodos("nome");
    	usuarioNovo = new Usuario();
    }
    @Produces
    public Usuario getNovoUsuario() {
        return usuarioNovo;
    }
  
    public void salvar() throws Exception {
        try {
            usuarioService.salvar(usuarioNovo, usuarioLogado);
            facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Usuario cadastrado com sucesso!"));
            usuarioNovo = new Usuario();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Usuario não cadastrado!");
            facesContext.addMessage(null, m);
        }
    }  
    private String getRootErrorMessage(Exception e) {
        String errorMessage = "Registro falhou. Veja o log do servidor para mais informações.";
        if (e == null) {
            // Se não houver uma Exception, retorna a mensagem padrão
            return errorMessage;
        }
        Throwable t = e;
        while (t != null) {
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        return errorMessage;
    }
}
