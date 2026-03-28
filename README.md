# Library Management API

Mini-projet WS REST - MBDS 2025-2026  
Cette application est une API REST permettant de gérer une bibliothèque : livres, auteurs, catégories, utilisateurs et emprunts.  
Elle illustre les concepts de CRUD, relations entre entités, authentification JWT, gestion des rôles, filtres de sécurité et documentation Swagger.

## Objectifs
Ce projet a pour but de fournir une API REST complète pour la gestion d’une bibliothèque.  
Il permet aux utilisateurs d’ajouter, modifier, supprimer et consulter les entités suivantes :
- **Books** (livres)
- **Authors** (auteurs)
- **Categories** (catégories)
- **Users** (utilisateurs)
- **Borrows** (emprunts)

Les relations entre entités sont prises en charge :
- Un auteur peut écrire plusieurs livres (relation 1..n).
- Un livre peut appartenir à plusieurs catégories (relation n..n).

Deux endpoints plus complexes sont également implémentés :
- Liste des livres empruntés par un utilisateur.
- Nombre d’emprunts par catégorie.

## Fonctionnalités techniques
- **CRUD** sur toutes les entités.
- **JWT Authentication** : connexion sécurisée avec token.
- **Role Management** : distinction entre `ADMIN` et `USER`.
- **Filters** : sécurisation des endpoints selon le rôle.
- **HATEOAS** : enrichissement des réponses avec des liens.
- **Base H2** : base de données en mémoire pour tester rapidement.
- **Swagger UI** : documentation interactive des endpoints.

## Installation et lancement
1. Cloner le projet :
   ```bash
   git clone https://github.com/AngotyFitia/library-management.git
   cd library-management
