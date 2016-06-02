# Projet S6 - Renju

> 
 - Mathieu BOCHATON
 - Guillaume CHAPUT
 - Clément HERESAZ
 - Raphaël JACQUET
 - Julien NAVAILS
 - Benjamin ROHAUT

----------

Projet de jeu de plateau japonais avec Java Swing

## Equipe

IHM

* Mathieu
* Clément

IA

* Raphaël
* Julien

Fonctionnalités

* Benjamin
* Guillaume

> Bien sûr, les équipes pourront varier en fonction.

## A faire

 > IHM
 
- [ ] Finir l'affichage
- [ ] Améliorations diverses
- [ ] Rapport IHM
- [x] Fin de partie
- [x] Règles
- [x] Nombres de coup adpaté à Anuler / Refaire

> IA

- [x]  Faire le Minimax
- [x] Ajouter les tabous
- [ ] Tester les autres IA
- [x] Faire le IA vs IA
- [ ] Rapport IA


> Fonctionnalités

- [x] Intégrer les fonctions de sauvegarde
- [ ] Faire le Compte-Rendu
- [ ] Mettre en place les animations diverses (victoire/ défaite ...)
- [ ] Revoir le code
- [x] Sauvegarder / Charger
- [x] Problème Annuler / Refaire au Chargement de partie
- [x] Subrillance joueurs
- [x] Historique


### Prochains Rendez-vous



### Rendez-vous passés

Audit IHM
:  Mardi 17 Mai 11h15

Audit IA
: Vendredi 20 Mai 10h

2ème Audit IHM
: Lundi 23 Mai 11h30



### Diagramme UML 
* Version 1

![PlantUML model](http://plantuml.com/plantuml/png/RLB1ReGW4Btp5Vo0xTs32MdQmpRPklRYpL0SksmGDWXDFwdzZlsnGa5GryHacFVUp9kPI1Ypm4LHlTz-1J33bUmkoeBLKucFodw14D3LL1iEw5vJL2GqA743Hbczfrc8sjqU1x0bVdj5BZhnoN9ZG7kn1we6c1f7aCnZOP8bXXRl8lPgLs30ZagkKR_TGx2sF5Xj5avsCnvpjD5bsosHhU-OlOEdGSW7bRsIQO2OenYpuGnaeuIaeyUkkAYm3oL-xhdthJeYfHMW7niOADUmLZsT5V_kQa4DKBkco31mTgQVcmetB1rlFqR35DR0XFVGkUyIobnMBI2qUAUDOc5NQtLLKwh2r_ySpKI-G5WJxxhvxE2DikY5IqV6domJYMd_0000)

```sequence
Accueil->Sélection_joueurs: Nouvelle_Partie
Sélection_joueurs->Accueil: Retour
Sélection_joueurs-->Accueil: X
Sélection_joueurs->Jeu: OK
Jeu-->Quitter: X
Jeu->Recommencer: Partie>Recommencer
Recommencer-->Jeu: X
Recommencer->Jeu: Non
Recommencer->Nouv.Jeu: Oui
Jeu->Nouvelle_Partie: Partie>Nouvelle Partie
Nouvelle_Partie-->Jeu: X
Nouvelle_Partie->Jeu: Non
Nouvelle_Partie->Sélection_joueurs: Oui
Jeu->Abandon: Partie>Abandon
Abandon-->Jeu: X
Abandon->Jeu: Non
Abandon->Accueil: Oui
Jeu -> Jeu : Edition>_Annuler/Refaire
Jeu -> Jeu : Choix_Plateau
Jeu -> Jeu : Affichage
Jeu -> Jeu : Paramètres
Jeu-> Aide_de_jeu : Aide>Aide_de_jeu
Aide_de_jeu->Jeu: X
Jeu-> A_Propos : Aide>A propos
A_Propos->Jeu: X
Accueil -> Aide_de_jeu : Aide_de_Jeu
Accueil -> Fin : Quitter
Accueil --> Fin : X

```
