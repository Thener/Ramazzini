package br.com.ramazzini.util.file;

import java.io.IOException;

public interface RamazziniFileWriter {
	
	/**
	 * escreve um conteudo no arquivo e pula uma linha.
	 * @param value
	 * @throws IOException
	 */
	void writeln(String value) throws IOException;

	/**
	 * escreve um conteudo no arquivo.
	 * @param value
	 * @throws IOException
	 */
	void write(String value) throws IOException;

	/**
	 * fecha o recurso.
	 * @throws IOException
	 */
	void close() throws IOException;

}
