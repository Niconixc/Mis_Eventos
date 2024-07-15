package com.example.miseventos;

public class Event {
    private int id;
    private String title;
    private String date;
    private String importance;
    private String observation;
    private String place;
    private String reminderTime;
    private int userId;

    public Event(int id, String title, String date, String importance, String observation, String place, String reminderTime, int userId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.importance = importance;
        this.observation = observation;
        this.place = place;
        this.reminderTime = reminderTime;
        this.userId = userId;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getImportance() { return importance; }
    public void setImportance(String importance) { this.importance = importance; }

    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getReminderTime() { return reminderTime; }
    public void setReminderTime(String reminderTime) { this.reminderTime = reminderTime; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
