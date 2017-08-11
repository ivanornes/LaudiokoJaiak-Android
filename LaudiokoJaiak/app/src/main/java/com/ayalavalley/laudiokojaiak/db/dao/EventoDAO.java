/**
 * Created by ivanornes on 08/07/14.
 */
package com.ayalavalley.laudiokojaiak.db.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ayalavalley.laudiokojaiak.db.DBHelper;
import com.ayalavalley.laudiokojaiak.db.DBOperations;
import com.ayalavalley.laudiokojaiak.model.Evento;

public class EventoDAO {
    private static final String TAG = EventoDAO.class.getSimpleName();
    private Context context;
    private DBOperations dbOperations;
    private DBHelper dbHelper;

    public EventoDAO(Context context){
        this.context = context;
        dbOperations = new DBOperations(this.context);
        dbHelper = new DBHelper(this.context);
    }

    public void insert(ArrayList<Evento> eventos){

        ContentValues values = new ContentValues();
        if(eventos!=null)
            for (Evento evento : eventos) {
                values.clear();
                values.put(DBHelper.C_EVENTOS_ID, evento.getId());
                values.put(DBHelper.C_EVENTOS_TITLE_ES, evento.getTitleES());
                values.put(DBHelper.C_EVENTOS_TITLE_EU, evento.getTitleEU());
                values.put(DBHelper.C_EVENTOS_HORA_ES, evento.getHoraES());
                values.put(DBHelper.C_EVENTOS_HORA_EU, evento.getHoraEU());
                values.put(DBHelper.C_EVENTOS_DAY, evento.getDay());
                values.put(DBHelper.C_EVENTOS_DESCRIPCION_ES, evento.getDescripcionES());
                values.put(DBHelper.C_EVENTOS_DESCRIPCION_EU, evento.getDescripcionEU());
                values.put(DBHelper.C_EVENTOS_FAVORITO, evento.getFavorito());
                values.put(DBHelper.C_EVENTOS_HASHTAG, evento.getHashtag());
                values.put(DBHelper.C_EVENTOS_ICONO, evento.getIcono());
                values.put(DBHelper.C_EVENTOS_LATITUD, evento.getLatitud());
                values.put(DBHelper.C_EVENTOS_LONGITUD, evento.getLongitud());
                values.put(DBHelper.C_EVENTOS_LUGAR_ES, evento.getLugarES());
                values.put(DBHelper.C_EVENTOS_LUGAR_EU, evento.getLugarEU());
                values.put(DBHelper.C_EVENTOS_ORDEN, evento.getOrden());
                Log.d(TAG, "INSERTED EVENTOS_TABLE");
                dbOperations.insertOrIgnore(values,DBHelper.EVENTOS_TABLE);
            }
    }

    public void deleteAll(){

        SQLiteDatabase dataBase = dbHelper.getReadableDatabase();

        dataBase.delete(DBHelper.EVENTOS_TABLE, null, null);

    }

    public ArrayList<Evento> getEventos() {
        ArrayList<Evento> eventos = new ArrayList<Evento>();

        SQLiteDatabase dataBase = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try{

            cursor = dataBase.query(DBHelper.EVENTOS_TABLE,null,null,null,null,null,DBHelper.C_EVENTOS_ORDEN,null);
            //cursor = dataBase.query(DBHelper.EVENTOS_TABLE, null, null, null, null, null, null);

            if(cursor.moveToFirst()){
                while (cursor.isAfterLast() == false) {
                    Evento evento = new Evento();
                    evento.setId(cursor.getInt(DBHelper.C_EVENTOS_ID_INDEX));
                    evento.setTitleES(cursor.getString(DBHelper.C_EVENTOS_TITLE_ES_INDEX));
                    evento.setTitleEU(cursor.getString(DBHelper.C_EVENTOS_TITLE_EU_INDEX));
                    evento.setHoraES(cursor.getString(DBHelper.C_EVENTOS_HORA_ES_INDEX));
                    evento.setHoraEU(cursor.getString(DBHelper.C_EVENTOS_HORA_EU_INDEX));
                    evento.setDay(cursor.getInt(DBHelper.C_EVENTOS_DAY_INDEX));
                    evento.setDescripcionES(cursor.getString(DBHelper.C_EVENTOS_DESCRIPCION_ES_INDEX));
                    evento.setDescripcionEU(cursor.getString(DBHelper.C_EVENTOS_DESCRIPCION_EU_INDEX));
                    evento.setFavorito(cursor.getInt(DBHelper.C_EVENTOS_FAVORITO_INDEX));
                    evento.setHashtag(cursor.getString(DBHelper.C_EVENTOS_HASHTAG_INDEX));
                    evento.setIcono(cursor.getString(DBHelper.C_EVENTOS_ICONO_INDEX));
                    evento.setLatitud(cursor.getFloat(DBHelper.C_EVENTOS_LATITUD_INDEX));
                    evento.setLongitud(cursor.getFloat(DBHelper.C_EVENTOS_LONGITUD_INDEX));
                    evento.setLugarES(cursor.getString(DBHelper.C_EVENTOS_LUGAR_ES_INDEX));
                    evento.setLugarEU(cursor.getString(DBHelper.C_EVENTOS_LUGAR_EU_INDEX));
                    evento.setOrden(cursor.getInt(DBHelper.C_EVENTOS_ORDEN_INDEX));

                    eventos.add(evento);
                    cursor.moveToNext();
                }
            }
        } finally{
            if(cursor != null) cursor.close();
            if(dataBase != null) dataBase.close();
        }
        return eventos;
    }
}
