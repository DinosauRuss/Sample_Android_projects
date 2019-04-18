package com.example.rek.fragmentrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static String welcomeTag = "welcome";

    private List<Character> mData;
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
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    public void setData(List<Character> allData) {
        mData = allData;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String mName;
        private TextView mTvName;
        private TextView mTvNumber;

        public ViewHolder(@NonNull View itemView, final ImageClickListener listener) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.tvName);
            mTvNumber = itemView.findViewById(R.id.tvColor);

            ImageView mIvWelcome = itemView.findViewById(R.id.imgWelcome);
            mIvWelcome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.respondImageclick(mName);
                }
            });
        }

        public void fillViews(int position) {
            if (mData != null) {
                Character c = mData.get(position);
                mName = c.getName();
                mTvName.setText(mName);
                mTvNumber.setText(c.getColor());
            }
        }
    }

}
