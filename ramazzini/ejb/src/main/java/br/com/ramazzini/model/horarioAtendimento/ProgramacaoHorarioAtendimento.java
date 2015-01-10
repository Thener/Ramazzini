package br.com.ramazzini.model.horarioAtendimento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.util.AbstractEntidade;

@SequenceGenerator(name = "seq_prog_horario_atendimento", sequenceName = "seq_prog_horario_atendimento", allocationSize = 1)
@Entity
@XmlRootElement
@Table(name = "programacao_horario_atendimento")
public class ProgramacaoHorarioAtendimento extends AbstractEntidade implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_programacao_horario_atendimento")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_prog_horario_atendimento")
    private Long id;
    
	@Column(name = "sg_dia_semana", length = 3)
    @NotNull 
    private String diaSemana;
	
	@Column(name = "hr_inicio", length = 5)
    @NotNull 	
	private String horaInicio;
	
	@Column(name = "hr_fim", length = 5)
    @NotNull 	
	private String horaFim;

	@Column(name = "no_intervalo")
    @NotNull 	
	private Integer intervalo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cd_horario_atendimento", nullable=false)
	private HorarioAtendimento horarioAtendimento;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public DiaSemana getDiaSemanaEnum() {
		return (this.diaSemana != null) ? DiaSemana.parse(this.diaSemana) : null;
	}

	public void setDiaSemanaEnum(DiaSemana diaSemana) {
		setDiaSemana(diaSemana.getValue());
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public HorarioAtendimento getHorarioAtendimento() {
		return horarioAtendimento;
	}

	public void setHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
		this.horarioAtendimento = horarioAtendimento;
	}	
    
	
}