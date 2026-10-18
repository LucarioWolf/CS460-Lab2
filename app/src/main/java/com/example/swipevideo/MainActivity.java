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

        //Assign the viewPager from the XML elements by ID
        final ViewPager2 videoViewPager = findViewById(R.id.videoViewPager);

        //A list of video objects that will be loaded
        List<VideoItem> videoItemsList = new ArrayList<>();

        //First Video "Pets for Riolu"
        VideoItem rioluVideo = new VideoItem();
        rioluVideo.videoURL = "https://github.com/LucarioWolf/CS460-Lab2/raw/refs/heads/main/Videos/RioluPets.mp4";
        rioluVideo.videoTitle = "Pets for Riolu";
        rioluVideo.videoDescription = "Pets for the best Pokemon known as Riolu :3";
        rioluVideo.videoId = "ID: 953113";
        videoItemsList.add(rioluVideo);

        //Second Video "The silly cat"
        VideoItem catVideo = new VideoItem();
        catVideo.videoURL = "https://github.com/LucarioWolf/CS460-Lab2/raw/refs/heads/main/Videos/CatVideo.mp4";
        catVideo.videoTitle = "The Silly Cat";
        catVideo.videoDescription = "A very cute cat indeed.";
        catVideo.videoId = "ID: 676584";
        videoItemsList.add(catVideo);

        //Third Video "A mother's lullaby"
        VideoItem lullabyVideo = new VideoItem();
        lullabyVideo.videoURL = "https://github.com/LucarioWolf/CS460-Lab2/raw/refs/heads/main/Videos/AvatarLullaby.mp4";
        lullabyVideo.videoTitle = "A mother's lullaby";
        lullabyVideo.videoDescription = "The twin avatars remembering their missing mother's lullaby";
        lullabyVideo.videoId = "ID: 222018";
        videoItemsList.add(lullabyVideo);


        //Fourth Video "The Lucario Line
        VideoItem lucarioVideo = new VideoItem();
        lucarioVideo.videoURL = "https://github.com/LucarioWolf/CS460-Lab2/raw/refs/heads/main/Videos/LucarioLine.mp4";
        lucarioVideo.videoTitle = "The Lucario Line";
        lucarioVideo.videoDescription = "The evoultionary line of Lucario";
        lucarioVideo.videoId = "ID: 447448";
        videoItemsList.add(lucarioVideo);


        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));
    }
}