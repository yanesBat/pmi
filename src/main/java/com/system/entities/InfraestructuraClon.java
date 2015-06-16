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
@Table(name = "infraestructura_clon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfraestructuraClon.findAll", query = "SELECT i FROM InfraestructuraClon i"),
    @NamedQuery(name = "InfraestructuraClon.findByIdInfraClon", query = "SELECT i FROM InfraestructuraClon i WHERE i.idInfraClon = :idInfraClon"),
    @NamedQuery(name = "InfraestructuraClon.findByDimensiones", query = "SELECT i FROM InfraestructuraClon i WHERE i.dimensiones = :dimensiones"),
    @NamedQuery(name = "InfraestructuraClon.findByPerimetro", query = "SELECT i FROM InfraestructuraClon i WHERE i.perimetro = :perimetro"),
    @NamedQuery(name = "InfraestructuraClon.findByNombre", query = "SELECT i FROM InfraestructuraClon i WHERE i.nombre = :nombre")})
public class InfraestructuraClon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_infra_clon")
    private Integer idInfraClon;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dimensiones")
    private Float dimensiones;
    @Column(name = "perimetro")
    private Float perimetro;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInfraClon")
    private List<InfraestructuraClonElemento> infraestructuraClonElementoList;
    @OneToMany(mappedBy = "idInfraClon")
    private List<Red> redList;
    @OneToMany(mappedBy = "idInfraClon")
    private List<Ot> otList;
    @JoinColumn(name = "id_contacto", referencedColumnName = "id_contacto")
    @ManyToOne
    private Contacto idContacto;
    @JoinColumn(name = "id_infra_model", referencedColumnName = "id_infra_model")
    @ManyToOne
    private InfraestructuraModelo idInfraModel;
    @JoinColumn(name = "id_ubic", referencedColumnName = "id_ubic")
    @ManyToOne
    private Ubicacion idUbic;

    public InfraestructuraClon() {
    }

    public InfraestructuraClon(Integer idInfraClon) {
        this.idInfraClon = idInfraClon;
    }

    public InfraestructuraClon(Integer idInfraClon, String nombre) {
        this.idInfraClon = idInfraClon;
        this.nombre = nombre;
    }

    public Integer getIdInfraClon() {
        return idInfraClon;
    }

    public void setIdInfraClon(Integer idInfraClon) {
        this.idInfraClon = idInfraClon;
    }

    public Float getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(Float dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Float getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(Float perimetro) {
        this.perimetro = perimetro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<InfraestructuraClonElemento> getInfraestructuraClonElementoList() {
        return infraestructuraClonElementoList;
    }

    public void setInfraestructuraClonElementoList(List<InfraestructuraClonElemento> infraestructuraClonElementoList) {
        this.infraestructuraClonElementoList = infraestructuraClonElementoList;
    }

    @XmlTransient
    public List<Red> getRedList() {
        return redList;
    }

    public void setRedList(List<Red> redList) {
        this.redList = redList;
    }

    @XmlTransient
    public List<Ot> getOtList() {
        return otList;
    }

    public void setOtList(List<Ot> otList) {
        this.otList = otList;
    }

    public Contacto getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Contacto idContacto) {
        this.idContacto = idContacto;
    }

    public InfraestructuraModelo getIdInfraModel() {
        return idInfraModel;
    }

    public void setIdInfraModel(InfraestructuraModelo idInfraModel) {
        this.idInfraModel = idInfraModel;
    }

    public Ubicacion getIdUbic() {
        return idUbic;
    }

    public void setIdUbic(Ubicacion idUbic) {
        this.idUbic = idUbic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInfraClon != null ? idInfraClon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfraestructuraClon)) {
            return false;
        }
        InfraestructuraClon other = (InfraestructuraClon) object;
        if ((this.idInfraClon == null && other.idInfraClon != null) || (this.idInfraClon != null && !this.idInfraClon.equals(other.idInfraClon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.InfraestructuraClon[ idInfraClon=" + idInfraClon + " ]";
    }
    
}
