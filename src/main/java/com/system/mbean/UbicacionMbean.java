package com.system.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.system.entities.Area;
import com.system.entities.Ubicacion;
import com.system.service.AreaService;
import com.system.service.UbicacionService;

@Component
@ManagedBean
@Scope("session")
public class UbicacionMbean {

	private static final long serialVersionUID = 1L;

	@Autowired
	UbicacionService ubicacionService;

	@Autowired
	AreaService areaService;

	private TreeNode root;

	private TreeNode selectedNode;

	private Integer selectedArea;

	List<TreeNode> tre;

	private List<Area> areas;

	private Boolean showData = false;

	private int rows = 7;

	private String colums = "col-lg-12";

	private Ubicacion currentUbicacion = new Ubicacion();

	private Ubicacion selectedUbicacion;

	private MapModel emptyModel;

	private Stack<TreeNode> breadcumb = new Stack<TreeNode>();

	private double lat;

	private double lng;

	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public double getLat() {
		return selectedUbicacion.getLat();
	}

	public void setLat(double lat) {
		selectedUbicacion.setLat(lat);
	}

	public double getLng() {
		return selectedUbicacion.getLng();
	}

	public void setLng(double lng) {
		selectedUbicacion.setLng(lng);
	}

	public String getCoordenada() {
		if (selectedUbicacion != null && selectedUbicacion.getLat() != null
				&& selectedUbicacion.getLng() != null) {
			return selectedUbicacion.getLat().toString() + ","
					+ selectedUbicacion.getLng().toString();
		}
		return "-0.188313616246991,-78.46639051875007";
	}

	public void addMarker() {
		Marker marker = new Marker(new LatLng(lat, lng),
				selectedUbicacion.getNombre());
		emptyModel.getMarkers().clear();
		emptyModel.addOverlay(marker);
		ubicacionService.update(selectedUbicacion);

	}

	@PostConstruct
	public void init() {
		this.root = ubicacionService.createUbicacionTree();
		emptyModel = new DefaultMapModel();
	}

	public TreeNode getRoot() {
		return root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void setUbicacionService(UbicacionService ubicacionService) {
		this.ubicacionService = ubicacionService;
	}
	
	public void cleanCurrent() {
		currentUbicacion = new Ubicacion();
		selectedArea = null;
	}

	public void add() {
		Integer x = 0;
		if (this.getSelectedUbicacion() != null) {
			x = this.getSelectedUbicacion().getIdUbic();
		}
		currentUbicacion.setIdArea(areaService.getByID(selectedArea));
		ubicacionService.add(x, currentUbicacion);
		this.init();
		cleanCurrent();
	}

	public void remove() {
		if (selectedNode != null) {
			ubicacionService.remove(this.getSelectedUbicacion());
			selectedNode = selectedNode.getParent();
			this.init();
		}
	}

	public void remove(TreeNode t) {
		ubicacionService.remove(getNodedata(t));
		this.init();
		this.updateSelected(t.getParent());

	}

	private Ubicacion getNodedata(TreeNode t) {
		return (Ubicacion)t.getData();
	}

	public void updateUbic() {

		currentUbicacion.setIdArea(areaService.getByID(selectedArea));
		ubicacionService.update(currentUbicacion);
		currentUbicacion = new Ubicacion();
	}

	public void update() {
		if (selectedNode != null) {
			currentUbicacion = (Ubicacion) selectedNode.getData();
			if (currentUbicacion.getIdArea() != null) {
				selectedArea = currentUbicacion.getIdArea().getIdArea();
			}
		}
	}

	public Ubicacion getSelectedUbicacion() {

		if (selectedNode != null) {
			selectedUbicacion = (Ubicacion) selectedNode.getData();
		}

		return selectedUbicacion;
	}

	public void updateSelected(TreeNode t) {

		Ubicacion actm = (Ubicacion) t.getData();

		if (actm.getIdUbic() == null || actm.getIdUbic() == 0) {
			breadcumb.removeAllElements();
			selectedNode = root;
			hideData();
		} else {
			if (breadcumb.contains(t)) {
				while (!breadcumb.peek().equals(t)) {
					breadcumb.pop();
				}
				breadcumb.pop();
			} else {
				if (root.getChildren().contains(t)) {
					Ubicacion at = new Ubicacion();
					at.setNombre("Root");
					at.setIdUbic(0);
					TreeNode tren = new DefaultTreeNode(at);
					breadcumb.removeAllElements();
					breadcumb.push(tren);
				} else {

					breadcumb.push(t.getParent());
				}
			}

			selectedNode = t;
		}
	}

	public void hideData() {
		showData = false;
		rows = 7;
		colums = "col-lg-12";

	}

	public void showData() {
		if (showData == false) {
			showData = true;
			rows = 2;
			colums = "col-lg-4";
		}

	}

	public void setSelectedUbicacion(Ubicacion selectedUbicacion) {
		this.selectedUbicacion = selectedUbicacion;
	}

	public Ubicacion getCurrentUbicacion() {
		return currentUbicacion;
	}

	public void setCurrentUbicacion(Ubicacion currentUbicacion) {
		this.currentUbicacion = currentUbicacion;
	}

	public Integer getSelectedArea() {
		return selectedArea;
	}

	public void setSelectedArea(Integer selectedArea) {
		this.selectedArea = selectedArea;
	}

	public List<Area> getAreas() {

		return areaService.list();

	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public Stack<TreeNode> getBreadcumb() {
		return breadcumb;
	}

	public void setBreadcumb(Stack<TreeNode> breadcumb) {
		this.breadcumb = breadcumb;
	}

	public Boolean getShowData() {
		return showData;
	}

	public void setShowData(Boolean showData) {
		this.showData = showData;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getColums() {
		return colums;
	}

	public void setColums(String colums) {
		this.colums = colums;
	}

	public List<TreeNode> getTre() {
		if (selectedNode == null) {
			tre = root.getChildren();
		} else {
			tre = selectedNode.getChildren();
		}

		return tre;
	}

	public void setTre(List<TreeNode> tre) {
		this.tre = tre;
	}

}
