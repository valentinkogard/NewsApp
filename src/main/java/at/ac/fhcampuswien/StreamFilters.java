package at.ac.fhcampuswien;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFilters {

    /*private String sourceWithMostArticles;
    private String authorWithLongestName;
    private int numberOfArticlesNyt;
    private ArrayList articlesWithHeadlineSub15Chars;
    private ArrayList articlesSortedByLengthThenAlphabetically;

    public ArrayList getArticlesSortedByLengthThenAlphabetically() {
        return articlesSortedByLengthThenAlphabetically;
    }
    public void setSourceWithMostArticles(String sourceWithMostArticles) {
        this.sourceWithMostArticles = sourceWithMostArticles;
    }

    public String getAuthorWithLongestName() {
        return authorWithLongestName;
    }
    public void setAuthorWithLongestName(String authorWithLongestName) {
        this.authorWithLongestName = authorWithLongestName;
    }

    public int getNumberOfArticlesNyt() {
        return numberOfArticlesNyt;
    }
    public void setNumberOfArticlesNyt(int numberOfArticlesNyt) {
        this.numberOfArticlesNyt = numberOfArticlesNyt;
    }

    public ArrayList getArticlesWithHeadlineSub15Chars() {
        return articlesWithHeadlineSub15Chars;
    }
    public void setArticlesWithHeadlineSub15Chars(ArrayList articlesWithHeadlineSub15Chars) {
        this.articlesWithHeadlineSub15Chars = articlesWithHeadlineSub15Chars;
    }

    public String getSourceWithMostArticles() {
        return sourceWithMostArticles;
    }

    public void setArticlesSortedByLengthThenAlphabetically(ArrayList articlesSortedByLengthThenAlphabetically) {
        this.articlesSortedByLengthThenAlphabetically = articlesSortedByLengthThenAlphabetically;
    }*/

    public String sourceWithMostArticles(List articleList) {

        Stream<Article> articleStream = articleList.stream();

        List<Article> sortedBySource = new ArrayList();

        sortedBySource = articleStream
                .filter(article -> Objects.nonNull(article.getSource().getName()))
                .sorted((a1,a2) -> a1.getSource().getName().compareTo(a2.getSource().getName()))
                .collect(Collectors.toList());

        int count, i, j = 0;

        HashMap<Integer, String> sourceAmount = new HashMap<>();

        for(i=j; i<sortedBySource.size(); i++) {
            count=0;
            for(j=i+1; j<sortedBySource.size(); j++) {
                if(sortedBySource.get(i).getSource().getName().equals(sortedBySource.get(j).getSource().getName())) {
                    count++;
                }
            }
            sourceAmount.put(count, sortedBySource.get(i).getSource().getName());
        }

        int max=-1;

        for (Map.Entry<Integer, String> entry : sourceAmount.entrySet()) {
            if (max < entry.getKey()) {
                max = entry.getKey();
            }
        }
        return sourceAmount.get(max);
    }



    public String authorWithLongestName(List articleList) {

        Stream<Article> articleStream = articleList.stream();

        List<Article> sortedByAuthor = new ArrayList();

        sortedByAuthor = articleStream
                .filter(article -> Objects.nonNull(article.getAuthor()))
                .sorted((a1,a2) -> Integer.compare(a1.getAuthor().length(),a2.getAuthor().length()))
                .collect(Collectors.toList());

        return sortedByAuthor.get(sortedByAuthor.size()-1).getAuthor();

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

    public List articlesWithHeadlineSub15CharsList (List articleList) {

        Stream<Article> articleStream = articleList.stream();

        return articleStream
                .filter(article -> Objects.nonNull(article.getTitle()))
                .filter(article -> article.getTitle().length()<15)
                .collect(Collectors.toList());
    }



    public List articlesSortedByLengthThenAlphabetically (List articleList) {

        Stream<Article> articleStream = articleList.stream();

        /*return articleStream
                .filter(article -> Objects.nonNull(article.getDescription()))
                .sorted((a1,a2) -> Integer.compare(a1.getTitle().length(),a2.getTitle().length()))
                .sorted((a1,a2) -> a1.getTitle().compareTo(a2.getTitle()))
                .collect(Collectors.toList());*/

        /*return articleStream
                .filter(article -> Objects.nonNull(article.getDescription()))
                .sorted((a1,a2) -> a1.getTitle().compareTo(a2.getTitle()))
                .sorted((a1,a2) -> Integer.compare(a1.getTitle().length(),a2.getTitle().length()))
                .collect(Collectors.toList());*/

        return articleStream
                .filter(article -> Objects.nonNull(article.getDescription()))
                .sorted((a1,a2) -> a1.getDescription().compareTo(a2.getDescription()))
                .sorted((a1,a2) -> Integer.compare(a1.getDescription().length(),a2.getDescription().length()))
                .collect(Collectors.toList());
    }


}
