/**
 * Created by ivanornes on 08/07/14.
 */

package com.ayalavalley.laudiokojaiak.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

    private static final String TAG = DBHelper.class.getSimpleName();

    private static final String DB_NAME = "laudiokojaiak.sqlite";
    private static final int DB_VERSION = 1;

    public static final String EVENTOS_TABLE = "eventos";
    public static final String C_EVENTOS_ID = BaseColumns._ID;
    public static final String C_EVENTOS_TITLE_ES = "titleES";
    public static final String C_EVENTOS_TITLE_EU = "titleEU";
    public static final String C_EVENTOS_HORA_ES = "horaES";
    public static final String C_EVENTOS_HORA_EU = "horaEU";
    public static final String C_EVENTOS_DAY = "day";
    public static final String C_EVENTOS_DESCRIPCION_ES = "descripcionES";
    public static final String C_EVENTOS_DESCRIPCION_EU = "descripcionEU";
    public static final String C_EVENTOS_FAVORITO = "favorito";
    public static final String C_EVENTOS_HASHTAG = "hashtag";
    public static final String C_EVENTOS_ICONO = "icono";
    public static final String C_EVENTOS_LATITUD = "latitud";
    public static final String C_EVENTOS_LONGITUD = "longitud";
    public static final String C_EVENTOS_LUGAR_ES = "lugarES";
    public static final String C_EVENTOS_LUGAR_EU = "lugarEU";
    public static final String C_EVENTOS_ORDEN = "orden";

    public static final int C_EVENTOS_ID_INDEX = 0;
    public static final int C_EVENTOS_TITLE_ES_INDEX = 1;
    public static final int C_EVENTOS_TITLE_EU_INDEX = 2;
    public static final int C_EVENTOS_HORA_ES_INDEX = 3;
    public static final int C_EVENTOS_HORA_EU_INDEX = 4;
    public static final int C_EVENTOS_DAY_INDEX = 5;
    public static final int C_EVENTOS_DESCRIPCION_ES_INDEX = 6;
    public static final int C_EVENTOS_DESCRIPCION_EU_INDEX = 7;
    public static final int C_EVENTOS_FAVORITO_INDEX = 8;
    public static final int C_EVENTOS_HASHTAG_INDEX = 9;
    public static final int C_EVENTOS_ICONO_INDEX = 10;
    public static final int C_EVENTOS_LATITUD_INDEX = 11;
    public static final int C_EVENTOS_LONGITUD_INDEX = 12;
    public static final int C_EVENTOS_LUGAR_ES_INDEX = 13;
    public static final int C_EVENTOS_LUGAR_EU_INDEX = 14;
    public static final int C_EVENTOS_ORDEN_INDEX = 15;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String  sql = "create table " + EVENTOS_TABLE + " ("
                + C_EVENTOS_ID + " int primary key, "
                + C_EVENTOS_TITLE_ES + " text, "
                + C_EVENTOS_TITLE_EU + " text, "
                + C_EVENTOS_HORA_ES + " text, "
                + C_EVENTOS_HORA_EU + " text, "
                + C_EVENTOS_DAY + " int, "
                + C_EVENTOS_DESCRIPCION_ES + " text, "
                + C_EVENTOS_DESCRIPCION_EU + " text, "
                + C_EVENTOS_FAVORITO + " int, "
                + C_EVENTOS_HASHTAG + " text, "
                + C_EVENTOS_ICONO + " text,"
                + C_EVENTOS_LATITUD + " real,"
                + C_EVENTOS_LONGITUD + " real,"
                + C_EVENTOS_LUGAR_ES + " text,"
                + C_EVENTOS_LUGAR_EU + " text,"
                + C_EVENTOS_ORDEN + " int"
                +")";
        db.execSQL(sql);
        Log.d(TAG, "onCreated sql: " + sql + " table: "+EVENTOS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Typically do ALTER TABLE statements, but... we're just in development, so:
        db.execSQL("drop table if exists " + EVENTOS_TABLE);
        Log.d(TAG, "onUpdated");
        onCreate(db); // run onCreate to get the new database
    }

    public static void deleteDB(Context context){
        context.deleteDatabase(DB_NAME);
    }
}
