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
@Table(name = "elemento_infraestructura_modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementoInfraestructuraModelo.findAll", query = "SELECT e FROM ElementoInfraestructuraModelo e"),
    @NamedQuery(name = "ElementoInfraestructuraModelo.findByIdInfraModelElem", query = "SELECT e FROM ElementoInfraestructuraModelo e WHERE e.idInfraModelElem = :idInfraModelElem")})
public class ElementoInfraestructuraModelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_infra_model_elem")
    private Integer idInfraModelElem;
    @JoinColumn(name = "id_elem", referencedColumnName = "id_elem")
    @ManyToOne(optional = false)
    private Elemento idElem;
    @JoinColumn(name = "id_infra_model", referencedColumnName = "id_infra_model")
    @ManyToOne(optional = false)
    private InfraestructuraModelo idInfraModel;

    public ElementoInfraestructuraModelo() {
    }

    public ElementoInfraestructuraModelo(Integer idInfraModelElem) {
        this.idInfraModelElem = idInfraModelElem;
    }

    public Integer getIdInfraModelElem() {
        return idInfraModelElem;
    }

    public void setIdInfraModelElem(Integer idInfraModelElem) {
        this.idInfraModelElem = idInfraModelElem;
    }

    public Elemento getIdElem() {
        return idElem;
    }

    public void setIdElem(Elemento idElem) {
        this.idElem = idElem;
    }

    public InfraestructuraModelo getIdInfraModel() {
        return idInfraModel;
    }

    public void setIdInfraModel(InfraestructuraModelo idInfraModel) {
        this.idInfraModel = idInfraModel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInfraModelElem != null ? idInfraModelElem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementoInfraestructuraModelo)) {
            return false;
        }
        ElementoInfraestructuraModelo other = (ElementoInfraestructuraModelo) object;
        if ((this.idInfraModelElem == null && other.idInfraModelElem != null) || (this.idInfraModelElem != null && !this.idInfraModelElem.equals(other.idInfraModelElem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.ElementoInfraestructuraModelo[ idInfraModelElem=" + idInfraModelElem + " ]";
    }
    
}
