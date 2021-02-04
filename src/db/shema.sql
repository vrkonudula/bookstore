
--
-- Table structure for table `customer`
--

CREATE  TABLE `customer` (
                             `customer_id` INT UNSIGNED,
                             `name` VARCHAR(60) NOT NULL,
                             `address` VARCHAR(100) NOT NULL,
                             `phone` VARCHAR(45) NOT NULL,
                             `email` VARCHAR(45) NOT NULL,
                             `cc_number` VARCHAR(19) NOT NULL,
                             `cc_exp_date` DATE NOT NULL,
                             PRIMARY KEY (`customer_id`)
) ENGINE = InnoDB;

--
-- Table structure for table `customer_order`
--

CREATE  TABLE `customer_order` (
                                   `customer_order_id` INT UNSIGNED,
                                   `amount` INT UNSIGNED NOT NULL,
                                   `date_created` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `confirmation_number` INT UNSIGNED NOT NULL,
                                   `customer_id` INT UNSIGNED DEFAULT NULL,
                                   PRIMARY KEY (`customer_order_id`),
                                   FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
) ENGINE = InnoDB;

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
                            `category_id` INT UNSIGNED,
                            `name` VARCHAR(45) NOT NULL,
                            PRIMARY KEY (`category_id`)
) ENGINE = InnoDB;

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
                        `book_id` INT UNSIGNED,
                        `title` VARCHAR(60) NOT NULL,
                        `author` VARCHAR(60) NOT NULL,
                        `price` INT UNSIGNED NOT NULL,
                        `is_public` BOOLEAN NOT NULL,
                        `category_id` INT UNSIGNED,
                        PRIMARY KEY (`book_id`),
                        FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`)
) ENGINE = InnoDB;

--
-- Table structure for the table `customer_order_line_item`
--

CREATE TABLE `customer_order_line_item` (
                                            `customer_order_id` INT UNSIGNED,
                                            `book_id` INT UNSIGNED,
                                            `quantity` SMALLINT UNSIGNED DEFAULT 1,
                                            PRIMARY KEY (`customer_order_id`, `book_id`),
                                            FOREIGN KEY (`customer_order_id`) REFERENCES `customer_order`(`customer_order_id`),
                                            FOREIGN KEY (`book_id`) REFERENCES `book`(`book_id`)
) ENGINE = InnoDB;

--
-- Inserting into category and books
--

INSERT INTO `category` (category_id,name) VALUES ('1','Fiction'),('2','Comedy'),('3','History'),('4','Romance'),('5','Literature');

INSERT INTO `book` (book_id,title, author, price, is_public, category_id) VALUES ('1','To Kill a Mocking Bird','Harper Lee',799,true,1001),('2','The Great Gatsby','F Scott Fitzgerald',999,true,1001),
                                                                         ('3','The Outsiders','S E Hinton',899,false,1001),('4','The Immortals of Meluha','Amish Tripathi',799,true,1001),
                                                                         ('5','And Then There Were None','Agatha Christie',1099,false,1001),('6','The Krishna Key','Ashwin Sanghi',1199,false,1001);

INSERT INTO `book` (book_id,title, author, price, is_public, category_id) VALUES ('7','The Divine Comedy','Dante Alighieri',899,true,1002),('8','The Comedy of Errors','William Shakespeare',1099,false,1002),
                                                                         ('9','The Comedies','Peter Brown,Terence',899,false,1002),('10','The Human Comedy','William Saroyan',1299,false,1002),
                                                                         ('11','Old Masters: A Comedy','Thomas Bernhard',999,true,1002),('12','Yes Please','Amy Poehler',699,true,1002);

INSERT INTO `book` (book_id,title, author, price, is_public, category_id) VALUES ('13','A God Within','Rene Dubos',1099,false,1003),('14','Humankind: A Brief History','Felipe Fernandez-Armesto',799,true,1003),
                                                                         ('15','Inductance in Man','Jean Edmiston',999,false,1003),('16','Guns,Germs and Steel','Jared Diamond',1199,false,1003),
                                                                         ('17','1776','David McCullough',1099,false,1003),('18','The Causes of the American Revolution','Dale Anderson',999,true,1003);


INSERT INTO `book` (book_id,title, author, price, is_public, category_id) VALUES ('19','The Giver of Stars','Jojo Moyes',999,true,1004),('20','Eleanor Oliphant is Completely Fine','Gail Honeyman',1199,false,1004),
                                                                         ('21','The Fault in Our Stars','John Freen',1099,true,1004),('22','The Great Alone','Kristin Hannah',1099,false,1004),
                                                                         ('23','The Rosie Project','Greame Simsion',1299,true,1004),('24','The Firey Cross','Diana Gabaldon',1199,false,1004);

INSERT INTO `book` (book_id,title, author, price, is_public, category_id) VALUES ('25','Pride and Prejudice','Jane Austen',1299,true,1005),('26','Little Women','Louisa May Alcott',699,true,1005),
                                                                         ('27','Brave New World','Aldous Huxley',1099,false,1005),('28','Lord of The Flies','William Golding',1099,false,1005),
                                                                         ('29','The Catcher in the Rye','J D Salinger',899,false,1005),('30','Animal Farm','George Orwell',1199,true,1005);

