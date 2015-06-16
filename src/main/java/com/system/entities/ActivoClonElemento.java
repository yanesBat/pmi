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
@Table(name = "activo_clon_elemento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoClonElemento.findAll", query = "SELECT a FROM ActivoClonElemento a"),
    @NamedQuery(name = "ActivoClonElemento.findByIdActClonElem", query = "SELECT a FROM ActivoClonElemento a WHERE a.idActClonElem = :idActClonElem")})
public class ActivoClonElemento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_act_clon_elem")
    private Integer idActClonElem;
    @JoinColumn(name = "id_activ_clon", referencedColumnName = "id_activ_clon")
    @ManyToOne(optional = false)
    private ActivoClon idActivClon;
    @JoinColumn(name = "id_elem", referencedColumnName = "id_elem")
    @ManyToOne(optional = false)
    private Elemento idElem;

    public ActivoClonElemento() {
    }

    public ActivoClonElemento(Integer idActClonElem) {
        this.idActClonElem = idActClonElem;
    }

    public Integer getIdActClonElem() {
        return idActClonElem;
    }

    public void setIdActClonElem(Integer idActClonElem) {
        this.idActClonElem = idActClonElem;
    }

    public ActivoClon getIdActivClon() {
        return idActivClon;
    }

    public void setIdActivClon(ActivoClon idActivClon) {
        this.idActivClon = idActivClon;
    }

    public Elemento getIdElem() {
        return idElem;
    }

    public void setIdElem(Elemento idElem) {
        this.idElem = idElem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActClonElem != null ? idActClonElem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoClonElemento)) {
            return false;
        }
        ActivoClonElemento other = (ActivoClonElemento) object;
        if ((this.idActClonElem == null && other.idActClonElem != null) || (this.idActClonElem != null && !this.idActClonElem.equals(other.idActClonElem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.ActivoClonElemento[ idActClonElem=" + idActClonElem + " ]";
    }
    
}
