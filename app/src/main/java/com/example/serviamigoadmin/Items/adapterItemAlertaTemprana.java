package com.comfacesar.serviamigoadmin.Items;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.comfacesar.ServiAmigo.Extra.MySocialMediaSingleton;
import com.comfacesar.ServiAmigo.Extra.WebService;
import com.comfacesar.ServiAmigo.Gestion.Gestion_asunto;
import com.comfacesar.ServiAmigo.Gestion.Gestion_datos_usuario;
import com.comfacesar.ServiAmigo.Gestion.Gestion_noticia;
import com.comfacesar.ServiAmigo.Modelo.Alerta_temprana;
import com.comfacesar.ServiAmigo.Modelo.Asunto;
import com.comfacesar.ServiAmigo.Modelo.Datos_usuario;
import com.comfacesar.ServiAmigo.Modelo.Noticia;
import com.comfacesar.serviamigoadmin.R;

import java.util.ArrayList;
import java.util.HashMap;

public class adapterItemAlertaTemprana extends  RecyclerView.Adapter<adapterItemAlertaTemprana.ViewHolderDatos>{
    private ArrayList<Alerta_temprana> alertas;
    public int h;
    public adapterItemAlertaTemprana(ArrayList<Alerta_temprana> alerta_tempranas)
    {
        this.alertas = alerta_tempranas;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_alerta_temprana,null, false);
        return new ViewHolderDatos(view);
    }

    private View view;

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
        viewHolderDatos.setDatos(alertas.get(i));
    }

    @Override
    public int getItemCount() {
        return alertas.size();
    }


    public static class ViewHolderDatos extends RecyclerView.ViewHolder{
        private TextView nombre;
        private TextView telefono;
        private TextView direccion;
        private TextView descripcion;
        private TextView asunto;
        private View view;
        private Datos_usuario datos_usuario;
        public ViewHolderDatos(@NonNull final View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreTextViiewConsultaAlertaTemprana);
            direccion = itemView.findViewById(R.id.direccionTextViewConsultaAlertaTemprana);
            telefono = itemView.findViewById(R.id.telefonoTextViewConsultaAlertaTemprana);
            descripcion = itemView.findViewById(R.id.descripcionTextViewConsultaAlertaTemprana);
            asunto = itemView.findViewById(R.id.asuntoTextViiewCobsultaAlertaTemprana);
            this.view = itemView;
        }

        private void consultar_datos_usuario(Alerta_temprana alerta_temprana)
        {
            HashMap<String, String> hashMap = new Gestion_datos_usuario()._consultar_datos_usuario_por_id(alerta_temprana.datos_usuario_alerta_temprana);
            Log.d("parametros", hashMap.toString());
            Response.Listener<String> stringListener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {

                    //aqui llega la respuesta, dependiendo del tipo de la consulta la proceso
                    llenar_datos_usuario(response);
                }
            };
            StringRequest stringRequest = MySocialMediaSingleton.volley_consulta(WebService.getUrl(),hashMap,stringListener, MySocialMediaSingleton.errorListener());
            MySocialMediaSingleton.getInstance(view.getContext()).addToRequestQueue(stringRequest);
        }

        private void llenar_datos_usuario(String response)
        {
            ArrayList<Datos_usuario> aux = new Gestion_datos_usuario().generar_json(response);
            if(!aux.isEmpty())
            {
                datos_usuario = aux.get(0);
                nombre.setText(datos_usuario.nombre_dato_usuario + " " +  datos_usuario.apellidos_dato_usuario);
                direccion.setText(datos_usuario.direccion_dato_usuario);
                telefono.setText(datos_usuario.telefono_dato_usuario);
            }
            else
            {
                nombre.setText("No encontrado");
                direccion.setText("No encontradp");
                telefono.setText("No encontrado");
            }
        }

        private void llenar_datos_usuario_anonimo()
        {
            nombre.setText("Anonimo");
            direccion.setText("");
            telefono.setText("");
        }

        private void consultar_asunto(final Alerta_temprana alerta_temprana)
        {
            HashMap<String, String> hashMap = new Gestion_asunto().consultar_asuntos();
            Log.d("parametros", hashMap.toString());
            Response.Listener<String> stringListener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    //aqui llega la respuesta, dependiendo del tipo de la consulta la proceso
                    ArrayList<Asunto> aux = new Gestion_asunto().generar_json(response);
                    escoger_asunto(aux, alerta_temprana.asunto_alerta_temprana);
                }
            };
            StringRequest stringRequest = MySocialMediaSingleton.volley_consulta(WebService.getUrl(),hashMap,stringListener, MySocialMediaSingleton.errorListener());
            MySocialMediaSingleton.getInstance(view.getContext()).addToRequestQueue(stringRequest);
        }
        public void setDatos(final Alerta_temprana Alerta_temprana)
        {
            consultar_datos_usuario(Alerta_temprana);
            consultar_asunto(Alerta_temprana);

            descripcion.setText(Alerta_temprana.descripcion_alerta_temprana);
        }

        private void escoger_asunto(ArrayList<Asunto> asuntos, int id)
        {
            for(Asunto item :  asuntos)
            {
                if(id == item.id_asunto)
                {
                    asunto.setText(item.nombre_asunto);
                    return;
                }
            }
            asunto.setText("no enconrado");
        }
    }
}
