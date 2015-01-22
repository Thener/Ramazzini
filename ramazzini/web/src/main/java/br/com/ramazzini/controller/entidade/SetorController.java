package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.setor.Setor;
import br.com.ramazzini.service.entidade.SetorService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class SetorController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_SETOR = "/pages/setor/cadastroSetor.jsf?faces-redirect=true";
	private static final String PAGINA_CADASTRO_EMPRESA = "/pages/empresa/cadastroEmpresa.jsf?faces-redirect=true";
	
    @Inject
    private SetorService setorService; 
    
    private List<Setor> setores;
    
    private Empresa empresa;
    
    private Setor setor;
    
    private String nomeSetorPesquisa;
    
	public String incluirSetor() {
		
		setor = new Setor();
		setor.setEmpresa(empresa);
		return cadastroSetor(setor, Boolean.FALSE);
	}
	    
    public String alterarSetor(Setor setor){
    	
    	return cadastroSetor(setor, Boolean.FALSE);
    }
    
    public String visualizarSetor(Setor setor){
    	
    	return cadastroSetor(setor, Boolean.TRUE);
    }
    
    public void removerSetor(Setor setor){
    	
    	try {
    		setorService.remover(setor, setor.getId());
    		setores.remove(setor);
    		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeExcluidaComSucesso", "Setor");
    	} catch (Exception e) {
    		UtilMensagens.mensagemErroPorChave("mensagem.erro.naoFoiPossivelExcluirRegistro", "o setor.");
        }
    }    
    
    private String cadastroSetor(Setor setor, Boolean somenteLeitura) {

    	setSetor(setor);
    	setSomenteLeitura(somenteLeitura);
    	return PAGINA_CADASTRO_SETOR;    	
    }
    
	public String gravarSetor() {
		
		setorService.salvar(setor);
		pesquisar();
		return PAGINA_CADASTRO_EMPRESA;
	}
    
    public void pesquisar() {
		
    	if (nomeSetorPesquisa == null || nomeSetorPesquisa.isEmpty()){
    		setores = setorService.recuperarPorEmpresa(empresa);
		} else {
			setores = setorService.recuperarPorNome(empresa, nomeSetorPesquisa);
		}      
    }    
	
	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		setSetores(null); // se está mudando a empresa, então zerar a lista.
		this.empresa = empresa;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getNomeSetorPesquisa() {
		return nomeSetorPesquisa;
	}

	public void setNomeSetorPesquisa(String nomeSetorPesquisa) {
		this.nomeSetorPesquisa = nomeSetorPesquisa;
	}

}