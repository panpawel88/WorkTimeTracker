package org.ppanek.worktimetracker;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import org.ppanek.worktimetracker.model.interfaces.IWorkday;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddNewWorkdayFragment.OnWorkdayCreatedListener} interface
 * to handle interaction events.
 * Use the {@link AddNewWorkdayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewWorkdayFragment extends Fragment {

    private OnWorkdayCreatedListener mListener;

    public AddNewWorkdayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddNewWorkdayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewWorkdayFragment newInstance() {
        return new AddNewWorkdayFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_new_workday, container, false);

        TimePicker beginPicker = (TimePicker) view.findViewById(R.id.addNewBeginWorkday);
        beginPicker.setIs24HourView(true);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onWorkdayCreated(IWorkday workday) {
        if (mListener != null) {
            mListener.onNewWorkday(workday);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnWorkdayCreatedListener) {
            mListener = (OnWorkdayCreatedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnWorkdayCreatedListener {
        void onNewWorkday(IWorkday workday);
    }
}
