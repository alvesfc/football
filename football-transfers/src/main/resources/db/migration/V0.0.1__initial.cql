CREATE TABLE football_transfers.transfers (
   player_id UUID,
   season text,
   create_date timestamp,
   attributes_json text,
   traits text,
   specialities text,
   additionalInfo text,
   PRIMARY KEY ((player_id,season), create_date)
)WITH CLUSTERING ORDER BY (create_date DESC);