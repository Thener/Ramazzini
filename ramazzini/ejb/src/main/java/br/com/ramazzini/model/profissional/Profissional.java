package br.com.ramazzini.model.profissional;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;
import br.com.ramazzini.model.responsavel.Responsavel;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_profissional", sequenceName = "seq_profissional", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "profissional", uniqueConstraints = @UniqueConstraint(columnNames = "nm_profissional"))
public class Profissional extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_profissional")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_profissional")
    private Long id;
    
    @Column(name = "nm_profissional", length = 100)
    @NotNull
    private String nome;
    
    @Column(name = "nm_abreviado", length = 50)
    private String nomeAbreviado;
    
	@Column(name = "sg_papel", length = 3)
    @NotNull 
    private String papelProfissional;    
    
	@Column(name = "ic_ativo")
	@NotNull
	private boolean ativo = Boolean.TRUE;
	
	@Column(name = "no_nit", length = 10)
	private String nit;
	
	@Column(name = "nm_titulacao", length = 50)
	private String titulacao;
	
	@Column(name = "no_registro", length = 10)
	private String registro;
	
	@Column(name = "no_telefone", length = 20)
	private String telefone;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cd_horario_atendimento")
	private HorarioAtendimento horarioAtendimento;	
	
	@OneToMany(mappedBy="profissional")
	private List<Responsavel> responsaveis; 	
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getTitulacao() {
		return titulacao;
	}

	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPapelProfissional() {
		return papelProfissional;
	}

	public void setPapelProfissional(String papelProfissional) {
		this.papelProfissional = papelProfissional;
	}

	public PapelProfissional getPapelProfissionalEnum() {
		return (this.papelProfissional != null) ? PapelProfissional.parse(this.papelProfissional) : null;
	}

	public void setPapelProfissionalEnum(PapelProfissional papelProfissional) {
		setPapelProfissional(papelProfissional.getValue());
	}

	public HorarioAtendimento getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}

	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}
	
	
}