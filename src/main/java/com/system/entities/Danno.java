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
@Table(name = "danno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Danno.findAll", query = "SELECT d FROM Danno d"),
    @NamedQuery(name = "Danno.findByIdDanno", query = "SELECT d FROM Danno d WHERE d.idDanno = :idDanno"),
    @NamedQuery(name = "Danno.findByNombre", query = "SELECT d FROM Danno d WHERE d.nombre = :nombre")})
public class Danno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_danno")
    private Integer idDanno;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDanno")
    private List<RedDanno> redDannoList;

    public Danno() {
    }

    public Danno(Integer idDanno) {
        this.idDanno = idDanno;
    }

    public Integer getIdDanno() {
        return idDanno;
    }

    public void setIdDanno(Integer idDanno) {
        this.idDanno = idDanno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idDanno != null ? idDanno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Danno)) {
            return false;
        }
        Danno other = (Danno) object;
        if ((this.idDanno == null && other.idDanno != null) || (this.idDanno != null && !this.idDanno.equals(other.idDanno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Danno[ idDanno=" + idDanno + " ]";
    }
    
}
