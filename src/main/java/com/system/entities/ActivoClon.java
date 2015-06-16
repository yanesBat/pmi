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
@Table(name = "activo_clon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoClon.findAll", query = "SELECT a FROM ActivoClon a"),
    @NamedQuery(name = "ActivoClon.findByIdActivClon", query = "SELECT a FROM ActivoClon a WHERE a.idActivClon = :idActivClon"),
    @NamedQuery(name = "ActivoClon.findByMarca", query = "SELECT a FROM ActivoClon a WHERE a.marca = :marca"),
    @NamedQuery(name = "ActivoClon.findByModelo", query = "SELECT a FROM ActivoClon a WHERE a.modelo = :modelo"),
    @NamedQuery(name = "ActivoClon.findBySerie", query = "SELECT a FROM ActivoClon a WHERE a.serie = :serie"),
    @NamedQuery(name = "ActivoClon.findByColor", query = "SELECT a FROM ActivoClon a WHERE a.color = :color"),
    @NamedQuery(name = "ActivoClon.findByCostoxhora", query = "SELECT a FROM ActivoClon a WHERE a.costoxhora = :costoxhora"),
    @NamedQuery(name = "ActivoClon.findByAnnoFab", query = "SELECT a FROM ActivoClon a WHERE a.annoFab = :annoFab"),
    @NamedQuery(name = "ActivoClon.findByAnnoVidaUtil", query = "SELECT a FROM ActivoClon a WHERE a.annoVidaUtil = :annoVidaUtil"),
    @NamedQuery(name = "ActivoClon.findByProcedencia", query = "SELECT a FROM ActivoClon a WHERE a.procedencia = :procedencia"),
    @NamedQuery(name = "ActivoClon.findByObservaciones", query = "SELECT a FROM ActivoClon a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "ActivoClon.findByValor", query = "SELECT a FROM ActivoClon a WHERE a.valor = :valor"),
    @NamedQuery(name = "ActivoClon.findByCodigo", query = "SELECT a FROM ActivoClon a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "ActivoClon.findByNombre", query = "SELECT a FROM ActivoClon a WHERE a.nombre = :nombre")})
public class ActivoClon implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activoClon")
    private List<ActivoClonMovimiento> activoClonMovimientoList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activ_clon")
    private Integer idActivClon;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "serie")
    private String serie;
    @Column(name = "color")
    private String color;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costoxhora")
    private Float costoxhora;
    @Column(name = "anno_fab")
    private Integer annoFab;
    @Column(name = "anno_vida_util")
    private Integer annoVidaUtil;
    @Column(name = "procedencia")
    private String procedencia;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "valor")
    private Float valor;
    @Column(name = "codigo")
    private Integer codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "id_act_model", referencedColumnName = "id_act_model")
    @ManyToOne
    private ActivoModelo idActModel;
    @JoinColumn(name = "id_contacto", referencedColumnName = "id_contacto")
    @ManyToOne
    private Contacto idContacto;
    @JoinColumn(name = "id_ubic", referencedColumnName = "id_ubic")
    @ManyToOne
    private Ubicacion idUbic;
    @OneToMany(mappedBy = "idActivClon")
    private List<Red> redList;
    @OneToMany(mappedBy = "idActivClon")
    private List<Ot> otList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActivClon")
    private List<ActivoClonSolicitAdminAct> activoClonSolicitAdminActList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActivClon")
    private List<ActivoClonElemento> activoClonElementoList;

    public ActivoClon() {
    }

    public ActivoClon(Integer idActivClon) {
        this.idActivClon = idActivClon;
    }

    public ActivoClon(Integer idActivClon, String nombre) {
        this.idActivClon = idActivClon;
        this.nombre = nombre;
    }

    public Integer getIdActivClon() {
        return idActivClon;
    }

    public void setIdActivClon(Integer idActivClon) {
        this.idActivClon = idActivClon;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getCostoxhora() {
        return costoxhora;
    }

    public void setCostoxhora(Float costoxhora) {
        this.costoxhora = costoxhora;
    }

    public Integer getAnnoFab() {
        return annoFab;
    }

    public void setAnnoFab(Integer annoFab) {
        this.annoFab = annoFab;
    }

    public Integer getAnnoVidaUtil() {
        return annoVidaUtil;
    }

    public void setAnnoVidaUtil(Integer annoVidaUtil) {
        this.annoVidaUtil = annoVidaUtil;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ActivoModelo getIdActModel() {
        return idActModel;
    }

    public void setIdActModel(ActivoModelo idActModel) {
        this.idActModel = idActModel;
    }

    public Contacto getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Contacto idContacto) {
        this.idContacto = idContacto;
    }

    public Ubicacion getIdUbic() {
        return idUbic;
    }

    public void setIdUbic(Ubicacion idUbic) {
        this.idUbic = idUbic;
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
    public List<ActivoClonSolicitAdminAct> getActivoClonSolicitAdminActList() {
        return activoClonSolicitAdminActList;
    }

    public void setActivoClonSolicitAdminActList(List<ActivoClonSolicitAdminAct> activoClonSolicitAdminActList) {
        this.activoClonSolicitAdminActList = activoClonSolicitAdminActList;
    }

    @XmlTransient
    public List<ActivoClonElemento> getActivoClonElementoList() {
        return activoClonElementoList;
    }

    public void setActivoClonElementoList(List<ActivoClonElemento> activoClonElementoList) {
        this.activoClonElementoList = activoClonElementoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivClon != null ? idActivClon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoClon)) {
            return false;
        }
        ActivoClon other = (ActivoClon) object;
        if ((this.idActivClon == null && other.idActivClon != null) || (this.idActivClon != null && !this.idActivClon.equals(other.idActivClon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.ActivoClon[ idActivClon=" + idActivClon + " ]";
    }

    @XmlTransient
    public List<ActivoClonMovimiento> getActivoClonMovimientoList() {
        return activoClonMovimientoList;
    }

    public void setActivoClonMovimientoList(List<ActivoClonMovimiento> activoClonMovimientoList) {
        this.activoClonMovimientoList = activoClonMovimientoList;
    }
    
}
