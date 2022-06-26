package at.ac.fhcampuswien.downloader;

import at.ac.fhcampuswien.controller.AppController;
import at.ac.fhcampuswien.articleStructure.Article;
import at.ac.fhcampuswien.news.NewsApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainDownload {

    private static MainDownload instance = null;

    private MainDownload(){}

    public static MainDownload getInstance(){
        if(instance == null){
            instance = new MainDownload();
        }
        return instance;
    }

    public void measureTimeOfDownload() throws NewsApiException{
        //sequentialDownload();
        //parallelDownload();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sequentialDownload();
                    parallelDownload();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void sequentialDownload() throws NewsApiException {
        SequentialDownloader sequentialDownloader = new SequentialDownloader();
        long startTimeSequential = System.nanoTime();
        int numOfDownloadsSequential = downloadURLs(sequentialDownloader);
        long endTimeSequential = System.nanoTime();
        System.out.println("Sequential download of " + numOfDownloadsSequential + " sites finished in approximately "+((endTimeSequential-startTimeSequential) / 1000000)+" milliseconds");

        startTimeSequential=System.nanoTime();
        int imageDownloadsSequential = downloadImageUrls(sequentialDownloader);
        endTimeSequential = System.nanoTime();
        System.out.println("Sequential download of " + imageDownloadsSequential + " images finished in approximately "+((endTimeSequential-startTimeSequential) / 1000000)+" milliseconds");

    }

    private void parallelDownload() throws NewsApiException {
        ParallelDownloader parallelDownloader = new ParallelDownloader();
        long startTimeParallel = System.nanoTime();
        int numOfDownloadsParallel = downloadURLs(parallelDownloader);
        long endTimeParallel = System.nanoTime();
        System.out.println("Parallel download of " + numOfDownloadsParallel + " sites finished in approximately "+((endTimeParallel-startTimeParallel) / 1000000)+" milliseconds");

        startTimeParallel=System.nanoTime();
        int imageDownloadsParallel = downloadImageUrls(parallelDownloader);
        endTimeParallel = System.nanoTime();
        System.out.println("Parallel download of " + imageDownloadsParallel + " images finished in approximately "+((endTimeParallel-startTimeParallel) / 1000000)+" milliseconds");
    }

    // returns number of downloaded article urls
    public int downloadURLs(Downloader downloader) throws NewsApiException{
        List<Article> articles = AppController.getInstance().getArticles();
        if(articles == null) {
            throw new NewsApiException("Error: No list of articles instantiated.");
        }

        List<String> urls = new ArrayList<>();
        urls = articles.stream()
                .filter(article -> Objects.nonNull(article.getUrl()))
                .map(Article::getUrl)
                .collect(Collectors.toList());

        return downloader.process(urls);
    }

    public int downloadImageUrls(Downloader downloader) throws NewsApiException {
        List<Article> articles = AppController.getInstance().getArticles();
        if(articles == null) {
            throw new NewsApiException("Error: No list of articles instantiated.");
        }

        List<String> imageUrls = new ArrayList<>();
        imageUrls = articles.stream()
                .filter(article -> Objects.nonNull((article.getUrlToImage())))
                .map(Article::getUrlToImage)
                .collect(Collectors.toList());

        return downloader.process(imageUrls);
    }
}
