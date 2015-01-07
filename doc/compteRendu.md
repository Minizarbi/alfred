# Lundi

- Nous avons rencontré quelques difficultés sans vraiment savoir à chaque fois si les bugs venaient de nous ou non.
- Après avoir étudié en partie le code, nous avons créé 3 potions et 5 personnages avec chacun une stratégie et des caractéristiques spécifiques.
- Nous avons aussi ajouté un test pour le ramasseur.
- Nous avons ajouté une méthode pour fuir dans Deplacements.
- Nous avons ajouté une méthode pour trouver la potion la plus proche dans Calculs.

# Mardi

- Nous avons amélioré et débuggé la stratégie du ramasseur et du fuyard.
- Nous avons rajouté le personnage téléporteur.
- Nous avons modifié l'IHM pour afficher des images selon l'élément à afficher et utiliser un fond blanc.

# Mercredi

- Nous avons posé des questions sur les règles et le sujet, en attendant des réponses des enseignants.
- Nous avons réfléchi a des stratégies qui pourraient bien fonctionner comme celle du Fuyard en exploitant au maximum les règles.
- Nous avons commencer a coder un personnage : Fuyard

# Jeudi

- Finir la programmation
- Finir le rapport

## Personnages & Strategies
Version locale :
	Combattant:	saisPlus
	Fuyard:	Tente de fuire (sans sortir de l'ecran) des qu'il vois un ennemis autour de lui. (Probleme : parfois il effectue des aller-retours (dans les coins) et n'arrive pas a fuir). Si personne autour, il ne bouge pas.
	Maitre: A des stats tres elevees et effectue la strategies d'un Personnage (a verifier !!!!)
	Seducteur: S'il voit un ennemis avec un charisme inferieur (egal ????) au sien, il va tenter d'aller en duel, sinon il fuit la Personne. Si personne, il erre (a verifier !!!!)
	Teleporteur: (a verifier !!!!) il se teleporte aleatoirement sur l'arene des qu'il apercoit un ennemis (il ne peut pas tomber sur une case deja prise). Sinon il est immobile (ou random ????)
	MeSouviensPlusDuDernier:

Version reseau :
	Fuyard: fuit continuellement vers l'exterieur de l'arene (fuis un ennemis en priorite s'il en voit un) (A t on le droit ????)
	
## Package Interaction
(Version locale)
	Ajout d'une methode fuir afin de fuir une reference donnee (Classe Deplacements)
	Ajout d'une methode fuir afin de se teleporter aleatoirement sur l'arene en allant sur une case vide (Classe Deplacements)
	Ajout d'une mthode shifumi afin d'avoir une autre facon de combattre (Classe Duel)		Fonctionne-t-elle ????
	
## Package IHM
(Version locale)
	Ajout d'images : chaque Element a sa propre image sur l'arene
	