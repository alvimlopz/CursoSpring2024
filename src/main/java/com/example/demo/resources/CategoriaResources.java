package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria ca1 = new Categoria(1, "Informatica");
		Categoria ca2 = new Categoria(2, "Escritorio");
		
		List<Categoria> list = new ArrayList<>();
		list.add(ca1);
		list.add(ca2);
		
		return list;
	}
}
