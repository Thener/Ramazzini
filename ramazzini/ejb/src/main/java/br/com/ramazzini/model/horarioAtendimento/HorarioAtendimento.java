package br.com.ramazzini.model.horarioAtendimento;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.profissional.Profissional;
import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_horario_atendimento", sequenceName = "seq_horario_atendimento", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "horario_atendimento", uniqueConstraints = @UniqueConstraint(columnNames = "nm_horario_atendimento"))
public class HorarioAtendimento extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_horario_atendimento")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_horario_atendimento")
    private Long id;
    
    @Column(name = "nm_horario_atendimento", length = 100)
    @NotNull
    private String nome;

	@OneToMany(mappedBy="horarioAtendimento")
	private List<ProgramacaoHorarioAtendimento> programacoes;
	
	@OneToMany(mappedBy="horarioAtendimento")
	private List<Profissional> profissionais;	
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<ProgramacaoHorarioAtendimento> getProgramacoes() {
		return programacoes;
	}

	public void setProgramacoes(List<ProgramacaoHorarioAtendimento> programacoes) {
		this.programacoes = programacoes;
	}

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}
    
	
}