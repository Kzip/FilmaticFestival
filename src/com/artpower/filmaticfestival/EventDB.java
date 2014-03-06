package com.artpower.filmaticfestival;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDB {
	
	public static final String DB_FILE = "eventdb.db";
	public static final int DB_VERSION = 0;
	public static final String CREATE_EVENT_TABLE ="CREATE TABLE event (" +
			"id					INTEGER PRIMARY KEY NOT NULL, " +
			"title 				TEXT NOT NULL, " +
			"link				TEXT NOT NULL, " +
			"pub_date 			LONG NOT NULL, " +
			"keywords 			TEXT," +
			"daily_event 		INTEGER NOT NULL, " + // represents a boolean
			"event_location		TEXT NOT NULL, " +
			"event_start_time	INTEGER NOT NULL, " +
			"event_end_time 	INTEGER NOT NULL, " +
			"event_description 	TEXT NOT NULL" +
			"event_concluded	INTEGER NOT NULL" +
			"event_notify		INTEGER NOT NULL" +
			")";
	
	public static final String DROP_EVENT_TABLE ="DROP TABLE IF EXISTS event";
	
	private SQLiteDatabase db = null;
	private DBHelper dbh = null;
	
	public EventDB(Context con) {
		dbh = new DBHelper(con, DB_FILE, null, DB_VERSION);
	}
	
	private void openDbReadable() {
		
	}
	
	private void openDbWriteable() {
		
	}
	
	private void closeDb() {
		
	}
	
	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_EVENT_TABLE);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL(DROP_EVENT_TABLE);
			onCreate(db);
		}
	}
}
