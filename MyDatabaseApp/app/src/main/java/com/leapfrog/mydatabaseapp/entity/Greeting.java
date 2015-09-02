package com.leapfrog.mydatabaseapp.entity;

/**
 * Created by Ajit Kumar Baral on 9/1/2015.
 */
public class Greeting {

    private int keyId;
    private int id;
    private String content;

    public Greeting() {
    }

    public Greeting(int keyId, int id, String content) {
        this.keyId = keyId;
        this.id = id;
        this.content = content;
    }

    public Greeting(int id, String content) {
        this.id = id;
        this.content = content;
    }
    public int getKeyId() {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "keyId=" + keyId +
                ", id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
