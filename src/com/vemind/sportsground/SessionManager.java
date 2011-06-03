package com.vemind.sportsground;

import com.vemind.gwcounter.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SessionManager {
	public static final String DATABASE_CREATE = "create table sessions (sid integer primary key autoincrement, sdate integer not null, "
        + "dips integer not null, pulls integer not null);";
	public static final String DATABASE_NAME = "sessions_db";
	public static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "sessions";
	private static final String KEY_DATE = "sdate";
	private static final String KEY_DIPS = "dips";
	private static final String KEY_PULLS = "pulls";
	private static final String KEY_ROWID = "sid";
	
	private static SessionManager instance;
	
	private long currentSId;
	
	public static synchronized SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}
	
	private SessionManager() {
		currentSId = -1;
	}

	private Context mCtx;
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	public SessionData getCurrentSession() {
		SessionData ret;
		if (currentSId != -1) {
			ret = openSession(currentSId);
		}
		else {
			Cursor cursor = getAllSessions();
			if (cursor.getCount() > 0){
				cursor.moveToLast();
				ret = fillData(cursor);
				currentSId = ret.id;
			}
			else {
				ret = new SessionData(cursor.getCount());
				long dbgl = addSession(ret);
				if (dbgl == -1) Toast.makeText(mCtx, R.string.wrong_data, Toast.LENGTH_SHORT).show();
				currentSId = ret.id;
			}
		}
		return ret;
	}
	
	private SessionData fillData (Cursor cursor) {
		long sId = cursor.getLong(cursor.getColumnIndex(KEY_ROWID));
		long date = cursor.getLong(cursor.getColumnIndex(KEY_DATE));
		int sDips = cursor.getInt(cursor.getColumnIndex(KEY_DIPS));
		int sPulls = cursor.getInt(cursor.getColumnIndex(KEY_PULLS));
		SessionData session = new SessionData(sId, date, sDips, sPulls);
		return session;
	}

	public void saveSession(SessionData session) {
		updateSession(session);
	}
	
	public SessionData openSession (long sId) {
		Cursor cursor = getSession (sId);
		return fillData(cursor);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS speeds");
            onCreate(db);
        }
    }
    
    public SessionManager openDB(Context ctx) throws SQLException {
    	mCtx = ctx;
    	if (mCtx != null) {
    		mDbHelper = new DatabaseHelper(mCtx);
            mDb = mDbHelper.getWritableDatabase();
            }
        return this;
    }
    
    public void close() {
        mDbHelper.close();
    }
    
    public long addSession (SessionData session) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE, session.started);
        cv.put(KEY_DIPS, session.dips);
        cv.put(KEY_PULLS, session.pulls);

        return mDb.insert(DATABASE_TABLE, null, cv);
    }
    
    public boolean updateSession (SessionData session) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE, session.started);
        cv.put(KEY_DIPS, session.dips);
        cv.put(KEY_PULLS, session.pulls);

         return mDb.update(DATABASE_TABLE, cv, KEY_ROWID + "=" + session.id, null) > 0;	
    }
    
    public boolean deleteSession (long rowId) {
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;    	
    }
    
    public Cursor getSession (long rowId) throws SQLException {
        Cursor mCursor =
            mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                    KEY_DATE, KEY_DIPS, KEY_PULLS}, KEY_ROWID + "=" + rowId, null,
                    null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    public Cursor getAllSessions () {
        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_DATE,
                KEY_DIPS, KEY_PULLS}, null, null, null, null, null);
    }
    
    public boolean clearAll () {
    	return mDb.delete(DATABASE_TABLE, null, null) > 0;
    }
	
}
