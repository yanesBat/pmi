package com.system.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.system.entities.ActivoClon;
import com.system.entities.ActivoModelo;
import com.system.entities.Area;
import com.system.entities.Elemento;
import com.system.service.ActivoService;
import com.system.service.AreaService;

@Component
@ManagedBean
@Scope("session")
public class ActivoMbean {

	@Autowired
	ActivoService activoModelService;

	@Autowired
	AreaService areaService;
	
	@Autowired
	UbicacionMbean ubic;

	@PersistenceContext
	EntityManager em;

	private TreeNode root;

	private Stack<TreeNode> breadcumb = new Stack<TreeNode>();

	private TreeNode selectedNode;	

	ActivoClon clon = new ActivoClon();

	private Integer selectedArea;

	private Integer selectedAct;

	private List<Area> areas;
	
	private boolean showChildren = true;

	List<TreeNode> tre;

	List<TreeNode> availTree = new ArrayList<TreeNode>();

	private List<ActivoModelo> activolist = new ArrayList<ActivoModelo>();

	private List<ActivoModelo> activoHijos = new ArrayList<ActivoModelo>();

	private List<Elemento> elementos = new ArrayList<Elemento>();

	private String currentElement;

	private Boolean isRoot;

	private Boolean showData = false;

	private Boolean isLevel = false;

	private int rows = 7;

	private String colums = "col-lg-12";

	private ActivoModelo currentActivoModelo = new ActivoModelo();

	private ActivoModelo selectedActivoModelo;

	@PostConstruct
	public void init() {
		this.root = activoModelService.createActivoTree();
		selectedNode = root;
		availTree.addAll(allTree());
	}

	public void add() {
		Integer x = 0;
		ActivoModelo am = getNodeData(selectedNode);
		if (am.getIdActModel() != null && am.getIdActModel() != 0) {
			x = this.getSelectedActivoModelo().getIdActModel();
		}
		currentActivoModelo.setIdArea(areaService.getByID(selectedArea));
		currentActivoModelo.setEsNivelAgrup(isLevel);
		currentActivoModelo.setActivoClonList(null);
		currentActivoModelo.setRedList(null);

		currentActivoModelo.setActivoModeloList(null);	
		activoModelService.add(x, currentActivoModelo);
		selectedAct = am.getIdActModel();
		this.init();
		if (selectedAct == null || selectedAct == 0) {
			updateSelected(root);
		} else {
			updateSelectedNode(root);
			updateSelected(selectedNode);
		}
		currentActivoModelo = new ActivoModelo();
		selectedArea = null;
		isRoot = false;
	}

	private ActivoModelo getNodeData(TreeNode t) {
		return (ActivoModelo) t.getData();
	}

	private void updateSelectedNode(TreeNode t) {
		ActivoModelo a = getNodeData(t);
		Integer aux = a.getIdActModel();
		if (selectedAct != null && aux!=null &&(selectedAct - aux) ==0) {
			selectedNode = t;
		} else {
			for (TreeNode tr : t.getChildren()) {
				updateSelectedNode(tr);
			}
		}

	}
	
	
	public void updateClon(){
		ActivoModelo x = getNodeData(selectedNode);
		ubic.setSelectedNode(ubic.getRoot());
		clon.setAnnoFab(x.getAnnoFab());
		clon.setAnnoVidaUtil(x.getAnnoVidaUtil());
		clon.setCodigo(x.getCodigo());
		clon.setColor(x.getColor());
		clon.setCostoxhora(x.getCostoxhora());
		clon.setIdContacto(x.getIdContacto());
		clon.setMarca(x.getMarca());
		clon.setProcedencia(x.getProcedencia());
		clon.setMarca(x.getMarca());
		clon.setModelo(x.getModelo());
		clon.setNombre(x.getNombre());
		clon.setIdActModel(x);
		
	}
	
	public void addClon(){
		clon.setIdUbic(ubic.getSelectedUbicacion());
		activoModelService.addClon(clon);
		ubic.setSelectedNode(ubic.getRoot());
		clon = new ActivoClon();
	}
	
	
	public List<ActivoClon> getClonList() {
		ActivoModelo am = getNodeData(selectedNode);
		if (am.getIdActModel() != null && am.getIdActModel() != 0) {
			return activoModelService.getClonList(am);
		}
		return new ArrayList<ActivoClon>();
	}
	

	public void remove(TreeNode t) {

		ActivoModelo am = getNodeData(selectedNode);
		selectedAct = am.getIdActModel();
		activoModelService.remove(getNodeData(t));
		this.init();
		if (selectedAct == null || selectedAct == 0) {
			selectedNode = root;
		} else {
			updateSelectedNode(root);
		}

	}

	public void updateAct() {

		currentActivoModelo.setIdArea(areaService.getByID(selectedArea));
		activoModelService.update(currentActivoModelo);
		currentActivoModelo = new ActivoModelo();
	}

	public void update() {

		ActivoModelo s = getNodeData(selectedNode);
		if (s.getIdActModel() != null && s.getIdActModel() != 0) {
			currentActivoModelo = (ActivoModelo) selectedNode.getData();
			if (currentActivoModelo.getIdArea() != null) {
				selectedArea = currentActivoModelo.getIdArea().getIdArea();
			}
		}
	}

	public void cleanCurrent() {
		currentActivoModelo = new ActivoModelo();
		selectedArea = null;
	}

	public void updateSelected(TreeNode t) {

		ActivoModelo actm = (ActivoModelo) t.getData();

		if (actm.getIdActModel() == null ||actm.getIdActModel() == 0) {
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
					ActivoModelo at = new ActivoModelo();
					at.setNombre("Root");
					at.setIdActModel(0);
					TreeNode tren = new DefaultTreeNode(at);
					breadcumb.removeAllElements();
					breadcumb.push(tren);
					
				} else {

					breadcumb.push(t.getParent());
				}
			}

