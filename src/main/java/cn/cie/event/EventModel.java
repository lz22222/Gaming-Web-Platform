package cn.cie.event;

import java.util.HashMap;
import java.util.Map;

/**
 * Event Entity
 */
public class EventModel {

    /**
     * Key for the event queue in the cache
     */
    public static final String EVENT_KEY = "event";

    /**
     * Event Type
     */
    private EventType eventType;
    /**
     * Event Initiator ID
     */
    private int fromId;
    /**
     * Event Receiver ID
     */
    private int toId;
    /**
     * Entity that triggered the event, such as a comment or like
     */
    private int entityId;
    /**
     * Entity Owner
     */
    private int entityOwnerId;
    /**
     * Optional additional information
     */
    private Map<String ,String> exts = new HashMap<String, String>();

    public EventModel() {

    }

    public EventModel(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventModel setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public int getFromId() {
        return fromId;
    }

    public EventModel setFromId(int fromId) {
        this.fromId = fromId;
        return this;
    }

    public int getToId() {
        return toId;
    }

    public EventModel setToId(int toId) {
        this.toId = toId;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public String getExts(String key) {
        return exts.get(key);
    }

    public EventModel setExts(String key, String value) {
        this.exts.put(key, value);
        return this;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public void setExts(Map<String, String> exts) {
        this.exts = exts;
    }
}
