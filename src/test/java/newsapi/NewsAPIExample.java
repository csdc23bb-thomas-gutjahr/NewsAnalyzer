package newsapi;

import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsapi.enums.Category;

import java.util.List;

public class NewsAPIExample {

    public static final String APIKEY = "236435ac0c264761a6e8a49696eced1a";

    public static void main(String[] args) {

        NewsApi newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.TOP_HEADLINES)
                .setSourceCountry(Country.at)
                .setSourceCategory(Category.health)
                .createNewsApi();
        try{
        NewsReponse newsResponse = newsApi.getNews();
        if(newsResponse != null){
            List<Article> articles = newsResponse.getArticles();
            articles.stream().forEach(article -> System.out.println(article.toString()));
        }}
        catch (NewsException e){
            System.out.println(e.getMessage());
        }


        newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.EVERYTHING)
                .setExcludeDomains("Lifehacker.com")
                .createNewsApi();
        try{
            NewsReponse newsResponse = newsApi.getNews();
        if(newsResponse != null){
            List<Article> articles = newsResponse.getArticles();
            articles.stream().forEach(article -> System.out.println(article.toString()));
        }}
        catch (NewsException e){
            System.out.println(e.getMessage());
        }
    }
}