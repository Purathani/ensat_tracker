package ensat.unimelb.ensat_mobile_app;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.easypost.EasyPost;
import com.easypost.exception.EasyPostException;
import com.easypost.model.Tracker;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrackMaterialFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrackMaterialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackMaterialFragment extends Fragment {

    public  static  String TAG ="TrackMaterialFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText textTrackingNo;
  //  private EditText textCarrier;
    private TextView lblMessageText;
    private TextView lblTrackStatusText;
    private TextView lblSigned;
    private TextView lblWeight;
    private LinearLayout trackDetailLayout;
    private Spinner spinnerCarrier;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrackMaterialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrackMaterialFragment newInstance(String param1, String param2) {
        TrackMaterialFragment fragment = new TrackMaterialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TrackMaterialFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_material, container, false);

        textTrackingNo = (EditText) view.findViewById(R.id.textTrackingNo);
      //  textCarrier = (EditText) view.findViewById(R.id.textCarrier);

        lblMessageText = (TextView) view.findViewById(R.id.lblMessageText);
        lblTrackStatusText = (TextView) view.findViewById(R.id.lblTrackStatusText);
        lblWeight = (TextView) view.findViewById(R.id.lblWeightText);
        lblSigned = (TextView) view.findViewById(R.id.lblSignedText);

        trackDetailLayout =  (LinearLayout) view.findViewById(R.id.trackDetailLayout);

        spinnerCarrier = (Spinner)view.findViewById(R.id.spinnerCarrier);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.carrier_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCarrier.setAdapter(adapter);

        Button button = (Button) view.findViewById(R.id.btnTrack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    EasyPost.apiKey = "cueqNZUb3ldeWTNX7MU3Mel8UXtaAMUi";

                    Map<String, Object> params = new HashMap<String, Object>();

                    params.put("tracking_code", textTrackingNo.getText());

                    params.put("carrier", spinnerCarrier.getSelectedItem().toString());// textCarrier.getText());

                    Tracker tracker = Tracker.create(params);
                    System.out.println("succ");

                    LoadTrackingResults(tracker);

                } catch (ActivityNotFoundException anfe) {

                } catch (EasyPostException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;

    }

    public  void LoadTrackingResults(Tracker tracker)
    {
        trackDetailLayout.setVisibility(View.VISIBLE);
        lblMessageText.setText(tracker.getEstDeliveryDate().toString());
        lblTrackStatusText.setText(tracker.getStatus());
        lblWeight.setText(Float.toString(tracker.getWeight()));
        lblSigned.setText(tracker.getSignedBy());
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
//            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
