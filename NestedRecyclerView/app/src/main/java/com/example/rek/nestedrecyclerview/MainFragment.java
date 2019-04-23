package com.example.rek.nestedrecyclerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        createRecyclerView(v);

        return v;
    }

    private void createRecyclerView(View v) {
        RecyclerView recyclo = v.findViewById(R.id.recyclerPrimary);
        recyclo.setLayoutManager( new LinearLayoutManager(getActivity()) );
        RecyclerView.Adapter adapto = new MyOuterAdapter( getActivity() );
        recyclo.setAdapter(adapto);
    }

}
