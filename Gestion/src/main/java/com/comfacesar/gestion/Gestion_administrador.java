package com.comfacesar.gestion;

import com.comfacesar.modelo.Administrador;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import jdk.nashorn.internal.parser.Token;

public class Gestion_administrador{
    //############################################################################################\\
    //###############################PROPIEDADES GLOBALES##########################################\\
    private String LLAVE_ADMINISTRADOR = Propiedades.LLAVE_ADMINISTRADOR;
    private final String TIPO_CONSULTA = Propiedades.TIPO_CONSULTA;
    private final String LLAVE_WS = Propiedades.LLAVE_WS;
    private final String JSON = Propiedades.JSON;
    private final String URL_SERVER = Propiedades.URL_SERVER;
    private final String TOKEN = Propiedades.TOKEN;
    //############################################################################################\\
    //###############################PROPIEDADES DE ADMINISTRADOR##################################\\
    private final String ID_ADMINISTRADOR = "id_administrador";
    private final String TIPO_ADMINISTRADOR = "tipo_administrador";
    private final String NOMBRE_CUENTA_ADMINISTRADOR = "nombre_cuenta_administrador";
    private final String CONTRASENA_ADMINISTRADOR = "contrasena_administrador";
    private final String NOMBRES_ADMINISTRADOR = "nombres_administrador";
    private final String APELLIDOS_ADMINISTRADOR = "apellidos_administrador";
    private final String FECHA_NACIMIENTO_ADMINISTRADOR = "fecha_nacimiento_administrador";
    private final String NUMERO_TELEFONO_ADMINISTRADOR = "numero_telefono_administrador";
    private final String DIRECCION_ADMINISTRADOR = "direccion_administrador";
    private final String CORREO_ELECTRONICO_ADMINISTRADOR = "correo_electronico_administrador";
    private final String SEXO_ADMINISTRADOR = "sexo_administrador";
    private final String ESTADO_ADMINISTRADOR = "estado_administrador";
    private final String FECHA_REGISTRO_ADMINISTRADOR = "fecha_registro_administrador";
    private final String HORA_REGISTRO_ADMINISTRADOR = "hora_registro_administrador";
    private final String URL_FOTO_PERFIL_ADMINISTRADOR = "url_foto_perfil_administrador";
    private final String URL_FOTO_PERFIL_ANTERIOR = "url_foto_perfil_anterior";
    private final String NUMERO_ASESORIAS_DADAS_ADMINISTRADOR = "numero_asesorias_dadas_administrador";
    private final String NUMERO_ASESORIAS_DADAS_PRIMERA_INFANCIA_ADMINISTRADOR = "numero_asesorias_dadas_primera_infancia_administrador";
    private final String NUMERO_ASESORIAS_DADAS_INFANCIA_ADMINISTRADOR = "numero_asesorias_dadas_infancia_administrador";
    private final String NUMERO_ASESORIAS_DADAS_ADOLECENCIA_ADMINISTRADOR = "numero_asesorias_dadas_adolecencia_administrador";
    private final String NUMERO_ASESORIAS_DADAS_JUVENTUD_ADMINISTRADOR = "numero_asesorias_dadas_juventud_administrador";
    private final String NUMERO_ASESORIAS_DADAS_ADULTEZ_ADMINISTRADOR = "numero_asesorias_dadas_adultez_administrador";
    private final String NUMERO_ASESORIAS_DADAS_MAYOR_ADMINISTRADOR = "numero_asesorias_dadas_mayor_administrador";
    private final String NUMERO_ASESORIAS_DADAS_PRIMERA_M_INFANCIA_ADMINISTRADOR = "numero_asesorias_dadas_primera_m_infancia_administrador";
    private final String NUMERO_ASESORIAS_DADAS_INFANCIA_M_ADMINISTRADOR = "numero_asesorias_dadas_infancia_m_administrador";
    private final String NUMERO_ASESORIAS_DADAS_ADOLECENCIA_M_ADMINISTRADOR = "numero_asesorias_dadas_adolecencia_m_administrador";
    private final String NUMERO_ASESORIAS_DADAS_JUVENTUD_M_ADMINISTRADOR = "numero_asesorias_dadas_juventud_m_administrador";
    private final String NUMERO_ASESORIAS_DADAS_ADULTEZ_M_ADMINISTRADOR = "numero_asesorias_dadas_adultez_m_administrador";
    private final String NUMERO_ASESORIAS_DADAS_MAYOR_M_ADMINISTRADOR = "numero_asesorias_dadas_mayor_m_administrador";
    private final String NUMERO_ASESORIAS_DADAS_PRIMERA_F_INFANCIA_ADMINISTRADOR = "numero_asesorias_dadas_primera_f_infancia_administrador";
    private final String NUMERO_ASESORIAS_DADAS_INFANCIA_F_ADMINISTRADOR = "numero_asesorias_dadas_infancia_f_administrador";
    private final String NUMERO_ASESORIAS_DADAS_ADOLECENCIA_F_ADMINISTRADOR = "numero_asesorias_dadas_adolecencia_f_administrador";
    private final String NUMERO_ASESORIAS_DADAS_JUVENTUD_F_ADMINISTRADOR = "numero_asesorias_dadas_juventud_f_administrador";
    private final String NUMERO_ASESORIAS_DADAS_ADULTEZ_F_ADMINISTRADOR = "numero_asesorias_dadas_adultez_f_administrador";
    private final String NUMERO_ASESORIAS_DADAS_MAYOR_F_ADMINISTRADOR = "numero_asesorias_dadas_mayor_f_administrador";
    private final String NUMERO_ESPECIALIDAD_ADMINISTRADOR = "numero_especialidad_administrador";
    //############################################################################################\\
    //###############################PROPIEDADES DE RELACION#######################################\\
    private final String ESPECIALIDAD = "especialidad";
    private final String SEXUALIDAD = "sexualidad";
    private final String IDENTIDAD = "identidad";
    private final String NUTRICION = "nutricion";
    private final String EMBARAZO = "embarazo";
    //############################################################################################\\
    //###############################CONSULTAS####################################################\\
    private final String CONSULTAR_ASESORES = "consultar_asesores";
    private final String CONSULTAR_POR_NOMBRE_CUENTA_NUM = "consultar_por_nombre_cuenta_num";
    private final String CAMBIAR_CONTRASENA = "cambiar_contrasena";
    private final String ACTUALIZAR_DATOS = "actualizar_datos";
    private final String ADMINISTRADOR_POR_ID = "administrador_por_id";
    private final String VALIDAR_ADMINISTRADOR = "validar_administrador";
    private final String HABILITAR_ADMINISTRADOR = "habilitar_administrador";
    private final String DESHABILITAR_ASESOR = "deshabilitar_asesor";
    private final String INSERT = "insert";
    private final String VALIDARTOKENOBTENERADMINISTRADOR = "validartokenobteneradministrador";
    private final String CERRAR_SESION = "cerrar_sesion";
    private JsonObject obj;
    private static Administrador administrador_actual = null;

