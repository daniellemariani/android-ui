package com.dmariani.androidui.manager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.dmariani.androidui.R;
import com.dmariani.androidui.model.Article;
import com.dmariani.androidui.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Danielle Mariani on 10/02/16.
 */
public class ArticleManager {

    private static final String LOG_TAG = "ArticleManager";

    public static ArrayList<Article> getArticles(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.articles); // TODO: See if we need to make a test only loader
        String jsonString = FileUtils.readJsonFromInputStream(is);
        try {
            is.close();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing file", e);
        }
        return getArticles(jsonString);
    }

    private static ArrayList<Article> getArticles(String jsonString) {
        if (TextUtils.isEmpty(jsonString)) {
            return null;
        }
        JsonObject jsonRoot = new JsonParser().parse(jsonString).getAsJsonObject();
        JsonArray jsonArray = jsonRoot.getAsJsonArray("articles");
        return new Gson().fromJson(jsonArray, new TypeToken<ArrayList<Article>>(){}.getType());
    }
}
