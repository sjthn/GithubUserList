/*
 * Copyright (c)  2017 Srijith
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.android.example.parcelabledemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by NIYATI on 1/24/2017.
 */

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.UserViewHolder> {

    private Context context;
    private List<User> userList;

    public GithubUserAdapter(List<User> userList) {

        this.userList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        Picasso.with(context).load(userList.get(position).getAvatarUrl()).into(holder.avatarImageView);
        holder.usernameTextView.setText(userList.get(position).getLogin());
        holder.userUrlTextView.setText(userList.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView avatarImageView;
        TextView usernameTextView;
        TextView userUrlTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            avatarImageView = (ImageView) itemView.findViewById(R.id.imageview_avatar);
            usernameTextView = (TextView) itemView.findViewById(R.id.textview_username);
            userUrlTextView = (TextView) itemView.findViewById(R.id.textview_user_url);
        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, UserDetailActivity.class);
            User user = userList.get(getLayoutPosition());
            intent.putExtra("user", user);
            context.startActivity(intent);
        }
    }

}
