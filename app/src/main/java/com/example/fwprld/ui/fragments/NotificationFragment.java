package com.example.fwprld.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fwprld.R;
import com.example.fwprld.ui.activities.CommentsActivity;
import com.example.fwprld.ui.activities.FriendsActivity;
import com.example.fwprld.ui.activities.GiftActivity;
import com.example.fwprld.ui.activities.NotiMentionActivity;

public class NotificationFragment extends Fragment {

    View view;
    CardView cardFriends, cardMention, cardChat, cardGift;

    public NotificationFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_notification, container, false);

        cardFriends = view.findViewById(R.id.cardFriends);
        cardMention = view.findViewById(R.id.cardMention);
        cardChat = view.findViewById(R.id.cardChat);
        cardGift = view.findViewById(R.id.cardGift);

        cardFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FriendsActivity.class));
            }
        });

        cardMention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NotiMentionActivity.class));
            }
        });

        cardChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CommentsActivity.class));
            }
        });

        cardGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GiftActivity.class));
            }
        });



        return view;
    }

}
