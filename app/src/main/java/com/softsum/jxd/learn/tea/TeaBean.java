package com.softsum.jxd.learn.tea;

import android.graphics.Bitmap;

import java.util.List;

public class TeaBean {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //url
    private String url;
    //作者
    private String author;
    //正文
    private String text;
    //标题
    private String title;
    //作者头像
    private Bitmap authorImg;
    //作者头像链接
    private String authorImgUrl;
    //正文图片
    private Bitmap image;
    //正文图片链接
    private String imageUrl;
    //点赞数量
    private String upvote;
    //评论
    private List<String> commentList;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bitmap getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(Bitmap authorImg) {
        this.authorImg = authorImg;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getUpvote() {
        return upvote;
    }

    public void setUpvote(String upvote) {
        this.upvote = upvote;
    }

    public List<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<String> commentList) {
        this.commentList = commentList;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getAuthorImgUrl() { return authorImgUrl; }

    public void setAuthorImgUrl(String authorImgUrl) { this.authorImgUrl = authorImgUrl; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }
}
