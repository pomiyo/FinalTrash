package com.example.taquio.trasearch.SearchLogic;

/**
 * Created by Del Mar on 3/14/2018.
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ArticleHTTPRequest {

    private HashMap<Integer, ArticleData> unfilteredData = new HashMap<Integer, ArticleData>();


    public HashMap<Integer, ArticleData> sendGet(String querySearch) throws Exception {
        String urlSource = "https://kgsearch.googleapis.com/v1/entities:search?languages=en&limit=30&types=WebSite&types=Book&types=EducationalOrganization&types=Organization&key=AIzaSyDUvqxt9hpw0CFfJG0fqsyoGbc96-h7hFk&query=";
        String url = urlSource.concat(querySearch.replace(' ', '+'));

        StringBuilder sb = new StringBuilder();
        URL conn = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) conn.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            String inputLine;
            while((inputLine = bin.readLine()) != null) {
                sb.append(inputLine);
            }
            this.jsonParser(sb.toString());

            return unfilteredData;
        }
        finally {
            urlConnection.disconnect();
        }
    }

    public void jsonParser(String apiOutput) throws Exception {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(apiOutput);

        JSONArray items = (JSONArray) jsonObject.get("itemListElement");

        int index = 0;
        for(int x = 0; x < items.size(); x++) {
            JSONObject itemObject = (JSONObject) jsonParser.parse(items.get(x).toString());
            JSONObject objectResult = (JSONObject) jsonParser.parse(itemObject.get("result").toString());
            JSONArray resultType = (JSONArray) objectResult.get("@type");

            ArticleData articleData = new ArticleData();
            articleData.setName(objectResult.get("name").toString());
            if (objectResult.containsKey("description")) {
                articleData.setDescription(objectResult.get("description").toString());
            }

            articleData.setResultType(resultType.toArray());

            if (objectResult.containsKey("detailedDescription")) {
                JSONObject objectDetailedDesc = (JSONObject) jsonParser.parse(objectResult.get("detailedDescription").toString());
                articleData.setArticleBody(objectDetailedDesc.get("articleBody").toString());
                articleData.setArticleURL(objectDetailedDesc.get("url").toString());
            }

            if (objectResult.containsKey("url")) {
                articleData.setUrl(objectResult.get("url").toString());
            }
            this.unfilteredData.put(index, articleData);
            index++;
        }

    }
}

