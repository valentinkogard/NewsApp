package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.news.NewsApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader implements Callable {

    private List<String> urlsString;
    //private static ParallelDownloader instance = null;

    public ParallelDownloader (List<String> urlsString) {
        this.urlsString=urlsString;
    }
    public ParallelDownloader(){}

    @Override
    public Integer call() throws NewsApiException {

        int count = 0;
        for (String url : this.urlsString) {
            try {
                String fileName = saveUrl2File(url);
                if(fileName != null)
                    count++;
            } catch (NewsApiException e){
                System.err.println(e.getMessage());
                throw new NewsApiException(e.getMessage());
            } catch (Exception e){
                throw new NewsApiException("Different problem occurred in " + this.getClass().getName() + ". Message: " + e.getMessage());
            }
        }
        return count;
    }

    // returns number of downloaded article urls
    @Override
    public int process(List<String> urls) throws NewsApiException {
        // TODO implement download function using multiple threads
        // Hint: use ExecutorService with Callables
        int numOfThreads = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);

        List<Future<Integer>> results = new ArrayList<>();
        for(int i = 0, bottom = 0, top = 0; i < numOfThreads; i++){
            top += Math.ceil(urls.size()/numOfThreads);
            if(top > urls.size()){
                top = urls.size();
            }
            results.add(executorService.submit(new ParallelDownloader(urls.subList(bottom, top))));
            bottom = top;
        }

        int sum = 0;
        for(Future<Integer> i : results) {
            try {
                sum += i.get();
            } catch (InterruptedException e) {
                //throw new NewsApiException("InterruptedException: " + e.getMessage());
                System.out.println(e);
            } catch (ExecutionException e) {
                //throw new NewsApiException("ExecutionException: " + e.getMessage());
                System.out.println(e);
            } catch (Exception e) {
                //System.out.println("...");
                throw new NewsApiException("Different problem occurred in " + this.getClass().getName() + ". Message: " + e.getMessage());
            }
        }
        executorService.shutdown();
        return sum;
    }
}
