package com.artpower.filmaticfestival;









import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NavigationFragment extends ListFragment {
	 private MainActivity myActivity = null;
	 int mCurCheckPosition = 0;
	
	 NavigationListener mCallback;
	public interface NavigationListener{
		
		public void OnNavigationSelection(int pos);
	}
	
	public static NavigationFragment newInstance(int index) {
        Log.v("hey", "in DetailsFragment newInstance(" + index + ")");

        NavigationFragment nf = new NavigationFragment();

        // Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		nf.setArguments(args);
		return nf;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int layout = android.R.layout.simple_list_item_1;
		
		
		setListAdapter(new ArrayAdapter<String>(getActivity(),layout, Meanu_choices.Navigation));
		
	}
	
	
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.myActivity = (MainActivity)activity;
	}

	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	public int getShownIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void onListItemClick(ListView l, View v, int pos, long id) {
    	Log.println(Log.ASSERT, myActivity+"","h" );
        myActivity.showDetails(pos);
    	mCurCheckPosition = pos;
    }



	

	

}
