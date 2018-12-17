package com.android.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GroupListAdapter extends RecyclerView.Adapter <GroupListAdapter.GroupViewHolder>{

    private ArrayList<GroupItem> groupsItems;
    public static class GroupViewHolder extends RecyclerView.ViewHolder{
        public ImageView groupImage;
        public TextView groupName;
        public TextView groupLastMsg;

        public GroupViewHolder(View itemView) {
            super(itemView);
            groupImage = itemView.findViewById(R.id.groupImage);
            groupName = itemView.findViewById(R.id.groupName);
            groupLastMsg = itemView.findViewById(R.id.groupLastMessage);
        }
    }

    // we can add another passing argument to the adapter
    public GroupListAdapter(ArrayList<GroupItem> groupItems){
        this.groupsItems = groupItems;
    }

    @Override
    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View groupView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
        GroupViewHolder groupViewHolder = new GroupViewHolder(groupView);
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
