

import java.rmi.RemoteException;
import java.util.Random;

import controle.Console;
import element.Charismatique;
import element.Personnage;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestCharismatiqueAlea {

	public static void main(String[] args) {
		
		try {
			int port = 5099; // par defaut, 5099
			if (args.length > 0) {
				port = Integer.parseInt(args[0]);
			}
			
			String ipArene = "localhost"; // par défaut, localhost
			if (args.length > 1) { 
				ipArene = args[1];
			}
			
			String nom = "Felix";
			if (args.length > 2) { 
				nom = args[2];
			}
			
			Random r = new Random();
			Personnage bidule = new Charismatique(nom);
			new Console(bidule, r.nextInt(100), r.nextInt(100), port, ipArene);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}