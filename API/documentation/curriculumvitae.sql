-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : lun. 14 avr. 2025 à 11:07
-- Version du serveur :  10.3.29-MariaDB
-- Version de PHP : 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `curriculumvitae`
--

-- --------------------------------------------------------

--
-- Structure de la table `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `nb_street` int(11) DEFAULT NULL,
  `street` varchar(250) DEFAULT NULL,
  `complement` text DEFAULT NULL,
  `city_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `address`
--

INSERT INTO `address` (`id`, `nb_street`, `street`, `complement`, `city_id`) VALUES
(1, 730, 'Rte de l\'Eponnet', '', 7),
(2, 95, 'Avenue du Recteur Pineau', '', 3),
(3, 5, 'Avenue Blaise Pascal', '', 4),
(4, 11, 'Avenue de la Gare des Eaux-Vives', '', 2),
(5, 4, 'Rue du Champs Blanchod', '', 6),
(6, 159, 'Avenue du Buet', '', 1),
(7, NULL, NULL, 'Cité Scientifique', 5);

-- --------------------------------------------------------

--
-- Structure de la table `city`
--

CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `zip` int(100) DEFAULT NULL,
  `country_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `city`
--

INSERT INTO `city` (`id`, `name`, `zip`, `country_id`) VALUES
(1, 'Bonneville', 74130, 1),
(2, 'Genève', 1201, 2),
(3, 'Poitiers', 86000, 1),
(4, 'Clermont-Ferrand', 63000, 1),
(5, 'Villeneuve-d\'Ascq', 59650, 1),
(6, 'Plan-les-Ouates', 1212, 2),
(7, 'Ayse', 74130, 1);

-- --------------------------------------------------------

--
-- Structure de la table `country`
--

CREATE TABLE `country` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `country`
--

INSERT INTO `country` (`id`, `name`) VALUES
(1, 'France'),
(2, 'Suisse');

-- --------------------------------------------------------

--
-- Structure de la table `establishement`
--

CREATE TABLE `establishement` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `establishement`
--

INSERT INTO `establishement` (`id`, `name`, `address_id`) VALUES
(1, 'Université', 2),
(2, 'IUT', 3),
(3, 'Fasteris', 5),
(4, 'Medisupport', 4),
(5, 'Crazy Round', 6),
(6, 'Centrale Lille', 7),
(7, 'International Scrum Institute', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `experience`
--

CREATE TABLE `experience` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `date_begining` date DEFAULT NULL,
  `date_ending` date DEFAULT NULL,
  `establishment_id` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `mission` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `is_formation` tinyint(1) NOT NULL DEFAULT 0,
  `person_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `experience`
--

