from flask import Flask, jsonify, request
import mysql.connector


app = Flask(__name__)

mydb= mysql.connector.connect(user='root', password='',
                            host='localhost', database='ecotracker')

mycursor = mydb.cursor()

@app.route('/crearUsuarios/<id_usuario>/<nombre>/<correo_electronico>/<contrasena>/<id_ubicacion>')

def crearUsuarios(id_usuario, nombre, correo_electronico, contrasena, id_ubicacion):
   
    mycursor.callproc("crearUsuarios",(f"{id_usuario}",f"{nombre}",f"{correo_electronico}",f"{contrasena}",f"{id_ubicacion}"))
   
    mydb.commit()
   
    return "Usuario creado"
   

@app.route('/buscarUsuarioPorID/<id_usuario_param>')

def buscarUsuarioPorID(id_usuario_param):
   
    mycursor.callproc("buscarUsuarioPorID", (f"{id_usuario_param}",))
   
    for resultado in mycursor.stored_results():
       
        resultado = resultado.fetchall()
        cliente = {"id_usuario":resultado[0][0], "nombre":resultado[0][1], "correo_electronico":resultado[0][2], "contrasena":resultado[0][3], "id_ubicacion":resultado[0][4]}
   
    return jsonify(cliente)


@app.route('/actualizarUsuarioPorID/<id_usuario_param>/<nuevo_nombre>/<nuevo_correo_electronico>/<nueva_contrasena>/<nueva_ubicacion>')

def actualizarUsuarioPorID(id_usuario_param, nuevo_nombre, nuevo_correo_electronico, nueva_contrasena, nueva_ubicacion):
   
    mycursor.callproc("actualizarUsuarioPorID",(id_usuario_param, nuevo_nombre, nuevo_correo_electronico, nueva_contrasena, nueva_ubicacion))
   
    mydb.commit()
   
    return "Usuario actualizado"


@app.route('/eliminarUsuarioPorID/<id_usuario_param>')

def eliminarUsuarioPorID(id_usuario_param):
   
    mycursor.callproc("eliminarUsuarioPorID",(f"{id_usuario_param}",))
   
    mydb.commit()
   
    return "Usuario eliminado"

@app.route('/crearUbicacion/<id_ubicacion>/<latitud>/<longitud>/<direccion>/<ciudad>/<pais>')

def crearUbicacion(id_ubicacion, latitud, longitud, direccion, ciudad, pais):
   
    mycursor.callproc("crearUbicacion",(f"{id_ubicacion}",f"{latitud}",f"{longitud}",f"{direccion}",f"{ciudad}", f"{pais}"))
   
    mydb.commit()
   
    return "Ubicación creada"
   

@app.route('/buscarUbicacionPorID/<id_ubicacion_param>')

def buscarUbicacionPorID(id_ubicacion_param):
   
    mycursor.callproc("buscarUbicacionPorID", (f"{id_ubicacion_param}",))
   
    for resultado in mycursor.stored_results():
       
        resultado = resultado.fetchall()
        ubi = {"id_ubicacion":resultado[0][0], "latitud":resultado[0][1], "longitud":resultado[0][2], "direccion":resultado[0][3], "ciudad":resultado[0][4],  "pais":resultado[0][5]}
   
    return jsonify(ubi)


@app.route('/actualizarUbicacionPorID/<id_ubicacion_param>/<nueva_latitud>/<nueva_longitud>/<nueva_direccion>/<nueva_ciudad>/<nuevo_pais>')

def actualizarUbicacionPorID(id_ubicacion_param, nueva_latitud, nueva_longitud, nueva_direccion, nueva_ciudad, nuevo_pais):
   
    mycursor.callproc("actualizarUbicacionPorID",(id_ubicacion_param, nueva_latitud, nueva_longitud, nueva_direccion, nueva_ciudad, nuevo_pais))
   
    mydb.commit()
   
    return "Ubicacion actualizada"


@app.route('/eliminarUbicacionPorID/<id_ubicacion_param>')

def eliminarUbicacionPorID(id_ubicacion_param):
   
    mycursor.callproc("eliminarUbicacionPorID",(f"{id_ubicacion_param}",))
   
    mydb.commit()
   
    return "Ubicación eliminada"

@app.route('/crearReporteEventoAmbiental/<id_reporte>/<id_usuario>/<ubicacion_evento>/<tipo_evento>/<descripcion>/<fecha_hora>/<estado>')
def crearReporteEventoAmbiental(id_reporte, id_usuario, ubicacion_evento, tipo_evento, descripcion, fecha_hora, estado):
    try:
        mycursor.callproc("crearReporte", (id_reporte, id_usuario, ubicacion_evento, tipo_evento, descripcion, fecha_hora, estado))
        mydb.commit()
        return "Reporte de evento ambiental creado", 201
    except Exception as e:
        return str(e), 500

