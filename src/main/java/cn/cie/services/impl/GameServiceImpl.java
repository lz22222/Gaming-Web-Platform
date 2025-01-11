package cn.cie.services.impl;

import cn.cie.entity.Game;
import cn.cie.entity.Kind;
import cn.cie.entity.Tag;
import cn.cie.entity.dto.GameDTO;
import cn.cie.mapper.*;
import cn.cie.services.GameService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.RedisUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private KindMapper kindMapper;
    @Autowired
    private KindmapperMapper kindmapperMapper;
    @Autowired
    private TagmapperMapper tagmapperMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ImgMapper imgMapper;
    @Autowired
    private RedisUtil<GameDTO> redisUtil;

    public Result<GameDTO> getById(Integer id) {
        Game game = gameMapper.selectById(id);
        if (game == null) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        List<Integer> tagIds = tagmapperMapper.selectByGame(id);
        List<Tag> tags = null;
        if (tagIds.size() != 0) {
            tags = tagMapper.selectByIds(tagIds);
        }
        List<String> img = imgMapper.selectByGame(game.getId());
        GameDTO res = new GameDTO(game, tags, img);
        return Result.success(res);
    }

    public Result<List<GameDTO>> getRandomGames() {
        // Get the data from the cache first, and then generate it automatically if it is not available
        List<GameDTO> res = redisUtil.lall("everyday", GameDTO.class);
        for (int i = 0; i < res.toArray().length; i++) {
            System.out.println(res.get(i).toString());
        }
        System.out.println("res:"+res.toArray().toString());
        if (res == null || res.size() == 0) {
            List<Game> allgames = gameMapper.selectByStat(Game.STAT_OK);
            int count = allgames.size();
            Set<Integer> numSet = new HashSet<Integer>();
            Random random = new Random();
            List<Game> games = new ArrayList<Game>();
            // If the number of games is greater than 5, randomly select 5, otherwise select all
            if (count > 5) {
                while (numSet.size() < 5) {
                    numSet.add(random.nextInt(count));
                }
                Iterator i = numSet.iterator();
                while (i.hasNext()) {
                    games.add(allgames.get((Integer) i.next()));
                }
            } else {
                games = allgames;
            }
            res = paresGameDTO(games);
            System.out.println("res:"+res.toArray().toString());
            // Store data in cache
            int tmp = 1000 * 3600 * 24;
            long zero = (System.currentTimeMillis() / tmp * tmp + tmp - TimeZone.getDefault().getRawOffset()) / 1000;
            redisUtil.rpushObjectExAtTime(RedisUtil.EVERYDAY, GameDTO.class, zero, res.toArray());
            System.out.println(redisUtil.lall("everyday", GameDTO.class).size());
            System.out.println("Store data in cache");
        }
        return Result.success(res);
    }

    public Result<List<GameDTO>> newestGames() {
        List<GameDTO> res = redisUtil.lall(RedisUtil.NEWESTGAME, GameDTO.class);
        if (res == null || res.size() == 0) {
            List<Game> games = gameMapper.selectByStatOrderByDate(Game.STAT_OK);
            res = paresGameDTO(games);
            redisUtil.rpushObjectEx(RedisUtil.NEWESTGAME, GameDTO.class, 60 * 10, res.toArray());
        }
        return Result.success(res);
    }

    public Result<List<GameDTO>> preUpGames() {
        List<GameDTO> res = redisUtil.lall(RedisUtil.PRE_UP_GAMES, GameDTO.class);
        if (res == null || res.size() == 0) {
            List<Game> games = gameMapper.selectByStatOrderByDate(Game.STAT_PRE);
            res = paresGameDTO(games);
            redisUtil.rpushObjectEx(RedisUtil.PRE_UP_GAMES, GameDTO.class, 60 * 10, res.toArray());
        }
        return Result.success(res);
    }

    public Result<List<GameDTO>> search(String info) {
        List<Integer> kindIds = kindMapper.selectIdByLikeName(info);
        List<Integer> tagIds = tagMapper.selectIdByLikeName(info);
        List<Integer> gameIdsOfKind = null;
        if (kindIds != null && kindIds.size() > 0) {
            gameIdsOfKind = kindmapperMapper.selectBatchByKinds(kindIds);
        }
        List<Integer> gameIdsOfTag = null;
        if (tagIds != null && tagIds.size() > 0) {
            gameIdsOfTag = tagmapperMapper.selectBatchByTags(tagIds);
        }
        Set<Integer> tmpGameIds = new HashSet<Integer>();
        if (gameIdsOfKind != null && gameIdsOfKind.size() > 0) {
            tmpGameIds.addAll(gameIdsOfKind);
        }
        if (gameIdsOfTag != null && gameIdsOfTag.size() > 0) {
            tmpGameIds.addAll(gameIdsOfTag);
        }
        List<Game> games = null;
        if (tmpGameIds.size() > 0) {
            List<Integer> gameIds = new ArrayList<Integer>(tmpGameIds);
            games = gameMapper.selectByIdsAndInfo(gameIds, info);
        } else {
            games = gameMapper.selectByInfo(info);
        }
        return Result.success(paresGameDTO(games));
    }

    public Result getFreeGames() {
        List<Game> games = gameMapper.selectFreeGames();
        return Result.success(paresGameDTO(games));
    }

    public boolean exists(Integer id) {
        return gameMapper.selectById(id) != null;
    }

    private List<GameDTO> paresGameDTO(List<Game> games) {
        List<GameDTO> gameDTOS = new ArrayList<GameDTO>();
        for (Game game : games) {
            List<String> img = imgMapper.selectByGame(game.getId());                // Get all images
            GameDTO dto = new GameDTO(game, null, img);
            gameDTOS.add(dto);
        }
        return gameDTOS;
    }
}
