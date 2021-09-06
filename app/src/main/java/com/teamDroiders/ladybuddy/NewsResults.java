package com.teamDroiders.ladybuddy;

import java.util.List;

public class NewsResults {

    public NewsResults(){

    }
    private List<News> articles = null;

    public NewsResults(List<News> articles) {
        this.articles = articles;
    }

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }

}
