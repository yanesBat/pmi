/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dy
 */
@Entity
@Table(name = "sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sistema.findAll", query = "SELECT s FROM Sistema s"),
    @NamedQuery(name = "Sistema.findByIdSistema", query = "SELECT s FROM Sistema s WHERE s.idSistema = :idSistema"),
    @NamedQuery(name = "Sistema.findByNombre", query = "SELECT s FROM Sistema s WHERE s.nombre = :nombre")})
public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sistema")
    private Integer idSistema;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "sistema")
    private List<ActivoModelo> activoModeloList;
    @OneToMany(mappedBy = "sistema")
    private List<InfraestructuraModelo> infraestructuraModeloList;

    public Sistema() {
    }

    public Sistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ActivoModelo> getActivoModeloList() {
        return activoModeloList;
    }

    public void setActivoModeloList(List<ActivoModelo> activoModeloList) {
        this.activoModeloList = activoModeloList;
    }

    @XmlTransient
    public List<InfraestructuraModelo> getInfraestructuraModeloList() {
        return infraestructuraModeloList;
    }

    public void setInfraestructuraModeloList(List<InfraestructuraModelo> infraestructuraModeloList) {
        this.infraestructuraModeloList = infraestructuraModeloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSistema != null ? idSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistema)) {
            return false;
        }
        Sistema other = (Sistema) object;
        if ((this.idSistema == null && other.idSistema != null) || (this.idSistema != null && !this.idSistema.equals(other.idSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Sistema[ idSistema=" + idSistema + " ]";
    }
    
}
