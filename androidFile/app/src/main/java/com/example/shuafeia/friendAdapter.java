package com.example.shuafeia;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


class friendAdapter extends RecyclerView.Adapter<friendAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<friend> mfriendData;
    private Context mContext;

    friendAdapter(Context context, ArrayList<friend> friendData) {
        this.mfriendData = friendData;
        this.mContext = context;
    }

    @Override
    public friendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.friend_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(friendAdapter.ViewHolder holder, int position) {
        // Get current sport.
        friend currentFriend = mfriendData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentFriend);
    }

    @Override
    public int getItemCount() {
        return mfriendData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mFriendNameText;
        private ImageView mUserIcon;
        private LinearLayout friend_list_item_linearLayout;

        ViewHolder(final View itemView) {
            super(itemView);
            // Initialize the views.
            mFriendNameText = itemView.findViewById(R.id.friend_name_block);
            mUserIcon = itemView.findViewById(R.id.user_icon);
            itemView.setOnClickListener(this);
        }

        void bindTo(final friend currentFriend){
            // Populate the textviews with data.
            mFriendNameText.setText(currentFriend.getName());
            friend_list_item_linearLayout = itemView.findViewById(R.id.friend_list_item_linearLayout);
            if(currentFriend.getNum()%2==0)
            {
                friend_list_item_linearLayout.setBackgroundColor(Color.parseColor("#FFF4EA"));
            }
            Glide.with(mContext).load(currentFriend.getImageResource()).into(mUserIcon);

        }

        @Override
        public void onClick(View v) {
            friend currentCat = mfriendData.get(getAdapterPosition());
            Intent intent = new Intent(mContext,friend_profile.class);
            intent.putExtra("name",currentCat.getName());
            intent.putExtra("icon",currentCat.getImageResource());
            mContext.startActivity(intent);
        }
    }
}

