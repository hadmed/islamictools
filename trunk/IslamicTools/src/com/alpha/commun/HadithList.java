package com.alpha.commun;

import java.io.File;

public class HadithList
{
private static HadithList instance;
private OpenSLCFile openSLC;

public class Msg{
	public String txt;
	public String ref;
	
	public Msg(String txt,String ref)
	{
	this.txt = txt;
	this.ref = ref;
	}
}


public static HadithList getInstance() {
	if(instance == null)
		instance = new HadithList();
	return instance;
}
	
private HadithList() {
	openSLC = new OpenSLCFile(new File(Param.FOLDER_SRC+Param.FILE_HADITH),OpenSLCFile.TYPEREAD.CONFIG_AND_DATA);
}	
	

public Msg getHadithTxt(int pos)
{
	if (pos<0)
	{
		pos = (int)(Math.random()*openSLC.length());
	}	
	return new Msg(openSLC.getMessage("_", pos), openSLC.getMessage("#", pos));
}

}
