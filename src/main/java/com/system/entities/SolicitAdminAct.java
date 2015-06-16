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
@Table(name = "solicit_admin_act")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitAdminAct.findAll", query = "SELECT s FROM SolicitAdminAct s"),
    @NamedQuery(name = "SolicitAdminAct.findByIdSolicitAdminAct", query = "SELECT s FROM SolicitAdminAct s WHERE s.idSolicitAdminAct = :idSolicitAdminAct"),
    @NamedQuery(name = "SolicitAdminAct.findByAsunto", query = "SELECT s FROM SolicitAdminAct s WHERE s.asunto = :asunto"),
    @NamedQuery(name = "SolicitAdminAct.findByJustificacion", query = "SELECT s FROM SolicitAdminAct s WHERE s.justificacion = :justificacion"),
    @NamedQuery(name = "SolicitAdminAct.findByAtendida", query = "SELECT s FROM SolicitAdminAct s WHERE s.atendida = :atendida")})
public class SolicitAdminAct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicit_admin_act")
    private Integer idSolicitAdminAct;
    @Column(name = "asunto")
    private String asunto;
    @Column(name = "justificacion")
    private String justificacion;
    @Column(name = "atendida")
    private Boolean atendida;
    @JoinColumn(name = "usuario", referencedColumnName = "id_user")
    @ManyToOne
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitAdminAct")
    private List<ActivoClonSolicitAdminAct> activoClonSolicitAdminActList;

    public SolicitAdminAct() {
    }

    public SolicitAdminAct(Integer idSolicitAdminAct) {
        this.idSolicitAdminAct = idSolicitAdminAct;
    }

    public Integer getIdSolicitAdminAct() {
        return idSolicitAdminAct;
    }

    public void setIdSolicitAdminAct(Integer idSolicitAdminAct) {
        this.idSolicitAdminAct = idSolicitAdminAct;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Boolean getAtendida() {
        return atendida;
    }

    public void setAtendida(Boolean atendida) {
        this.atendida = atendida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<ActivoClonSolicitAdminAct> getActivoClonSolicitAdminActList() {
        return activoClonSolicitAdminActList;
    }

    public void setActivoClonSolicitAdminActList(List<ActivoClonSolicitAdminAct> activoClonSolicitAdminActList) {
        this.activoClonSolicitAdminActList = activoClonSolicitAdminActList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitAdminAct != null ? idSolicitAdminAct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SolicitAdminAct)) {
            return false;
        }
        SolicitAdminAct other = (SolicitAdminAct) object;
        if ((this.idSolicitAdminAct == null && other.idSolicitAdminAct != null) || (this.idSolicitAdminAct != null && !this.idSolicitAdminAct.equals(other.idSolicitAdminAct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.SolicitAdminAct[ idSolicitAdminAct=" + idSolicitAdminAct + " ]";
    }
    
}
