package jp.ac.hal.ths30470;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


/**
 * Created by keima on 15/07/03.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "MovieDB";
    private final static int DB_VERSION = 1;

    private final static String CREATE_TABLE =
            "create table movies(id integer primary key autoincrement ,title text not null,genre text,actor text,rating text,date text)";

    public MyDatabaseHelper(Context context) {
        //ÉfÅ[É^ÉxÅ[ÉXçÏê¨
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        //ÉeÅ[ÉuÉãçÏê¨
        db.execSQL(CREATE_TABLE);

        //ÉTÉìÉvÉãÉfÅ[É^çÏê¨
        //ContentValueÉNÉâÉXÇóòópÇ∑ÇÈ

        ContentValues cv = new ContentValues();


        //DBÇ÷ÇÃë}ì¸
        db.insert("movies", null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}