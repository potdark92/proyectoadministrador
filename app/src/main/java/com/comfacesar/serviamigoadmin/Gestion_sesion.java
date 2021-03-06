package com.comfacesar.serviamigoadmin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.comfacesar.extra.MySocialMediaSingleton;
import com.comfacesar.extra.WebService;
import com.comfacesar.gestion.Gestion_administrador;
import com.comfacesar.modelo.Administrador;

import java.util.ArrayList;
import java.util.HashMap;

public class Gestion_sesion {

    public interface EscuchadoAsignarAdministrador
    {
        void responseOk(String response, String contraseña, Gestion_sesion gestion_sesion);
        void responseError(Gestion_sesion gestion_sesion);
    }

    private EscuchadoAsignarAdministrador escuchadoAsignarAdministrador;
    private Administrador administrador;
    private SharedPreferences prefs;
    private Activity activity;
    public Gestion_sesion(Activity activity)
    {
        this.activity = activity;
        this.prefs = activity.getSharedPreferences("SESION", Context.MODE_PRIVATE);
    }

    public Gestion_sesion(Activity activity,EscuchadoAsignarAdministrador escuchadoAsignarAdministrador)
    {
        this.activity = activity;
        this.prefs = activity.getSharedPreferences("SESION", Context.MODE_PRIVATE);
        this.escuchadoAsignarAdministrador = escuchadoAsignarAdministrador;
    }

    public void salvarSesion()
    {
        SharedPreferences.Editor myEditor = prefs.edit();
        myEditor.putString("TOKEN", Gestion_administrador.getAdministrador_actual().token);
        myEditor.commit();
    }

    public void recuperarSesion()
    {
        String token = prefs.getString("TOKEN", "-1");
        administrador = null;
        if(!token.equals("-1"))
        {
            administrador = new Administrador();
            administrador.token = token;
        }
    }

    public void quitarSesionAdministrador()
    {
        SharedPreferences.Editor myEditor = prefs.edit();
        myEditor.putString("TOKEN", "-1");
        myEditor.commit();
    }

    public void asignarAdministrador()
    {
        if(administrador != null)
        {
            Gestion_administrador.setAdministrador_actual(administrador);
            HashMap<String,String> params = new Gestion_administrador().validartokenobteneradministrador(administrador.token);
            Response.Listener<String> stringListener = new Response.Listener<String>()
            {
                @Override
                public void onResponse(String response) {
                    if(escuchadoAsignarAdministrador != null)
                    {
                        escuchadoAsignarAdministrador.responseOk(response, administrador.contrasena_administrador, Gestion_sesion.this);
                    }
                    else
                    {
                        if(!response.equals("")) {
                            Log.d("response", response);
                            ArrayList<Administrador> arrayList = new Gestion_administrador().generar_json(response);
                            if (!arrayList.isEmpty()) {
                                arrayList.get(0).contrasena_administrador = administrador.contrasena_administrador;
                                Gestion_administrador.setAdministrador_actual(arrayList.get(0));
                                salvarSesion();
                            } else {
                                quitarSesionAdministrador();
                            }
                        }
                    }
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(escuchadoAsignarAdministrador != null)
                    {
                        escuchadoAsignarAdministrador.responseError(Gestion_sesion.this);
                    }
                }
            };
            StringRequest stringRequest = MySocialMediaSingleton.volley_consulta(WebService.getUrl(),params,stringListener, errorListener);
            MySocialMediaSingleton.getInstance(activity.getBaseContext()).addToRequestQueue(stringRequest);
        }

    }
}
