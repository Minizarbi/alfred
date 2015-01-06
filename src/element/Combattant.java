package element;

/**
 * Le combattant est un personnage avec plus de force. Il a le meme comportement
 * qu'un personnages.
 */
public class Combattant extends Personnage {

	private static final long serialVersionUID = 1L;

	public Combattant(String nom) {
		super(nom, 400, 50);
	}

	/*
	 * public void strategie(VueElement ve, Hashtable<Integer,VueElement>
	 * voisins, Integer refRMI) throws RemoteException {
	 * 
	 * }
	 */
}
