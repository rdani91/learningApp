package hu.rozsa.daniel.learningapplication.sixth.db_cp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyTestDbWrapper {

    private static final MyTestDbWrapper instance = new MyTestDbWrapper();
    private MyDBHandler myDBHandler;

    private MyTestDbWrapper() {

    }

    public static MyTestDbWrapper getInstance() {
        return instance;
    }

    public void initContext(Context c) {
        myDBHandler = new MyDBHandler(c);
    }

    public void insertData(String name, String age) {
        SQLiteDatabase db = myDBHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyDBHandler.NAME, name);
        values.put(MyDBHandler.AGE, age);

        long newRowid = db.insert(MyDBHandler.TABLE_NAME, null, values);
    }

    public List<String> getNamesWithAgeAbove25() {
        List<String> namesWitAge = new ArrayList<>();

        SQLiteDatabase db = myDBHandler.getReadableDatabase();


        Cursor query = db.query(MyDBHandler.TABLE_NAME,

                new String[]{MyDBHandler.NAME,
                        MyDBHandler.AGE},

                MyDBHandler.AGE + "> ?",
                new String[]{"25"},
                null,
                null,
                MyDBHandler.AGE + " DESC");

        while (query.moveToNext()) {
            String tmpString = "";
            tmpString += query.getString(query.getColumnIndex(MyDBHandler.NAME));
            tmpString += " - ";
            tmpString += query.getString(query.getColumnIndex(MyDBHandler.AGE));

            namesWitAge.add(tmpString);
        }
        query.close();

        return namesWitAge;
    }

}
