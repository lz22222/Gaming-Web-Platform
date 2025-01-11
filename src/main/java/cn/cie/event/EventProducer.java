package cn.cie.event;

import cn.cie.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Event Producer
 */
@Component
public class EventProducer {

    @Autowired
    private RedisUtil redisUtil;

    public long product(EventModel model) {
        return redisUtil.rpushObject(EventModel.EVENT_KEY, EventModel.class, model);
    }

}
