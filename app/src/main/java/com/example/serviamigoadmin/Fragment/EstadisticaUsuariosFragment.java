package com.example.serviamigoadmin.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.servimaigoadmin.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EstadisticaUsuariosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EstadisticaUsuariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstadisticaUsuariosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView usuariosTotalTextView;
    private TextView usuariosFemeninoTotalTextView;
    private TextView usuariosMasculinoTotalTextView;
    private TextView usuariosInfanciaTextView;
    private TextView usuariosFemeninoInfanciaTextView;
    private TextView usuariosMasculinosInfantesTextView;
    private TextView usuariosAdolecentesTextView;
    private TextView usuariosFemeninoAdolecenteTextView;
    private TextView usuariosMasculinoAdolecenteTextView;
    private TextView usuariosJovenTextView;
    private TextView usuariosFemeninoJovenTextView;
    private TextView usuariosMasculinoJovenTextView;
    private TextView usuariosAdultosTextView;
    private TextView usuariosFemeninoAdultoTextView;
    private TextView usuariosMasculinoAdultoTextView;
    private TextView usuariosMayorTextView;
    private TextView usauariosFemeninoMayorTextView;
    private TextView usuariosMasculinoMayorTextView;
    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EstadisticaUsuariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstadisticaUsuariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstadisticaUsuariosFragment newInstance(String param1, String param2) {
        EstadisticaUsuariosFragment fragment = new EstadisticaUsuariosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
        view =  inflater.inflate(R.layout.fragment_estadistica_usuarios, container, false);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosFemeninoTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosMasculinoTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        usuariosTotalTextView = view.findViewById(R.id.usuarioTotalTextView);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
