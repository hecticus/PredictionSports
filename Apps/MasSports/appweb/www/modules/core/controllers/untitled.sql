-- --------------------------------------------------------------------------------
-- Routine DDL
-- Note: comments before and after the routine body will not be stored by the server
-- --------------------------------------------------------------------------------
DELIMITER $$

CREATE DEFINER=`admin`@`%` PROCEDURE `sp_LeaderboardCalculatorNew`()
BEGIN

	INSERT INTO `tim_sports_panama`.`leaderboard`
	(`id_client`,
	`id_tournament`,
	`id_phase`,
	`score`,
	`correct_bets`, `sport_id` )
	select id_client,id_tournament,id_phase, 0 ,0, sport_id
	from client_bets
	ON DUPLICATE KEY UPDATE id_leaderboard = id_leaderboard;


	INSERT INTO `tim_sports_panama`.`leaderboard_global`
	(
	`id_client`,
	`id_tournament`,
	`score`,
	`correct_bets`, `sport_id` )
	select id_client,id_tournament, 0 ,0, sport_id
	from client_bets
	ON DUPLICATE KEY UPDATE id_leaderboard_global = id_leaderboard_global;

	UPDATE leaderboard_total inner join 
	(
		SELECT    id_client, count(1)*50 result, score sc, count(1) p
		FROM (
			SELECT 
				cb.id_client, 
				c.login,
				cb.id_tournament, 
				gm.id_game_matches, 
				cb.client_bet, 
				lb.score,
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
			FROM 	
				tim_sports_panama.clients AS c, 
				tim_sports_panama.client_bets AS cb, 
				football_manager_panama.game_matches AS gm,
				tim_sports_panama.leaderboard_total as lb
			WHERE 
				c.id_client = cb.id_client AND
				#c.id_client = 5611 AND 
				c.status = 1 AND
				cb.id_game_match = gm.id_game_matches AND
				lb.id_client = c.id_client  AND
				#AND client_bet = result
				#cb.status = 1 AND
				#gm.id_game_match_status = gms.id_game_match_status AND
				gm.id_game_match_status = 1 AND
				cb.sport_id = 1
		) AS merged
		WHERE 
			client_bet = result
		GROUP BY login, id_client

		UNION

		SELECT    id_client, count(1)*50 result, score sc, count(1) p
		FROM (
			SELECT 
				cb.id_client, 
				c.login,
				cb.id_tournament, 
				gm.id_game, 
				cb.client_bet, 
				lb.score,
				CASE 
					WHEN gm.r_home > gm.r_away THEN
						0 
					ELSE 
						CASE 
							WHEN gm.r_home < gm.r_away THEN 
								2 
							ELSE 
								1 
						END
				END AS result
			FROM 	
				tim_sports_panama.clients AS c, 
				tim_sports_panama.client_bets AS cb, 
				mlb_master.game AS gm,
				tim_sports_panama.leaderboard_total as lb
			WHERE 
				c.id_client = cb.id_client AND
				#c.id_client = 5611 AND 
				c.status = 1 AND
				cb.id_game_match = gm.id_game AND
				lb.id_client = c.id_client  AND
				#AND client_bet = result
				#cb.status = 1 AND
				#gm.id_game_match_status = gms.id_game_match_status AND
				gm.status_id_status = 2 AND
				cb.sport_id = 2
		) AS mergedos
		WHERE 
			client_bet = result
		GROUP BY login, id_client
	)
	AS x ON x.id_client = leaderboard_total.id_client
	SET leaderboard_total.correct_bets = p,
		leaderboard_total.score = result;


	##Segunda actualizacion!

	UPDATE tim_sports_panama.leaderboard_global INNER JOIN 
	(
		SELECT    id_client, count(1)*50 result, score sc, count(1) p, id_competition
		FROM 
		(
			SELECT 
				cb.id_client, 
				c.login,
				cb.id_tournament, 
				gm.id_game_matches, 
				gm.id_competition,
				cb.client_bet, 
				lb.score,
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
			FROM 	
				tim_sports_panama.clients AS c, 
				tim_sports_panama.client_bets AS cb, 
				football_manager_panama.game_matches AS gm,
				tim_sports_panama.leaderboard_global as lb
			WHERE 
				c.id_client = cb.id_client AND
				#c.id_client = 5611 AND 
				c.status = 1 AND
				cb.id_game_match = gm.id_game_matches AND
				lb.id_client = c.id_client and 
				lb.id_tournament= gm.id_competition AND
				#AND client_bet = result
				gm.id_game_match_status = 1 
		) AS merged
		WHERE 
			client_bet = result
		GROUP BY login, id_client,id_tournament

		UNION

		SELECT    id_client, count(1)*50 result, score sc, count(1) p, id_competition
		FROM 
		(
			SELECT 
					cb.id_client, 
					c.login,
					cb.id_tournament, 
					gm.id_game, 
					gm.league_id_league,
					cb.client_bet, 
					lb.score,
					CASE 
						WHEN gm.r_home > gm.r_away THEN
							0 
						ELSE 
							CASE 
								WHEN gm.r_home < gm.r_away THEN 
									2 
								ELSE 
									1 
							END
					END AS result
				FROM 	
					tim_sports_panama.clients AS c, 
					tim_sports_panama.client_bets AS cb, 
					mlb_master.game AS gm,
					tim_sports_panama.leaderboard_total as lb
				WHERE 
					c.id_client = cb.id_client AND
					#c.id_client = 5611 AND 
					c.status = 1 AND
					cb.id_game_match = gm.id_game AND
					lb.id_client = c.id_client  AND
					#AND client_bet = result
					#cb.status = 1 AND
					#gm.id_game_match_status = gms.id_game_match_status AND
					gm.status_id_status = 2 AND
					cb.sport_id = 2
		) AS mergedos
		WHERE 
			client_bet = result
		GROUP BY login, id_client,id_tournament
	)
	AS x on x.id_client = leaderboard_global.id_client AND 
			x.id_competition = leaderboard_global.id_tournament
	SET leaderboard_global.correct_bets = p,
		leaderboard_global.score = result;

END