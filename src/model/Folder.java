package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class Folder {

	private String pathFile;
	
	/**
	 * Constructeur
	 * @param path
	 */
	public Folder(String path) {this.pathFile = path;}
	
	/**
	 * @param nameDir
	 * @return {@link Void}
	 * @throws IOException
	 * create_Dir ==> permet de creer un dossier en donnant son nom
	 * PS: elle le cree dans le repertoire courant !
	 */
	public boolean create_Dir(String nameDir) throws IOException {
		 Path name = Paths.get(this.pathFile + "/" + nameDir);
	     if (!Files.exists(name)) {
	    	 Files.createDirectories(name);
	    	 return true;
	     }	
	     return false;
	}
	
	/**
	 * @param nameDir
	 * @return {@link Boolean}
	 * removeDirectory ==> permet de supprimer un dossier en indiquant son nom
	 * PS: elle le supprimer si il est present dans le repertoire courant && qu'il est vide !
	 * @throws IOException 
	 */
	public void removeDirectory() throws IOException {
		Directories dir = new Directories(this.pathFile.toString());
		Iterator<String> it = dir.getListOfDirs().iterator();
		while(it.hasNext()) {
			String nameDir = it.next().toString();
			File index = new File(nameDir).getAbsoluteFile();
			if((index.exists() && isDirEmpty(nameDir))) {
				index.delete();
			}
		}
		dir.getListOfDirs().clear();
	}
	
	/**
	 * 
	 * @param dir
	 * @return {@link Boolean}n
	 * @throws IOException
	 * isDirEmpty ==> retourne un boolean; (true si le dossier est vide, false sinon)
	 */
	private boolean isDirEmpty(String dir) throws IOException {
		Path path = Paths.get(dir);
		if (Files.isDirectory(path)) {return (!Files.list(path).findAny().isPresent())? true : false ;} 
		return false;
	}
}