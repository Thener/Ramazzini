package br.com.ramazzini.applet;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JApplet;

public class Impressao extends JApplet { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() { 
		FileOutputStream outputFile = null; 
		try {
			outputFile = new FileOutputStream("LPT1"); 
			PrintWriter out = new PrintWriter(outputFile); 
			out.println("Teste Impressao"); 
			out.close(); 
			outputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
