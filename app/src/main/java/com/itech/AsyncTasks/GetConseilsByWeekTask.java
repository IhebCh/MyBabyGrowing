package com.itech.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetConseilsByWeekTask extends AsyncTask<Void,Void,String> {


        private Context context ;
        private String week ;
        String httpcode;
    ProgressDialog dialog ;


        public GetConseilsByWeekTask(Context context, String week){
            this.context=context ;
            this.week=week ;
        }


        @Override
        protected String doInBackground(Void... params) {
            //Emulateur
            String url ="http://10.0.2.120:8080/getconseilbyweek?week='"+week+"'" ;
            HttpClient httpClient =new DefaultHttpClient() ;
            HttpGet httpGet = new HttpGet(url) ;
            String resultat ="aa" ;
            try {
                HttpResponse httpResponse = httpClient.execute(httpGet) ;
                httpcode = ((Integer) httpResponse.getStatusLine().getStatusCode()).toString() ;
                resultat= EntityUtils.toString(httpResponse.getEntity()) ;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultat ;
        }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage(" Connection en cours ...");
        dialog.show();
    }

        @Override
        protected void onPostExecute(String s) {

            if(httpcode==null || !httpcode.equals("200")){
                Toast.makeText(context, "Impossible d'établir une connection", Toast.LENGTH_LONG).show();
            }

            else{
                if(!s.equals("{}")){

                    // Toast.makeText(context, "true", Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(context, "Pas de resultat", Toast.LENGTH_LONG).show();

                }
            }

            dialog.dismiss();

        }
}
