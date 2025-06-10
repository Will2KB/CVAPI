--
-- Déchargement des données de la table `country`
--

INSERT INTO `country` (`id`, `name`) VALUES
(1, 'France'),
(2, 'Suisse');

--
-- Déchargement des données de la table `city`
--

INSERT INTO `city` (`id`, `name`, `zip`, `country_id`) VALUES
(1, 'Bonneville', 74130, 1),
(2, 'Genève', 1201, 2),
(3, 'Poitiers', 86000, 1),
(4, 'Clermont-Ferrand', 63000, 1),
(5, 'Villeneuve-d''Ascq', 59650, 1),
(6, 'Plan-les-Ouates', 1212, 2),
(7, 'Ayse', 74130, 1);

--
-- Déchargement des données de la table `address`
--

INSERT INTO `address` (`id`, `nb_street`, `street`, `complement`, `city_id`) VALUES
(1, 730, 'Rte de l''Eponnet', '', 7),
(2, 95, 'Avenue du Recteur Pineau', '', 3),
(3, 5, 'Avenue Blaise Pascal', '', 4),
(4, 11, 'Avenue de la Gare des Eaux-Vives', '', 2),
(5, 4, 'Rue du Champs Blanchod', '', 6),
(6, 159, 'Avenue du Buet', '', 1),
(7, NULL, NULL, 'Cité Scientifique', 5);

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `name`, `first_name`, `birthday`, `mail`, `phone`, `img`, `title`, `subtitle`, `personal_values`, `address_id`, `linkedInLink`, `gitHubLink`) VALUES
(1, 'Baroni', 'William', '1986-07-04', 'baroni.will@gmail.com', '+33609215347', NULL, 'Développeur full-stack', '4 ans d''expérience - Disponible de suite', 'Sens de l''initiative, Esprit d''équipe, Sens des responsabilités', 1, "https://www.linkedin.com/in/baroniwilliam/", "https://github.com/Will2KB");


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

--
-- Déchargement des données de la table `experience`
--

INSERT INTO `experience` (`id`, `name`, `date_begining`, `date_ending`, `establishment_id`, `city_id`, `mission`, `description`, `is_formation`, `person_id`) VALUES
(1, 'Gestion familliale, formation continue et projets personnels', '2021-01-01', NULL, NULL, 7, NULL, 'Gérer des taches familliales, planification et organisation quotidienne\r\nSuivre les formations SpringBoot, Test Unitaires, API REST, et Angular (OpenClassRoom)\r\nConcrétiser des projets personnels de rénovation d''intérieur: rénovationet création d''un sous-sol', 0, 1),
(2, 'Chef d''entreprise', '2018-03-01', '2020-12-31', 5, 1, 'Concrétiser un projet entrepreunarial et créer une entreprise', 'Créer et gérer un centre de loisirs indoor pour modèles réduits\r\nConcevoir et assurer la maintenance du site web (JOOMLA) permettant aux clients de réserver en ligne\r\nSuivre la gestion administrative, élaborer la stratégie commerciale, créer des partenariats et garantir la relation client', 0, 1),
(3, 'Développeur full-stack', '2016-03-01', '2018-02-01', 4, 2, 'Maintenance, évolution et création d''applications ASP.NET / C#', 'Maintenance, évolution et suivi de production d''un système de récolte de résultats d''analyses faites au cabinet du médecin\r\nEvolutiond''un système de communication de résultats aux patients Romands via l''API de MonDossierMedical.ch\r\nCréation d''un outil de prescription électronique spécifique à certaines analyses génétiques\r\nEvolutions de la sauvegarde et du chargement des réglages d''un outil de production interne', 0, 1),
(4, 'Bioinformaticien junior', '2011-12-12', '2016-02-12', 3, 2, 'Communication des résultats de séquençage aux clients', 'Préparation et envoie de résultats de séquençage\r\nAutomatisation des processus de création des rapports de résultats de séquençage (en Bash et en R)\r\nDéveloppement d''un outil web pour la préparation de run de séquençage et de l''analyse de leur qualité (PHP, JQuery, CSS)', 0, 1),
(5, 'MOOC \"Gestion de projet\" - Parcours avancé', NULL, '2018-05-31', 6, NULL, NULL, 'Notions fondamentales du management et de l''organisation de projet\r\nOutils avancés de gestion de projet: conception, planification, budget', 1, 1),
(6, 'Scrum Master Accredited Certification', NULL, '2016-06-01', 7, NULL, NULL, NULL, 1, 1),
(7, 'Master Génie Physiologique et Informatique', NULL, '2011-09-16', 1, 3, NULL, 'Programmation (ADA, Java, R, SQL, PL/SQL\r\nLogiciel éducatif sur le SIDA pour les collégiens (JAVA)\r\nVice-président de ProGphy: association proposant des projets informatiques rémunérés aux étudiants', 1, 1);


--
-- Déchargement des données de la table `skill_type`
--

INSERT INTO `skill_type` (`id`, `name`) VALUES
(1, 'technique'),
(2, 'méthodologie'),
(3, 'key');

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
(14, 'Esprit d''équipe', 3, 1),
(15, 'Automatisation de processus', 2, 1),
(16, 'Relation client', 2, 1),
(17, 'Formalisation des besoins utilisateurs', 2, 1),
(18, 'Esprit d''initiative', 3, 1),
(19, 'Gestion de projet', 2, 1),
(20, ' Conception et maintenance de site web', 1, 1),
(21, 'apprentissage continu', 3, 1),
(22, 'Esprit de créativité', 3, 1),
(23, 'Organisation', 2, 1),
(24, 'adaptabilité', 3, 1);

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

--
-- Déchargement des données de la table `hobby`
--

INSERT INTO `hobby` (`id`, `name`) VALUES
(1, 'Modélisme'),
(2, 'Bricolage');

--
-- Déchargement des données de la table `hobby_description`
--

INSERT INTO `hobby_description` (`id`, `hobby_id`, `descirption`) VALUES
(1, 1, 'Construction de modèles réduits à piloter et d''exposition'),
(2, 1, 'Pilotage de modèles réduits de camion en compétition'),
(3, 1, 'Organisation de compétitions'),
(4, 2, 'Rénovation et entretien d''une maison'),
(5, 2, 'Construction et rénovation de meubles');

--
-- Déchargement des données de la table `person_has_hobby`
--

INSERT INTO `person_has_hobby` (`person_id`, `hobby_id`, `year_of_practice`) VALUES
(1, 1, NULL),
(1, 2, NULL);

--
-- Déchargement des données de la table `language`
--

INSERT INTO `language` (`id`, `name`) VALUES
(1, 'Anglais'),
(2, 'Français'),
(3, 'Espagnol');

--
-- Déchargement des données de la table `person_speaks_language`
--

INSERT INTO `person_speaks_language` (`person_id`, `langage_id`, `level`) VALUES
(1, 1, 'B1'),
(1, 2, 'Maternelle');


--
-- Déchargement des données de la table `nationality`
--

INSERT INTO `nationality` (`id`, `name`) VALUES
(1, 'Française'),
(2, 'Suisse');

--
-- Déchargement des données de la table `person_has_nationality`
--

INSERT INTO `person_has_nationality` (`person_id`, `nationality_id`) VALUES
(1, 1);