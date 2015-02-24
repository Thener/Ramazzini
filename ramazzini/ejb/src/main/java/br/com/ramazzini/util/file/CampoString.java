package br.com.ramazzini.util.file;

import org.apache.commons.lang3.ObjectUtils;

/**
 * Entidade referente a representacao de um campo String no relatorio.
 */
public class CampoString extends Campo<String> {

	private static final char SPACE = ' ';

	private char paddingChar;

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 */
	public CampoString(String name, int length) {
		this(name, length, "");
	}

	/**
	 * Construtor padr�o.
	 * 
	 * @param name
	 *            Nome do campo.
	 * @param length
	 *            Total de digitos, que devem ser utilizados como tamanho do
	 *            campo.
	 * @param align
	 *            Alinhamento do valor do campo, seu valor pode ser
	 *            {@link Align#LEFT} ou {@link Align#RIGHT}.
	 */
	public CampoString(String name, int length, Align align) {
		this(name, length, align, SPACE);
	}

	/**
	 * Construtor padr�o.
	 * 
	 * @param name
	 *            Nome do campo.
	 * @param length
	 *            Total de digitos, que devem ser utilizados como tamanho do
	 *            campo.
	 * @param align
	 *            Alinhamento do valor do campo, seu valor pode ser
	 *            {@link Align#LEFT} ou {@link Align#RIGHT}.
	 * @param paddingChar
	 */
	public CampoString(String name, int length, Align align, char paddingChar) {
		this(name, length, align, paddingChar, "");
	}

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param align
	 * @param paddingChar
	 * @param value
	 */
	public CampoString(String name, int length, Align align, char paddingChar, String value) {
		setName(name);
		setLength(length);
		setAlign(align);
		this.paddingChar = paddingChar;
		setValue((String) ObjectUtils.defaultIfNull(value, ""));
	}

	/**
	 * Construtor.
	 * 
	 * @param name
	 * @param length
	 * @param value
	 */
	public CampoString(String name, int length, String value) {
		this(name, length, Align.LEFT, SPACE, value);
	}

	@Override
	protected String doRender() {
		return getAlign().apply(getValue(), getLength(), paddingChar);
	}

	@Override
	public void set(String value) {
		setValue(getAlign().remove(value, paddingChar));
	}
	
}
