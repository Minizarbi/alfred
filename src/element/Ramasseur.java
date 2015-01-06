package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

import utilitaires.Calculs;

/**
 * Le ramasseur est un personnage qui va essentiellement ramasser les potions,
 * s'il n'en trouve pas, il erre.
 */
public class Ramasseur extends Personnage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ramasseur(String nom) {
		super(nom, 100, 100);
	}

	public void strategie(VueElement ve,
			Hashtable<Integer, VueElement> voisins, Integer refRMI)
			throws RemoteException {
		Actions actions = new Actions(ve, voisins); // je recupere les voisins
													// (distance < 10)
		Deplacements deplacements = new Deplacements(ve, voisins);

		if (0 == voisins.size()) { // je n'ai pas de voisins, j'erre
			parler("Y'a pas de potion, j'erre...", ve);
			deplacements.seDirigerVers(0); // errer

		} else {
			VueElement cible = Calculs.chercherPotionProche(ve, voisins);

			if (cible == null) { // si il n'y a pas de potion a proximite
				parler("Y'a que des personnages, j'erre...", ve);
				deplacements.seDirigerVers(0);
			}

			int distPlusProche = Calculs.distanceChebyshev(ve.getPoint(),
					cible.getPoint());

			int refPlusProche = cible.getRef();

			if (distPlusProche <= 2) { // si suffisamment proches
				parler("Je ramasse une potion", ve);
				actions.ramasser(refRMI, refPlusProche, ve.getControleur()
						.getArene());
			} else { // si potions, mais plus eloignes
				// je vais vers la potion
				parler("Je vais vers la potion " + refPlusProche, ve);
				deplacements.seDirigerVers(refPlusProche);
			}
		}
	}
}
