package rdc.blackboxstudios.colorlock;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView option1, option2, option3, option4, option5, option6, choice1, choice2, choice3, choice4, 
				lock1, lock2, lock3, lock4, chk1, chk2, chk3, chk4;
	private ImageButton lock_btn, refresh, tutorial;
	private static final int CHOICES_AMOUNT = 4;
	private static int fCounter = 0;
	private static int gCounter = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent objIntent = new Intent(MainActivity.this, PlayAudio.class);
		startService(objIntent);
		
		//secret locks
		lock1 = (TextView)findViewById(R.id.lock_1);
		lock1.setVisibility(View.INVISIBLE);
		lock2 = (TextView)findViewById(R.id.lock_2);
		lock2.setVisibility(View.INVISIBLE);
		lock3 = (TextView)findViewById(R.id.lock_3);
		lock3.setVisibility(View.INVISIBLE);
		lock4 = (TextView)findViewById(R.id.lock_4);
		lock4.setVisibility(View.INVISIBLE);
		
		//check validation
		chk1 = (TextView)findViewById(R.id.check1);
		chk2 = (TextView)findViewById(R.id.check2);
		chk3 = (TextView)findViewById(R.id.check3);
		chk4 = (TextView)findViewById(R.id.check4);
		
		//views to drag
		option1 = (TextView)findViewById(R.id.option_1);
		option2 = (TextView)findViewById(R.id.option_2);
		option3 = (TextView)findViewById(R.id.option_3);
		option4 = (TextView)findViewById(R.id.option_4);
		option5 = (TextView)findViewById(R.id.option_5);
		option6 = (TextView)findViewById(R.id.option_6);
		 
		//views to drop onto
		choice1 = (TextView)findViewById(R.id.choice_1);    
		choice2 = (TextView)findViewById(R.id.choice_2);
		choice3 = (TextView)findViewById(R.id.choice_3);
		choice4 = (TextView)findViewById(R.id.choice_4);
		/*remind1 = (TextView)findViewById(R.id.remind_1);
		remind2 = (TextView)findViewById(R.id.remind_2);
		remind3 = (TextView)findViewById(R.id.remind_3);
		remind4 = (TextView)findViewById(R.id.remind_4);*/


		//set touch listeners
		option1.setOnTouchListener(new ChoiceTouchListener());
		option2.setOnTouchListener(new ChoiceTouchListener());
		option3.setOnTouchListener(new ChoiceTouchListener());
		option4.setOnTouchListener(new ChoiceTouchListener());
		option5.setOnTouchListener(new ChoiceTouchListener());
		option6.setOnTouchListener(new ChoiceTouchListener());
		
		//set drag listeners
		choice1.setOnDragListener(new ChoiceDragListener());
		choice2.setOnDragListener(new ChoiceDragListener());
		choice3.setOnDragListener(new ChoiceDragListener());
		choice4.setOnDragListener(new ChoiceDragListener());
		
		//valitaion button
		lock_btn = (ImageButton)findViewById(R.id.imageButton1);
		
		lock_btn.setOnClickListener(new ListenerClicks ());
		
		ColorUtil color = new ColorUtil();
		color.setColorVariedad(6);
		String [] coloresRandom = color.getColores();
		lock1.setBackgroundColor(Color.parseColor(coloresRandom[0]));
		lock2.setBackgroundColor(Color.parseColor(coloresRandom[1]));
		lock3.setBackgroundColor(Color.parseColor(coloresRandom[2]));
		lock4.setBackgroundColor(Color.parseColor(coloresRandom[3]));
		
		
		refresh = (ImageButton)findViewById(R.id.btn_refresh);
		refresh.setOnClickListener(new ListenerRefresh());
		
		tutorial = (ImageButton)findViewById(R.id.tuto_btn);
		tutorial.setOnClickListener(new ListenerTutorial());
		
	}
	
	
	
	private class ListenerRefresh implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			/*remind1.setVisibility(View.INVISIBLE);
			remind2.setVisibility(View.INVISIBLE);
			remind3.setVisibility(View.INVISIBLE);
			remind4.setVisibility(View.INVISIBLE);*/
			Intent objIntent = new Intent(MainActivity.this, PlayAudio.class);
			stopService(objIntent);  
			
			Intent intent = getIntent();
			fCounter=0;
			gCounter = 0;
			finish();
			startActivity(intent);
		}
	}
	
	private class ListenerTutorial implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			
			showDialog(R.string.tutorial,
					getString(R.string.tuto_ask)); 
		}
	}
	
	private void showDialog(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("Iniciar", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// do something when the OK button is clicked
				/*remind1.setVisibility(View.INVISIBLE);
				remind2.setVisibility(View.INVISIBLE);
				remind3.setVisibility(View.INVISIBLE);
				remind4.setVisibility(View.INVISIBLE);*/
				Intent objIntent = new Intent(MainActivity.this, PlayAudio.class);
				stopService(objIntent);  
				
				fCounter=0;
				gCounter = 0;
				Intent intent = new Intent();
                intent.setClass(MainActivity.this, TutorialActivity.class);
                startActivity(intent);
			}
		});
		builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// do something when the cancel button is clicked
				
			}
		});
		builder.show();
	}
	
	private void showDialogEnd(int title, CharSequence message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// do something when the OK button is clicked
				
				/*remind1.setVisibility(View.INVISIBLE);
				remind2.setVisibility(View.INVISIBLE);
				remind3.setVisibility(View.INVISIBLE);
				remind4.setVisibility(View.INVISIBLE);*/
				Intent objIntent = new Intent(MainActivity.this, PlayAudio.class);
				stopService(objIntent);  
				Intent intent = getIntent();
				fCounter=0;
				gCounter = 0;
				finish();
				startActivity(intent);
			}
		});
		builder.show();
	}
	
	private class ListenerClicks implements OnClickListener{
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			
			
			ColorDrawable l1 =(ColorDrawable) lock1.getBackground();
			ColorDrawable l2 =(ColorDrawable) lock2.getBackground();
			ColorDrawable l3 =(ColorDrawable) lock3.getBackground();
			ColorDrawable l4 =(ColorDrawable) lock4.getBackground();
			
			ColorDrawable c1 =(ColorDrawable) choice1.getBackground();
			ColorDrawable c2 =(ColorDrawable) choice2.getBackground();
			ColorDrawable c3 =(ColorDrawable) choice3.getBackground();
			ColorDrawable c4 =(ColorDrawable) choice4.getBackground();
			
			/*remind1.setBackground(choice1.getBackground());
			remind2.setBackground(choice2.getBackground());
			remind3.setBackground(choice3.getBackground());
			remind4.setBackground(choice4.getBackground());
			remind1.setVisibility(View.VISIBLE);
			remind2.setVisibility(View.VISIBLE);
			remind3.setVisibility(View.VISIBLE);
			remind4.setVisibility(View.VISIBLE);*/
			
			
			int [] choices = new int[4];
			
			choices[0] = c1.getColor();
			choices[1] = c2.getColor();
			choices[2] = c3.getColor();
			choices[3] = c4.getColor();
			
			List<Integer> locks = new ArrayList<Integer>();
			
			locks.add(l1.getColor());
			locks.add(l2.getColor());
			locks.add(l3.getColor());
			locks.add(l4.getColor());
			
			
			
			Drawable [] grades = califica(locks,choices);
			
			chk1.setBackground(grades[0]);
			chk2.setBackground(grades[1]);
			chk3.setBackground(grades[2]);
			chk4.setBackground(grades[3]);
			
			
			
			option1.setVisibility(View.VISIBLE);
			option2.setVisibility(View.VISIBLE);
			option3.setVisibility(View.VISIBLE);
			option4.setVisibility(View.VISIBLE);
			option5.setVisibility(View.VISIBLE);
			option6.setVisibility(View.VISIBLE);
			
			fCounter = 0;
			
			choice1.setEnabled(true);
			choice2.setEnabled(true);
			choice3.setEnabled(true);
			choice4.setEnabled(true);
			choice1.setBackground(getResources().getDrawable(R.drawable.choice));
			choice2.setBackground(getResources().getDrawable(R.drawable.choice));
			choice3.setBackground(getResources().getDrawable(R.drawable.choice));
			choice4.setBackground(getResources().getDrawable(R.drawable.choice));
			lock_btn.setVisibility(View.INVISIBLE);
			allGood();
			gCounter = 0;
		}
	}
	
	private void allGood(){
		
		if (gCounter == CHOICES_AMOUNT){
			showDialogEnd(R.string.end,
					getString(R.string.cong));
		}
		
	}
	
	private Drawable [] califica(
						  List<Integer> locks,
						  int [] choices ){
		
		int i = 0;
		Drawable [] ds = new Drawable[CHOICES_AMOUNT];
		Drawable d = null ;
		
		
		for(Integer lop : locks){
			
			if(lop == choices[i]){
				d = getResources().getDrawable( R.drawable.chk_correct );
				gCounter++;
			}
			else if(locks.contains(choices[i])){
				d = getResources().getDrawable( R.drawable.chk_move );
					}
			else{
				d = getResources().getDrawable( R.drawable.chk_wrong );
			}
			ds[i] = d;
			i++;
		}
		return ds;
	}
	
	private final class ChoiceTouchListener implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) { 
			    //setup drag
				ClipData data = ClipData.newPlainText("", "");
				DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
				
				//start dragging the item touched
				v.startDrag(data, shadowBuilder, v, 0);
			    return true;
			} 
			else {
			    return false;
			}
		}
		 
	}
	
	private class ChoiceDragListener implements OnDragListener {

		@Override
		public boolean onDrag(View v, DragEvent event) {
			switch (event.getAction()) {
		    case DragEvent.ACTION_DRAG_STARTED:
		        //no action necessary
		        break;
		    case DragEvent.ACTION_DRAG_ENTERED:
		        //no action necessary
		        break;
		    case DragEvent.ACTION_DRAG_EXITED:        
		        //no action necessary
		        break;
		    case DragEvent.ACTION_DROP:
		        //handle the dragged view being dropped over a drop view
		    	//handle the dragged view being dropped over a target view
		    	View view = (View) event.getLocalState();
		    	//stop displaying the view where it was before it was dragged
		    	view.setVisibility(View.INVISIBLE);
		    	//view dragged item is being dropped on
		    	TextView dropTarget = (TextView) v;
		    	//view being dragged and dropped
		    	TextView dropped = (TextView) view;
		    	//update the text in the target view to reflect the data being dropped
		    	dropTarget.setBackground(dropped.getBackground());
		    	//make it bold to highlight the fact that an item has been dropped
		    	dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
		    	//if an item has already been dropped here, there will be a tag
		    	Object tag = dropTarget.getTag();
		    	//if there is already an item here, set it back visible in its original place
		    	if(tag!=null)
		    	{
		    	    //the tag is the view id already dropped here
		    	    int existingID = (Integer)tag;
		    	    //set the original view visible again
		    	    findViewById(existingID).setVisibility(View.VISIBLE);
		    	}
		    	//set the tag in the target view to the ID of the view being dropped
		    	dropTarget.setTag(dropped.getId());
		    	dropTarget.setEnabled(false);
		    	validateChoices();
		        break;
		    case DragEvent.ACTION_DRAG_ENDED:
		        //no action necessary
		        break;
		    default:
		        break;
			}
			
		return true;
		}
		 
	}
	
	private void validateChoices(){
				
		fCounter ++;
		//System.out.println("Contador : " + fCounter);
		if(	fCounter == CHOICES_AMOUNT ){
			lock_btn.setVisibility(View.VISIBLE);
		}
		else{
			lock_btn.setVisibility(View.INVISIBLE);
		}
	}
		
}
