package com.artpower.filmaticfestival;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class NavigationFragment extends ListFragment {

	NavigationListener mCallback;
	public interface NavigationListener{
		
		public void OnNavigationSelection(int pos);
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
	}

	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

}
