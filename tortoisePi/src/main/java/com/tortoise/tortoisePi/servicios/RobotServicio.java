package com.tortoise.tortoisePi.servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import com.jcraft.jsch.JSchException;
import com.tortoise.tortoisePi.conexiones.Conexion;

public class RobotServicio {
	
	public com.tortoise.tortoisePi.conexiones.Conexion conexion = new Conexion();
	public UtilidadesServicio utils = new UtilidadesServicio();
	PropiedadesServicio propierties = new PropiedadesServicio();
	
	public ArrayList<String> listaProgramas() throws IllegalAccessException, JSchException, IOException, NumberFormatException, TimeoutException {
       
		conexion.connect();
		String result = conexion.executeCommand("ls");
        conexion.disconnect();
        
        return utils.quitarEspaciosLista(result, "#");
	}
	
	public void lanzarPrograma (String nombrePrograma) throws IllegalAccessException, JSchException, IOException, NumberFormatException, TimeoutException {
		
		String instruccion = propierties.getPropiedades().getProperty("robot.ruta")  + nombrePrograma +"'";
		conexion.connect();
		conexion.executeCommand(instruccion);
        conexion.disconnect();
	}
	
}
