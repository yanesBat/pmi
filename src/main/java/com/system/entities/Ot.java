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
@Table(name = "ot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ot.findAll", query = "SELECT o FROM Ot o"),
    @NamedQuery(name = "Ot.findByIdOt", query = "SELECT o FROM Ot o WHERE o.idOt = :idOt")})
public class Ot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ot")
    private Integer idOt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOt")
    private List<OtTarea> otTareaList;
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
    @OneToMany(mappedBy = "idOt")
    private List<Condicion> condicionList;

    public Ot() {
    }

    public Ot(Integer idOt) {
        this.idOt = idOt;
    }

    public Integer getIdOt() {
        return idOt;
    }

    public void setIdOt(Integer idOt) {
        this.idOt = idOt;
    }

    @XmlTransient
    public List<OtTarea> getOtTareaList() {
        return otTareaList;
    }

    public void setOtTareaList(List<OtTarea> otTareaList) {
        this.otTareaList = otTareaList;
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
    public List<Condicion> getCondicionList() {
        return condicionList;
    }

    public void setCondicionList(List<Condicion> condicionList) {
        this.condicionList = condicionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOt != null ? idOt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ot)) {
            return false;
        }
        Ot other = (Ot) object;
        if ((this.idOt == null && other.idOt != null) || (this.idOt != null && !this.idOt.equals(other.idOt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Ot[ idOt=" + idOt + " ]";
    }
    
}
