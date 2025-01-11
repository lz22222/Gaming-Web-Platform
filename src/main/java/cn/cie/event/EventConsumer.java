package cn.cie.event;

import cn.cie.event.handler.EventHandler;
import cn.cie.utils.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * event consumer，Continuously retrieves events from the event queue and processes them based on their types.
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware, DisposableBean {

    private final Logger logger = Logger.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    @Autowired
    private RedisUtil<EventModel> redisUtil;

    private ThreadPoolExecutor threadPool;

    /**
     *  event types and their corresponding handlers
     */
    private Map<EventType, List<EventHandler>> handlers = new HashMap<EventType, List<EventHandler>>();

    public void afterPropertiesSet() throws Exception {
        // Retrieve all handlers from the context
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                // Iterate through all the handlers and add the event-handler mappings to handlers
                List<EventType> types = entry.getValue().getSupportEvent();
                for (EventType type : types) {
                    if (!handlers.containsKey(type)) {
                        handlers.put(type, new ArrayList<EventHandler>());
                    }
                    handlers.get(type).add(entry.getValue());
                }
            }
        }
        for (Map.Entry<EventType, List<EventHandler>> entry : handlers.entrySet()) {
            System.out.println("handleer");
            EventType eventType = entry.getKey();
            List<EventHandler> eventHandlers = entry.getValue();
            System.out.println(eventType.getValue());
            for (int i = 0; i <eventHandlers.size() ; i++) {
                EventHandler eventHandler = eventHandlers.get(i);
                System.out.println(eventHandler.getClass().getSimpleName());
                System.out.println(eventHandler.getSupportEvent().get(0).getValue());
            }
            // Iterate through all the handlers and add the event-handler mappings to handlers

        }
        // Set the thread pool size to CPU cores * 2
        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    EventModel event = redisUtil.lpopObject(EventModel.EVENT_KEY, EventModel.class);
                    if (event == null) {
                        continue;
                    }
                    if (!handlers.containsKey(event.getEventType())) {
                        logger.error("error event type");
                        continue;
                    }
                    for (EventHandler handler : handlers.get(event.getEventType())) {
                        threadPool.execute(new EventConsumerThread(handler, event));
                    }
                }
            }
        }).start();
    }

    class EventConsumerThread implements Runnable {

        private EventHandler handler;

        private EventModel event;

        public EventConsumerThread(EventHandler handler, EventModel event) {
            this.handler = handler;
            this.event = event;
        }

        public void run() {
            handler.doHandler(event);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void destroy() throws Exception {
        if (threadPool != null) {
            while (threadPool.getQueue().size() != 0 || threadPool.getActiveCount() != 0) {
                // Wait for all tasks to complete
            }
            threadPool.shutdownNow();
        }
    }
}
