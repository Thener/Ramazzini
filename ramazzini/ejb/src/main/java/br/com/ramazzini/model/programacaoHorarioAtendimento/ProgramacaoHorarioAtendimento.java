package br.com.ramazzini.model.programacaoHorarioAtendimento;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.ramazzini.model.horarioAtendimento.DiaSemana;
import br.com.ramazzini.model.horarioAtendimento.HorarioAtendimento;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hr_inicio", columnDefinition = "timestamp without time zone")
	@NotNull
	private Date horaInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hr_fim", columnDefinition = "timestamp without time zone")
	@NotNull	
	private Date horaFim;

	@Column(name = "no_intervalo")
    @NotNull 	
	private Integer intervalo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@NotNull
	@JoinColumn(name="cd_horario_atendimento", nullable=false)
	private HorarioAtendimento horarioAtendimento;	
	
	public Long getId() {
		return id;
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

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
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