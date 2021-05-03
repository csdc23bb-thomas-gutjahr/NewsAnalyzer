package newsdownload;


import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader{

    ExecutorService executer = Executors.newFixedThreadPool(10);

    @Override
    public int process(List<String> urls) {
        int count = 0;
        long start1 = System.currentTimeMillis();
        for (String url : urls) {
            try{
                Future<?> taskStatus = executer.submit(()->{saveUrl2File(url);});
                    count++;
            }catch(Exception e){
                System.out.println("Article not found");
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Elapsed Download Time " + ((end1-start1)) + " Milliseconds");
        executer.shutdown(); //Shutdown Threads
        return count;
    }

}
