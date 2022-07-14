package com.propets.apirest.main.models.entity;

import com.propets.apirest.main.models.objects.UsuarioData;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="personas")
public class Persona implements Serializable {

	@Id
	@Column(name = "persona_id",length = 36)
	private String id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="usuario_email",referencedColumnName = "usuario_email",foreignKey = @ForeignKey(name = "fk_persona_usuario_email"),unique = true)
	private Usuario usuario;
	@Column(name="persona_nombre",length=30)
	@NotEmpty
	@NotNull
	@Size(max = 30)
	private String nombre;
	@Column(name="persona_apellido",length=30)
	@NotEmpty
	@NotNull
	@Size(max = 30)
	private String apellido;
	@Column(name="persona_telefono",length=10)
	@NotEmpty
	@NotNull
	@Positive
	@Size(max = 10)
	private String telefono;

	public Persona(){}
	public Persona(UsuarioData data, Usuario user){
		this.nombre= data.getNombre();
		this.apellido= data.getApellido();
		this.telefono=data.getTelefono();
		this.usuario=user;
		this.id = UUID.randomUUID().toString();
	}
	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	public String getApellido() {return apellido;}
	public void setApellido(String apellido) {this.apellido = apellido;}
	public String getTelefono() {return telefono;}
	public void setTelefono(String numero) {this.telefono = numero;}
	public void update(UsuarioData data){
		this.nombre= data.getNombre();
		this.apellido= data.getApellido();
		this.telefono= data.getTelefono();
	}
	
	private static final long serialVersionUID = 1L;
}