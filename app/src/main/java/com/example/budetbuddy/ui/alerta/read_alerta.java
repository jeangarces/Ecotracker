package com.example.budetbuddy.ui.alerta;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.budetbuddy.R;

import org.json.JSONException;
import org.json.JSONObject;

public class read_alerta extends Fragment {

    EditText text_id_alerta_buscar, text_ubicacion, text_fecha_hora, text_descripcion, text_nivel_gravedad, text_estado;
    Button btn_buscar_alerta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_alerta, container, false);

        text_id_alerta_buscar = view.findViewById(R.id.text_id_alerta_buscar);
        text_ubicacion = view.findViewById(R.id.text_ubicacion);
        text_fecha_hora = view.findViewById(R.id.text_fecha_hora);
        text_descripcion = view.findViewById(R.id.text_descripcion);
        text_nivel_gravedad = view.findViewById(R.id.text_nivel_gravedad);
        text_estado = view.findViewById(R.id.text_estado);
        btn_buscar_alerta = view.findViewById(R.id.btn_buscar_alerta);

        btn_buscar_alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarAlertaPorID();
            }
        });

        return view;
    }

    private void buscarAlertaPorID() {
        String idAlertaBuscar = text_id_alerta_buscar.getText().toString();

        if (idAlertaBuscar.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID de la alerta", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/buscarAlertaPorID/" + idAlertaBuscar;

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            text_ubicacion.setText(jsonObject.getString("ubicacion"));
                            text_fecha_hora.setText(jsonObject.getString("fecha_hora"));
                            text_descripcion.setText(jsonObject.getString("descripcion"));
                            text_nivel_gravedad.setText(jsonObject.getString("nivel_gravedad"));
                            text_estado.setText(jsonObject.getString("estado"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Error al analizar la respuesta del servidor", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Error en la solicitud: " + error.getMessage());
                        Toast.makeText(getActivity(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(getRequest);
    }
}
