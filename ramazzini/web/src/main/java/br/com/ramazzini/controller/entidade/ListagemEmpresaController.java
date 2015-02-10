package br.com.ramazzini.controller.entidade;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.ramazzini.controller.util.AbstractBean;
import br.com.ramazzini.controller.util.ExportarPdfController;
import br.com.ramazzini.model.empresa.Empresa;
import br.com.ramazzini.model.empresa.SituacaoEmpresa;
import br.com.ramazzini.model.grupo.Grupo;
import br.com.ramazzini.service.entidade.EmpresaService;
import br.com.ramazzini.util.UtilMensagens;

@Named
@ConversationScoped
public class ListagemEmpresaController extends AbstractBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Inject
    private EmpresaService empresaService; 
    
    private List<Empresa> empresas;
    
    String PATH_DIRECTORY_LISTAGEM_EMPRESAS = "EMPRESA";
    String JASPER_EMPRESAS = "Empresas.jasper";
    
    //Filtros
    private Grupo grupo;
    private Date dataSelecionada;

    private List<String> situacoes = new ArrayList<String>();
    
    @PostConstruct
	public void init() {
    	situacoes = new ArrayList<String>();
    	situacoes.add(SituacaoEmpresa.ATIVA.getValue());
	}
    
    public void export() throws Exception {
    	empresas = empresaService.recuperarPor(situacoes, grupo, dataSelecionada);
    	if (!empresas.isEmpty()){
	    	File relatorio = getCaminhoRelatorio(PATH_DIRECTORY_LISTAGEM_EMPRESAS, JASPER_EMPRESAS);
	    	ExportarPdfController export = new ExportarPdfController(carregaParametros(), new JRBeanCollectionDataSource(empresas), "Listagem_Empresas", relatorio);
			export.download();
    	} else{
			UtilMensagens.mensagemInformacaoPorChave("mensagem.info.nenhumRegistroLocalizado");
    	}
    }
    
    private HashMap<String, Object> carregaParametros() {
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("PARAM_CLIENTE", super.cliente);
		StringBuilder sb = new StringBuilder();
		for(String situacao : situacoes){
			sb.append(situacao).append(" - ");
		}
		parameters.put("FILTRO_SITUACOES", sb.toString());
		parameters.put("FILTRO_GRUPO", grupo);
		parameters.put("IMAGE_PATH", getRequest().getServletContext().getRealPath("/resources/img/")+ "\\" );
		if (dataSelecionada != null){
			parameters.put("FILTRO_DATA", new java.text.SimpleDateFormat("dd/MM/yyyy").format(dataSelecionada));
		}
		return parameters;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<String> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(List<String> situacoes) {
		this.situacoes = situacoes;
	}

	public Date getDataSelecionada() {
		return dataSelecionada;
	}

	public void setDataSelecionada(Date dataSelecionada) {
		this.dataSelecionada = dataSelecionada;
	}
}