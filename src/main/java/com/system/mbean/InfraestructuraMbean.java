package com.system.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.system.entities.InfraestructuraModelo;
import com.system.entities.Area;
import com.system.entities.Elemento;
import com.system.entities.InfraestructuraClon;
import com.system.entities.InfraestructuraModelo;
import com.system.entities.Ubicacion;
import com.system.service.AreaService;
import com.system.service.InfraestructuraModelService;

@Component
@ManagedBean
@Scope("session")
public class InfraestructuraMbean {
	
	
	@Autowired
	InfraestructuraModelService infraestructuraModelService;
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	UbicacionMbean ubic;

	@PersistenceContext
	EntityManager em;

	private TreeNode root;

	private Stack<TreeNode> breadcumb = new Stack<TreeNode>();

	private TreeNode selectedNode;	

	InfraestructuraClon clon = new InfraestructuraClon();

	private Integer selectedArea;

	private Integer selectedInfra;

	private List<Area> areas;
	
	private boolean showChildren = true;

	List<TreeNode> tre;

	List<TreeNode> availTree = new ArrayList<TreeNode>();

	private List<InfraestructuraModelo> infraList = new ArrayList<InfraestructuraModelo>();

	private List<InfraestructuraModelo> infraHijos = new ArrayList<InfraestructuraModelo>();

	private List<Elemento> elementos = new ArrayList<Elemento>();

	private String currentElement;

	private Boolean showData = false;
	
	private Boolean isLevel = false;

	private int rows = 7;

	private String colums = "col-lg-12";

	private InfraestructuraModelo currentInfraestructuraModelo = new InfraestructuraModelo();

	private InfraestructuraModelo selectedInfraestructuraModelo;

	@PostConstruct
	public void init() {
		this.root = infraestructuraModelService.createInfraestructuraTree();
		selectedNode = root;
		availTree.addAll(allTree());
	}

	public void add() {
		Integer x = 0;
		InfraestructuraModelo am = getNodeData(selectedNode);
		if (am.getIdInfraModel() != null && am.getIdInfraModel() != 0) {
			x = this.getSelectedInfraestructuraModelo().getIdInfraModel();
		}
		currentInfraestructuraModelo.setIdArea(areaService.getByID(selectedArea));
		currentInfraestructuraModelo.setEsNivelAgrup(isLevel);
		currentInfraestructuraModelo.setInfraestructuraClonList(null);
		currentInfraestructuraModelo.setRedList(null);

		currentInfraestructuraModelo.setInfraestructuraModeloList(null);	
		infraestructuraModelService.add(x, currentInfraestructuraModelo);
		selectedInfra = am.getIdInfraModel();
		this.init();
		if (selectedInfra == null || selectedInfra == 0) {
			updateSelected(root);
		} else {
			updateSelectedNode(root);
			updateSelected(selectedNode);
		}
		currentInfraestructuraModelo = new InfraestructuraModelo();
		selectedArea = null;
	}

	private InfraestructuraModelo getNodeData(TreeNode t) {
		return (InfraestructuraModelo) t.getData();
	}

	private void updateSelectedNode(TreeNode t) {
		InfraestructuraModelo a = getNodeData(t);
		Integer aux = a.getIdInfraModel();
		if (selectedInfra != null && aux!=null &&(selectedInfra - aux) ==0) {
			selectedNode = t;
		} else {
			for (TreeNode tr : t.getChildren()) {
				updateSelectedNode(tr);
			}
		}

	}
	
	
	public void updateClon(){
		InfraestructuraModelo x = getNodeData(selectedNode);
		ubic.setSelectedNode(ubic.getRoot());
		clon.setDimensiones(x.getDimensiones());
		clon.setNombre(x.getNombre());
		clon.setIdInfraModel(x);
		
	}
	
	public void addClon(){
		clon.setIdUbic(ubic.getSelectedUbicacion());
		infraestructuraModelService.addClon(clon);
		ubic.setSelectedNode(ubic.getRoot());
		clon = new InfraestructuraClon();
	}
	
	
	public List<InfraestructuraClon> getClonList() {
		InfraestructuraModelo am = getNodeData(selectedNode);
		if (am.getIdInfraModel() != null && am.getIdInfraModel() != 0) {
			return infraestructuraModelService.getClonList(am);
		}
		return new ArrayList<InfraestructuraClon>();
	}
	
	
	
	
	

