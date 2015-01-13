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
	
	private static final String PAGINA_INCLUIR_USUARIO = "incluirUsuario.jsf?faces-redirect=true";
	private static final String PAGINA_ALTERAR_ALTERAR_USUARIO = "alterarUsuario.jsf?faces-redirect=true";
	
	private @Inject Conversation conversation;

	@Inject
    private UsuarioService usuarioService;  
    
    @Inject
	private PerfilService perfilService;
    
    private Usuario usuarioNovo;    
    private Usuario usuarioSelecionado;    
    private List<Usuario> usuarios;
    
    private Perfil perfilSelecionado;
    private List<Perfil> perfisDisponiveis;
    private List<Perfil> perfisUsuario;
    
    private String loginPesquisa;
    private String gridMsg;   
    
    private String senhaAtual;
    private String senhaNova;
    private String senhaNovaConfirmacao;
    
    private boolean somenteLeitura = Boolean.FALSE;
       
    @PostConstruct  
    public void init() {
    	usuarios = new ArrayList<Usuario>();
    	usuarioNovo = new Usuario();
    	senhaNova = "";
    	gridMsg = "";
    	somenteLeitura = Boolean.FALSE; 
    	if (conversation.isTransient()) {
			conversation.begin();
		}
    }
          
    public String salvar() throws Exception {
        try {
        	usuarioNovo.setSenha(Md5.hashMd5(usuarioNovo.getSenha())); 
        	perfisUsuario.add(perfilSelecionado);
        	perfisDisponiveis.remove(perfilSelecionado);
        	usuarioNovo.setPerfis(perfisUsuario);
            usuarioService.salvar(usuarioNovo);
            UtilMensagens.mensagemInformacao("Usuario salvo com sucesso!");
            String alterar = editar(usuarioNovo);
            init();
            return alterar;
        } catch (Exception e) {
        	UtilMensagens.mensagemErro("Não foi possível salvar o Usuário!");
        	init();
            return null;
        }
    } 
    
    public void atualizar() throws Exception {
        try {
        	if (!senhaNova.isEmpty()){
        		usuarioSelecionado.setSenha(Md5.hashMd5(senhaNova));
        	}
            usuarioService.salvar(usuarioSelecionado);
            init();
            UtilMensagens.mensagemInformacao("Usuario alterado com sucesso!");
        } catch (Exception e) {
        	UtilMensagens.mensagemErro("Não foi possível alterar o Usuário!");
        	init();  
        }
    }  
    
    public void remover(Usuario usuario){
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
    		usuarioSelecionado.setPerfis(perfisUsuario);
    		atualizar();
    		UtilMensagens.mensagemInformacao("Perfil removido com sucesso!");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErro("Não foi possível remover o Perfil!");            
        }            
    }
    
    public String editar(Usuario usuario){    	
    	setUsuarioSelecionado(usuarioService.recuperarPorId(usuario.getId()));
    	setSomenteLeitura(Boolean.FALSE);    	
    	perfisDisponiveis = perfilService.recuperarPerfisDisponiveisPorUsuario(usuario);
    	perfisUsuario = perfilService.recuperarTudoPorUsuario(usuario);
    	return PAGINA_ALTERAR_ALTERAR_USUARIO;
    }
    
    public String incluirUsuario(){
    	perfisDisponiveis = perfilService.recuperarTodosMenosAdmin();
    	perfisUsuario = new ArrayList<Perfil>();
    	return PAGINA_INCLUIR_USUARIO;
    }
    
    public String visualizar(Usuario usuario){
    	setUsuarioSelecionado(usuario);
    	setSomenteLeitura(Boolean.TRUE);
    	perfisDisponiveis = perfilService.recuperarPerfisDisponiveisPorUsuario(usuario);
    	perfisUsuario = perfilService.recuperarTudoPorUsuario(usuario);
    	return "alterarUsuario.js?faces-redirect=truef";
    }
    
    public void pesquisar() throws Exception {
		if (loginPesquisa.isEmpty()){
			usuarios = usuarioService.recuperarTodos("nome");        		
		} else {
			List<Usuario> usuariosRecuperados = usuarioService.recuperarPorLikeLogin(loginPesquisa);
			if (!usuariosRecuperados.isEmpty()) {
				usuarios=usuariosRecuperados;
			} else {
				init();
				gridMsg = "Login informado não cadastrado.";
			}
		}      
    }  
    
    public void adicionarPerfil(){
    	perfisUsuario.add(perfilSelecionado);
    	usuarioSelecionado.setPerfis(perfisUsuario);
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

	public String getGridMsg() {
		return gridMsg;
	}

	public void setGridMsg(String gridMsg) {
		this.gridMsg = gridMsg;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
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
	
	public Usuario getNovoUsuario() {
        return usuarioNovo;
    }
}
