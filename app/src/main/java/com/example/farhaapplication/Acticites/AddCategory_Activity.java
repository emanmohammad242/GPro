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

public class AddCategory_Activity extends AppCompatActivity {

    private EditText category_name , category_imageId, product_number;
    String cat_name="" , cat_image="" , pro_num="" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_);
        setUp();
    }
    public void setUp()
    {
        category_name=findViewById(R.id.category_name);
        category_imageId=findViewById(R.id.category_imageId);
        product_number=findViewById(R.id.product_number);

    }
    public void insert_btn_OnClick(View view)
    {
        String restUrl = "http://172.19.9.27:84/rest/addCategory.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            AddCategory_Activity.SendPostRequest runner = new AddCategory_Activity.SendPostRequest();
            runner.execute(restUrl);
        }

    }

    private String processRequest(String restUrl) throws UnsupportedEncodingException {


        cat_name=category_name.getText().toString().trim();
        cat_image=category_imageId.getText().toString().trim();
        pro_num=product_number.getText().toString().trim();



        String data = URLEncoder.encode("nameCat", "UTF-8")
                + "=" + URLEncoder.encode(cat_name, "UTF-8");

        data += "&" + URLEncoder.encode("imageCat", "UTF-8") + "="
                + URLEncoder.encode(cat_image, "UTF-8");


        data += "&" + URLEncoder.encode("numPro", "UTF-8")
                + "=" + URLEncoder.encode(pro_num, "UTF-8");

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