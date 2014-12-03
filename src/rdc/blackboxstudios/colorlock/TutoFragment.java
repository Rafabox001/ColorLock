package rdc.blackboxstudios.colorlock;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TutoFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		
		showDialog1(R.string.tuto_title,
				getString(R.string.tuto1));
		
		return rootView;
	}
	
	private void showDialog1(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// do something when the OK button is clicked
				showDialog2(R.string.tuto_title,
						getString(R.string.tuto1_desc));
			}
		});
		builder.show();
	}
	
	private void showDialog2(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// do something when the OK button is clicked
				FragmentManager fm = getFragmentManager(); 
				FragmentTransaction ft = fm.beginTransaction(); 
				Fragment_1 rf = new Fragment_1(); 
				
				ft.replace(R.id.container, rf); 
				ft.commit();
			}
		});
		builder.show();
	}

}
