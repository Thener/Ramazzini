package br.com.ramazzini.util.file;

import java.util.Date;

public abstract class Campo<T> {
	
	private int tamanho;

	private String name;

	private T valor;
	
	private Align align;
	
	private String cached;
	
	private boolean useCache = true;

	protected Campo() {

	}
	
	protected Campo(boolean useCache) {
		this.useCache = useCache;
	}

	public int getLength() {
		return tamanho;
	}

	public String getName() {
		return name;
	}

	/**
	 * Get String Value.
	 * 
	 * @return String
	 */
	public String getStringValue() {
		if (getValue() != null) {
			return String.valueOf(getValue());
		}

		return "";
	}

	public T getValue() {
		return valor;
	}

	/**
	 * Render.
	 * 
	 * @return String
	 */
	public final String render() {
		if (!useCache) {
			return doRender();
		}

		if (cached != null) {
			return cached;
		}

		cached = doRender();
		return cached;
	}
	
	protected abstract String doRender();

	/**
	 * Faz o rerender do campo.
	 *
	 * @return
	 */
	public String rerender() {
		cached = null;

		return render();
	}

	/**
	 * Altera o valor.
	 * @param value
	 */
	public void setValue(T value) {
		cached = null;

		this.valor = value;
	}
	
	void setLength(int length) {
		this.tamanho = length;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Align getAlign() {
		return align;
	}

	public void setAlign(Align align) {
		this.align = align;
	}
	
	/**
	 * Set.
	 * 
	 * @param value
	 */
	public abstract void set(String value);

	/**
	 * fabrica para Campos. Esse metodo não constroe Campo para data
	 * 
	 * @param <E>
	 * @param value
	 * @param tamanho
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> Campo<E> get(int lengthOrDatePatternCode, E value) {

		if (value instanceof Integer) {
			return (Campo<E>) new CampoInteger("", lengthOrDatePatternCode, (Integer) value);
		}

		if (value instanceof Long) {
			return (Campo<E>) new CampoLong("", lengthOrDatePatternCode, (Long) value);
		}

		if (value instanceof Date) {
			if (lengthOrDatePatternCode >= CampoDate.DD_MM_YYYY_PATTERN) {
				return (Campo<E>) new CampoDate("", "ddMMyyyy", (Date) value);
			} else if (lengthOrDatePatternCode == CampoDate.YYYY_MM_DD_PATTERN) {
				return (Campo<E>) new CampoDate("", "yyyyMMdd", (Date) value);
			}
		}

		return (Campo<E>) new CampoString("", lengthOrDatePatternCode, (String) value); // padrão
	}
}