INSERT INTO `experience` (`id`, `name`, `date_begining`, `date_ending`, `establishment_id`, `city_id`, `mission`, `description`, `is_formation`, `person_id`) VALUES
(1, 'Gestion familliale, formation continue et projets personnels', '2021-01-01', NULL, NULL, 7, NULL, 'Gérer des taches familliales, planification et organisation quotidienne\r\nSuivre les formations SpringBoot, Test Unitaires, API REST, et Angular (OpenClassRoom)\r\nConcrétiser des projets personnels de rénovation d\'intérieur: rénovationet création d\'un sous-sol', 0, 1),
(2, 'Chef d\'entreprise', '2018-03-01', '2020-12-31', 5, 1, 'Concrétiser un projet entrepreunarial et créer une entreprise', 'Créer et gérer un centre de loisirs indoor pour modèles réduits\r\nConcevoir et assurer la maintenance du site web (JOOMLA) permettant aux clients de réserver en ligne\r\nSuivre la gestion administrative, élaborer la stratégie commerciale, créer des partenariats et garantir la relation client', 0, 1),
(3, 'Développeur full-stack', '2016-03-01', '2018-02-01', 4, 2, 'Maintenance, évolution et création d\'applications ASP.NET / C#', 'Maintenance, évolution et suivi de production d\'un système de récolte de résultats d\'analyses faites au cabinet du médecin\r\nEvolutiond\'un système de communication de résultats aux patients Romands via l\'API de MonDossierMedical.ch\r\nCréation d\'un outil de prescription électronique spécifique à certaines analyses génétiques\r\nEvolutions de la sauvegarde et du chargement des réglages d\'un outil de production interne', 0, 1),
(4, 'Bioinformaticien junior', '2011-12-12', '2016-02-12', 3, 2, 'Communication des résultats de séquençage aux clients', 'Préparation et envoie de résultats de séquençage\r\nAutomatisation des processus de création des rapports de résultats de séquençage (en Bash et en R)\r\nDéveloppement d\'un outil web pour la préparation de run de séquençage et de l\'analyse de leur qualité (PHP, JQuery, CSS)', 0, 1),
(5, 'MOOC \"Gestion de projet\" - Parcours avancé', NULL, '2018-05-31', 6, NULL, NULL, 'Notions fondamentales du management et de l\'organisation de projet\r\nOutils avancés de gestion de projet: conception, planification, budget', 1, 1),
(6, 'Scrum Master Accredited Certification', NULL, '2016-06-01', 7, NULL, NULL, NULL, 1, 1),
(7, 'Master Génie Physiologique et Informatique', NULL, '2011-09-16', 1, 3, NULL, 'Programmation (ADA, Java, R, SQL, PL/SQL\r\nLogiciel éducatif sur le SIDA pour les collégiens (JAVA)\r\nVice-président de ProGphy: association proposant des projets informatiques rémunérés aux étudiants', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `experience_developp_skill`
--

CREATE TABLE `experience_developp_skill` (
  `experience_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `experience_developp_skill`
--

INSERT INTO `experience_developp_skill` (`experience_id`, `skill_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 8),
(1, 11),
(1, 19),
(1, 21),
(1, 23),
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(2, 24),
(3, 4),
(3, 6),
(3, 7),
(3, 8),
(3, 11),
(3, 12),
(3, 13),
(3, 14),
(3, 23),
(4, 8),
(4, 9),
(4, 15),
(4, 16),
(4, 17),
(4, 18),
(5, 19),
(5, 23),
(6, 6),
(6, 19),
(7, 1),
(7, 8),
(7, 10),
(7, 19);

-- --------------------------------------------------------

--
-- Structure de la table `hobby`
--

CREATE TABLE `hobby` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `hobby`
--

INSERT INTO `hobby` (`id`, `name`) VALUES
(1, 'Modélisme'),
(2, 'Bricolage');

-- --------------------------------------------------------

--
-- Structure de la table `hobby_description`
--

CREATE TABLE `hobby_description` (
  `id` int(11) NOT NULL,
  `hobby_id` int(11) NOT NULL,
  `descirption` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `hobby_description`
--

INSERT INTO `hobby_description` (`id`, `hobby_id`, `descirption`) VALUES
(1, 1, 'Construction de modèles réduits à piloter et d\'exposition'),
(2, 1, 'Pilotage de modèles réduits de camion en compétition'),
(3, 1, 'Organisation de compétitions'),
(4, 2, 'Rénovation et entretien d\'une maison'),
(5, 2, 'Construction et rénovation de meubles');

-- --------------------------------------------------------

--
-- Structure de la table `language`
--

CREATE TABLE `language` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `language`
--

INSERT INTO `language` (`id`, `name`) VALUES
(1, 'Anglais'),
(2, 'Français'),
(3, 'Espagnol');

-- --------------------------------------------------------

--
-- Structure de la table `nationality`
--

CREATE TABLE `nationality` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `nationality`
--

INSERT INTO `nationality` (`id`, `name`) VALUES
(1, 'Française'),
(2, 'Suisse');

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `img` blob DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `personal_values` text DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `name`, `first_name`, `birthday`, `mail`, `phone`, `img`, `title`, `subtitle`, `personal_values`, `address_id`) VALUES
(1, 'Baroni', 'William', '1986-07-04', 'baroni.will@gmail.com', '+33609215347', NULL, 'Développeur full-stack', '4 ans d\'expérience - Disponible de suite', 'Sens de l\'initiative, Esprit d\'équipe, Sens des responsabilités', 1);

-- --------------------------------------------------------

--
-- Structure de la table `person_has_hobby`
--

CREATE TABLE `person_has_hobby` (
  `person_id` int(11) NOT NULL,
  `hobby_id` int(11) NOT NULL,
  `year_of_practice` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `person_has_hobby`
--

INSERT INTO `person_has_hobby` (`person_id`, `hobby_id`, `year_of_practice`) VALUES
(1, 1, NULL),
(1, 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `person_has_nationality`
--

CREATE TABLE `person_has_nationality` (
  `person_id` int(11) NOT NULL,
  `nationality_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `person_has_nationality`
--

INSERT INTO `person_has_nationality` (`person_id`, `nationality_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `person_speaks_language`
--

CREATE TABLE `person_speaks_language` (
  `person_id` int(11) NOT NULL,
  `langage_id` int(11) NOT NULL,
  `level` varchar(10) NOT NULL DEFAULT 'A1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `person_speaks_language`
--

INSERT INTO `person_speaks_language` (`person_id`, `langage_id`, `level`) VALUES
(1, 1, 'B1'),
(1, 2, 'Maternelle');

-- --------------------------------------------------------

--
-- Structure de la table `skill`
--

CREATE TABLE `skill` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type_id` int(11) DEFAULT NULL,
  `enable` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `skill`
--

INSERT INTO `skill` (`id`, `name`, `type_id`, `enable`) VALUES
(1, 'Java', 1, 1),
(2, 'Angular', 1, 1),
(3, 'JUnit', 1, 1),
(4, 'C#', 1, 1),
(5, 'Spring Boot', 1, 1),
(6, 'Agile', 2, 1),
(7, 'ASP.NET', 1, 1),
(8, 'SQL', 1, 1),
(9, 'Bash', 1, 0),
(10, 'R', 1, 0),
(11, 'API', 1, 1),
(12, 'Conception UX', 2, 1),
(13, 'Environnement Microsoft (Teams, Visual Studio)', 2, 1),
(14, 'Esprit d\'équipe', 3, 1),
(15, 'Automatisation de processus', 2, 1),
(16, 'Relation client', 2, 1),
(17, 'Formalisation des besoins utilisateurs', 2, 1),
(18, 'Esprit d\'initiative', 3, 1),
(19, 'Gestion de projet', 2, 1),
(20, ' Conception et maintenance de site web', 1, 1),
(21, 'apprentissage continu', 3, 1),
(22, 'Esprit de créativité', 3, 1),
(23, 'Organisation', 2, 1),
(24, 'adaptabilité', 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `skill_type`
--

CREATE TABLE `skill_type` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `skill_type`
--

INSERT INTO `skill_type` (`id`, `name`) VALUES
(1, 'technique'),
(2, 'méthodologie'),
(3, 'key');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `address_in_city` (`city_id`);

--
-- Index pour la table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD KEY `city_in_country` (`country_id`);

--
-- Index pour la table `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `establishement`
--
ALTER TABLE `establishement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `establishement_at_address` (`address_id`);

--
-- Index pour la table `experience`
--
ALTER TABLE `experience`
  ADD PRIMARY KEY (`id`),
  ADD KEY `city_of_experience` (`city_id`),
  ADD KEY `experience_of_person` (`person_id`),
  ADD KEY `experience_in_establishement` (`establishment_id`);

--
-- Index pour la table `experience_developp_skill`
--
ALTER TABLE `experience_developp_skill`
  ADD PRIMARY KEY (`experience_id`,`skill_id`),
  ADD KEY `skill_developped` (`skill_id`);

--
-- Index pour la table `hobby`
--
ALTER TABLE `hobby`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `hobby_description`
--
ALTER TABLE `hobby_description`
  ADD PRIMARY KEY (`id`),
  ADD KEY `description_of_hobby` (`hobby_id`);

--
-- Index pour la table `language`
--
ALTER TABLE `language`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `nationality`
--
ALTER TABLE `nationality`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD KEY `adress_of_person` (`address_id`);

--
-- Index pour la table `person_has_hobby`
--
ALTER TABLE `person_has_hobby`
  ADD PRIMARY KEY (`person_id`,`hobby_id`),
  ADD KEY `hobby_practiced` (`hobby_id`);

--
-- Index pour la table `person_has_nationality`
--
ALTER TABLE `person_has_nationality`
  ADD PRIMARY KEY (`person_id`,`nationality_id`),
  ADD KEY `nationality_has` (`nationality_id`);

--
-- Index pour la table `person_speaks_language`
--
ALTER TABLE `person_speaks_language`
  ADD PRIMARY KEY (`person_id`,`langage_id`),
  ADD KEY `language_spoken` (`langage_id`);

--
-- Index pour la table `skill`
--
ALTER TABLE `skill`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `type_of` (`type_id`);

--
-- Index pour la table `skill_type`
--
ALTER TABLE `skill_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `country`
--
ALTER TABLE `country`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `establishement`
--
ALTER TABLE `establishement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `experience`
--
ALTER TABLE `experience`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `hobby`
--
ALTER TABLE `hobby`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `hobby_description`
--
ALTER TABLE `hobby_description`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `language`
--
ALTER TABLE `language`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `nationality`
--
ALTER TABLE `nationality`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `skill`
--
ALTER TABLE `skill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `skill_type`
--
ALTER TABLE `skill_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_in_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `city_in_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `establishement`
--
ALTER TABLE `establishement`
  ADD CONSTRAINT `establishement_at_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `experience`
--
ALTER TABLE `experience`
  ADD CONSTRAINT `experience_in_establishement` FOREIGN KEY (`establishment_id`) REFERENCES `establishement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `experience_of_person` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `experience_developp_skill`
--
ALTER TABLE `experience_developp_skill`
  ADD CONSTRAINT `experience_developp` FOREIGN KEY (`experience_id`) REFERENCES `experience` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `skill_developped` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `hobby_description`
--
ALTER TABLE `hobby_description`
  ADD CONSTRAINT `description_of_hobby` FOREIGN KEY (`hobby_id`) REFERENCES `hobby` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `person`
--
ALTER TABLE `person`
  ADD CONSTRAINT `adress_of_person` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `person_has_hobby`
--
ALTER TABLE `person_has_hobby`
  ADD CONSTRAINT `hobby_practiced` FOREIGN KEY (`hobby_id`) REFERENCES `hobby` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `person_practice` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `person_has_nationality`
--
ALTER TABLE `person_has_nationality`
  ADD CONSTRAINT `nationality_has` FOREIGN KEY (`nationality_id`) REFERENCES `nationality` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `person_is` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `person_speaks_language`
--
ALTER TABLE `person_speaks_language`
  ADD CONSTRAINT `language_spoken` FOREIGN KEY (`langage_id`) REFERENCES `language` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `person_speaks` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `skill`
--
ALTER TABLE `skill`
  ADD CONSTRAINT `type_of` FOREIGN KEY (`type_id`) REFERENCES `skill_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
