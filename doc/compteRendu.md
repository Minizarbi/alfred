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

- Nous avons décidé d'utiliser una stratégie basée sur le charisme et la fuite plutôt que juste la fuite.
- Nous avons implanté cette stratégie avec le personnage : Charismatique.
- Nous avons effectué quelques modifications mineures dans le code comme par exemple l'ajout d'un paramètre nom sur les tests.

## Personnages & Strategies
### Version locale
#### Combattant
Il effectue la stratégie de base mais possède plus de force qu'un personnage normal.

#### Fuyard
Tente de fuir, sans sortir de l'arène, dés qu'il voit un ennemi autour de lui. (Probleme : parfois il effectue des aller-retours (dans les coins) et n'arrive pas a fuir). Si il n'y a personne autour, il ne bouge pas.

#### Maitre
A des caractéristiques très élevées et effectue la stratégies d'un Personnage sans ramasser les potions car il n'en a pas besoin.

#### Seducteur
S'il voit un ennemis avec un charisme inférieur au sien, il va tenter d'aller en duel, sinon il fuit la personne. S'il n'a pas de voisins, il erre.

#### Teleporteur
Il se téléporte aléatoirement sur une case vide de l'arène des qu'il aperçoit un ennemi, sinon il reste immobile. Il ramasse aussi les potions quand il en trouve.

#### Ramasseur
Il erre à la recherche de potions et les ramasses dés qu'il est à portée. Il continue à errer s'il aperçoit un autre personnage.

### Version réseau
#### Fuyard : Il fuit continuellement vers l'éxterieur de l'arene (fuit un ennemi en priorité s'il en voit un).

#### Charismatique : Il essaye de convertir les personnages qu'il peut convertir, essaye de faire des coups d'état, et fuit les personnages avec lesquels il risque de perdre le duel.

## Potions
### Version locale
#### Charisme
Elle donne 50 de charisme quand on la ramasse.

#### Charisme et force
Elle donne 50 de charisme et 50 de force quand on la ramasse.

#### Force
Elle donne 50 de force quand on la ramasse.

## Package Interaction
### Version locale
- Ajout d'une méthode fuir afin de fuir une reference donnee (classe Deplacements).
- Ajout d'une méthode fuir afin de se téléporter aléatoirement sur l'arène en allant sur une case vide (Classe Deplacements).
- Ajout d'une méthode shifumi afin d'avoir une autre facon de combattre (Classe Duel).
	
## Package IHM
### Version locale
	Ajout d'images : chaque Element a sa propre image sur l'arene
	