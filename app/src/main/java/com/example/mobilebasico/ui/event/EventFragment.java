package com.example.mobilebasico.ui.event;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.mobilebasico.R;
import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.utils.AppConstants;
import com.example.mobilebasico.utils.DateCustom;
import com.example.mobilebasico.utils.RecyclerItemClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EventFragment extends Fragment implements EventContract.View{

    @BindView(R.id.edtDataEvento)
    TextInputEditText mDataEvento;
    @BindView(R.id.edtDescricaoEvento)
    TextInputEditText mDescricaoEvento;
    @BindView(R.id.eventRecyclerView)
    RecyclerView recyclerView;

    EventPresenter mPresenter;
    AppDbHelper appDbHelper;

    private EventAdapter mAdapter;
    private List<Events> events = new ArrayList<>();
    private String mUserMail;
    private int mUserId;

    public static EventFragment newInstance(String mail, int userId){
        Bundle args = new Bundle();
        args.putString(AppConstants.BUNDLE_USER_MAIL, mail);
        args.putInt(AppConstants.BUNDLE_USER_ID, userId);
        EventFragment f = new EventFragment();
        f.setArguments(args);
        return f;
    }

    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this, view);

        mUserMail = getArguments().getString(AppConstants.BUNDLE_USER_MAIL);
        mUserId = getArguments().getInt(AppConstants.BUNDLE_USER_ID);

        mDataEvento.setText( DateCustom.dataAtual() );
        mDescricaoEvento.requestFocus();

        mPresenter = new EventPresenter(this, appDbHelper);

        //Pega a lista do banco de dados e armazena na Lista que vai para o adapter
        try {
            events = mPresenter.getListEvents( mUserId );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mAdapter = new EventAdapter( events, getContext() );

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter( mAdapter );

        //Evento de clique do RecyclerView
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Events event = events.get(position);
                                onAlertDialog(event.getDate_event(), event.getDescription());
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Events event = events.get(position);
                                onAlertDialogDeleteEvent(position, event.getId(),"Tem certeza que quer excluir o evento " + event.getDate_event() + " - "
                                        + event.getDescription() + "?");
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        return view ;
    }

    @OnClick(R.id.btnAddEvent)
    public void btnAddEventClick() {

        try {
            mPresenter.checkValues( mUserMail, mDataEvento.getText().toString(), mDescricaoEvento.getText().toString() );
            mAdapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAlertDialog(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle( title );
        alertDialog.setMessage( message );

        AlertDialog alert = alertDialog.create();
        alert.show();

    }

    @Override
    public void onAlertDialogDeleteEvent(int position, int eventId, String message) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle( "Excluir evento" );
        alertDialog.setMessage( message );
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.deleteEvent(eventId);
                mAdapter.notifyItemRemoved(position);
            }
        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onMessage("Exclusão cancelada.");
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
