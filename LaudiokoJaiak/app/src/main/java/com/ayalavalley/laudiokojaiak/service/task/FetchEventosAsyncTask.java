package com.ayalavalley.laudiokojaiak.service.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ayalavalley.laudiokojaiak.db.dao.EventoDAO;
import com.ayalavalley.laudiokojaiak.model.Evento;
import com.ayalavalley.laudiokojaiak.service.EventoService;
import com.ayalavalley.laudiokojaiak.service.OnTaskCompleted;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ivanornes on 08/07/14.
 */
public class FetchEventosAsyncTask extends AsyncTask<ArrayList<Evento>, Void, ArrayList<Evento>> {

    private final String TAG = FetchEventosAsyncTask.class.getSimpleName();
    private Context context;
    private EventoService eventoService;
    public OnTaskCompleted listener;

    public FetchEventosAsyncTask(Context context){
        this.context = context;
        this.eventoService = new EventoService(context);
    }

    public FetchEventosAsyncTask(Context context,OnTaskCompleted listener){
        this.context = context;
        this.eventoService = new EventoService(context);
        this.listener = listener;
    }

    @Override
    protected ArrayList<Evento> doInBackground(ArrayList<Evento>... params) {
        ArrayList<Evento> eventos = null;

        try{
            //final String url = "http://95.85.16.23:49154/eventos";
            final String url = "http://laudiokojaiak.herokuapp.com/eventos.json";

            HttpHeaders requestHeaders = new HttpHeaders();
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

            RestTemplate getRestTemplate = new RestTemplate();

            getRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            getRestTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Evento[]> eventosResponse = getRestTemplate.exchange(url, HttpMethod.GET, requestEntity, Evento[].class);

            if (eventosResponse.getStatusCode()==HttpStatus.OK){

                eventos = new ArrayList<Evento>(Arrays.asList(eventosResponse.getBody()));

                this.eventoService.deleteAll();

                this.eventoService.insert(eventos);

            }

        }catch (HttpClientErrorException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        } catch (Exception e) {
            Log.e("MainActivity", e.getMessage(), e);
        }

        return  eventos;
    }

    @Override
    protected void onPostExecute(ArrayList<Evento> eventos) {
        if (listener!=null)
            listener.onTaskCompleted(eventos);

    }
}