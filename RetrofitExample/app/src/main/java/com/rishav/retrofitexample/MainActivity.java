package com.rishav.retrofitexample;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rishav.retrofitexample.adapters.AnswersAdapter;
import com.rishav.retrofitexample.listener.ApiListener;
import com.rishav.retrofitexample.model.Item;
import com.rishav.retrofitexample.model.Result;
import com.rishav.retrofitexample.model.SOAnswersResponse;
import com.rishav.retrofitexample.networkService.RetrofitClient;
import com.rishav.retrofitexample.networkService.SOService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements ApiListener {

    private AnswersAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RetrofitClient mRetrofitClient;
    public Context context = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize retrofit client
        mRetrofitClient = RetrofitClient.getClientInstance(getApplicationContext());

        mRecyclerView = findViewById(R.id.rv_answers);

        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id, String usertype, String imageUrl) {
                //Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
                loadDialog(imageUrl, usertype, id);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        mRetrofitClient.loadAnswers();
    }


    private void loadDialog(String url, String userType, long userId) {
        // Create custom dialog object
        final Dialog dialog = new Dialog(context);
        // Include dialog.xml file
        dialog.setContentView(R.layout.show_profile);
        // Set dialog title
        dialog.setTitle("Profile");

        ImageView imageView = dialog.findViewById(R.id.image);
        TextView textView = dialog.findViewById(R.id.userType);

        Picasso.get()
                .load(url)
                .resize(250, 250)
                .centerCrop()
                .into(imageView);
        textView.setText(userType);
        dialog.show();
        dialog.setCancelable(true);
    }

    private void showErrorMessage(String url_type, String message) {
        System.out.println("Error: " + url_type);
        Log.d(MainActivity.class.getSimpleName(), message);
    }

    @Override
    public void onSuccessResponse(int id, Result data) {
        switch (id) {
            case SOService.answers:
                SOAnswersResponse response = (SOAnswersResponse) data.getData();
                mAdapter.updateAnswers(response.getItems());
                Log.d("MainActivity", "posts loaded from API");
                break;
            case SOService.answer_tags:
                break;
        }
    }

    @Override
    public void onFailureResponse(int id, Result data) {
        switch (id) {
            case SOService.answers:
                showErrorMessage("Answers", (String) data.getData());
                break;
            case SOService.answer_tags:
                showErrorMessage("Answers with tag", (String) data.getData());
                break;
        }
    }
}
