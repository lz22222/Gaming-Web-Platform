package cn.cie.services;

import cn.cie.entity.Game;
import cn.cie.utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Admin-related operations
 */
public interface AdminService {

    /**
     * Admin login
     * @param username Admin username
     * @param password Admin password
     * @return Login result
     */
    Result login(String username, String password);

    /**
     * Retrieve all users
     * @param page Current page number
     * @return User Page
     */
    Result getUser(int page);

    /**
     * Restrict account operations
     * @param uid User ID
     * @return Restriction result
     */
    Result restrict(Integer uid);

    /**
     * Remove account restrictions
     * @param uid User ID
     * @return Result of restriction removal
     */
    Result relieve(Integer uid);

    /**
     * Delete a user
     * @param uid User ID
     * @return Deletion result
     */
    Result delete(Integer uid);

    /**
     * Retrieve all games
     * @param page Page number for game retrieval
     * @return GameDTO Page
     */
    Result getGames(int page);

    /**
     * Add a new game
     * @param game Game entity containing name, developer, description, configuration, and price
     * @param kind Array of game categories
     * @param header Game cover image
     * @param pics At least 5 game screenshots
     * @param path Storage path for images
     * @return Result of the game addition
     */
    Result addGame(Game game, Integer[] kind, MultipartFile header, MultipartFile[] pics, String path) throws IOException;

    /**
     * Update game information
     * @param game Game entity with updated information
     * @return Update result
     */
    Result updateGameInfo(Game game);

    /**
     * Retrieve game categories
     * @param game Game ID
     * @return List of game categories
     */
    Result getGameKind(Integer game);

    /**
     * Update game categories
     * @param game Game ID
     * @param kinds List of new category IDs
     * @return Update result
     */
    Result updateGameKind(Integer game, List<Integer> kinds);

    /**
     * Publish a game
     * @param id Game ID
     * @param date Publish date
     * @return Result of publishing
     */
    Result upGame(Integer id, Date date);

    /**
     * Unpublish a game
     * @param id Game ID
     * @return Result of unpublishing
     */
    Result downGame(Integer id);

    /**
     * Add a new game category
     * @param name Category name
     * @return Result of category addition
     */
    Result addKind(String name);

    /**
     * Manage games under a specific category
     * @param kind Category ID
     * @param games List of game IDs
     * @return Management result
     */
    Result managerKind(Integer kind, List<Integer> games);

    /**
     * Retrieve all games
     * @return List of all games
     */
    Result getAllGames();
}

