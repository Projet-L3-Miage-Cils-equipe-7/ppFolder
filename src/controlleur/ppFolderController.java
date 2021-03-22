package controlleur;

import model.Classifieur;
import view.ppFolderView;

public class ppFolderController {
	
	private ppFolderView vue;
	@SuppressWarnings("unused")
	private Classifieur model;
	
	public ppFolderController(ppFolderView vue, Classifieur model) {
		this.vue = vue;
		this.model = model;
		
		// les listeners
		try {
			this.vue.ajouterUnPathListener(new ValidatePath());
			this.vue.allerAajouteruneregleListener(new AllerDansAjouterUneeRegle());
			this.vue.supprimerUneRegleListener(new SupprimerUneRegle());
			this.vue.ajouterUneReglelistener(new AjouterUneRegle());
			this.vue.miseAjourListeRegleListener(new UpdateListRegles());
		}catch(NullPointerException e) {
			System.out.println("erreur !");
		}
	}
}
