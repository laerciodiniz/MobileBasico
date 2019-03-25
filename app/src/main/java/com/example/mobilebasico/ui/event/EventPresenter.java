package com.example.mobilebasico.ui.event;

import android.util.Log;

import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.utils.DataCustom;

public class EventPresenter implements EventContract.Presenter {

    private static final String TAG = EventPresenter.class.getSimpleName();

    EventFragment view;
    AppDbHelper appDbHelper;

    public EventPresenter(EventFragment view, AppDbHelper appDbHelper) {
        this.view = view;
        this.appDbHelper = appDbHelper;
    }

    @Override
    public void insertEvent(Events events) {
        AppDbHelper helper = new AppDbHelper(view.getContext());
        helper.insertEvent(events);
    }

    @Override
    public void checkValues(String dataEvento, String descricaoEvento) {

        if ( !dataEvento.isEmpty() ){
            if ( !descricaoEvento.isEmpty() ) {

                Events event = new Events();
                //event.setUser_id();
                event.setDate_create( DataCustom.dataAtual() );
                event.setDate_event( dataEvento );
                event.setDescription( descricaoEvento );

                Log.i(TAG,"ADD EVENT" + event);

                insertEvent(event);

            }else {
                view.onMessage("Informe uma descrição.");
            }
        }else {
            view.onMessage("Informe a data do evento.");
        }

    }
}
