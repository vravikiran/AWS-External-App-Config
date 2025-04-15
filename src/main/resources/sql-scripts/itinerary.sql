CREATE TABLE 'itinerary' (
  'itinerary_id' bigint NOT NULL AUTO_INCREMENT,
  'source' varchar(255) NOT NULL,
  'destination' varchar(255) NOT NULL,
  'duration' int NOT NULL,
  'isactive' tinyint(1) DEFAULT '1',
  'price' double NOT NULL,
  PRIMARY KEY ('itinerary_id')
)