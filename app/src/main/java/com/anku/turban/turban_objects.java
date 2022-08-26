package com.anku.turban;

public class turban_objects {

    private String _id;
    private String name;
    private String location;
    private String description;
    private String imageUrl;
    private String modelUrl;

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    private String videoUrl;


    public String ShowAsString() {
        return "User ["+name+", "+ _id + ", " +location+ "]";
    }
}
