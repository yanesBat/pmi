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
@Table(name = "activo_modelo_elemento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoModeloElemento.findAll", query = "SELECT a FROM ActivoModeloElemento a"),
    @NamedQuery(name = "ActivoModeloElemento.findByIdActivoModelElemet", query = "SELECT a FROM ActivoModeloElemento a WHERE a.idActivoModelElemet = :idActivoModelElemet")})
public class ActivoModeloElemento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activo_model_elemet")
    private Integer idActivoModelElemet;
    @JoinColumn(name = "id_act_model", referencedColumnName = "id_act_model")
    @ManyToOne(optional = false)
    private ActivoModelo idActModel;
    @JoinColumn(name = "id_elem", referencedColumnName = "id_elem")
    @ManyToOne(optional = false)
    private Elemento idElem;

    public ActivoModeloElemento() {
    }

    public ActivoModeloElemento(Integer idActivoModelElemet) {
        this.idActivoModelElemet = idActivoModelElemet;
    }

    public Integer getIdActivoModelElemet() {
        return idActivoModelElemet;
    }

    public void setIdActivoModelElemet(Integer idActivoModelElemet) {
        this.idActivoModelElemet = idActivoModelElemet;
    }

    public ActivoModelo getIdActModel() {
        return idActModel;
    }

    public void setIdActModel(ActivoModelo idActModel) {
        this.idActModel = idActModel;
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
        hash += (idActivoModelElemet != null ? idActivoModelElemet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoModeloElemento)) {
            return false;
        }
        ActivoModeloElemento other = (ActivoModeloElemento) object;
        if ((this.idActivoModelElemet == null && other.idActivoModelElemet != null) || (this.idActivoModelElemet != null && !this.idActivoModelElemet.equals(other.idActivoModelElemet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.ActivoModeloElemento[ idActivoModelElemet=" + idActivoModelElemet + " ]";
    }
    
}
