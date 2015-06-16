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
@Table(name = "infraestructura_modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfraestructuraModelo.findAll", query = "SELECT i FROM InfraestructuraModelo i"),
    @NamedQuery(name = "InfraestructuraModelo.findByIdInfraModel", query = "SELECT i FROM InfraestructuraModelo i WHERE i.idInfraModel = :idInfraModel"),
    @NamedQuery(name = "InfraestructuraModelo.findByNombre", query = "SELECT i FROM InfraestructuraModelo i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InfraestructuraModelo.findByDimensiones", query = "SELECT i FROM InfraestructuraModelo i WHERE i.dimensiones = :dimensiones"),
    @NamedQuery(name = "InfraestructuraModelo.findByPerimetro", query = "SELECT i FROM InfraestructuraModelo i WHERE i.perimetro = :perimetro"),
    @NamedQuery(name = "InfraestructuraModelo.findByEsNivelAgrup", query = "SELECT i FROM InfraestructuraModelo i WHERE i.esNivelAgrup = :esNivelAgrup"),
    @NamedQuery(name = "InfraestructuraModelo.findByIcono", query = "SELECT i FROM InfraestructuraModelo i WHERE i.icono = :icono"),
    @NamedQuery(name = "InfraestructuraModelo.findByColor", query = "SELECT i FROM InfraestructuraModelo i WHERE i.color = :color")})
public class InfraestructuraModelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_infra_model")
    private Integer idInfraModel;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dimensiones")
    private Float dimensiones;
    @Column(name = "perimetro")
    private Float perimetro;
    @Column(name = "es_nivel_agrup")
    private Boolean esNivelAgrup;
    @Column(name = "icono")
    private String icono;
    @Column(name = "color")
    private String color;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInfraModel")
    private List<ElementoInfraestructuraModelo> elementoInfraestructuraModeloList;
    @OneToMany(mappedBy = "idInfraModel")
    private List<Red> redList;
    @OneToMany(mappedBy = "idInfraModel")
    private List<Ot> otList;
    @OneToMany(mappedBy = "idInfraModel")
    private List<InfraestructuraClon> infraestructuraClonList;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area idArea;
    @JoinColumn(name = "id_contacto", referencedColumnName = "id_contacto")
    @ManyToOne
    private Contacto idContacto;
    @OneToMany(mappedBy = "padre")
    private List<InfraestructuraModelo> infraestructuraModeloList;
    @JoinColumn(name = "padre", referencedColumnName = "id_infra_model")
    @ManyToOne
    private InfraestructuraModelo padre;
    @JoinColumn(name = "sistema", referencedColumnName = "id_sistema")
    @ManyToOne
    private Sistema sistema;
    @JoinColumn(name = "id_ubic", referencedColumnName = "id_ubic")
    @ManyToOne
    private Ubicacion idUbic;

    public InfraestructuraModelo() {
    }

    public InfraestructuraModelo(Integer idInfraModel) {
        this.idInfraModel = idInfraModel;
    }

    public InfraestructuraModelo(Integer idInfraModel, String nombre) {
        this.idInfraModel = idInfraModel;
        this.nombre = nombre;
    }

    public Integer getIdInfraModel() {
        return idInfraModel;
    }

    public void setIdInfraModel(Integer idInfraModel) {
        this.idInfraModel = idInfraModel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Boolean getEsNivelAgrup() {
        return esNivelAgrup;
    }

    public void setEsNivelAgrup(Boolean esNivelAgrup) {
        this.esNivelAgrup = esNivelAgrup;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @XmlTransient
    public List<ElementoInfraestructuraModelo> getElementoInfraestructuraModeloList() {
        return elementoInfraestructuraModeloList;
    }

    public void setElementoInfraestructuraModeloList(List<ElementoInfraestructuraModelo> elementoInfraestructuraModeloList) {
        this.elementoInfraestructuraModeloList = elementoInfraestructuraModeloList;
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

    @XmlTransient
    public List<InfraestructuraClon> getInfraestructuraClonList() {
        return infraestructuraClonList;
    }

    public void setInfraestructuraClonList(List<InfraestructuraClon> infraestructuraClonList) {
        this.infraestructuraClonList = infraestructuraClonList;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Contacto getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Contacto idContacto) {
        this.idContacto = idContacto;
    }

    @XmlTransient
    public List<InfraestructuraModelo> getInfraestructuraModeloList() {
        return infraestructuraModeloList;
    }

    public void setInfraestructuraModeloList(List<InfraestructuraModelo> infraestructuraModeloList) {
        this.infraestructuraModeloList = infraestructuraModeloList;
    }

    public InfraestructuraModelo getPadre() {
        return padre;
    }

    public void setPadre(InfraestructuraModelo padre) {
        this.padre = padre;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
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
        hash += (idInfraModel != null ? idInfraModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfraestructuraModelo)) {
            return false;
        }
        InfraestructuraModelo other = (InfraestructuraModelo) object;
        if ((this.idInfraModel == null && other.idInfraModel != null) || (this.idInfraModel != null && !this.idInfraModel.equals(other.idInfraModel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.InfraestructuraModelo[ idInfraModel=" + idInfraModel + " ]";
    }
    
}
