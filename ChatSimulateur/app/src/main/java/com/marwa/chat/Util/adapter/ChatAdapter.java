package com.marwa.chat.Util.adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.marwa.chat.DB.entity.Chat;
import com.marwa.chat.R;
import com.marwa.chat.Util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private List<Chat> chatList;
    private Context context;

    private String A;
    private String B;


    public ChatAdapter(List<Chat> couponLst, Context ctx, String A, String B) {
        chatList = couponLst;
        context = ctx;
        this.A = A;
        this.B = B;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chat chat = chatList.get(position);


        holder.txtMessage.setText(chat.getMsg().getMsg());
        holder.txtDateMessage.setText(Util.getShordDate(chat.getMsg().getDateMsg()));
        holder.txtUserMessage.setText(chat.getName());



        if (chat.getName().equals(A)) {
            holder.rlLeft.setVisibility(View.VISIBLE);
            holder.rlRight.setVisibility(View.GONE);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorA));
        }
        if (chat.getName().equals(B)) {
            holder.rlLeft.setVisibility(View.GONE);
            holder.rlRight.setVisibility(View.VISIBLE);
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorB));
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public TextView txtMessage;
        public TextView txtDateMessage;
        public TextView txtUserMessage;

        public ImageView rlLeft;
        public ImageView rlRight;


        public ViewHolder(View view) {
            super(view);
            cardView=view.findViewById(R.id.card);
            txtMessage = view.findViewById(R.id.txtMessage);
            txtDateMessage = view.findViewById(R.id.txtDateMsg);
            txtUserMessage = view.findViewById(R.id.txtUSer);
            rlLeft = view.findViewById(R.id.location_iconA);
            rlRight = view.findViewById(R.id.location_iconB);
        }
    }


    public void setFilter(List<Chat> chat) {
        chatList = new ArrayList<>();
        chatList.addAll(chat);
        notifyDataSetChanged();
    }
}