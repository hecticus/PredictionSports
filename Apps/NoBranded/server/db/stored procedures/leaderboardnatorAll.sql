#leaderboardnator recibe la fecha today a partir de la cual se calculara el leaderboard y los puntos points que seran asignados a todos los que apostaron
#trabaja haciendo join de football_brazil.client_bets con football_manager.game_matches para filtrar los clientes que tengan apuestas correctas a ser procesadas (el status del client bet
#se usa para filtrar las apuestas a calcular, 1 para no calculada y 3 para calculada). Una apuesta se hace seteando el campo client_bet (0 si gana home, 1 si empatan y 2 si gana away)
#luego por cada row de ese join se llama a writeLeaderboards que se encargara de calcular el leaderboard en una transaccion

CREATE DEFINER=`app`@`10.18%` PROCEDURE `leaderboardnatorAll`(IN today VARCHAR(14), IN points INT)
BEGIN
    
    DECLARE idClient INT;
    DECLARE idTournament INT;
    DECLARE idPhases INT;
    DECLARE idGameMatches INT;
		DECLARE done INT;
        
    DECLARE correctBets CURSOR
    FOR
    SELECT 
			cb.id_client, 
			cb.id_tournament, 
			gm.id_phases,
			gm.id_game_matches
		FROM football_brazil.clients AS c, football_brazil.client_bets AS cb, football_manager.game_matches AS gm, football_manager.game_match_status AS gms 
		WHERE 
			cb.game_match_date <= today AND
			c.id_client = cb.id_client AND
			c.status = 1 AND
			cb.status = 1 AND
			gm.id_game_match_status = gms.id_game_match_status AND
			gms.id_game_match_status = 1 AND
			cb.id_game_match = gm.id_game_matches;
    
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;
    OPEN correctBets;
    pointer:LOOP
      FETCH correctBets INTO idClient,idTournament,idPhases,idGameMatches;
			IF done THEN 
          LEAVE pointer; 
      END IF;
      call writeLeaderboards(idClient, idTournament, idPhases, idGameMatches, points);
		END LOOP pointer;
    CLOSE correctBets;    
END
