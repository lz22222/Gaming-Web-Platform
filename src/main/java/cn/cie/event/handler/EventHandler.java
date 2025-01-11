package cn.cie.event.handler;

import cn.cie.event.EventModel;
import cn.cie.event.EventType;

import java.util.List;

/**
 * Event Handling Interface，includes event processing
 */
public interface EventHandler {

    /**
     * Process Event
     */
    void doHandler(EventModel eventModel);

    /**
     * Get Supported Event Types
     * @return
     */
    List<EventType> getSupportEvent();

}
