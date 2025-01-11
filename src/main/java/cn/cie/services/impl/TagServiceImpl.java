package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Tag;
import cn.cie.entity.Tagmapper;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.GameMapper;
import cn.cie.mapper.ImgMapper;
import cn.cie.mapper.TagMapper;
import cn.cie.mapper.TagmapperMapper;
import cn.cie.services.TagService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.PageUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagmapperMapper tagmapperMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private ImgMapper imgMapper;

    public String getNameById(Integer id) {
        Tag tag = tagMapper.selectById(id);
        if (tag == null) {
            return null;
        }
        return tag.getName();
    }

    public Result<List<Tag>> getAll() {
        return Result.success(tagMapper.selectAll());
    }

    public Result<Tag> addTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        if (1 == tagMapper.insert(tag)) {
            return Result.success(tag);
        } else {
            return Result.fail(MsgCenter.ERROR);
        }
    }

    @Transactional
    public Result addTag(String name, Integer game) {
        // If the game does not exist, return 404.
        if (gameMapper.selectById(game) == null) {
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        Result result = addTag(name);
        if (result.isSuccess()) {
            Tagmapper tagmapper = new Tagmapper();
            tagmapper.setGame(game);
            tagmapper.setTag(((Tag) result.getData()).getId());
            if (1 == tagmapperMapper.insert(tagmapper)) {
                return Result.success();
            }
        }
        return Result.fail(MsgCenter.ERROR);
    }

    public Result addTag(Integer tag, Integer game) {
        // If the game does not exist, return 404.
        if (gameMapper.selectById(game) == null) {
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        Tagmapper tagmapper = new Tagmapper();
        tagmapper.setGame(game);
        tagmapper.setTag(tag);
        if (1 == tagmapperMapper.insert(tagmapper)) {
            return Result.success();
        } else {
            return Result.fail(MsgCenter.ERROR);
        }
    }

    public Result<List<GameDTO>> getGamesByTag(Integer tag, Integer page) {
        // If tag does not exist, return 404.
        if (tagMapper.selectById(tag) == null) {
            return Result.fail(MsgCenter.NOT_FOUND);
        }
        List<Integer> gameIds = tagmapperMapper.selectByTag(tag);                  // Get all game IDs by tag
        List<Game> games = gameMapper.selectByIdsAndStat(gameIds, Game.STAT_OK);
        PageUtil pageUtil = new PageUtil(games.size(), page);
        int size = pageUtil.getStartPos() + 10 > games.size() - 1 ? games.size() : pageUtil.getStartPos() + 10;
        List<GameDTO> gameDTOS = paresGameDTO(games.subList(pageUtil.getStartPos(), size));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("game", gameDTOS);
        map.put("page", pageUtil);
        return Result.success(map);
    }

    private List<GameDTO> paresGameDTO(List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<Tag> tags = null;
            List<Integer> tagIds = tagmapperMapper.selectByGame(game.getId());
            if (tagIds.size() != 0) {
                tags = tagMapper.selectByIds(tagIds);
            }
            List<String> img = imgMapper.selectByGame(game.getId());                // get all imgs
            GameDTO dto = new GameDTO(game, tags, img);
            gameDTOS.add(dto);
        }
        return gameDTOS;
    }
}
