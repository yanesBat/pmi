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
@Table(name = "activo_clon_movimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoClonMovimiento.findAll", query = "SELECT a FROM ActivoClonMovimiento a"),
    @NamedQuery(name = "ActivoClonMovimiento.findByIdActivoClonMovimiento", query = "SELECT a FROM ActivoClonMovimiento a WHERE a.idActivoClonMovimiento = :idActivoClonMovimiento")})
public class ActivoClonMovimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activo_clon_movimiento")
    private Integer idActivoClonMovimiento;
    @JoinColumn(name = "activo_clon", referencedColumnName = "id_activ_clon")
    @ManyToOne(optional = false)
    private ActivoClon activoClon;
    @JoinColumn(name = "movimiento", referencedColumnName = "id_movimiento")
    @ManyToOne(optional = false)
    private Movimiento movimiento;

    public ActivoClonMovimiento() {
    }

    public ActivoClonMovimiento(Integer idActivoClonMovimiento) {
        this.idActivoClonMovimiento = idActivoClonMovimiento;
    }

    public Integer getIdActivoClonMovimiento() {
        return idActivoClonMovimiento;
    }

    public void setIdActivoClonMovimiento(Integer idActivoClonMovimiento) {
        this.idActivoClonMovimiento = idActivoClonMovimiento;
    }

    public ActivoClon getActivoClon() {
        return activoClon;
    }

    public void setActivoClon(ActivoClon activoClon) {
        this.activoClon = activoClon;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivoClonMovimiento != null ? idActivoClonMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoClonMovimiento)) {
            return false;
        }
        ActivoClonMovimiento other = (ActivoClonMovimiento) object;
        if ((this.idActivoClonMovimiento == null && other.idActivoClonMovimiento != null) || (this.idActivoClonMovimiento != null && !this.idActivoClonMovimiento.equals(other.idActivoClonMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.ActivoClonMovimiento[ idActivoClonMovimiento=" + idActivoClonMovimiento + " ]";
    }
    
}
