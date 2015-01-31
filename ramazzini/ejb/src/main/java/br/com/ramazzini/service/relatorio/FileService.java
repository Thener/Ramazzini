package br.com.ramazzini.service.relatorio;

import java.io.File;
import java.io.Serializable;

public class FileService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String COMPONENT_NAME = "fileService";

	private File baseDir;
	
	public FileService(String baseDir) {
		if (baseDir.endsWith("/")) {
			baseDir = baseDir.substring(0, baseDir.length() - 1);
		}
		this.baseDir = new File(baseDir);
		mkdirs(this.baseDir);
	}
	
	/**
	 * Constroi um arquivo a partir de um dado conjunto de paths relativos.
	 * 
	 * @param relativePaths conjunto de paths relativos
	 * @return
	 */
	public File getFile(final String... relativePaths) {
		return mkdirs(new File(getBaseDir(), buildRelativePath(relativePaths)), true);
	}
	
	public File getBaseDir() {
		return this.baseDir;
	}
	
	protected String buildRelativePath(final String... relativePaths) {
		final StringBuilder builder = new StringBuilder();
		for (final String relativePath : relativePaths) {
			if (builder.length() > 0) {
				builder.append('/');
			}
			
			builder.append(relativePath);
		}
		return builder.toString();
	}
	
	private File mkdirs(final File dir) {
		return mkdirs(dir, false);
	}

	private File mkdirs(final File dir, boolean onlyParent) {
		if (onlyParent) {
			mkdirs(dir.getParentFile());
			return dir;
		}

		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				throw new RuntimeException("Directory '" + dir + "' cannot be created");
			}
		}

		return dir;
	}
	
}
