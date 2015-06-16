/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dy
 */
@Entity
@Table(name = "condicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Condicion.findAll", query = "SELECT c FROM Condicion c"),
    @NamedQuery(name = "Condicion.findByIdCondicion", query = "SELECT c FROM Condicion c WHERE c.idCondicion = :idCondicion"),
    @NamedQuery(name = "Condicion.findByNombre", query = "SELECT c FROM Condicion c WHERE c.nombre = :nombre")})
public class Condicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_condicion")
    private Integer idCondicion;
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_ot", referencedColumnName = "id_ot")
    @ManyToOne
    private Ot idOt;

    public Condicion() {
    }

    public Condicion(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public Integer getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(Integer idCondicion) {
        this.idCondicion = idCondicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ot getIdOt() {
        return idOt;
    }

    public void setIdOt(Ot idOt) {
        this.idOt = idOt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCondicion != null ? idCondicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Condicion)) {
            return false;
        }
        Condicion other = (Condicion) object;
        if ((this.idCondicion == null && other.idCondicion != null) || (this.idCondicion != null && !this.idCondicion.equals(other.idCondicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Condicion[ idCondicion=" + idCondicion + " ]";
    }
    
}