# Ruta para buscar un reporte de evento ambiental por ID
@app.route('/buscarReporteEventoAmbientalPorID/<id_reporte>')
def buscarReporteEventoAmbientalPorID(id_reporte):
    try:
        mycursor.callproc("buscarReportePorID", (id_reporte,))
        for resultado in mycursor.stored_results():
            resultado = resultado.fetchall()
            reporte = {
                "id_reporte": resultado[0][0],
                "id_usuario": resultado[0][1],
                "ubicacion_evento": resultado[0][2],
                "tipo_evento": resultado[0][3],
                "descripcion": resultado[0][4],
                "fecha_hora": resultado[0][5],
                "estado": resultado[0][6]
            }
        return jsonify(reporte), 200
    except Exception as e:
        return str(e), 500

# Ruta para actualizar un reporte de evento ambiental por ID
@app.route('/actualizarReporteEventoAmbientalPorID/<id_reporte>/<id_usuario>/<ubicacion_evento>/<tipo_evento>/<descripcion>/<fecha_hora>/<estado>')
def actualizarReporteEventoAmbientalPorID(id_reporte, id_usuario, ubicacion_evento, tipo_evento, descripcion, fecha_hora, estado):
    try:
        mycursor.callproc("actualizarReportePorID", (id_reporte, id_usuario, ubicacion_evento, tipo_evento, descripcion, fecha_hora, estado))
        mydb.commit()
        return "Reporte de evento ambiental actualizado", 200
    except Exception as e:
        return str(e), 500

# Ruta para eliminar un reporte de evento ambiental por ID
@app.route('/eliminarReporteEventoAmbientalPorID/<id_reporte>')
def eliminarReporteEventoAmbientalPorID(id_reporte):
    try:
        mycursor.callproc("eliminarReportePorID", (id_reporte,))
        mydb.commit()
        return "Reporte de evento ambiental eliminado", 200
    except Exception as e:
        return str(e), 500
    
@app.route('/crearNotificacion/<id_notificacion>/<id_usuario_destinatario>/<contenido>/<tipo>/<fecha_hora>/<estado>')
def crearNotificacion(id_notificacion, id_usuario_destinatario, contenido, tipo, fecha_hora, estado):
    try:
        mycursor.callproc("crearNotificacion", (id_notificacion, id_usuario_destinatario, contenido, tipo, fecha_hora, estado))
        mydb.commit()
        return "Notificación creada", 201
    except Exception as e:
        return str(e), 500

# Ruta para buscar una notificación por ID
@app.route('/buscarNotificacionPorID/<id_notificacion>')
def buscarNotificacionPorID(id_notificacion):
    try:
        mycursor.callproc("buscarNotificacionPorID", (id_notificacion,))
        for resultado in mycursor.stored_results():
            resultado = resultado.fetchall()
            notificacion = {
                "id_notificacion": resultado[0][0],
                "id_usuario_destinatario": resultado[0][1],
                "contenido": resultado[0][2],
                "tipo": resultado[0][3],
                "fecha_hora": resultado[0][4],
                "estado": resultado[0][5]
            }
        return jsonify(notificacion), 200
    except Exception as e:
        return str(e), 500

# Ruta para actualizar una notificación por ID
@app.route('/actualizarNotificacionPorID/<id_notificacion>/<id_usuario_destinatario>/<contenido>/<tipo>/<fecha_hora>/<estado>')
def actualizarNotificacionPorID(id_notificacion, id_usuario_destinatario, contenido, tipo, fecha_hora, estado):
    try:
        mycursor.callproc("actualizarNotificacionPorID", (id_notificacion, id_usuario_destinatario, contenido, tipo, fecha_hora, estado))
        mydb.commit()
        return "Notificación actualizada", 200
    except Exception as e:
        return str(e), 500

# Ruta para eliminar una notificación por ID
@app.route('/eliminarNotificacionPorID/<id_notificacion>')
def eliminarNotificacionPorID(id_notificacion):
    try:
        mycursor.callproc("eliminarNotificacionPorID", (id_notificacion,))
        mydb.commit()
        return "Notificación eliminada", 200
    except Exception as e:
        return str(e), 500
    
@app.route('/crearComentario/<id_comentario>/<id_usuario>/<entidad>/<id_entidad>/<contenido>/<fecha_hora>')
def crearComentario(id_comentario, id_usuario, entidad, id_entidad, contenido, fecha_hora):
    mycursor.callproc("crearComentario", (id_comentario, id_usuario, entidad, id_entidad, contenido, fecha_hora))
    mydb.commit()
    return "Comentario creado"

@app.route('/buscarComentarioPorID/<id_comentario_param>')
def buscarComentarioPorID(id_comentario_param):
    mycursor.callproc("buscarComentarioPorID", (id_comentario_param,))
    for resultado in mycursor.stored_results():
        resultado = resultado.fetchall()
        comentario = {"id_comentario": resultado[0][0], "id_usuario": resultado[0][1], "entidad": resultado[0][2], "id_entidad": resultado[0][3], "contenido": resultado[0][4], "fecha_hora": resultado[0][5]}
    return jsonify(comentario)

