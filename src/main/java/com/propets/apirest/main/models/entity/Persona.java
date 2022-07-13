package com.propets.apirest.main.models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="personas")
public class Persona implements Serializable {

	@Id
	@Column(name = "persona_id",length = 36)
	private String id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="usuario_email",referencedColumnName = "usuario_email",foreignKey = @ForeignKey(name = "fk_persona_usuario_email"))
	private Usuario usuario;
	@Column(name="persona_nombre",length=30)
	private String nombre;
	@Column(name="persona_apellido",length=30)
	private String apellido;
	@Column(name="persona_telefono",length=10)
	private String telefono;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String numero) {
		this.telefono = numero;
	}
	
	private static final long serialVersionUID = 1L;
}