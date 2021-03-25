package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Classifieur {
	
	private File mainPath;
	private static Rules<String> rule = new Rules<>(new ArrayList<>());
	private static HashMap<String, String> listeOfPathsFiles = new HashMap<>();
	private List<String> listExtension = new ArrayList<>();
	
	/**
	 * Constructeur 
	 * @param path
	 */
	public Classifieur() {}
	public Classifieur(String path) {
		this.mainPath = new File(path).getAbsoluteFile();
		listFiles(this.mainPath);
	}
	
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
	public static String get_extension(String fileName) {
		String extension = "";
        int index = fileName.lastIndexOf('.');
        if (index > 0) {extension = fileName.substring(index + 1);}
        return extension;
    }
	
	/**
	 * @param regles 
	 * @param null
	 * @return void
	 * trier ==> trier les fichiers selons les regles & le dictionnaire de (path, extension)
	 * @throws IOException 
	 */
	public void trier(List<String> regles) throws IOException {
		this.rulesToFolders(regles);
		if(!getListeOfPathsFiles().isEmpty()) {
	        for (String regle : regles) {
	        	 Iterator<String> it = getListeOfPathsFiles().keySet().iterator();
	        	 while (it.hasNext()) {
	        		 String i = it.next();
	        		if (regle.toLowerCase().indexOf(get_extension(i).toLowerCase().toString()) != -1) {
	        			if(get_extension(i).indexOf('/') != -1 || get_extension(i).indexOf('\\') != -1 || get_extension(i) != "") {
	        			this.move_file(i.toString(), this.mainPath+"/"+regle.replaceAll(",", " -").toString());
	        			}
	        		}
	        	}
	        }
	        getListeOfPathsFiles().clear();
	       new Folder(this.mainPath.toString()).removeDirectory();
		}
	}
	
	/**
	 * 
	 * @param null
	 * @return void
	 * @throws IOException
	 * rulesToFolders ==> permet de creer des dossiers pour chacunes des regles crée
	 */
	private void rulesToFolders(List<String> regles) throws IOException {
		Folder cd = new Folder(this.mainPath.toString());
		Iterator<String> it = regles.iterator();
		while(it.hasNext()){
			String rule = it.next();
			cd.create_Dir(rule.replaceAll(",", " -"));
		}
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
		return false;
	}

	/**
	 * getteur pour rule de type Rules<String>
	 * @return {@link Rules}
	 */
	public static Rules<String> getRule() {
		return rule;
	}

	/**
	 * getteur pour la liste les (chemins/extensions) des fichiers
	 * @return {@link HashMap}
	 */
	public static HashMap<String, String> getListeOfPathsFiles() {
		return listeOfPathsFiles;
	}
	
	public List<String> getListOfExtension() {
		Set<String> exts = new HashSet<>(Classifieur.getListeOfPathsFiles().values());
		this.listExtension.clear();
		this.listExtension.addAll(exts);
		return listExtension;
	}
}