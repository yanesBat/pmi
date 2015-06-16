package com.system.service;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.ActivoDAO;
import com.system.dao.InfraestructuraModelDAO;
import com.system.entities.ActivoClon;
import com.system.entities.ActivoModelo;
import com.system.entities.InfraestructuraClon;
import com.system.entities.InfraestructuraModelo;
import com.system.entities.Ubicacion;

@Service
@ManagedBean
@RequestScoped
public class InfraestructuraModelService {

	@Autowired
	InfraestructuraModelDAO infraestructuraModelDAO;

	public TreeNode createInfraestructuraTree() {

		TreeNode root = new DefaultTreeNode(new InfraestructuraModelo(), null);

		List<InfraestructuraModelo> level = infraestructuraModelDAO.getRoots();

		for (InfraestructuraModelo u : level) {
			TreeNode x = new DefaultTreeNode(u, root);
		}
		root = this.fillTree(root);

		return root;
	}
	
	private TreeNode fillTree(TreeNode root) {

		for (TreeNode t : root.getChildren()) {
			InfraestructuraModelo u = (InfraestructuraModelo)t.getData();
			if(infraestructuraModelDAO.gethijos(u.getIdInfraModel()).size() > 0 ){
				
				for (InfraestructuraModelo ub : infraestructuraModelDAO.gethijos(u.getIdInfraModel())) {
					
					TreeNode treeN = new DefaultTreeNode(ub,t);
				}
				fillTree(t);
			}
			
		}
		return root;
		
	}

	public void remove(InfraestructuraModelo u) {
		infraestructuraModelDAO.remove(u);
	}
	
	public void update(InfraestructuraModelo u) {
		infraestructuraModelDAO.update(u);
	}
	
	public void add(Integer p,InfraestructuraModelo h) {
		infraestructuraModelDAO.add(h, p);
	}
	
	public List<InfraestructuraClon> getAllClon() {		
		return infraestructuraModelDAO.getAllClon();
	}
	
	public InfraestructuraClon getClonById(Integer id) {

		return infraestructuraModelDAO.getClonById(id);
	}

	public List<InfraestructuraClon> getClonList(InfraestructuraModelo am) {
		return infraestructuraModelDAO.getClonList(am);
	}
		
	public void updateClon(InfraestructuraClon u) {
		infraestructuraModelDAO.updateClon(u);
	}
		
	public void addClon(InfraestructuraClon ac) {
		infraestructuraModelDAO.addClon(ac);
	}

	public InfraestructuraModelo getById(int id) {
		return this.infraestructuraModelDAO.getById(id);
	}
	
	public void setInfraestructuraModelDAO(InfraestructuraModelDAO infraestructuraModelDAO) {
		this.infraestructuraModelDAO = infraestructuraModelDAO;
	}

	
}
