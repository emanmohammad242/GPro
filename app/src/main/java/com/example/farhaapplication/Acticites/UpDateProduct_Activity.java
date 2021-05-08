package com.example.farhaapplication.Acticites;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.farhaapplication.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class UpDateProduct_Activity extends AppCompatActivity {

    private EditText product_name , product_price , product_color , product_size , product_token , product_imageId , product_category ;
    String name_pro="" , price_pro="" , color_pro="" , size_pro="" , image_id ="" , category_pro="" , ids="" ,token_pro="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date_);
        setUp();

        product_name.setText(getIntent().getStringExtra("name"));
        product_price.setText(getIntent().getStringExtra("price"));
        product_color.setText(getIntent().getStringExtra("color"));
        product_size.setText(getIntent().getStringExtra("size"));
        product_token.setText(getIntent().getStringExtra("token"));
        product_imageId.setText(getIntent().getStringExtra("imageId"));
        product_category.setText(getIntent().getStringExtra("category"));

    }

    public void setUp()
    {
        product_name=findViewById(R.id.category_name);
        product_price=findViewById(R.id.product_price);
        product_color=findViewById(R.id.product_color);
        product_size=findViewById(R.id.product_size);
        product_token=findViewById(R.id.product_token);
        product_imageId=findViewById(R.id.category_imageId);
        product_category=findViewById(R.id.product_number);

    }

    public void update_btn_OnClick(View view)
    {

        String restUrl = "http://172.19.9.27:84/rest/upDateProduct.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            UpDateProduct_Activity.SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }

    }

    private String processRequest(String restUrl) throws UnsupportedEncodingException {

        ids=getIntent().getStringExtra("id");
        name_pro=product_name.getText().toString().trim();
        price_pro=product_price.getText().toString().trim();
        color_pro=product_color.getText().toString().trim();
        size_pro=product_size.getText().toString().trim();
        image_id=product_imageId.getText().toString().trim();
       token_pro=product_token.getText().toString().trim();
        category_pro=product_category.getText().toString().trim();



        String data = URLEncoder.encode("id", "UTF-8")
                + "=" + URLEncoder.encode(ids+"", "UTF-8");

        data += "&" + URLEncoder.encode("product_name", "UTF-8") + "="
                + URLEncoder.encode(name_pro, "UTF-8");

        data += "&" + URLEncoder.encode("product_pric", "UTF-8") + "="
                + URLEncoder.encode(price_pro, "UTF-8");


        data += "&" + URLEncoder.encode("product_color", "UTF-8")
                + "=" + URLEncoder.encode(color_pro, "UTF-8");


        data += "&" + URLEncoder.encode("product_size", "UTF-8")
                + "=" + URLEncoder.encode(size_pro, "UTF-8");

        data += "&" + URLEncoder.encode("product_imageId", "UTF-8")
                + "=" + URLEncoder.encode(image_id, "UTF-8");

        data += "&" + URLEncoder.encode("product_token", "UTF-8")
                + "=" + URLEncoder.encode(token_pro, "UTF-8");

        data += "&" + URLEncoder.encode("category", "UTF-8")
                + "=" + URLEncoder.encode(category_pro, "UTF-8");

        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL(restUrl);

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally{
            if ((reader != null)) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Show response on activity
        return text;



    }

    public class SendPostRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                return processRequest(strings[0]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}