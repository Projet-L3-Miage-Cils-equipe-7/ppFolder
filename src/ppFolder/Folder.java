package ppFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Folder {

	private String pathFile;
	
	/**
	 * Constructeur
	 * @param path
	 */
	public Folder(String path) {
		this.pathFile = path;
	}
	
	/**
	 * @param nameDir
	 * @return void
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
	 * @return boolean
	 * removeDirectory ==> permet de supprimer un dossier en indiquant son nom
	 * PS: elle le supprimer si il est present dans le repertoire courant && qu'il est vide !
	 * @throws IOException 
	 */
	public boolean removeDirectory(String nameDir) throws IOException {
		File index = new File(this.pathFile + "/" + nameDir);
		return (index.exists() && isDirEmpty(nameDir) )? index.delete() : false ;
	}
	
	/**
	 * 
	 * @param dir
	 * @return boolean
	 * @throws IOException
	 * isDirEmpty ==> retourne un boolean; (true si le dossier est vide, false sinon)
	 */
	private boolean isDirEmpty(String dir) throws IOException {
		Path path = Paths.get(dir);
		if (Files.isDirectory(path)) {return (!Files.list(path).findAny().isPresent())? true : false ;} 
		return false;
	}
	
}