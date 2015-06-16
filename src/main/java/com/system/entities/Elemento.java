/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "elemento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elemento.findAll", query = "SELECT e FROM Elemento e"),
    @NamedQuery(name = "Elemento.findByIdElem", query = "SELECT e FROM Elemento e WHERE e.idElem = :idElem"),
    @NamedQuery(name = "Elemento.findByNombre", query = "SELECT e FROM Elemento e WHERE e.nombre = :nombre")})
public class Elemento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_elem")
    private Integer idElem;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElem")
    private List<InfraestructuraClonElemento> infraestructuraClonElementoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElem")
    private List<ElementoInfraestructuraModelo> elementoInfraestructuraModeloList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElem")
    private List<ActivoModeloElemento> activoModeloElementoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "padre")
    private List<Elemento> elementoList;
    @JoinColumn(name = "padre", referencedColumnName = "id_elem")
    @ManyToOne(optional = false)
    private Elemento padre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idElem")
    private List<ActivoClonElemento> activoClonElementoList;

    public Elemento() {
    }

    public Elemento(Integer idElem) {
        this.idElem = idElem;
    }

    public Elemento(Integer idElem, String nombre) {
        this.idElem = idElem;
        this.nombre = nombre;
    }

    public Integer getIdElem() {
        return idElem;
    }

    public void setIdElem(Integer idElem) {
        this.idElem = idElem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<InfraestructuraClonElemento> getInfraestructuraClonElementoList() {
        return infraestructuraClonElementoList;
    }

    public void setInfraestructuraClonElementoList(List<InfraestructuraClonElemento> infraestructuraClonElementoList) {
        this.infraestructuraClonElementoList = infraestructuraClonElementoList;
    }

    @XmlTransient
    public List<ElementoInfraestructuraModelo> getElementoInfraestructuraModeloList() {
        return elementoInfraestructuraModeloList;
    }

    public void setElementoInfraestructuraModeloList(List<ElementoInfraestructuraModelo> elementoInfraestructuraModeloList) {
        this.elementoInfraestructuraModeloList = elementoInfraestructuraModeloList;
    }

    @XmlTransient
    public List<ActivoModeloElemento> getActivoModeloElementoList() {
        return activoModeloElementoList;
    }

    public void setActivoModeloElementoList(List<ActivoModeloElemento> activoModeloElementoList) {
        this.activoModeloElementoList = activoModeloElementoList;
    }

    @XmlTransient
    public List<Elemento> getElementoList() {
        return elementoList;
    }

    public void setElementoList(List<Elemento> elementoList) {
        this.elementoList = elementoList;
    }

    public Elemento getPadre() {
        return padre;
    }

    public void setPadre(Elemento padre) {
        this.padre = padre;
    }

    @XmlTransient
    public List<ActivoClonElemento> getActivoClonElementoList() {
        return activoClonElementoList;
    }

    public void setActivoClonElementoList(List<ActivoClonElemento> activoClonElementoList) {
        this.activoClonElementoList = activoClonElementoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idElem != null ? idElem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elemento)) {
            return false;
        }
        Elemento other = (Elemento) object;
        if ((this.idElem == null && other.idElem != null) || (this.idElem != null && !this.idElem.equals(other.idElem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Elemento[ idElem=" + idElem + " ]";
    }
    
}
