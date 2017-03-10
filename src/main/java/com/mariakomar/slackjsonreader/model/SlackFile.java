package com.mariakomar.slackjsonreader.model;

/**
 * Entity class with parameters of attachment.
 * Names of fields corresponds to names from json.
 *
 * Created by Maria Komar on 09.03.17.
 */
public class SlackFile {
    private String id;
    private String timestamp;
    private String name;
    private String filetype;
    private String url_private_download;
    private String url_private;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getUrl_private_download() {
        return url_private_download;
    }

    public void setUrl_private_download(String url_private_download) {
        this.url_private_download = url_private_download;
    }

    public String getUrl_private() {
        return url_private;
    }

    public void setUrl_private(String url_private) {
        this.url_private = url_private;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", name='" + name + '\'' +
                ", filetype='" + filetype + '\'' +
                ", url_private_download='" + url_private_download + '\'' +
                ", url_private='" + url_private + '\'' +
                '}';
    }
}
