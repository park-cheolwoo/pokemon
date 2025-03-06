/**
 * DB Tables
 */
package kr.co.pokemon.data.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DBTables {
    EVOLUTION_TRIGGER("evolution_trigger"),
    ABILITY("ability"),
    TYPES("types"),
    TYPES_RELATIONSHIP("types_relationship", List.of(DBTables.TYPES)),
    ATTACK("attack", List.of(DBTables.TYPES)),
    HABITAT("habitat"),
    EGG_GROUP("egg_group"),
    STAT("stat"),
    CHARACTERISTIC("characteristic", List.of(DBTables.STAT)),
    POKEMON("pokemon", List.of(DBTables.EGG_GROUP)),
    SPRITES("sprites", List.of(DBTables.POKEMON)),
    EVOLUTION("evolution", List.of(DBTables.EVOLUTION_TRIGGER, DBTables.POKEMON)),
    GROWTH("growth", List.of(DBTables.POKEMON)),
    TOTAL_EXPERIENCE("total_experience", List.of(DBTables.GROWTH)),
    POKEMON_ABILITY("pokemon_ability", List.of(DBTables.ABILITY, DBTables.POKEMON)),
    POKEMON_ATTACK("pokemon_attack", List.of(DBTables.ATTACK, DBTables.POKEMON)),
    POKEMON_TYPES("pokemon_types", List.of(DBTables.TYPES, DBTables.POKEMON)),
    POKEMON_HABITAT("pokemon_habitat", List.of(DBTables.HABITAT, DBTables.POKEMON)),
    POKEMON_BASE_STAT("pokemon_base_stat", List.of(DBTables.STAT, DBTables.POKEMON)),
    PLAYER("player"),
    PLAYER_POKEMON("player_pokemon", List.of(DBTables.POKEMON, DBTables.PLAYER)),
    FRIEND("friend", List.of(DBTables.PLAYER)),
    ITEM_CATEGORY("item_category"),
    ITEM("item", List.of(DBTables.ITEM_CATEGORY)),
    PLAYER_ITEM("player_item", List.of(DBTables.ITEM, DBTables.PLAYER)),
    GAME_STAGE("game_stage", List.of(DBTables.HABITAT)),
    INGAME("ingame", List.of(DBTables.GAME_STAGE)),
    INGAME_POKEMON("ingame_pokemon", List.of(DBTables.PLAYER, DBTables.PLAYER_POKEMON)),
    INGAME_ENEMY("ingame_enemy", List.of(DBTables.POKEMON, DBTables.PLAYER));

    private final String tableName;
    private final List<DBTables> dependencies;

    DBTables(String tableName, List<DBTables> dependencies) {
    	this.tableName = tableName;
    	this.dependencies = dependencies;
    }

    DBTables(String tableName) {
        this.tableName = tableName;
        this.dependencies = List.of();
    }

    public String getTableName() {
        return tableName;
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
