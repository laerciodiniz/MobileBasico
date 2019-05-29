package com.example.mobilebasico.ui.event;

import com.example.mobilebasico.model.Events;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface EventContract {

    interface View{

        void onMessage(String message);

    }

    interface Presenter{

        void checkValues(String userMail, String dataEvento, String descricaoEvento) throws SQLException;

        void insertEvent(String userMail, String dataEvento, String descricaoEvento) throws SQLException;

        List<Events> getListEvents(int userId) throws SQLException;

        void sendMail(String DescricaoEvento);

    }

}
