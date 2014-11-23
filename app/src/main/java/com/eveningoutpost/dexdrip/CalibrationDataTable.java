package com.eveningoutpost.dexdrip;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import com.activeandroid.Cache;

import java.util.ArrayList;


public class CalibrationDataTable extends ListActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private String menu_name = "Calibration Data Table";
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private ArrayList<String> results = new ArrayList<String>();
    private View mRootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raw_data_list);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), menu_name, this);
        getData();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        mNavigationDrawerFragment.swapContext(position);
    }

    private void getData() {
        Cursor cursor = Cache.openDatabase().rawQuery("Select * from Calibration order by _ID desc", null);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.raw_data_list_item,
                cursor,
                new String[] { "estimate_raw_at_time_of_calibration", "raw_value", "slope", "intercept" },
                new int[] { R.id.raw_data_id, R.id.raw_data_value , R.id.raw_data_slope, R.id.raw_data_timestamp });

        this.setListAdapter(adapter);
//        ListView listView = (ListView) findViewById(R.id.list);
//        listView.setAdapter(adapter);
    }


}