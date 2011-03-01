package com.alpha.model;

import java.io.File;

import com.alpha.commun.OpenSLCFile;

public class SLCFile {
private File fichier;
private String filename;
private String titre;
private Boolean traite;
private int actif;

public SLCFile(File file) {
		this.fichier = file;
		this.setFilename(file.getAbsolutePath());
		this.titre = null;
		this.traite = Boolean.FALSE;
		this.actif = 0;
	}



public String getTitre()
{
return traite ? titre : readSLC(fichier);
}	

private String readSLC(final File src)
{
	OpenSLCFile openSLCFile = new OpenSLCFile(src);
	if (openSLCFile.getActif())
	{
		this.actif = 1;
		this.traite = true;
		this.titre = openSLCFile.getTitre();
	}
	return openSLCFile.getTitre();
}

public int isActif() {
	return this.actif;
}



private void setFilename(String filename) {
	this.filename = filename;
}



public String getFilename() {
	return filename;
}

}
