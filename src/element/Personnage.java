/**
 * 
 */
package element;


import interaction.Actions;
import interaction.Deplacements;
import interfaceGraphique.VueElement;

import java.awt.Point;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

import utilitaires.Calculs;

/**
 * Un personnage: un element possedant des caracteristiques et etant capable
 * de jouer une strategie. 
 */
public class Personnage extends Element implements IPersonnage {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Reference du leader de ce personnage, -1 si aucun.
	 */
	private int leader;
	
	/**
	 * Reference des personnages de l'equipe de ce personnage. 
	 * Vide si le leader n'est pas egal a -1.
	 */
	private ArrayList<Integer> equipe;

	/**
	 * Constructeur d'un personnage avec un nom et une quantite de force et de charisme.
	 * Au depart, le personnage n'a ni leader ni equipe.
	 * @param nom
	 * @param force
	 * @param charisme
	 * @param hp
	 * @param vitesse
	 * @param defense
	 */
	public Personnage(String nom, int force, int charisme, int hp, int vitesse, int defense) {
		super(nom);
		ajouterCaract("force", force);
		ajouterCaract("charisme", charisme);
		
		/* -------------------------------------------
		 * Nouvelles stats
		 * ---------------------------------------- */
		ajouterCaract("hp", hp);
		ajouterCaract("vitesse", vitesse);
		ajouterCaract("defense", defense);
				
		leader = -1;
		equipe = new ArrayList<Integer>();
	}
	
	public Personnage(String nom) {
		this(nom, 0, 99, 1, 1, 0);
	}
	
	/* ----------------------------------------------------------
	 * ACCESSEURS
	 * ---------------------------------------------------------- */
	
	/** ---------------------------------------------------------
	 * 		public int getForce
	 *  ---------------------------------------------------------
	 * Retourne la valeur de force.
	 * @return bonus de force
	 *  ------------------------------------------------------ */
	public int getForce() {
		return getCaract("force");
	}
	
	
	/** ---------------------------------------------------------
	 * 		public int getCharisme
	 *  ---------------------------------------------------------
	 * Retourne la valeur de charisme.
	 * @return bonus de charisme
	 *  ------------------------------------------------------ */
	public int getCharisme() {
		return getCaract("charisme");
	}
	
	/** ---------------------------------------------------------
	 * 		public int getHP
	 *  ---------------------------------------------------------
	 * Retourne la valeur de la vie.
	 * @return bonus de vie
	 *  ------------------------------------------------------ */
	public int getHP() {
		return getCaract("hp");
	}
	
	/** ---------------------------------------------------------
	 * 		public int getVitesse
	 *  ---------------------------------------------------------
	 * Retourne la valeur de vitesse.
	 * @return bonus de vitesse
	 *  ------------------------------------------------------ */
	public int getVitesse() {
		return getCaract("vitesse");
	}
	
	/** ---------------------------------------------------------
	 * 		public int getDefense
	 *  ---------------------------------------------------------
	 * Retourne la valeur de defense.
	 * @return bonus de defense
	 *  ------------------------------------------------------ */
	public int getDefense(){
		return getCaract("defense");
	}

	
	/** ----------------------------------------------------------
	 * 		public int getLeader()
	 *  ----------------------------------------------------------
	 * Retourne le leader.
	 * @return leader (-1 si aucun)
	 *  ---------------------------------------------------------- */
	public int getLeader() {
		return leader;
	}

	
	/** ----------------------------------------------------------
	 * 		public ArrayList<Integer> getEquipe
	 *  ----------------------------------------------------------
	 * Retourne la liste des personnages de l'equipe.
	 * @return equipe
	 * -------------------------------------------------------- */
	public ArrayList<Integer> getEquipe() {
		return equipe;
	}
	

