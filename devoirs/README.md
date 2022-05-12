# README

Cette application est un prototype de VaxTodo permettant de...

## Fonctionnalités

- Réserver une période de vaccination
- Gérer les comptes des visiteurs, bénévoles
- ...

## Manuel d'utilisation

Pour utiliser l'application, il vous faut exécuter la commande suivante: `java App` (ou autre commande).
Étant un prototype, nous avons inclus un jeu de données afin de tester l'application.
À l'ouverture, vous devez vous connecter en tant qu'employé ou bénévole. Ceci vous donnera accès
au menu principal propre au rôle.

### Données incluses dans l'application

- Rendez-vous
  - 000001;foret;aura;2021-11-05;10:00;1
  - 000002;buisson;paul;2021-12-02;14:00;2
- Visiteur
  - 202010250001;cortez;juan;1990-01-13;juan.cortez@gmail.com;5147836802
  - 202004280010;hannoune;isabelle;1994-10-06;isa.hannoune@gmail.com;4382835821
- ...

### Connexion

Pour se connecter à l'application, veuillez utiliser un des identifiants suivants:

- Rôle de l'employé
  - username: argo | password: argopass
  - username: anna | password: annapass
- Rôle du bénévole
  - username: benoit | password: benoitpass
  - username: viola | password: violapass

### Menu principal (Employé)

À partir du menu principal, dans le rôle de l'employé, vous pouvez choisir l'une des options suivantes en tapant le chiffre correspondant.
En tout tant vous pouvez taper 0 pour revenir au menu principal.

- [1] Gestion des visiteurs: Accédez à la liste des visiteurs et ajouter, modifier ou supprimer un visiteur.
- [2] Envoi courriel de suivi: Envoyez un courriel de suivi à un visiteur pour lui rappeler
- [3] Consultation du calendrier: Accédez au calendrier et cherchez les rendez-vous à venir
- ...

#### Gestion des visiteurs

Dans cette section, vous pouvez effectuer les actions suivantes en tapant le chiffre correspondant.
Suivez les instructions à l'écran pour compléter la tache

- [1] Créer un compte
- [2] Modifier un compte
- [3] Supprimer un compte
- [4] Chercher un compte
- ...

### Menu principal (Bénévole)
