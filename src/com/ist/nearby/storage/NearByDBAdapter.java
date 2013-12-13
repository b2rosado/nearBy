package com.ist.nearby.storage;

import java.util.ArrayList;

import com.ist.nearby.domain.InterestPoint;
import com.ist.nearby.domain.PublicTransport;
import com.ist.nearby.domain.Restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class NearByDBAdapter {
	
	//Transports table columns
	public static final String KEY_TRANSPORTS_ID = "_id";
	public static final String KEY_TRANSPORTS_COMPANY = "company";
	public static final String KEY_TRANSPORTS_TYPE = "type";
	public static final String KEY_TRANSPORTS_PRICE = "price";
	public static final String KEY_TRANSPORTS_SCHEDULE = "schedule";
	public static final String KEY_TRANSPORTS_DESTINATION = "destination";
	
	//Interest Points table columns
	public static final String KEY_INTEREST_POINTS_NAME_ID = "_name";
	public static final String KEY_INTEREST_POINTS_DESCRIPTION = "description";
	public static final String KEY_INTEREST_POINTS_TYPE = "type";
	public static final String KEY_INTEREST_POINTS_PRICE = "price";
	public static final String KEY_INTEREST_POINTS_SCHEDULE = "schedule";
	public static final String KEY_INTEREST_POINTS_VOTES = "number_of_votes";
	public static final String KEY_INTEREST_POINTS_RATING = "rating";
	
	//Restaurants table columns
	public static final String KEY_RESTAURANT_NAME_ID = "_name";
	public static final String KEY_RESTAURANT_TYPE = "type";
	public static final String KEY_RESTAURANT_PRICE = "price";
	public static final String KEY_RESTAURANT_SCHEDULE = "schedule";
	public static final String KEY_RESTAURANT_VOTES = "number_of_votes";
	public static final String KEY_RESTAURANT_RATING = "rating";
	
	private static final String TAG = "NearByDBAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;
	
	private static NearByDBAdapter mDataBaseInstance = null;
	
	//Database information
	
	private static final String DATABASE_NAME = "nearByData";
	private static final String TABLE_PUBLIC_TRANSPORTS = "public_transports";
	private static final String TABLE_INTEREST_POINTS = "interest_points";
	private static final String TABLE_RESTAURANTS = "restaurants";
	private static final int DATABASE_VERSION = 1;
	
	private final Context mCtx;
	
	//Table creation SQL Statements
	
	private static final String CREATE_TABLE_PUBLIC_TRANSPORTS = 
	"CREATE TABLE IF NOT EXISTS " + TABLE_PUBLIC_TRANSPORTS + " (" + KEY_TRANSPORTS_ID + " INTEGER PRIMARY KEY, "
	+ KEY_TRANSPORTS_COMPANY + " TEXT NOT NULL, " + KEY_TRANSPORTS_TYPE + " TEXT NOT NULL, " + KEY_TRANSPORTS_PRICE + " TEXT NOT NULL, "
	+ KEY_TRANSPORTS_SCHEDULE + " TEXT NOT NULL, " + KEY_TRANSPORTS_DESTINATION + " TEXT NOT NULL);";
	
	private static final String CREATE_TABLE_INTEREST_POINTS = 
	"CREATE TABLE IF NOT EXISTS " + TABLE_INTEREST_POINTS + " (" + KEY_INTEREST_POINTS_NAME_ID + " TEXT PRIMARY KEY, " 
	+ KEY_INTEREST_POINTS_DESCRIPTION + " TEXT NOT NULL, " + KEY_INTEREST_POINTS_TYPE + " TEXT NOT NULL, " + KEY_INTEREST_POINTS_PRICE + " TEXT NOT NULL, " 
	+ KEY_INTEREST_POINTS_SCHEDULE + " TEXT NOT NULL, " + KEY_INTEREST_POINTS_VOTES + " INTEGER NOT NULL, " + KEY_INTEREST_POINTS_RATING + " TEXT NOT NULL);";
	
	private static final String CREATE_TABLE_RESTAURANTS = 
	"CREATE TABLE IF NOT EXISTS " + TABLE_RESTAURANTS + " (" + KEY_RESTAURANT_NAME_ID + " TEXT PRIMARY KEY, "
	+ KEY_RESTAURANT_TYPE + " TEXT NOT NULL, " + KEY_RESTAURANT_PRICE + " TEXT NOT NULL, " + KEY_RESTAURANT_SCHEDULE + " TEXT NOT NULL, " 
	+ KEY_RESTAURANT_VOTES + " INTEGER NOT NULL, " + KEY_RESTAURANT_RATING + " TEXT NOT NULL);";
	
	//Table insertion SQL Statements
	
	private static final String TABLE_PUBLIC_TRANSPORTS_INSERT = 
	"INSERT INTO " + TABLE_PUBLIC_TRANSPORTS + " (" + KEY_TRANSPORTS_ID + "," + KEY_TRANSPORTS_COMPANY + "," 
	+ KEY_TRANSPORTS_TYPE + "," + KEY_TRANSPORTS_PRICE + "," + KEY_TRANSPORTS_SCHEDULE + "," + KEY_TRANSPORTS_DESTINATION + ") VALUES (?,?,?,?,?,?)";
	
	private static final String TABLE_INTEREST_POINTS_INSERT = 
	"INSERT INTO " + TABLE_INTEREST_POINTS + " (" + KEY_INTEREST_POINTS_NAME_ID + "," + KEY_INTEREST_POINTS_DESCRIPTION + "," 
	+ KEY_INTEREST_POINTS_TYPE + "," + KEY_INTEREST_POINTS_PRICE + "," + KEY_INTEREST_POINTS_SCHEDULE + "," + KEY_INTEREST_POINTS_VOTES + "," + KEY_INTEREST_POINTS_RATING + ") VALUES (?,?,?,?,?,?,?)";
	
	private static final String TABLE_RESTAURANTS_INSERT = 
	"INSERT INTO " + TABLE_RESTAURANTS + " (" + KEY_RESTAURANT_NAME_ID + "," + KEY_RESTAURANT_TYPE + "," 
	+ KEY_RESTAURANT_PRICE + "," + KEY_RESTAURANT_SCHEDULE + "," + KEY_RESTAURANT_VOTES + "," + KEY_RESTAURANT_RATING + ") VALUES (?,?,?,?,?,?)";
	
	
	//Table deletion SQL Statements
	
	private static final String DROP_TABLE_PUBLIC_TRANSPORTS = 
	"DROP TABLE IF EXISTS " + TABLE_PUBLIC_TRANSPORTS;
	
	private static final String DROP_TABLE_INTEREST_POINTS = 
	"DROP TABLE IF EXISTS " + TABLE_INTEREST_POINTS;
	
	private static final String DROP_TABLE_RESTAURANTS = 
	"DROP TABLE IF EXISTS " + TABLE_RESTAURANTS;
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_TABLE_PUBLIC_TRANSPORTS);
			db.execSQL(CREATE_TABLE_INTEREST_POINTS);
			db.execSQL(CREATE_TABLE_RESTAURANTS);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			db.execSQL(DROP_TABLE_PUBLIC_TRANSPORTS);
			db.execSQL(DROP_TABLE_INTEREST_POINTS);
			db.execSQL(DROP_TABLE_RESTAURANTS);
			onCreate(db);
		}	
	}
	
	//Database singleton
	public synchronized static NearByDBAdapter getInstance(Context ctx) {
		if(mDataBaseInstance == null) {
			mDataBaseInstance = new NearByDBAdapter(ctx);
		}
		return mDataBaseInstance;
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
	
	// ************************************************************************* //	
	
	/**
	 * This updates the public_transports table content
	 *
	 * @param publicTransports An array list with PublicTransports
	 * @return long The array list given as input size or -1 in case of an error
	 */
	public long updateTransports(ArrayList<PublicTransport> publicTransports) {
		
		//Recreates the table every time we update it
		mDb.execSQL(DROP_TABLE_PUBLIC_TRANSPORTS);
		mDb.execSQL(CREATE_TABLE_PUBLIC_TRANSPORTS);
		
		String sql = TABLE_PUBLIC_TRANSPORTS_INSERT;
		SQLiteStatement stmt = mDb.compileStatement(sql);
		
		mDb.beginTransaction();
		try {
			
			for (PublicTransport publicTransport : publicTransports) {
				
				stmt.bindLong(1, publicTransport.getId());
				stmt.bindString(2, publicTransport.getCompany());
				stmt.bindString(3, publicTransport.getType());
				stmt.bindString(4, Float.toString(publicTransport.getPrice()));
				stmt.bindString(5, publicTransport.getSchedule());
				stmt.bindString(6, publicTransport.getDestination());
				
				if(stmt.executeInsert() == -1) {
					stmt.clearBindings();
					mDb.endTransaction();
					return -1;
				} else {
					stmt.clearBindings();
				}
			}
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			return -1;
		} finally {
			mDb.endTransaction();
		}
		return publicTransports.size();
	}
	
	/**
	 *  This method obtains all the available transports at the public_transports table
	 *  
	 *  @return an ArrayList of transports
	 */
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
	
	/**
	 * This fetches a PublicTransport with the specified id
	 *
	 * @param id The PublicTransport id
	 * @return PublicTransport The specified PublicTransport
	 */
	public PublicTransport fetchTransport(int id) {
		
		PublicTransport publicTransport = null;
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_PUBLIC_TRANSPORTS + " WHERE " + KEY_TRANSPORTS_ID + "=?", new String[]{"" + id});
		
		if(cursor != null) {
            cursor.moveToFirst();
            publicTransport = PublicTransport.cursorToPublicTransport(cursor);
            cursor.close();
        }
		return publicTransport;
	}
	
	/**
	 * This updates the interest_points table content
	 *
	 * @param interestPoints An array list with interestPoints
	 * @return long The array list given as input size or -1 in case of an error
	 */
	public long updateInterestPoints(ArrayList<InterestPoint> interestPoints) {
		
		//Recreates the table every time we update it
		mDb.execSQL(DROP_TABLE_INTEREST_POINTS);
		mDb.execSQL(CREATE_TABLE_INTEREST_POINTS);
		
		String sql = TABLE_INTEREST_POINTS_INSERT;
		SQLiteStatement stmt = mDb.compileStatement(sql);
		
		mDb.beginTransaction();
		try {
			
			for (InterestPoint interestPoint : interestPoints) {
				
				stmt.bindString(1, interestPoint.getName());
				stmt.bindString(2, interestPoint.getDescription());
				stmt.bindString(3, interestPoint.getType());
				stmt.bindString(4, Float.toString(interestPoint.getPrice()));
				stmt.bindString(5, interestPoint.getSchedule());
				stmt.bindLong(6, interestPoint.getNumberOfVotes());
				stmt.bindString(7, Float.toString(interestPoint.getRating()));
				
				if(stmt.executeInsert() == -1) {
					stmt.clearBindings();
					mDb.endTransaction();
					return -1;
				} else {
					stmt.clearBindings();
				}
			}
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			return -1;
		} finally {
			mDb.endTransaction();
		}
		return interestPoints.size();
	}
	
	/**
	 *  This method obtains all the available interest points at the interest_points table
	 *  
	 *  @return an ArrayList of interestPoints
	 */
	public ArrayList<InterestPoint> fetchInterestPoints() {
		
		ArrayList<InterestPoint> interestPoints = new ArrayList<InterestPoint>();
		
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_INTEREST_POINTS, null);
		
		while(cursor.moveToNext()) {
			InterestPoint interestPoint = InterestPoint.cursorToInterestPoint(cursor);
			interestPoints.add(interestPoint);
		}
		cursor.close();
		
		return interestPoints;	
	}
	
	/**
	 * This fetches a InterestPoint with the specified id
	 *
	 * @param id The InterestPoint id
	 * @return InterestPoint The specified InterestPoint
	 */
	public InterestPoint fetchInterestPoint(String interestPointName) {
		
		InterestPoint interestPoint = null;
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_INTEREST_POINTS + " WHERE " + KEY_INTEREST_POINTS_NAME_ID + "=?", new String[]{"" + interestPointName});
		
		if(cursor != null) {
            cursor.moveToFirst();
            interestPoint = InterestPoint.cursorToInterestPoint(cursor);
            cursor.close();
        }
		return interestPoint;
	}
	
	/**
	 * This updates a InterestPoint rating
	 *
	 * @param _id The object unique id
	 * @param rating The InterestPoint new rating
	 * 
	 * @return Boolean > 0 if successful, -1 otherwise
	 */
	public boolean updateInterestPointRating(String _id, float rating, int numVotos) {
		
		ContentValues updatedValues = new ContentValues();
		
		updatedValues.put(KEY_INTEREST_POINTS_RATING, Float.toString(rating));
		updatedValues.put(KEY_RESTAURANT_VOTES, numVotos);
		
		return mDb.update(TABLE_INTEREST_POINTS, updatedValues, KEY_INTEREST_POINTS_NAME_ID + "=?", new String[]{"" + _id}) > 0;
	}
	
	/**
	 * This updates the restaurants table content
	 *
	 * @param restaurants An array list with Restaurants
	 * @return long The array list given as input size or -1 in case of an error
	 */
	public long updateRestaurants(ArrayList<Restaurant> restaurants) {
		
		//Recreates the table every time we update it
		mDb.execSQL(DROP_TABLE_RESTAURANTS);
		mDb.execSQL(CREATE_TABLE_RESTAURANTS);
		
		String sql = TABLE_RESTAURANTS_INSERT;
		SQLiteStatement stmt = mDb.compileStatement(sql);
		
		mDb.beginTransaction();
		try {
			
			for (Restaurant restaurant : restaurants) {
				
				stmt.bindString(1, restaurant.getName());
				stmt.bindString(2, restaurant.getType());
				stmt.bindString(3, Float.toString(restaurant.getPrice()));
				stmt.bindString(4, restaurant.getSchedule());
				stmt.bindLong(5, restaurant.getNumberOfVotes());
				stmt.bindString(6, Float.toString(restaurant.getRating()));
				
				if(stmt.executeInsert() == -1) {
					stmt.clearBindings();
					mDb.endTransaction();
					return -1;
				} else {
					stmt.clearBindings();
				}
			}
			mDb.setTransactionSuccessful();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return -1;
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			return -1;
		} finally {
			mDb.endTransaction();
		}
		return restaurants.size();
	}
	
	/**
	 *  This method obtains all the available restaurants at the restaurants table
	 *  
	 *  @return an ArrayList with restaurants
	 */
	public ArrayList<Restaurant> fetchRestaurants() {
		
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_RESTAURANTS, null);
		
		while(cursor.moveToNext()) {
			Restaurant restaurant = Restaurant.cursorToRestaurant(cursor);
			restaurants.add(restaurant);
		}
		cursor.close();
		
		return restaurants;	
	}
	
	/**
	 * This fetches a Restaurant with the specified id
	 *
	 * @param id The Restaurant id
	 * @return Restaurant The specified Restaurant
	 */
	public Restaurant fetchRestaurant(String restaurantName) {
		
		Restaurant restaurant = null;
		Cursor cursor = mDb.rawQuery("SELECT * FROM " + TABLE_RESTAURANTS + " WHERE " + KEY_RESTAURANT_NAME_ID + "=?", new String[]{"" + restaurantName});
		
		if(cursor != null) {
            cursor.moveToFirst();
            restaurant = Restaurant.cursorToRestaurant(cursor);
            cursor.close();
        }
		return restaurant;
	}
	
	/**
	 * This updates a Restaurant rating
	 *
	 * @param _id The object unique id
	 * @param rating The Restaurant new rating
	 * 
	 * @return Boolean > 0 if successful, -1 otherwise
	 */
	public boolean updateRestaurantRating(String _id, float rating, int numVotos) {
		
		ContentValues updatedValues = new ContentValues();
		
		updatedValues.put(KEY_RESTAURANT_RATING, Float.toString(rating));
		updatedValues.put(KEY_RESTAURANT_VOTES, numVotos);
		
		return mDb.update(TABLE_RESTAURANTS, updatedValues, KEY_RESTAURANT_NAME_ID + "=?", new String[]{"" + _id}) > 0;	
	}
}
