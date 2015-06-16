/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dy
 */
@Entity
@Table(name = "contacto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contacto.findAll", query = "SELECT c FROM Contacto c"),
    @NamedQuery(name = "Contacto.findByIdContacto", query = "SELECT c FROM Contacto c WHERE c.idContacto = :idContacto"),
    @NamedQuery(name = "Contacto.findByNombre", query = "SELECT c FROM Contacto c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Contacto.findByCedula", query = "SELECT c FROM Contacto c WHERE c.cedula = :cedula"),
    @NamedQuery(name = "Contacto.findBySexo", query = "SELECT c FROM Contacto c WHERE c.sexo = :sexo"),
    @NamedQuery(name = "Contacto.findByFechNac", query = "SELECT c FROM Contacto c WHERE c.fechNac = :fechNac"),
    @NamedQuery(name = "Contacto.findByDireccion", query = "SELECT c FROM Contacto c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Contacto.findByFax", query = "SELECT c FROM Contacto c WHERE c.fax = :fax"),
    @NamedQuery(name = "Contacto.findByEmail", query = "SELECT c FROM Contacto c WHERE c.email = :email"),
    @NamedQuery(name = "Contacto.findByWeb", query = "SELECT c FROM Contacto c WHERE c.web = :web"),
    @NamedQuery(name = "Contacto.findByCargo", query = "SELECT c FROM Contacto c WHERE c.cargo = :cargo"),
    @NamedQuery(name = "Contacto.findByOficina", query = "SELECT c FROM Contacto c WHERE c.oficina = :oficina"),
    @NamedQuery(name = "Contacto.findByReferencia", query = "SELECT c FROM Contacto c WHERE c.referencia = :referencia"),
    @NamedQuery(name = "Contacto.findByEsNatural", query = "SELECT c FROM Contacto c WHERE c.esNatural = :esNatural"),
    @NamedQuery(name = "Contacto.findByEsEmpleado", query = "SELECT c FROM Contacto c WHERE c.esEmpleado = :esEmpleado")})
public class Contacto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contacto")
    private Integer idContacto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cedula")
    private BigInteger cedula;
    @Column(name = "sexo")
    private Character sexo;
    @Column(name = "fech_nac")
    @Temporal(TemporalType.DATE)
    private Date fechNac;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fax")
    private String fax;
    @Column(name = "email")
    private String email;
    @Column(name = "web")
    private String web;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "oficina")
    private String oficina;
    @Column(name = "referencia")
    private String referencia;
    @Column(name = "es_natural")
    private Boolean esNatural;
    @Column(name = "es_empleado")
    private Boolean esEmpleado;
    @OneToMany(mappedBy = "idContacto")
    private List<ActivoClon> activoClonList;
    @OneToMany(mappedBy = "idContacto")
    private List<Telefono> telefonoList;
    @JoinColumn(name = "sector", referencedColumnName = "id_sector")
    @ManyToOne
    private Sector sector;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_user")
    @OneToOne
    private Usuario idUsuario;
    @OneToMany(mappedBy = "idContacto")
    private List<InfraestructuraClon> infraestructuraClonList;
    @OneToMany(mappedBy = "idContacto")
    private List<ActivoModelo> activoModeloList;
    @OneToMany(mappedBy = "idContacto")
    private List<InfraestructuraModelo> infraestructuraModeloList;

    public Contacto() {
    }

    public Contacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getCedula() {
        return cedula;
    }

    public void setCedula(BigInteger cedula) {
        this.cedula = cedula;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getFechNac() {
        return fechNac;
    }

    public void setFechNac(Date fechNac) {
        this.fechNac = fechNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getEsNatural() {
        return esNatural;
    }

    public void setEsNatural(Boolean esNatural) {
        this.esNatural = esNatural;
    }

    public Boolean getEsEmpleado() {
        return esEmpleado;
    }

    public void setEsEmpleado(Boolean esEmpleado) {
        this.esEmpleado = esEmpleado;
    }

    @XmlTransient
    public List<ActivoClon> getActivoClonList() {
        return activoClonList;
    }

    public void setActivoClonList(List<ActivoClon> activoClonList) {
        this.activoClonList = activoClonList;
    }

    @XmlTransient
    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idContacto != null ? idContacto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contacto)) {
            return false;
        }
        Contacto other = (Contacto) object;
        if ((this.idContacto == null && other.idContacto != null) || (this.idContacto != null && !this.idContacto.equals(other.idContacto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.system.entities.Contacto[ idContacto=" + idContacto + " ]";
    }
    
}
