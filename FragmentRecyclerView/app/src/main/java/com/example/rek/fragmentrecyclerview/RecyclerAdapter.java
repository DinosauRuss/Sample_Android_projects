package com.example.rek.fragmentrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> mData = new ArrayList<>();
    LayoutInflater inflato;
    ImageClickListener mImageListener;

    /**
     * Used to get a handle to MainActivity and list_item clicks
     */
    public interface ImageClickListener {
        void respondImageclick(String name);
    }

    public RecyclerAdapter(Context context) {
        inflato = LayoutInflater.from(context);
        mImageListener = (ImageClickListener) context;
        setData();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflato.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v, mImageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.fillViews(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * Sets sample data for RecyclerView
     */
    private void setData() {
        mData.add("Grimace");
        mData.add("Dinger");
        mData.add("Kermit");
        mData.add("Fozzie");
        mData.add("Piggy");
        mData.add("Gonzo");
        mData.add("Penelope");
        mData.add("Scooter");
        mData.add("Rowlf");
        mData.add("Rizzo");
        mData.add("Beauregard");
        mData.add("Billy Bob");
        mData.add("Camila");
        mData.add("Petunia");
        mData.add("Ratso");
        mData.add("Beaker");
        mData.add("Bunsen");
        mData.add("Waldorf");
        mData.add("Bobo");
        mData.add("Sam");
        mData.add("Constantine");
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private String mName;
        private TextView mTvName;
        private TextView mTvNumber;

        public ViewHolder(@NonNull View itemView, final ImageClickListener listener) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.tvName);
            mTvNumber = itemView.findViewById(R.id.tvNumber);
            ImageView mImageView = itemView.findViewById(R.id.imgAddCircle);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.respondImageclick(mName);
                }
            });
        }

        public void fillViews(int position) {
            mName = mData.get(position);
            mTvName.setText(mName);
            mTvNumber.setText(String.valueOf(position));
        }
    }

}
