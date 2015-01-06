package element;

import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.rmi.RemoteException;
import java.util.Hashtable;

import utilitaires.Calculs;

public class Seducteur extends Personnage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Seducteur(String nom) {
		super(nom, 50, 400);
	}

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
					// ramassage
					parler("Je ramasse une potion", ve);
					actions.ramasser(refRMI, refPlusProche, ve.getControleur()
							.getArene());

				} else { // personnage
					if (!memeEquipe) { // duel seulement si pas dans la meme
										// equipe (pas de coup d'etat possible
										// dans ce cas)
						// On se combat que si on a un charisme superieur
						if (this.getCaract("charisme") > ve.getControleur()
								.getArene().consoleFromRef(refPlusProche)
								.getElement().getCaract("charisme")) {
							// duel
							parler("Je fais un duel avec " + refPlusProche, ve);
							actions.interaction(refRMI, refPlusProche, ve
									.getControleur().getArene());
						} else {
							parler("Je fuis " + refPlusProche, ve);
							deplacements.fuir(refPlusProche); // fuir
						}
					} else {
						parler("J'erre...", ve);
						deplacements.seDirigerVers(0); // errer
					}
				}
			} else { // si voisins, mais plus eloignes
				if (!memeEquipe) { // potion ou enemmi
					if (this.getCaract("charisme") > ve.getControleur()
							.getArene().consoleFromRef(refPlusProche)
							.getElement().getCaract("charisme")) {
						// je vais vers le plus proche
						parler("Je vais vers mon voisin " + refPlusProche, ve);
						deplacements.seDirigerVers(refPlusProche);
					} else {
						parler("Je fuis " + refPlusProche, ve);
						deplacements.fuir(refPlusProche); // fuir
					}

				} else {
					parler("J'erre...", ve);
					deplacements.seDirigerVers(0); // errer
				}
			}
		}
	}
}
