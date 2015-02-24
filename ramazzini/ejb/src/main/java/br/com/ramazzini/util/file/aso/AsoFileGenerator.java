package br.com.ramazzini.util.file.aso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import br.com.ramazzini.model.funcionario.Funcionario;
import br.com.ramazzini.service.relatorio.FileService;

public class AsoFileGenerator {
	private File aso;
	private Funcionario funcionario;
	private PrintWriter writer;
	
	public AsoFileGenerator(Funcionario funcionario) throws IOException {
		super();
		createFile(funcionario);
        setWriter();
	}

	private String getNomeArquivo(Funcionario funcionario) {
		return funcionario.getNome()+".txt";
	}
	
	public void gerar(){
		
		writer.println(funcionario.getCtps());
        writer.println(funcionario.getNome());
       
        
        close();
	}
	
	private void createFile(Funcionario funcionario) throws IOException {
		this.funcionario = funcionario;
		FileService fileService = new FileService("C:/RamazziniArquivos/ASO");
		aso = fileService.getFile(getNomeArquivo(funcionario));
	}
	
	private void setWriter() throws IOException {
		FileWriter fileWriter = new FileWriter(aso, false);
		writer = new PrintWriter(fileWriter);
	}

	private void close() {
		writer.flush();
		writer.close();
	}
	
}
