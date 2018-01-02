package org.ppanek.worktimetracker;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarNewWorkdayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarNewWorkdayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarNewWorkdayFragment extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Calendar calendar;

    public CalendarNewWorkdayFragment() {
        // Required empty public constructor
    }

    public static CalendarNewWorkdayFragment newInstance(Calendar calendar) {
        CalendarNewWorkdayFragment fragment = new CalendarNewWorkdayFragment();
        fragment.setCalendar(calendar);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar_new_workday, container, false);
        if (calendar != null) {
            TextView textView = (TextView) view.findViewById(R.id.calendar_new_workday_date);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM");
            textView.setText(format.format(calendar.getTime()));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDaySelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
