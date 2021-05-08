package com.example.farhaapplication.Acticites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farhaapplication.Adapters.Adapter_Product;
import com.example.farhaapplication.Models.product;
import com.example.farhaapplication.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Products_Activity extends AppCompatActivity {


    List<product> productList;
    RecyclerView recyclerView;
    String cat="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_);

        cat=getIntent().getStringExtra("cat");

        String url = "http://172.19.9.27:84/rest/getProduct.php?cat="+cat;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else {
            Products_Activity.DownloadTextTask runner = new DownloadTextTask();
            runner.execute(url);
        }

        productList = new ArrayList<>();
        recyclerView=findViewById(R.id.pro);
    }
    private InputStream OpenHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection conn = url.openConnection();

        if (!(conn instanceof HttpURLConnection))
            throw new IOException("Not an HTTP connection");
        try {
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            response = httpConn.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        } catch (Exception ex) {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }
        return in;
    }

    private String DownloadText(String URL) {
        int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }

        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        String str = "";
        char[] inputBuffer = new char[BUFFER_SIZE];
        try {
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //---convert the chars to a String---
                String readString =
                        String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        return str;
    }
    private class DownloadTextTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return DownloadText(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {


            String obj[] = s.split("////");
            product pro;
            for(int i=0 ; i<obj.length ; i++)
            {

                if(! obj[i].equals(null)) {
                    String objects[] = obj[i].split(",");

                    if(!objects.equals(null)) {

                        int id = Integer.parseInt(objects[0]);
                        String proName=objects[1];
                        String proPric=objects[2];
                        String proColor=objects[3];
                        int proToken = Integer.parseInt(objects[4]);
                        String proSize = objects[5];
                        String proImageId = objects[6];
                        String category = objects[7];
                        pro=new product(id ,proName,proPric,proColor,proToken,proSize,proImageId,category);
                        productList.add(pro);
                    }
                }
            }
              recyclerView.setLayoutManager(new LinearLayoutManager(Products_Activity.this));
            Adapter_Product adapter = new Adapter_Product(Products_Activity.this, productList);
            recyclerView.setAdapter(adapter);

        }

    }

    public void insert_btn_OnClick(View view){
        Intent intent =new Intent(this, addProduct_Activity.class);
        startActivity(intent);
    }
    public void back_btn_OnClick(View view){
        Intent intent = new Intent(this,Category_Activity.class);
        startActivity(intent);
    }
}