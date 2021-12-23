package com.nebula.linjiangxian.model;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jerrick
 * @Date: 2021/12/18/23:45
 * @Description:
 */

public class Poetry {
    public String id;
    public String name;
    public Author author;
    public String content;
    public String ancientAddress;
    public String address;
    public double longitude;
    public double latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAncientAddress() {
        return ancientAddress;
    }

    public void setAncientAddress(String ancientAddress) {
        this.ancientAddress = ancientAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
