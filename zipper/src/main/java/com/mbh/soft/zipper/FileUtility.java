package com.mbh.soft.zipper;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

/**
 * FileUtility : Class permettant le traitement des fichiers/dossiers; ajout, suppression
 * 
 * @author boufatah
 *
 */
public class FileUtility {

	 /**
	  * Méthode permettant de supprimer un dossier.
	  * @param path: Le chemin du dossier à supprimer
	  */
	 public  void deleteFile(String path) {
		 File directory = new File(path);
		 if (directory.exists()) {
			 if (directory.isDirectory()) {
			    for (File file : directory.listFiles()) {
			        file.delete();
			    }
			 }
			    directory.delete();
			}
	 }
	 
	 /**
	  * Méthode permettant d'ajouter un fichier à un dossier
	  * @param folder
	  * @param content
	  */
	 public  void addFile(String folder , byte[] content) {
		 try {
			Files.touch(new File(folder + "/" + "parametres.txt"));
			Files.write(content, new File(folder + "/" + "parametres.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	 }
}
