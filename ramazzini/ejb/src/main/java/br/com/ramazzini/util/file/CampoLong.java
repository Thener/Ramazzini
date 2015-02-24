package br.com.ramazzini.util.file;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Entidade referente a representacao de um campo Long.
 */
public class CampoLong extends Campo<Long> {

	private char paddingValue;

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 */
	public CampoLong(String name, int length) {
		this(name, length, Long.valueOf(0));
	}

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param value
	 */
	public CampoLong(String name, int length, Long value) {
		this(name, length, Align.RIGHT, value);
	}
	
	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param paddingValue
	 */
	public CampoLong(String name, int length, char paddingValue) {
		this(name, length, Align.RIGHT, Long.valueOf(0), paddingValue);
	}
	
	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param align
	 * @param value
	 */
	public CampoLong(String name, int length, Align align, Long value) {
		this(name, length, align, value, '0');
	}

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param align
	 * @param value
	 * @param paddingValue
	 */
	public CampoLong(String name, int length, Align align, Long value, char paddingValue) {
		setName(name);
		setLength(length);
		setValue((Long) ObjectUtils.defaultIfNull(value, Long.valueOf(0)));
		setPaddingValue(paddingValue);
	}

	@Override
	protected String doRender() {
		return getAlign().apply(getStringValue(), getLength(), getPaddingValue());
	}

	@Override
	public void set(String value) {
		setValue(Long.valueOf(value));
	}

	public char getPaddingValue() {
		return paddingValue;
	}

	public void setPaddingValue(char paddingValue) {
		this.paddingValue = paddingValue;
	}

}
