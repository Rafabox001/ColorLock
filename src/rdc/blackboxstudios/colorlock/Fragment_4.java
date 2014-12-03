package rdc.blackboxstudios.colorlock;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Fragment_4 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
final View rootView = inflater.inflate(R.layout.tuto4_fragment, container, false);
		
		showDialog1(R.string.tuto5_title,
				getString(R.string.tuto5_1));
		
		
		return rootView;
	}
	
	private void showDialog1(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// do something when the OK button is clicked
				showDialog2(R.string.tuto_end,
						getString(R.string.tuto_info));
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
				Intent i = new Intent(getActivity().getApplicationContext(), MainActivity.class);
				startActivity(i);
			}
		});
		builder.show();
	}
	

}
