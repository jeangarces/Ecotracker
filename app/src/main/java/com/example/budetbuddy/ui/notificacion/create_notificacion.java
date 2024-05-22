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

public class create_notificacion extends Fragment {

    EditText text_create_id_notificacion, text_create_id_usuario_destinatario, text_create_contenido, text_create_tipo, text_create_fecha_hora, text_create_estado;
    Button btn_create_notificacion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_notificacion, container, false);

        text_create_id_notificacion = view.findViewById(R.id.text_create_id_notificacion);
        text_create_id_usuario_destinatario = view.findViewById(R.id.text_create_id_usuario_destinatario);
        text_create_contenido = view.findViewById(R.id.text_create_contenido);
        text_create_tipo = view.findViewById(R.id.text_create_tipo);
        text_create_fecha_hora = view.findViewById(R.id.text_create_fecha_hora);
        text_create_estado = view.findViewById(R.id.text_create_estado);
        btn_create_notificacion = view.findViewById(R.id.btn_create_notificacion);

        btn_create_notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearNotificacion();
            }
        });

        return view;
    }

    private void crearNotificacion() {
        String idNotificacion = text_create_id_notificacion.getText().toString();
        String idUsuarioDestinatario = text_create_id_usuario_destinatario.getText().toString();
        String contenido = text_create_contenido.getText().toString();
        String tipo = text_create_tipo.getText().toString();
        String fechaHora = text_create_fecha_hora.getText().toString();
        String estado = text_create_estado.getText().toString();

        if (idNotificacion.isEmpty() || idUsuarioDestinatario.isEmpty() || contenido.isEmpty() || tipo.isEmpty() || fechaHora.isEmpty() || estado.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/crearNotificacion/" + idNotificacion + "/" + idUsuarioDestinatario + "/" + contenido + "/" + tipo + "/" + fechaHora + "/" + estado;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Notificación creada con éxito", Toast.LENGTH_SHORT).show();
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
