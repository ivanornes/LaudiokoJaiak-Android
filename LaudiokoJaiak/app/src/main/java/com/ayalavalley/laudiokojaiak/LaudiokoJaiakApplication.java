package com.ayalavalley.laudiokojaiak;

import android.app.Application;
import com.ayalavalley.laudiokojaiak.model.Evento;

/**
 * Created by ivanornes on 11/08/14.
 */
public class LaudiokoJaiakApplication extends Application {

    private static final String TAG = LaudiokoJaiakApplication.class.getSimpleName();
    public Evento eventoActual;
    public boolean euskeraz;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
