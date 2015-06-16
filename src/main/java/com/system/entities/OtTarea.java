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
@Table(name = "ot_tarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OtTarea.findAll", query = "SELECT o FROM OtTarea o"),
    @NamedQuery(name = "OtTarea.findByIdOtTarea", query = "SELECT o FROM OtTarea o WHERE o.idOtTarea = :idOtTarea")})
public class OtTarea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ot_tarea")
    private Integer idOtTarea;
    @JoinColumn(name = "id_ot", referencedColumnName = "id_ot")
    @ManyToOne(optional = false)
    private Ot idOt;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea")
    @ManyToOne(optional = false)
    private Tarea idTarea;

    public OtTarea() {
    }

    public OtTarea(Integer idOtTarea) {
        this.idOtTarea = idOtTarea;
    }

    public Integer getIdOtTarea() {
        return idOtTarea;
    }

    public void setIdOtTarea(Integer idOtTarea) {
        this.idOtTarea = idOtTarea;
    }

    public Ot getIdOt() {
        return idOt;
    }

    public void setIdOt(Ot idOt) {
        this.idOt = idOt;
    }

    public Tarea getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Tarea idTarea) {
        this.idTarea = idTarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOtTarea != null ? idOtTarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OtTarea)) {
            return false;
        }
        OtTarea other = (OtTarea) object;
        if ((this.idOtTarea == null && other.idOtTarea != null) || (this.idOtTarea != null && !this.idOtTarea.equals(other.idOtTarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.OtTarea[ idOtTarea=" + idOtTarea + " ]";
    }
    
}
