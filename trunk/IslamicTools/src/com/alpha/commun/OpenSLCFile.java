package com.alpha.commun;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.util.Log;

public class OpenSLCFile {
private File fichier;
private String filename;
private int type,version;
private String titre;
private int opener;
private Boolean actif;
private Map<String,List<String>> data;
private Map<String,String> config ;
private Integer length;
private static String INDEX_ZIP="index.slc",CONFIG_R="@",SEPARATOR_R="|",TYPE = "t",TITLE="h",VERSION="v",OPENER= "r";
public static enum TYPEREAD { CONFIG_AND_DATA, CONFIG};


private void checkSLCFile(File file,String fileInZip,TYPEREAD typeRead) {
		this.fichier = file;
		this.filename = file.getAbsolutePath();
		data = new HashMap<String, List<String>>();
		setType(0);
		setVersion(0);
		setTitre("-");
		setActif(false);
		setOpener(0);
		this.readConfigAndData(file,fileInZip,typeRead); 
		length = null;			
	}


public OpenSLCFile(File file) {
	this.checkSLCFile(file,INDEX_ZIP,TYPEREAD.CONFIG);
}

public OpenSLCFile(File file,TYPEREAD typeRead) {
	this.checkSLCFile(file,INDEX_ZIP,typeRead);
}

public OpenSLCFile(File file,String fileInZip) {
	this.checkSLCFile(file,fileInZip,TYPEREAD.CONFIG_AND_DATA);
}

public OpenSLCFile(File file,String fileInZip,TYPEREAD typeRead) {
	this.checkSLCFile(file,INDEX_ZIP,typeRead);
}

private void readConfigAndData(File file,String nameInZip, TYPEREAD typeRead) {
	String filename = file.getName();
	BufferedReader br ;
	boolean inZip = file.getName().endsWith(".slcz");
	try {
		InputStream is = new FileInputStream(file);
		if (is != null)
		{
			br = null;
			if (inZip)
			{
				ByteArrayInputStream bais = null;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipInputStream zis = new ZipInputStream(is);
	        	ZipEntry ze;
	        	boolean exit = false;
	        	byte[] datax = new byte[1024];
	        	while ( (ze = zis.getNextEntry())!=null && !exit )
	        	{
	        		if (nameInZip.equalsIgnoreCase(ze.getName()))
	        		{
	        			exit = true;
	        			int count;
	        			while ((count = zis.read(datax)) != -1) {
	        			baos.write(datax, 0, count);
	        			bais = new ByteArrayInputStream(baos.toByteArray());
	        			}
	        			br =new BufferedReader(new InputStreamReader(bais));
	        		}
	        	}
	        	zis.close();
			} else
			{
				br = new BufferedReader(new InputStreamReader(is));					
			}
		//br = new BufferedReader(new FileReader(file));
		String line;
		boolean foundConfig = false;
		while (br.ready() && !foundConfig)
		{
			line = br.readLine();	
			if ( line!=null && !"".equals(line.trim()) && line.length()>1 )
			{
				if (line.startsWith(CONFIG_R))
				{
				setActif(true);
				this.config = checkLineConfig(line.substring(1));
				//exit if i found the config line & we are just in ConfigRead Mode
				if (TYPEREAD.CONFIG.equals(typeRead))
					{
					foundConfig = true;
					}
				} else if (line.length()>1)
				{					
					String key = ""+line.charAt(0);
					if (!this.data.containsKey(key))
					{
						this.data.put(key, new ArrayList<String>());
					}
					this.data.get(key).add(line.substring(1));
				}
			}
		}		
		br.close();
		}
	} catch (FileNotFoundException e) {
		Log.e("sam", "fichier supprimé : "+filename);
	} catch (IOException e) {
		Log.e("sam", "acces au fichier impossible  :"+filename);
	}
	finally
	{
		//br.close();
	}
}

private Map<String, String> checkLineConfig(String line) {
	Map<String,String> config = new HashMap<String, String>();
	StringTokenizer token = new StringTokenizer(line, SEPARATOR_R);
	String tokStr,key,val;
	String[] tmp;
	while (token.hasMoreTokens())
	{
	tokStr = token.nextToken();
	
	tmp = tokStr.split("=");
	key = tmp.length>0 ? tmp[0].trim(): null;
	val = tmp.length>1 ? tokStr.substring(key.length()+1).trim():null;
	if (key!=null && val != null && !config.containsKey(key))
		{
			config.put(key, val);
			if (TYPE.equalsIgnoreCase(key))
			{
				setType(val);
			} else if (TITLE.equalsIgnoreCase(key))
			{
				setTitre(val);
				setActif(true);
			} else if (VERSION.equalsIgnoreCase(key))
			{
				setVersion(val);
			} else if (OPENER.equalsIgnoreCase(key))
			{
				setOpener(val);
			}
		}
	}	
	return config;
}

public String getMessage(String code,int position)
{
	String str = getData(code,position);
	if (containtConfigKey(code))
	{
		str = getConfigFromKey(code).replaceAll("[$]", str).replaceAll("%", ""+(position+1));			
		str+=" ";
	} 
	return str;
}


public List<String> getListDataFromKey(String key)	
{
	return (data!=null && data.containsKey(key))? data.get(key) : null;	
}

public String getConfigFromKey(String key)	
{
	return (config!=null && config.containsKey(key)) ? config.get(key) : "";	
}

public boolean containtConfigKey(String key)	
{
	return config.containsKey(key);	
}


public String getData(String key, int location) {

return (this.data.containsKey(key) && this.data.get(key).size()>location && this.data.get(key).get(location) != null ) ? 
		  this.data.get(key).get(location).trim().replaceAll("\\\\n", "\n") : "";
	
}


public int length()
{
if (length == null)
{
	length = 0;
	int size; 
	for (String key : data.keySet())
	{
		size = data.get(key).size();
		if (size>length) 
			length = size; 
	}
}
return length;
}

public File getFichier() {
	return fichier;
}

private void setType(int type) {
	this.type = type;
}

private void setType(String key) {
	try
	{
		this.type = Integer.parseInt(key);
	} catch(NumberFormatException nfe)
	{
		Log.d("sam", "NumberFormatException type / val:"+key+"/ file :"+filename );
		this.type = 0;
	}
}

public int getType() {
	return type;
}

private void setTitre(String titre) {
	this.titre = titre;
}

public String getTitre() {
	return titre;
}

private void setVersion(int version) {
	this.version = version;
}

private void setVersion(String val) {
	try
	{
		this.version = Integer.parseInt(val);
	} catch(NumberFormatException nfe)
	{
		Log.d("sam", "NumberFormatException version / val:"+val+" / file :"+filename );
		this.type = 0;
	}
}

private void setOpener(String val) {
	try
	{
		this.opener = Integer.parseInt(val);
	} catch(NumberFormatException nfe)
	{
		Log.d("sam", "NumberFormatException opener / val:"+val+" / file :"+filename );
		this.type = 0;
	}	
}


public int getVersion() {
	return version;
}

private void setActif(Boolean actif) {
	this.actif = actif;
}

public Boolean getActif() {
	return actif;
}

private void setOpener(int opener) {
	this.opener = opener;
}

public int getOpener() {
	return opener;
}


}
