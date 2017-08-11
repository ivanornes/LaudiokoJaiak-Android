package com.ayalavalley.laudiokojaiak;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;

import com.ayalavalley.laudiokojaiak.Adapter.EventoAdapter;
import com.ayalavalley.laudiokojaiak.model.Evento;
import com.ayalavalley.laudiokojaiak.service.EventoService;
import com.ayalavalley.laudiokojaiak.service.OnTaskCompleted;
import com.ayalavalley.laudiokojaiak.service.task.FetchEventosAsyncTask;

import java.util.ArrayList;

public class InitActivity extends ListActivity implements OnTaskCompleted{

    private static final String TAG = InitActivity.class.getSimpleName();

    private ArrayList<Evento> eventos = new ArrayList<Evento>();
    private EventoAdapter adapter = null;
    private static EventoService eventoService = null;
    private LaudiokoJaiakApplication app = null;
    private Menu menu;

    @Override
    public void onTaskCompleted(ArrayList<Evento> eventos) {

        loadEventos();

    }

    private void loadEventos() {

        if (this.eventoService == null){
            this.eventoService = new EventoService(this.getApplicationContext());
        }

        this.eventos = this.eventoService.getEventos();
        this.adapter = new EventoAdapter(this.getApplicationContext(), R.id.eventosList, null,(LaudiokoJaiakApplication)getApplication());

        addItemsToAdapter();

        ListView listView = getListView();
        listView.setAdapter(this.adapter);

        this.adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_activity);

        app = (LaudiokoJaiakApplication) this.getApplication();
        app.euskeraz = true;

        fetchEvents();

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if(getListView().getAdapter().getItem(position).getClass().equals(Evento.class)){
                    Intent intent = new Intent(InitActivity.this, EventDetailActivity.class);
                    app.eventoActual = (Evento)getListView().getAdapter().getItem(position);
                    startActivity(intent);
                }

            }
        });

        loadEventos();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void fetchEvents() {
        FetchEventosAsyncTask fetcheventosAsynctask = new FetchEventosAsyncTask(this.getApplicationContext(),this);
        fetcheventosAsynctask.execute();
    }

    private void addItemsToAdapter() {

        Integer[] days = {15,16,17,27,28,29,30,31};

        for (Integer day : days){

            if(app.euskeraz) {
                this.adapter.addSectionHeaderItem(day + " eguna");
            }else{
                this.adapter.addSectionHeaderItem("día " + day);
            }

            for (Evento evento : this.eventos){
                if (evento.getDay()==day){
                    this.adapter.addItem(evento);
                }
            }

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.init, menu);
        this.menu = menu;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            MenuItem langMenuItem = menu.findItem(R.id.action_settings);
            MenuItem updateMenuItem = menu.findItem(R.id.action_update);

            if(app.euskeraz){
                app.euskeraz = false;
                langMenuItem.setTitle("Cambiar idioma");
                updateMenuItem.setTitle("Actualizar eventos");
            }else {
                app.euskeraz = true;
                langMenuItem.setTitle("Hizkuntza aldatu");
                updateMenuItem.setTitle("Informazioa Eguneratu");
            }

            this.adapter = new EventoAdapter(this.getApplicationContext(),R.id.eventosList, null,(LaudiokoJaiakApplication)getApplication());

            addItemsToAdapter();

            ListView listView = getListView();
            listView.setAdapter(this.adapter);

            return true;

        }else if (id == R.id.action_update){

            fetchEvents();

        }

        return super.onOptionsItemSelected(item);
    }

}
