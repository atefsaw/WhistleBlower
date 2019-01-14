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
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GroupListAdapter extends RecyclerView.Adapter <GroupListAdapter.GroupViewHolder>{




    public List<GroupItem> groupsItems = Collections.emptyList();
    private OnItemClickListener mListener;
    static Map<String, List<Message>> currentgroupToMessages;
    private int groupId;

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
        public TextView groupId;


        Context context;

        public GroupViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            groupImage = itemView.findViewById(R.id.groupImage);
            groupName = itemView.findViewById(R.id.groupName);
            groupLastMsg = itemView.findViewById(R.id.groupLastMessage);
            groupId = itemView.findViewById(R.id.groupId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // listener.onItemClick(position);
                            // TODO: pass the contacts list from this intent
                            final ArrayList<String> messsagesList = new ArrayList<>();
//                            for (Message message : currentgroupToMessages.get(groupName.getText())) {
//                                messsagesList.add(message.getContent());
//                            }
                            Intent intent = new Intent (v.getContext(), ChatActivity.class);
                            intent.putExtra("GROUP_ID", groupId.getText());
                            intent.putExtra("GROUP_NAME", groupName.getText());
                            intent.putExtra("PHONE_NUMBER", currentPhoneNumber);
                            intent.putStringArrayListExtra("GROUP_MESSAGES", messsagesList);
                            v.getContext().startActivity(intent);
                        }
                    }
                }
            });
        }
    }

    // we can add another passing argument to the adapter
    public GroupListAdapter(String phoneNumber, Map<String, List<Message>> groupToMessages){
//        this.groupsItems = groupItems;
        currentPhoneNumber = phoneNumber;
        currentgroupToMessages = groupToMessages;
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
        holder.groupId.setText(String.valueOf(currentItem.getGroupId()));
    }

    @Override
    public int getItemCount() {
        return groupsItems.size();
    }

//    public void updateGroupItems(GroupItem item){
//        groupsItems.add(item);
//    }

    public void setGroupsItems(List<GroupItem> groupsItems) {
        this.groupsItems = groupsItems;
        notifyDataSetChanged();
    }
}
