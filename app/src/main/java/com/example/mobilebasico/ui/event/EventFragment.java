package com.example.mobilebasico.ui.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobilebasico.R;
import com.example.mobilebasico.database.AppDbHelper;
import com.example.mobilebasico.model.Events;
import com.google.android.material.textfield.TextInputEditText;

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
    private int mUserId;

    public static EventFragment newInstance(Events event){
        Bundle args = new Bundle();
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
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

        mPresenter = new EventPresenter(this, appDbHelper);

        mAdapter = new EventAdapter(events, view.getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter( mAdapter );

        return view ;
    }

    @OnClick(R.id.btnAddEvent)
    public void btnAddEventClick(){

        mPresenter.checkValues( mDataEvento.getText().toString(), mDescricaoEvento.getText().toString() );
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
