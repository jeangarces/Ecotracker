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

public class update_comentario extends Fragment {

    EditText text_update_id_comentario, text_update_id_usuario, text_update_entidad, text_update_id_entidad, text_update_contenido, text_update_fecha_hora;
    Button btn_update_comentario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_comentario, container, false);

        text_update_id_comentario = view.findViewById(R.id.text_update_id_comentario);
        text_update_id_usuario = view.findViewById(R.id.text_update_id_usuario);
        text_update_entidad = view.findViewById(R.id.text_update_entidad);
        text_update_id_entidad = view.findViewById(R.id.text_update_id_entidad);
        text_update_contenido = view.findViewById(R.id.text_update_contenido);
        text_update_fecha_hora = view.findViewById(R.id.text_update_fecha_hora);
        btn_update_comentario = view.findViewById(R.id.btn_update_comentario);

        btn_update_comentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarComentarioPorID();
            }
        });

        return view;
    }

    private void actualizarComentarioPorID() {
        String idComentario = text_update_id_comentario.getText().toString();
        String nuevoIdUsuario = text_update_id_usuario.getText().toString();
        String nuevaEntidad = text_update_entidad.getText().toString();
        String nuevoIdEntidad = text_update_id_entidad.getText().toString();
        String nuevoContenido = text_update_contenido.getText().toString();
        String nuevaFechaHora = text_update_fecha_hora.getText().toString();

        if (idComentario.isEmpty() || nuevoIdUsuario.isEmpty() || nuevaEntidad.isEmpty() || nuevoIdEntidad.isEmpty() || nuevoContenido.isEmpty() || nuevaFechaHora.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/actualizarComentarioPorID/" + idComentario + "/" + nuevoIdUsuario + "/" + nuevaEntidad + "/" + nuevoIdEntidad + "/" + nuevoContenido + "/" + nuevaFechaHora;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Comentario actualizado con éxito", Toast.LENGTH_SHORT).show();
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

        Volley.newRequestQueue(requireContext()).add(postRequest);
    }
}
