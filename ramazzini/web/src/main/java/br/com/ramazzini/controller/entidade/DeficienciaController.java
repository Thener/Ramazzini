package br.com.ramazzini.controller.entidade;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.funcionario.Deficiencia;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.service.entidade.FuncionarioService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class DeficienciaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PAGINA_CADASTRO_DEFICIENCIA = "/pages/funcionario/cadastroDeficiencia.jsf?faces-redirect=true";
	
    @Inject private FuncionarioService funcionarioService;
    
    private Funcionario funcionario;
    private Deficiencia deficiencia;
    
    private Integer tabAtiva;
    
	public String incluirDeficiencia() {
		deficiencia = new Deficiencia();
		return cadatroDeficiencia(deficiencia, Boolean.FALSE);
	}
	    
    public String alterarDeficiencia(Deficiencia deficiencia){
    	
    	return cadatroDeficiencia(deficiencia, Boolean.FALSE);
    }    
    
    public String visualizarDeficiencia(Deficiencia deficiencia){
    	
    	return cadatroDeficiencia(deficiencia, Boolean.TRUE);
    }
    
    private String cadatroDeficiencia(Deficiencia deficiencia, Boolean somenteLeitura) {
    	setDeficiencia(deficiencia);
    	setSomenteLeitura(somenteLeitura);
    	setUriRequisicao(getControleAcesso().getUriRequisicao());
    	return PAGINA_CADASTRO_DEFICIENCIA;    	
    }
    
	public String gravarDeficiencia() {
		funcionario.setDeficiencia(deficiencia);
		funcionarioService.salvar(funcionario);
		UtilMensagens.mensagemInformacaoPorChave("mensagem.info.entidadeGravadaComSucesso");
		return "";
	}
    
    public String voltar() {				
		return getUriRequisicao()+"?faces-redirect=true";
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getTabActiveIndex() {
        return tabAtiva;
    }

    public void setTabActiveIndex(Integer tabActiveIndex) {
        this.tabAtiva = tabActiveIndex;
    }

	public String getIdadeFuncionario() {
		return funcionario.getIdadeTexto();
	}

	public Deficiencia getDeficiencia() {
		return deficiencia;
	}

	public void setDeficiencia(Deficiencia deficiencia) {
		this.deficiencia = deficiencia;
	}
	
}