			selectedNode = t;
			availTree.clear();
			availTree.addAll(allTree());
		}
	}

	public void showData() {
		if (showData == false) {
			showData = true;
			rows = 2;
			colums = "col-lg-4";
		}

	}

	public List<TreeNode> allTree() {
		List<TreeNode> all = new ArrayList<TreeNode>();

		all = getAllTree(all, root);

		return all;
	}

	private List<TreeNode> getAllTree(List<TreeNode> all, TreeNode root2) {

		for (TreeNode t : root2.getChildren()) {
			if (!all.contains(t)) {
				all.add(t);
			}
			if (!t.isLeaf()) {
				List<TreeNode> aux = getAllTree(all, t);
				for (TreeNode tr : aux) {
					if (!all.contains(tr)) {
						all.add(tr);
					}
				}
			}
		}
		return all;
	}

	public void hideData() {
		showData = false;
		rows = 7;
		colums = "col-lg-12";

	}

	public void onAct(TreeNode t) {
		ActivoModelo temp = getNodeData(t);
		selectedArea = temp.getIdArea().getIdArea();
		isLevel = temp.getEsNivelAgrup();
		currentActivoModelo = temp;
		this.add();

	}

	public ActivoModelo getSelectedActivoModelo() {

		return (ActivoModelo) selectedNode.getData();
	}

	public void setSelectedActivoModelo(ActivoModelo selectedActivoModelo) {
		this.selectedActivoModelo = selectedActivoModelo;
	}

	public List<String> listElement() {
		return (List<String>) em.createQuery("select e.nombre from Elemento e")
				.getResultList();
	}

	public void addElemt() {

		if (this.listElement().contains(currentElement)) {
			elementos.add((Elemento) em.createQuery(
					"select e from Elemento e where e.nombre:=n").setParameter(
					"n", currentElement));
		} else {
			Elemento e = new Elemento();
			e.setNombre(currentElement);
			elementos.add(e);
		}

	}

	public void removeElemt(Elemento e) {
		elementos.remove(e);
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void setActivoModelService(ActivoService activoModelService) {
		this.activoModelService = activoModelService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
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

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public ActivoModelo getCurrentActivoModelo() {
		return currentActivoModelo;
	}

	public void setCurrentActivoModelo(ActivoModelo currentActivoModelo) {
		this.currentActivoModelo = currentActivoModelo;
	}

	public Boolean getIsLevel() {
		return isLevel;
	}

	public void setIsLevel(Boolean isLevel) {
		this.isLevel = isLevel;
	}

	public List<Elemento> getElementos() {
		return elementos;
	}

	public void setElementos(List<Elemento> elementos) {
		this.elementos = elementos;
	}

	public String getCurrentElement() {
		return currentElement;
	}

	public void setCurrentElement(String currentElement) {
		this.currentElement = currentElement;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<ActivoModelo> getActivolist() {
		return activolist;
	}

	public void setActivolist(List<ActivoModelo> activolist) {
		this.activolist = activolist;
	}

	public List<ActivoModelo> getActivoHijos() {

		List<ActivoModelo> aux = new ArrayList<ActivoModelo>();
		for (TreeNode t : selectedNode.getChildren()) {
			aux.add((ActivoModelo) t.getData());
		}
		return aux;
	}

	private TreeNode getSelectedNode(ActivoModelo selectedAct, TreeNode t) {

		ActivoModelo a = (ActivoModelo) t.getData();
		if (a.getIdActModel() == selectedAct.getIdActModel()) {
			return t;
		}

		else {
			if (!t.isLeaf()) {
				for (TreeNode tn : t.getChildren()) {
					TreeNode tre = getSelectedNode(selectedAct, tn);
					if (tre != tn) {
						return tre;
					}

				}
			}

		}

		return t;
	}

	public void setActivoHijos(List<ActivoModelo> activoHijos) {
		this.activoHijos = activoHijos;
	}

	public List<TreeNode> getBreadcumb() {
		return breadcumb;
	}

	public List<TreeNode> getTre() {
		if (selectedNode == null) {
			tre = root.getChildren();
		} else {
			if (selectedNode.getChildren().size() < 1) {
				showChildren = false;
			}else {
				showChildren = true;
			}
			tre = selectedNode.getChildren();
		}

		return tre;
	}

	public void setTre(List<TreeNode> tre) {
		this.tre = tre;
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

	public List<TreeNode> getAvailTree() {
		List<String> aux = new ArrayList<String>();
		List<TreeNode> rem = new ArrayList<TreeNode>();
		for (TreeNode t : availTree) {
			if (aux.contains(getNodeData(t).getNombre())) {
				rem.add(t);
			} else {
				aux.add(getNodeData(t).getNombre());
			}
		}
		for (TreeNode treeNode : rem) {
			availTree.remove(treeNode);
		}
		return availTree;
	}

	public void setAvailTree(List<TreeNode> availTree) {
		this.availTree = availTree;
	}

	public ActivoClon getClon() {
		return clon;
	}

	public void setClon(ActivoClon clon) {
		this.clon = clon;
	}

	public boolean isShowChildren() {
		return showChildren;
	}

	public void setShowChildren(boolean showChildren) {
		this.showChildren = showChildren;
	}
	
	public boolean isSelected(ActivoModelo a) {
		if (a.getActivoModeloList().contains(getNodeData(selectedNode))) {
			return true;
		}
		return false;
	}

}
