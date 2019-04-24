package com.example.rek.fragmentrecyclerviewpaging;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RecyclerAdapter extends PagedListAdapter<Character, RecyclerAdapter.ViewHolder> {

    private LayoutInflater inflato;
    private ImageClickListener mImageListener;

    private static DiffUtil.ItemCallback<Character> DIFF_CALLBACK = new DiffUtil.ItemCallback<Character>() {
        @Override
        public boolean areItemsTheSame(@NonNull Character oldCharacter, @NonNull Character newCharacter) {
            return oldCharacter.getId() == newCharacter.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Character oldCharacter, @NonNull Character newCharacter) {
            return oldCharacter.getName().equals(newCharacter.getName()) &&
                    oldCharacter.getColor().equals(newCharacter.getName());
        }
    };

    public interface ImageClickListener {
        void respondWelcomeImageclick(Character character);

        void respondEditImageclick(Character character);
    }


    public RecyclerAdapter(Context context, ImageClickListener listener) {
        super(DIFF_CALLBACK);
        this.inflato = LayoutInflater.from(context);
        this.mImageListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d("something", "oncreateviewholder " + String.valueOf(i));
        View v = inflato.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v, mImageListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d("something", "onbind " + String.valueOf(position));
        Character c = getItem(position);
        if (c != null) {
            viewHolder.fillViews(c);
        } else {
            viewHolder.clear();
        }
    }

    public Character getCharacterAtPosition(int position) {
        return getItem(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Character mCharacter;
        private TextView mTvName;
        private TextView mTvColor;

        public ViewHolder(@NonNull View itemView, final ImageClickListener listener) {
            super(itemView);

            mTvName = itemView.findViewById(R.id.tvName);
            mTvColor = itemView.findViewById(R.id.tvColor);

            ImageView mIvWelcome = itemView.findViewById(R.id.imgWelcome);
            mIvWelcome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.respondWelcomeImageclick(mCharacter);
                }
            });

            ImageView mIvEdit = itemView.findViewById(R.id.imgEdit);
            mIvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.respondEditImageclick(mCharacter);
                }
            });
        }

        public void fillViews(Character c) {
            mCharacter = c;
            mTvName.setText(c.getName());
            mTvColor.setText(c.getColor());
        }

        public void clear() {
            mTvName.setText("");
            mTvColor.setText("");
        }
    }

}
