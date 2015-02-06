package br.com.ramazzini.controller.util;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.inject.Named;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *  
 */
@Named("exportarPdfBean")
public class ExportarPdfController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File relatorio;	
	private HashMap<String, Object> parametros;
	private JRBeanCollectionDataSource jrBeanCollectionDataSource;
	private String nome;
	
	/**
	 * 
	 * @param parametros
	 * @param jrBeanCollectionDataSource
	 * @param nome
	 * @param caminhoRelatorio
	 * @throws MalformedURLException
	 */
	public ExportarPdfController(
			HashMap<String, Object> parametros,
			JRBeanCollectionDataSource jrBeanCollectionDataSource,
			String nome, File relatorio) throws MalformedURLException {
		this.relatorio = relatorio ;
		this.parametros = parametros;
		this.jrBeanCollectionDataSource = jrBeanCollectionDataSource;
		this.nome = nome;
	}
			
	/**
	 * Realiza o download de um pdf recebendo um datasource e uma lista de parametros.
	 * @throws Exception
	 */
	public void download() throws Exception {
		JasperReport report = (JasperReport) JRLoader.loadObject(relatorio);
		JasperPrint jasperPrint = JasperFillManager
				.fillReport(report, parametros, jrBeanCollectionDataSource);
		DownloadUtil.download(
				JasperExportManager.exportReportToPdf(jasperPrint),
				nome,
				MimeType.APPLICATION_PDF_PDF);
	}	
}
