package com.ayalavalley.laudiokojaiak;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ayalavalley.laudiokojaiak.R;
import com.ayalavalley.laudiokojaiak.model.Evento;

public class EventDetailActivity extends Activity {

    public static final String ARG_ITEM_ID = "item_id";
    private LaudiokoJaiakApplication app = null;
    private TextView titulo = null;
    private TextView hora = null;
    private TextView descripcion = null;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        app = (LaudiokoJaiakApplication) getApplication();

        titulo = (TextView) findViewById(R.id.titulo);
        hora = (TextView) findViewById(R.id.hora);
        descripcion = (TextView) findViewById(R.id.descripcion);

        if (app.euskeraz){
            titulo.setText(app.eventoActual.getTitleEU());
            hora.setText(app.eventoActual.getHoraEU());
            descripcion.setText(app.eventoActual.getDescripcionEU());
        }else {
            titulo.setText(app.eventoActual.getTitleES());
            hora.setText(app.eventoActual.getHoraES());
            descripcion.setText(app.eventoActual.getDescripcionES());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.event_detail, menu);
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
            if(app.euskeraz){
                app.euskeraz = false;
            }else {
                app.euskeraz = true;
            }

            MenuItem langMenuItem = menu.findItem(R.id.action_settings);

            if (app.euskeraz){
                titulo.setText(app.eventoActual.getTitleEU());
                hora.setText(app.eventoActual.getHoraEU());
                descripcion.setText(app.eventoActual.getDescripcionEU());
                setTitle("Gertakizunaren zehaztasunak");
                langMenuItem.setTitle("Hizkuntza aldatu");
            }else {
                titulo.setText(app.eventoActual.getTitleES());
                hora.setText(app.eventoActual.getHoraES());
                descripcion.setText(app.eventoActual.getDescripcionES());
                setTitle("Detalles del evento");
                langMenuItem.setTitle("Cambiar idioma");
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
