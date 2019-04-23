package com.example.rek.nestedrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyOuterAdapter extends RecyclerView.Adapter<MyOuterAdapter.ViewHolder> {

    private int mNumOfRecyclers = 13;
    private LayoutInflater inflato;
    private Context contexto;
    private RecyclerView.RecycledViewPool viewPool;

    MyOuterAdapter(Context context) {
        inflato = LayoutInflater.from(context);
        contexto = context;
        viewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflato.inflate(R.layout.list_item_outer, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mTvLabel.setText(String.valueOf(i));
    }

    @Override
    public int getItemCount() {
        return mNumOfRecyclers;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView mRecyclerView;
        private TextView mTvLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mRecyclerView = itemView.findViewById(R.id.recyclerOfRecycler);
            mTvLabel = itemView.findViewById(R.id.tvRecyclerLabel);

            makeRecycler();
        }

        private void makeRecycler() {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(contexto,
                    LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setAdapter(new MyInnerAdapter(contexto));

            mRecyclerView.setRecycledViewPool(viewPool);
        }
    }
}
