package com.cide.appadsotarde;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Buscar extends AppCompatActivity {

    Button regresar, btnActualizar, btnBuscar;

    EditText boxDoc, boxPass, boxEstado, boxFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        boxDoc = findViewById(R.id.boxDoc);
        boxPass = findViewById(R.id.boxPass);
        boxEstado = findViewById(R.id.boxEstado);
        boxFecha = findViewById(R.id.boxFecha);

        regresar = findViewById(R.id.regresar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnBuscar = findViewById(R.id.btnBuscar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Buscar.this, MenuPrincipal.class));
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar("https://kratoshoteleria.000webhostapp.com/buscar.php");
            }
        });
    }

    private void buscar(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               if (!response.isEmpty()) {
                   // Esto se ejecuta si la respuesta no está vacía
                   try {
                       JSONArray jsonArray = new JSONArray(response);

                       // Verifica si el arreglo contiene elementos
                       if (jsonArray.length() > 0) {
                           JSONObject jsonObject = jsonArray.getJSONObject(0); // Suponiendo que solo hay un objeto JSON en el arreglo

                           // Obtén los valores del JSON y asígnalos a las cajas de texto correspondientes
                           String nroDoc = jsonObject.getString("nro_doc");
                           //String contrasena = jsonObject.getString("contrasena");
                           //String usuEstado = jsonObject.getString("usu_estado");
                           //String restablecimiento = jsonObject.getString("restablecimiento");

                           // Asigna los valores a las cajas de texto
                           boxDoc.setText(nroDoc);
                           //boxPass.setText(contrasena);
                           //boxEstado.setText(usuEstado);
                           //boxFecha.setText(restablecimiento);

                           Toast.makeText(getApplicationContext(), "Búsqueda exitosa"+response, Toast.LENGTH_SHORT).show();
                       } else {
                           // Esto se ejecuta si el arreglo está vacío
                           Toast.makeText(getApplicationContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                       }
                   } catch (JSONException e) {
                       Toast.makeText(getApplicationContext(), "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                       e.printStackTrace(); // Agrega esto para imprimir la traza completa de la excepción en el registro
                   }
               } else {
                   // Esto se ejecuta si la respuesta está vacía
                   Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_SHORT).show();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en la solicitud: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("nro_doc", boxDoc.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }}

                    /*JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, (response) -> {
                        JSONObject jsonObject = null;
                        for(int i = 0; i < response.length(); i++){
                            try{
                                jsonObject= response.(i);
                                boxDoc.setText(jsonObject.getString("nro_doc"));
                            }catch (JSONException e){
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    });

                    Toast.makeText(getApplicationContext(), "Busqueda exitosa", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Usuario no existe", Toast.LENGTH_SHORT).show();
                }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();

                parametros.put("nro_doc", boxDoc.getText().toString());
                parametros.put("contrasena",boxPass.getText().toString());
                parametros.put("usu_estado",boxEstado.getText().toString());
                parametros.put("restablecimiento",boxFecha.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}};*/