package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;

	@NotEmpty
	private String celular;


	private String otro_telefono;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Pattern(regexp = "[0-9]{2}[-][0-9]{2}[\\d]{3}[0-9]{3}[-][0-9]{1}") // ej 20-35552198-3
	private String cuil;


	private String domicilio;


	private String localidad;

	@NotEmpty
	private String provincia;

	@NotEmpty
	private String dependencia;

	@NotEmpty
	private String situacion_laboral;

	@NotEmpty
	private String banco_cobro;


	/*
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")

	 */
	private Date createAt;


	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getOtro_telefono() {
		return otro_telefono;
	}

	public void setOtro_telefono(String otro_telefono) {
		this.otro_telefono = otro_telefono;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getSituacion_laboral() {
		return situacion_laboral;
	}

	public void setSituacion_laboral(String situacion_laboral) {
		this.situacion_laboral = situacion_laboral;
	}

	public String getBanco_cobro() {
		return banco_cobro;
	}

	public void setBanco_cobro(String banco_cobro) {
		this.banco_cobro = banco_cobro;
	}

	private static final long serialVersionUID = 1L;

}
