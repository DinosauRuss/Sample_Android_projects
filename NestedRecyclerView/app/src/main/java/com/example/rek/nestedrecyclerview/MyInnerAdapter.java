package com.example.rek.nestedrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MyInnerAdapter extends RecyclerView.Adapter<MyInnerAdapter.ViewHolder> {

    private Context contexto;
    private LayoutInflater inflato;
    private int mNumOfItems = 8;

    public MyInnerAdapter(Context context) {
        inflato = LayoutInflater.from(context);
        contexto = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflato.inflate(R.layout.list_item_inner, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Random rnd = new Random();
        int color = 0xFF000000 | rnd.nextInt(0xFFFFFF);
        String hexString = Integer.toHexString(color);
        String newString = "#" + hexString.substring(2);

        viewHolder.fillViews(newString, color);
        viewHolder.changeCardBackgroundColor(i);
    }

    @Override
    public int getItemCount() {
        return mNumOfItems;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTv;
        private ImageView mImageView;
        private CardView mCardView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            mTv = itemView.findViewById(R.id.tvColorId);
            mImageView = itemView.findViewById(R.id.imgColorBlock);
            mCardView = itemView.findViewById(R.id.cardView);
        }

        public void fillViews(String string, int color) {
            mTv.setText(string);
            mImageView.setColorFilter(color);
        }

        public void changeCardBackgroundColor(int position) {
            int color;
            if (position == 0) {
                color = contexto.getResources().getColor(R.color.cardview_start);
            } else if (position == (mNumOfItems-1) ){
                color = contexto.getResources().getColor(R.color.cardview_end);
            } else {
                color = contexto.getResources().getColor(R.color.cardview);
            }
            mCardView.setCardBackgroundColor(color);
        }
    }

}
