package com.comfacesar.serviamigoadmin.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.comfacesar.extra.MySocialMediaSingleton;
import com.comfacesar.extra.WebService;
import com.comfacesar.gestion.Gestion_chat_asesoria;
import com.comfacesar.gestion.Gestion_especialidad;
import com.comfacesar.modelo.Chat_asesoria;
import com.comfacesar.modelo.Especialidad;
import com.comfacesar.serviamigoadmin.Adapter.AdapterChat;
import com.comfacesar.serviamigoadmin.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MisAsesoriasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MisAsesoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisAsesoriasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private RecyclerView chat_asesorRecyclerView;
    private Spinner tipo_asesoriasSpinner;
    private ArrayList<Especialidad> especialidades;
    private ArrayList<Chat_asesoria> chat_asesoria_general;
    private ArrayList<Chat_asesoria> chat_asesoria_filtrada;
    private AdapterChat adapterChat;
    private Especialidad especialidad_selecionada;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public MisAsesoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisAsesoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisAsesoriasFragment newInstance(String param1, String param2) {
        MisAsesoriasFragment fragment = new MisAsesoriasFragment();
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
        view = inflater.inflate(R.layout.fragment_mis_asesorias, container, false);
        chat_asesorRecyclerView = view.findViewById(R.id.mis_asesoriasRecyclerView);
        tipo_asesoriasSpinner = view.findViewById(R.id.tipo_asesoriasSpinner);
        Gestion_chat_asesoria.arrayChatCambiado = new Gestion_chat_asesoria.ArrayChatCambiado() {
            @Override
            public void chatCambiado() {
                Log.d("Cambio", "true");
                cargar_chat("");
            }
        };
        return view;
    }

    private void iniciar_evento()
    {
        tipo_asesoriasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                {
                    especialidad_selecionada = especialidades.get(position - 1);
                    filtrar_chat(especialidad_selecionada.id_especialidad);
                }
                else
                {
                    especialidad_selecionada = null;
                    chat_asesoria_filtrada = chat_asesoria_general;
                }
                adapterChat = new AdapterChat(chat_asesoria_filtrada, getFragmentManager());
                chat_asesorRecyclerView.setAdapter(adapterChat);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void filtrar_chat(int filtro)
    {
        chat_asesoria_filtrada = new ArrayList<>();
        int cont = 0;
        for(Chat_asesoria item : chat_asesoria_general) {
            if (item.especializacion_chat_asesoria == filtro) {
                chat_asesoria_filtrada.add(item);
            }
        }
    }

    private void consultar_especializaciones()
    {
        HashMap<String, String> hashMap = new Gestion_especialidad().consultar_especialidades();
        Response.Listener<String> stringListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                cargar_especializaciones(response);
            }
        };
        StringRequest stringRequest = MySocialMediaSingleton.volley_consulta(WebService.getUrl(),hashMap,stringListener, MySocialMediaSingleton.errorListener());
        MySocialMediaSingleton.getInstance(view.getContext()).addToRequestQueue(stringRequest);
    }

    private void cargar_especializaciones(String json)
    {
        especialidades = new Gestion_especialidad().generar_json(json);
        String[] asuntosArray;
        if(!especialidades.isEmpty())
        {
            asuntosArray = asuntos_a_array_string();
        }
        else
        {
            asuntosArray= asuntos_a_array_string_vacio();

        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_spinner_item, asuntosArray);
        tipo_asesoriasSpinner.setAdapter(arrayAdapter);
        iniciar_evento();
    }

    private String[] asuntos_a_array_string()
    {
        String[] array = new String[especialidades.size() + 1];
        array[0] = "Selecione una especialidad";
        int cont = 1;
        for(Especialidad item : especialidades)
        {
            array[cont] = item.nombre_especialidad;
            cont ++;
        }
        return array;
    }

    private String[] asuntos_a_array_string_vacio()
    {
        String[] array = new String[1];
        array[0] = "No hay especialidades";
        return array;
    }

    private void cargar_chat(String json)
    {
        chat_asesoria_general = Gestion_chat_asesoria.getChat_asesorias();
        chat_asesorRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        if(especialidad_selecionada != null)
        {
            filtrar_chat(especialidad_selecionada.id_especialidad);
        }
        else
        {
            chat_asesoria_filtrada = chat_asesoria_general;
        }
        adapterChat = new AdapterChat(chat_asesoria_filtrada, getFragmentManager());
        chat_asesorRecyclerView.setAdapter(adapterChat);
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
    public void onResume() {
        super.onResume();
        consultar_especializaciones();
        cargar_chat("");
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