	public void remove(TreeNode t) {

		InfraestructuraModelo am = getNodeData(selectedNode);
		selectedInfra = am.getIdInfraModel();
		infraestructuraModelService.remove(getNodeData(t));
		this.init();
		if (selectedInfra == null || selectedInfra == 0) {
			selectedNode = root;
		} else {
			updateSelectedNode(root);
		}

	}

	public void updateInfra() {

		currentInfraestructuraModelo.setIdArea(areaService.getByID(selectedArea));
		infraestructuraModelService.update(currentInfraestructuraModelo);
		currentInfraestructuraModelo = new InfraestructuraModelo();
	}

	public void update() {

		InfraestructuraModelo s = getNodeData(selectedNode);
		if (s.getIdInfraModel() != null && s.getIdInfraModel() != 0) {
			currentInfraestructuraModelo = (InfraestructuraModelo) selectedNode.getData();
			if (currentInfraestructuraModelo.getIdArea() != null) {
				selectedArea = currentInfraestructuraModelo.getIdArea().getIdArea();
			}
		}
	}

	public void cleanCurrent() {
		currentInfraestructuraModelo = new InfraestructuraModelo();
		selectedArea = null;
	}

	public void updateSelected(TreeNode t) {

		InfraestructuraModelo actm = (InfraestructuraModelo) t.getData();

		if (actm.getIdInfraModel() == null ||actm.getIdInfraModel() == 0) {
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
					InfraestructuraModelo at = new InfraestructuraModelo();
					at.setNombre("Root");
					at.setIdInfraModel(0);
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
		InfraestructuraModelo temp = getNodeData(t);
		selectedArea = temp.getIdArea().getIdArea();
		isLevel = temp.getEsNivelAgrup();
		currentInfraestructuraModelo = temp;
		this.add();

	}

	public InfraestructuraModelo getSelectedInfraestructuraModelo() {

		return (InfraestructuraModelo) selectedNode.getData();
	}

	public void setSelectedInfraestructuraModelo(InfraestructuraModelo selectedInfraestructuraModelo) {
		this.selectedInfraestructuraModelo = selectedInfraestructuraModelo;
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

	public void setinfraestructuraModelService(InfraestructuraModelService infraestructuraModelService) {
		this.infraestructuraModelService = infraestructuraModelService;
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


	public InfraestructuraModelo getCurrentInfraestructuraModelo() {
		return currentInfraestructuraModelo;
	}

	public void setCurrentInfraestructuraModelo(InfraestructuraModelo currentInfraestructuraModelo) {
		this.currentInfraestructuraModelo = currentInfraestructuraModelo;
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

	public List<InfraestructuraModelo> getActivolist() {
		return infraList;
	}

	public void setActivolist(List<InfraestructuraModelo> activolist) {
		this.infraList = activolist;
	}

	public List<InfraestructuraModelo> getActivoHijos() {

		List<InfraestructuraModelo> aux = new ArrayList<InfraestructuraModelo>();
		for (TreeNode t : selectedNode.getChildren()) {
			aux.add((InfraestructuraModelo) t.getData());
		}
		return aux;
	}

	private TreeNode getSelectedNode(InfraestructuraModelo selectedAct, TreeNode t) {

		InfraestructuraModelo a = (InfraestructuraModelo) t.getData();
		if (a.getIdInfraModel() == selectedAct.getIdInfraModel()) {
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

	public void setActivoHijos(List<InfraestructuraModelo> activoHijos) {
		this.infraHijos = activoHijos;
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


	public boolean isShowChildren() {
		return showChildren;
	}

	public void setShowChildren(boolean showChildren) {
		this.showChildren = showChildren;
	}
	
	public boolean isSelected(InfraestructuraModelo a) {
		if (a.getInfraestructuraModeloList().contains(getNodeData(selectedNode))) {
			return true;
		}
		return false;
	}

	public InfraestructuraClon getClon() {
		return clon;
	}

	public void setClon(InfraestructuraClon clon) {
		this.clon = clon;
	}

	public void setInfraestructuraModelService(
			InfraestructuraModelService infraestructuraModelService) {
		this.infraestructuraModelService = infraestructuraModelService;
	}

	public void setBreadcumb(Stack<TreeNode> breadcumb) {
		this.breadcumb = breadcumb;
	}

	
	
		 
	 

}
