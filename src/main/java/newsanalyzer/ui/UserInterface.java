package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.NewsApiBuilder;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.*;

public class UserInterface 
{

	private static final String APIKEY = "236435ac0c264761a6e8a49696eced1a";
	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		System.out.println("Austria");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setLanguage(Language.de)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.entertainment)
				.createNewsApi();
		ctrl.process(newsApi);
	}

	public void getDataFromCtrl2(){
		System.out.println("Sports");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setLanguage(Language.en)
				.setSourceCountry(Country.us)
				.setSourceCategory(Category.sports)
				.createNewsApi();
		ctrl.process(newsApi);
	}

	public void getDataFromCtrl3(){
		System.out.println("Health");
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ("")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setLanguage(Language.en)
				.setSourceCountry(null)
				.setSourceCategory(Category.health)
				.createNewsApi();
		ctrl.process(newsApi);
	}
	
	public void getDataForCustomInput() {
		System.out.println("Geben Sie ein Keyword ein");
		Scanner in = new Scanner(System.in);
		String word = in.nextLine();
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ(word)
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.createNewsApi();
		ctrl.process(newsApi);
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Austria", this::getDataFromCtrl1);
		menu.insert("b", "Sports", this::getDataFromCtrl2);
		menu.insert("c", "Health", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
