package com.example.shuafeia;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class friend_list extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<friend> mFriendData;
    private friendAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        mRecyclerView = view.findViewById(R.id.friend_list_recycleview);
       // setContentView(R.layout.fragment_friend_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // Initialize the ArrayList that will contain the data.
        mFriendData = new ArrayList<>();


        //mAdapter = new RecyclerAdapter(getNames());

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new friendAdapter(this.getActivity(), mFriendData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();



        return view;
    }
    private void initializeData() {
        // Get the resources from the XML file.
        String[] friendList = getResources().getStringArray(R.array.frindNameList);

        TypedArray catsImageResources =getResources().obtainTypedArray(R.array.cat_images);

        // Clear the existing data (to avoid duplication).
        mFriendData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<friendList.length;i++){
            mFriendData.add(new friend(friendList[i],catsImageResources.getResourceId(i,0),i));
        }

        catsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }
}
