package com.example.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DetailsBlogActivity extends AppCompatActivity {

    public static final String IMAGE_URL =
            "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw/" +
                    "3436e16367c8ec2312a0644bebd2694d484eb047/images/sydney_image.jpg";
    public static final String AVATAR_URL =
            "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw/" +
                    "3436e16367c8ec2312a0644bebd2694d484eb047/avatars/avatar1.jpg";

    ImageView imageMain, imageAvatar, imageBack;
    TextView textTitle, textDate, textAuthor, textRating, textViews, textDescription;
    RatingBar ratingBar;
    ProgressBar blogProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_blog);

        imageMain = findViewById(R.id.imageMain);
        imageAvatar = findViewById(R.id.imageAvatar);
        textTitle = findViewById(R.id.textTitle);
        textDate = findViewById(R.id.textDate);
        textAuthor = findViewById(R.id.textAuthor);
        textRating = findViewById(R.id.textRating);
        textViews = findViewById(R.id.textViews);
        textDescription = findViewById(R.id.textDescription);
        ratingBar = findViewById(R.id.ratingBar);
        imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(v -> finish());
        blogProgressBar = findViewById(R.id.blogProgressBar);

        LoadData();
    }

    public void LoadData() {
        BlogHttpClient.INSTANCE.loadBlogArticles(new BlogArticlesCallback() {
            @Override
            public void onSuccess(List<Blog> blogList) {
                runOnUiThread(() -> showData(blogList.get(0)));
            }

            @Override
            public void onError() {
                runOnUiThread(() -> showErrorSnackbar());

            }
        });
    }

    private void showErrorSnackbar() {
        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, "Error during loading blog articles", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.orange500));
        snackbar.setAction("Retry", v -> {
           LoadData();
           snackbar.dismiss();
        });
        snackbar.show();
    }

    private void showData(Blog blog) {
        blogProgressBar.setVisibility(View.GONE);
        textAuthor.setText(blog.getAuthor().getName());
        textDate.setText(blog.getDate());
        textDescription.setText(Html.fromHtml(blog.getDescription()));
        textTitle.setText(blog.getTitle());
        textViews.setText(String.format("(%d views)", blog.getViews()));
        textRating.setText(String.valueOf(blog.getRating()));
        ratingBar.setRating(blog.getRating());

        Glide.with(this)
                .load(blog.getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageMain);

        Glide.with(this)
                .load(blog.getAuthor().getAvtar())
                .transform(new CircleCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar);

    }
}