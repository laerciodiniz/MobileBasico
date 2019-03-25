package com.example.mobilebasico.ui.event;

import com.example.mobilebasico.model.Events;

public interface EventContract {

    interface View{

        void onMessage(String message);

    }

    interface Presenter{

        void checkValues(String dataEvento, String descricaoEvento);

        void insertEvent(Events events);

    }

}
