package com.artpower.filmaticfestival;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NavigationFragment extends Fragment {
	
	private int mIndex = 0;

	public static NavigationFragment newInstance(int index) {
        Log.v(MainActivity.TAG, "in NavigationFragment newInstance(" + index + ")");

        NavigationFragment df = new NavigationFragment();

        // Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		df.setArguments(args);
		return df;
	}
	
	public static NavigationFragment newInstance(Bundle bundle) {
		int index = bundle.getInt("index", 0);
        return newInstance(index);
	}

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
    	Log.v(MainActivity.TAG,
    			"in NavigationFragment onInflate. AttributeSet contains:");
    	for(int i=0; i<attrs.getAttributeCount(); i++)
            Log.v(MainActivity.TAG, "    " + attrs.getAttributeName(i) +
            		" = " + attrs.getAttributeValue(i));
    	//super.onInflate(attrs, savedInstanceState);
    }

	@Override
    public void onAttach(Activity myActivity) {
    	Log.v(MainActivity.TAG, "in NavigationFragment onAttach; activity is: " +
    			myActivity);
    	super.onAttach(myActivity);
    }

    @Override
    public void onCreate(Bundle myBundle) {
    	Log.v(MainActivity.TAG, "in NavigationFragment onCreate. Bundle contains:");
    	if(myBundle != null) {
            for(String key : myBundle.keySet()) {
                Log.v(MainActivity.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(MainActivity.TAG, "    myBundle is null");
    	}
    	super.onCreate(myBundle);

    	mIndex = getArguments().getInt("index", 0);
    }

    public int getShownIndex() {
    	return mIndex;
    }

	/*@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
        Log.v(MainActivity.TAG, "in NavigationFragment onCreateView. container = " +
        		container);

        // Don't tie this fragment to anything through the inflater. Android
        // takes care of attaching fragments for us. The container is only
        // passed in so we can know about the container where this View
        // hierarchy is going to go. If we're not going anywhere, don't
        // bother to create the view hierarchy and just return null.
        if(container == null) {
        	Log.v(MainActivity.TAG, "container is null. No need to inflate.");
        	return null;
        }

        View v = inflater.inflate(R.layout.details, container, false);
		TextView text1 = (TextView) v.findViewById(R.id.text1);
		text1.setText(Meanu_choices.Navigation[ mIndex ] );
		return v;
	}
*/
    @Override
    public void onActivityCreated(Bundle savedState) {
    	Log.v(MainActivity.TAG,
    			"in NavigationFragment onActivityCreated. savedState contains:");
    	if(savedState != null) {
            for(String key : savedState.keySet()) {
                Log.v(MainActivity.TAG, "    " + key);
            }
    	}
    	else {
            Log.v(MainActivity.TAG, "    savedState is null");
    	}
        super.onActivityCreated(savedState);
    }

    @Override
    public void onStart() {
    	Log.v(MainActivity.TAG, "in NavigationFragment onStart");
    	super.onStart();
    }

    @Override
    public void onResume() {
    	Log.v(MainActivity.TAG, "in NavigationFragment onResume");
    	super.onResume();
    }

    @Override
    public void onPause() {
    	Log.v(MainActivity.TAG, "in NavigationFragment onPause");
    	super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    	Log.v(MainActivity.TAG, "in NavigationFragment onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
    	Log.v(MainActivity.TAG, "in NavigationFragment onStop");
    	super.onStop();
    }

    @Override
    public void onDestroyView() {
    	Log.v(MainActivity.TAG, "in NavigationFragment onDestroyView, view = " +
    			getView());
    	super.onDestroyView();
    }

    @Override
    public void onDestroy() {
    	Log.v(MainActivity.TAG, "in NavigationFragment onDestroy");
    	super.onDestroy();
    }

    @Override
    public void onDetach() {
    	Log.v(MainActivity.TAG, "in NavigationFragment onDetach");
    	super.onDetach();
    }
}