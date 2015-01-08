package element;

import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.Hashtable;

import utilitaires.Calculs;

public class Fuyard extends Personnage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fuyard() {
		super("biche", 1, 1, 1, 1, 1);
	}
	
	public void fuir(VueElement ve, Deplacements deplacements){
		int fuiteX, fuiteY;
		
		//On regarde où est la fuite la plus rapide depuis le centre de l'arene
		if(ve.getPoint().x < 50)
			fuiteX = -20;
		else
			fuiteX = 20;
		
		if(ve.getPoint().y < 50)
			fuiteY = -20;
		else
			fuiteY = 20;
		
		//On se deplace vers le point de fuite
		deplacements.seDirigerVers(new Point(ve.getPoint().x + fuiteX, ve.getPoint().y + fuiteY));
	}
	
	public void fuir(VueElement ve, VueElement cible, Deplacements deplacements){
		int x,y;
		if(ve.getPoint().x > cible.getPoint().x)
			x = ve.getPoint().x+10;
		else
			x = ve.getPoint().x-10;
		if(ve.getPoint().y > cible.getPoint().y)
			y = ve.getPoint().y+10;
		else
			y = ve.getPoint().y-10;
		
		deplacements.seDirigerVers(new Point(x,y)); // fuir
	}
    
	public void strategie(VueElement ve, Hashtable<Integer,VueElement> voisins, Integer refRMI) throws RemoteException {
        Deplacements deplacements = new Deplacements(ve,voisins);

        if (0 == voisins.size()) { // je n'ai pas de voisins, j'erre
        	parler("Je fuis...", ve);
        	this.fuir(ve, deplacements); //fuite
            
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
			
			if(distPlusProche <= 4) { // si suffisamment proches
				if(elemPlusProche instanceof Potion) { // potion
					parler("Je fuis...", ve);
		        	this.fuir(ve, deplacements); //fuite
					
				} else { // personnage
					if(!memeEquipe) { // duel seulement si pas dans la meme equipe (pas de coup d'etat possible dans ce cas)
						parler("Je fuis la Personne...", ve);
			        	this.fuir(ve, cible, deplacements); //fuite
					} else {
						parler("Je fuis...", ve);
			        	this.fuir(ve, deplacements); //fuite
					}
				}
			} else { // si voisins, mais plus eloignes
				parler("Je fuis...", ve);
	        	this.fuir(ve, deplacements); //fuite
			}
        }
	}
}

