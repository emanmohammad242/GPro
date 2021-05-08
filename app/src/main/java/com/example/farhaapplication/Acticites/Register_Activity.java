package com.example.farhaapplication.Acticites;

import android.Manifest;
import android.content.Intent;
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

public class Register_Activity extends AppCompatActivity {

    EditText name_txt,email_txt,phone_no_txt,address_txt,password_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_txt=findViewById(R.id.name_txt);
        email_txt=findViewById(R.id.email_txt);
        phone_no_txt=findViewById(R.id.phone_no_txt);
        address_txt=findViewById(R.id.address_txt);
        password_txt=findViewById(R.id.password_txt);
    }

    public void sign_up_OnClick(View view)
    {


        String restUrl = "http://172.19.9.27:84/rest/add_user.php";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    123);

        } else{
            SendPostRequest runner = new SendPostRequest();
            runner.execute(restUrl);
        }


         Intent intent = new Intent(this, LogIn_Activity.class);
         startActivity(intent);
    }

    private String processRequest(String restUrl) throws UnsupportedEncodingException {
        String names = name_txt.getText().toString();
        String emailes = email_txt.getText().toString();
        String phone_no = phone_no_txt.getText().toString();
        String addresss = address_txt.getText().toString();
        String pass = password_txt.getText().toString();

        String data = URLEncoder.encode("phone", "UTF-8")
                + "=" + URLEncoder.encode(phone_no, "UTF-8");

        data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                + URLEncoder.encode(emailes, "UTF-8");


        data += "&" + URLEncoder.encode("password", "UTF-8")
                + "=" + URLEncoder.encode(pass, "UTF-8");


        data += "&" + URLEncoder.encode("address", "UTF-8")
                + "=" + URLEncoder.encode(addresss, "UTF-8");

        data += "&" + URLEncoder.encode("name", "UTF-8")
                + "=" + URLEncoder.encode(names, "UTF-8");



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

    private class SendPostRequest extends AsyncTask<String, Void, String> {

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