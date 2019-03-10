package com.mbh.soft.zipper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String URL = "http://mapsrefdev.brgm.fr/wxs/carmat/exploitations";
	private static final String OUTPUT_FOLDER = "C:/downloadsig";
	
	public static String downloadFile() {
		
		   String fileURL = URL + "?TYPENAME=export_contours&VERSION=2.0.0&SERVICE=WFS&REQUEST=GetFeature&OUTPUTFORMAT=SHAPEZIP&SRS=EPSG:2154&id_exploitation=282\r\n"; 
		   
	        try {
	            String path = HttpDownloadUtility.downloadFile(fileURL, OUTPUT_FOLDER);
	            System.out.println(path);
	            return path;
	            
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return null ;
	}
	 

	 
	 public static byte[] writeContent() {
		 String content = "Hello le monde, this JAVA 1o1";
		 return content.getBytes();
	 }
	 
    public static void main( String[] args )
    {
    	System.out.println("Tets");
    	Path path = new File("C://downloadsig/BDCM_20181019_143805.zip").toPath();
    	
    	FileUtility fileUtility = new FileUtility();

    	// Retrieving zip file from server
    	String thePath = downloadFile();
    	
    	// Unzip the directory
    	UnzipUtility unzip = new UnzipUtility();
    	String unzippedFolder = unzip.unZipIt(thePath);
    	
    	// Add a content file to the directory
    	fileUtility.addFile(unzippedFolder , writeContent());
		
		// Delete the zip file
    	fileUtility.deleteFile(thePath);
    	
    	// Zip the new directory
    	ZipUtility zip = new ZipUtility(unzippedFolder, thePath);
    	zip.zipIt();
    	
    	// Delete the unzipped directory
    	fileUtility.deleteFile(unzippedFolder);
    }
}
