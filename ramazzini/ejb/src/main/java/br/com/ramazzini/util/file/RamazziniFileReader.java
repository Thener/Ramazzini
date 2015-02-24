package br.com.ramazzini.util.file;

import javax.resource.cci.Record;


public interface RamazziniFileReader {
	/**
     * Le uma linha do arquivo.
     * @return a linha como um {@link Record} ou <tt>null</tt> se não houver mais nenhuma linha
     * @throws Throwable
     */
    Registro read() throws Throwable;

    /**
     * Fecha o leitor de arquivos.
     */
    void close();
}
