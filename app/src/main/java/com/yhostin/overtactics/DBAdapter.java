package com.yhostin.overtactics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;
public class DBAdapter {

    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_LASTNAME="last_name";
    static final String KEY_CONTENT= "content_text";
    static final String KEY_EMAIL = "email";
    static final String KEY_TIME = "time";
    static final String KEY_DESTINATARIO = "destinatario_id";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE_CONTACT = "contacts";
    static final String DATABASE_TABLE_MESSAGE = "messages";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE_1=
            "create table contacts (_id integer primary key autoincrement,"
                    + "name text not null," +
                    "last_name text not null," +
                    "email text not null);";
    static final String DATABASE_CREATE_2=
            "create table messages(_id integer primary key autoincrement," +
                    "content_text text not null," +
                    "time text not null," +
                    "destinatario_id integer default 0);";

    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE_2);
                db.execSQL(DATABASE_CREATE_1);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    //---opens the database--
    public DBAdapter open() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database--
    public void close()
    {

        DBHelper.close();

    }

    //---insert a contact into the database--
    public long insertContact(String name,String last_name,String email)
    {

        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_NAME, name);

        initialValues.put(KEY_LASTNAME, last_name);

        initialValues.put(KEY_EMAIL, email);

        return db.insert(DATABASE_TABLE_CONTACT, null, initialValues);

    }

    //---insert a contact into the database--
    public long insertMessage(String content_text,int id_destinatario)
    {
        Date date = new Date();
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_CONTENT, content_text);
        initialValues.put(KEY_TIME, DateFormat.getDateTimeInstance().format(date));
        initialValues.put(KEY_DESTINATARIO,id_destinatario);
        return db.insert(DATABASE_TABLE_MESSAGE, null, initialValues);

    }

    //---deletes a particular contact--
    public boolean deleteContact(long rowId)

    {

        return db.delete(DATABASE_TABLE_CONTACT, KEY_ROWID + "=" + rowId, null) > 0;

    }
    public boolean deleteMessage(long rowId)

    {

        return db.delete(DATABASE_TABLE_MESSAGE, KEY_ROWID + "=" + rowId, null) > 0;

    }


    //---retrieves all the contacts--
    public Cursor getAllContacts()

    {

        return db.query(DATABASE_TABLE_CONTACT, new String[] {KEY_ROWID, KEY_NAME,KEY_LASTNAME,

                KEY_EMAIL}, null, null, null, null, null);

    }

    public Cursor getAllMessages()

    {

        return db.query(DATABASE_TABLE_MESSAGE, new String[] {KEY_ROWID,KEY_CONTENT,KEY_TIME,KEY_DESTINATARIO

        }, null, null, null, null, null);

    }

    //---retrieves a particular contact--
    public Cursor getContact(long rowId) throws SQLException

    {

        Cursor mCursor =

                db.query(true, DATABASE_TABLE_CONTACT, new String[] {KEY_ROWID,

                                KEY_NAME,KEY_LASTNAME,KEY_EMAIL},
                        KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor getMessage(long rowId) throws SQLException

    {

        Cursor mCursor =

                db.query(true, DATABASE_TABLE_MESSAGE, new String[] {KEY_ROWID,
                                KEY_CONTENT,KEY_TIME,KEY_DESTINATARIO},
                        KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //---updates a contact--
    public boolean updateContact(long rowId, String name,String last_name,String email)

    {

        ContentValues args = new ContentValues();

        args.put(KEY_NAME, name);
        args.put(KEY_LASTNAME, last_name);
        args.put(KEY_EMAIL, email);


        return db.update(DATABASE_TABLE_CONTACT, args, KEY_ROWID + "=" + rowId, null) >
                0;

    }
}