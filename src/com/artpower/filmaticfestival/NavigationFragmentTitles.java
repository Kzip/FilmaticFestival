package com.artpower.filmaticfestival;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NavigationFragmentTitles extends ListFragment {
    private MainActivity myActivity = null;
    int mCurCheckPosition = 0;
    
    
    public static NavigationFragmentTitles newInstance(int index) {
        Log.v(MainActivity.TAG, "in NavigationFragment newInstance(" + index + ")");

        NavigationFragmentTitles df = new NavigationFragmentTitles();

        // Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		df.setArguments(args);
		return df;
	}
	
	public static NavigationFragmentTitles newInstance(Bundle bundle) {
		int index = bundle.getInt("index", 0);
        return newInstance(index);
	}

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle icicle) {
    	Log.v(MainActivity.TAG,
    			"in NavigationFragmentTitles onInflate. AttributeSet contains:");
    	for(int i=0; i<attrs.getAttributeCount(); i++) {
            Log.v(MainActivity.TAG, "    " + attrs.getAttributeName(i) +
            		" = " + attrs.getAttributeValue(i));
    	}
    	super.onInflate(attrs, icicle);
    }

   @Override
    public void onAttach(Activity myActivity) {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onAttach; activity is: " + myActivity);
    	super.onAttach(myActivity);
    	this.myActivity = (MainActivity)myActivity;
    }

    @Override
    public void onCreate(Bundle icicle) {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onCreate. Bundle contains:");
    	if(icicle != null) {
            for(String key : icicle.keySet()) {
                Log.v(MainActivity.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(MainActivity.TAG, "    myBundle is null");
    	}
    	super.onCreate(icicle);
        if (icicle != null) {
            // Restore last state for checked position.
            mCurCheckPosition = icicle.getInt("curChoice", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater myInflater, ViewGroup container, Bundle icicle) {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onCreateView. container is " + container);
    	return super.onCreateView(myInflater, container, icicle);
    }
    
    @Override
    public void onActivityCreated(Bundle icicle) {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onActivityCreated. icicle contains:");
    	if(icicle != null) {
            for(String key : icicle.keySet()) {
                Log.v(MainActivity.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(MainActivity.TAG, "    icicle is null");
    	}
        super.onActivityCreated(icicle);

        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.myrow,R.id.label,
               Meanu_choices.Navigation));

        ListView lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setSelection(mCurCheckPosition);

        myActivity.showDetails(mCurCheckPosition);
    }

    @Override
    public void onStart() {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onStart");
    	super.onStart();
    }

    @Override
    public void onResume() {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onResume");
    	super.onResume();
    }

    @Override
    public void onPause() {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onPause");
    	super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle icicle) {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onSaveInstanceState");
        super.onSaveInstanceState(icicle);
        icicle.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onListItemClick. pos = "
    			+ pos);
        myActivity.showDetails(pos);
    	mCurCheckPosition = pos;
    }

    @Override
    public void onStop() {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onStop");
    	super.onStop();
    }

    @Override
    public void onDestroyView() {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onDestroyView");
    	super.onDestroyView();
    }

    @Override
    public void onDestroy() {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onDestroy");
    	super.onDestroy();
    }

    @Override
    public void onDetach() {
    	Log.v(MainActivity.TAG, "in NavigationFragmentTitles onDetach");
    	super.onDetach();
    	myActivity = null;
    }

	
}
