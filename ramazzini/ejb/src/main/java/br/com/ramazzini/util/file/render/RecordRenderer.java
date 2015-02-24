package br.com.ramazzini.util.file.render;

import br.com.ramazzini.util.file.Registro;



/**
 * Interface que define como uma entidade deve gerar um registro.
 * 
 */
public interface RecordRenderer {

	/**
	 * M�todo que � utilizado para converter os valores existentes nos campos do
	 * registro, para o formato de texto.
	 * 
	 * @param record
	 *            O registro de deve ter os valores passados para texto.
	 * @return Um texto que representa os valores convertidos do registro
	 */
	String render(Registro record);

	/**
	 * Recebe uma linha inteira do arquivo, retira a parte da linha que
	 * corresponde � este campo e devolve oa render o restante, para ser
	 * processado pelos demais campos, caso existam.
	 * 
	 * @param line
	 *            Uma linha do arquivo para ser tratada.
	 */
	void read(Registro record, String line);
}