	@Override
	/** ----------------------------------------------------------
	 * 		public String toString
	 *  ----------------------------------------------------------
	 *  Cette méthode détermine l'affichage des différentes stats
	 *  ----------------------------------------------------------
	 * @return string
	 * ------------------------------------------------------- */
	public String toString(){
		String lead = (leader != -1)? ", leader: " + leader: "";
		String eq = "";
		
		if(!equipe.isEmpty()) {
			eq += ", equipe: ";
			
			for(int i = 0; i < equipe.size(); i++) {
				eq += equipe.get(i) + " ";
				
				if(i < equipe.size() - 1) {
					eq += " ";
				}
			}
		}
		
		return super.toString() + "[" + "HP : " + getHP() + ", For : " +  getForce() + ", Cha :" + getCharisme() 
				+  ", Def : " + getDefense() + ", Vit : " + getVitesse() +  lead + eq + "]";
	}
	
	
	
	
	@Override
	/** ----------------------------------------------------------
	 * 		public void setLeader()
	 *  ----------------------------------------------------------
	 * Cette méthode définie qui est le leader
	 * ------------------------------------------------------- */
	public void setLeader(int ref) throws RemoteException {
		leader = ref;
	}
	
	@Override
	/** ----------------------------------------------------------
	 * 		public void clearLeader()
	 *  ----------------------------------------------------------
	 * Cette méthode supprime le leader
	 * ------------------------------------------------------- */
	public void clearLeader() throws RemoteException {
		leader = -1;
	}
	
	@Override
	/** ----------------------------------------------------------
	 * 		public void ajouterEquipe(int ref)
	 *  ----------------------------------------------------------
	 * Cette méthode permet d'ajouter un personnage dans l'équipe
	 * -----------------------------------------------------------
	 * @param int ref
	 * ------------------------------------------------------- */
	public void ajouterEquipe(int ref) throws RemoteException {
		equipe.add((Integer) ref);
	}

	@Override
	/** ----------------------------------------------------------
	 * 		public void enleverEquipe(int ref)
	 *  ----------------------------------------------------------
	 * Cette méthode permet d'enlever un personnage dans l'équipe
	 * -----------------------------------------------------------
	 * @param int ref
	 * ------------------------------------------------------- */
	public void enleverEquipe(int ref) throws RemoteException {
		equipe.remove((Integer) ref);
	}

	@Override
	/** ----------------------------------------------------------
	 * 		public void enleverTouteEquipe()
	 *  ----------------------------------------------------------
	 * Cette méthode permet d'enlever toute l'équipe
	 * -------------------------------------------------------- */
	public void enleverTouteEquipe() throws RemoteException {
		equipe.clear();
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
	
	/**
	 * On evite les combats perdus d'avance
	 * On va chercher les combat gagnes d'avance et essayer de les faire
	 * On essaie de faire des coups d'etat si on les gagne
	 */
	public void strategie(VueElement ve, Hashtable<Integer, VueElement> voisins, Integer refRMI) throws RemoteException {
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

			if (distPlusProche <= 2) { // si suffisamment proches
				if (elemPlusProche instanceof Potion) { // potion
					parler("J'erre...", ve);
					deplacements.seDirigerVers(0); // errer

				} else { // personnage
						//On regarde si le combat est perdu d'avance
						if (this.getCaract("charisme") <= ve.getControleur()
								.getArene().consoleFromRef(refPlusProche)
								.getElement().getCaract("force")) {
							parler("Je fuis " + refPlusProche, ve);
							this.fuir(ve, cible, deplacements);
						}
						else if (this.getCaract("charisme") <= ve.getControleur()
								.getArene().consoleFromRef(refPlusProche)
								.getElement().getCaract("charisme")) {
							parler("Je fuis " + refPlusProche, ve);
							this.fuir(ve, cible, deplacements);
						}
						else{
							// Le combat semble gagner ==> duel
							parler("Miaou ! duel avec " + refPlusProche, ve);
							actions.interaction(refRMI, refPlusProche, ve
									.getControleur().getArene());
						}
					}
			} else { // si voisins, mais plus eloignes
					//On regarde si le combat est perdu d'avance
					if (this.getCaract("charisme") <= ve.getControleur()
							.getArene().consoleFromRef(refPlusProche)
							.getElement().getCaract("force")) {
						parler("Je fuis " + refPlusProche, ve);
						this.fuir(ve, cible, deplacements);
					}
					else if (this.getCaract("charisme") <= ve.getControleur()
							.getArene().consoleFromRef(refPlusProche)
							.getElement().getCaract("charisme")) {
						parler("Je fuis " + refPlusProche, ve);
						this.fuir(ve, cible, deplacements);
					}
					else{
						parler("Je vais vers mon voisin " + refPlusProche, ve);
						deplacements.seDirigerVers(refPlusProche);
					}

			}
		}
	}
}