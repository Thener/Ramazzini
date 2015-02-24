package br.com.ramazzini.util.file;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;;
/**
 * Entidade referente a representacao de um campo Integer no relatorio.
 */
public class CampoInteger extends Campo<Integer> {

	private char paddingValue;

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 */
	public CampoInteger(String name, int length) {
		this(name, length, Integer.valueOf(0));
	}

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param align
	 * @param value
	 */
	public CampoInteger(String name, int length, Align align, Integer value) {
		setName(name);
		setLength(length);
		setAlign(align);
		setValue((Integer) defaultIfNull(value, Integer.valueOf(0)));
		setPaddingValue('0');
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
	public CampoInteger(String name, int length, Align align, Integer value, char paddingValue) {
		this(name, length, align, value);
		setPaddingValue(paddingValue);
	}

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param paddingValue
	 */
	public CampoInteger(String name, int length, char paddingValue) {
		this(name, length, Align.RIGHT, Integer.valueOf(0), paddingValue);
	}

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param value
	 */
	public CampoInteger(String name, int length, Integer value) {
		this(name, length, Align.RIGHT, value);
	}

	@Override
	protected String doRender() {
		return getAlign().apply(getStringValue(), getLength(), getPaddingValue());
	}

	@Override
	public void set(String value) {
		if (value == null) {
			setValue(Integer.valueOf(0));
		} else {
			setValue(Integer.valueOf(value));
		}
	}

	public char getPaddingValue() {
		return paddingValue;
	}

	public void setPaddingValue(char paddingValue) {
		this.paddingValue = paddingValue;
	}
}
