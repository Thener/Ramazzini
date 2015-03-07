package br.com.ramazzini.applet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JApplet;
  
public class AppletImpressao extends JApplet implements Printable {      
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Book book;  
    public double fatorConverMMPt = 2.834646D;  
  
    //========== -  Metodo de iniciação do applet - =============  
    @Override  
    public void init() {  
      imprimir();  
    }  
  
    //========== -  Metodo de configuração da impressora Padrão - =============  
    public void imprimir() {  
        PrinterJob impressoraPadrao = PrinterJob.getPrinterJob();  
        //p.defaultPage();  
        
        impressoraPadrao.setPrintable(this);  
        book = new Book();  
        PageFormat pageFormat = new PageFormat();  
        //pageFormat = printJob.pageDialog(pageFormat);  
        Paper Folha = new Paper();  
        //Papel A4  
        //8,5 pol.  
        double width  = 210.9 * fatorConverMMPt;  
        //13 pol.  
        double height = 214.9 * fatorConverMMPt;  
        //double width  = 8.2;  
        //double height = 8.2;  
        //System.out.print("Largura: " + width + " Altura: " + height);  
        Folha.setSize(width, height);  
        Folha.setImageableArea(0.0, 0.0, width, height);  
        pageFormat.setPaper(Folha);  
        book.append(this, pageFormat);  
        impressoraPadrao.setPageable(book);  
        try {  
            impressoraPadrao.print();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** Método da interface Printable */  
    public int print(Graphics g, PageFormat format, int pageIndex) throws PrinterException {  
  
        Graphics2D eventoGrafico = (Graphics2D) g;  
  
        //==== - Define o formado da folha de impressão (area possivel de se fazer a impressão do conteudo) - =======  
        eventoGrafico.translate(format.getImageableX(), format.getImageableX());  
  
        //======- Definição da font a ser utilizada - ================  
        eventoGrafico.setFont(new Font("Sans Serif", Font.PLAIN, 8 ));  
        eventoGrafico.setColor(Color.BLACK);
  
        //======- Imprimindo o conteudo evento.drawString(valor, eixo x, e eixo y) - ================  
          
        eventoGrafico.drawString("Texto a ser impresso1", 46, 169);  
        eventoGrafico.drawString("Texto a ser impresso2", 46, 169);  
        eventoGrafico.drawString("Texto a ser impresso3", 46, 169);  
        eventoGrafico.drawString("Texto a ser impresso4", 46, 169);  
  
        return PAGE_EXISTS;  
    }  
}  
