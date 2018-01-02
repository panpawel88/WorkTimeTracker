package org.ppanek.worktimetracker;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CalendarFragment.OnDaySelectedListener, StatsFragment.OnFragmentInteractionListener, CalendarNewWorkdayFragment.OnFragmentInteractionListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home: {
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.content, CalendarFragment.newInstance());
                        transaction.commit();
                        return true;
                    }
                    case R.id.navigation_stats: {
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.content, new StatsFragment());
                        transaction.commit();
                        return true;
                    }
                    case R.id.navigation_settings:
                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content, CalendarFragment.newInstance());
        transaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDaySelected(Calendar calendar) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CalendarNewWorkdayFragment calendarNewWorkdayFragment = CalendarNewWorkdayFragment.newInstance(calendar);
        fragmentTransaction.replace(R.id.calendarContent, calendarNewWorkdayFragment);
        fragmentTransaction.commit();
    }
}
