package com.ayalavalley.laudiokojaiak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.TreeSet;

import com.ayalavalley.laudiokojaiak.LaudiokoJaiakApplication;
import com.ayalavalley.laudiokojaiak.model.Evento;
import com.ayalavalley.laudiokojaiak.R;

import java.util.ArrayList;

/**
 * Created by ivanornes on 09/07/14.
 */
public class EventoAdapter extends ArrayAdapter<Object> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;

    private Context context;
    public ArrayList<Object> eventos;
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();
    private LayoutInflater mInflater;

    private LaudiokoJaiakApplication app;

    public EventoAdapter(Context context, int resource, ArrayList<Object> eventos, LaudiokoJaiakApplication application) {
        super(context, resource, eventos);
        this.context = context;
        this.eventos = new ArrayList<Object>();
        app = application;

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final Evento item) {
        eventos.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final String item) {
        eventos.add(item);
        sectionHeader.add(eventos.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {
        return (Object)eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        public TextView titulo;
        public TextView hora;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        int rowType = getItemViewType(position);

        if (convertView == null) {

            ViewHolder viewHolder = new ViewHolder();

            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.row_evento, null);
                    viewHolder.titulo = (TextView) convertView.findViewById(R.id.titulo);
                    viewHolder.hora = (TextView) convertView.findViewById(R.id.hora);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.header_dia, null);
                    viewHolder.titulo = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }

            convertView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        switch (rowType) {
            case TYPE_ITEM:
                Evento evento = (Evento)eventos.get(position);
                if (app.euskeraz){
                    holder.titulo.setText(evento.getTitleEU());
                    holder.hora.setText(evento.getHoraEU());
                }else {
                    holder.titulo.setText(evento.getTitleES());
                    holder.hora.setText(evento.getHoraES());
                }

                break;
            case TYPE_SEPARATOR:
                String daySeparator = (String)eventos.get(position);
                holder.titulo.setText(daySeparator);
                break;
        }

        return convertView;
    }
}
