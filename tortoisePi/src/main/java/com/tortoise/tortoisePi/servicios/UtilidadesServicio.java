package com.tortoise.tortoisePi.servicios;

import java.util.ArrayList;

public class UtilidadesServicio {
	
	/**
	 * Guarda en un ArrayList cada uno de los String separados por un caracter
	 * 
	 * @param listaConEspacios
	 * @return
	 */
	public ArrayList<String> quitarEspaciosLista(String listaConCaracter, String caracter) {
		
		ArrayList<String> lista = new ArrayList<String>();
		String[] parts = listaConCaracter.split(caracter);
		
		for (String string : parts) {
			lista.add(string);
		}

		return lista;	
	}
	
}
