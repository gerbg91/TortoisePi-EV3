package com.tortoise.tortoisePi.conexiones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.tortoise.tortoisePi.servicios.PropiedadesServicio;
import com.tortoise.tortoisePi.servicios.UtilidadesServicio;
 
/**
 * Clase encargada de establecer conexion y ejecutar comandos SSH.
 */
public class Conexion {

	PropiedadesServicio propierties = new PropiedadesServicio();
	UtilidadesServicio  utilidades	= new UtilidadesServicio();
	
    /**
     * Caracter de separion.
     */
    private static final String ENTER_KEY = "#";
    /**
     * sesion SSH establecida.
     */
    public Session session;
 
    public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	/**
     * Establece una conexion SSH.
     *
     * @param username Nombre de usuario.
     * @param password contrasenya.
     * @param host     Host a conectar.
     * @param port     Puerto del Host.
     *
     * @throws JSchException          Cualquier error al establecer
     *                                conexion SSH.
     * @throws IllegalAccessException Indica que ya existe una conexion
     *                                SSH establecida.
     * @throws IOException 
     * @throws NumberFormatException 
     */
    public void connect()
        throws JSchException, IllegalAccessException, NumberFormatException, IOException {
        
    	if (this.session == null || !this.session.isConnected()) {
		    JSch jsch = new JSch();			 
			 
		    this.session = jsch.getSession(
		    		propierties.getPropiedades().getProperty("robot.usuario"), 
		    		propierties.getPropiedades().getProperty("robot.host"),
		    		Integer.parseInt(propierties.getPropiedades().getProperty("robot.puerto"))
		    		);
		    this.session.setPassword(propierties.getPropiedades().getProperty("robot.password"));
 
		    // Parametro para no validar key de conexion.
		    this.session.setConfig("StrictHostKeyChecking", "no");
		    
		    try {
		    	this.session.connect();
		    }catch (JSchException e ){
		    	throw new IllegalAccessException("No esta accesible el dispositivo o imposible conectar con los parametros establecidos.");
		    }
		    
		} else {
		    throw new IllegalAccessException("Sesion SSH ya iniciada.");
		}	
	}
 
    /**
     * Ejecuta un comando SSH.
     *
     * @param command Comando SSH a ejecutar.
     *
     * @return
     *
     * @throws IllegalAccessException Excepcion lanzada cuando no hay
     *                                conexion establecida.
     * @throws JSchException          Excepcion lanzada por algun
     *                                error en la ejecucion del comando
     *                                SSH.
     * @throws IOException            Excepcion al leer el texto arrojado
     *                                luego de la ejecucion del comando
     *                                SSH.
     */
    public final String executeCommand(String command) throws JSchException, IOException, IllegalAccessException {
        if (this.session != null && this.session.isConnected()) {
 
            // Abrimos un canal SSH. Es como abrir una consola.
            ChannelExec channelExec = (ChannelExec) this.session.
                openChannel("exec");
 
            InputStream in = channelExec.getInputStream();
 
            // Ejecutamos el comando.
            channelExec.setCommand(command);
            channelExec.connect();
 
            // Obtenemos el texto impreso en la consola.
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            String linea;
 
            while ((linea = reader.readLine()) != null) {
                builder.append(linea);
                builder.append(ENTER_KEY);
            }
 
            // Cerramos el canal SSH.
            channelExec.disconnect();
 
            // Retornamos el texto impreso en la consola.
            return builder.toString();
        } else {
        	throw new IllegalAccessException("No existe sesion SSH iniciada.");
        }
    }
 
    /**
     * Cierra la sesi&oacute;n SSH.
     */
    public final void disconnect() {
        this.session.disconnect();
    }
    
}