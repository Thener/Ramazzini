package br.com.ramazzini.util.file;


import org.apache.commons.lang3.StringUtils;

/**
 * Classe responsável por alinhar o conteudo.
 * 
 */
public enum Align {

	/**
	 * Pad or chop data to the left, so the text is aligned to the right.
	 */
	RIGHT {
		@Override
		public String apply(String data, int length, char paddingChar) {
			String result;
			if (data == null) {
				data = "";
			}
			int dataLength = data.length();
			if (dataLength > length) {
				result = StringUtils.substring(data, dataLength - length, dataLength);
			} else {
				result = StringUtils.leftPad(data, length, paddingChar);
			}
			return result;
		}

		@Override
		public String remove(String data, char paddingChar) {
			String result = data;
			if (data == null) {
				result = "";
			}
			while (result.startsWith("" + paddingChar)) {
				result = result.substring(1, result.length());
			}
			return result;
		}
	},

	/**
	 * Pad or chop data to the right, so the text is aligned to the left.
	 */
	LEFT {
		@Override
		public String apply(String data, int length, char paddingChar) {
			String result;
			if (data == null) {
				data = "";
			}
			int dataLength = data.length();
			if (dataLength > length) {
				result = StringUtils.substring(data, 0, length);
			} else {
				result = StringUtils.rightPad(data, length, paddingChar);
			}
			return result;
		}

		@Override
		public String remove(String data, char paddingChar) {
			String result = data;
			if (data == null) {
				result = "";
			}
			while (result.endsWith("" + paddingChar)) {
				result = result.substring(0, result.length() - 1);
			}
			return result;
		}
	};

	/**
	 * Pads the data in the length specified with the given padding char. No
	 * padding will be applied if the length of the data is longer than the
	 * given length.
	 * 
	 * @param data
	 *            the data to pad.
	 * @param length
	 *            the minimum length after the padding is applied.
	 * @param paddingChar
	 *            the char the data is padded with.
	 * @return the data after padding is applied.
	 */
	public abstract String apply(String data, int length, char paddingChar);

	/**
	 * Remove the padding chars from the data.
	 * 
	 * @param data
	 *            the data including padding chars
	 * @param paddingChar
	 *            the padding char to remove
	 * @return the data after padding is removed.
	 */
	public abstract String remove(String data, char paddingChar);
}
