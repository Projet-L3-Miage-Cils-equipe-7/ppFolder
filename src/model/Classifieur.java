package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

public class Classifieur {
	
	private File mainPath;
	private static Rules<String> rule = new Rules<>();
	private static HashMap<String, String> listeOfPathsFiles = new HashMap<>();
	
	/**
	 * Constructeur 
	 * @param path
	 */
	public Classifieur(String path) {
		this.mainPath = new File(path).getAbsoluteFile();
		listFiles(this.mainPath);
	}
	
	public Classifieur() {}
	
	/**
	 * @param path
	 * @return void
	 * listFiles ==> ajoute recurssivement tout lesfichiers dans une liste
	 */
	private static void listFiles(File path) {
		if (path.isDirectory()) {
		    File[] list = path.listFiles();
		    if (list != null) for (int i = 0; i < list.length; i++) {listFiles(list[i]);}
		} else {getListeOfPathsFiles().put(path.getAbsolutePath(), get_extension(path.toString()).toLowerCase());}
	}
	
	/**
	 * @param chemin_fichier
	 * @return String
	 * get_extension ==> permet de bien decouper l'extension du fichier
	 */
	public static String get_extension(String s) {
		String extension = "";
		boolean fin = false;
		for(int i=0; i< s.toString().length(); i++) {
    		if(fin) {extension += s.toString().charAt(i);}
    		if(s.toString().charAt(i)=='.') {fin = true;}
    	}
    	return extension;
    }
	
	/**
	 * @param null
	 * @return void
	 * trier ==> trier les fichiers selons les regles & le dictionnaire de (path, extension)
	 * @throws IOException 
	 */
	public void trier() throws IOException {
		this.rulesToFolders();
		System.out.println("Debut du tri...");
		if(!getListeOfPathsFiles().isEmpty()) {
	        for (String regle : getRule().readSerializedRules()) {
	        	for (String path : getListeOfPathsFiles().keySet()) {
	        		if (regle.indexOf(get_extension(path).toString()) != -1) {
	        			this.move_file(path.toString(), this.mainPath+"/"+regle.replaceAll(",", " -").toString());
	        		}
	        	}
	        }
		}
		System.out.println("Fin du tri...");
	}
	
	/**
	 * 
	 * @param null
	 * @return void
	 * @throws IOException
	 * rulesToFolders ==> permet de creer des dossiers pour chacunes des regles crée
	 */
	private void rulesToFolders() throws IOException {
		Folder cd = new Folder(this.mainPath.toString());
		List<String> listRules = getRule().readSerializedRules();
		for (int i = 0; i < listRules.size(); i++) {
			if(listRules.get(i).toString().contains((CharSequence) getListeOfPathsFiles().values())) {
				cd.create_Dir(listRules.get(i).replaceAll(",", " -"));
			}
		}
		System.out.println("Création des dossiers...");
	}
	
	/**
	 * @param source
	 * @param destination
	 * @throws IOException
	 * @return void
	 * move_file ==> permet de deplacer un fichier d'un repertoire vers un autre
	 */
	public boolean move_file(String source, String dest) throws IOException {
		if(new File(dest).isDirectory()) {
			Files.move(Paths.get(source), Paths.get(dest + "//" + new File(source).getName()), StandardCopyOption.REPLACE_EXISTING);
			return true;
		}
		System.out.println("Erreur sur le dossier de destination !");
		return false;
	}

	public static Rules<String> getRule() {
		return rule;
	}

	public static HashMap<String, String> getListeOfPathsFiles() {
		return listeOfPathsFiles;
	}	
}