import view.ppFolderView;
import model.Classifieur;
import controlleur.ppFolderController;

public class MVC_ppFolder {
    
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Classifieur model = new Classifieur();
    	ppFolderView vue = new ppFolderView();
    	ppFolderController controller = new ppFolderController(vue, model);
        vue.setVisible(true);
    }
}