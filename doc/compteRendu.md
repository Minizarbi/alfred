
![image qui montre](le chemin)

Introduction :

Dans le cadre de nos études en L3 Informatique, nous avons eu l'occassion d'avoir à développer un projet de programmation (JAVA).
Le but final étant de confronter dans une arène les personnages de chaque équipe (étudiants de la licence) dans une arène.
Nous devions donc réfléchir à des stratégies afin de remporter ce tournoi !
Une occassion d'avoir un apperçu sur le monde du travaille et de développer notre expérience dans le langage JAVA.
Ce rapport sera constitué de trois parties : une premiere partie explicant le sujet, une seconde partie détaillant la version locale et une dernière partie explicant la version collective.

# Projet

##Sujet

A partir d'un code existant, nous devions ajouter des Personnage et Potions ayant certaines caractéristiques et stratégies.
Nous avions à disposition un Personnage d'exemple, une arène, un serveur, une IHM, de quoi avoir un programme fonctionnel dès le départ.
L'accent était donc mit sur la réutilisation de code et l'application de nos connaissances JAVA acquise lors du premier semestre de notre 3ème année de Licence.
Le développement devait (de préférence) s'effectuer en binôme (Nous pouvions aussi choisir de le faire seul ou à trois).
Nous avions deux versions à faire : une version locale et une version collective.
Chaque version sera détaillée plus loin.

##Choix

Avec mon binôme, nous avons décider d'utiliser le gestionnaire de version nommé GIT afin de pouvoir développer notre application de manière simple et efficace.
En effet, GIT permet de "partager" et de gérer les différentes versions du code.
La stratégie utilisée pour le tournois est l'utilisation du charisme : 99 charisme et 1 de vie.
Notre personnage fuit les plus fort et tente d'aller en duel contre les personnages avec lesquels il gagne.
Il tente des coups d'états afin de ne pas avoir de leader.

## Personnages

Chaque personne a des caractéristique bien précises.
Dans la version locale, un personnage a, en plus d'un nom, une quantité de :
- force : permet de tuer une autre personne
- charisme : permet d'ajouter ou de se faire ajouter à une équipe
- vie : 0 ou 1 qui dit si un personnage est en vie ou non
- leader : -1 si on en a pas, sinon la référence de notre leader
	 
Dans la version collective, un personnage a, en plus d'un nom, une quantité de :
- force : permet de tuer une autre personne
- charisme : permet d'ajouter ou de se faire ajouter à une équipe
- hp : nombre de points de vie du personnage
- vitesse : distance de vision d'un personnage (10 à 20)
- defence : permet de réduire les dégats subits (valeur de 0 à 60)
- leader : -1 si on en a pas, sinon la référence de notre leader

De plus, dans la version collective, afin qu'in personnage puisse arriver dans l'arène, il faut que ses caractéristiques réspectent l'équation suivante :
Force + Charisme + Vie + (10/60)*Defence <= 100

## Potion

Chaque potion permet d'augmenter (ou abaisser) les caractéristiques d'un personnage qui la rammasse.
Exemple : une potion ayant 50 de force va rajouter 50 de force à la personne la rammassant.


# Version locale

La version locale est une version dans laquelle nous avions le droit d'effectuer toutes les modifications de code souhaitées dans toutes les classes du projet.
Nous n'étions pas limité.
L'objectif étant de créer au moins 5 personnages, 3 potions et d'ajouter quelques méthodes dans le package "interaction" (package gérant principalement les duels et les déplacements).

## Diagramme de classes

Nous avons utilisé le plugin ObjetAid afin de générer le diagramme de classes.

#image#

## Personnages & Strategies

### Combattant
Le combattant est un personnage qui effectue la stratégie de base (mise à disposition dans le projet qu'on nous a donné) mais possède plus de force qu'un personnage de base.

### Fuyard
Un fuyard a des caractéristiques relativement faibles. Il tente donc de fuir (sans sortir de l'arène) dés qu'il voit un ennemi autour de lui. Si il n'y a personne autour, il ne bouge pas.

### Maitre
Ce personnage a des caractéristiques très élevées et effectue la stratégies d'un personnage de base (Il erre ou effectue des duels s'il voit quelqu'un) sans ramasser les potions (il n'en a pas besoin au vu de ses caractéristiques).

### Seducteur
Le séducteur, s'il voit un ennemi avec un charisme inférieur au sien, il tente de le prendre en duel, sinon il fuit la personne. S'il n'a pas de voisins, il erre.

### Teleporteur
Il se téléporte aléatoirement sur une case vide de l'arène des qu'il aperçoit un ennemi, sinon il reste immobile. Il ramasse aussi les potions quand il en trouve.

### Ramasseur
Le ramasseur erre à la recherche de potions et les ramasses dés qu'il est à portée. Il continue à errer s'il aperçoit un autre personnage.


## Potions

### Charisme
Elle donne 50 de charisme quand on la ramasse.

### Charisme et force
Elle donne 50 de charisme et 50 de force quand on la ramasse.

### Force
Elle donne 50 de force quand on la ramasse.


## Ajout de méthodes dans le package Interaction

- Ajout d'une méthode fuir afin de fuir une reference donnee (classe Deplacements).
- Ajout d'une méthode fuir afin de se téléporter aléatoirement sur l'arène en allant sur une case vide (Classe Deplacements).
- Ajout d'une méthode shifumi afin d'avoir une autre facon de combattre (Classe Duel).
	
## Modification du package IHM

Nous avons remplacer l'affichage de base de l'IHM.
Tel quel, les éléments de l'arène (Personnages & Potions) sont représentés par des cercles (pleins) de couleur.
Afin d'avoir une représentation plus jolie et de pouvoir distinguer facilement chaque element, nous avons appliqué une image à chaqu'un de ces éléments.


# Version collective

La version collective est une version en réseau. Les modification que l'on peut faire au code sont limitées. Le but est de créer un personnage et de développer sa stratégie afin qu'il gagne la partie.
C'est cette version qui est utilisée pour le tournois. Chaque groupe a le droit de faire apparaitre un et un seul personnage dans l'arène de tournois.

## Personnages & Strategies

### Fuyard

Le fuyard fuit continuellement vers l'éxterieur de l'arene (fuit un ennemi en priorité s'il en voit un).

### Charismatique

Ce personnage fuit les personnages avec lesquels il risque de perdre le duel.
Il essaye de convertir les personnages qu'il peut convertir (il a un charisme qui le lui permet).
S'il se fait capturé (il a donc un leader), il essaye de faire des coups d'état.

# Conclusion

Ce projet nous a permis de mettre en pratique nos connaissances JAVA.
En effet, en plus d'avoir l'occassion de coder, nous avons dû lire, comprendre et utilisé un code déjà écrit par d'autres personnes.
Nous avons donc compris l'importance des commentaires, sans lesquels il serait très long et complexe de pouvoir maintenir et utiliser du code existant.
Ce projet était motivant car il aboutissait sur un tournois final entre étudiants. Cela nous a donc poussé à trouver une stratégie permettant de gagner la partie, et donc à développer un code performant.
Le fait d'avoir codé en binôme nous a permis de voir les avantages et inconvéniants.
Par exemple, trouver comment se répartir les tâches et comment réunir le code de chacun(solution prise : GIT).
De plus, si l'un était bloqué, le fait de pouvoir s'appuyé sur son binôme permettait de se débloquer (et donc de développer) plus rapidement.
Cette expérience a été bénéfique pour nous deux.


#Annexe : travail journalier
	
	
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