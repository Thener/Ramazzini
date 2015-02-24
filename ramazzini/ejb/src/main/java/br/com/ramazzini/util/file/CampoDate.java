package br.com.ramazzini.util.file;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import br.com.ramazzini.util.TimeFactory;

/**
 * 
 * Entidade referente a representacao de um campo de Data no relatorio.
 * 
 */
public class CampoDate extends Campo<Date> {

	public static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";

	public static final Integer DD_MM_YYYY_PATTERN = 0;

	public static final Integer YYYY_MM_DD_PATTERN = -1;

	private String pattern;

	private EmptyValue emptyValue;

	/**
	 * 
	 * @param name
	 */
	public CampoDate(String name) {
		this(name, DEFAULT_DATE_PATTERN);
	}

	/**
	 * Define o valor padrão que deve ser utilizado, caso o campo não tenha valor.
	 * @param name
	 * @param emptyValue
	 */
	public CampoDate(String name, EmptyValue emptyValue) {
		this(name, DEFAULT_DATE_PATTERN);

		this.emptyValue = emptyValue;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public CampoDate(String name, Date value) {
		this(name, DEFAULT_DATE_PATTERN, value);
	}

	/**
	 * Formato de data para o campo utilizar no valor.
	 * @param name
	 * @param pattern
	 */
	
	public CampoDate(String name, String pattern) {
		this(name, pattern, TimeFactory.createDataHora());
	}

	/**
	 * 
	 * @param name
	 * @param pattern
	 * @param emptyValue
	 */
	public CampoDate(String name, String pattern, EmptyValue emptyValue) {
		this(name, pattern, TimeFactory.createDataHora());

		this.emptyValue = emptyValue;
	}

	/**
	 * 
	 * @param name
	 * @param pattern
	 * @param value
	 */
	public CampoDate(String name, String pattern, Date value) {
		super.setName(name);
		this.pattern = pattern;
		if (value == null) {
			setValue(TimeFactory.createDataHora());
		} else {
			setValue(value);
		}
	}

	@Override
	public String getStringValue() {
		SimpleDateFormat format = null;

		if (pattern != null) {
			if (getValue() != null) {
				format = new SimpleDateFormat(pattern);
				return format.format(getValue());
			} else if (emptyValue != null) {
				// Tratando o campo data sem valor definido:
				if (emptyValue.equals(EmptyValue.EMPTY)) {
					return StringUtils.leftPad("", pattern.length(), "");
				} else if (emptyValue.equals(EmptyValue.ZERO)) {
					return StringUtils.leftPad("", pattern.length(), "0");
				}
			}

			return StringUtils.leftPad("", pattern.length());
		}
		return super.getStringValue();
	}

	@Override
	protected String doRender() {
		return getStringValue();
	}

	@Override
	public int getLength() {
		return pattern.length();
	}
	
	@Override
	public void set(String value) {
		try {
			if (StringUtils.isBlank(value)) {
				setValue(null);
			} else {
				setValue(new SimpleDateFormat(pattern).parse(value));
			}
		} catch (ParseException e) {
			System.out.println("parsing.error"+e);
		}
	}

}