@app.route('/actualizarComentarioPorID/<id_comentario_param>/<nuevo_id_usuario>/<nueva_entidad>/<nuevo_id_entidad>/<nuevo_contenido>/<nueva_fecha_hora>')
def actualizarComentarioPorID(id_comentario_param, nuevo_id_usuario, nueva_entidad, nuevo_id_entidad, nuevo_contenido, nueva_fecha_hora):
    mycursor.callproc("actualizarComentarioPorID", (id_comentario_param, nuevo_id_usuario, nueva_entidad, nuevo_id_entidad, nuevo_contenido, nueva_fecha_hora))
    mydb.commit()
    return "Comentario actualizado"

@app.route('/eliminarComentarioPorID/<id_comentario_param>')
def eliminarComentarioPorID(id_comentario_param):
    mycursor.callproc("eliminarComentarioPorID", (id_comentario_param,))
    mydb.commit()
    return "Comentario eliminado"

@app.route('/crearCampana/<ID_Campana>/<Nombre>/<Descripcion>/<Tipo_de_Anuncio>/<Estado>')

def crearCampana(ID_Campana, Nombre, Descripcion, Tipo_de_Anuncio, Estado):
   
    mycursor.callproc("crearCampana",(f"{ID_Campana}",f"{Nombre}",f"{Descripcion}",f"{Tipo_de_Anuncio}",f"{Estado}"))
   
    mydb.commit()
   
    return "Campaña creada"
   

@app.route('/buscarCampanaPorID/<id_campana_param>')

def buscarCampanaPorID(id_campana_param):
   
    mycursor.callproc("buscarCampanaPorID", (f"{id_campana_param}",))
   
    for resultado in mycursor.stored_results():
       
        resultado = resultado.fetchall()
        cliente = {"ID_Campana":resultado[0][0], "Nombre":resultado[0][1], "Descripcion":resultado[0][2], "Tipo_de_Anuncio":resultado[0][3], "Estado":resultado[0][4]}
    return jsonify(cliente)


@app.route('/actualizarCampanaPorID/<id_campana_param>/<nuevo_nombre>/<nueva_descripcion>/<nuevo_tipo>/<nuevo_estado>')

def actualizarCampanaPorID(id_campana_param, nuevo_nombre, nueva_descripcion, nuevo_tipo, nuevo_estado):
   
    mycursor.callproc("actualizarCampanaPorID",(id_campana_param, nuevo_nombre, nueva_descripcion, nuevo_tipo, nuevo_estado))
   
    mydb.commit()
   
    return "Campaña actualizada"


@app.route('/eliminarCampanaPorID/<id_campana_param>')

def eliminarCampanaPorID(id_campana_param):
   
    mycursor.callproc("eliminarCampanaPorID",(f"{id_campana_param}",))
   
    mydb.commit()
   
    return "Campaña eliminada"

@app.route('/crearAlerta/<ID_alerta>/<ubicacion>/<fecha_hora>/<descripcion>/<nivel_gravedad>/<estado>')

def crearAlerta(ID_alerta, ubicacion, fecha_hora, descripcion, nivel_gravedad, estado):
   
    mycursor.callproc("crearAlerta",(f"{ID_alerta}",f"{ubicacion}",f"{fecha_hora}",f"{descripcion}",f"{nivel_gravedad}",f"{estado}"))
   
    mydb.commit()
   
    return "Alerta creada"
   

@app.route('/buscarAlertaPorID/<id_alerta_param>')

def buscarAlertaPorID(id_alerta_param):
   
    mycursor.callproc("buscarAlertaPorID", (f"{id_alerta_param}",))
   
    for resultado in mycursor.stored_results():
       
        resultado = resultado.fetchall()
        alerta = {"ID_alerta":resultado[0][0], "ubicacion":resultado[0][1], "fecha_hora":resultado[0][2], "descripcion":resultado[0][3], "nivel_gravedad":resultado[0][4], "estado":resultado[0][5]}
   
    return jsonify(alerta)


@app.route('/actualizarAlertaPorID/<id_alerta_param>/<nueva_ubicacion>/<nueva_fecha_hora>/<nueva_descripcion>/<nueva_gravedad>/<nuevo_estado>')

def actualizarAlertaPorID(id_alerta_param, nueva_ubicacion, nueva_fecha_hora, nueva_descripcion, nueva_gravedad, nuevo_estado):
   
    mycursor.callproc("actualizarAlertaPorID",(id_alerta_param, nueva_ubicacion, nueva_fecha_hora, nueva_descripcion, nueva_gravedad, nuevo_estado))
   
    mydb.commit()
   
    return "Alerta actualizada"


@app.route('/eliminarAlertaPorID/<id_alerta_param>')

def eliminarAlertaPorID(id_alerta_param):
   
    mycursor.callproc("eliminarAlertaPorID",(f"{id_alerta_param}",))
   
    mydb.commit()
   
    return "Alerta eliminada"

if __name__ == '__main__':
    print(app.url_map)
    app.run(debug=True)