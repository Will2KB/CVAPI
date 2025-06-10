--
-- Structure de la table `country`
--
CREATE TABLE `country` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
);


--
-- Structure de la table `city`
--

CREATE TABLE `city` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `zip` INT DEFAULT NULL,
  `country_id` INT NOT NULL
)  ;

--
-- Structure de la table `address`
--

CREATE TABLE `address` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `nb_street` INT DEFAULT NULL,
  `street` VARCHAR(250) DEFAULT NULL,
  `complement` VARCHAR(250) DEFAULT NULL,
  `city_id` INT NOT NULL
);

--
-- Structure de la table `establishement`
--

CREATE TABLE `establishement` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `address_id` INT DEFAULT NULL
);

--
-- Structure de la table `person`
--

CREATE TABLE `person` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `birthday` date DEFAULT NULL,
  `mail` VARCHAR(100) DEFAULT NULL,
  `phone` VARCHAR(100) DEFAULT NULL,
  `img` blob DEFAULT NULL,
  `title` VARCHAR(250) DEFAULT NULL,
  `subtitle` VARCHAR(250) DEFAULT NULL,
  `personal_values` CLOB DEFAULT NULL,
  `address_id` INT DEFAULT NULL, 
  `linkedInLink` VARCHAR(2083) DEFAULT NULL,
  `gitHubLink` VARCHAR(2083) DEFAULT NULL
)  ;

--
-- Structure de la table `experience`
--

CREATE TABLE `experience` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `date_begining` DATE DEFAULT NULL,
  `date_ending` DATE DEFAULT NULL,
  `establishment_id` INT DEFAULT NULL,
  `city_id` INT DEFAULT NULL,
  `mission` VARCHAR(250) DEFAULT NULL,
  `description` CLOB DEFAULT NULL,
  `is_formation` TINYINT NOT NULL DEFAULT 0,
  `person_id` INT NOT NULL
);

--
-- Structure de la table `experience_developp_skill`
--

CREATE TABLE `experience_developp_skill` (
  `experience_id` INT AUTO_INCREMENT NOT NULL,
  `skill_id` INT NOT NULL
)  ;

--
-- Structure de la table `hobby`
--

CREATE TABLE `hobby` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
)  ;

--
-- Structure de la table `hobby_description`
--

CREATE TABLE `hobby_description` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `hobby_id` INT NOT NULL,
  `descirption` CLOB NOT NULL
)  ;

--
-- Structure de la table `language`
--

CREATE TABLE `language` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
)  ;

--
-- Structure de la table `nationality`
--

CREATE TABLE `nationality` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL
)  ;

--
-- Structure de la table `person_has_hobby`
--

CREATE TABLE `person_has_hobby` (
  `person_id` INT NOT NULL,
  `hobby_id` INT NOT NULL,
  `year_of_practice` INT DEFAULT NULL
)  ;

--
-- Structure de la table `person_has_nationality`
--

CREATE TABLE `person_has_nationality` (
  `person_id` INT NOT NULL,
  `nationality_id` INT NOT NULL
)  ;

--
-- Structure de la table `person_speaks_language`
--

CREATE TABLE `person_speaks_language` (
  `person_id` INT NOT NULL,
  `langage_id` INT NOT NULL,
  `level` VARCHAR(10) NOT NULL DEFAULT 'A1'
  );



-- --------------------------------------------------------
--
-- Structure de la table `skill_type`
--

CREATE TABLE `skill_type` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL 
)  ;


--
-- Structure de la table `skill`
--

CREATE TABLE `skill` (
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `type_id` INT DEFAULT NULL,
  `enable` TINYINT NOT NULL DEFAULT 0
)  ;




--
-- Index pour les tables déchargées
--

--
-- Index pour la table `address`
--
ALTER TABLE `address`
  ADD FOREIGN KEY (`city_id`)
  REFERENCES `city` (`id`);

--
-- Index pour la table `city`
--
ALTER TABLE `city`
ADD FOREIGN KEY (`country_id`)
  REFERENCES `country` (`id`);

--
-- Index pour la table `establishement`
--
ALTER TABLE `establishement`
ADD FOREIGN KEY (`address_id`)
  REFERENCES `address` (`id`);

--
-- Index pour la table `experience`
--
ALTER TABLE `experience`
ADD FOREIGN KEY (`city_id`)
REFERENCES `city` (`id`);

ALTER TABLE `experience`
ADD FOREIGN KEY (`person_id`)
REFERENCES `person` (`id`);

ALTER TABLE `experience`
ADD FOREIGN KEY (`establishment_id`)
REFERENCES `establishement` (`id`);
--
-- Index pour la table `experience_developp_skill`
--
ALTER TABLE `experience_developp_skill`
ADD PRIMARY KEY (`experience_id`, `skill_id`);

ALTER TABLE `experience_developp_skill`
ADD FOREIGN KEY (`skill_id`)
REFERENCES `skill` (`id`);
  
ALTER TABLE `experience_developp_skill`
ADD FOREIGN KEY (`experience_id`)
REFERENCES `experience` (`id`);
--
-- Index pour la table `hobby_description`
--
ALTER TABLE `hobby_description`
 ADD FOREIGN KEY (`hobby_id`)
  REFERENCES `hobby` (`id`);

--
-- Index pour la table `person`
--
ALTER TABLE `person`
ADD FOREIGN KEY (`address_id`)
  REFERENCES `address` (`id`);

--
-- Index pour la table `person_has_hobby`
--
ALTER TABLE `person_has_hobby`
ADD PRIMARY KEY (`person_id`, `hobby_id`);

ALTER TABLE `person_has_hobby`
ADD FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`);
  
ALTER TABLE `person_has_hobby`
ADD FOREIGN KEY (`hobby_id`)
  REFERENCES `hobby` (`id`);
--
-- Index pour la table `person_has_nationality`
--
ALTER TABLE `person_has_nationality`
ADD PRIMARY KEY (`person_id`, `nationality_id`);

ALTER TABLE `person_has_nationality`
ADD FOREIGN KEY (`person_id`)
  REFERENCES `person` (`id`);
  
ALTER TABLE `person_has_nationality`
ADD FOREIGN KEY (`nationality_id`)
  REFERENCES `nationality` (`id`);
--
-- Index pour la table `person_speaks_language`
--
ALTER TABLE `person_speaks_language`
ADD PRIMARY KEY (`person_id`, `langage_id`);

ALTER TABLE `person_speaks_language`
ADD FOREIGN KEY (`person_id`)
REFERENCES `person` (`id`);

ALTER TABLE `person_speaks_language`
ADD FOREIGN KEY (`langage_id`)
  REFERENCES `language` (`id`);
--
-- Index pour la table `skill`
--
ALTER TABLE `skill`
ADD FOREIGN KEY (`type_id`)
  REFERENCES `skill_type` (`id`);
