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
@Table(name = "red_danno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RedDanno.findAll", query = "SELECT r FROM RedDanno r"),
    @NamedQuery(name = "RedDanno.findByIdRedDanno", query = "SELECT r FROM RedDanno r WHERE r.idRedDanno = :idRedDanno")})
public class RedDanno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_red_danno")
    private Integer idRedDanno;
    @JoinColumn(name = "id_danno", referencedColumnName = "id_danno")
    @ManyToOne(optional = false)
    private Danno idDanno;
    @JoinColumn(name = "id_red", referencedColumnName = "id_red")
    @ManyToOne(optional = false)
    private Red idRed;

    public RedDanno() {
    }

    public RedDanno(Integer idRedDanno) {
        this.idRedDanno = idRedDanno;
    }

    public Integer getIdRedDanno() {
        return idRedDanno;
    }

    public void setIdRedDanno(Integer idRedDanno) {
        this.idRedDanno = idRedDanno;
    }

    public Danno getIdDanno() {
        return idDanno;
    }

    public void setIdDanno(Danno idDanno) {
        this.idDanno = idDanno;
    }

    public Red getIdRed() {
        return idRed;
    }

    public void setIdRed(Red idRed) {
        this.idRed = idRed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRedDanno != null ? idRedDanno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RedDanno)) {
            return false;
        }
        RedDanno other = (RedDanno) object;
        if ((this.idRedDanno == null && other.idRedDanno != null) || (this.idRedDanno != null && !this.idRedDanno.equals(other.idRedDanno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.RedDanno[ idRedDanno=" + idRedDanno + " ]";
    }
    
}
