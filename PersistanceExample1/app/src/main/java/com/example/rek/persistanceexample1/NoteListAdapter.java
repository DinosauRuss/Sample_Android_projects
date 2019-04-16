package com.example.rek.persistanceexample1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    public static final String ID = "note_id";

    private List<Note> mNotes;
    private LayoutInflater inflato;
    private Context mContext;
    private OnDeleteClickListener mDeleteListener;

    public interface OnDeleteClickListener {
        void onDeleteClicked(Note note);
    }

    public NoteListAdapter(Context context, OnDeleteClickListener deleteListener) {
        inflato = LayoutInflater.from(context);
        mContext = context;
        mDeleteListener = deleteListener;
    }

    @NonNull
    @Override
    public NoteListAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflato.inflate(R.layout.list_item, parent, false);

        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteListAdapter.NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note currentNote = mNotes.get(position);

            holder.setData(currentNote, position);
            holder.setListeners();
        }
    }

    @Override
    public int getItemCount() {
        if (mNotes != null) {
            return mNotes.size();
        } else {
            return 0;
        }
    }

    /**
     * Notifies adapter that dataset has changed
     * Called by LiveData observer in MainActivity
     * @param notes Dataset
     */
    public void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mEditImage;
        private ImageView mDeleteImage;
        private int mPosition;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.tvItemNote);
            mEditImage = itemView.findViewById(R.id.ivRowEdit);
            mDeleteImage = itemView.findViewById(R.id.ivRowDelete);
        }

        public void setData(Note note, int position) {
            mPosition = position;
            mTextView.setText(note.getNoteText());
        }

        /**
         * Add clickListeners to imageviews in each list_item
         */
        public void setListeners() {
            mEditImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intento = new Intent(mContext, NoteEditActivity.class);
                    intento.putExtra( ID, mNotes.get(mPosition).getId() );
                    ((Activity) mContext).startActivityForResult(intento,
                            MainActivity.UPDATE_NOTE_ACTIVITY_RESULT_CODE);
                }
            });

            mDeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDeleteListener.onDeleteClicked(mNotes.get(mPosition));
                }
            });
        }
    }
}
