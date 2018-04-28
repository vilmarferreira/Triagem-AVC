package com.example.vilmarferreira.triagem_avc;



import android.app.Activity;
import android.os.AsyncTask;


import com.example.vilmarferreira.triagem_avc.model.model;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vilmarferreira on 26/10/2017.
 */

public class DownloadConteudo extends AsyncTask<URL,Void, ArrayList>
{
    //Metodo que executa a Thread em segundo plano
    @Override
    protected ArrayList<model> doInBackground(URL...urls) {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        ArrayList<model> lista= new ArrayList();

        Request request = new Request.Builder().url(urls[0]).build();

        try {
            Response response = client.newCall(request).execute();

            String json= response.body().string();
            lista= gson.fromJson(json,new TypeToken<ArrayList<model>>(){}.getType());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    //Metodo que executa apos a conclusao da Thread
    //Codigo chamado ja na tread principal
    public void onPostExecute(ArrayList<model> model)
    {



    }

}