package com.system.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.ActivoDAO;
import com.system.entities.ActivoClon;
import com.system.entities.ActivoModelo;
import com.system.entities.Ubicacion;

@Service
@ManagedBean
@RequestScoped
public class ActivoService implements Serializable {

	@Autowired
	ActivoDAO actDAO;

	public TreeNode createActivoTree() {

		TreeNode root = new DefaultTreeNode(new ActivoModelo(), null);

		List<ActivoModelo> level = actDAO.getRoots();

		for (ActivoModelo u : level) {
			TreeNode x = new DefaultTreeNode(u, root);
		}
		root = this.fillTree(root);

		return root;
	}
	
	private TreeNode fillTree(TreeNode root) {

		for (TreeNode t : root.getChildren()) {
			ActivoModelo u = (ActivoModelo)t.getData();
			if(actDAO.gethijos(u.getIdActModel()).size() > 0 ){
				
				for (ActivoModelo ub : actDAO.gethijos(u.getIdActModel())) {
					
					TreeNode treeN = new DefaultTreeNode(ub,t);
				}
				fillTree(t);
			}
			
		}
		return root;
		
	}
	
	public void remove(ActivoModelo u) {
		actDAO.remove(u);
	}
	
	public void update(ActivoModelo u) {
		actDAO.update(u);
	}
	
	public void add(Integer p,ActivoModelo h) {
		actDAO.add(h, p);
	}
	
	
	
	
	
	
	public List<ActivoClon> getAllClon() {		
		return actDAO.getAllClon();
	}
	
	public ActivoClon getClonById(Integer id) {

		return actDAO.getClonById(id);
	}

	public List<ActivoClon> getClonList(ActivoModelo am) {
		return actDAO.getClonList(am);
	}
		
	public void updateClon(ActivoClon u) {
		actDAO.updateClon(u);
	}
		
	public void addClon(ActivoClon ac) {
		actDAO.addClon(ac);
	}

	public ActivoModelo getById(int id) {
		return this.actDAO.getById(id);
	}

	
	
	
	
	
	public void setActDAO(ActivoDAO actDAO) {
		this.actDAO = actDAO;
	}
	
	
	
}
