package com.example.swipevideo;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    //An List that holds the video objects
    private List<VideoItem> videoItems;

    /**
     * A constructor that obtains the videoItem objects to turn into media playing videos
     * @param videoItems The List Video Object
     */
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    /**
     * A helper function to determine how many videos are loaded.
     * @return The number of videos on the app
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    /**
     *This class sets up the videos by getting the URL, setting up the title, ID and decription,
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {

        //XML Elements of a video
        TextView textVideoTitle, textVideoDescription, textVideoID;
        VideoView videoView;
        ProgressBar progressBar;

        ImageView imageView;

        /**
         * The constructor that assigns the XML elements using the IDs
         * @param itemView The view where we find the xml elements
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle = itemView.findViewById(R.id.textViewTitle);
            textVideoDescription = itemView.findViewById(R.id.textViewDescription);
            progressBar = itemView.findViewById(R.id.videoProgessBar);
            textVideoID = itemView.findViewById(R.id.textID);

            imageView = itemView.findViewById(R.id.pauseImage);
            imageView.setImageResource(R.drawable.pause);

        }

        /**
         * A function that assigns videos information to the XML elements
         * @param videoItem A video object that contains the videos info
         */
        void setVideoData(VideoItem videoItem){

            //Sets the Video info to the XML elements
            textVideoTitle.setText(videoItem.videoTitle);
            textVideoDescription.setText(videoItem.videoDescription);
            videoView.setVideoPath(videoItem.videoURL);
            textVideoID.setText(videoItem.videoId);

            //Whenever the video is clicked on, the video will pause
            videoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        imageView.setVisibility(View.VISIBLE);
                    } else {
                        videoView.start();
                        imageView.setVisibility(View.GONE);
                    }
                }
            });

            //When the video is loaded, begin to prepare the video with the onPrepared function
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * When the video is loaded, set the video ratios to match the phones settings
                 * @param mp The object the runs the video.
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {

                    //Remove the progress bar and begin playing the video
                    progressBar.setVisibility(View.GONE);
                    imageView.setVisibility(View.GONE);
                    mp.start();

                    //Ratios varibles to determine the scale of the videos
                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth() / (float) videoView.getHeight();
                    float scale = videoRatio/screenRatio;

                    //If the video is larger than screen, scaled the width of the video to fit the screen
                    if(scale >= 1f) {
                        videoView.setScaleX(scale);
                    } else { //If video is smaller, increase the height of the video
                        videoView.setScaleY(1f / scale);
                    }
                }

                public void pauseVideo(MediaPlayer mp){

                }
            });

            //When the video ends, restart video
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /**
                 * When the video ends, restart the video
                 * @param mp The object that plays the video
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }

    }
}
