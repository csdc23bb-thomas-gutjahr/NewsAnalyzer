package newsdownload;

import newsapi.NewsException;
import newsapi.beans.Article;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<String> urls) {
        int count = 0;
        long start1 = System.currentTimeMillis();
        for (String url : urls) {
            try{
            String fileName = saveUrl2File(url);
            if(fileName != null)
                count++;
            }catch(Exception e){
                System.out.println("Article not found");
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Elapsed Download Time " + ((end1-start1)/1000) + " Seconds");
        return count;
    }
}
