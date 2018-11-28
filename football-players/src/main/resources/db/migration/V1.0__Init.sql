
CREATE SEQUENCE IDT_PLAYER_SQ;

CREATE TABLE football_player.players (
	idt_player int8 NOT NULL,
	dat_borndate date NOT NULL,
	nam_fullname varchar(60) NULL,
	nam_name varchar(30) NOT NULL,
	nam_nationality varchar(30) NOT NULL,
	flg_active boolean NOT NULL,
	CONSTRAINT players_pkey PRIMARY KEY (idt_player)
);

CREATE TABLE football_player.positions (
	idt_position int8 NOT NULL,
	nam_acronym varchar(3) NOT NULL,
	CONSTRAINT positions_pkey PRIMARY KEY (idt_position)
) ;

CREATE TABLE football_player.players_positions (
	idt_player int8 NOT NULL,
	idt_position int8 NOT NULL,
	CONSTRAINT players_positions_pkey PRIMARY KEY (idt_player,idt_position),
	CONSTRAINT fk_idt_player FOREIGN KEY (idt_player) REFERENCES football_player.players(idt_player),
	CONSTRAINT fk_idt_position FOREIGN KEY (idt_position) REFERENCES football_player.positions(idt_position)
);

