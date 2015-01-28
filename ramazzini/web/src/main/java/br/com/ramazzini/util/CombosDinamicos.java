package br.com.ramazzini.util;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.ramazzini.model.cbo.Cbo;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.funcao.Funcao;
import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;
import br.com.ramazzini.model.lotacao.Lotacao;
import br.com.ramazzini.model.procedimento.Procedimento;
import br.com.ramazzini.model.procedimento.TipoProcedimento;
import br.com.ramazzini.model.profissional.PapelProfissional;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.setor.Setor;
import br.com.ramazzini.service.entidade.CboService;
import br.com.ramazzini.service.entidade.FuncaoService;
import br.com.ramazzini.service.entidade.HorarioAtendimentoService;
import br.com.ramazzini.service.entidade.LotacaoService;
import br.com.ramazzini.service.entidade.ProcedimentoService;
import br.com.ramazzini.service.entidade.ProfissionalService;
import br.com.ramazzini.service.entidade.SetorService;

@Named("combosDinamicos")
public class CombosDinamicos {

    @Inject
    CboService cboService; 
    
    @Inject
    FuncaoService funcaoService;
    
    @Inject
    HorarioAtendimentoService horarioAtendimentoService;    
    
    @Inject
    LotacaoService lotacaoService; 
    
    @Inject
    ProcedimentoService procedimentoService;
    
    @Inject
    ProfissionalService profissionalService;
    
    @Inject
    SetorService setorService;    
    
	public List<Cbo> getCbos() {
		return cboService.recuperarTodos("numero");
	}
	
	public List<Funcao> getFuncoes(Empresa empresa) {
		return funcaoService.recuperarPorEmpresa(empresa);
	}
	
	public List<HorarioAtendimento> getHorariosAtendimento() {
		return horarioAtendimentoService.recuperarTodos("nome");
	}	
	
	public List<Lotacao> getLotacoes(Empresa empresa) {
		return lotacaoService.recuperarPorEmpresa(empresa);
	}	

	public List<Procedimento> getProcedimentos() {
		return procedimentoService.recuperarTodos("nome");
	}
	
	public List<Procedimento> getProcedimentosTipoExameClinicoOcupacional() {
		return procedimentoService.recuperarPorTipoProcedimento(TipoProcedimento.EXAME_CLINICO_OCUPACIONAL);
	}	
	
	public List<Profissional> getProfissionais() {
		return profissionalService.recuperarTodos("nome");
	}
	
	public List<Profissional> getProfissionaisMedicos() {
		return profissionalService.recuperarPorPapelProfissional(PapelProfissional.MEDICO, Boolean.TRUE);
	}	
	
	public List<Setor> getSetores(Empresa empresa) {
		return setorService.recuperarPorEmpresa(empresa);
	}		
}
