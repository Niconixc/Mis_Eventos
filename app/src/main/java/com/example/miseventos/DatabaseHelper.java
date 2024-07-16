package com.example.miseventos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MisEventos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_SECRET_QUESTION = "secret_question";
    public static final String COLUMN_SECRET_ANSWER = "secret_answer";

    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_EVENT_ID = "event_id";
    public static final String COLUMN_EVENT_TITLE = "title";
    public static final String COLUMN_EVENT_DATE = "date";
    public static final String COLUMN_EVENT_IMPORTANCE = "importance";
    public static final String COLUMN_EVENT_OBSERVATION = "observation";
    public static final String COLUMN_EVENT_PLACE = "place";
    public static final String COLUMN_EVENT_REMINDER_TIME = "reminder_time";
    public static final String COLUMN_USER_ID_FK = "user_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_SECRET_QUESTION + " TEXT, " +
                COLUMN_SECRET_ANSWER + " TEXT)";

        String createEventTable = "CREATE TABLE " + TABLE_EVENTS + "(" +
                COLUMN_EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EVENT_TITLE + " TEXT, " +
                COLUMN_EVENT_DATE + " TEXT, " +
                COLUMN_EVENT_IMPORTANCE + " TEXT, " +
                COLUMN_EVENT_OBSERVATION + " TEXT, " +
                COLUMN_EVENT_PLACE + " TEXT, " +
                COLUMN_EVENT_REMINDER_TIME + " TEXT, " +
                COLUMN_USER_ID_FK + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "))";

        db.execSQL(createUserTable);
        db.execSQL(createEventTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    public void updateUserPassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, newPassword);

        db.update(TABLE_USERS, values, COLUMN_USERNAME + "=?", new String[]{username});
        db.close();
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_SECRET_QUESTION, user.getSecretQuestion());
        values.put(COLUMN_SECRET_ANSWER, user.getSecretAnswer());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_TITLE, event.getTitle());
        values.put(COLUMN_EVENT_DATE, event.getDate());
        values.put(COLUMN_EVENT_IMPORTANCE, event.getImportance());
        values.put(COLUMN_EVENT_OBSERVATION, event.getObservation());
        values.put(COLUMN_EVENT_PLACE, event.getPlace());
        values.put(COLUMN_EVENT_REMINDER_TIME, event.getReminderTime());
        values.put(COLUMN_USER_ID_FK, event.getUserId());

        db.insert(TABLE_EVENTS, null, values);
        db.close();
    }

    public List<Event> getEventsByUserId(int userId) {
        List<Event> events = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EVENTS, null, COLUMN_USER_ID_FK + "=?", new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_EVENT_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TITLE));
                String date = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DATE));
                String importance = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_IMPORTANCE));
                String observation = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_OBSERVATION));
                String place = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_PLACE));
                String reminderTime = cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_REMINDER_TIME));

                Event event = new Event(id, title, date, importance, observation, place, reminderTime, userId);
                events.add(event);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return events;
    }

    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, COLUMN_USER_ID_FK + "=?", new String[]{String.valueOf(userId)});
        db.delete(TABLE_USERS, COLUMN_USER_ID + "=?", new String[]{String.valueOf(userId)});
        db.close();
    }

    public User getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID));
            String secretQuestion = cursor.getString(cursor.getColumnIndex(COLUMN_SECRET_QUESTION));
            String secretAnswer = cursor.getString(cursor.getColumnIndex(COLUMN_SECRET_ANSWER));
            cursor.close();
            return new User(id, username, password, secretQuestion, secretAnswer);
        } else {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }
}
