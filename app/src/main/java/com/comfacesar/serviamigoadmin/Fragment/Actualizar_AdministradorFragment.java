package com.comfacesar.serviamigoadmin.Fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.comfacesar.extra.Calculo;
import com.comfacesar.extra.MySocialMediaSingleton;
import com.comfacesar.extra.WebService;
import com.comfacesar.gestion.Gestion_administrador;
import com.comfacesar.modelo.Administrador;
import com.comfacesar.serviamigoadmin.Dialog.DatePickerFragment;
import com.comfacesar.serviamigoadmin.R;
import com.squareup.picasso.Picasso;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import de.hdodenhof.circleimageview.CircleImageView;
import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Actualizar_AdministradorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Actualizar_AdministradorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Actualizar_AdministradorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private EditText nombresEditText;
    private EditText apellidosEditText;
    private EditText direccionEditText;
    private EditText telefonoEditText;
    private EditText correoElectronicoEditText;
    private EditText fechaNacimientoEditText;
    private RadioButton femeninoRadioButton;
    private RadioButton masculinoRadioButton;
    private Button actualizar_datos;
    private CircleImageView fotoPerfilImageView;
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;
    private Bitmap bitmap = null;
    private Button tomar_fotoButton;
    private Button subirFotoButton;
    private Button eliminarImagenButton;
    private String url_foto_anterior;
    private int REQUEST_IMAGE_CAPTURE = 1;
    private boolean imagen_eliminada = false;
    private boolean imagen_modificada = false;
    private ProgressDialog progressDialog;
    private View view;
    public Actualizar_AdministradorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registrar_AdministradorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Actualizar_AdministradorFragment newInstance(String param1, String param2) {
        Actualizar_AdministradorFragment fragment = new Actualizar_AdministradorFragment();
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
        view =  inflater.inflate(R.layout.fragment_actualizar_mis_datos, container, false);
        actualizar_foto_perfil();
        progressDialog = new ProgressDialog(getContext());
        nombresEditText = view.findViewById(R.id.nombresAsesorEditText);
        apellidosEditText = view.findViewById(R.id.apellidosAsesorEditText);
        fechaNacimientoEditText = view.findViewById(R.id.fechaNacimientoAsesorEditText);
        correoElectronicoEditText = view.findViewById(R.id.correoElectronioAsesorEditText);
        telefonoEditText = view.findViewById(R.id.numeroTelefonoAsesorEditText);
        direccionEditText = view.findViewById(R.id.direccionAsesorEditText);
        actualizar_datos = view.findViewById(R.id.registrarAsesorButton);
        masculinoRadioButton = view.findViewById(R.id.masculinoAsesorRadioButton);
        femeninoRadioButton = view.findViewById(R.id.femeninoAsesorRadioButton);
        fotoPerfilImageView = view.findViewById(R.id.fotoPerfilImageView);
        tomar_fotoButton = view.findViewById(R.id.tomarFotoButton);
        subirFotoButton = view.findViewById(R.id.subirFotoButton);
        eliminarImagenButton = view.findViewById(R.id.eliminar_imagenButton);
        subirFotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        tomar_fotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });
        eliminarImagenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagen_eliminada = true;
                imagen_modificada = false;
                bitmap = null;
                Picasso.with(getContext()).load(R.drawable.perfil2).into(fotoPerfilImageView);
            }
        });
        fechaNacimientoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                fechaNacimientoEditText.setText(fechaNacimientoEditText.getText().toString().trim());
                if(new Calculo().validarFecha(fechaNacimientoEditText.getText().toString()))
                {
                    fechaNacimientoEditText.setTextColor(getResources().getColor(R.color.Black));
                }
                else
                {
                    fechaNacimientoEditText.setTextColor(getResources().getColor(R.color.rojo));
                }
            }
        });
        actualizar_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                progressDialog.setMessage("Actualizando datos...");
                progressDialog.setCancelable(false);
                if(nombresEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(view.getContext(), "Ingrese sus nombres", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                if(apellidosEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(view.getContext(), "Ingrese sus apellidos", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                if(fechaNacimientoEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(view.getContext(), "Ingrese su fecha de nacimiento", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                if(!new Calculo().validarFecha(fechaNacimientoEditText.getText().toString()))
                {
                    Toast.makeText(view.getContext(), "Fecha de nacimiento ingresada no valida", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                Administrador administrador = new Administrador();
                administrador.url_foto_perfil_administrador = Gestion_administrador.getAdministrador_actual().url_foto_perfil_administrador;
                administrador.url_foto_perfil_anterior = Gestion_administrador.getAdministrador_actual().url_foto_perfil_administrador;
                if(imagen_modificada)
                {
                    if(bitmap != null)
                    {
                        administrador.url_foto_perfil_administrador = bitmap_conver_to_String(bitmap);
                    }
                }
                if(imagen_eliminada)
                {
                    administrador.url_foto_perfil_administrador = "-1";
                }
                administrador.id_administrador = Gestion_administrador.getAdministrador_actual().id_administrador;
                administrador.nombres_administrador = nombresEditText.getText().toString();
                administrador.apellidos_administrador = apellidosEditText.getText().toString();
                administrador.numero_telefono_administrador = telefonoEditText.getText().toString();
                administrador.direccion_administrador = direccionEditText.getText().toString();
                administrador.correo_electronico_administrador = correoElectronicoEditText.getText().toString();
                administrador.fecha_nacimiento_administrador = fechaNacimientoEditText.getText().toString();
                if(masculinoRadioButton.isChecked())
                {
                    administrador.sexo_administrador = 0;
                }
                else
                {
                    administrador.sexo_administrador = 1;
                }
                actualizar_administrador(administrador);
            }
        });
        cargar_datos_administrador();
        return view;
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE)
        {
            imageUri = data.getData();
            fotoPerfilImageView.setImageURI(imageUri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(view.getContext().getContentResolver(), imageUri);
                imagen_modificada = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            fotoPerfilImageView.setImageBitmap(bitmap);
            imagen_modificada = true;
        }
    }

    private String bitmap_conver_to_String(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, stream);
        byte[] bytes = stream.toByteArray();
        String s = Base64.encodeToString(bytes, Base64.DEFAULT);
        return s;
    }

    private void cargar_datos_administrador()
    {
        Administrador administrador = Gestion_administrador.getAdministrador_actual();
        nombresEditText.setText(administrador.nombres_administrador);
        apellidosEditText.setText(administrador.apellidos_administrador);
        direccionEditText.setText(administrador.direccion_administrador);
        telefonoEditText.setText(administrador.numero_telefono_administrador);
        correoElectronicoEditText.setText(administrador.correo_electronico_administrador);
        fechaNacimientoEditText.setText(administrador.fecha_nacimiento_administrador);
        imagen_eliminada = false;
        imagen_modificada = false;
        if(administrador.sexo_administrador == 0)
        {
            masculinoRadioButton.setChecked(true);
        }
        else
        {
            femeninoRadioButton.setChecked(true);
        }
    }

    private void actualizar_administrador(final Administrador administrador)
    {
        final HashMap<String, String> hashMap = new Gestion_administrador().actualizar_datos(administrador);
        Response.Listener<String> stringListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    int val = Integer.parseInt(response);
                    if(val > 0)
                    {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Cuenta actualizada con exito", Toast.LENGTH_SHORT).show();
                        Gestion_administrador.getAdministrador_actual().nombres_administrador = administrador.nombres_administrador;
                        Gestion_administrador.getAdministrador_actual().apellidos_administrador = administrador.apellidos_administrador;
                        Gestion_administrador.getAdministrador_actual().fecha_nacimiento_administrador = administrador.fecha_nacimiento_administrador;
                        Gestion_administrador.getAdministrador_actual().sexo_administrador = administrador.sexo_administrador;
                        Gestion_administrador.getAdministrador_actual().direccion_administrador = administrador.direccion_administrador;
                        Gestion_administrador.getAdministrador_actual().numero_telefono_administrador = administrador.numero_telefono_administrador;
                        Gestion_administrador.getAdministrador_actual().correo_electronico_administrador = administrador.numero_telefono_administrador;
                        fechaNacimientoEditText.setTextColor(getResources().getColor(R.color.Black));
                        if(Gestion_administrador.getAdministrador_actual().tipo_administrador == 1)
                        {
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framengMaster,new ConsultaAlertasTempranasFragment()).commit();
                        }
                        else
                        {
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framengMaster,new MisAsesoriasFragment()).commit();
                        }
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Cuenta no actualizada", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NumberFormatException exc)
                {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), "Ha ocurrido un error en el servidor", Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Ha ocurrido un error en el servidor", Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest = MySocialMediaSingleton.volley_consulta(WebService.getUrl(),hashMap,stringListener, errorListener);
        MySocialMediaSingleton.getInstance(view.getContext()).addToRequestQueue(stringRequest);
    }

    private void actualizar_foto_perfil()
    {

        HashMap<String,String> params = new Gestion_administrador().consultar_administrador_por_id(Gestion_administrador.getAdministrador_actual().id_administrador);
        Response.Listener<String> stringListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                if(!response.equals(""))
                {
                    ArrayList<Administrador> arrayList = new Gestion_administrador().generar_json(response);
                    if(!arrayList.isEmpty())
                    {
                        Administrador administrador = arrayList.get(0);
                        Gestion_administrador.getAdministrador_actual().url_foto_perfil_administrador = administrador.url_foto_perfil_administrador;
                        Picasso.with(view.getContext()).load(Gestion_administrador.getAdministrador_actual().url_foto_perfil_administrador).placeholder(view.getContext().getResources().getDrawable(R.drawable.perfil2)).error(R.drawable.perfil2).into(fotoPerfilImageView);
                    }
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };
        StringRequest stringRequest = MySocialMediaSingleton.volley_consulta(WebService.getUrl(),params,stringListener, errorListener);
        MySocialMediaSingleton.getInstance(view.getContext()).addToRequestQueue(stringRequest);
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

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.create();
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
