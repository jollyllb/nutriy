package sfsu.csc780.jied.nutriy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NutritionChartFragment extends Fragment {
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	return inflater.inflate(R.layout.fragment_nutrition_chart, container, false);        
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	//initializeView();
    }
    
    /**
     * creating a custom View component and drawing with a Canvas in View.onDraw()
     */
}