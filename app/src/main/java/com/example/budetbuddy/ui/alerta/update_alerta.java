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

public class update_alerta extends Fragment {

    EditText text_id_alerta_actualizar, text_nueva_ubicacion, text_nueva_fecha_hora, text_nueva_descripcion, text_nueva_gravedad, text_nuevo_estado;
    Button btn_actualizar_alerta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_alerta, container, false);

        text_id_alerta_actualizar = view.findViewById(R.id.text_id_alerta_actualizar);
        text_nueva_ubicacion = view.findViewById(R.id.text_nueva_ubicacion);
        text_nueva_fecha_hora = view.findViewById(R.id.text_nueva_fecha_hora);
        text_nueva_descripcion = view.findViewById(R.id.text_nueva_descripcion);
        text_nueva_gravedad = view.findViewById(R.id.text_nueva_gravedad);
        text_nuevo_estado = view.findViewById(R.id.text_nuevo_estado);
        btn_actualizar_alerta = view.findViewById(R.id.btn_actualizar_alerta);

        btn_actualizar_alerta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarAlertaPorID();
            }
        });

        return view;
    }

    private void actualizarAlertaPorID() {
        String idAlertaActualizar = text_id_alerta_actualizar.getText().toString();
        String nuevaUbicacion = text_nueva_ubicacion.getText().toString();
        String nuevaFechaHora = text_nueva_fecha_hora.getText().toString();
        String nuevaDescripcion = text_nueva_descripcion.getText().toString();
        String nuevaGravedad = text_nueva_gravedad.getText().toString();
        String nuevoEstado = text_nuevo_estado.getText().toString();

        if (idAlertaActualizar.isEmpty() || nuevaUbicacion.isEmpty() || nuevaFechaHora.isEmpty() || nuevaDescripcion.isEmpty() || nuevaGravedad.isEmpty() || nuevoEstado.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:5000/actualizarAlertaPorID/" + idAlertaActualizar + "/" + nuevaUbicacion + "/" + nuevaFechaHora + "/" + nuevaDescripcion + "/" + nuevaGravedad + "/" + nuevoEstado;

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "Alerta actualizada con éxito", Toast.LENGTH_SHORT).show();
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
