package org.ppanek.worktimetracker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import io.objectbox.BoxStore;
import org.ppanek.worktimetracker.model.Properties;
import org.ppanek.worktimetracker.model.WorkTimeTrackerFactory;
import org.ppanek.worktimetracker.model.WorkTimeTrackerInstance;
import org.ppanek.worktimetracker.model.db.BoxStoreInstance;
import org.ppanek.worktimetracker.model.db.MyObjectBox;
import org.ppanek.worktimetracker.model.interfaces.IWorkTimeTracker;
import org.ppanek.worktimetracker.model.interfaces.IWorkday;

import java.io.File;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CalendarFragment.OnDaySelectedListener, StatsFragment.OnFragmentInteractionListener, CalendarNewWorkdayFragment.OnFragmentInteractionListener, AddNewWorkdayFragment.OnWorkdayCreatedListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home: {
                replaceFragment(R.id.content, CalendarFragment.newInstance());
                return true;
            }
            case R.id.navigation_stats: {
                replaceFragment(R.id.content, new StatsFragment());
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

        initializeBoxStore();

        initializeWorkTimeTracker();

        setContentView(R.layout.activity_main);

        replaceFragment(R.id.content, CalendarFragment.newInstance());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initializeBoxStore() {
        if (BoxStoreInstance.Get() == null) {
            BoxStore boxStore = MyObjectBox.builder().androidContext(MainActivity.this).build();
            BoxStoreInstance.Set(boxStore);
        }
    }

    private void initializeWorkTimeTracker() {
        File filesDir = getApplicationContext().getFilesDir();
        Properties properties = new Properties(filesDir);
        IWorkTimeTracker workTimeTracker = WorkTimeTrackerFactory.create(properties);
        WorkTimeTrackerInstance.Set(workTimeTracker);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onDaySelected(Calendar calendar) {
        replaceFragment(R.id.calendarContent, CalendarNewWorkdayFragment.newInstance(calendar));
    }

    private void replaceFragment(int viewId, Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(viewId, fragment);
        transaction.commit();
    }

    @Override
    public void onNewWorkday(IWorkday workday) {

    }
}
