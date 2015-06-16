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
@Table(name = "activo_modelo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActivoModelo.findAll", query = "SELECT a FROM ActivoModelo a"),
    @NamedQuery(name = "ActivoModelo.findByIdActModel", query = "SELECT a FROM ActivoModelo a WHERE a.idActModel = :idActModel"),
    @NamedQuery(name = "ActivoModelo.findByNombre", query = "SELECT a FROM ActivoModelo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "ActivoModelo.findByModelo", query = "SELECT a FROM ActivoModelo a WHERE a.modelo = :modelo"),
    @NamedQuery(name = "ActivoModelo.findBySerie", query = "SELECT a FROM ActivoModelo a WHERE a.serie = :serie"),
    @NamedQuery(name = "ActivoModelo.findByColor", query = "SELECT a FROM ActivoModelo a WHERE a.color = :color"),
    @NamedQuery(name = "ActivoModelo.findByCostoxhora", query = "SELECT a FROM ActivoModelo a WHERE a.costoxhora = :costoxhora"),
    @NamedQuery(name = "ActivoModelo.findByAnnoFab", query = "SELECT a FROM ActivoModelo a WHERE a.annoFab = :annoFab"),
    @NamedQuery(name = "ActivoModelo.findByAnnoVidaUtil", query = "SELECT a FROM ActivoModelo a WHERE a.annoVidaUtil = :annoVidaUtil"),
    @NamedQuery(name = "ActivoModelo.findByProcedencia", query = "SELECT a FROM ActivoModelo a WHERE a.procedencia = :procedencia"),
    @NamedQuery(name = "ActivoModelo.findByObservaciones", query = "SELECT a FROM ActivoModelo a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "ActivoModelo.findByValor", query = "SELECT a FROM ActivoModelo a WHERE a.valor = :valor"),
    @NamedQuery(name = "ActivoModelo.findByCodigo", query = "SELECT a FROM ActivoModelo a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "ActivoModelo.findByMarca", query = "SELECT a FROM ActivoModelo a WHERE a.marca = :marca"),
    @NamedQuery(name = "ActivoModelo.findByEsNivelAgrup", query = "SELECT a FROM ActivoModelo a WHERE a.esNivelAgrup = :esNivelAgrup"),
    @NamedQuery(name = "ActivoModelo.findByIcono", query = "SELECT a FROM ActivoModelo a WHERE a.icono = :icono"),
    @NamedQuery(name = "ActivoModelo.findByPanelColor", query = "SELECT a FROM ActivoModelo a WHERE a.panelColor = :panelColor")})
public class ActivoModelo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_act_model")
    private Integer idActModel;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
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
    @Column(name = "marca")
    private String marca;
    @Column(name = "es_nivel_agrup")
    private Boolean esNivelAgrup;
    @Column(name = "icono")
    private String icono;
    @Column(name = "panel_color")
    private String panelColor;
    @OneToMany(mappedBy = "idActModel")
    private List<ActivoClon> activoClonList;
    @OneToMany(mappedBy = "idActModel")
    private List<Red> redList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActModel")
    private List<ActivoModeloElemento> activoModeloElementoList;
    @OneToMany(mappedBy = "idActModel")
    private List<Ot> otList;
    @OneToMany(mappedBy = "padre")
    private List<ActivoModelo> activoModeloList;
    @JoinColumn(name = "padre", referencedColumnName = "id_act_model")
    @ManyToOne
    private ActivoModelo padre;
    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne
    private Area idArea;
    @JoinColumn(name = "id_contacto", referencedColumnName = "id_contacto")
    @ManyToOne
    private Contacto idContacto;
    @JoinColumn(name = "sistema", referencedColumnName = "id_sistema")
    @ManyToOne
    private Sistema sistema;
    @JoinColumn(name = "id_ubic", referencedColumnName = "id_ubic")
    @ManyToOne
    private Ubicacion idUbic;

    public ActivoModelo() {
    }

    public ActivoModelo(Integer idActModel) {
        this.idActModel = idActModel;
    }

    public ActivoModelo(Integer idActModel, String nombre) {
        this.idActModel = idActModel;
        this.nombre = nombre;
    }

    public Integer getIdActModel() {
        return idActModel;
    }

    public void setIdActModel(Integer idActModel) {
        this.idActModel = idActModel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public String getPanelColor() {
        return panelColor;
    }

    public void setPanelColor(String panelColor) {
        this.panelColor = panelColor;
    }

    @XmlTransient
    public List<ActivoClon> getActivoClonList() {
        return activoClonList;
    }

    public void setActivoClonList(List<ActivoClon> activoClonList) {
        this.activoClonList = activoClonList;
    }

    @XmlTransient
    public List<Red> getRedList() {
        return redList;
    }

    public void setRedList(List<Red> redList) {
        this.redList = redList;
    }

    @XmlTransient
    public List<ActivoModeloElemento> getActivoModeloElementoList() {
        return activoModeloElementoList;
    }

    public void setActivoModeloElementoList(List<ActivoModeloElemento> activoModeloElementoList) {
        this.activoModeloElementoList = activoModeloElementoList;
    }

    @XmlTransient
    public List<Ot> getOtList() {
        return otList;
    }

    public void setOtList(List<Ot> otList) {
        this.otList = otList;
    }

    @XmlTransient
    public List<ActivoModelo> getActivoModeloList() {
        return activoModeloList;
    }

    public void setActivoModeloList(List<ActivoModelo> activoModeloList) {
        this.activoModeloList = activoModeloList;
    }

    public ActivoModelo getPadre() {
        return padre;
    }

    public void setPadre(ActivoModelo padre) {
        this.padre = padre;
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
        hash += (idActModel != null ? idActModel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActivoModelo)) {
            return false;
        }
        ActivoModelo other = (ActivoModelo) object;
        if ((this.idActModel == null && other.idActModel != null) || (this.idActModel != null && !this.idActModel.equals(other.idActModel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.ActivoModelo[ idActModel=" + idActModel + " ]";
    }
    
}
