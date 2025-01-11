package cn.cie.services;

import cn.cie.entity.dto.GameDTO;
import cn.cie.utils.Result;

import java.util.List;


public interface GameService {

    /**
     * Retrieve detailed game information by game ID
     * @param id Game ID
     * @return Result containing GameDTO
     */
    Result<GameDTO> getById(Integer id);

    /**
     * Retrieve random images for daily recommendations
     * @return Result containing a list of GameDTO
     */
    Result<List<GameDTO>> getRandomGames();

    /**
     * Retrieve the newest 5 games from the cache, or from the database if not available
     * @return Result containing a list of GameDTO
     */
    Result<List<GameDTO>> newestGames();

    /**
     * Retrieve the newest 5 games ready for release from the cache, or from the database if not available
     * @return Result containing a list of GameDTO
     */
    Result<List<GameDTO>> preUpGames();

    /**
     * Search games by category, tag, or game information
     * @param info Search keyword
     * @return Result containing a list of GameDTO
     */
    Result<List<GameDTO>> search(String info);

    /**
     * Retrieve free games
     * @return Result containing free games
     */
    Result getFreeGames();

    /**
     * Check if a game exists by its ID
     * @param id Game ID
     * @return True if the game exists, false otherwise
     */
    boolean exists(Integer id);
}
