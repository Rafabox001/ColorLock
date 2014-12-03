package rdc.blackboxstudios.colorlock;

import android.app.Activity;
import android.os.Bundle;

public class TutorialActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_tutorial);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new TutoFragment())
                    .commit();
        }
		
		
		
	}
	
	

}
