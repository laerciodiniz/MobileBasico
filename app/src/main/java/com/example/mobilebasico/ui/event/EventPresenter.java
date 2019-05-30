package com.example.mobilebasico.ui.event;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

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
    private SharedPreferences mPrefs;

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
    public void deleteEvent(int eventId) {
        AppDbHelper helper = new AppDbHelper(view.getContext());
        helper.deleteEvent( eventId );
        view.onMessage("Evento excluído com sucesso.");
    }

    @Override
    public void checkValues(String userMail, String dataEvento, String descricaoEvento) throws SQLException {

        if ( !dataEvento.isEmpty() ){
            if ( !descricaoEvento.isEmpty() ) {

                //Date dateF = DateCustom.ConvertToDate(dataEvento);

                insertEvent(userMail, dataEvento, descricaoEvento);
                sendMail(descricaoEvento + " - " + dataEvento);
                view.onMessage("Evento adicionado com sucesso.");
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

    @Override
    public void sendMail(String DescricaoEvento) {

//        mPrefs = view.getContext().getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(view.getContext());

        boolean sendMail =  mPrefs.getBoolean("send_mail",true);

        if ( sendMail ) {

            Intent mailSelect = new Intent(Intent.ACTION_SENDTO);
            mailSelect.setData(Uri.parse("mailto:"));

            final Intent mail = new Intent(Intent.ACTION_SEND);
            mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"laerciodiniz@gmail.com"});
            mail.putExtra(Intent.EXTRA_SUBJECT,"Novo Evento");
            mail.putExtra(Intent.EXTRA_TEXT,DescricaoEvento);
            mail.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            mail.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            mail.setSelector( mailSelect );

            //mail.setType("message/rfc822");
            try{
                if ( mail.resolveActivity(view.getContext().getPackageManager()) != null );{

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            view.startActivity( mail );
                        }
                    }).start();

                }
                // view.startActivity( Intent.createChooser(mail, "Escolha o app de e-mail") );
            }catch (ActivityNotFoundException ex){
                view.onMessage("Falha ao enviar e-mail.");
            }
        }

    }

}
