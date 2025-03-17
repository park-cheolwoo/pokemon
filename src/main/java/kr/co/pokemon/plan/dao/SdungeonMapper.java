package kr.co.pokemon.plan.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.pokemon.plan.dto.SdungeonDTO;

@Mapper

public interface SdungeonMapper {

	 @Update("UPDATE SDUNGEON SET gamemoney = 0")
	    void resetGameMoney();
	
    @Update("UPDATE SDUNGEON SET daily_clear_count = 0")
    void resetDailyClearCount();

    @Update("UPDATE SDUNGEON SET weekly_clear_count = 0")
    void resetWeeklyClearCount();

    @Update("""
        BEGIN
            UPDATE SDUNGEON
            SET pokemon1_id = NULL, pokemon1_name = NULL, pokemon1_img = NULL,
                pokemon2_id = NULL, pokemon2_name = NULL, pokemon2_img = NULL,
                pokemon3_id = NULL, pokemon3_name = NULL, pokemon3_img = NULL;

            FOR rec IN (SELECT id FROM SDUNGEON) LOOP
                DECLARE
                    v_pokemon1_id NUMBER(10);
                    v_pokemon1_name VARCHAR2(100);
                    v_pokemon1_img VARCHAR2(255);
                    v_pokemon2_id NUMBER(10);
                    v_pokemon2_name VARCHAR2(100);
                    v_pokemon2_img VARCHAR2(255);
                    v_pokemon3_id NUMBER(10);
                    v_pokemon3_name VARCHAR2(100);
                    v_pokemon3_img VARCHAR2(255);
                BEGIN
                    SELECT ID, NAME, IMAGE
                    INTO v_pokemon1_id, v_pokemon1_name, v_pokemon1_img
                    FROM pokemon
                    ORDER BY DBMS_RANDOM.VALUE
                    FETCH FIRST 1 ROWS ONLY;

                    SELECT ID, NAME, IMAGE
                    INTO v_pokemon2_id, v_pokemon2_name, v_pokemon2_img
                    FROM pokemon
                    WHERE ID != v_pokemon1_id
                    ORDER BY DBMS_RANDOM.VALUE
                    FETCH FIRST 1 ROWS ONLY;

                    SELECT ID, NAME, IMAGE
                    INTO v_pokemon3_id, v_pokemon3_name, v_pokemon3_img
                    FROM pokemon
                    WHERE ID NOT IN (v_pokemon1_id, v_pokemon2_id)
                    ORDER BY DBMS_RANDOM.VALUE
                    FETCH FIRST 1 ROWS ONLY;

                    -- SDUNGEON 테이블에 업데이트
                    UPDATE SDUNGEON
                    SET pokemon1_id = v_pokemon1_id,
                        pokemon1_name = v_pokemon1_name,
                        pokemon1_img = v_pokemon1_img,
                        pokemon2_id = v_pokemon2_id,
                        pokemon2_name = v_pokemon2_name,
                        pokemon2_img = v_pokemon2_img,
                        pokemon3_id = v_pokemon3_id,
                        pokemon3_name = v_pokemon3_name,
                        pokemon3_img = v_pokemon3_img
                    WHERE id = rec.id;
                END;
            END LOOP;
        END;
    """)
    void resetPokemonData();
    
    
    // player 가입시 sdungeon 생성
    @Insert("""
    	    INSERT INTO SDUNGEON (id, daily_clear_count, weekly_clear_count, total_count, gamemoney, 
    	        pokemon1_id, pokemon1_name, pokemon1_img, 
    	        pokemon2_id, pokemon2_name, pokemon2_img, 
    	        pokemon3_id, pokemon3_name, pokemon3_img)
    	    WITH RandomPokemon AS (
    	        SELECT ID, NAME, IMAGE, ROWNUM AS rn
    	        FROM POKEMON
    	        ORDER BY DBMS_RANDOM.VALUE
    	        FETCH FIRST 3 ROWS ONLY
    	    )
    	    SELECT 
    	        #{id}, 
    	        #{dailyClearCount}, 
    	        #{weeklyClearCount}, 
    	        #{totalCount}, 
    	        #{gameMoney},
    	        MAX(CASE WHEN rn = 1 THEN ID END),
    	        MAX(CASE WHEN rn = 1 THEN NAME END),
    	        MAX(CASE WHEN rn = 1 THEN IMAGE END),
    	        MAX(CASE WHEN rn = 2 THEN ID END),
    	        MAX(CASE WHEN rn = 2 THEN NAME END),
    	        MAX(CASE WHEN rn = 2 THEN IMAGE END),
    	        MAX(CASE WHEN rn = 3 THEN ID END),
    	        MAX(CASE WHEN rn = 3 THEN NAME END),
    	        MAX(CASE WHEN rn = 3 THEN IMAGE END)
    	    FROM RandomPokemon
    	""")
    	void insertSdungeon(SdungeonDTO sdungeonDto);


    
    
    // sdungeon 데이터 가져오기
	SdungeonDTO findById(String id);
	
    // SDungeonDTO를 업데이트하는 메서드
    @Update("UPDATE sdungeon SET daily_clear_count = #{dailyClearCount}, " +
            "weekly_clear_count = #{weeklyClearCount}, total_count = #{totalCount},  gamemoney = #{gameMoney} " +
            "WHERE id = #{id}")
    void updateSdungeonCount(SdungeonDTO sdungeonDto);
    
    // SdungeonDTO를 가져오는 메서드
    SdungeonDTO getSdungeonById(String playerId);
}