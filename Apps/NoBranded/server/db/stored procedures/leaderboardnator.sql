#leaderboardnator recibe la fecha today a partir de la cual se calculara el leaderboard y los puntos points que seran asignados a los que tengan apuestas correctas
#trabaja haciendo join de football_brazil.client_bets con football_manager.game_matches para filtrar los clientes que tengan apuestas correctas a ser procesadas (el status del client bet
#se usa para filtrar las apuestas a calcular, 1 para no calculada y 3 para calculada). Una apuesta se hace seteando el campo client_bet (0 si gana home, 1 si empatan y 2 si gana away)
#luego por cada row de ese join se llama a writeLeaderboards que se encargara de calcular el leaderboard en una transaccion

CREATE DEFINER=`app`@`10.18%` PROCEDURE `leaderboardnator`(IN today VARCHAR(14), IN points INT)
BEGIN
    
    DECLARE idClient INT;
    DECLARE idTournament INT;
    DECLARE idPhases INT;
    DECLARE idGameMatches INT;
		DECLARE done INT;
        
    DECLARE correctBets CURSOR
    FOR
    SELECT id_client, id_tournament, id_phases, id_game_matches FROM (
			SELECT 
				cb.id_client, 
				cb.id_tournament, 
				gm.id_phases,
				gm.id_game_matches, 
				cb.client_bet, 
				CASE 
					WHEN gm.home_team_goals > gm.away_team_goals THEN
						0 
					ELSE 
						CASE 
							WHEN gm.home_team_goals < gm.away_team_goals THEN 
								2 
							ELSE 
								1 
						END
				END AS result
			FROM football_brazil.clients AS c, football_brazil.client_bets AS cb, football_manager.game_matches AS gm, football_manager.game_match_status AS gms 
			WHERE 
				cb.game_match_date <= today AND
				c.id_client = cb.id_client AND
				c.status = 1 AND
				cb.status = 1 AND
				gm.id_game_match_status = gms.id_game_match_status AND
				gms.id_game_match_status = 1 AND
				cb.id_game_match = gm.id_game_matches
			) AS merged
		WHERE 
			client_bet = result;
    
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


#writeLeaderboards recibe el cliente, torneo, fase, partido y puntos a asignar por la apuesta hecha por el cliente
#en una transaccion calcula el leaderboard insertando un row nuevo o midificando uno existente (la tabla leaderboard tiene un index unique de
#id_client, id_tournament, id_phase para que esta operacion sea posible), luego calcula el leaderboard global insertando un row nuevo o midificando
#uno existente (la tabla leaderboard_global tiene un index unique de id_client, id_tournament para que esta operacion sea posible) y fianlmente
#actualiza el estado de la apuesta para que no sea recalculada. en caso de algun error durante la transaccion, se ejecuta el rollback de las operaciones
CREATE DEFINER=`app`@`10.18%` PROCEDURE `writeLeaderboards`(IN idClient INT, IN idTournament INT, IN idPhases INT, IN idGameMatches INT, IN points INT)
BEGIN
	
	DECLARE exit handler for sqlexception
	  BEGIN
	    -- ERROR
	  ROLLBACK;
	END;
	
	DECLARE exit handler for sqlwarning
	 BEGIN
	    -- WARNING
	 ROLLBACK;
	END;
	
	START TRANSACTION;
		#Update Leaderboard
		INSERT INTO leaderboard
			(id_client, id_tournament, id_phase, score, correct_bets)
		VALUES
			(idClient, idTournament, idPhases, points, 1)
		ON DUPLICATE KEY UPDATE
			score     = score + points,
			correct_bets = correct_bets + 1;
		
		#Update Leaderboard Global
		INSERT INTO leaderboard_global
			(id_client, id_tournament, score, correct_bets)
		VALUES
			(idClient, idTournament, points, 1)
		ON DUPLICATE KEY UPDATE
			score     = score + points,
			correct_bets = correct_bets + 1;

		#Update Leaderboard Total
		INSERT INTO leaderboard_total
			(id_client, score, correct_bets)
		VALUES
			(idClient, points, 1)
		ON DUPLICATE KEY UPDATE
			score = score + points,
			correct_bets = correct_bets + 1;	
			
		#Insert event for PMC
		INSERT INTO leaderboard_push
			(id_client, score)
		VALUES
			(idClient, points)
		ON DUPLICATE KEY UPDATE
			score = score + points;
		
		#Update Client Bet
		UPDATE client_bets 
		SET 
			status = 3 
		WHERE 
			id_client = idClient AND
			id_game_match = idGameMatches;
	COMMIT;
END


