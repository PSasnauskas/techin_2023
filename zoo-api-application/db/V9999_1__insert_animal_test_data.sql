CREATE TABLE IF NOT EXISTS `room` (

`id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` varchar(20),
`created_date` timestamp

) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

--constraint pk_person primary key (id)