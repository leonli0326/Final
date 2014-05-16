CREATE DATABASE  IF NOT EXISTS `amazon` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `amazon`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: amazon
-- ------------------------------------------------------
-- Server version	5.6.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `deliverypackages`
--

DROP TABLE IF EXISTS `deliverypackages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliverypackages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `endTime` datetime DEFAULT NULL,
  `packageStatus` varchar(255) NOT NULL,
  `startTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliverypackages`
--

LOCK TABLES `deliverypackages` WRITE;
/*!40000 ALTER TABLE `deliverypackages` DISABLE KEYS */;
INSERT INTO `deliverypackages` VALUES (1,NULL,'Submitted','2014-04-22 15:04:07'),(2,NULL,'Submitted','2014-04-22 15:05:45'),(3,'2014-04-22 15:12:41','Delivered','2014-04-22 15:07:00'),(4,'2014-04-22 15:12:37','Delivered','2014-04-22 15:08:07'),(5,'2014-04-22 15:12:39','Delivered','2014-04-22 15:10:19');
/*!40000 ALTER TABLE `deliverypackages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enterprises`
--

DROP TABLE IF EXISTS `enterprises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enterprises` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL,
  `enterpriseName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enterprises`
--

LOCK TABLES `enterprises` WRITE;
/*!40000 ALTER TABLE `enterprises` DISABLE KEYS */;
INSERT INTO `enterprises` VALUES (1,0,'someEnterprise'),(2,0,'someEnterprise2');
/*!40000 ALTER TABLE `enterprises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagelinks`
--

DROP TABLE IF EXISTS `imagelinks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagelinks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orth9i586xs1n6av06ixgf3iw` (`product_id`),
  CONSTRAINT `FK_orth9i586xs1n6av06ixgf3iw` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagelinks`
--

