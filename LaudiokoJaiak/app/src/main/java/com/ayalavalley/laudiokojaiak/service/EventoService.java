/**
 * Created by ivanornes on 08/07/14.
 */

package com.ayalavalley.laudiokojaiak.service;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;

import com.ayalavalley.laudiokojaiak.model.Evento;

import com.ayalavalley.laudiokojaiak.db.dao.EventoDAO;
import com.ayalavalley.laudiokojaiak.model.EventosResponse;
import com.ayalavalley.laudiokojaiak.service.task.FetchEventosAsyncTask;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class EventoService{
    private static final String TAG = EventoService.class.getSimpleName();
    private Context context;

    private EventoDAO eventoDAO;

    public EventoService(Context context) {
        this.context = context;
        this.eventoDAO = new EventoDAO(this.context);
    }

    public void insertOrUpdate(Evento evento) {
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        eventos.add(evento);
        eventoDAO.insert(eventos);
    }

    public void insert(ArrayList<Evento> eventos){
        eventoDAO.insert(eventos);
    }

    public void deleteAll(){
        eventoDAO.deleteAll();
    }

    public ArrayList<Evento> getEventos(){
        return eventoDAO.getEventos();
    }

}