    private void adjuntar_aseso()
    {
        if(getAdministrador_actual() != null)
        {
            obj.addProperty(TOKEN,administrador_actual.token);
        }
    }

    public HashMap<String, String> cerrar_sesion()
    {
        obj = new JsonObject();
        try {
            obj.addProperty(TIPO_CONSULTA,CERRAR_SESION);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
            adjuntar_aseso();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> consultar_asesores()
    {
        obj = new JsonObject();
        try {
            obj.addProperty(TIPO_CONSULTA,CONSULTAR_ASESORES);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
            adjuntar_aseso();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> consultar_por_nombre_cuenta_num(String nombre_cuenta)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(NOMBRE_CUENTA_ADMINISTRADOR,nombre_cuenta);
            obj.addProperty(TIPO_CONSULTA,CONSULTAR_POR_NOMBRE_CUENTA_NUM);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
            adjuntar_aseso();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> cambiar_contrasena(Administrador administrador)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(ID_ADMINISTRADOR, administrador.id_administrador);
            obj.addProperty(CONTRASENA_ADMINISTRADOR,administrador.contrasena_administrador);
            obj.addProperty(TIPO_CONSULTA,CAMBIAR_CONTRASENA);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
            adjuntar_aseso();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> actualizar_datos(Administrador administrador)
    {
        obj = new JsonObject();
        try {
            if(administrador.url_foto_perfil_administrador.equals(administrador.url_foto_perfil_anterior))
            {
                administrador.url_foto_perfil_administrador = administrador.url_foto_perfil_administrador.replace(URL_SERVER,"");
            }
            if(administrador.url_foto_perfil_anterior.contains(URL_SERVER))
            {
                administrador.url_foto_perfil_anterior = administrador.url_foto_perfil_anterior.replace(URL_SERVER, "");
            }
            obj.addProperty(ID_ADMINISTRADOR, administrador.id_administrador);
            obj.addProperty(NOMBRES_ADMINISTRADOR,administrador.nombres_administrador);
            obj.addProperty(APELLIDOS_ADMINISTRADOR,administrador.apellidos_administrador);
            obj.addProperty(FECHA_NACIMIENTO_ADMINISTRADOR,administrador.fecha_nacimiento_administrador);
            obj.addProperty(DIRECCION_ADMINISTRADOR,administrador.direccion_administrador);
            obj.addProperty(NUMERO_TELEFONO_ADMINISTRADOR,administrador.numero_telefono_administrador);
            obj.addProperty(CORREO_ELECTRONICO_ADMINISTRADOR,administrador.correo_electronico_administrador);
            obj.addProperty(SEXO_ADMINISTRADOR,administrador.sexo_administrador);
            adjuntar_aseso();
            obj.addProperty(TIPO_CONSULTA,ACTUALIZAR_DATOS);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
            obj.addProperty(URL_FOTO_PERFIL_ANTERIOR,administrador.url_foto_perfil_anterior);
            obj.addProperty(URL_FOTO_PERFIL_ADMINISTRADOR,administrador.url_foto_perfil_administrador);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> consultar_administrador_por_id(int id_administrador)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(ID_ADMINISTRADOR, id_administrador);
            adjuntar_aseso();
            obj.addProperty(TIPO_CONSULTA,ADMINISTRADOR_POR_ID);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> validar_administrador(Administrador administrador)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(NOMBRE_CUENTA_ADMINISTRADOR,administrador.nombre_cuenta_administrador);
            obj.addProperty(CONTRASENA_ADMINISTRADOR,administrador.contrasena_administrador);
            obj.addProperty(TIPO_CONSULTA,VALIDAR_ADMINISTRADOR);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> validartokenobteneradministrador(String token)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(TOKEN,token);
            obj.addProperty(TIPO_CONSULTA,VALIDARTOKENOBTENERADMINISTRADOR);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> habilitar_administrador(int id_administrador)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(ID_ADMINISTRADOR, id_administrador);
            adjuntar_aseso();
            obj.addProperty(TIPO_CONSULTA,HABILITAR_ADMINISTRADOR);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> deshabilitar_asesor(int id_administrador)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(ID_ADMINISTRADOR, id_administrador);
            adjuntar_aseso();
            obj.addProperty(TIPO_CONSULTA,DESHABILITAR_ASESOR);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public HashMap<String, String> registrar_administrador(Administrador administrador, boolean sexualidad, boolean identidad, boolean nutricion, boolean embarazo)
    {
        obj = new JsonObject();
        try {
            obj.addProperty(NOMBRE_CUENTA_ADMINISTRADOR,administrador.nombre_cuenta_administrador);
            obj.addProperty(CONTRASENA_ADMINISTRADOR,administrador.contrasena_administrador);
            obj.addProperty(NOMBRES_ADMINISTRADOR,administrador.nombres_administrador);
            obj.addProperty(APELLIDOS_ADMINISTRADOR,administrador.apellidos_administrador);
            obj.addProperty(FECHA_NACIMIENTO_ADMINISTRADOR,administrador.fecha_nacimiento_administrador);
            obj.addProperty(DIRECCION_ADMINISTRADOR,administrador.direccion_administrador);
            obj.addProperty(NUMERO_TELEFONO_ADMINISTRADOR,administrador.numero_telefono_administrador);
            obj.addProperty(CORREO_ELECTRONICO_ADMINISTRADOR,administrador.correo_electronico_administrador);
            obj.addProperty(SEXO_ADMINISTRADOR,administrador.sexo_administrador);
            adjuntar_aseso();
            if(sexualidad)
            {
                obj.addProperty(SEXUALIDAD,1);
            }
            if(identidad)
            {
                obj.addProperty(IDENTIDAD,1);
            }
            if(nutricion)
            {
                obj.addProperty(NUTRICION,1);
            }
            if(embarazo)
            {
                obj.addProperty(EMBARAZO,1);
            }
            obj.addProperty(TIPO_CONSULTA,INSERT);
            obj.addProperty(LLAVE_WS, LLAVE_ADMINISTRADOR);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(JSON,obj.toString());
        return hashMap;
    }

    public ArrayList<Administrador> generar_json(String respuesta)
    {
        ArrayList<Administrador> lista_elementos = new ArrayList<>();
        try {
            JsonArray array = new JsonParser().parse(respuesta).getAsJsonArray();
            for(JsonElement element : array )
            {
                lista_elementos.add (agregar_elemento(element.getAsJsonObject()));
            }
        }
        catch(JsonSyntaxException | IllegalStateException | NullPointerException e)
        {
            lista_elementos = new ArrayList<>();
        }
        return lista_elementos;
    }

    private Administrador agregar_elemento(final JsonObject jsonObject)
    {
        return new Administrador(){{
            try {
                id_administrador = jsonObject.get(ID_ADMINISTRADOR).getAsInt();
                tipo_administrador = jsonObject.get(TIPO_ADMINISTRADOR).getAsInt();
                nombre_cuenta_administrador = jsonObject.get(NOMBRE_CUENTA_ADMINISTRADOR).getAsString();
                nombres_administrador = jsonObject.get(NOMBRES_ADMINISTRADOR).getAsString();
                apellidos_administrador = jsonObject.get(APELLIDOS_ADMINISTRADOR).getAsString();
                fecha_nacimiento_administrador = jsonObject.get(FECHA_NACIMIENTO_ADMINISTRADOR).getAsString();
                numero_telefono_administrador = jsonObject.get(NUMERO_TELEFONO_ADMINISTRADOR).getAsString();
                direccion_administrador = jsonObject.get(DIRECCION_ADMINISTRADOR).getAsString();
                correo_electronico_administrador = jsonObject.get(CORREO_ELECTRONICO_ADMINISTRADOR).getAsString();
                sexo_administrador = jsonObject.get(SEXO_ADMINISTRADOR).getAsInt();
                estado_administrador = jsonObject.get(ESTADO_ADMINISTRADOR).getAsInt();
                fecha_registro_administrador = jsonObject.get(FECHA_REGISTRO_ADMINISTRADOR).getAsString();
                hora_registro_administrador = jsonObject.get(HORA_REGISTRO_ADMINISTRADOR).getAsString();
                if(!jsonObject.get(URL_FOTO_PERFIL_ADMINISTRADOR).isJsonNull())
                {
                    url_foto_perfil_administrador = jsonObject.get(URL_FOTO_PERFIL_ADMINISTRADOR).getAsString();
                }
                else
                {
                    url_foto_perfil_administrador = "";
                }
                if(jsonObject.has(TOKEN))
                {
                    if(!jsonObject.get(TOKEN).isJsonNull())
                    {
                        token = jsonObject.get(TOKEN).getAsString();
                    }
                    else
                    {
                        token = "";
                    }
                }
                else
                {
                    token = "";
                }
                numero_asesorias_dadas_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_primera_infancia_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_PRIMERA_INFANCIA_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_infancia_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_INFANCIA_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_adolecencia_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_ADOLECENCIA_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_juventud_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_JUVENTUD_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_adultez_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_ADULTEZ_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_mayor_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_MAYOR_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_primera_m_infancia_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_PRIMERA_M_INFANCIA_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_infancia_m_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_INFANCIA_M_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_adolecencia_m_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_ADOLECENCIA_M_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_juventud_m_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_JUVENTUD_M_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_adultez_m_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_ADULTEZ_M_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_mayor_m_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_MAYOR_M_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_primera_f_infancia_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_PRIMERA_F_INFANCIA_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_infancia_f_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_INFANCIA_F_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_adolecencia_f_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_ADOLECENCIA_F_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_juventud_f_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_JUVENTUD_F_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_adultez_f_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_ADULTEZ_F_ADMINISTRADOR).getAsInt();
                numero_asesorias_dadas_mayor_f_administrador = jsonObject.get(NUMERO_ASESORIAS_DADAS_MAYOR_F_ADMINISTRADOR).getAsInt();
                numero_especialidad_administrador = jsonObject.get(NUMERO_ESPECIALIDAD_ADMINISTRADOR).getAsInt();
                if(!jsonObject.get(ESPECIALIDAD).isJsonNull())
                {
                    especialidades = jsonObject.get(ESPECIALIDAD).getAsString();
                }
            } catch (JsonSyntaxException | IllegalStateException | NullPointerException e) {
                e.printStackTrace();
            }
        }};
    }

    public static Administrador getAdministrador_actual() {
        return administrador_actual;
    }

    public static void setAdministrador_actual(Administrador administrador_actual_e) {
        administrador_actual = administrador_actual_e;
    }
}
