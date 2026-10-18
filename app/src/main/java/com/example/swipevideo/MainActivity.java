package com.example.swipevideo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ViewPager2 videoViewPager = findViewById(R.id.videoViewPager);

        List<VideoItem> videoItemsList = new ArrayList<>();
        VideoItem rioluVideo = new VideoItem();
        rioluVideo.videoURL = "https://github.com/LucarioWolf/CS460-Lab2/raw/refs/heads/main/Videos/RioluPets.mp4";
        rioluVideo.videoTitle = "Pets for Riolu";
        rioluVideo.videoDescription = "Pets for the best Pokemon known as Riolu :3";

        videoItemsList.add(rioluVideo);

        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));
    }
}