LOCK TABLES `imagelinks` WRITE;
/*!40000 ALTER TABLE `imagelinks` DISABLE KEYS */;
INSERT INTO `imagelinks` VALUES (1,'http://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg',1),(2,'http://2.bp.blogspot.com/-hdkXfWeRV4A/TdizZhkLhhI/AAAAAAAAHUA/GlC3S4HL8vE/s1600/139865_1.jpg',1),(3,'http://upload.wikimedia.org/wikipedia/commons/4/4c/Bananas.jpg',2),(4,'http://www.connectthedotsmovement.com/wp-content/uploads/2012/03/banana.jpeg',2),(5,'http://pineapplestreetstudios.com/wp-content/uploads/2013/05/Tasty_Pineapple.jpg',3),(6,'http://www.fitho.in/wp-content/uploads/2010/10/Pineapple-Fruit.jpg',3),(7,'http://www.house-foods.com/uploads/premium_tofu16oz_firm.jpg',4),(8,'http://www.theshelbyreport.com/wp-content/uploads/2012/12/French-Beans_8oz.jpg',5),(9,'http://i.dailymail.co.uk/i/pix/2010/01/08/article-0-07C4978E000005DC-43_468x308.jpg',5),(10,'http://i.walmartimages.com/i/p/00/02/19/08/50/0002190850335_500X500.jpg',6),(11,'http://www.chicagomeat.com/product_images/73393%20CMA%20Buffet%20Beef%20Short%20Ribs,%20portioned.jpg',7),(12,'http://www.chicagomeat.com/product_images/73323%20CMA%20Buffet%20Beef%20Short%20Ribs%202%20x%202.jpg',7),(13,'http://www.benekeith.com/images/gallery/1364/lamb-rack-raw-2_web.jpg',9),(15,'http://media.salon.com/2013/09/barilla_spaghetti.jpg',10),(16,'http://www.hy1004.com/magento/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/b/a/barilla_spagetti_b.jpg',10),(17,'http://www.ifish.hk/images/chilean_seabass/chilean_seabass_2.jpg',11),(18,'http://thumbs.dreamstime.com/z/rainbow-trout-ice-fresh-herbs-fish-market-35272835.jpg',12),(19,'http://www.aoseafood.co.uk/uploads/1/3/2/6/13260184/8656612_orig.jpg?655',12);
/*!40000 ALTER TABLE `imagelinks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'Boston','MA'),(2,'Cambridge','MA'),(3,'New York','NY'),(4,'Chicago','IL');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderedCount` int(11) NOT NULL,
  `orderedPrice` double NOT NULL,
  `orderedTaxRate` double NOT NULL,
  `deliveryPackage_id` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `stock_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pdu64tn8yixgrhr44a77de0vd` (`deliveryPackage_id`),
  KEY `FK_g5rnyyx3jcxelh5i3x4ldj7a0` (`order_id`),
  KEY `FK_if90x14diehuj9tpns54iln2q` (`stock_id`),
  CONSTRAINT `FK_if90x14diehuj9tpns54iln2q` FOREIGN KEY (`stock_id`) REFERENCES `stocks` (`id`),
  CONSTRAINT `FK_g5rnyyx3jcxelh5i3x4ldj7a0` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_pdu64tn8yixgrhr44a77de0vd` FOREIGN KEY (`deliveryPackage_id`) REFERENCES `deliverypackages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` VALUES (1,1,5.99,0,1,1,1),(2,2,15.95,0,2,2,11),(3,2,3.29,0,2,2,6),(4,2,9.99,0,3,3,7),(5,5,15.95,0,4,4,11),(6,5,3.99,0,5,5,3);
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderStatus` varchar(255) NOT NULL,
  `orderTime` datetime NOT NULL,
  `orderTotal` double NOT NULL,
  `orderTotalBeforeTax` double NOT NULL,
  `userAddress_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `userPaymentMethod_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dwll192bdpwkrnetw64sbhh9n` (`userAddress_id`),
  KEY `FK_k8kupdtcdpqd57b6j4yq9uvdj` (`user_id`),
  KEY `FK_8is3x7moh3rb1xuxhj9bsfll8` (`userPaymentMethod_id`),
  CONSTRAINT `FK_8is3x7moh3rb1xuxhj9bsfll8` FOREIGN KEY (`userPaymentMethod_id`) REFERENCES `userpaymentmethods` (`id`),
  CONSTRAINT `FK_dwll192bdpwkrnetw64sbhh9n` FOREIGN KEY (`userAddress_id`) REFERENCES `useraddresses` (`id`),
  CONSTRAINT `FK_k8kupdtcdpqd57b6j4yq9uvdj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'Submitted','2014-04-22 15:04:07',5.99,5.99,1,1,1),(2,'Submitted','2014-04-22 15:05:45',38.48,38.48,1,1,1),(3,'Submitted','2014-04-22 15:07:00',19.98,19.98,1,1,1),(4,'Submitted','2014-04-22 15:08:07',79.75,79.75,1,1,1),(5,'Submitted','2014-04-22 15:10:19',19.95,19.95,1,1,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_tag`
--

DROP TABLE IF EXISTS `product_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_tag` (
  `product_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`product_id`,`tag_id`),
  KEY `FK_mkghhht2rh837xtjr4a7csjk8` (`tag_id`),
  CONSTRAINT `FK_2rkcr5ky2nboypb8b67s7ixai` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK_mkghhht2rh837xtjr4a7csjk8` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tag`
--

LOCK TABLES `product_tag` WRITE;
/*!40000 ALTER TABLE `product_tag` DISABLE KEYS */;
INSERT INTO `product_tag` VALUES (1,1),(2,1),(3,1),(1,2),(2,2),(3,2),(12,2),(4,3),(5,3),(6,3),(1,4),(2,4),(4,4),(5,5),(6,5),(6,6),(11,6),(7,7),(9,7),(7,8),(9,9),(10,10),(10,11),(11,12),(12,12),(12,13),(1,14),(3,15);
/*!40000 ALTER TABLE `product_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastUpdateTime` datetime DEFAULT NULL,
  `productDescribe` varchar(255) NOT NULL,
  `productEAN` varchar(255) NOT NULL,
  `productMake` varchar(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `productSpec` varchar(255) DEFAULT NULL,
  `retailPrice` double NOT NULL,
  `reviewScore` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hy5jsikn18g47x3o2mselomy3` (`productEAN`),
  UNIQUE KEY `UK_eq2aju8jrchp2j3to4imhufi6` (`productName`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'2014-04-22 12:47:42','Grown in Washington','1111111111111','Gala','Gala Apple, Organic','3 lb Bag',5.99,3.5),(2,'2014-04-22 12:55:28','Grown in Ecuador or Mexico','1111111111112','Ecuador','Bananas, Organic',' 5 Count Bunch, Ripe ',1.99,4.2),(3,'2014-04-22 12:57:53','Grown in Costa Rica','1111111111113','Costa Rica','Gold Pineapple','1 Pineapple',3.99,2.5),(4,'2014-04-22 13:06:56','Ingredients: Filtered Water, Organic Soybeans, Calcium Sulfate.','1111111111114','House Foods','House Foods, Organic Firm Tofu','ngredients: Filtered Water, Organic Soybeans, Calcium Sulfate.',2.09,3),(5,'2014-04-22 13:08:27','Grown in United States','1111111111115','Mann','Mann\'s Green Beans','12 oz Package',3.49,3.7),(6,'2014-04-22 13:10:55','Organic Broccoli, Water, Contains less than 1% of Sea Salt, Baking Soda.','1111111111116','Cascadian Farm','Cascadian Farm, Broccoli Florets, Organic',' 7 oz (Frozen)',3.29,1.5),(7,'2014-04-22 13:13:15','Short Ribs, 1 lb. (Boneless) Harris Ranch Black Angus,100% USDA Choice Beef. A Barbeque Delight! Prepare meat from your favorite recipes, cook in slow-cooker, grill or oven. A real crowd-pleaser!,','1111111111117','Huntington Meats','The Huntington Meats, Beef Short Ribs','Boneless, 1 lb',9.99,3),(9,'2014-04-22 13:16:54','Ready to season and grill or bake','1111111111118','Marconda\'s Meats','Marconda\'s Meats, Lamb Rack Frenched','New Zealand, 16 oz',16.98,4.8),(10,'2014-04-22 13:19:47','From its humble beginnings in 1877 as a small shop and bakery in Parma, Italy, Barilla has grown and strived to become the internationally trusted brand of pastas, sauces and Italian entrées that it is today','1111111111119','Barilla','Barilla, Spaghetti','16 oz',1.19,3),(11,'2014-04-22 13:22:38','Wild, Antarctica','1111111111120','Santa Monica','Santa Monica Seafood, Chilean Sea Bass Fillet','Skin Off, Previously Frozen, 1 lb',31.99,2.1),(12,'2014-04-22 13:24:52','Trout is full of Omega 3, nice flavor and tender meat. Some bones may still be present.','1111111111121','Dry Dock','Dry Dock Fish, Fresh Rainbow Trout','Cleaned & Boned, 1.2 LB',15.95,4);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviews` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reviewContent` varchar(255) NOT NULL,
  `score` int(11) NOT NULL,
  `reviewProduct_id` int(11) NOT NULL,
  `reviewUser_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_49d62lqrjgf8y1jqk9dd2py65` (`reviewProduct_id`),
  KEY `FK_6icvly27bd1f50l55skga2t21` (`reviewUser_id`),
  CONSTRAINT `FK_6icvly27bd1f50l55skga2t21` FOREIGN KEY (`reviewUser_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_49d62lqrjgf8y1jqk9dd2py65` FOREIGN KEY (`reviewProduct_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'good and fresh',5,12,1),(2,'it is ok',4,1,1),(5,'just soso\r\n',2,3,1),(7,'111111111',3,6,1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g50w4r0ru3g9uf6i6fr4kpro8` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ADMIN'),(1,'CUSTOMER'),(4,'PRODUCTMANAGER'),(3,'SUPPLIER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks`
--

DROP TABLE IF EXISTS `stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stocks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` double NOT NULL,
  `stockCount` int(11) NOT NULL,
  `taxRate` double NOT NULL,
  `enterprise_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_avhu5m18etxh78qsjljtvk0jd` (`enterprise_id`),
  KEY `FK_95u4ybuliu9fvg67ojx7drtpx` (`location_id`),
  KEY `FK_htp625bmmsb6gay567r5sdfoc` (`product_id`),
  CONSTRAINT `FK_htp625bmmsb6gay567r5sdfoc` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FK_95u4ybuliu9fvg67ojx7drtpx` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`),
  CONSTRAINT `FK_avhu5m18etxh78qsjljtvk0jd` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprises` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks`
--

LOCK TABLES `stocks` WRITE;
/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` VALUES (1,5,4,0,1,1,1),(2,2,4,0,1,1,2),(3,3.98,15,0,1,1,3),(4,1.59,10,0,1,1,4),(5,3.6,1,0,1,1,5),(6,1.99,5,0,1,1,6),(7,8.88,2,0,1,1,7),(8,15.99,5,0,1,1,9),(9,1,22,0,1,1,10),(10,30.99,5,0,1,1,11),(11,14.99,10,0,1,1,12),(12,10,1,0.06,1,3,7),(13,4,15,0.06,1,3,1),(14,30,14,0.06,1,4,11),(15,1.8,4,0.05,1,4,2),(16,1.8,4,0.02,1,3,2),(17,3,10,0,2,1,5);
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ranking` int(11) NOT NULL,
  `tagContent` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8b7dvod4bj705l50qcxcnwxnb` (`tagContent`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,6,'fruit'),(2,4,'fresh'),(3,3,'vegetarian'),(4,3,'organic'),(5,2,'vegetable'),(6,3,'frozen'),(7,2,'meat'),(8,1,'beef'),(9,1,'lamb'),(10,1,'grain'),(11,1,'pasta'),(12,2,'fish'),(13,2,'awsome'),(14,2,'ok'),(15,2,'soso');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_enterprise`
--

DROP TABLE IF EXISTS `user_enterprise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_enterprise` (
  `enterprise_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_b1w9crhrailprlbw5i1m0nh1h` (`enterprise_id`),
  CONSTRAINT `FK_1djlqv6401hi4bxeeqw6l41w8` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_b1w9crhrailprlbw5i1m0nh1h` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprises` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_enterprise`
--

LOCK TABLES `user_enterprise` WRITE;
/*!40000 ALTER TABLE `user_enterprise` DISABLE KEYS */;
INSERT INTO `user_enterprise` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `user_enterprise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_it77eq964jhfqtu54081ebtio` (`role_id`),
  CONSTRAINT `FK_apcc8lxk2xnug8377fatvbn04` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_it77eq964jhfqtu54081ebtio` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(1,2),(1,3),(2,3),(1,4),(2,4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraddresses`
