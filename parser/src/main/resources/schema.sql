CREATE TABLE IF NOT EXISTS LOGDATA(
id int(11) NOT NULL AUTO_INCREMENT,
startdate DATETIME NOT NULL, 
ip varchar (25),
httprequest varchar (50),
httpcode int,
useragent varchar (250),
PRIMARY KEY (id)
);

