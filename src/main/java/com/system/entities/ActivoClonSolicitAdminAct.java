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
@Table(name = "activo_clon_solicit_admin_act")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoClonSolicitAdminAct.findAll", query = "SELECT a FROM ActivoClonSolicitAdminAct a"),
    @NamedQuery(name = "ActivoClonSolicitAdminAct.findByIdActClonSolAdmin", query = "SELECT a FROM ActivoClonSolicitAdminAct a WHERE a.idActClonSolAdmin = :idActClonSolAdmin")})
public class ActivoClonSolicitAdminAct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_act_clon_sol_admin")
    private Integer idActClonSolAdmin;
    @JoinColumn(name = "id_activ_clon", referencedColumnName = "id_activ_clon")
    @ManyToOne(optional = false)
    private ActivoClon idActivClon;
    @JoinColumn(name = "id_solicit_admin_act", referencedColumnName = "id_solicit_admin_act")
    @ManyToOne(optional = false)
    private SolicitAdminAct idSolicitAdminAct;

    public ActivoClonSolicitAdminAct() {
    }

    public ActivoClonSolicitAdminAct(Integer idActClonSolAdmin) {
        this.idActClonSolAdmin = idActClonSolAdmin;
    }

    public Integer getIdActClonSolAdmin() {
        return idActClonSolAdmin;
    }

    public void setIdActClonSolAdmin(Integer idActClonSolAdmin) {
        this.idActClonSolAdmin = idActClonSolAdmin;
    }

    public ActivoClon getIdActivClon() {
        return idActivClon;
    }

    public void setIdActivClon(ActivoClon idActivClon) {
        this.idActivClon = idActivClon;
    }

    public SolicitAdminAct getIdSolicitAdminAct() {
        return idSolicitAdminAct;
    }

    public void setIdSolicitAdminAct(SolicitAdminAct idSolicitAdminAct) {
        this.idSolicitAdminAct = idSolicitAdminAct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActClonSolAdmin != null ? idActClonSolAdmin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoClonSolicitAdminAct)) {
            return false;
        }
        ActivoClonSolicitAdminAct other = (ActivoClonSolicitAdminAct) object;
        if ((this.idActClonSolAdmin == null && other.idActClonSolAdmin != null) || (this.idActClonSolAdmin != null && !this.idActClonSolAdmin.equals(other.idActClonSolAdmin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.ActivoClonSolicitAdminAct[ idActClonSolAdmin=" + idActClonSolAdmin + " ]";
    }
    
}
