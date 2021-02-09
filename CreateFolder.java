
import static java.nio.file.StandardCopyOption.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFolder {


	// Le chemin absolu du fichier et du r�pertoire en param�tre
	public static void move_file(String source, String dest) {
		File tmp = new File(source);
		File dir =new File(dest);
        String fichier = tmp.getName();
        Path path_source = Paths.get(source);
        Path path_dest = Paths.get(dest+"//"+fichier);
        // Le double slash pour indiquer qu'on se situe � l'int�rieur du r�pertoire

        try {
        	if(dir.isDirectory()) {
				Files.move(path_source, path_dest);
			
        	}else {
					System.out.println("Erreur, le dossier n'est pas valide");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
        
	
	
	public static void main(String[] args) {
		move_file(fichier1,dossier1);
	}
	
}
