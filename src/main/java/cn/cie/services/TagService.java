package cn.cie.services;

import cn.cie.entity.Tag;
import cn.cie.entity.dto.GameDTO;
import cn.cie.utils.Result;

import java.util.List;

public interface TagService {

    /**
     * Retrieve the tag name by tag ID. Returns null if the tag does not exist.
     * @param id Tag ID
     * @return Tag name or null if not found
     */
    String getNameById(Integer id);

    /**
     * Retrieve all tags
     * @return Result containing a list of tags
     */
    Result<List<Tag>> getAll();

    /**
     * Add a tag without binding it to a game
     * @param name Tag name
     * @return Result containing the added tag
     */
    Result<Tag> addTag(String name);

    /**
     * Add a tag and bind it to a game
     * @param name Tag name
     * @param game Game ID
     * @return Result of the operation
     */
    Result addTag(String name, Integer game);

    /**
     * Bind an existing tag to a game
     * @param tag Tag ID
     * @param game Game ID
     * @return Result of the operation
     */
    Result addTag(Integer tag, Integer game);

    /**
     * Retrieve all games by tag
     * @param tag Tag ID
     * @param page Page number
     * @return Result containing a list of GameDTO
     */
    Result<List<GameDTO>> getGamesByTag(Integer tag, Integer page);

}
