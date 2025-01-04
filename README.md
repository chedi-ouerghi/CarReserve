# CarReserve

CarReserve est une application backend de réservation de voitures développée avec Spring Boot. Elle permet aux utilisateurs de gérer les réservations de véhicules via une API RESTful. Ce projet inclut un système d'authentification avec plusieurs rôles, notamment administrateur, utilisateur et modérateur. L'application utilise une base de données MySQL pour stocker les informations relatives aux réservations, ainsi que les procédures et événements nécessaires à la planification du système de notification.

## Fonctionnalités

- **API RESTful** : Fournit des endpoints pour gérer les réservations de voitures, incluant la création, la mise à jour et la suppression des réservations.
- **Authentification sécurisée** : Intègre un système d'authentification basé sur JWT (JSON Web Tokens) permettant aux utilisateurs de se connecter et d'accéder aux fonctionnalités selon leur rôle (admin, utilisateur, modérateur).
- **Gestion des utilisateurs** : Permet aux administrateurs de gérer les comptes utilisateurs et leurs rôles.
- **Notifications** : Implémente un système de notification pour informer les utilisateurs des mises à jour concernant leurs réservations.
- **Gestion des erreurs** : Fournit des réponses appropriées en cas d'erreurs lors des opérations sur l'API.

## Prérequis

- Java 11 ou version ultérieure
- Maven
- Base de données MySQL

## Installation

1. Clonez le dépôt :
git clone https://github.com/chedi-ouerghi/CarReserve.git

2. Accédez au répertoire du projet :
cd CarReserve

3. Installez les dépendances :
mvn install


4. Configurez votre base de données dans le fichier `src/main/resources/application.properties`.
   Assurez-vous d'inclure les informations nécessaires pour établir la connexion à votre base de données MySQL.

6. Exécutez l'application :
mvn spring-boot:run


## Fichiers importants

- **application.properties** : Ce fichier contient la configuration nécessaire pour la connexion à la base de données et d'autres paramètres de l'application.
- **pom.xml** : Le fichier Maven qui gère les dépendances et la configuration du projet.

### Dépendances dans `pom.xml`

Voici les dépendances clés à inclure dans votre fichier `pom.xml` :

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.8.RELEASE</version>
		<relativePath />
	</parent>
	<groupId>com.reservations</groupId>
	<artifactId>spring-boot-security-jwt</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-boot-security-jwt</name>
	<description>Demo project for Spring Boot Security - JWT reservations</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.11.5</version>
			<scope>runtime</scope>
		</dependency>

		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-api</artifactId>
			<version>2.0.0</version>
		</dependency>

		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-simple</artifactId>
			<version>2.0.0</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<!-- Plugin Spring Boot -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>

			<!-- Plugin Maven Compiler -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>

			<!-- Plugin Maven Surefire -->
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.22.2</version>
			</plugin>
		</plugins>
	</build>
</project>
