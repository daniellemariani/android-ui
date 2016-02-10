package com.dmariani.androidui.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author Danielle Mariani on 10/02/16.
 */
public class Article implements Serializable {

    private int id;
    @SerializedName("short_title")
    private String shortTitle;
    @SerializedName("long_title")
    private String longTitle;
    private String description;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("image_legend")
    private String imageLegend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getLongTitle() {
        return longTitle;
    }

    public void setLongTitle(String longTitle) {
        this.longTitle = longTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageLegend() {
        return imageLegend;
    }

    public void setImageLegend(String imageLegend) {
        this.imageLegend = imageLegend;
    }
}
