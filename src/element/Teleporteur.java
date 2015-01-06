package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

import utilitaires.Calculs;

public class Teleporteur extends Personnage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Teleporteur(String nom) {
		super(nom, 10, 10);
	}

	public void strategie(VueElement ve, Hashtable<Integer,VueElement> voisins, Integer refRMI) throws RemoteException {
		Actions actions = new Actions(ve, voisins); //je recupere les voisins (distance < 10)
        Deplacements deplacements = new Deplacements(ve,voisins);
        
        if (0 == voisins.size()) { // je n'ai pas de voisins, j'erre
        	parler("Je ne bouge pas...", ve);
        	deplacements.seDirigerVers(ve.getRef()); //ne pas bouger
            
        } else {
			VueElement cible = Calculs.chercherElementProche(ve, voisins);
			
			int distPlusProche = Calculs.distanceChebyshev(ve.getPoint(), cible.getPoint());
			
			int refPlusProche = cible.getRef();
			Element elemPlusProche = cible.getControleur().getElement();
			
			// dans la meme equipe ?
			boolean memeEquipe = false;
			
			if(elemPlusProche instanceof Personnage) {
				memeEquipe = (getLeader() != -1 && getLeader() == ((Personnage) elemPlusProche).getLeader()) || // meme leader
						getLeader() == refPlusProche || // cible est le leader de this
						((Personnage) elemPlusProche).getLeader() == refRMI; // this est le leader de cible
			}
			
			if(distPlusProche <= 2) { // si suffisamment proches
				if(elemPlusProche instanceof Potion) { // potion
					// ramassage
					parler("Je ramasse une potion", ve);
					actions.ramasser(refRMI, refPlusProche, ve.getControleur().getArene());
					
				} else { // personnage
					if(!memeEquipe) { // duel seulement si pas dans la meme equipe (pas de coup d'etat possible dans ce cas)
						// teleportation
						parler("Je me teleporte " + refPlusProche, ve);
						deplacements.seTeleporter();
					} else {
						parler("Je ne bouge pas...", ve);
			        	deplacements.seDirigerVers(ve.getRef()); //ne pas bouger
					}
				}
			} else { // si voisins, mais plus eloignes
				if(!memeEquipe) { // potion ou enemmi 
					// teleportation
					parler("Je me teleporte " + refPlusProche, ve);
					deplacements.seTeleporter();
		        	
				} else {
					parler("Je ne bouge pas...", ve);
		        	deplacements.seDirigerVers(ve.getRef()); //ne pas bouger
				}
			}
        }
	}
}
