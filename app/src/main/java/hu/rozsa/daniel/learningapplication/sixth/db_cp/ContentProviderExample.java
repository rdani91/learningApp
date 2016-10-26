package hu.rozsa.daniel.learningapplication.sixth.db_cp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

import static android.provider.BaseColumns._ID;

public class ContentProviderExample extends ContentProvider {

    static final int ELEMENTS = 1;
    static final int ELEMENT_ID = 2;
    static final UriMatcher uriMatcher;
    private static final String PROVIDER_NAME = "hu.rozsa.daniel.provider.TEST";
    private static final String URL = "content://" + PROVIDER_NAME + "/elements";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "elements", ELEMENTS);
        uriMatcher.addURI(PROVIDER_NAME, "elements/#", ELEMENT_ID);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        MyDBHandler myDBHandler = new MyDBHandler(getContext());
        db = myDBHandler.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(MyDBHandler.TABLE_NAME);

        HashMap<String, String> projectionMap = new HashMap<>();
        projectionMap.put("_ID", "_ID");
        projectionMap.put(MyDBHandler.NAME, MyDBHandler.NAME);
        projectionMap.put(MyDBHandler.AGE, MyDBHandler.AGE);
        switch (uriMatcher.match(uri)) {
            case ELEMENTS:
                qb.setProjectionMap(projectionMap);
                break;

            case ELEMENT_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments()
                                              .get(1));
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (sortOrder == null || "".equals(sortOrder)) {
            sortOrder = MyDBHandler.AGE;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);

        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ELEMENTS:
                return "vnd.android.cursor.dir/" + PROVIDER_NAME + ".elements";
            case ELEMENT_ID:
                return "vnd.android.cursor.item/" + PROVIDER_NAME + ".elements";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(MyDBHandler.TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver()
                        .notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case ELEMENTS:
                count = db.delete(MyDBHandler.TABLE_NAME, selection, selectionArgs);
                break;

            case ELEMENT_ID:
                String id = uri.getPathSegments()
                               .get(1);
                count = db.delete(MyDBHandler.TABLE_NAME, _ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver()
                    .notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count;

        switch (uriMatcher.match(uri)) {
            case ELEMENTS:
                count = db.update(MyDBHandler.TABLE_NAME, values, selection, selectionArgs);
                break;

            case ELEMENT_ID:
                count = db.update(MyDBHandler.TABLE_NAME, values, _ID + " = " + uri.getPathSegments()
                                                                                       .get(1) +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver()
                    .notifyChange(uri, null);
        return count;
    }
}
