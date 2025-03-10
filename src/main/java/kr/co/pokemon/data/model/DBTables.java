/**
 * DB Tables
 */
package kr.co.pokemon.data.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DBTables {
	STAT("stat", "/stat"),
	CHARACTERISTIC("characteristic", "/characteristic", List.of(DBTables.STAT)),

	GROWTH("growth", "/growth-rate"),
    TOTAL_EXPERIENCE("total_experience", null, List.of(DBTables.GROWTH)),

	EVOLUTION_TRIGGER("evolution_trigger", "/evolution-trigger"),

	POKEMON("pokemon", "/pokemon", List.of(DBTables.STAT)),
	SPRITES("sprites", null, List.of(DBTables.POKEMON)),

	ABILITY("ability", "/ability", List.of(DBTables.POKEMON)),
    TYPES("types", "/type", List.of(DBTables.POKEMON)),
    TYPES_RELATIONSHIP("types_relationship", "/type", List.of(DBTables.TYPES)),
    ATTACK("attack", "/move", List.of(DBTables.TYPES, DBTables.POKEMON)),
    HABITAT("habitat", "/pokemon-habitat", List.of(DBTables.POKEMON)),
    EGG_GROUP("egg_group", "/egg-group", List.of(DBTables.POKEMON)),
    EGG_GROUP_POKEMON("egg_group_pokemon", null,List.of(DBTables.EGG_GROUP, DBTables.POKEMON)),
    EVOLUTION("evolution", "/evolution", List.of(DBTables.EVOLUTION_TRIGGER, DBTables.POKEMON)),

    POKEMON_ABILITY("pokemon_ability", null, List.of(DBTables.ABILITY, DBTables.POKEMON)),
    POKEMON_ATTACK("pokemon_attack", "/pokemon", List.of(DBTables.ATTACK, DBTables.POKEMON)),
    POKEMON_TYPES("pokemon_types", null, List.of(DBTables.TYPES, DBTables.POKEMON)),
    POKEMON_HABITAT("pokemon_habitat", null, List.of(DBTables.HABITAT, DBTables.POKEMON)),
    POKEMON_BASE_STAT("pokemon_base_stat", null, List.of(DBTables.STAT, DBTables.POKEMON)),

    ITEM_CATEGORY("item_category", "/item-category"),
    ITEM("item", "/item", List.of(DBTables.ITEM_CATEGORY)),

    PLAYER("player", null),
    PLAYER_POKEMON("player_pokemon", null, List.of(DBTables.POKEMON, DBTables.PLAYER)),
    FRIEND("friend", null, List.of(DBTables.PLAYER)),
    PLAYER_ITEM("player_item", null, List.of(DBTables.ITEM, DBTables.PLAYER)),
    GAME_STAGE("game_stage", null, List.of(DBTables.HABITAT)),
    INGAME("ingame", null, List.of(DBTables.GAME_STAGE)),
    INGAME_POKEMON("ingame_pokemon", null, List.of(DBTables.PLAYER, DBTables.PLAYER_POKEMON)),
    INGAME_ENEMY("ingame_enemy", null, List.of(DBTables.POKEMON, DBTables.PLAYER));

    private final String tableName;
    private final String uri;
    private final List<DBTables> dependencies;

    DBTables(String tableName, String uri, List<DBTables> dependencies) {
    	this.tableName = tableName;
    	this.uri = uri;
    	this.dependencies = dependencies;
    }

    DBTables(String tableName, String uri) {
    	this.tableName = tableName;
    	this.uri = uri;
    	this.dependencies = List.of();
    }

    public String getTableName() {
        return tableName;
    }
    
    public String getUri() {
    	return uri;
    }
    
    public List<DBTables> getDependencies() {
    	return dependencies;
    }

    public static boolean contains(String tableName) {
    	try {
    		DBTables.valueOf(tableName.toUpperCase());
    		return true;
    	} catch (IllegalArgumentException e) {
    		return false;
    	}
    }
    
    public static List<String> getTableNames() {
        return Arrays.stream(DBTables.values())
                     .map(DBTables::getTableName)
                     .collect(Collectors.toList());
    }
}
