package com.mbh.soft.zipper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * UnzipUtility: Class permettant le unzip d'un dossier zip
 * 
 * @author boufatah
 *
 */
public class UnzipUtility {

	/**
	 * Méthode permettant d'unzipper un folder zip.
	 * 
	 * @param zipFileLocation: Le chemin vers le dossier zip à unzipper 
	 * @return Location du dossier unzippé
	 */
	 public  String unZipIt(String zipFileLocation){
	     byte[] buffer = new byte[1024];
	     String outputFolder = "";
	     try{
	    	 outputFolder = zipFileLocation + "_unzipped"; 
	    	ZipInputStream zis =
	    		new ZipInputStream(new FileInputStream(zipFileLocation));
	    	ZipEntry ze = zis.getNextEntry();
	    	while(ze!=null){
	    	   String fileName = ze.getName();
	           File newFile = new File(outputFolder + File.separator + fileName);

	            new File(newFile.getParent()).mkdirs();
	            FileOutputStream fos = new FileOutputStream(newFile);
	            int len;
	            while ((len = zis.read(buffer)) > 0) {
	       		fos.write(buffer, 0, len);
	            }
	            fos.close();
	            ze = zis.getNextEntry();
	    	}
	        zis.closeEntry();
	    	zis.close();
	    }catch(IOException ex){
	       ex.printStackTrace();
	    }
	     return outputFolder;
	   }
	 
}
