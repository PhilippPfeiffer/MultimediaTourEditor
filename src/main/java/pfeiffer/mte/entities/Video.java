package pfeiffer.mte.entities;

/**
 * Represents an entity of a Video and encapsulates all its data.
 * @author Philipp Pfeiffer
 */
public class Video {
    
    private String id;
    private String thumbnailUrl;
    private String title;
    
    /**
     * Sets the video ID.
     * @param id 
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Returns the ID of the video
     * @return String of the ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the URL for the video thumbnail
     * @param url 
     */
    public void setThumbnailUrl(String url) {
        this.thumbnailUrl = url;
    }
    
    /**
     * Returns the URL of the video thumbnail
     * @return String of the thumbnail URL
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    /**
     * Sets the title of the video
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Returns the title of the video
     * @return String of the title
     */
    public String getTitle() {
        return title;
    }
}
