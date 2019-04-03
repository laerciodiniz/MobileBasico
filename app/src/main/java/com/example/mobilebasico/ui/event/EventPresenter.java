package com.example.mobilebasico.ui.event;

import android.util.Log;

import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.model.Users;
import com.example.mobilebasico.utils.DateCustom;

import java.sql.SQLException;
import java.util.Date;
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
        event.setDate_create( DateCustom.dataAtual() );//DateCustom.ConvertToDate(DateCustom.dataAtual()) );
        event.setDate_event( dataEvento );
        event.setDescription( descricaoEvento );

        Log.i(TAG,"ADD EVENT" + event + dataEvento);

        helper.insertEvent(event);
    }

    @Override
    public void checkValues(String userMail, String dataEvento, String descricaoEvento) throws SQLException {

        if ( !dataEvento.isEmpty() ){
            if ( !descricaoEvento.isEmpty() ) {

                //Date dateF = DateCustom.ConvertToDate(dataEvento);

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
        return helper.queryEvents( userId );
    }
}
