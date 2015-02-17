package br.com.ramazzini.controller.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.model.acao.Acao;
import br.com.ramazzini.model.modulo.Modulo;
import br.com.ramazzini.model.perfil.Perfil;
import br.com.ramazzini.model.perfilTela.PerfilTela;
import br.com.ramazzini.model.tela.Tela;
import br.com.ramazzini.service.seguranca.AcaoService;
import br.com.ramazzini.service.seguranca.ModuloService;
import br.com.ramazzini.service.seguranca.PerfilService;
import br.com.ramazzini.service.seguranca.PerfilTelaService;
import br.com.ramazzini.service.seguranca.TelaService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class PerfilController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private PerfilService perfilService;
	@Inject private ModuloService moduloService;
	@Inject private TelaService telaService;
	@Inject private PerfilTelaService perfilTelaService;
	@Inject private AcaoService acaoService;		

	private List<Perfil> perfis;
	
	private List<PerfilTela> perfisTelas;

	private List<Modulo> modulos;
	
	private List<Modulo> modulosDoPerfil;

	private List<Tela> telas;
	
	private DualListModel<Acao> acoes;
	
	private Perfil perfilSelecionado;

	private Modulo moduloSelecionado;

	private Tela telaSelecionada;
	
	private PerfilTela perfilTelaSelecionado;
	
	private Modulo filtroModulo;
	
	@PostConstruct
	public void init() {

		perfis = perfilService.recuperarTodos("nome");
		//setModulos(moduloService.recuperarTodos("nome"));

		beginConversation();
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public Perfil getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Perfil perfilSelecionado) {
		this.perfilSelecionado = perfilSelecionado;
	}

	public String alterarPerfil(Perfil perfil) {
		setPerfilSelecionado(perfil);
		setModulos(moduloService.recuperarTodos("nome"));
		getPerfisTelas().clear();
		return "/pages/perfil/alterarPerfil.jsf?faces-redirect=true";
	}

	public List<Modulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}

	public List<Modulo> getModulosDoPerfil() {
		modulosDoPerfil = moduloService.recuperarPorPerfil(perfilSelecionado);
		return modulosDoPerfil;
	}

	public void setModulosDoPerfil(List<Modulo> modulosDoPerfil) {
		this.modulosDoPerfil = modulosDoPerfil;
	}

	public Modulo getModuloSelecionado() {
		return moduloSelecionado;
	}

	public void setModuloSelecionado(Modulo moduloSelecionado) {
		this.moduloSelecionado = moduloSelecionado;
	}

	public List<Tela> getTelas() {
		return telas;
	}

	public void setTelas(List<Tela> telas) {
		this.telas = telas;
	}

	public DualListModel<Acao> getAcoes() {
		return acoes;
	}

	public void setAcoes(DualListModel<Acao> acoes) {
		this.acoes = acoes;
	}

	public Tela getTelaSelecionada() {
		return telaSelecionada;
	}

	public void setTelaSelecionada(Tela telaSelecionada) {
		this.telaSelecionada = telaSelecionada;
	}

	public PerfilTela getPerfilTelaSelecionado() {
		return perfilTelaSelecionado;
	}

	public void setPerfilTelaSelecionado(PerfilTela perfilTelaSelecionado) {
		this.perfilTelaSelecionado = perfilTelaSelecionado;
	}

	public void perfilChange() {
		setTelas(telaService.recuperarPorModulo(getModuloSelecionado(), false, "nome"));
	}
	
    public void autorizarTela() {
        
		if (perfilSelecionado == null) {
			UtilMensagens.mensagemInformacao("Perfil não selecionado!");
			return;
		}
		
		if (telaSelecionada == null) {
			UtilMensagens.mensagemErro("Tela não selecionada!");
			return;
		}
		
    	if (perfilService.incluirTelaVerificandoExistencia(perfilSelecionado, telaSelecionada)) {
    		UtilMensagens.mensagemInformacao("Tela incluída com sucesso!");
    		getPerfisTelas().clear();
    	} else {
    		UtilMensagens.mensagemErro("Tela já autorizada!");
    	}

    }

	public List<PerfilTela> getPerfisTelas() {
		if (perfisTelas == null || perfisTelas.isEmpty()) {
			if (filtroModulo == null) {
				perfisTelas = perfilTelaService.recuperarPorPerfil(perfilSelecionado);
			} else {
				perfisTelas = perfilTelaService.recuperarPorPerfilModulo(perfilSelecionado, filtroModulo);
			}
		}
		return perfisTelas;
	}
	
    public void removerPerfilTela(PerfilTela perfilTela){
    	if (perfilTelaService.removerEmCascata(perfilTela)) {
    		perfisTelas.clear();
    		UtilMensagens.mensagemInformacao("Acesso removido!");
    	} else {
            UtilMensagens.mensagemErro("Não foi possível exluir o acesso do Perfil!");            
        }            
    }
    
    public String editarPerfilTela(PerfilTela perfilTela) {
    	
    	// Buscando tudo para evitar LazyInitializationException
    	PerfilTela pt = perfilTelaService.recuperarTudoPorId(perfilTela.getId());
    	
    	setPerfilTelaSelecionado(pt);
    	
        //PickList:
    	List<Acao> acoesDaTela = acaoService.recuperarPorTela(pt.getTela());
        List<Acao> acoesSource = new ArrayList<Acao>();
        List<Acao> acoesTarget = new ArrayList<Acao>();
        
    	for (Acao a : acoesDaTela) {
    		
    		String nome = a.getNome();
    		Long idAcao = a.getId();
    		boolean autorizada = false;
    		for (Acao a2 : pt.getAcoes()) {
    			if (nome.equals(a2.getNome()) && idAcao == a2.getId()) {
    				autorizada = true;
    				break;
    			}
    		}
    		if (autorizada) {
    			acoesTarget.add(a);
    		} else {
    			acoesSource.add(a);
    		}
    	}
    	
        acoes = new DualListModel<Acao>(acoesSource, acoesTarget);    	
    	
    	return "/pages/perfil/alterarPerfilTela.jsf?faces-redirect=true";
    }

    public void onTransferAcao(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Acao) item).getNome()).append("<br />");
        }
    } 
    
    public void autorizarAcao() {
    	
    	perfilTelaSelecionado.getAcoes().clear();
    	perfilTelaService.salvar(perfilTelaSelecionado);
    	
    	perfilTelaSelecionado.getAcoes().addAll(acoes.getTarget());
    	perfilTelaService.salvar(perfilTelaSelecionado);
    	
    	UtilMensagens.mensagemInformacao("Atualizado!");
    }
    
    public void ativaDesativaTela(PerfilTela perfilTela) {
    	telaService.salvar(perfilTela.getTela());
    	String msg = perfilTela.getTela().isAtiva() ? "Tela ativada!" : "Atenção: Tela Desativada!";
    	UtilMensagens.mensagemInformacao(msg);
    }

	public Modulo getFiltroModulo() {
		return filtroModulo;
	}

	public void setFiltroModulo(Modulo filtroModulo) {
		this.filtroModulo = filtroModulo;
	}
	
	public void filtrarModulosChange() {
		perfisTelas.clear();
	}	
    
}