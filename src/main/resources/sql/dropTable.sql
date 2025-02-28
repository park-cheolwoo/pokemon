DECLARE
  is_exist NUMBER;
BEGIN
  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('evolution_trigger');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE evolution_trigger CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('ability');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE ability CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('types');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE types CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('types_relationship');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE types_relationship CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('attack');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE attack CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('habitat');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE habitat CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('egg_group');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE egg_group CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('stat');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE stat CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('characteristic');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE characteristic CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('pokemon');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE pokemon CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('sprites');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE sprites CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('evolution');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE evolution CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('growth');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE growth CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('total_experience');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE total_experience CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('pokemon_ability');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE pokemon_ability CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('pokemon_attack');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE pokemon_attack CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('pokemon_types');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE pokemon_types CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('pokemon_habitat');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE pokemon_habitat CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('pokemon_base_stat');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE pokemon_base_stat CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('player');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE player CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('player_pokemon');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE player_pokemon CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('friend');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE friend CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('item_category');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE item_category CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('item');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE item CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('player_item');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE player_item CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('game_stage');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE game_stage CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('ingame');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE ingame CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('ingame_pokemon');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE ingame_pokemon CASCADE CONSTRAINTS';
	END IF;

  SELECT COUNT(*) INTO is_exist FROM user_tables WHERE table_name = UPPER('ingame_enemy');
	IF is_exist > 0 THEN
		EXECUTE IMMEDIATE 'DROP TABLE ingame_enemy CASCADE CONSTRAINTS';
	END IF;
END;
/
