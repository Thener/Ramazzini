package br.com.ramazzini.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.usuario.Usuario;
import br.com.ramazzini.service.PerfilService;
import br.com.ramazzini.service.UsuarioService;
import br.com.ramazzini.util.Md5;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class UsuarioController extends AbstractBean implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_USUARIO = "cadastroUsuario.jsf?faces-redirect=true";
	private static final String PAGINA_PESQUISAR_USUARIO = "pesquisarUsuario.jsf?faces-redirect=true";
	
	private @Inject Conversation conversation;

	@Inject
    private UsuarioService usuarioService;  
    
    @Inject
	private PerfilService perfilService;
    
    private Usuario usuario;    
    private List<Usuario> usuarios;
    
    private Perfil perfilSelecionado;
    private List<Perfil> perfisDisponiveis;
    private List<Perfil> perfisUsuario;
    
    private String loginPesquisa;
    
    private String senhaAtual;
    private String senhaNova;
    private String senhaNovaConfirmacao;
    
    private boolean acaoInclusao = Boolean.FALSE;
    
    private boolean somenteLeitura = Boolean.FALSE;
       
    @PostConstruct  
    public void init() {

    	if (conversation.isTransient()) {
			conversation.begin();
		}
    }
    
    public String gravar() {
    	
    	if (acaoInclusao == Boolean.TRUE) {
    		perfisUsuario.add(perfilSelecionado);
    		usuario.setSenha(Md5.hashMd5(usuario.getSenha())); 
    		usuario.setPerfis(perfisUsuario);
    		perfisDisponiveis.remove(perfilSelecionado);
    	} else {
        	if (!senhaNova.isEmpty()){
        		usuario.setSenha(Md5.hashMd5(senhaNova));
        	}    		
    	}
    	usuarioService.salvar(usuario);
    	
    	usuarios = usuarioService.recuperarTodos("nome");
    	
    	return PAGINA_PESQUISAR_USUARIO;
    }
    
    public void removerUsuario(Usuario usuario){
    	try {
    		usuarioService.remover(usuario, usuario.getId());
    		usuarios.remove(usuario);
    		UtilMensagens.mensagemInformacao("Usuario removido com sucesso!");    		
    	} catch (Exception e) {
    		UtilMensagens.mensagemErro("Não foi possível remover o Usuário!");                       
        }            
    }
    
    public void removerPerfilUsuario(Perfil perfil){
    	try {
    		perfisUsuario.remove(perfil);
    		perfisDisponiveis.add(perfil);
    		usuario.setPerfis(perfisUsuario);
    		UtilMensagens.mensagemInformacao("Perfil removido com sucesso!");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErro("Não foi possível remover o Perfil!");            
        }            
    }
    
    public String alterarUsuario(Usuario usuario){    	
    	setAcaoInclusao(Boolean.FALSE);
    	return cadastroUsuario(usuarioService.recuperarPorId(usuario.getId()), Boolean.FALSE);    	
    }
    
    public String incluirUsuario(){
    	usuario = new Usuario();
    	setAcaoInclusao(Boolean.TRUE);
    	return cadastroUsuario(usuario, Boolean.FALSE);
    }
    
    public String visualizarUsuario(Usuario usuario){
    	setAcaoInclusao(Boolean.FALSE);
    	return cadastroUsuario(usuario, Boolean.TRUE);
    }
    
    private String cadastroUsuario(Usuario usuario, boolean somenteLeitura) {
    	
    	this.usuario = usuario;
    	setSomenteLeitura(somenteLeitura);
    	
    	if (acaoInclusao == Boolean.TRUE) {
        	perfisDisponiveis = perfilService.recuperarTodosMenosAdmin();
        	perfisUsuario = new ArrayList<Perfil>();
    	} else {
        	perfisDisponiveis = perfilService.recuperarPerfisDisponiveisPorUsuario(this.usuario);
        	perfisUsuario = perfilService.recuperarTudoPorUsuario(this.usuario);
    	}
   	
    	return PAGINA_CADASTRO_USUARIO;
    }
    
    public void pesquisar() throws Exception {
    	
    	if (loginPesquisa == null || loginPesquisa.isEmpty()){
    		usuarios = usuarioService.recuperarTodos("nome");
		} else {
			usuarios = usuarioService.recuperarPorLikeLogin(loginPesquisa);
		} 		
    }  
    
    public void adicionarPerfil(){
    	
    	perfisUsuario.add(perfilSelecionado);
    	usuario.setPerfis(perfisUsuario);
    	perfisDisponiveis.remove(perfilSelecionado);
    }
    
    public void trocarSenha(Usuario usuarioLogado) throws Exception {
        try {
        	if (usuarioService.autenticar(usuarioLogado.getLogin(), senhaAtual)) {
        		if(senhaNova.equals(senhaNovaConfirmacao)){
	        		usuarioLogado.setSenha(Md5.hashMd5(senhaNova));
	        		usuarioService.salvar(usuarioLogado);
	        		UtilMensagens.mensagemInformacao("Senha alterada com sucesso!");
        		} else {
        			UtilMensagens.mensagemErro("Senha nova não confere com a senha de confirmação.");
        		}
    		} else {
    			UtilMensagens.mensagemErro("Senha atual incorreta! Favor verificar os dados informados.");
    		}
        } catch (Exception e) {
        	UtilMensagens.mensagemErro("Não foi possível alterar a senha do Usuário!");        	
        }
    }  
    
	public String getLoginPesquisa() {
		return loginPesquisa;
	}

	public void setLoginPesquisa(String loginPesquisa) {
		this.loginPesquisa = loginPesquisa;
	} 

	public boolean isSomenteLeitura() {
		return somenteLeitura;
	}

	public void setSomenteLeitura(boolean somenteLeitura) {
		this.somenteLeitura = somenteLeitura;
	}
	
	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	public List<Perfil> getPerfisDisponiveis() {
		return perfisDisponiveis;
	}

	public void setPerfisDisponiveis(List<Perfil> perfis) {
		this.perfisDisponiveis = perfis;
	}

	public Collection<Perfil> getPerfisUsuario() {
		return perfisUsuario;
	}

	public void setPerfisUsuario(List<Perfil> perfisUsuario) {
		this.perfisUsuario = perfisUsuario;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaNovaConfirmacao() {
		return senhaNovaConfirmacao;
	}

	public void setSenhaNovaConfirmacao(String senhaNovaConfirmacao) {
		this.senhaNovaConfirmacao = senhaNovaConfirmacao;
	}	
	
	public List<Usuario> getUsuarios() {
        return usuarios;
    }
	
	public void setAcaoInclusao(boolean acaoInclusao) {
		this.acaoInclusao = acaoInclusao;
	}
	
	public boolean isAcaoInclusao() {
		return acaoInclusao;
	}	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
}
