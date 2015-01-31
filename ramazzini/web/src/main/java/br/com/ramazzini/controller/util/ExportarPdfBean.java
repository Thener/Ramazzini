package br.com.ramazzini.controller.util;

import java.io.File;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import br.com.ramazzini.model.parametro.ParametroSistema;
import br.com.ramazzini.service.entidade.ParametroService;
import br.com.ramazzini.service.relatorio.FileService;

/**
 *  
 */
@Named("exportarPdfBean")
public class ExportarPdfBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	ParametroService parametroService;
	
	private FileService jasperFileService;	
	private HashMap<String, Object> parametros;
	private JRBeanCollectionDataSource jrBeanCollectionDataSource;
	private String nome;
	
	/**
	 * 
	 * @param jasperFileService
	 * @param parametros
	 * @param jrBeanCollectionDataSource
	 * @param nome
	 * @throws MalformedURLException
	 */
	public ExportarPdfBean(
			HashMap<String, Object> parametros,
			JRBeanCollectionDataSource jrBeanCollectionDataSource,
			String nome) throws MalformedURLException {
		String baseDir = parametroService.recuperarPorParametroSistema(ParametroSistema.DIR_BASE_RELATORIO).getValor();
		this.jasperFileService = new FileService(baseDir);
		this.parametros = parametros;
		this.jrBeanCollectionDataSource = jrBeanCollectionDataSource;
		this.nome = nome;
	}
		
	/**
	 * Realiza o download de um pdf recebendo um datasource e uma lista de parametros.
	 * @param parametros
	 * @param jrBeanCollectionDataSource
	 * @param nome
	 * @throws Exception
	 */
	public void download(final String... relativePaths) throws Exception {
		File arquivo = jasperFileService
				.getFile(relativePaths);
		JasperReport report = (JasperReport) JRLoader.loadObject(arquivo);
		JasperPrint jasperPrint = JasperFillManager
				.fillReport(report, parametros, jrBeanCollectionDataSource);
		DownloadUtil.download(
				JasperExportManager.exportReportToPdf(jasperPrint),
				nome,
				MimeType.APPLICATION_PDF_PDF);
	}	
}
