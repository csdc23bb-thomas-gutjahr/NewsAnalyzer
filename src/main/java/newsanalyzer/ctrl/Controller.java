package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.NewsException;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;
import newsapi.beans.Source;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;
import newsapi.enums.Language;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Controller {

	public static final String APIKEY = "236435ac0c264761a6e8a49696eced1a";
	private String keyword;
	private Endpoint endpoint;
	private Country country;
	private Language language;
	private Category category;

	private int getSizeReports(List<Article> articles) {
		return articles.size();
	}

	public String getProvider(List<Article> providerList) {

		Map<String, Long> frequencyMap = providerList.stream().collect(Collectors.groupingBy(provider -> provider.getSource().getName(),
				Collectors.counting()));
		String result = frequencyMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getValue().toString();
		String provider = frequencyMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey().toString();

		return "Die meisten Artikel, Anzahl " + result + " hat " + provider;
	}

	public String getAuthor(List<Article> authorList) {

		Article result = authorList.stream().filter(a -> a.getAuthor() != null)
				.sorted(Comparator.comparingInt(a -> a.getAuthor().length())).findFirst().orElse(new Article());

		return result.getAuthor();
	}

	public  List<Article> getTitlesSort(List<Article> sortList){
		 List result = sortList.stream().sorted(Comparator.comparingInt(sortarticle -> sortarticle.getTitle().
				 length())).collect(Collectors.toList());
		 Collections.reverse(result);
		 return result;
	}


	public void process(NewsApi newsApi) {
		System.out.println("Start process");
		List<Article> articles = null;
		try{
			NewsReponse newsReponse = newsApi.getNews();
			articles = newsReponse.getArticles();
			int size = getSizeReports(articles);
			System.out.println("Amount of articles in this category: " + size);
			System.out.println(getProvider(articles));
			System.out.println("Author: " + getAuthor(articles));
			getTitlesSort(articles).forEach(article -> System.out.println(article.getTitle()));
			}
		catch (NewsException e){
			System.out.println(e.getMessage());
		}

		Scanner scan = new Scanner(System.in);
		System.out.println("Do you want to download all the articles ? yes or no");
		scan.close();

		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		System.out.println("End process");
	}



	public Object getData() {
		
		return null;
	}
}
