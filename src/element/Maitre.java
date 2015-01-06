package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

import utilitaires.Calculs;

/**
 * Le maitre est un personnage trop puissant, il ne ramasse pas les potions car
 * il n'en a oas besoin.
 */
public class Maitre extends Personnage {

	private static final long serialVersionUID = 1L;

	public Maitre(String nom) {
		super(nom, 2147483647, 2147483647);
	}

	@Override
	public void strategie(VueElement ve,
			Hashtable<Integer, VueElement> voisins, Integer refRMI)
			throws RemoteException {
		Actions actions = new Actions(ve, voisins); // je recupere les voisins
													// (distance < 10)
		Deplacements deplacements = new Deplacements(ve, voisins);

		if (0 == voisins.size()) { // je n'ai pas de voisins, j'erre
			parler("J'erre...", ve);
			deplacements.seDirigerVers(0); // errer

		} else {
			VueElement cible = Calculs.chercherElementProche(ve, voisins);

			int distPlusProche = Calculs.distanceChebyshev(ve.getPoint(),
					cible.getPoint());

			int refPlusProche = cible.getRef();
			Element elemPlusProche = cible.getControleur().getElement();

			// dans la meme equipe ?
			boolean memeEquipe = false;

			if (elemPlusProche instanceof Personnage) {
				memeEquipe = (getLeader() != -1 && getLeader() == ((Personnage) elemPlusProche)
						.getLeader()) || // meme leader
						getLeader() == refPlusProche || // cible est le leader
														// de this
						((Personnage) elemPlusProche).getLeader() == refRMI; // this
																				// est
																				// le
																				// leader
																				// de
																				// cible
			}

			if (distPlusProche <= 2) { // si suffisamment proches
				if (elemPlusProche instanceof Potion) { // potion
					// Stats max donc on ne ramasse pas la potion
					parler("J'erre...", ve);
					deplacements.seDirigerVers(0); // errer

				} else { // personnage
					if (!memeEquipe) { // duel seulement si pas dans la meme
										// equipe (pas de coup d'etat possible
										// dans ce cas)
						// duel
						parler("Je fais un duel avec " + refPlusProche, ve);
						actions.interaction(refRMI, refPlusProche, ve
								.getControleur().getArene());
					} else {
						parler("J'erre...", ve);
						deplacements.seDirigerVers(0); // errer
					}
				}
			} else { // si voisins, mais plus eloignes
				if (!memeEquipe) { // potion ou enemmi
					// je vais vers le plus proche
					parler("Je vais vers mon voisin " + refPlusProche, ve);
					deplacements.seDirigerVers(refPlusProche);

				} else {
					parler("J'erre...", ve);
					deplacements.seDirigerVers(0); // errer
				}
			}
		}
	}
}