--

DROP TABLE IF EXISTS `useraddresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraddresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `isDefault` bit(1) DEFAULT NULL,
  `location_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bo885wb9ayn3cucid2tjsl4w9` (`location_id`),
  KEY `FK_l57ffs19duv6cq4je152l7pny` (`user_id`),
  CONSTRAINT `FK_l57ffs19duv6cq4je152l7pny` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_bo885wb9ayn3cucid2tjsl4w9` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraddresses`
--

LOCK TABLES `useraddresses` WRITE;
/*!40000 ALTER TABLE `useraddresses` DISABLE KEYS */;
INSERT INTO `useraddresses` VALUES (1,'#1 some street','\0',1,1),(2,'#2 some other street','\0',3,1);
/*!40000 ALTER TABLE `useraddresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userpaymentmethods`
--

DROP TABLE IF EXISTS `userpaymentmethods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userpaymentmethods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardNumber` varchar(255) NOT NULL,
  `cardType` varchar(255) NOT NULL,
  `isDefault` bit(1) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9m8jc6x6m0plowpe8kj7u5jv0` (`user_id`),
  CONSTRAINT `FK_9m8jc6x6m0plowpe8kj7u5jv0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userpaymentmethods`
--

LOCK TABLES `userpaymentmethods` WRITE;
/*!40000 ALTER TABLE `userpaymentmethods` DISABLE KEYS */;
INSERT INTO `userpaymentmethods` VALUES (1,'4444444444444444','Visa','',1);
/*!40000 ALTER TABLE `userpaymentmethods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'123@123.com','John','Doe','a1c60c3c80420b290ae7e02d3a8b254ad909f65fe529087b58cf9cfb18b231c1','123-456-7890','john'),(2,'111@111.com','Jane','Doe','b7f4d09decf25be2605c12e7c4c1a4a5ffe0ab657fa0e68abec397c1d9a4abd4','111-111-1111','jane');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-22 19:13:36
