package com.manmeet.droidme.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.manmeet.droidme.R;
import com.manmeet.droidme.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BodyPartFragment extends Fragment {
    public List<Integer> mImageList;
    public int mImageListIndex;
    public BodyPartFragment(){};
    public static final String IMAGE_LIST= "image_list";
    public static final String IMAGE_LIST_ITEM_ID = "image_list_index";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the DroidMe fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part,container,false);

        if (savedInstanceState != null){
            mImageList = savedInstanceState.getIntegerArrayList(IMAGE_LIST);
            mImageListIndex = savedInstanceState.getInt(IMAGE_LIST_ITEM_ID);
        }
        //Get the reference to the ImageView in the fragment_body_part
        final ImageView imageview = rootView.findViewById(R.id.body_part_image_view);
        if (mImageList != null) {
            imageview.setImageResource(mImageList.get(mImageListIndex));
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mImageListIndex < mImageList.size() - 1) {
                        mImageListIndex++;
                    } else {
                        mImageListIndex = 0;
                    }
                    imageview.setImageResource(mImageList.get(mImageListIndex));
                }
            });
            } else {
            Log.i(TAG, "onCreateView: mImageList is null");
        }
        return rootView;
    }

    public void setImageList(List<Integer> imageList){
        mImageList = imageList;
    }

    public void setImageListIndex(int index){
        mImageListIndex = index;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(IMAGE_LIST,(ArrayList<Integer>) mImageList);
        outState.putInt(IMAGE_LIST_ITEM_ID,mImageListIndex);
    }
}
