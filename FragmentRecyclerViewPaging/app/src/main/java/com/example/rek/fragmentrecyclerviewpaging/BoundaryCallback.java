package com.example.rek.fragmentrecyclerviewpaging;

import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;

public class BoundaryCallback extends PagedList.BoundaryCallback {




    @Override
    public void onItemAtEndLoaded(@NonNull Object itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);

        Log.d("something", "boundary callback");
    }
}
