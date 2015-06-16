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
@Table(name = "infraestructura_clon_elemento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfraestructuraClonElemento.findAll", query = "SELECT i FROM InfraestructuraClonElemento i"),
    @NamedQuery(name = "InfraestructuraClonElemento.findByIdInfraClonElem", query = "SELECT i FROM InfraestructuraClonElemento i WHERE i.idInfraClonElem = :idInfraClonElem")})
public class InfraestructuraClonElemento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_infra_clon_elem")
    private Integer idInfraClonElem;
    @JoinColumn(name = "id_elem", referencedColumnName = "id_elem")
    @ManyToOne(optional = false)
    private Elemento idElem;
    @JoinColumn(name = "id_infra_clon", referencedColumnName = "id_infra_clon")
    @ManyToOne(optional = false)
    private InfraestructuraClon idInfraClon;

    public InfraestructuraClonElemento() {
    }

    public InfraestructuraClonElemento(Integer idInfraClonElem) {
        this.idInfraClonElem = idInfraClonElem;
    }

    public Integer getIdInfraClonElem() {
        return idInfraClonElem;
    }

    public void setIdInfraClonElem(Integer idInfraClonElem) {
        this.idInfraClonElem = idInfraClonElem;
    }

    public Elemento getIdElem() {
        return idElem;
    }

    public void setIdElem(Elemento idElem) {
        this.idElem = idElem;
    }

    public InfraestructuraClon getIdInfraClon() {
        return idInfraClon;
    }

    public void setIdInfraClon(InfraestructuraClon idInfraClon) {
        this.idInfraClon = idInfraClon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInfraClonElem != null ? idInfraClonElem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfraestructuraClonElemento)) {
            return false;
        }
        InfraestructuraClonElemento other = (InfraestructuraClonElemento) object;
        if ((this.idInfraClonElem == null && other.idInfraClonElem != null) || (this.idInfraClonElem != null && !this.idInfraClonElem.equals(other.idInfraClonElem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.InfraestructuraClonElemento[ idInfraClonElem=" + idInfraClonElem + " ]";
    }
    
}
