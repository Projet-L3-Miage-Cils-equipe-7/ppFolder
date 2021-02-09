import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.StandardCopyOption;
public class debut {
	
	//Déplace un fichier/dossier cible vers un dossier
	public static void Move_file(Path pathfrom,Path pathto) {
		String nom_fichier = "//"+pathfrom.getFileName().toString();
		// pour ajouter un niveau hierarchique inférieur pour le fichier
        try {
        	if(pathto.toFile().isDirectory()) {
        		Files.move(pathfrom, Paths.get(pathto.toAbsolutePath()+nom_fichier));
			
        	}else {
					System.out.println("Erreur, le dossier n'est pas valide");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
    public static String get_extension(Path chemin_fichier) {
    	if(chemin_fichier.toFile().isDirectory()) {
    	System.out.println("Un répertoire ne peut pas avoir d'extension.");
    	
    	return "";
    	}
    	else {
    	String extension ="";
    	boolean fin = false;
    	for(int i=0; i<chemin_fichier.getFileName().toString().length(); i++) {
    		if(fin) {
    			extension += chemin_fichier.getFileName().toString().charAt(i);
    		}
    		if(chemin_fichier.getFileName().toString().charAt(i)=='.') {
    			fin = true;
    		}
    	}
    	//pour voir l'extension du fichier System.out.print(extension);
    	return extension;
    	}
    }
    
	public static ArrayList<String> liste_extension(File[] repertoire) {
    	ArrayList<String> ext = new ArrayList<String>();;
    	for(File f : repertoire) {
    		if(!f.isDirectory()) {
    			if(!(ext.contains(get_extension(f.toPath())))){
    				ext.add(get_extension(f.toPath()));
    			}
    		}

    	}
    	return ext;
    }
		
    public static ArrayList<File> parcourir(Path rep) {
    	ArrayList<File> fichiers = null;
		return fichiers;
    	
    }
    
	public static void main(String[] args) {
		/*Path source = Paths.get("C:\\Users\\renard\\Desktop\\Projet_repertoire\\hi.txt");
		Path dest = Paths.get("C:\\Users\\renard\\Desktop\\Projet_repertoire\\1");
		Move_file(source,dest);
		*/
		/*
		Path source = Paths.get("C:\\Users\\renard\\Desktop\\Projet_repertoire\\coucou.txt");
		get_extension(source);*/
		//Path source = Paths.get("C:\\Users\\renard\\Desktop\\Projet_repertoire\\coucou.txt");
		File test = new File ("C:\\Users\\renard\\Desktop\\Projet_repertoire");
		File[] rep = test.listFiles();
		System.out.println(liste_extension(rep).toString());
			}

	

}
