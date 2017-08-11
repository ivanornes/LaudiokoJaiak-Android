/**
 * Created by ivanornes on 09/07/14.
 */
package com.ayalavalley.laudiokojaiak.service;

import com.ayalavalley.laudiokojaiak.model.Evento;

import java.util.ArrayList;

public interface OnTaskCompleted {
    void onTaskCompleted(ArrayList<Evento> eventos);
}
