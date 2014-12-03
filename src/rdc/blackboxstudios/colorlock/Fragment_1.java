package rdc.blackboxstudios.colorlock;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Fragment_1 extends Fragment {
	
	private ImageView next;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final View rootView = inflater.inflate(R.layout.tuto1_fragment, container, false);
		
		showDialog1(R.string.tuto2_title,
				getString(R.string.tuto2_1));
		
		next = (ImageView)rootView.findViewById(R.id.imageNext);
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				FragmentManager fm = getFragmentManager(); 
				FragmentTransaction ft = fm.beginTransaction(); 
				Fragment_2 rf = new Fragment_2(); 
				
				ft.replace(R.id.container, rf); 
				ft.commit();
				
			}
		});
		
		return rootView;
	}
	
	private void showDialog1(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// do something when the OK button is clicked
				showDialog2(R.string.tuto2_title,
						getString(R.string.tuto2_2));
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
				next.setVisibility(View.VISIBLE);
			}
		});
		builder.show();
	}
}
