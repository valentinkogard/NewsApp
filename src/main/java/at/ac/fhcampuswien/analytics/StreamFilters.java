package at.ac.fhcampuswien.analytics;

import at.ac.fhcampuswien.articleStructure.Article;
import at.ac.fhcampuswien.articleStructure.Source;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilters {

    private static StreamFilters instance = null;

    private StreamFilters(){}

    public static StreamFilters getInstance(){
        if(instance == null){
            instance = new StreamFilters();
        }
        return instance;
    }

    public String sourceWithMostArticles(List articleList) {
        Stream<Article> articleStream = articleList.stream();

        return articleStream
                .map(Article::getSource)
                .map(Source::getName)
                .filter(name -> Objects.nonNull(name))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse(null);
    }

    public String authorWithLongestName(List articleList) {
        Stream<Article> articleStream = articleList.stream();

        return articleStream
                .map(Article::getAuthor)
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(String::length)).get();
    }

    public long numberOfArticlesNyt (List articleList) {
        Stream<Article> articleStream = articleList.stream();

        return articleStream
                .filter(article -> Objects.nonNull(article.getSource().getName()))
                .filter(article -> article.getSource().getName().equals("New York Times"))
                .count();
    }

    public long articlesWithHeadlineSub15Chars (List articleList) {
        Stream<Article> articleStream = articleList.stream();

        return articleStream
                .filter(article -> Objects.nonNull(article.getTitle()))
                .filter(article -> article.getTitle().length()<15)
                .count();
    }

    public List<Article> articlesWithHeadlineSub15CharsList (List articleList) {
        Stream<Article> articleStream = articleList.stream();

        return articleStream
                .filter(article -> Objects.nonNull(article.getTitle()))
                .filter(article -> article.getTitle().length()<15)
                .collect(Collectors.toList());
    }

    public List<Article> articlesSortedByLengthThenAlphabetically (List articleList) {
        Stream<Article> articleStream = articleList.stream();

        return articleStream
                .filter(article -> Objects.nonNull(article.getDescription()))
                .sorted((a1,a2) -> a1.getDescription().compareTo(a2.getDescription()))
                .sorted((a1,a2) -> Integer.compare(a1.getDescription().length(),a2.getDescription().length()))
                .collect(Collectors.toList());
    }
}
