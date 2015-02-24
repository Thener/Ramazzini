package br.com.ramazzini.util.file.render;

import br.com.ramazzini.util.file.Campo;
import br.com.ramazzini.util.file.Registro;



/**
 * Entidade que cuida de como gerar um registro Fixado por tamanho.
 */
public class FixedWidthRecordRenderer implements RecordRenderer {

	@SuppressWarnings("unchecked")
	@Override
	public String render(Registro record) {
		StringBuilder buffer = new StringBuilder();
		Campo tmp = null;

		try {
			for (Campo field : record.getCampos()) {
				tmp = field;
				buffer.append(field.render());
			}
		} catch (ClassCastException e) {
			throw new ClassCastException(tmp.getName().concat(" : ".concat(e.getMessage())));
		}

		return buffer.toString();
	}

	/**
	 * Recebe uma linha inteira do arquivo, retira a parte da linha que
	 * corresponde � este campo e devolve oa render o restante, para ser
	 * processado pelos demais campos, caso existam.
	 * 
	 * @param line
	 *            Uma linha do arquivo para ser tratada.
	 *         campo.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public void read(Registro record, String line) {
		String value = null;

		for (Campo field : record.getCampos()) {
			value = line.substring(0, field.getLength());

			// field.setValue( field.valueOf( value ));
			line = line.substring(field.getLength());
		}

	}
}
