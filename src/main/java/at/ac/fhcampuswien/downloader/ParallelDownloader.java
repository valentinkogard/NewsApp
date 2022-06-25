package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.NewsApiException;
import okhttp3.Call;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Class is needed for exercise 4 - ignore for exercise 3 solution
public class ParallelDownloader extends Downloader implements Callable {

    private List<String> urlsString;

    public ParallelDownloader (List<String> urlsString) {
        this.urlsString=urlsString;
    }

    public ParallelDownloader() {};

    @Override
    public Integer call() throws Exception {

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
    public int process(List<String> urls) {
        // TODO implement download function using multiple threads
        // Hint: use ExecutorService with Callables
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<String> subList1 = new ArrayList<>();
        List<String> subList2 = new ArrayList<>();

        for(int i=0; i<urls.size(); i++) {
            if(i%2==0) {
                subList1.add(urls.get(i));
            } else {
                subList2.add(urls.get(i));
            }
        }

        Future<Integer> result1 = executorService.submit(new ParallelDownloader(subList1));
        Future<Integer> result2 = executorService.submit(new ParallelDownloader(subList2));

        int a = 0;
        int b = 0;

        try {
            a = result1.get();
            b = result2.get();
        } catch (InterruptedException ie) {
            ie.getMessage();
        } catch (ExecutionException ee) {
            ee.getMessage();
        }

        executorService.shutdown();

        return a+b;
    }
}
