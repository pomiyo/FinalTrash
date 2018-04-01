package com.example.taquio.trasearch.Samok;

import com.example.taquio.trasearch.SearchLogic.DataFilter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Taquio on 2/23/2018.
 */

public class Spider {
    private Set<String> pagesVisited = new HashSet<String>(); // Links visited that contains the searchWord || source of the Links of video
    private List<String> pagesToVisit = new LinkedList<String>(); // Links found to be visited || Links of video
    private List<String> searchedWord = new LinkedList<String>();
    private HashMap<Integer, CrawledData> unfiltered = new HashMap<Integer, CrawledData>();
    private HashMap<Integer, CrawledData> filtered = new HashMap<Integer, CrawledData>();
    private Object[] paganation;

    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = this.pagesToVisit.remove(0);
        }while(this.pagesVisited.contains(nextUrl));
        this.pagesVisited.add(nextUrl);
        return nextUrl;
    }

    public HashMap<Integer, CrawledData> searchEngine(String searchWord) {
        SpiderLeg leg = new SpiderLeg();
        String urlSource = "https://www.youtube.com/results?search_query=";
        String url = urlSource + searchWord.replace(" ", "+");
        while (leg.checkConnection(url)){
            String currentUrl;

            if(this.pagesToVisit.isEmpty()) {
                currentUrl = url;
                this.pagesVisited.add(url);
            }
            else {
                currentUrl = this.nextUrl();
            }

            if (this.searchedWord.isEmpty()) {
                this.searchedWord.add(searchWord);
            }
            else {
                this.searchedWord.add(searchWord);
            }

            leg.crawl(currentUrl);
            this.unfiltered = leg.getVideoData();
            filterData();

//			for(Integer index: unfiltered.keySet()){
//				Integer key = index;
//				CrawledData value = unfiltered.get(key);
////				System.out.println("Key: " + key);
//				System.out.println("Value Title: " + value.getTitle());
////				System.out.println("Value Url: " + value.getUrl() + "\n");
//			}

            setPaganation(leg.getnextLinks().toArray());
//			for (Integer index: video.keySet()) {
//				CrawledData data = new CrawledData();
//
//			}
//			int x = 0;
//			do{
//				System.out.println("Page " + (x+1) + ": " + paganation[x]);
//				x++;
//			}while(x < paganation.length);

            boolean success = leg.searchForWord(searchWord);
            if(success) {
//				System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                break;
            }
            this.pagesToVisit.addAll(leg.getLinks());
        }
//		System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));
        return this.filtered;
    }

    public void filterData() {
        DataFilter filters = new DataFilter();
        String[] words = filters.getWordFilters();

//		System.out.println("HashMapSize: " + this.unfilteredData.size());

        int wordsIndex = 0;
        int filteredDataIndex = 0;
        do {
            for (Integer index: this.unfiltered.keySet()) {
                CrawledData data = unfiltered.get(index);
                if  (data.getTitle().toLowerCase().contains(words[wordsIndex])) {
                    CrawledData filteredData = new CrawledData();
                    filteredData.setTitle(data.getTitle());
                    filteredData.setUrl(data.getUrl());
                    this.filtered.put(filteredDataIndex, data);
                    this.unfiltered.remove(index);
                    break;
                }
            }
            filteredDataIndex++;
            wordsIndex++;
        }while(wordsIndex < words.length);
    }


    public Set<String> getPagesVisited() {
        return this.pagesVisited;
    }
    public List<String> getPagesToVisit() {
        return this.pagesToVisit;
    }

    public List<String> getSearchedWord() {
        return this.searchedWord;
    }

    public Object[] getPaganation() {
        return paganation;
    }

    public void setPaganation(Object[] paganation) {
        this.paganation = paganation;
    }

    public List<CrawledData> convertToList(HashMap<Integer, CrawledData> filteredData) {
        List<CrawledData> videoList = new LinkedList<>();
        for(Integer index: filteredData.keySet()) {
            CrawledData value = filteredData.get(index);
            videoList.add(value);
        }
        return videoList;
    }
}
