package br.com.ramazzini.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.FileWriterWithEncoding;

import br.com.ramazzini.service.relatorio.FileService;


public class BasicFileWriter implements RamazziniFileWriter{
	
	private BufferedWriter writer;

	/**
	 * 
	 * @param pathName
	 * @throws IOException
	 */
	public BasicFileWriter(final String baseDir, final String relativePath, final String fileName) throws IOException {
		FileService fileService = new FileService(baseDir);
		final File file = fileService.getFile(relativePath, fileName);
		file.createNewFile();
		writer = new BufferedWriter(new FileWriterWithEncoding(file, getEncoding()));
	}
	
	/**
	 * 
	 * @param pathName
	 * @throws IOException
	 */
	public BasicFileWriter(final String fileName) throws IOException {
		final File file = new File(fileName);
		file.createNewFile();
		writer = new BufferedWriter(new FileWriterWithEncoding(file, getEncoding()));
	}
	
	public BasicFileWriter(final File file) throws IOException {
		writer = new BufferedWriter(new FileWriterWithEncoding(file, getEncoding()));
	}

	/**
	 * 
	 * @param value
	 * @throws IOException
	 */
	@Override
	public void write(String value) throws IOException {
		writer.write(value);
	}
	
	/**
	 * 
	 * @param value
	 * @throws IOException
	 */
	@Override
	public void writeln(String value) throws IOException {
		writer.write(value);
		writer.write(IOUtils.LINE_SEPARATOR_WINDOWS);
	}
	
	protected Charset getEncoding() {
		return Charset.forName("ISO-8859-1");
	}
	
	/**
	 * @throws IOException  
	 * 
	 */
	@Override
	public void close() throws IOException {
		writer.flush();
		writer.close();
	}
}
