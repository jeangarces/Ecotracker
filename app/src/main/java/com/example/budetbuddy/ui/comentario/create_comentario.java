package com.example.budetbuddy.ui.comentario;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.budetbuddy.R;

public class create_comentario extends Fragment {

    EditText text_create_id_comentario, text_create_id_usuario, text_create_entidad, text_create_id_entidad, text_create_contenido, text_create_fecha_hora;
    Button btn_create_comentario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_comentario, container, false);

        text_create_id_comentario = view.findViewById(R.id.text_create_id_comentario);
        text_create_id_usuario = view.findViewById(R.id.text_create_id_usuario);
        text_create_entidad = view.findViewById(R.id.text_create_entidad);
        text_create_id_entidad = view.findViewById(R.id.text_create_id_entidad);
        text_create_contenido = view.findViewById(R.id.text_create_contenido);
        text_create_fecha_hora = view.findViewById(R.id.text_create_fecha_hora);
        btn_create_comentario = view.findViewById(R.id.btn_create_comentario);

        btn_create_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearComentario();
            }
        });

        return view;
    }

    private void crearComentario() {
        String idComentario = text_create_id_comentario.getText().toString();
        String idUsuario = text_create_id_usuario.getText().toString();
        String entidad = text_create_entidad.getText().toString();
        String idEntidad = text_create_id_entidad.getText().toString();
        String contenido = text_create_contenido.getText().toString();
        String fechaHora = text_create_fecha_hora.getText().toString();

        if (idComentario.isEmpty() || idUsuario.isEmpty() || entidad.isEmpty() || idEntidad.isEmpty() || contenido.isEmpty() || fechaHora.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/crearComentario/" + idComentario + "/" + idUsuario + "/" + entidad + "/" + idEntidad + "/" + contenido + "/" + fechaHora;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), "Comentario creado con éxito", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", "Error en la solicitud: " + error.getMessage());
                        Toast.makeText(getActivity(), "Error en la solicitud: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(postRequest);
    }
}
