package br.com.ramazzini.controller.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.model.funcionario.SituacaoFuncionario;
import br.com.ramazzini.service.entidade.FuncionarioService;

@Named
@ConversationScoped
public class ListagemFuncionarioController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private FuncionarioService funcionarioService; 
    
    private List<Funcionario> funcionarios;
    
    //Filtros
    private Funcao funcao;
    private SituacaoFuncionario situacao = SituacaoFuncionario.ATIVO;
	private Date dataSelecionada;

    

    public void export() {
    	funcionarios = funcionarioService.recuperarPor(funcao, situacao, null);    	
    }
		
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
 
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}


	public Funcao getFuncao() {
		return funcao;
	}


	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}


	public SituacaoFuncionario getSituacao() {
		return situacao;
	}


	public void setSituacao(SituacaoFuncionario situacao) {
		this.situacao = situacao;
	}


	public Date getDataSelecionada() {
		return dataSelecionada;
	}


	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}
	
}