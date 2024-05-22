package com.example.budetbuddy.ui.notificacion;

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
import org.json.JSONException;
import org.json.JSONObject;

public class read_notificacion extends Fragment {

    EditText text_read_id_notificacion, text_read_id_usuario_destinatario, text_read_contenido, text_read_tipo, text_read_fecha_hora, text_read_estado;
    Button btn_read_notificacion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_notificacion, container, false);

        text_read_id_notificacion = view.findViewById(R.id.text_read_id_notificacion);
        text_read_id_usuario_destinatario = view.findViewById(R.id.text_read_id_usuario_destinatario);
        text_read_contenido = view.findViewById(R.id.text_read_contenido);
        text_read_tipo = view.findViewById(R.id.text_read_tipo);
        text_read_fecha_hora = view.findViewById(R.id.text_read_fecha_hora);
        text_read_estado = view.findViewById(R.id.text_read_estado);
        btn_read_notificacion = view.findViewById(R.id.btn_read_notificacion);

        btn_read_notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarNotificacionPorID();
            }
        });

        return view;
    }

    private void buscarNotificacionPorID() {
        String idNotificacion = text_read_id_notificacion.getText().toString();

        if (idNotificacion.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, ingrese el ID de la notificación", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/buscarNotificacionPorID/" + idNotificacion;

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            text_read_id_usuario_destinatario.setText(jsonObject.getString("id_usuario_destinatario"));
                            text_read_contenido.setText(jsonObject.getString("contenido"));
                            text_read_tipo.setText(jsonObject.getString("tipo"));
                            text_read_fecha_hora.setText(jsonObject.getString("fecha_hora"));
                            text_read_estado.setText(jsonObject.getString("estado"));
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
