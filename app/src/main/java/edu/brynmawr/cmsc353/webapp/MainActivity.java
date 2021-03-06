package edu.brynmawr.cmsc353.webapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int DONATION_FEED_ACTIVITY_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    public void onDonationFeedClick(View v) {
        // create an Intent using the current Activity
        // and the Class to be created
        //Intent i = new Intent(this, DonationFeedActivity.class);


        try {
            //URL url = new URL("http://10.0.2.2:3000/test");
            URL url = new URL("http://10.0.2.2:3000/viewListingsForSocialService?zipcode=");
            AccessWebTask task = new AccessWebTask();
            task.execute(url);
            List<Map<String, String>> listings = task.get();
            System.out.println("The size is " + listings.size());
            for (Map<String, String> map : listings) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    System.out.println(key + " " + value);
                }
            }

            Intent intent = new Intent(this, DonationFeedActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("list",(Serializable)listings);
            intent.putExtra("BUNDLE",args);
            startActivity(intent);

            // you can pass arguments to the new Activity
            // using key/value pairs in the Intent

            //i.putExtra("food_description", item);
            //i.putExtra("food_list", (Parcelable) listings);

            // pass the Intent to the Activity,
            // using the specified request code
            //startActivityForResult(i, DONATION_FEED_ACTIVITY_ID);  //handle this with that request code function stuff

            /*ArrayList<String> list = new ArrayList<String>();
            i.putExtra("arraylist", list);
            startActivity(i);*/

        }
        catch (Exception e) {
            // uh oh
            e.printStackTrace();
        }

    }

    public void onReceivedDonationClick (View v){
        startActivity(new Intent(this, UnimplementedActivity.class));
    }
    public void onMyDonationClick (View v){
        startActivity(new Intent(this, UnimplementedActivity.class));
    }
    public void onCreateDonationClick (View v){
        startActivity(new Intent(this, CreateDonationActivity.class));
    }




    public void onConnectButtonClick(View v) {

        TextView tv = findViewById(R.id.statusField);

        try {
            //URL url = new URL("http://10.0.2.2:3000/test");
            URL url = new URL("http://10.0.2.2:3000/viewListingsForSocialService?zipcode=19010");
            AccessWebTask task = new AccessWebTask();
            task.execute(url);
            List<Map<String, String>> listings = task.get();
            String item = "";

            for(Map<String, String> listing: listings){
                item = "food description: " + listing.get("food_description");
            }
            tv.setText(item);

        }
        catch (Exception e) {
            // uh oh
            e.printStackTrace();
            tv.setText(e.toString());
        }


    }
}