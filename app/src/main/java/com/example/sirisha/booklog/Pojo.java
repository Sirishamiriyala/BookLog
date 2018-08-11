package com.example.sirisha.booklog;

/**
 * Created by sirisha on 15-05-2018.
 */

class Pojo {


    String kind,selfLink,etag,id,title,author,publisher,publishedDate,pageCount,smallThumb,
    thumb,infoLink,preview,lang,country;
    public Pojo(String video, String analysis, String rel_date,
                String id, String title, String author,
                String publishedDate, String pageCount, String smallThumbnail,
                String thumbnail, String infoLink, String previewLink,
                String lang, String country) {
        this.kind=analysis;
        this.selfLink=video;
        this.etag=rel_date;
        this.id=id;
        this.title=title;
        this.author=author;
        this.publishedDate=publishedDate;
        this.pageCount=pageCount;
        this.smallThumb=smallThumbnail;
        this.thumb=thumbnail;
        this.infoLink=infoLink;
        this.preview=previewLink;
        this.lang=lang;
        this.country=country;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getSmallThumb() {
        return smallThumb;
    }

    public void setSmallThumb(String smallThumb) {
        this.smallThumb = smallThumb;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
