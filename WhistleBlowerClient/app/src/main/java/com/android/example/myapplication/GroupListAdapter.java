package com.android.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class GroupListAdapter extends RecyclerView.Adapter <GroupListAdapter.GroupViewHolder>{


    private ArrayList<GroupItem> groupsItems;
    private OnItemClickListener mListener;

    static String currentPhoneNumber;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class GroupViewHolder extends RecyclerView.ViewHolder{
        public ImageView groupImage;
        public TextView groupName;
        public TextView groupLastMsg;

        Context context;

        public GroupViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            groupImage = itemView.findViewById(R.id.groupImage);
            groupName = itemView.findViewById(R.id.groupName);
            groupLastMsg = itemView.findViewById(R.id.groupLastMessage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // listener.onItemClick(position);
                            // TODO: pass the contacts list from this intent
                            Intent intent = new Intent (v.getContext(), ChatActivity.class);
                            intent.putExtra("GROUP_NAME", groupName.getText());
                            intent.putExtra("CURRENT_PHONE_NUMBER", currentPhoneNumber);
                            v.getContext().startActivity(intent);
                        }
                    }
                }
            });
        }
    }

    // we can add another passing argument to the adapter
    public GroupListAdapter(ArrayList<GroupItem> groupItems, String phoneNumber){
        this.groupsItems = groupItems;
        currentPhoneNumber = phoneNumber;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        GroupViewHolder groupViewHolder = new GroupViewHolder(groupView, mListener);
        return groupViewHolder;
    }

    @Override
    public void onBindViewHolder(GroupViewHolder holder, int position) {
        GroupItem currentItem = this.groupsItems.get(position);

        holder.groupImage.setImageResource(currentItem.getImageResource());
        holder.groupName.setText(currentItem.getGroupName());
        holder.groupLastMsg.setText(currentItem.getGroupLastMessage());
    }

    @Override
    public int getItemCount() {
        return groupsItems.size();
    }

    public void updateGroupItems(GroupItem item){
        groupsItems.add(item);
    }
}
