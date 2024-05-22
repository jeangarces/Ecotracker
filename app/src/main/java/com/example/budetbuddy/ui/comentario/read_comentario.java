package com.example.budetbuddy.ui.comentario;

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

public class read_comentario extends Fragment {

    EditText text_read_id_comentario, text_read_id_usuario, text_read_entidad, text_read_id_entidad, text_read_contenido, text_read_fecha_hora;
    Button btn_read_comentario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_comentario, container, false);

        text_read_id_comentario = view.findViewById(R.id.text_read_id_comentario);
        text_read_id_usuario = view.findViewById(R.id.text_read_id_usuario);
        text_read_entidad = view.findViewById(R.id.text_read_entidad);
        text_read_id_entidad = view.findViewById(R.id.text_read_id_entidad);
        text_read_contenido = view.findViewById(R.id.text_read_contenido);
        text_read_fecha_hora = view.findViewById(R.id.text_read_fecha_hora);
        btn_read_comentario = view.findViewById(R.id.btn_read_comentario);

        btn_read_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarComentarioPorID();
            }
        });

        return view;
    }

    private void buscarComentarioPorID() {
        String idComentario = text_read_id_comentario.getText().toString();

        if (idComentario.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID del Comentario", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/buscarComentarioPorID/" + idComentario;

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            text_read_id_usuario.setText(jsonObject.getString("id_usuario"));
                            text_read_entidad.setText(jsonObject.getString("entidad"));
                            text_read_id_entidad.setText(jsonObject.getString("id_entidad"));
                            text_read_contenido.setText(jsonObject.getString("contenido"));
                            text_read_fecha_hora.setText(jsonObject.getString("fecha_hora"));
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
