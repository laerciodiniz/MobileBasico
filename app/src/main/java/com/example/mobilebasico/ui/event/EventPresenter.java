package com.example.mobilebasico.ui.event;

import android.util.Log;

import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.model.Users;
import com.example.mobilebasico.utils.DataCustom;

import java.sql.SQLException;
import java.util.List;

public class EventPresenter implements EventContract.Presenter {

    private static final String TAG = EventPresenter.class.getSimpleName();

    EventFragment view;
    AppDbHelper appDbHelper;

    public EventPresenter(EventFragment view, AppDbHelper appDbHelper) {
        this.view = view;
        this.appDbHelper = appDbHelper;
    }

    @Override
    public void insertEvent(String userMail, String dataEvento, String descricaoEvento) throws SQLException {

        AppDbHelper helper = new AppDbHelper(view.getContext());

        Events event = new Events();

        Users user = helper.queryUser(userMail);

        event.setUser_id(user);
        event.setDate_create( DataCustom.dataAtual() );
        event.setDate_event( dataEvento );
        event.setDescription( descricaoEvento );

        Log.i(TAG,"ADD EVENT" + event);

        helper.insertEvent(event);
    }

    @Override
    public void checkValues(String userMail, String dataEvento, String descricaoEvento) throws SQLException {

        if ( !dataEvento.isEmpty() ){
            if ( !descricaoEvento.isEmpty() ) {

                insertEvent(userMail, dataEvento, descricaoEvento);

            }else {
                view.onMessage("Informe uma descrição.");
            }
        }else {
            view.onMessage("Informe a data do evento.");
        }

    }

    @Override
    public List<Events> getListEvents(int userId) throws SQLException {
        AppDbHelper helper = new AppDbHelper(view.getContext());
        userId = 7;
        return helper.queryEvents(userId);
    }
}
