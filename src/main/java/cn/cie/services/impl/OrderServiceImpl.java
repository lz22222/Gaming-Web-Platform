package cn.cie.services.impl;

import cn.cie.entity.*;
import cn.cie.entity.dto.OrderDTO;
import cn.cie.entity.dto.OrderItemDTO;
import cn.cie.mapper.*;
import cn.cie.services.OrderService;
import cn.cie.utils.MsgCenter;
import cn.cie.utils.PageUtil;
import cn.cie.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderitemMapper orderitemMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private OrdermapperMapper ordermapperMapper;
    @Autowired
    private CodeMapper codeMapper;

    @Transactional
    public Result addOrders(int uid, List<Integer> gids) {
        Order order = new Order();
        order.setUid(uid);
        orderMapper.insert(order);
        double total = 0;
        // Store and price all games
        for (Integer gid : gids) {
            Game game = gameMapper.selectById(gid);
            if (game.getStat() != Game.STAT_OK) {
                return Result.fail(MsgCenter.ERROR_PARAMS);
            }
            Orderitem item = new Orderitem();
            item.setGid(gid);
            // If the current price is not empty, then calculate the current price
            if (game.getDiscount() != null) {
                total += game.getDiscount();
                item.setPrice(game.getDiscount());
            } else {
                total += game.getPrice();
                item.setPrice(game.getPrice());
            }
            orderitemMapper.insert(item);
            Ordermapper mapper = new Ordermapper();
            mapper.setItem(item.getId());
            mapper.setOrder(order.getId());
            ordermapperMapper.insert(mapper);
        }
        // Update prices for all games
        order.setPrice(total);
        orderMapper.update(order);
        return Result.success(order);
    }

    public Result cancelOrder(int uid, int orderid) {
        Order order = orderMapper.selectById(orderid);
        if (order == null || order.getUid() != uid) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        order.setStat(Order.STAT_CANCEL);
        if (1 == orderMapper.update(order)) {
            return Result.success();
        }
        return Result.fail(MsgCenter.ERROR);
    }

    @Transactional
    public Result pay(int uid, int orderid) {
        Order order = orderMapper.selectById(orderid);
        // If the orderid is wrong or the user does not match the order, an error is returned
        if (order == null || order.getUid() != uid) {
            return Result.fail(MsgCenter.ERROR_PARAMS);
        }
        order.setStat(Order.STAT_PAY);
        orderMapper.update(order);
        List<Integer> orderitems = ordermapperMapper.selectByOrder(orderid);
        List<Orderitem> orderitemList = orderitemMapper.selectByIds(orderitems);
        for (Orderitem item : orderitemList) {
            // Generate activation code and insert it into the database
            String uuid = UUID.randomUUID().toString();
            Code code = new Code();
            code.setItem(item.getId());
            code.setUid(uid);
            code.setCode(uuid);
            codeMapper.insert(code);
            item.setCode(code.getId());
            orderitemMapper.update(item);
        }
        return Result.success();
    }

    public boolean exists(int orderid) {
        return orderMapper.selectById(orderid) != null;
    }


    public Result getNotPayOrders(int uid, int page) {
        PageUtil pageUtil = new PageUtil(orderMapper.getOrderNumsByUidAndStat(uid, Order.STAT_NOT_PAY), page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", parseOrderByStatAndPage(uid, Order.STAT_NOT_PAY, pageUtil));
        map.put("page", pageUtil);
        return Result.success(map);
    }

    public Result getPaidOrders(int uid, int page) {
        PageUtil pageUtil = new PageUtil(orderMapper.getOrderNumsByUidAndStat(uid, Order.STAT_PAY), page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", parseOrderByStatAndPage(uid, Order.STAT_PAY, pageUtil));
        map.put("page", pageUtil);
        return Result.success(map);
    }

    public Result getCancelOrders(int uid, int page) {
        PageUtil pageUtil = new PageUtil(orderMapper.getOrderNumsByUidAndStat(uid, Order.STAT_CANCEL), page);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order", parseOrderByStatAndPage(uid, Order.STAT_CANCEL, pageUtil));
        map.put("page", pageUtil);
        return Result.success(map);
    }

    public void autoCancelOrder() {
        Date date = new Date();
        date.setTime(date.getTime() - 1000 * 60 * 15);  // The expiration time is 15 minutes
        orderMapper.updateStatByDate(Order.STAT_NOT_PAY, Order.STAT_CANCEL, date);
    }

    private List<OrderDTO> parseOrderByStatAndPage(Integer uid, Byte stat, PageUtil pageUtil) {
        List<OrderDTO> res = new ArrayList<OrderDTO>();
        List<Order> orders = orderMapper.selectByUidAndStatAndPage(uid, stat, pageUtil.getStartPos(), pageUtil.getSize());
        for (Order order : orders) {
            List<Integer> orderids = ordermapperMapper.selectByOrder(order.getId());
            List<Orderitem> orderitemList = orderitemMapper.selectByIds(orderids);
            List<OrderItemDTO> orderItemDTOList = new ArrayList<OrderItemDTO>();

            for (Orderitem orderitem : orderitemList) {
                Game game = gameMapper.selectById(orderitem.getGid());
                Code code;
                if (orderitem.getCode() != null) {
                    code = codeMapper.selectById(orderitem.getCode());
                } else {
                    code = null;
                }
                orderItemDTOList.add(new OrderItemDTO(orderitem, game, code));
            }
            res.add(new OrderDTO(order, orderItemDTOList));
        }
        return res;
    }
}
