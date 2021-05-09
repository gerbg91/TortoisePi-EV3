package com.tortoise.tortoisePi.servicios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropiedadesServicio {
	
	private static InputStream inputStream = null;
	
	public Properties getPropiedades() throws IOException {
		
		Properties prop = new Properties();
		String propFileName = "config.properties";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("El fichero '" + propFileName + "' no se encuentra en la ruta");
		}
		
		return prop;
	}
}
