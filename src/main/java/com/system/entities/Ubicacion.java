/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dy
 */
@Entity
@Table(name = "ubicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ubicacion.findAll", query = "SELECT u FROM Ubicacion u"),
    @NamedQuery(name = "Ubicacion.findByIdUbic", query = "SELECT u FROM Ubicacion u WHERE u.idUbic = :idUbic"),
    @NamedQuery(name = "Ubicacion.findByCodigo", query = "SELECT u FROM Ubicacion u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "Ubicacion.findBySiglas", query = "SELECT u FROM Ubicacion u WHERE u.siglas = :siglas"),
    @NamedQuery(name = "Ubicacion.findByArea", query = "SELECT u FROM Ubicacion u WHERE u.area = :area"),
    @NamedQuery(name = "Ubicacion.findByPerimetro", query = "SELECT u FROM Ubicacion u WHERE u.perimetro = :perimetro"),
    @NamedQuery(name = "Ubicacion.findByDimensiones", query = "SELECT u FROM Ubicacion u WHERE u.dimensiones = :dimensiones"),
    @NamedQuery(name = "Ubicacion.findByNombre", query = "SELECT u FROM Ubicacion u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Ubicacion.findByLat", query = "SELECT u FROM Ubicacion u WHERE u.lat = :lat"),
    @NamedQuery(name = "Ubicacion.findByLng", query = "SELECT u FROM Ubicacion u WHERE u.lng = :lng")})
public class Ubicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ubic")
    private Integer idUbic;
    @Column(name = "codigo")
    private Integer codigo;
    @Column(name = "siglas")
    private String siglas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "area")
    private Float area;
    @Column(name = "perimetro")
    private Float perimetro;
    @Column(name = "dimensiones")
    private Float dimensiones;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lng")
    private Double lng;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne
    private Area idArea;
    @OneToMany(mappedBy = "padre")
    private List<Ubicacion> ubicacionList;
    @JoinColumn(name = "padre", referencedColumnName = "id_ubic")
    @ManyToOne
    private Ubicacion padre;
    @OneToMany(mappedBy = "idUbic")
    private List<ActivoClon> activoClonList;
    @OneToMany(mappedBy = "idUbic")
    private List<InfraestructuraClon> infraestructuraClonList;
    @OneToMany(mappedBy = "idUbic")
    private List<ActivoModelo> activoModeloList;
    @OneToMany(mappedBy = "idUbic")
    private List<InfraestructuraModelo> infraestructuraModeloList;

    public Ubicacion() {
    }

    public Ubicacion(Integer idUbic) {
        this.idUbic = idUbic;
    }

    public Ubicacion(Integer idUbic, String nombre) {
        this.idUbic = idUbic;
        this.nombre = nombre;
    }

    public Integer getIdUbic() {
        return idUbic;
    }

    public void setIdUbic(Integer idUbic) {
        this.idUbic = idUbic;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Float getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(Float perimetro) {
        this.perimetro = perimetro;
    }

    public Float getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(Float dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    @XmlTransient
    public List<Ubicacion> getUbicacionList() {
        return ubicacionList;
    }

    public void setUbicacionList(List<Ubicacion> ubicacionList) {
        this.ubicacionList = ubicacionList;
    }

    public Ubicacion getPadre() {
        return padre;
    }

    public void setPadre(Ubicacion padre) {
        this.padre = padre;
    }

    @XmlTransient
    public List<ActivoClon> getActivoClonList() {
        return activoClonList;
    }

    public void setActivoClonList(List<ActivoClon> activoClonList) {
        this.activoClonList = activoClonList;
    }

    @XmlTransient
    public List<InfraestructuraClon> getInfraestructuraClonList() {
        return infraestructuraClonList;
    }

    public void setInfraestructuraClonList(List<InfraestructuraClon> infraestructuraClonList) {
        this.infraestructuraClonList = infraestructuraClonList;
    }

    @XmlTransient
    public List<ActivoModelo> getActivoModeloList() {
        return activoModeloList;
    }

    public void setActivoModeloList(List<ActivoModelo> activoModeloList) {
        this.activoModeloList = activoModeloList;
    }

    @XmlTransient
    public List<InfraestructuraModelo> getInfraestructuraModeloList() {
        return infraestructuraModeloList;
    }

    public void setInfraestructuraModeloList(List<InfraestructuraModelo> infraestructuraModeloList) {
        this.infraestructuraModeloList = infraestructuraModeloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUbic != null ? idUbic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.idUbic == null && other.idUbic != null) || (this.idUbic != null && !this.idUbic.equals(other.idUbic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Ubicacion[ idUbic=" + idUbic + " ]";
    }
    
}
