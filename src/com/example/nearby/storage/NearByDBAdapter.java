package com.example.nearby.storage;

import java.util.ArrayList;

import com.example.nearby.domain.PublicTransport;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class NearByDBAdapter {

	public static final String KEY_ID = "_id";
	
	//Transports columns
	public static final String KEY_TRANSPORTS_COMPANY = "company";
	public static final String KEY_TRANSPORTS_TYPE = "type";
	public static final String KEY_TRANSPORTS_PRICE = "price";
	public static final String KEY_TRANSPORTS_NUMBER = "number";
	public static final String KEY_TRANSPORTS_SCHEDULE = "schedule";
	
	private static final String TAG = "NearByDBAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private static NearByDBAdapter mDataBaseInstance = null;
	
	private static final String DATABASE_NAME = "nearByData";
	private static final String TABLE_PUBLIC_TRANSPORTS = "public_transports";
	private static final String TABLE_INTEREST_POINTS = "interest_points";
	private static final String TABLE_RESTAURANTS = "restaurants";
	private static final int DATABASE_VERSION = 1;
	private final Context mCtx;
	
	private static final String CREATE_TABLE_PUBLIC_TRANSPORTS = "CREATE TABLE " + TABLE_PUBLIC_TRANSPORTS + " (" + KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ KEY_TRANSPORTS_TYPE + " TEXT NOT NULL, " + KEY_TRANSPORTS_PRICE + " REAL NOT NULL, "
			+ KEY_TRANSPORTS_NUMBER + "INTEGER " + KEY_TRANSPORTS_SCHEDULE + "TEXT NOT NULL);";
			
			 
	private static final String DROP_PUBLIC_TRANSPORTS_TABLE = 
			"DROP TABLE IF EXISTS " + TABLE_PUBLIC_TRANSPORTS;
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_PUBLIC_TRANSPORTS);
			//TODO: Create the remaining tables here
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			 Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
	            db.execSQL(DROP_PUBLIC_TRANSPORTS_TABLE);
	            //TODO: Drop the remaining tables here
	            onCreate(db);
		}	
	}
	
	public NearByDBAdapter(Context ctx) {
		this.mCtx = ctx;
		mDbHelper = new DatabaseHelper(mCtx.getApplicationContext());
		mDb = mDbHelper.getWritableDatabase();
	}
	
	public void close() {
        mDataBaseInstance = null;
        mDb.close();
        mDbHelper.close();
    }
	
	public synchronized static NearByDBAdapter getInstance(Context ctx) {
		if(mDataBaseInstance == null) {
			mDataBaseInstance = new NearByDBAdapter(ctx);
		}
		return mDataBaseInstance;
	}
	
	public ArrayList<PublicTransport> fetchTransports() {
		
		ArrayList<PublicTransport> publicTransports = new ArrayList<PublicTransport>();
		
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_PUBLIC_TRANSPORTS, null);
		
		while(cursor.moveToNext()) {
			PublicTransport publicTransport = PublicTransport.cursorToPublicTransport(cursor);
			publicTransports.add(publicTransport);
		}
		cursor.close();
		
		return publicTransports;	
	}	
}
