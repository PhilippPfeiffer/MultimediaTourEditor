/*
 * © Copyright 2014, Philipp Pfeiffer
 * 
 * This file is part of MultimediaTourEditor.

    MultimediaTourEditor is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    MultimediaTourEditor is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with MultimediaTourEditor.  If not, see <http://www.gnu.org/licenses/>.
 */

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
