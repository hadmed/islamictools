package com.alpha.commun;

import com.alpha.view.R;

public final  class Param
{
public static final int VERSION_PARAM = 14;
public static final int VERSION_DOC = 1;
public static final int VERSION_DB = 2;
public static final String DB_NAME = "IslamicTools";
public static final String NAME = "islamicTools";
public static final String folder = "/sdcard/IslamicTools/citys"; 
public static final int fadjr = 1 , shurooq = 2 , zuhr = 4, asr = 8 , maghrib = 16, isha = 32 ; 
public static final int[] idx_prayer = {1,2,4,8,16,32}; 
public static final int[] str_prayer = {R.string.pt_fadjr , R.string.pt_shurooq , R.string.pt_zuhr ,R.string.pt_asr,R.string.pt_maghrib,R.string.pt_isha};
public static final String FOLDER_SRC = "/sdcard/islamicTools/";
public static final String HTTP_SRC = "http://islamictools.googlecode.com/files/";
public static final String FILE_DOWNLOAD = "download.sam";
public static final String FILE_HADITH = "hadith.slc";

}
