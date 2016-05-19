# Projet S6 - Renju

> 
 - Mattieu BOCHATON
 - Guillaume CHAPUT
 - Clément HERESAZ
 - Raphaël JACQUET
 - Julien NAVAILS
 - Benjamin ROHAUT

----------

Projet de jeu de plateau japonais 

## Equipe
IHM
: Mattieu
: Clément

IA
: Raphaël
: Julien

Fonctionnalité
: Benjamin
: Guillaume

> Bien sûr, les équipes pourront varier en fonction.

### Prochains Rendez-vous
Audit IA
: Vendredi 20 Mai 10h

2ème Audit IHM
: Lundi 23 Mai
### Rendez-vous passés
Audit IHM
:  Mardi 17 Mai 11h15


### Diagramme UML

```sequence
Accueil->Sélection joueurs: Nouvelle Partie
Sélection joueurs->Accueil: Retour
Sélection joueurs-->Accueil: X
Sélection joueurs->Jeu: OK
Jeu-->Quitter: X
Jeu->Recommencer: Partie > Recommencer
Recommencer-->Jeu: X
Recommencer->Jeu: Non
Recommencer->Nouv. Jeu: Oui
Jeu->Nouvelle Partie: Partie > Nouvelle Partie
Nouvelle Partie-->Jeu: X
Nouvelle Partie->Jeu: Non
Nouvelle Partie->Sélection joueurs: Oui
Jeu->Abandon: Partie > Abandon
Abandon-->Jeu: X
Abandon->Jeu: Non
Abandon->Accueil: Oui
Jeu -> Jeu : Edition > Annuler / Refaire
Jeu -> Jeu : Choix Plateau
Jeu -> Jeu : Affichage
Jeu -> Jeu : Paramètres
Jeu-> Aide de jeu : Aide > Aide de jeu
Aide de jeu->Jeu: X
Jeu-> A Propos : Aide > A propos
A Propos->Jeu: X
Accueil -> Aide de jeu : Aide de Jeu
Accueil -> Fin : Quitter
Accueil --> Fin : X

```
