package hu.rozsa.daniel.learningapplication.sixth.db_cp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "testTable";
    public static final String NAME = "name";
    public static final String AGE = "age";
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "test.db";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " +
            AGE + " INTEGER NOT NULL);";

    public MyDBHandler(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
