package com.system.service;

import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.UbicacionDAO;
import com.system.entities.Ubicacion;

@Service
public class UbicacionService {
	
	@Autowired
	UbicacionDAO ubicacionDAO; 
	
	
	public TreeNode createUbicacionTree() {		
		
        TreeNode root = new DefaultTreeNode(new Ubicacion(), null);
        
        List<Ubicacion> level = ubicacionDAO.getRoots();        
        
       for (Ubicacion u : level) {
			TreeNode x = new DefaultTreeNode(u,root);
		}
       root = this.fillTree(root);            
         
        return root;
    }
	
	public void remove(Ubicacion u) {
		ubicacionDAO.remove(u);
	}
	
	public void update(Ubicacion u) {
		ubicacionDAO.update(u);
	}


	private TreeNode fillTree(TreeNode root) {

		for (TreeNode t : root.getChildren()) {
			Ubicacion u = (Ubicacion)t.getData();
			if(ubicacionDAO.gethijos(u.getIdUbic()).size() > 0 ){
				
				for (Ubicacion ub : ubicacionDAO.gethijos(u.getIdUbic())) {
					
					TreeNode treeN = new DefaultTreeNode(ub,t);
				}
				fillTree(t);
			}
			
		}
		return root;
		
	}
	
	public void add(Integer p,Ubicacion h) {
		ubicacionDAO.add(h, p);
	}

}
