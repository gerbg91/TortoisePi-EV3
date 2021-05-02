package es.tortoise.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


 
@RestController
public class RestApiController {
	
  
	@RequestMapping(value = "/prueba", method = RequestMethod.GET)
	public ResponseEntity<String> prueba() {
		  return new ResponseEntity<>("Los bonsais dominaran el mundo", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prueba1", method = RequestMethod.GET)
	public ResponseEntity<String> prueba1() {
		  return new ResponseEntity<>("Los bonsais dominaran el mundo 1", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prueba2", method = RequestMethod.GET)
	public ResponseEntity<String> prueba2() {
		  return new ResponseEntity<>("Los bonsais dominaran el mundo 2", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/prueba3", method = RequestMethod.GET)
	public ResponseEntity<String> prueba3() {
		  return new ResponseEntity<>("Los bonsais dominaran el mundo 3", HttpStatus.OK);
	}
}