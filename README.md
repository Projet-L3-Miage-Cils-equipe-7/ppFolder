##ppFolder

## Introduction

Le projet ppFolder a été réalisé par Yacine BOUFALA, Romain HUISDACK et Rayan KAHLFOUN.
	ppFolder est une application qui a été conçu dans le but d'approfondir nos connaissances en Java à travers un 
	projet pédagogique encadré par madame Nadia ABCHICHE-MIMOUNI.
	La réalisation de ce projet a commencé le mardi 12 janvier 2021 et s'est terminée le jeudi 25 mars 2021.
	
L'application est ppFolder est une application qui a pour but de faciliter la vie de ses utilisateurs pour organiser 
	des fichiers à l'intérieur d'un répertoire en fonction de leurs extensions.
	L'application possède des extensions par défaut qui sont intégrées à l'intérieur de la liste des règles. Une règle 
	est une liste d'extension pouvant contenir au moins une extension et qui n'a pas de limite. Ainsi on peut trier 
	des fichiers dans un nouveau repertoire contenant plusieurs fichiers avec des extensions différentes selon le choix 
	de l'utilisateur.
	
## Development team

<a href="https://github.com/Mockingbird01001">
	<img src="https://avatars.githubusercontent.com/u/63344951?v=4" title="Yacine Boufala" width="80" height="80">
</a> &nbsp;

<a href="https://github.com/rena-hsck">
	<img src="https://avatars.githubusercontent.com/u/70547187?v=4" title="Romain Huisdack" width="80" height="80">
</a> &nbsp;

<a href="https://github.com/rayanus">
	<img src="https://avatars.githubusercontent.com/u/58557771?v=4" title="Rayan Khalfoun" width="80" height="80">
</a> &nbsp;

## Getting Started

Tout d'abord, on indique le chemin du dossier à trier, une fois le chemin indiquer et validé on se retrouve sur l'interface de travail, elle contient à droite plusieurs règles entrées par défaut qu'il est bien entendu possible de supprimer avec le bouton "supprimer règle" ainsi que d'en ajouter de nouvelles. Pour ajouter une extension, il faut d'abord l'ajouter avec le bouton "ajouter règle" puis saisir la règle et valider sa saisie. une règle comme nous les appelons est une extension de fichiers à prendre en compte dans l'ajout de celle-ci, elle peut être seul ou composer de plusieurs autres séparées par une virgule ", " une fois la règle ajoutée et validée elle est ajoutée parmi celles déjà présentes.

Une partie centrale où nous aurons un aperçu des extensions presentes dans le dossier. Et enfin, une dernière partie ou nous y ajouterons les règles déjà créees au préalable pour que notre tri les prenne en compte. Il est possible d'en seléctionner une  en appuyant sur une règle deja ajoutée. Le systeme n'autorise pas les doublons dans la seléction. C'est-à-dire qu'une règle ne peut être selectionnée qu'une seule et unique fois. On pourra la retirer en cliquant sur celle-ci dans la partie du bas. Le bouton qui lance le tri ne s'active que s'il y'a au moins une règle de selectionnée.
Une fois les règles choisies, il faut lancer le tri. L'application triera ensuite les fichiers dans le dossier indiqué au tout début.

## Changelog 

Execution du programme via la commande:

```shell
java -jar ppFolder.jar
```

### v1.0.9 
- importation des images dans l'executable et modification du code 
- generation d'un ".jar" 

### v1.0.8
- ajout de la classe "Directories" pour la gestions des dossiers et suppression des dossiers vide.

### v1.0.7
- bug sur le controlleur

### v.1.0.6
- modification mineur sur la vue (le panel des extensions disparaissait).

### v1.0.5
- modification des champs de saisis et ajout d'un PlaceHolder

### v1.0.4
- correction du bug sur le tri (prise en compte des fichiers sans extensions).
- ajout de la classe "WrapLayout" pour un effet responsif de l'application.

### v1.0.2 
- correction de la validation du path (detection que c'est un dossier et qu'il est non vide).
- ajout des try...catch ainsi que des pop-up d'erreurs.

### v.1.0.0 
- version bêta

## License

This project is licensed under the GPL-3.0 License  - see the [LICENSE.md](LICENSE.md) file for details
