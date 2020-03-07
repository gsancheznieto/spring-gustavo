package com.spring.bean;

import java.util.List;

public class PersonaTarea{
	
	String nombre;
	double sueldoDiario;
	int edad;
	String sexo;
	List<PersonaTarea> subordinados;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getSueldoDiario() {
		return sueldoDiario;
	}
	public void setSueldoDiario(double sueldoDiario) {
		this.sueldoDiario = sueldoDiario;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public List<PersonaTarea> getSubordinados() {
		return subordinados;
	}
	public void setSubordinados(List<PersonaTarea> subordinados) {
		this.subordinados = subordinados;
	}
}
