package br.com.ramazzini.model.anamnese;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.avaliacaoClinica.AvaliacaoClinica;
import br.com.ramazzini.model.avaliacaoClinica.SituacaoAvaliacaoClinica;
import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.util.AbstractEntidade;
import br.com.ramazzini.util.TimeFactory;

@SequenceGenerator(name = "seq_anamnese", sequenceName = "seq_anamnese", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "anamnese")
public class Anamnese extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_anamnese")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_anamnese")
    private Long id;
    
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_avaliacao_clinica")
	private AvaliacaoClinica avaliacaoClinica;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="cd_profissional")
	private Profissional medico;	
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_realizacao", columnDefinition = "Date")
	@NotNull
	private Date dataRealizacao = TimeFactory.createDataHora();	
	
	@Column(name = "ds_ultimo_trabalho", length = 100)
	private String ultimoTrabalho;	
	
	@Column(name = "ds_ultimo_cargo", length = 100)
	private String ultimoCargo;	
	
	@Column(name = "ic_ultimo_cargo_insalubre")
	private boolean ultimoCargoInsalubre = Boolean.FALSE;
	
	@Column(name = "ds_ultimo_cargo_tempo", length = 20)
	private String ultimoCargoTempo;

	@Column(name = "ic_antecedente_insalubre")
	private boolean antecedenteInsalubre = Boolean.FALSE;	
	
	@Column(name = "ds_antecedente_insalubre", length = 100)
	private String descricaoAntecedenteInsalubre;
	
	@Column(name = "ic_antecedente_morbido")
	private boolean antecedenteMorbido = Boolean.FALSE;	
	
	@Column(name = "ds_antecedente_morbido", length = 100)
	private String descricaoAntecedenteMorbido;
	
	@Column(name = "ic_acidente_trabalho")
	private boolean acidenteTrabalho = Boolean.FALSE;	
	
	@Column(name = "ds_acidente_trabalho", length = 100)
	private String descricaoAcidenteTrabalho;
	
	@Column(name = "ic_doenca_ocupacional")
	private boolean doencaOcupacional = Boolean.FALSE;	
	
	@Column(name = "ds_doenca_ocupacional", length = 100)
	private String descricaoDoencaOcupacional;
	
	@Column(name = "ic_queixa_principal")
	private boolean queixaPrincipal = Boolean.FALSE;	
	
	@Column(name = "ds_queixa_principal", length = 100)
	private String descricaoQueixaPrincipal;
	
	@Column(name = "ic_deficiente_fisico")
	private boolean deficienteFisico = Boolean.FALSE;	
	
	@Column(name = "ds_deficiencia_fisica", length = 100)
	private String descricaoDeficienciaFisica;	
	
	@Column(name = "ic_alimentacao_boa")
	private boolean alimentacaoBoa = Boolean.TRUE;	

	@Column(name = "ic_aspecto_geral_bom")
	private boolean aspectoGeralBom = Boolean.TRUE;
	
	@Column(name = "tp_habito", length = 2)
	private String tipoHabito;	
	
	@Column(name = "ic_dort")
	private boolean dort = Boolean.FALSE;
	
	@Column(name = "ic_lombalgia")
	private boolean lombalgia = Boolean.FALSE;
	
	@Column(name = "ic_afastado_doenca")
	private boolean afastadoDoenca = Boolean.FALSE;
	
	@Column(name = "ic_alergia")
	private boolean alergia = Boolean.FALSE;
	
	@Column(name = "ic_dermatite")
	private boolean dermatite = Boolean.FALSE;
	
	@Column(name = "ic_varizes")
	private boolean varizes = Boolean.FALSE;
	
	@Column(name = "ic_edemas")
	private boolean edemas = Boolean.FALSE;
	
	@Column(name = "ic_tonturas")
	private boolean tonturas = Boolean.FALSE;
	
	@Column(name = "ic_uso_medicamentos")
	private boolean usoMedicamentos = Boolean.FALSE;
	
	@Column(name = "ic_adaptado_ferramenta_trabalho")
	private boolean adaptadoFerramentaTrabalho = Boolean.TRUE;
	
	@Column(name = "ic_adaptado_local_trabalho")
	private boolean adaptadoLocalTrabalho = Boolean.TRUE;
	
	@Column(name = "ic_cirurgia")
	private boolean cirurgia = Boolean.FALSE;
	
	@Column(name = "ic_hernia")
	private boolean hernia = Boolean.FALSE;	
	
	@Column(name = "st_ar", length = 3)
	private String situacaoAR;
	
	@Column(name = "st_acv", length = 3)
	private String situacaoACV;	
	
	@Column(name = "ds_pulso", length = 3)
	private String pulso;
	
	@Column(name = "ds_pressao_arterial", length = 10)
	private String pressaoArterial;	

	@Column(name = "ds_temperatura", length = 5)
	private String temperatura;	
	
	@Column(name = "ic_temperatura_normal")
	private boolean temperaturaNormal = Boolean.TRUE;	
	
    @Column(name = "te_parecer", columnDefinition="TEXT")
    private String parecer;
    
    @Column(name = "te_restricoes_aptidao", columnDefinition="TEXT")
    private String restricoesAptidao;
    
    @Column(name = "te_motivo_inaptidao", columnDefinition="TEXT")
    private String motivoInaptidao;    
    
	@Column(name = "st_avaliacao_clinica", length = 3)
    @NotNull 
    private String situacaoAvaliacaoClinica;  
	
	@Column(name = "ic_alerta_proximo_atendimento")
	private boolean alertaProximoAtendimento = Boolean.FALSE;	
	
	public Long getId() {
		return id;
	}	
	
	public AvaliacaoClinica getAvaliacaoClinica() {
		return avaliacaoClinica;
	}

	public void setAvaliacaoClinica(AvaliacaoClinica avaliacaoClinica) {
		this.avaliacaoClinica = avaliacaoClinica;
	}

	public Profissional getMedico() {
		return medico;
	}

	public void setMedico(Profissional medico) {
		this.medico = medico;
	}

	public String getUltimoTrabalho() {
		return ultimoTrabalho;
	}

	public void setUltimoTrabalho(String ultimoTrabalho) {
		this.ultimoTrabalho = ultimoTrabalho;
	}

	public String getUltimoCargo() {
		return ultimoCargo;
	}

	public void setUltimoCargo(String ultimoCargo) {
		this.ultimoCargo = ultimoCargo;
	}

	public boolean isUltimoCargoInsalubre() {
		return ultimoCargoInsalubre;
	}

	public void setUltimoCargoInsalubre(boolean ultimoCargoInsalubre) {
		this.ultimoCargoInsalubre = ultimoCargoInsalubre;
	}

	public String getUltimoCargoTempo() {
		return ultimoCargoTempo;
	}

	public void setUltimoCargoTempo(String ultimoCargoTempo) {
		this.ultimoCargoTempo = ultimoCargoTempo;
	}

	public boolean isAntecedenteInsalubre() {
		return antecedenteInsalubre;
	}

	public void setAntecedenteInsalubre(boolean antecedenteInsalubre) {
		this.antecedenteInsalubre = antecedenteInsalubre;
	}

	public String getDescricaoAntecedenteInsalubre() {
		return descricaoAntecedenteInsalubre;
	}

	public void setDescricaoAntecedenteInsalubre(
			String descricaoAntecedenteInsalubre) {
		this.descricaoAntecedenteInsalubre = descricaoAntecedenteInsalubre;
	}

	public boolean isAntecedenteMorbido() {
		return antecedenteMorbido;
	}

	public void setAntecedenteMorbido(boolean antecedenteMorbido) {
		this.antecedenteMorbido = antecedenteMorbido;
	}

	public String getDescricaoAntecedenteMorbido() {
		return descricaoAntecedenteMorbido;
	}

	public void setDescricaoAntecedenteMorbido(String descricaoAntecedenteMorbido) {
		this.descricaoAntecedenteMorbido = descricaoAntecedenteMorbido;
	}

	public boolean isAcidenteTrabalho() {
		return acidenteTrabalho;
	}

	public void setAcidenteTrabalho(boolean acidenteTrabalho) {
		this.acidenteTrabalho = acidenteTrabalho;
	}

	public String getDescricaoAcidenteTrabalho() {
		return descricaoAcidenteTrabalho;
	}

	public void setDescricaoAcidenteTrabalho(String descricaoAcidenteTrabalho) {
		this.descricaoAcidenteTrabalho = descricaoAcidenteTrabalho;
	}

	public boolean isDoencaOcupacional() {
		return doencaOcupacional;
	}

	public void setDoencaOcupacional(boolean doencaOcupacional) {
		this.doencaOcupacional = doencaOcupacional;
	}

	public String getDescricaoDoencaOcupacional() {
		return descricaoDoencaOcupacional;
	}

	public void setDescricaoDoencaOcupacional(String descricaoDoencaOcupacional) {
		this.descricaoDoencaOcupacional = descricaoDoencaOcupacional;
	}

	public boolean isDeficienteFisico() {
		return deficienteFisico;
	}

	public void setDeficienteFisico(boolean deficienteFisico) {
		this.deficienteFisico = deficienteFisico;
	}

	public String getDescricaoDeficienciaFisica() {
		return descricaoDeficienciaFisica;
	}

	public void setDescricaoDeficienciaFisica(String descricaoDeficienciaFisica) {
		this.descricaoDeficienciaFisica = descricaoDeficienciaFisica;
	}

	public boolean isAlimentacaoBoa() {
		return alimentacaoBoa;
	}

	public void setAlimentacaoBoa(boolean alimentacaoBoa) {
		this.alimentacaoBoa = alimentacaoBoa;
	}

	public boolean isAspectoGeralBom() {
		return aspectoGeralBom;
	}

	public void setAspectoGeralBom(boolean aspectoGeralBom) {
		this.aspectoGeralBom = aspectoGeralBom;
	}

	public String getTipoHabito() {
		return tipoHabito;
	}

	public void setTipoHabito(String tipoHabito) {
		this.tipoHabito = tipoHabito;
	}

	public TipoHabito getTipoHabitoEnum() {
		return (this.tipoHabito != null) ? TipoHabito.parse(this.tipoHabito) : null;
	}

	public void setTipoHabitoEnum(TipoHabito tipoHabito) {
		setTipoHabito(tipoHabito.getValue());
	}

	public boolean isDort() {
		return dort;
	}

	public void setDort(boolean dort) {
		this.dort = dort;
	}

	public boolean isLombalgia() {
		return lombalgia;
	}

	public void setLombalgia(boolean lombalgia) {
		this.lombalgia = lombalgia;
	}

	public boolean isAfastadoDoenca() {
		return afastadoDoenca;
	}

	public void setAfastadoDoenca(boolean afastadoDoenca) {
		this.afastadoDoenca = afastadoDoenca;
	}

	public boolean isAlergia() {
		return alergia;
	}

	public void setAlergia(boolean alergia) {
		this.alergia = alergia;
	}

	public boolean isDermatite() {
		return dermatite;
	}

	public void setDermatite(boolean dermatite) {
		this.dermatite = dermatite;
	}

	public boolean isVarizes() {
		return varizes;
	}

	public void setVarizes(boolean varizes) {
		this.varizes = varizes;
	}

	public boolean isEdemas() {
		return edemas;
	}

	public void setEdemas(boolean edemas) {
		this.edemas = edemas;
	}

	public boolean isTonturas() {
		return tonturas;
	}

	public void setTonturas(boolean tonturas) {
		this.tonturas = tonturas;
	}

	public boolean isUsoMedicamentos() {
		return usoMedicamentos;
	}

	public void setUsoMedicamentos(boolean usoMedicamentos) {
		this.usoMedicamentos = usoMedicamentos;
	}

	public boolean isAdaptadoFerramentaTrabalho() {
		return adaptadoFerramentaTrabalho;
	}

	public void setAdaptadoFerramentaTrabalho(boolean adaptadoFerramentaTrabalho) {
		this.adaptadoFerramentaTrabalho = adaptadoFerramentaTrabalho;
	}

	public boolean isAdaptadoLocalTrabalho() {
		return adaptadoLocalTrabalho;
	}

	public void setAdaptadoLocalTrabalho(boolean adaptadoLocalTrabalho) {
		this.adaptadoLocalTrabalho = adaptadoLocalTrabalho;
	}

	public boolean isCirurgia() {
		return cirurgia;
	}

	public void setCirurgia(boolean cirurgia) {
		this.cirurgia = cirurgia;
	}

	public boolean isHernia() {
		return hernia;
	}

	public void setHernia(boolean hernia) {
		this.hernia = hernia;
	}

	public String getSituacaoAR() {
		return situacaoAR;
	}

	public void setSituacaoAR(String situacaoAR) {
		this.situacaoAR = situacaoAR;
	}
	
	public SituacaoAR getSituacaoAREnum() {
		return (this.situacaoAR != null) ? SituacaoAR.parse(this.situacaoAR) : null;
	}

	public void setSituacaoAREnum(SituacaoAR situacaoAR) {
		setSituacaoAR(situacaoAR.getValue());
	}	

	public String getSituacaoACV() {
		return situacaoACV;
	}

	public void setSituacaoACV(String situacaoACV) {
		this.situacaoACV = situacaoACV;
	}	
	
	public SituacaoACV getSituacaoACVEnum() {
		return (this.situacaoACV != null) ? SituacaoACV.parse(this.situacaoACV) : null;
	}

	public void setSituacaoACVEnum(SituacaoACV situacaoACV) {
		setSituacaoACV(situacaoACV.getValue());
	}

	public String getPulso() {
		return pulso;
	}

	public void setPulso(String pulso) {
		this.pulso = pulso;
	}

	public String getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(String pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public String getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(String temperatura) {
		this.temperatura = temperatura;
	}

	public boolean isTemperaturaNormal() {
		return temperaturaNormal;
	}

	public void setTemperaturaNormal(boolean temperaturaNormal) {
		this.temperaturaNormal = temperaturaNormal;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public String getRestricoesAptidao() {
		return restricoesAptidao;
	}

	public void setRestricoesAptidao(String restricoesAptidao) {
		this.restricoesAptidao = restricoesAptidao;
	}

	public String getSituacaoAvaliacaoClinica() {
		return situacaoAvaliacaoClinica;
	}

	public void setSituacaoAvaliacaoClinica(String situacaoAvaliacaoClinica) {
		this.situacaoAvaliacaoClinica = situacaoAvaliacaoClinica;
	}	
	
	public SituacaoAvaliacaoClinica getSituacaoAvaliacaoClinicaEnum() {
		return (this.situacaoAvaliacaoClinica != null) ? SituacaoAvaliacaoClinica.parse(this.situacaoAvaliacaoClinica) : null;
	}

	public void setSituacaoAvaliacaoClinicaEnum(SituacaoAvaliacaoClinica situacaoAvaliacaoClinica) {
		setSituacaoAvaliacaoClinica(situacaoAvaliacaoClinica.getValue());
	}

	public String getMotivoInaptidao() {
		return motivoInaptidao;
	}

	public void setMotivoInaptidao(String motivoInaptidao) {
		this.motivoInaptidao = motivoInaptidao;
	}

	public boolean isAlertaProximoAtendimento() {
		return alertaProximoAtendimento;
	}

	public void setAlertaProximoAtendimento(boolean alertaProximoAtendimento) {
		this.alertaProximoAtendimento = alertaProximoAtendimento;
	}

	public boolean isQueixaPrincipal() {
		return queixaPrincipal;
	}

	public void setQueixaPrincipal(boolean queixaPrincipal) {
		this.queixaPrincipal = queixaPrincipal;
	}

	public String getDescricaoQueixaPrincipal() {
		return descricaoQueixaPrincipal;
	}

	public void setDescricaoQueixaPrincipal(String descricaoQueixaPrincipal) {
		this.descricaoQueixaPrincipal = descricaoQueixaPrincipal;
	}	
	
	
}