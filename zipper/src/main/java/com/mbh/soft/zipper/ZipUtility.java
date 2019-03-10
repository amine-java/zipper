package com.mbh.soft.zipper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * ZipUtility: Class permettant de zipper des folders
 * 
 * @author boufatah
 *
 */
public class ZipUtility {
	
    private List<String> fileList = new ArrayList<String>(); ;
    private String sourceFolder ; 
    private String resultLocation ;
    
    /**
     * Constructeur
     * @param sourceFolder: Le chemin du dossier à zipper
     * @param resultLocation: Le nom du dossier à zipper (result.zip)
     */
    public ZipUtility(String sourceFolder , String resultLocation) {
    	this.sourceFolder = sourceFolder;
    	this.resultLocation = resultLocation;
    }

    /**
     * Méthode permettant de ziper le dossier sourceFolder. Le resultat est un dossier zippé qui se trouve sur zipFileName
     */
    public void zipIt(){
    generateFileList(new File(sourceFolder));
     byte[] buffer = new byte[1024];
     try{
    	FileOutputStream fos = new FileOutputStream(resultLocation + ".zip");
    	ZipOutputStream zos = new ZipOutputStream(fos);
    	for(String file : this.fileList){
    		ZipEntry ze= new ZipEntry(file);
        	zos.putNextEntry(ze);
        	FileInputStream in =
                       new FileInputStream(sourceFolder + File.separator + file);
        	int len;
        	while ((len = in.read(buffer)) > 0) {
        		zos.write(buffer, 0, len);
        	}
        	in.close();
    	}
    	zos.closeEntry();
    	zos.close();
    }catch(IOException ex){
       ex.printStackTrace();
    }
   }
    /**
     * Boucle sur le dossier & récupère tous les fichiers,
     * les fichiers sont ajoutés au fileList.
     * @param node fichier ou directory
     */
    public void generateFileList(File node){

	if(node.isFile()){
		fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
	}
	if(node.isDirectory()){
		String[] subNote = node.list();
		for(String filename : subNote){
			generateFileList(new File(node, filename));
		}
	}
    }
    /**
     * Format du file path 
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file){
    	return file.substring(sourceFolder.length() + 1 , file.length());
    }
}