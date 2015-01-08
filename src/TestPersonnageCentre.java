


import java.rmi.RemoteException;
import java.util.Random;

import controle.Console;
import element.Charismatique;
import element.Personnage;

/**
 * Test de la Console avec un Element qui s'ajoute a l'Arene (apres lancement Arene et IHM). A lancer en plusieurs exemplaires.
 */
public class TestPersonnageCentre {

	public static void main(String[] args) {

		try {
			int port = 5099; // par defaut, 5099
			if (args.length > 0) {
				port = Integer.parseInt(args[0]);
			}
			
			String ipArene = "localhost"; // par defaut, localhost
			if (args.length > 1) { 
				ipArene = args[1];
			}
			
			String nom = "Alfred";
			if (args.length > 2) { 
				nom = args[2];
			}
			
			Random r = new Random();
			
			Personnage bidule = new Personnage(nom,  r.nextInt(30), r.nextInt(30), r.nextInt(29)+1, 1, r.nextInt(10));
			
			new Console(bidule, 40, 40, port, ipArene);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}