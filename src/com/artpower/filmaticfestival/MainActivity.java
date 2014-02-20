package com.artpower.filmaticfestival;





import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }
    
    public boolean isMultiPane() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
    public void showDetails(int index) {
    	

        if (isMultiPane()) {
            // Check what fragment is shown, replace if needed.
            NavigationFragment details = (NavigationFragment)
                    getFragmentManager().findFragmentById(R.id.details);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                details = NavigationFragment.newInstance(index);
                
               
            }
            if(index == 1){
                startActivity(new Intent(Intent.ACTION_VIEW, 
                	    Uri.parse("http://client.frankshanley.com/filmatic/")));
                }
                else if (index== 0){
                	startActivity(new Intent(Intent.ACTION_VIEW, 
                    	    Uri.parse("https://ucsdboxoffice.com/Online/default.asp")));
                }
            
            
            

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            /*Intent intent = new Intent();
            intent.setClass(this, FilmaticWebviewActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);*/
        	Log.v("Mylogcat", "Index is..."+index);
            if(index == 1){
            startActivity(new Intent(Intent.ACTION_VIEW, 
            	    Uri.parse("http://client.frankshanley.com/filmatic/")));
            }
            else if (index== 0){
            	startActivity(new Intent(Intent.ACTION_VIEW, 
                	    Uri.parse("https://ucsdboxoffice.com/Online/default.asp")));
            }
        }
   
    
    
    
    }
}
