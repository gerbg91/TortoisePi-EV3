package com.tortoise.tortoisePi.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.JSchException;
import com.tortoise.tortoisePi.servicios.RobotServicio;


//Indiciamos que es un controlador rest
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/robot") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/robot/

public class RobotRestController {
	
	public RobotServicio robotServicio = new RobotServicio();
	
    /*Este método se hará cuando por una petición GET (como indica la anotación) se llame a la url + el id de un usuario
    http://127.0.0.1:8080/robot/lanzarOrden*/
    @GetMapping("/lanzarOrden")
    public ResponseEntity<String> lanzarOrden() throws NumberFormatException, IllegalAccessException, JSchException, IOException, TimeoutException{
    	robotServicio.lanzarPrograma("main2.py");
    	return new ResponseEntity<>("{\"data\": [{\"title\": \"sometext\" }, {\"title\": \"sometext\" }]}", HttpStatus.OK);
    }
}