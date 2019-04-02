package com.example.mobilebasico.ui.event;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilebasico.R;
import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.utils.AppConstants;
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

    public static EventFragment newInstance(String mail, int user){
        Bundle args = new Bundle();
        args.putString(AppConstants.BUNDLE_USER_MAIL, mail);
        args.putInt(AppConstants.BUNDLE_USER_ID, user);
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

        Toast.makeText(getContext(), mUserId + " - " + mUserMail, Toast.LENGTH_LONG).show();

        mPresenter = new EventPresenter(this, appDbHelper);

        //Pega a lista do banco de dados e armazena na Lista que vai par ao adapter
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

        return view ;
    }

    @OnClick(R.id.btnAddEvent)
    public void btnAddEventClick() {

        try {
            mPresenter.checkValues( mUserMail, mDataEvento.getText().toString(), mDescricaoEvento.getText().toString() );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
