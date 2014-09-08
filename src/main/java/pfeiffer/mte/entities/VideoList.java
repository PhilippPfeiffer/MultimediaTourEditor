package pfeiffer.mte.entities;

import java.util.ArrayList;

/**
 * Represents a list of videos
 * @author Philipp Pfeiffer
 */
public class VideoList {
    
    private ArrayList<Video> videoList = new ArrayList<Video>();
    
    /**
     * Returns the full list of videos.
     * @return ArrayListy<Video> with all videos
     */
    public ArrayList<Video> getList() {
        return videoList;
    }
    
    /**
     * Replaces the video list with a new ArrayList<Video>
     * @param videoList 
     */
    public void setList(ArrayList<Video> videoList) {
        this.videoList = videoList;
    }
    
    /**
     * Adds a video to the list.
     * @param video 
     */
    public void addVideo(Video video) {
        videoList.add(video);
    }
    
    /**
     * Removes the video found at the given index from the list.
     * @param index 
     */
    public void removeVideo(int index) {
        videoList.remove(index);
    }

    /**
     * Returns the video found at the given index of the list.
     * @param i
     * @return Video at index i
     */
    public Video getVideo(int i) {
        return videoList.get(i);
    }
    
}
