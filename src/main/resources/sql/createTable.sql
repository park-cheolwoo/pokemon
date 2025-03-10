CREATE TABLE stat (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	original_name VARCHAR2(100) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE evolution_trigger (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE growth (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(30) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE total_experience (
	id NUMBER(5) PRIMARY KEY,
	growth_id NUMBER(5) NOT NULL,
	lv NUMBER(5) NOT NULL,
	experience NUMBER(10) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_total_experience_growth FOREIGN KEY(growth_id) REFERENCES growth (id) ON DELETE CASCADE
);

CREATE TABLE pokemon (
	id NUMBER(10) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	image VARCHAR2(255) DEFAULT '/images/no-pokemon.png' NOT NULL,
	image3d VARCHAR2(255) DEFAULT '/images/no-pokemon.png' NOT NULL,
	gender_rate NUMBER(2) NOT NULL,
	gender_diff NUMBER(1) DEFAULT 0 NOT NULL CHECK(gender_diff BETWEEN 0 AND 1),
	genus VARCHAR2(100) NOT NULL,
	height NUMBER(10) NOT NULL,
	weight NUMBER(10) NOT NULL,
	flavor_text CLOB NOT NULL,
	is_legendary NUMBER(1) DEFAULT 0 NOT NULL CHECK(is_legendary BETWEEN 0 AND 1),
	is_mythical NUMBER(1) DEFAULT 0 NOT NULL CHECK(is_mythical BETWEEN 0 AND 1),
	growth_id NUMBER(5),
	evolution_id NUMBER(5),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE sprites (
	id NUMBER(10) PRIMARY KEY,
	back_default VARCHAR2(255) NOT NULL,
	back_female VARCHAR2(255),
	back_shiny VARCHAR2(255) NOT NULL,
	back_shiny_female VARCHAR2(255),
	front_default VARCHAR2(255) NOT NULL,
	front_female VARCHAR2(255),
	front_shiny VARCHAR2(255) NOT NULL,
	front_shiny_female VARCHAR2(255),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_sprites_pokemon FOREIGN KEY(id) REFERENCES pokemon (id) ON DELETE CASCADE
);

CREATE TABLE evolution (
	id NUMBER(5) PRIMARY KEY,
	baby_id NUMBER(10) NOT NULL,
	child_trigger_id NUMBER(5) NOT NULL,
	child_id NUMBER(10) NULL,
	adult_trigger_id NUMBER(5) NOT NULL,
	adult_id NUMBER(10) NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_evolution_baby_pokemon FOREIGN KEY(baby_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_evolution_child_pokemon FOREIGN KEY(child_id) REFERENCES pokemon (id),
	CONSTRAINT fk_evolution_adult_pokemon FOREIGN KEY(adult_id) REFERENCES pokemon (id),
	CONSTRAINT fk_evolution_child_trigger FOREIGN KEY(child_trigger_id) REFERENCES evolution_trigger (id),
	CONSTRAINT fk_evolution_adult_trigger FOREIGN KEY(adult_trigger_id) REFERENCES evolution_trigger (id)
);

ALTER TABLE pokemon ADD CONSTRAINT fk_pokemon_evolution FOREIGN KEY(evolution_id) REFERENCES evolution (id);

CREATE TABLE ability (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	description CLOB DEFAULT 'NO_TEXT' NOT NULL,
	flavor_text CLOB DEFAULT 'NO_TEXT' NOT NULL,
	is_active NUMBER(1) DEFAULT 0 NOT NULL CHECK(is_active BETWEEN 0 AND 1),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE types (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	original_name VARCHAR2(100) NOT NULL,
	image VARCHAR2(255) DEFAULT '/images/no-type.png' NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE types_relationship (
	id NUMBER(5) PRIMARY KEY,
	from_id NUMBER(5) NOT NULL,
	to_id NUMBER(5) NOT NULL,
	effect NUMBER(2) DEFAULT 0 NOT NULL CHECK(effect BETWEEN -1 AND 2),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,

	CONSTRAINT fk_types_relationship_from FOREIGN KEY(from_id) REFERENCES types (id) ON DELETE CASCADE,
	CONSTRAINT fk_types_relationship_to FOREIGN KEY(to_id) REFERENCES types (id) ON DELETE CASCADE
);

CREATE TABLE attack (
	id NUMBER(5) PRIMARY KEY,
	types_id NUMBER(5) NOT NULL,
	name VARCHAR2(100) NOT NULL,
	description CLOB NOT NULL,
	flavor_text CLOB NOT NULL,
	damage NUMBER(5) DEFAULT 0 NOT NULL,
	is_active NUMBER(1) DEFAULT 0 NOT NULL CHECK(is_active BETWEEN 0 AND 1),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_attack_types FOREIGN KEY(types_id) REFERENCES types (id) ON DELETE CASCADE
);

CREATE TABLE habitat (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	original_name VARCHAR2(100) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE egg_group (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE characteristic (
	id NUMBER(5) PRIMARY KEY,
	stat_id NUMBER(5) NOT NULL,
	description CLOB NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_characteristic_stat FOREIGN KEY(stat_id) REFERENCES stat (id) ON DELETE CASCADE
);

CREATE TABLE egg_group_pokemon (
	id NUMBER(5) PRIMARY KEY,
	pokemon_id NUMBER(10) NOT NULL,
	egg_group_id NUMBER(5) NOT NULL,
	
	CONSTRAINT fk_egg_group_pokemon_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_egg_group_pokemon_egg_group FOREIGN KEY(egg_group_id) REFERENCES egg_group (id) ON DELETE CASCADE
);

CREATE TABLE pokemon_ability (
	id NUMBER(20) PRIMARY KEY,
	pokemon_id NUMBER(10) NOT NULL,
	ability_id NUMBER(5) NOT NULL,
	slot NUMBER(3) DEFAULT 0 NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_pokemon_ability_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_pokemon_ability_ability FOREIGN KEY(ability_id) REFERENCES ability (id) ON DELETE CASCADE
);

CREATE TABLE pokemon_attack (
	id NUMBER(20) PRIMARY KEY,
	pokemon_id NUMBER(10) NOT NULL,
	attack_id NUMBER(5) NOT NULL,
	lv NUMBER(5) DEFAULT 0 NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_pokemon_attack_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_pokemon_attack_attack FOREIGN KEY(attack_id) REFERENCES attack (id) ON DELETE CASCADE
);

CREATE TABLE pokemon_types (
	id NUMBER(20) PRIMARY KEY,
	pokemon_id NUMBER(10) NOT NULL,
	types_id NUMBER(5) NOT NULL,
	slot NUMBER(3) DEFAULT 0 NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_pokemon_types_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_pokemon_types_types FOREIGN KEY(types_id) REFERENCES types (id) ON DELETE CASCADE
);

CREATE TABLE pokemon_habitat (
	id NUMBER(20) PRIMARY KEY,
	pokemon_id NUMBER(10) NOT NULL,
	habitat_id NUMBER(5) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_pokemon_habitat_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_pokemon_habitat_habitat FOREIGN KEY(habitat_id) REFERENCES habitat (id) ON DELETE CASCADE
);

CREATE TABLE pokemon_base_stat (
	id NUMBER(20) PRIMARY KEY,
	pokemon_id NUMBER(10) NOT NULL,
	stat_id NUMBER(5) NOT NULL,
	value NUMBER(3) DEFAULT 0 NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,

	CONSTRAINT fk_pokemon_stat_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_pokemon_stat_stat FOREIGN KEY(stat_id) REFERENCES stat (id) ON DELETE CASCADE
);

CREATE TABLE item_category (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	image VARCHAR2(255) DEFAULT '/images/no-item-category.png' NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE item (
	id NUMBER(5) PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	image VARCHAR2(255) DEFAULT '/images/no-item.png' NOT NULL,
	category_id NUMBER(5) NOT NULL,
	cost NUMBER(10) DEFAULT 0 NOT NULL,
	real_cost NUMBER(10) DEFAULT 0 NOT NULL,
	description CLOB NOT NULL,
	store_type NUMBER(2) DEFAULT 0 NOT NULL CHECK(store_type BETWEEN 0 AND 3),
	is_active NUMBER(1) DEFAULT 0 NOT NULL CHECK(is_active BETWEEN 0 AND 1),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_item_category FOREIGN KEY(category_id) REFERENCES item_category (id) ON DELETE CASCADE
);

CREATE TABLE player (
    id VARCHAR2(30) PRIMARY KEY,
    nickname VARCHAR2(30) NOT NULL,
    tag VARCHAR2(10) NOT NULL,
    profile VARCHAR2(255) DEFAULT '/images/no-profile.png' NOT NULL,
    password VARCHAR2(255) NOT NULL,
    description CLOB,
    lv NUMBER(5) DEFAULT 1 NOT NULL,
    experience NUMBER(5) DEFAULT 0 NOT NULL,
    game_money NUMBER(20) DEFAULT 0 NOT NULL,
    real_money NUMBER(20) DEFAULT 0 NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
    
    CONSTRAINT unique_nickname_tag UNIQUE (nickname, tag)
);

CREATE TABLE player_pokemon (
	id NUMBER(20) PRIMARY KEY,
	player_id VARCHAR2(30) NOT NULL,
	pokemon_id NUMBER(10) NOT NULL,
	name VARCHAR2(30) NOT NULL,
	gender NUMBER(1) DEFAULT 0 NOT NULL CHECK(gender BETWEEN 0 AND 1),
	lv NUMBER(3) DEFAULT 0 NOT NULL,
	experience NUMBER(5) DEFAULT 0 NOT NULL,
	characteristic_id NUMBER(5) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_player_pokemon_player FOREIGN KEY(player_id) REFERENCES player (id) ON DELETE CASCADE,
	CONSTRAINT fk_player_pokemon_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE,
	CONSTRAINT fk_player_pokemon_characteristic FOREIGN KEY(characteristic_id) REFERENCES characteristic (id) ON DELETE CASCADE
);

CREATE TABLE friend (
	id NUMBER(20) PRIMARY KEY,
	player_from VARCHAR2(30) NOT NULL,
	player_to VARCHAR2(30) NOT NULL,
	is_accept NUMBER(1) DEFAULT 0 NOT NULL CHECK(is_accept BETWEEN 0 AND 1),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_friend_player_from FOREIGN KEY(player_from) REFERENCES player (id) ON DELETE CASCADE,
	CONSTRAINT fk_friend_player_to FOREIGN KEY(player_to) REFERENCES player (id) ON DELETE CASCADE
);

CREATE TABLE player_item (
	id NUMBER(10) PRIMARY KEY,
	player_id VARCHAR2(30) NOT NULL,
	item_id NUMBER(5) NOT NULL,
	count NUMBER(5) DEFAULT 0 NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_player_item_player FOREIGN KEY(player_id) REFERENCES player (id) ON DELETE CASCADE,
	CONSTRAINT fk_player_item_item FOREIGN KEY(item_id) REFERENCES item (id) ON DELETE CASCADE
);

CREATE TABLE game_stage (
	id NUMBER(5) PRIMARY KEY,
	habitat_id NUMBER(5) NOT NULL,
	stage NUMBER(3) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,

	CONSTRAINT fk_stage_habitat FOREIGN KEY(habitat_id) REFERENCES habitat (id) ON DELETE CASCADE
);

CREATE TABLE ingame (
	player_id VARCHAR2(30) PRIMARY KEY,
	is_ingame NUMBER(1) DEFAULT 0 NOT NULL CHECK(is_ingame BETWEEN 0 AND 1),
	stage_id NUMBER(5) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_ingame_stage FOREIGN KEY(stage_id) REFERENCES game_stage (id) ON DELETE CASCADE
);

CREATE TABLE ingame_pokemon (
	id NUMBER(20) PRIMARY KEY,
	player_id VARCHAR2(30) NOT NULL,
	pokemon_id NUMBER(20) UNIQUE NOT NULL,
	hp NUMBER(5) NOT NULL,
	slot NUMBER(3) NOT NULL CHECK(slot BETWEEN 0 AND 5),
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_ingame_pokemon_player FOREIGN KEY(player_id) REFERENCES player (id) ON DELETE CASCADE,
	CONSTRAINT fk_ingame_pokemon_pokemon FOREIGN KEY(pokemon_id) REFERENCES player_pokemon (id) ON DELETE CASCADE
);

CREATE TABLE ingame_enemy (
	id NUMBER(20) PRIMARY KEY,
	player_id VARCHAR2(30) NOT NULL,
	pokemon_id NUMBER(10) NOT NULL,
	hp NUMBER(5) NOT NULL,
	lv NUMBER(5) NOT NULL,
	updated_at DATE DEFAULT SYSDATE NOT NULL,
	created_at DATE DEFAULT SYSDATE NOT NULL,
	
	CONSTRAINT fk_ingame_enemy_player FOREIGN KEY(player_id) REFERENCES player (id) ON DELETE CASCADE,
	CONSTRAINT fk_ingame_enemy_pokemon FOREIGN KEY(pokemon_id) REFERENCES pokemon (id) ON DELETE CASCADE
);

CREATE SEQUENCE types_relationship_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999
;

CREATE SEQUENCE total_experience_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 999
;

CREATE SEQUENCE pokemon_ability_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
;

CREATE SEQUENCE pokemon_types_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
;

CREATE SEQUENCE pokemon_attack_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
;

CREATE SEQUENCE pokemon_base_stat_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 99999
;

CREATE SEQUENCE pokemon_habitat_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9999
;

CREATE SEQUENCE egg_group_pokemon_seq
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9999
;

COMMIT;
