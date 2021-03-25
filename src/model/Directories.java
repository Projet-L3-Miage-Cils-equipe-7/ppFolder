package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
public class Directories {
 
    private List<String> listOfDirs = new ArrayList<>();
	private boolean recursivePath;
 
    /**
     * 
     * @return
     */
    public Directories(String path) {
        this.recursivePath = true;
        listDirectory(path);
    }
 
    /**
     * @return {@link Void}
     * @param dir
     */
    private void listDirectory(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                	listOfDirs.add(files[i].getAbsolutePath());
                }
                if (files[i].isDirectory() == true && this.recursivePath == true) {
                    this.listDirectory(files[i].getAbsolutePath());
                }
            }
        }
    }  
    
    /**
     * 
     * @return {@link List}
     */
    public List<String> getListOfDirs(){
    	return listOfDirs;
    }
    
    public static void main(String[] args) {
		Directories  dir = new Directories("/home/mockingbird/Downloads");
		System.out.println(dir.getListOfDirs().toString());
	}
}