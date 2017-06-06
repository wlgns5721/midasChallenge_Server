package midas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wonjerry
 */
public class SideBarPanel extends javax.swing.JPanel {

	/**
	 * Creates new form SideBarPanel
	 */
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton addClassBtn;
	private javax.swing.JButton addDependencyArrowBtn;
	private javax.swing.JButton addExtendsArrowBtn;
	private javax.swing.JPanel drawClassPanel;
	private javax.swing.JPanel drawArrowPanel;
	private javax.swing.JPanel viewDocumentPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTree jTree1;
	// End of variables declaration//GEN-END:variables

	public SideBarPanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		drawClassPanel = new javax.swing.JPanel();
		addClassBtn = new javax.swing.JButton();
		drawArrowPanel = new javax.swing.JPanel();
		addDependencyArrowBtn = new javax.swing.JButton();
		addExtendsArrowBtn = new javax.swing.JButton();
		viewDocumentPanel = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTree1 = new javax.swing.JTree();

		setPreferredSize(new java.awt.Dimension(300, 933));

		drawClassPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		// addClassBtn.setText("addClassBtn");
		ImageIcon classIcon = new ImageIcon(System.getProperty("user.dir") + "\\images\\classIcon2.png");
		addClassBtn.setIcon(classIcon);
		addClassBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 액션리스너 달아야
				// addClassBtn.setText("Class");
				// addClassBtn.repaint();
				MainFrame.mode = "Class";
			}

		});

		javax.swing.GroupLayout drawClassPanelLayout = new javax.swing.GroupLayout(drawClassPanel);
		drawClassPanel.setLayout(drawClassPanelLayout);
		drawClassPanelLayout
				.setHorizontalGroup(drawClassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(drawClassPanelLayout.createSequentialGroup().addContainerGap()
								.addComponent(addClassBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
								.addContainerGap()));
		drawClassPanelLayout
				.setVerticalGroup(
						drawClassPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(drawClassPanelLayout.createSequentialGroup().addContainerGap()
										.addComponent(addClassBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(238, Short.MAX_VALUE)));

		drawArrowPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		// addDependencyArrowBtn.setText("addDependencyArrowBtn");
		ImageIcon dependencyArrowIcon = new ImageIcon(System.getProperty("user.dir") + "\\images\\dependencyarrow.png");
		addDependencyArrowBtn.setIcon(dependencyArrowIcon);
		addDependencyArrowBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 액션리스너 달아야
				// addDependencyArrowBtn.setText("Dependency");
				// addDependencyArrowBtn.repaint();
				MainFrame.mode = "Dependency";
			}

		});

		// addExtendsArrowBtn.setText("addExtendsArrowBtn");
		ImageIcon extendsArrowIcon = new ImageIcon(System.getProperty("user.dir") + "\\images\\extendsarrow.png");
		addExtendsArrowBtn.setIcon(extendsArrowIcon);

		addExtendsArrowBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 액션리스너 달아야
				// addExtendsArrowBtn.setText("Extends");
				// addExtendsArrowBtn.repaint();
				MainFrame.mode = "Extends";
			}

		});

		javax.swing.GroupLayout drawArrowPanelLayout = new javax.swing.GroupLayout(drawArrowPanel);
		drawArrowPanel.setLayout(drawArrowPanelLayout);
		drawArrowPanelLayout.setHorizontalGroup(drawArrowPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(drawArrowPanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(drawArrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(addExtendsArrowBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(addDependencyArrowBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		drawArrowPanelLayout
				.setVerticalGroup(
						drawArrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(drawArrowPanelLayout.createSequentialGroup().addContainerGap()
										.addComponent(addDependencyArrowBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(addExtendsArrowBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(162, Short.MAX_VALUE)));

		viewDocumentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		DefaultMutableTreeNode root;
		root = new DefaultMutableTreeNode("Documents");// 다큐멘트 이름
		jTree1 = new JTree(root);
		jScrollPane1.setViewportView(jTree1);

		javax.swing.GroupLayout viewDocumentPanelLayout = new javax.swing.GroupLayout(viewDocumentPanel);
		viewDocumentPanel.setLayout(viewDocumentPanelLayout);
		viewDocumentPanelLayout.setHorizontalGroup(viewDocumentPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1));
		viewDocumentPanelLayout
				.setVerticalGroup(viewDocumentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(drawClassPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(drawArrowPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(viewDocumentPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(drawClassPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(drawArrowPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(viewDocumentPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}// </editor-fold>//GEN-END:initComponents

	public void refreshTree(PanelInformation info) {
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)jTree1.getModel().getRoot();
		
		String DocumentName = info.getDocumentName();
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(DocumentName);
		
		// 탐색 후 tree에 다 담아둠 
		for(ClassObject classes : info.getClassList()){
			
			DefaultMutableTreeNode classnode = new DefaultMutableTreeNode(classes.getClassName());
			
			System.out.println("in refresh Tree" + node.getUserObject());
			for(String attr : classes.getAttributes()){
				classnode.add(new DefaultMutableTreeNode(attr));
			}
			for(String operation : classes.getOperations()){
				classnode.add(new DefaultMutableTreeNode(operation));
			}
			node.add(classnode);
		}
		root.add(node);
		jTree1 = new JTree(root);
		jScrollPane1.setViewportView(jTree1);
		jScrollPane1.repaint();

	}
	
	private DefaultMutableTreeNode getSearchNodes(DefaultMutableTreeNode root, String document) {
        List<DefaultMutableTreeNode> searchNodes = new ArrayList<DefaultMutableTreeNode>();

        Enumeration<?> e = root.preorderEnumeration();
        while(e.hasMoreElements()) {
        	if(((DefaultMutableTreeNode)e.nextElement()).getUserObject().equals(document)){
        		return (DefaultMutableTreeNode)e.nextElement();
        	}
        }
        return null;
    }

	public boolean deleteNode(String document , String searchString) {//노드 받아서 지울꺼 

		DefaultMutableTreeNode root = (DefaultMutableTreeNode)jTree1.getModel().getRoot();
		DefaultMutableTreeNode searchNodes = getSearchNodes(root ,document);
        if(searchNodes == null) return false;
        Enumeration<?> e = searchNodes.preorderEnumeration();
        while(e.hasMoreElements()) {
        	if(((DefaultMutableTreeNode)e.nextElement()).getUserObject().equals(searchString)){
        		searchNodes.remove((DefaultMutableTreeNode)e.nextElement());
        		jTree1 = new JTree(root);
        		jScrollPane1.setViewportView(jTree1);
        		jScrollPane1.repaint();
        		
        		return true;
        	}
        }
        return false;
       
    }
	
	public boolean addNode(PanelInformation info) {//노드 받아서 지울꺼 

		DefaultMutableTreeNode root = (DefaultMutableTreeNode)jTree1.getModel().getRoot();
		DefaultMutableTreeNode searchNodes = getSearchNodes(root ,info.getDocumentName());
		if(searchNodes == null) return false;
		searchNodes.removeFromParent();
        refreshTree(info);
        return true;
       
    }
	// TODO modify 정보 로딩해야됨 
}
