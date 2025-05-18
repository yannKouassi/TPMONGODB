# 🎬 Application Java MongoDB - Gestion de Films

Ce projet est une application Java permettant d’insérer, afficher, modifier et supprimer des films dans une base de données **MongoDB**.  
L’application fonctionne en mode console avec l’aide de l’utilisateur.

---

## 🚀 Fonctionnalités

- 📥 **Insertion** de films avec :
    - Nom
    - Année
    - Réalisateur (nom, prénom)
    - Acteurs (liste de noms/prénoms)
    - Catégories (liste)

- 🔍 **Affichage** de films selon un critère (nom, année...)

- ✏️ **Modification** de films selon un critère

- ❌ **Suppression** de films

---

## 🛠️ Technologies utilisées

- Java 8+
- MongoDB
- MongoDB Java Driver
- IDE recommandé : IntelliJ ou Eclipse
- Mongo Compass ou Terminal Mongo

---

## 📂 Structure du projet

```bash
src/
├── AfficherFilms.java
├── CinemaApp.java
├── ConnexionMongo.java
├── InsererFilm.java
├── Main.java
├── MongoModifier.java
├── MongoSupprimer.java
