package com.futuro_vestibular.BackEndfuturovestibular.controller;

import java.net.URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futuro_vestibular.BackEndfuturovestibular.entities.Denuncia;
import com.futuro_vestibular.BackEndfuturovestibular.entities.User;
import com.futuro_vestibular.BackEndfuturovestibular.services.DenunciaService;
import com.futuro_vestibular.BackEndfuturovestibular.services.UserService;



@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

	@Autowired
	private DenunciaService service;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<Denuncia>> findAll() {
		List<Denuncia> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Denuncia> findById(@PathVariable Integer id) {
		Denuncia obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping 
	public ResponseEntity<Denuncia> insert(@RequestBody Denuncia obj) {
		User user = userService.findById(obj.getUser().getId());
		obj.setUser(user);
		obj.setDenunciaStatus("OPEN");
		Denuncia newObj = service.insert(obj);
		return ResponseEntity.created(URI.create("/denuncias"+ newObj.getId())).body(newObj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
