use restaurante;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: restaurante
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `categoria_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`categoria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Bebidas sin alcohol'),(14,'Pizzas'),(15,'Hamburguesas'),(16,'Lomitos'),(17,'Papas Fritas'),(18,'Bebidas con alcohol'),(20,'Postres');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudades` (
  `ciudad_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `provincia_id` int DEFAULT NULL,
  PRIMARY KEY (`ciudad_id`),
  KEY `provincia_id` (`provincia_id`),
  CONSTRAINT `ciudades_ibfk_1` FOREIGN KEY (`provincia_id`) REFERENCES `provincias` (`provincia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=694 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


-- VERSION WORKBENCH 8.0.30
DROP TABLE IF EXISTS `ciudades`;
CREATE TABLE `ciudades` (
  `ciudad_id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) DEFAULT NULL,
  `provincia_id` INT DEFAULT NULL,
  PRIMARY KEY (`ciudad_id`),
  KEY `provincia_id` (`provincia_id`),
  CONSTRAINT `ciudades_ibfk_1` FOREIGN KEY (`provincia_id`) REFERENCES `provincias` (`provincia_id`)
) ENGINE=InnoDB AUTO_INCREMENT=694 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
INSERT INTO `ciudades` VALUES (1,'Mendiolaza',1),(2,'Rio Ceballos',1),(3,'Unquillo',1),(4,'Villa Maria',1),(5,'Rosario',2),(8,'San Fernando del Valle de Catamarca',7),(9,'Alta Gracia',1),(10,'Bell Ville',1),(11,'Cruz del Eje',1),(12,'Dean Funes',1),(13,'Laboulaye',1),(14,'Río Tercero',1),(15,'Villa Allende',1),(16,'Villa Carlos Paz',1),(17,'Villa del Rosario',1),(18,'Villa Dolores',1),(19,'Villa General Belgrano',1),(20,'Villa María',1),(21,'Villa Nueva',1),(22,'Jesús María',1),(23,'Morteros',1),(24,'Río Cuarto',1),(25,'Río Segundo',1),(26,'San Francisco',1),(27,'San José de la Dormida',1),(28,'Santa Rosa de Calamuchita',1),(29,'Tanti',1),(30,'Villa Giardino',1),(31,'Villa Huidobro',1),(32,'Villa Nueva',1),(33,'Villa Rumipal',1),(34,'Villa María',1),(35,'Villa María',1),(36,'Arocena',2),(37,'Avellaneda',2),(38,'Esperanza',2),(39,'Frontera',2),(40,'Gálvez',2),(41,'Granadero Baigorria',2),(42,'Helvecia',2),(43,'Laguna Paiva',2),(44,'Las Rosas',2),(45,'Mojón Grande',2),(46,'Reconquista',2),(47,'San Carlos Centro',2),(48,'San Javier',2),(49,'San Jorge',2),(50,'San José del Rincón',2),(51,'Santa Fe',2),(52,'Santa Rosa de Calchines',2),(53,'Santo Tomé',2),(54,'Sunchales',2),(55,'Tacuarendí',2),(56,'Tostado',2),(57,'Venado Tuerto',2),(58,'Vera',2),(59,'Villa Amelia',2),(60,'Villa Constitución',2),(61,'Villa Gobernador Gálvez',2),(62,'Villa Minetti',2),(63,'Wheelwright',2),(64,'Andalgalá',7),(65,'Antofagasta de la Sierra',7),(66,'Belén',7),(67,'Capayán',7),(68,'Capillitas',7),(69,'Chumbicha',7),(70,'El Rodeo',7),(71,'Fiambalá',7),(72,'Hualfín',7),(73,'Icaño',7),(74,'La Puerta',7),(75,'Las Juntas',7),(76,'Los Altos',7),(77,'Los Varela',7),(78,'Pomán',7),(79,'San José',7),(80,'Santa María',7),(81,'Santa Rosa',7),(82,'Saujil',7),(83,'Tapso',7),(84,'Tinogasta',7),(85,'Valle Viejo',7),(86,'Villa Dolores',7),(87,'Villa Vil',7),(88,'Yamánas',7),(89,'Yerba Buena',7),(90,'Zapata',7),(91,'Zarzal',7),(92,'Aimogasta',6),(93,'Anillaco',6),(94,'Anillaco',6),(95,'Chamical',6),(96,'Chepes',6),(97,'Chepes Viejo',6),(98,'Famatina',6),(99,'La Rioja',6),(100,'Machigasta',6),(101,'Milagro',6),(102,'Nonogasta',6),(103,'Olta',6),(104,'Patinó',6),(105,'San Blas de los Sauces',6),(106,'San Juan',6),(107,'San Pedro',6),(108,'Santa Clara',6),(109,'Santa Cruz',6),(110,'Santo Domingo',6),(111,'Tama',6),(112,'Tama',6),(113,'Tama',6),(114,'Tama',6),(115,'Tama',6),(116,'Tama',6),(117,'Tama',6),(118,'Tama',6),(119,'Tama',6),(120,'Villa Casana',6),(121,'Villa Castelli',6),(122,'Adrogué',8),(123,'Avellaneda',8),(124,'Bahía Blanca',8),(125,'Balcarce',8),(126,'Baradero',8),(127,'Benito Juárez',8),(128,'Berazategui',8),(129,'Bolívar',8),(130,'Campana',8),(131,'CABA',8),(132,'Chascomús',8),(133,'Chivilcoy',8),(134,'Dolores',8),(135,'Ensenada',8),(136,'Escobar',8),(137,'Gral. Alvear',8),(138,'Gral. Belgrano',8),(139,'Gral. Guido',8),(140,'Junín',8),(141,'La Plata',8),(142,'Lomas de Zamora',8),(143,'Mercedes',8),(144,'Navarro',8),(145,'Olavarría',8),(146,'Pergamino',8),(147,'Quilmes',8),(148,'San Isidro',8),(149,'San Nicolás',8),(150,'Tandil',8),(151,'Villa Gesell',8),(152,'Barranqueras',9),(153,'Charata',9),(154,'Colonia Benítez',9),(155,'Colonia Elisa',9),(156,'Concepción del Bermejo',9),(157,'Corzuela',9),(158,'El Espinillo',9),(159,'Fontana',9),(160,'Gancedo',9),(161,'General Pinedo',9),(162,'General San Martín',9),(163,'Las Breñas',9),(164,'Las Palmas',9),(165,'Machagai',9),(166,'Margarita Belén',9),(167,'Miraflores',9),(168,'Pampa del Indio',9),(169,'Presidencia de la Plaza',9),(170,'Presidencia Roca',9),(171,'Presidencia Roque Sáenz Peña',9),(172,'Puerto Bermejo',9),(173,'Puerto Eva Perón',9),(174,'Puerto Tirol',9),(175,'Quitilipi',9),(176,'Resistencia',9),(177,'Taco Pozo',9),(178,'Tres Isletas',9),(179,'Villa Ángela',9),(180,'Villa Berthet',9),(181,'Villa Río Bermejito',9),(182,'Comodoro Rivadavia',10),(183,'Esquel',10),(184,'Puerto Madryn',10),(185,'Rawson',10),(186,'Trelew',10),(187,'Camarones',10),(188,'Gaiman',10),(189,'Trevelin',10),(190,'Dolavon',10),(191,'Puerto Pirámides',10),(192,'Playa Unión',10),(193,'Sarmiento',10),(194,'Rada Tilly',10),(195,'Gastre',10),(196,'Paso de Indios',10),(197,'Tecka',10),(198,'Gualjaina',10),(199,'Las Plumas',10),(200,'Telsen',10),(201,'Colan Conhué',10),(202,'Aldea Apeleg',10),(203,'Aldea Epulef',10),(204,'Alto Río Senguer',10),(205,'Buen Pasto',10),(206,'Doctor Ricardo Rojas',10),(207,'El Maitén',10),(208,'Frontera',10),(209,'Garayalde',10),(210,'Lago Blanco',10),(211,'Las Heras',10),(212,'Bella Vista',11),(213,'Berón de Astrada',11),(214,'Caá Catí',11),(215,'Colonia Carlos Pellegrini',11),(216,'Colonia Libertad',11),(217,'Colonia Liebig',11),(218,'Concepción',11),(219,'Curuzú Cuatiá',11),(220,'Empedrado',11),(221,'Esquina',11),(222,'Estación Torrent',11),(223,'Garruchos',11),(224,'Goya',11),(225,'Itatí',11),(226,'Ituzaingó',11),(227,'La Cruz',11),(228,'Lavalle',11),(229,'Mburucuyá',11),(230,'Mercedes',11),(231,'Mocoretá',11),(232,'Monte Caseros',11),(233,'Paso de la Patria',11),(234,'Paso de los Libres',11),(235,'Saladas',11),(236,'San Cosme',11),(237,'San Lorenzo',11),(238,'Santa Ana',11),(239,'Santa Lucía',11),(240,'Santo Tomé',11),(241,'Yapeyú',11),(242,'Bovril',12),(243,'Crespo',12),(244,'Concepción del Uruguay',12),(245,'Colón',12),(246,'Diamante',12),(247,'Federación',12),(248,'Gualeguay',12),(249,'Gualeguaychú',12),(250,'La Paz',12),(251,'Larroque',12),(252,'Lucas González',12),(253,'Nogoyá',12),(254,'Oro Verde',12),(255,'Paraná',12),(256,'Rosario del Tala',12),(257,'San Benito',12),(258,'San Jaime',12),(259,'San José',12),(260,'Santa Elena',12),(261,'Seguí',12),(262,'Urdinarrain',12),(263,'Viale',12),(264,'Villaguay',12),(265,'Victoria',12),(266,'Villa Clara',12),(267,'Villa Elisa',12),(268,'Villa Mantero',12),(269,'Villa Paranacito',12),(270,'Villa Urquiza',12),(271,'Clorinda',13),(272,'Colonia Pastoril',13),(273,'Comandante Fontana',13),(274,'El Colorado',13),(275,'El Espinillo',13),(276,'Estanislao del Campo',13),(277,'General Güemes',13),(278,'General Mansilla',13),(279,'Herradura',13),(280,'Ibarreta',13),(281,'Ing. Guillermo N. Juárez',13),(282,'Laguna Blanca',13),(283,'Las Lomitas',13),(284,'Los Chiriguanos',13),(285,'Misión San Francisco',13),(286,'Palo Santo',13),(287,'Pirané',13),(288,'Pozo del Tigre',13),(289,'Riacho He Hé',13),(290,'San Francisco de Laishi',13),(291,'San Hilario',13),(292,'San Martín Dos',13),(293,'Siete Palmas',13),(294,'Subteniente Perín',13),(295,'Tres Lagunas',13),(296,'Villa Dos Trece',13),(297,'Villa Escolar',13),(298,'Villa General Güemes',13),(299,'Villa Lucero',13),(300,'Villa Pilcomayo',13),(301,'Abra Pampa',14),(302,'Humahuaca',14),(303,'La Quiaca',14),(304,'Libertador General San Martín',14),(305,'Maimará',14),(306,'Palpalá',14),(307,'Perico',14),(308,'San Antonio',14),(309,'San Pedro',14),(310,'San Salvador de Jujuy',14),(311,'Tilcara',14),(312,'Yuto',14),(313,'Calilegua',14),(314,'El Carmen',14),(315,'Pampa Blanca',14),(316,'Purmamarca',14),(317,'Santa Clara',14),(318,'Santa Catalina',14),(319,'Volcán',14),(320,'Aguas Calientes',14),(321,'Caimancito',14),(322,'La Mendieta',14),(323,'Ledesma',14),(324,'Monterrico',14),(325,'Palma Sola',14),(326,'Reyes',14),(327,'San Francisco',14),(328,'Susques',14),(329,'Tumbaya',14),(330,'Vinalito',14),(331,'Alpachiri',15),(332,'Anguil',15),(333,'Arata',15),(334,'Bernasconi',15),(335,'Catriló',15),(336,'Doblas',15),(337,'Eduardo Castex',15),(338,'General Acha',15),(339,'General Pico',15),(340,'Guatraché',15),(341,'Ingeniero Luiggi',15),(342,'Intendente Alvear',15),(343,'Jacinto Arauz',15),(344,'La Adela',15),(345,'Macachín',15),(346,'Mauricio Mayer',15),(347,'Miguel Riglos',15),(348,'Parera',15),(349,'Puelches',15),(350,'Quemú Quemú',15),(351,'Realicó',15),(352,'Santa Isabel',15),(353,'Santa Rosa',15),(354,'Toay',15),(355,'Trenel',15),(356,'Uriburu',15),(357,'Veinticinco de Mayo',15),(358,'Victorica',15),(359,'Villa Mirasol',15),(360,'Winifreda',15),(361,'General Alvear',16),(362,'Godoy Cruz',16),(363,'Guaymallén',16),(364,'Junín',16),(365,'La Paz',16),(366,'Las Heras',16),(367,'Lavalle',16),(368,'Luján de Cuyo',16),(369,'Maipú',16),(370,'Malargüe',16),(371,'Mendoza',16),(372,'San Carlos',16),(373,'San Martín',16),(374,'San Rafael',16),(375,'Tunuyán',16),(376,'Tupungato',16),(377,'Agrelo',16),(378,'Colonia Las Rosas',16),(379,'Corralitos',16),(380,'El Challao',16),(381,'El Plumerillo',16),(382,'La Consulta',16),(383,'La Primavera',16),(384,'Los Árboles',16),(385,'Los Molles',16),(386,'Los Sauces',16),(387,'Potrerillos',16),(388,'San Vicente',16),(389,'Uspallata',16),(390,'Villa Nueva',16),(391,'Apóstoles',17),(392,'Aristóbulo del Valle',17),(393,'Azara',17),(394,'Campo Grande',17),(395,'Campo Ramón',17),(396,'Campo Viera',17),(397,'Candelaria',17),(398,'Capioví',17),(399,'Caraguatay',17),(400,'Colonia Aurora',17),(401,'Colonia Delicia',17),(402,'Colonia Victoria',17),(403,'Dos de Mayo',17),(404,'Eldorado',17),(405,'Garuhapé',17),(406,'Jardín América',17),(407,'Leandro N. Alem',17),(408,'Montecarlo',17),(409,'Oberá',17),(410,'Puerto Esperanza',17),(411,'Puerto Iguazú',17),(412,'Puerto Libertad',17),(413,'Puerto Piray',17),(414,'Puerto Rico',17),(415,'San Antonio',17),(416,'San Ignacio',17),(417,'San Javier',17),(418,'San José',17),(419,'San Pedro',17),(420,'Wanda',17),(421,'Aluminé',18),(422,'Andacollo',18),(423,'Añelo',18),(424,'Bajada del Agrio',18),(425,'Buta Ranquil',18),(426,'Caviahue',18),(427,'Centenario',18),(428,'Chos Malal',18),(429,'Cutral-Có',18),(430,'El Cholar',18),(431,'Huinganco',18),(432,'Junín de los Andes',18),(433,'Las Coloradas',18),(434,'Las Lajas',18),(435,'Loncopué',18),(436,'Los Catutos',18),(437,'Neuquén',18),(438,'Picún Leufú',18),(439,'Plaza Huincul',18),(440,'Plottier',18),(441,'Rincón de los Sauces',18),(442,'San Martín de los Andes',18),(443,'San Patricio del Chañar',18),(444,'Santo Tomás',18),(445,'Sauzal Bonito',18),(446,'Senillosa',18),(447,'Taquimilán',18),(448,'Tricao Malal',18),(449,'Villa La Angostura',18),(450,'Villa Pehuenia',18),(451,'Allen',19),(452,'Catriel',19),(453,'Cervantes',19),(454,'Chichinales',19),(455,'Cinco Saltos',19),(456,'Cipolletti',19),(457,'Contralmirante Cordero',19),(458,'Cubanea',19),(459,'General Conesa',19),(460,'General Roca',19),(461,'Ingeniero Huergo',19),(462,'Ingeniero Jacobacci',19),(463,'Los Menucos',19),(464,'Mainqué',19),(465,'Río Colorado',19),(466,'San Antonio Oeste',19),(467,'San Carlos de Bariloche',19),(468,'Sierra Colorada',19),(469,'Valcheta',19),(470,'Viedma',19),(471,'Villa Regina',19),(472,'Choele Choel',19),(473,'Coronel Belisle',19),(474,'Darwin',19),(475,'Fernández Oro',19),(476,'Guardia Mitre',19),(477,'Luis Beltrán',19),(478,'Maquinchao',19),(479,'Ñorquinco',19),(480,'Pilcaniyeu',19),(481,'Aguaray',20),(482,'Cachi',20),(483,'Cafayate',20),(484,'Campo Quijano',20),(485,'Cerrillos',20),(486,'Chicoana',20),(487,'Colonia Santa Rosa',20),(488,'Embarcación',20),(489,'El Carril',20),(490,'El Galpón',20),(491,'El Quebrachal',20),(492,'General Güemes',20),(493,'General Mosconi',20),(494,'Hipólito Yrigoyen',20),(495,'Joaquín V. González',20),(496,'La Caldera',20),(497,'Metán',20),(498,'Orán',20),(499,'Pichanal',20),(500,'Rosario de la Frontera',20),(501,'Rosario de Lerma',20),(502,'Salta',20),(503,'San Antonio de los Cobres',20),(504,'San José de Metán',20),(505,'San Ramón de la Nueva Orán',20),(506,'Santa Victoria Este',20),(507,'Tartagal',20),(508,'Vaqueros',20),(509,'Villa San Lorenzo',20),(510,'Yrigoyen',20),(511,'Albardón',21),(512,'Angaco',21),(513,'Calingasta',21),(514,'Caucete',21),(515,'Chimbas',21),(516,'Iglesia',21),(517,'Jáchal',21),(518,'Nueve de Julio',21),(519,'Pocito',21),(520,'Rawson',21),(521,'Rivadavia',21),(522,'San Juan',21),(523,'San Martín',21),(524,'Santa Lucía',21),(525,'Sarmiento',21),(526,'Ullum',21),(527,'Valle Fértil',21),(528,'Veinticinco de Mayo',21),(529,'Zonda',21),(530,'Aberastain',21),(531,'Achango',21),(532,'Barreal',21),(533,'Barrio Rivadavia',21),(534,'Bermejo',21),(535,'Carpintería',21),(536,'Colola',21),(537,'Desamparados',21),(538,'Guandacol',21),(539,'La Bebida',21),(540,'Las Chacritas',21),(541,'Candelaria',22),(542,'Concarán',22),(543,'El Trapiche',22),(544,'Juana Koslay',22),(545,'La Florida',22),(546,'La Toma',22),(547,'La Tranca',22),(548,'Lafinur',22),(549,'Mercedes',22),(550,'Naschel',22),(551,'Navia',22),(552,'Nogolí',22),(553,'Papagayos',22),(554,'Potrero de los Funes',22),(555,'Quines',22),(556,'Renca',22),(557,'Salto del Tala',22),(558,'San Francisco',22),(559,'San Gerónimo',22),(560,'San Martín',22),(561,'Santa Rosa del Conlara',22),(562,'Talita',22),(563,'Tilisarao',22),(564,'Unión',22),(565,'Villa de la Quebrada',22),(566,'Villa de la Quebrada',22),(567,'Villa de la Quebrada',22),(568,'Villa de la Quebrada',22),(569,'Villa de la Quebrada',22),(570,'28 de Noviembre',23),(571,'Caleta Olivia',23),(572,'El Calafate',23),(573,'El Chaltén',23),(574,'Gobernador Gregores',23),(575,'Las Heras',23),(576,'Los Antiguos',23),(577,'Perito Moreno',23),(578,'Pico Truncado',23),(579,'Puerto Deseado',23),(580,'Puerto San Julián',23),(581,'Puerto Santa Cruz',23),(582,'Puerto  Deseado',23),(583,'Río Gallegos',23),(584,'Río Turbio',23),(585,'Tres Lagos',23),(586,'Veintiocho de Noviembre',23),(587,'28 de Noviembre',23),(588,'Caleta Olivia',23),(589,'El Calafate',23),(590,'El Chaltén',23),(591,'Gobernador Gregores',23),(592,'Las Heras',23),(593,'Los Antiguos',23),(594,'Perito Moreno',23),(595,'Pico Truncado',23),(596,'Puerto Deseado',23),(597,'Puerto San Julián',23),(598,'Puerto Santa Cruz',23),(599,'Puerto  Deseado',23),(600,'Río Gallegos',23),(601,'Río Turbio',23),(602,'Tres Lagos',23),(603,'Veintiocho de Noviembre',23),(604,'Árraga',24),(605,'Brea Pozo',24),(606,'Campo Gallo',24),(607,'Clodomira',24),(608,'Colonia Dora',24),(609,'El Bobadal',24),(610,'Forres',24),(611,'Frias',24),(612,'Garza',24),(613,'Icaño',24),(614,'Ingeniero Forres',24),(615,'La Banda',24),(616,'Los Juries',24),(617,'Los Telares',24),(618,'Malbrán',24),(619,'Monte Quemado',24),(620,'Nueva Esperanza',24),(621,'Pampa de Los Guanacos',24),(622,'Pozo Hondo',24),(623,'Quimilí',24),(624,'San Pedro',24),(625,'Santiago del Estero',24),(626,'Selva',24),(627,'Sol de Julio',24),(628,'Sumampa',24),(629,'Suncho Corral',24),(630,'Termas de Río Hondo',24),(631,'Vilelas',24),(632,'Villa Atamisqui',24),(633,'Villa Ojo de Agua',24),(634,'Río Grande',25),(635,'Tolhuin',25),(636,'Ushuaia',25),(637,'Cameron',25),(638,'Estancia Policía Montada',25),(639,'Estancia Santo Domingo',25),(640,'Estancia San Gregorio',25),(641,'Estancia Viamonte',25),(642,'Estancia Vicuña',25),(643,'Estancia Villa Sara',25),(644,'Harberton',25),(645,'Lago Blanco',25),(646,'Lapataia',25),(647,'Las Bandurrias',25),(648,'Paso Garibaldi',25),(649,'Paso San Sebastián',25),(650,'Paso Virginia',25),(651,'Puerto Almanza',25),(652,'Puerto Arturo',25),(653,'Puerto Toro',25),(654,'Puerto Yartou',25),(655,'Rancho Hambre',25),(656,'San Sebastián',25),(657,'Sarandí',25),(658,'Témpanos',25),(659,'Túnel de los Vientos',25),(660,'Villa Ecológica',25),(661,'Villa Las Cotorras',25),(662,'Villa Marina',25),(663,'Villa Parque Alem',25),(664,'Alderetes',26),(665,'Bella Vista',26),(666,'Burruyacú',26),(667,'Capitán Cáceres',26),(668,'Choromoro',26),(669,'Concepción',26),(670,'Delfín Gallo',26),(671,'El Bracho',26),(672,'El Cadillal',26),(673,'Famaillá',26),(674,'Garmendia',26),(675,'Graneros',26),(676,'Lamadrid',26),(677,'Las Talitas',26),(678,'Lules',26),(679,'Monteros',26),(680,'Río Chico',26),(681,'Río Seco',26),(682,'Simoca',26),(683,'Soldado Maldonado',26),(684,'Tafí del Valle',26),(685,'Tafí Viejo',26),(686,'Trancas',26),(687,'Villa Belgrano',26),(688,'Villa Benjamín Araoz',26),(689,'Villa Carmela',26),(690,'Villa de Leales',26),(691,'Villa Quinteros',26),(692,'Yánima',26),(693,'Yerba Buena',26);
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `cliente_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `telefono` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `correo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`cliente_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedidos`
--

DROP TABLE IF EXISTS `detalle_pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedidos` (
  `detalle_pedido_id` bigint NOT NULL AUTO_INCREMENT,
  `pedido_id` int NOT NULL,
  `producto_id` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio_unitario` double NOT NULL,
  `subtotal` double NOT NULL,
  PRIMARY KEY (`detalle_pedido_id`),
  KEY `pedido_id` (`pedido_id`),
  KEY `producto_id` (`producto_id`),
  CONSTRAINT `detalle_pedidos_ibfk_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`pedido_id`),
  CONSTRAINT `detalle_pedidos_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--

-- TABLA DETALLE PEDIDOS WORKBENCH 8.0.30
DROP TABLE IF EXISTS `detalle_pedidos`;

CREATE TABLE `detalle_pedidos` (
  `detalle_pedido_id` BIGINT NOT NULL AUTO_INCREMENT,
  `pedido_id` INT NOT NULL,
  `producto_id` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `precio_unitario` DOUBLE NOT NULL,
  `subtotal` DOUBLE NOT NULL,
  PRIMARY KEY (`detalle_pedido_id`),
  KEY `pedido_id` (`pedido_id`),
  KEY `producto_id` (`producto_id`),
  CONSTRAINT `detalle_pedidos_ibfk_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`pedido_id`),
  CONSTRAINT `detalle_pedidos_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`producto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table `detalle_pedidos`
--

LOCK TABLES `detalle_pedidos` WRITE;
/*!40000 ALTER TABLE `detalle_pedidos` DISABLE KEYS */;
INSERT INTO `detalle_pedidos` VALUES (177,97,58,1,2850,2850),(178,99,61,2,2800,5600);
/*!40000 ALTER TABLE `detalle_pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesas`
--

DROP TABLE IF EXISTS `mesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesas` (
  `mesa_id` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `capacidad` int NOT NULL,
  `estado` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`mesa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesas`
--

LOCK TABLES `mesas` WRITE;
/*!40000 ALTER TABLE `mesas` DISABLE KEYS */;
INSERT INTO `mesas` VALUES (2,2,7,'DISPONIBLE'),(3,1,8,'DISPONIBLE'),(6,3,4,'DISPONIBLE'),(9,4,10,'DISPONIBLE'),(10,5,4,'RESERVADA'),(11,12,8,'DISPONIBLE'),(12,25,4,'DISPONIBLE'),(13,15,4,'DISPONIBLE');
/*!40000 ALTER TABLE `mesas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paises` (
  `pais_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pais_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--
-- VERSION WORKBENCH 8.0.30
DROP TABLE IF EXISTS `paises`;

CREATE TABLE `paises` (
  `pais_id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`pais_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES (1,'Argentina');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `pedido_id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `fecha` datetime(6) NOT NULL,
  `mesa_id` int DEFAULT NULL,
  `estado` varchar(255) DEFAULT 'pendiente',
  `total` double DEFAULT NULL,
  `creador_pedido` int DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `mesa_id` (`mesa_id`),
  KEY `FK_creador_pedido` (`creador_pedido`),
  CONSTRAINT `FK_creador_pedido` FOREIGN KEY (`creador_pedido`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`mesa_id`) REFERENCES `mesas` (`mesa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

-- VERSION 'PEDIDOS' WORKBENCH 8.0.30

DROP TABLE IF EXISTS `pedidos`;

CREATE TABLE `pedidos` (
  `pedido_id` INT NOT NULL AUTO_INCREMENT,
  `usuario_id` INT NOT NULL,
  `fecha` DATETIME(6) NOT NULL,
  `mesa_id` INT DEFAULT NULL,
  `estado` VARCHAR(255) DEFAULT 'pendiente',
  `total` DOUBLE DEFAULT NULL,
  `creador_pedido` INT DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `mesa_id` (`mesa_id`),
  KEY `FK_creador_pedido` (`creador_pedido`),
  CONSTRAINT `FK_creador_pedido` FOREIGN KEY (`creador_pedido`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`usuario_id`),
  CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`mesa_id`) REFERENCES `mesas` (`mesa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (97,28,'2023-12-22 14:03:00.000000',2,'PAGADO',2850,28),(98,38,'2023-12-22 14:10:00.000000',3,'PAGADO',0,33),(99,48,'2023-12-22 16:52:00.000000',9,'PAGADO',5600,48);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `producto_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `precio` double DEFAULT NULL,
  `disponible` int DEFAULT NULL,
  `stock` int NOT NULL,
  `id_categoria` int NOT NULL,
  PRIMARY KEY (`producto_id`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`categoria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (32,'Margarita','Tomate, mozzarella y albahaca',4000,NULL,56,14),(33,'Napolitana','Tomate, mozzarella, ajo y albahaca',4000,1,100,14),(34,'Fugazzeta','Masa, mozzarella, cebolla y aceitunas',4400,1,100,14),(35,'Jamón y Morrones','Tomate, mozzarella, jamón y morrones',4200,1,100,14),(36,'Calabresa','Tomate, mozzarella, salchicha calabresa y aceitunas',4650,1,99,14),(37,'Roquefort','Tomate, mozzarella y queso roquefort',4700,1,100,14),(38,'Cuatro Quesos','Mozzarella, gorgonzola, emmental y parmesano',3850,1,100,14),(39,'Americana','Tomate, mozzarella, panceta y huevo',3990,1,100,14),(40,'Vegetariana','Tomate, mozzarella y variedad de vegetales',4100,1,100,14),(41,'Caprese','Tomate, mozzarella, tomate cherry y albahaca',4250,1,99,14),(42,'Coca Cola','Bebida gasificada sabor cola',850,1,9,1),(43,'Pepsi','Bebida gasificada sabor cola',850,NULL,100,1),(44,'Sprite','Bebida gasificada sabor lima-limón',900,1,100,1),(45,'Fanta','Bebida gasificada sabor naranja',850,1,93,1),(46,'Schweppes','Bebida gasificada sabor jengibre',850,1,100,1),(47,'Manaos','Bebida gasificada sabor cola',850,1,100,1),(48,'Paso de los Toros','Bebida gasificada sabor pomelo',850,1,100,1),(49,'Mirinda','Bebida gasificada sabor naranja',850,1,100,1),(50,'Pritty Limón','Bebida gasificada sabor limon',850,1,100,1),(51,'Criolla','Jugosa carne argenta en pan casero, con cheddar, lechuga, tomate y mayo.',3500,1,100,15),(52,'Gaucha','Mix de carne y chorizo, chimichurri, provolone, cebolla y rúcula en pan rústico.',4000,1,100,15),(53,'Patagónica','Cordero patagonico, queso de cabra, hongos, rúcula y salsa de frutos rojos.',4500,1,100,15),(54,'Criollo',' Lomo jugoso, chimichurri, queso provolone y hojas de albahaca en pan de campo.',3250,1,99,16),(55,'Pampeano','Lomo a la parrilla, panceta crocante, queso ahumado, tomate y mayonesa de ajo en pan artesanal.',3350,1,100,16),(56,'Porteño','Lomo a la parrilla, rúcula, tomate seco, queso azul y aderezo de mostaza y miel en pan de centeno.',3650,1,100,16),(57,'Austral','Lomo a la brasa, huevo a la parrilla, palta, lechuga y mayonesa de cilantro en pan integral.',3100,1,100,16),(58,'Cheddar','Papas fritas crujientes y doradas, cubiertas con generosas porciones de queso cheddar derretido.',2850,1,99,17),(59,'Crema y Perejil','Papas doradas, acompañadas de una delicada mezcla de crema fresca y perejil picado finamente.',2550,1,100,17),(60,'Bravas','Papas fritas crujientes bañadas en una salsa brava casera, con ese toque justo de picante que despierta tus papilas.',1950,1,100,17),(61,'Fernet con Coca','Una bebida popular en Argentina, especialmente en la región de Córdoba.',2800,1,98,18),(62,'Malbec','Un vino tinto argentino conocido internacionalmente.',3500,1,100,18),(63,'Torrontés','Un vino blanco argentino con un sabor fresco y afrutado.',3200,1,100,18),(64,'Quilmes','Cerveza argentina, una de las más consumidas en el país.',2500,1,100,18),(65,'Campari con Naranja','Un aperitivo refrescante.',3000,1,100,18),(66,'Aperol Spritz','Una bebida a base de Aperol, prosecco y soda.',3300,1,98,18),(67,'Gancia con Limón','Un aperitivo dulce y suave.',2700,1,100,18),(68,'Gin Tonic con Pomelo','Una variante refrescante del clásico Gin Tonic.',3200,1,87,18),(69,'Caipirinha','Preparada con cachaza, azúcar y lima.',2900,1,100,18),(70,'Whisky 1884','Un whisky argentino reconocido por su calidad.',3800,1,100,18),(71,'Cynar con Soda','Un aperitivo a base de alcachofa.',3100,1,100,18),(72,'Vermut Cinzano','Un vermut italiano que se consume ampliamente en Argentina.',3400,1,100,18),(73,'Cerveza Artesanal','Argentina tiene una escena cervecera artesanal en crecimiento con diversas opciones.',2800,1,100,18),(74,'Fernet con Sprite','Otra variante de Fernet popular, especialmente entre los jóvenes.',2900,1,100,18),(75,'Vodka Tonic con Limón','Una opción clásica y refrescante.',3300,1,100,18),(78,'2 bochas de helado.','Consultar por gustos disponibles.',2500,1,15,20);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincias` (
  `provincia_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `pais_id` int DEFAULT NULL,
  PRIMARY KEY (`provincia_id`),
  KEY `pais_id` (`pais_id`),
  CONSTRAINT `provincias_ibfk_1` FOREIGN KEY (`pais_id`) REFERENCES `paises` (`pais_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--
-- VERSION 'PROVINCIAS' WORKBENCH 8.0.30
DROP TABLE IF EXISTS `provincias`;

CREATE TABLE `provincias` (
  `provincia_id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) DEFAULT NULL,
  `pais_id` INT DEFAULT NULL,
  PRIMARY KEY (`provincia_id`),
  KEY `pais_id` (`pais_id`),
  CONSTRAINT `provincias_ibfk_1` FOREIGN KEY (`pais_id`) REFERENCES `paises` (`pais_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;





LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,'Cordoba',1),(2,'Santa Fe',1),(6,'La Rioja',1),(7,'Catamarca',1),(8,'Buenos Aires',1),(9,'Chaco',1),(10,'Chubut',1),(11,'Corrientes',1),(12,'Entre Ríos',1),(13,'Formosa',1),(14,'Jujuy',1),(15,'La Pampa',1),(16,'Mendoza',1),(17,'Misiones',1),(18,'Neuquén',1),(19,'Río Negro',1),(20,'Salta',1),(21,'San Juan',1),(22,'San Luis',1),(23,'Santa Cruz',1),(24,'Santiago del Estero',1),(25,'Tierra del Fuego',1),(26,'Tucumán',1);
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservas` (
  `id_reserva` bigint NOT NULL AUTO_INCREMENT,
  `cant_pers` int DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora_inicio` varchar(255) DEFAULT NULL,
  `hora_fin` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `mesa_id` int DEFAULT NULL,
  `rango_horario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `FK_mesa_id` (`mesa_id`),
  CONSTRAINT `FK_mesa_id` FOREIGN KEY (`mesa_id`) REFERENCES `mesas` (`mesa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

-- VERSION 'RESERVAS' WORKBENCH 8.0.30
DROP TABLE IF EXISTS `reservas`;

CREATE TABLE `reservas` (
  `id_reserva` BIGINT NOT NULL AUTO_INCREMENT,
  `cant_pers` INT DEFAULT NULL,
  `fecha` DATE DEFAULT NULL,
  `hora_inicio` VARCHAR(255) DEFAULT NULL,
  `hora_fin` VARCHAR(255) DEFAULT NULL,
  `nombre` VARCHAR(255) DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `dni` VARCHAR(255) DEFAULT NULL,
  `codigo` VARCHAR(255) DEFAULT NULL,
  `mesa_id` INT DEFAULT NULL,
  `rango_horario` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `FK_mesa_id` (`mesa_id`),
  CONSTRAINT `FK_mesa_id` FOREIGN KEY (`mesa_id`) REFERENCES `mesas` (`mesa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (21,8,'2023-12-06','18:00','20:00','Mateo','mateoduartej@gmail.com','2345678912','ac357462',3,'18hs a 20hs'),(23,10,'2023-12-13','20:00','22:00','Carlos','charly123@gmail.com','23367482','09cd92b0',9,'20hs a 22hs'),(27,4,'2023-12-12','18:00','20:00','Sabrina','mateoduartej@gmail.com','43282607','173f6da0',6,'18hs a 20hs'),(41,4,'2023-12-22','18:00','20:00','Stefanie','stefi22@gmail.com','269874956','6fe59005',10,'18hs a 20hs'),(42,8,'2023-12-22','18:00','20:00','Mateo','mateoduartej@gmail.com','123456789','0e3bc95c',3,'18hs a 20hs');
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `rol_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'EMPLEADO'),(3,'CLIENTE'),(8,'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `usuario_id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `apellido` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `clave` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `rol_id` int NOT NULL,
  `telefono` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pais_id` int DEFAULT NULL,
  `provincia_id` int DEFAULT NULL,
  `ciudad_id` int DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  KEY `usuarios_ibfk_1` (`rol_id`),
  KEY `pais_id` (`pais_id`),
  KEY `provincia_id` (`provincia_id`),
  KEY `ciudad_id` (`ciudad_id`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`pais_id`) REFERENCES `paises` (`pais_id`),
  CONSTRAINT `usuarios_ibfk_3` FOREIGN KEY (`provincia_id`) REFERENCES `provincias` (`provincia_id`),
  CONSTRAINT `usuarios_ibfk_4` FOREIGN KEY (`ciudad_id`) REFERENCES `ciudades` (`ciudad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES
(28,'Gregorio','Whiteman','cliente','$2a$10$2ktXDi/GvZBp0tnAWhqigONZW0oS31QW7IjttzyAR2vJzqnCKD3/O',3,'3519845616',1,1,1),
(33,'Emanuel','Rodriguez','admin','$2a$10$FNQs.y6wzuVkI6UF7EGsP.PjtAkWtBO4SXGHm6.l42581tXPk2H5e',8,'3547565555',1,1,2),
(34,'Juan Manuel','Horse','empleado','$2a$10$1I0l/AaWd/cv1mlancgTVOrssRktuxTOJveI5f.GFGIZ.EIkfg76u',2,'351464748',1,1,10),
(38,'No asignado','No asignado','noAsignado','$2a$10$jbXKK5gp8BnonjxhyzWd.eoqGn4yNwoNNY.kDV3doDlKghslWZP0u',3,'000000',1,1,2),
(48,'Andrea','Perez','migue12','$2a$10$UdzAHeY73C.38Sa.f1oDdeNzeuOja0QRyqzmSDu9TqcdzVJFwbp4K',3,'035714676568',1,1,14);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-30 14:07:48
