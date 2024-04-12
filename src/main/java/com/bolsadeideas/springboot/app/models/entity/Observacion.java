package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "observaciones")
public class Observacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idObservacion;

    private Date createdAt;

    @NotEmpty
    private String comentario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public Observacion() {

    }

    @PrePersist
    public void PrePersist(){

        this.createdAt = new Date();
    }

    public Long getIdObservacion() {
        return idObservacion;
    }

    public void setIdObservacion(Long idObservacion) {
        this.idObservacion = idObservacion;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
