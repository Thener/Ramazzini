package br.com.ramazzini.util.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import br.com.ramazzini.util.file.render.FixedWidthRecordRenderer;
import br.com.ramazzini.util.file.render.RecordRenderer;

/**
 * Entidade representativa de um registro para relatorio.
 */
@SuppressWarnings("unchecked")
public class Registro implements Cloneable {

	/**
	 * Enum definindo os tipos de registros validos. 
	 */
	public enum RecordType {
		HEADER, DETAIL, TRAILER
	};

	private final RecordType type;

	private RecordRenderer renderer;

	private List<Campo<?>> CamposByIndex = new ArrayList<Campo<?>>();
	private Map<String, Campo<?>> CamposByName = new HashMap<String, Campo<?>>();
	
	/**
	 * Identifica��o do registro.
	 */
	private String id;

	private int length;
	
	/**
	 * Construtor padr�o.
	 * 
	 * @param type
	 * @param renderer
	 */
	public Registro(final RecordType type, final RecordRenderer renderer) {
		Validate.notNull(type, "'type' must not be null");
		Validate.notNull(renderer, "'renderer' must not be null");
		this.type = type;
		this.renderer = renderer;
	}
	
	/**
	 * Cria um novo registro com o tipo especificado.
	 * @param type tipo do registro (detail, header ou trailer)
	 */
	public Registro(final RecordType type) {
		this(type, new FixedWidthRecordRenderer());
	}
	
	/**
	 * Constroi um novo registro do tipo <tt>type</tt>, com a lista de campos <tt>Campos</tt>. 
	 * @param Campos lista de campos para o registro
	 * @param type tipo do registro (detail, header ou trailer)
	 */
	public Registro(final List<Campo<?>> Campos, final RecordType type) {
		this(type);
		this.addCampo(Campos);
	}
	
	private void addCampo(final List<Campo<?>> Campos) {
		for (Campo<?> Campo : Campos) {
			this.addCampo(Campo);
		}
	}
	
	/**
	 * Adiciona um campo neste record.
	 * 
	 * @param Campo
	 *            Campo para o record.
	 */
	public void addCampo(final Campo Campo) {
		Validate.notNull(Campo, "'Campo' must not be null");
		CamposByIndex.add(Campo);
		if (Campo.getName() != null) {
			addCampoByName(Campo);
		}
		length += Campo.getLength();
	}

	private void addCampoByName(final Campo Campo) {
		/*
		 * 23/06 Davy Diegues Duran
		if (CamposByName.get(Campo.getName()) != null) {
			throw new IllegalStateException(String
					.format("A Campo with the name '%s' already exists.", Campo.getName()));
		}
		*/
		CamposByName.put(Campo.getName(), Campo);
	}

	/**
	 * Retorna um capo deste record, identificado por sua ordem de cria��o.
	 * 
	 * @param CampoIndex
	 *            Indice do campo.
	 * @return O campo especificado.
	 */
	public Campo getCampo(final int CampoIndex) {
		return CamposByIndex.get(CampoIndex);
	}

	/**
	 * Retorna um capo deste record, identificado por um nome definido.
	 * 
	 * @param CampoName
	 *            O nome definido para o campo.
	 * @return O campo especificado.
	 */
	public Campo getCampo(final String CampoName) {
		return CamposByName.get(CampoName);
	}

	public List<Campo<?>> getCampos() {
		return Collections.unmodifiableList(CamposByIndex);
	}

	public RecordType getType() {
		return type;
	}

	public boolean isDetail() {
		return RecordType.DETAIL.equals(type);
	}

	public boolean isHeader() {
		return RecordType.HEADER.equals(type);
	}

	public boolean isTrailler() {
		return RecordType.TRAILER.equals(type);
	}

	/**
	 * M�todo que monta a linha de texto que ser� gravada no arquivo � partir
	 * deste objeto.
	 * 
	 * @return
	 */
	public String render() {
		return renderer.render(this);
	}

	/**
	 * Define o valor de um campo deste record.
	 * 
	 * @param <T>
	 *            Tipo do campo.
	 * @param CampoName
	 *            Nome do campo
	 * @param value
	 *            Valor que deve ser armazenado.
	 */
	public <T> void setCampoValue(final String CampoName, final T value) {
		Campo<T> Campo = getCampo(CampoName);
		if (Campo != null) {
			Campo.setValue(value);
		}
	}

	/**
	 * Define um ({@link RecordRenderer} para este record.
	 * 
	 * @param renderer
	 */
	public void setRenderer(final RecordRenderer renderer) {
		Validate.notNull(renderer, "'renderer' must not be null");
		this.renderer = renderer;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}
	
    public int getLength() {
        return length;
    }
    
    public int getNumberOfCampos() {
    	return CamposByIndex.size();
    }
    
    
    @Override
    public String toString() {
    	String result = type.name() + " Record[";
    	for (Iterator<Campo<?>> iter = CamposByIndex.iterator(); iter.hasNext();) {
    		Campo<?> Campo = iter.next();
			result += Campo.getName() + "=" + Campo.getStringValue();
			if (iter.hasNext()) {
				result += ", ";
			}
		}
    	result += "]";
    	return result;
    }
}
