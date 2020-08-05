package db;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

public class Event {
    private int id;
    private int userId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String title;
    private String description;


    private static final int MSECOND = 1000;


    public Event(String json) {
        JSONObject jsonEvent = new JSONObject(json);
        getFromJSONObject(jsonEvent);
    }

    public Event(JSONObject jsonEvent) {
        getFromJSONObject(jsonEvent);
    }

    public Event() {
    }

    public Event(int id, int userId, Timestamp startTime, Timestamp endTime, String title, String description) {
        this.setId(id);
        this.setUserId(userId);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setTitle(title);
        this.setDescription(description);
    }

    public Event(int id) {
        this.setId(id);
    }

    public Event(int id, int userId) {
        this.setId(id);
        this.setUserId(userId);
    }

    public Event(int id, int userId, String title, String description) {
        this.setId(id);
        this.setUserId(userId);
        this.setStartTime(new Timestamp(System.currentTimeMillis()));
        this.setEndTime(getStartTime());
        this.setTitle(title);
        this.setDescription(description);
    }

    public Event(int userId, String title, String description) {
        this.setUserId(userId);
        this.setStartTime(new Timestamp(System.currentTimeMillis()));
        this.setEndTime(getStartTime());
        this.setTitle(title);
        this.setDescription(description);
    }

    public Event(Timestamp startTime, Timestamp endTime, String title, String description) {
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setTitle(title);
        this.setDescription(description);

    }

    public Event(String title, String description) {
        setTitle(title);
        setDescription(description);
        this.setStartTime(new Timestamp(System.currentTimeMillis()));
        this.setEndTime(getStartTime());

    }

    private void getFromJSONObject(JSONObject jsonEvent) {
        Set<String> keys = jsonEvent.keySet();
        if (keys.contains(FunNotesDB.DatabaseIds.ID)) {
            setId(jsonEvent.getInt(FunNotesDB.DatabaseIds.ID));
        }
        if (keys.contains(FunNotesDB.DatabaseIds.USER_ID)) {
            setUserId(jsonEvent.getInt(FunNotesDB.DatabaseIds.USER_ID));
        }
        if (keys.contains(FunNotesDB.DatabaseIds.START_TIME)) {
            setStartTime(new Timestamp(jsonEvent.getLong(FunNotesDB.DatabaseIds.START_TIME) * MSECOND));
        }
        if (keys.contains(FunNotesDB.DatabaseIds.END_TIME)) {
            setEndTime(new Timestamp(jsonEvent.getLong(FunNotesDB.DatabaseIds.END_TIME) * MSECOND));
        }
        if (keys.contains(FunNotesDB.DatabaseIds.TITLE)) {
            setTitle(jsonEvent.getString(FunNotesDB.DatabaseIds.TITLE));
        }
        if (keys.contains(FunNotesDB.DatabaseIds.DESCRIPTION)) {
            setDescription(jsonEvent.getString(FunNotesDB.DatabaseIds.DESCRIPTION));
        }
    }


    @Override
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        JSONObject jsonEvent = new JSONObject();
        jsonEvent.put(FunNotesDB.DatabaseIds.ID, getId());
        jsonEvent.put(FunNotesDB.DatabaseIds.START_TIME, getStartTime() != null ? getStartTime().getTime() / MSECOND : null);
        jsonEvent.put(FunNotesDB.DatabaseIds.END_TIME, getEndTime() != null ? getEndTime().getTime() / MSECOND : null);
        jsonEvent.put(FunNotesDB.DatabaseIds.TITLE, getTitle());
        jsonEvent.put(FunNotesDB.DatabaseIds.DESCRIPTION, getDescription());
        return jsonEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean equals(Event event) {
        return userId == event.userId &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description);
    }

    public boolean canInsert() {
        return startTime != null && endTime != null && title != null && description != null;
    }
}
