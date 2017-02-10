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

package com.android.example.githubuserlist;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class UserDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ImageView avatarImageView = (ImageView) findViewById(R.id.imageview_user_detail_avatar);
        TextView userNameTextView = (TextView) findViewById(R.id.textview_user_detail_name);
        TextView userUrlTextView = (TextView) findViewById(R.id.textview_user_detail_url);

        User user = getIntent().getParcelableExtra("user");

        Picasso.with(this).load(user.getAvatarUrl()).into(avatarImageView);
        userNameTextView.setText(getString(R.string.user_name, user.getLogin()));
        userUrlTextView.setText(getString(R.string.user_url, user.getHtmlUrl()));
        BetterLinkMovementMethod movementMethod = BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, userUrlTextView);
        movementMethod.setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
            @Override
            public boolean onClick(TextView textView, String url) {
                getCustomTabIntentInstance().launchUrl(UserDetailActivity.this, Uri.parse(url));
                return true;
            }
        });
    }

    private CustomTabsIntent getCustomTabIntentInstance() {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        return builder.build();
    }

}
