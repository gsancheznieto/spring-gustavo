package com.spring.bean;

import java.util.List;

public class Empresa {
	
	String nombre;
	Persona ceo;
	List<Persona> empleados;
	
	public List<Persona> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Persona> empleados) {
		this.empleados = empleados;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Persona getCeo() {
		return ceo;
	}
	public void setCeo(Persona ceo) {
		this.ceo = ceo;
	}
	
}
