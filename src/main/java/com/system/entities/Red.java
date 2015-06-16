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
@Table(name = "red")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Red.findAll", query = "SELECT r FROM Red r"),
    @NamedQuery(name = "Red.findByIdRed", query = "SELECT r FROM Red r WHERE r.idRed = :idRed")})
public class Red implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_red")
    private Integer idRed;
    @JoinColumn(name = "id_activ_clon", referencedColumnName = "id_activ_clon")
    @ManyToOne
    private ActivoClon idActivClon;
    @JoinColumn(name = "id_act_model", referencedColumnName = "id_act_model")
    @ManyToOne
    private ActivoModelo idActModel;
    @JoinColumn(name = "id_infra_clon", referencedColumnName = "id_infra_clon")
    @ManyToOne
    private InfraestructuraClon idInfraClon;
    @JoinColumn(name = "id_infra_model", referencedColumnName = "id_infra_model")
    @ManyToOne
    private InfraestructuraModelo idInfraModel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRed")
    private List<RedDanno> redDannoList;

    public Red() {
    }

    public Red(Integer idRed) {
        this.idRed = idRed;
    }

    public Integer getIdRed() {
        return idRed;
    }

    public void setIdRed(Integer idRed) {
        this.idRed = idRed;
    }

    public ActivoClon getIdActivClon() {
        return idActivClon;
    }

    public void setIdActivClon(ActivoClon idActivClon) {
        this.idActivClon = idActivClon;
    }

    public ActivoModelo getIdActModel() {
        return idActModel;
    }

    public void setIdActModel(ActivoModelo idActModel) {
        this.idActModel = idActModel;
    }

    public InfraestructuraClon getIdInfraClon() {
        return idInfraClon;
    }

    public void setIdInfraClon(InfraestructuraClon idInfraClon) {
        this.idInfraClon = idInfraClon;
    }

    public InfraestructuraModelo getIdInfraModel() {
        return idInfraModel;
    }

    public void setIdInfraModel(InfraestructuraModelo idInfraModel) {
        this.idInfraModel = idInfraModel;
    }

    @XmlTransient
    public List<RedDanno> getRedDannoList() {
        return redDannoList;
    }

    public void setRedDannoList(List<RedDanno> redDannoList) {
        this.redDannoList = redDannoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRed != null ? idRed.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Red)) {
            return false;
        }
        Red other = (Red) object;
        if ((this.idRed == null && other.idRed != null) || (this.idRed != null && !this.idRed.equals(other.idRed))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Red[ idRed=" + idRed + " ]";
    }
    
}
