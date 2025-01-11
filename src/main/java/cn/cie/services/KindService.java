package cn.cie.services;

import cn.cie.entity.Kind;
import cn.cie.entity.dto.GameDTO;
import cn.cie.utils.Result;

import java.util.List;

public interface KindService {

    /**
     * Retrieve the name of a category by its ID. Returns null if the category does not exist.
     * @param id Category ID
     * @return Category name or null if not found
     */
    String getNameById(Integer id);

    /**
     * Retrieve all categories
     * @return Result containing all categories
     */
    Result<Kind> getAll();

    /**
     * Retrieve all games by category, including game information, category, and tags
     * @param kind Category ID
     * @param page Page number
     * @return Result containing a list of GameDTO
     */
    Result<List<GameDTO>> getGamesByKind(int kind, int page);
}
