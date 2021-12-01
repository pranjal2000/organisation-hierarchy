import java.io.*; 
import java.util.*;

// import javafx.scene.media.SubtitleTrack; 

// Tree node
class Node {
	int id;
 	Node boss;
	int level;
	int balance_factor;
	Node parent;
	Vector<Node> children;
	Node left_child;
	Node right_child;

	Node(int id){
		this.id=id;
		this.boss=null;
		this.level=1;
		this.balance_factor=0;
		this.children= new Vector<Node>();
		this.left_child=null;
		this.right_child=null;
	}
}


public class OrgHierarchy implements OrgHierarchyInterface{

//root nodep
public Node root=null;
int size=0;

public Node searchId(Node root, int id){
	if(root==null||root.id==id)
		return root;
	if(root.id>id){
		return searchId(root.left_child, id);
	}
	return searchId(root.right_child, id);
}
public boolean isEmpty(){
	//your implementation
	return (root==null);
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
} 

public int size(){
	//your implementation
	return size;
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public int level(int id) throws IllegalIDException, EmptyTreeException{
	//your implementation
	Node res = searchId(root, id);
	if(root == null){
		throw new EmptyTreeException("Tree is empty");
	}
	if (res==null){
		throw new IllegalIDException("Id not present");
	}
	return res.level;
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public void hireOwner(int id) throws NotEmptyException{
	//your implementation
	if(root==null){
		root = new Node(id);
		size++;
	}else{
		throw new NotEmptyException("Tree not empty");
	}
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public void LR(Node n1) {
	Node n2 = n1.right_child;
	n1.right_child = n2.left_child;
	if (n2.left_child != null) {
		n2.left_child.parent = n1;
	}
	n2.parent = n1.parent;
	if (n1.parent == null) {
		this.root = n2;
	} else if (n1 == n1.parent.left_child) {
		n1.parent.left_child = n2;
	} else {
		n1.parent.right_child = n2;
	}
	n2.left_child = n1;
	n1.parent = n2;

	//changing balance_factoes
	n1.balance_factor = n1.balance_factor-1;
	if(n2.balance_factor>0){
		n1.balance_factor-=n2.balance_factor;
	}
	
	n2.balance_factor = n2.balance_factor -1;
	if(n1.balance_factor<0){
		n2.balance_factor+=n1.balance_factor;
	}
}


public void RR(Node n1) {
	Node n2 = n1.left_child;
	n1.left_child = n2.right_child;
	if (n2.right_child != null) {
		n2.right_child.parent = n1;
	}
	n2.parent = n1.parent;
	if (n1.parent == null) {
		this.root = n2;
	} else if (n1 == n1.parent.right_child) {
		n1.parent.right_child = n2;
	} else {
		n1.parent.left_child = n2;
	}
	n2.right_child = n1;
	n1.parent = n2;

	// chanoing the balance_factor
	n1.balance_factor = n1.balance_factor+1;
	if(n2.balance_factor<0){
		n1.balance_factor-=n2.balance_factor;
	}
	
	n2.balance_factor = n2.balance_factor +1;
	if(n1.balance_factor>0){
		n2.balance_factor+=n1.balance_factor;
	}
}
public void rotation(Node p) {
	if (p.balance_factor > 0) {
		if (p.right_child.balance_factor < 0) {
			if(root==p){
				root=p.right_child.left_child;
			}
			RR(p.right_child);
			LR(p);
		} else {
			if(root==p){
				root=p.right_child.right_child;
			}
			LR(p);
		}
	} else if (p.balance_factor < 0) {
		if (p.left_child.balance_factor > 0) {
			if(root==p){
				root=p.left_child.right_child;
			}
			LR(p.left_child);
			RR(p);
			
		} else {
			if(root==p){
				root=p.left_child.left_child;
			}
			RR(p);
		}
	}
}

public void balancing(Node child) {
	
	while(child.balance_factor > -2 && child.balance_factor < 2){
		if (child.parent != null) {
				if (child == child.parent.right_child) {
					child.parent.balance_factor += 1;
				}
				if (child == child.parent.left_child) {
					child.parent.balance_factor -= 1;
				} 
				if (child.parent.balance_factor != 0) {
					child=child.parent;
				}
			}
			else break;
	}
	if (child.balance_factor <= -2 || child.balance_factor >= 2) {
		rotation(child);
		return;
	}

	
}
public void insertInTree(Node child){
	Node temp = null;
	Node p= this.root;
	while(p!=null){
		temp=p;
		if(child.id>p.id){
			p=p.right_child;
		} else {
			p=p.left_child;
		}
	}
	child.parent =  temp;
	if(temp==null){
		root = child;
	}else if (child.id>temp.id){
		temp.right_child=child;
	}else{
		temp.left_child=child;
	}
	balancing(child);
	

}
public void inorder(Node temp){
	if(temp==null) return;

	inorder(temp.left_child);
	System.out.println(temp.id);
	inorder(temp.right_child);
}

public Node minimum(Node n) {
	while (n.left_child != null) {
		n = n.left_child;
	}
	return n;
}
public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
	if(root==null){
		throw new EmptyTreeException("Tree is empty");
	}
	Node boss = searchId(root,bossid);
	
	if(boss==null){
		throw new IllegalIDException("Boss not present");
	}else{
		

		Node child = new Node(id);
		child.level=boss.level+1;
		child.boss = boss;
		boss.children.add(child);
		// Node p = root;
		insertInTree(child);
		size++;
		

	}
	//your implementation
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 


public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	// inorder(root);
	if(isEmpty()){
		throw new EmptyTreeException("Tree is empty");
	}
	Node node = searchId(root, id);
	if(node==null){
		throw new IllegalIDException("Id not present");
	}else{
		// System.out.println(node.id);
		Node boss = node.boss;
		Node parent = node.parent;

		//if id is of owner
		if(boss==null){
			throw new IllegalIDException("It is the owner");
		}

		//deleting employee from boss's vector of Nodes
		for(int i=0;i<boss.children.size();i++){
			if(boss.children.get(i).id==node.id){
				Collections.swap(boss.children,boss.children.size()-1,i);
				break;
			}
		}
		
		boss.children.remove(boss.children.size()-1);
		
		//deleting Node node of  id =id 

		//If node has no children
		if (node.left_child == null && node.right_child == null) {
			
			if(node.id<node.parent.id){
				node.parent.left_child=null;
			}else{
				node.parent.right_child=null;
			}
			node = null;
			size--;
			
		}

		//If node has exactly one child
		else if(node.left_child!=null && node.right_child==null||node.left_child==null && node.right_child!=null)
		{
			if(node.parent.id>node.id){
				if(node.left_child!=null && node.right_child==null){
					node.parent.left_child=node.left_child;
					node.left_child.parent=node.parent;
					
					node=null;
					size--;
				}else if(node.left_child==null && node.right_child!=null){
					node.parent.left_child=node.right_child;
					node.right_child.parent=node.parent;
					node=null;
					size--;
				}
			}else if(node.parent.id<node.id){
				if(node.left_child!=null && node.right_child==null){
					node.parent.right_child=node.left_child;
					node.left_child.parent=node.parent;
					node=null;
					size--;
				}else if(node.left_child==null && node.right_child!=null){
					node.parent.right_child=node.right_child;
					node.right_child.parent=node.parent;
					node=null;
					size--;
				}
			}
		}

		//if both children of node are present
		else if(node.left_child!=null && node.right_child!=null){
			Node substitute= minimum(node.right_child);
			node.id = substitute.id;
			node.level=substitute.level;
			node.children=substitute.children;
			// if substitute has no children
			if (substitute.left_child == null && substitute.right_child == null) {
				if(substitute.id<substitute.parent.id){
					substitute.parent.left_child=null;
				}else{
					substitute.parent.right_child=null;
				}
				
				substitute = null;
				size--;
			}
			// if substitute has one child
			else if(substitute.left_child!=null && substitute.right_child==null||substitute.left_child==null && substitute.right_child!=null)
			{
				if(substitute.parent.id>substitute.id){
					if(substitute.left_child!=null && substitute.right_child==null){
						substitute.parent.left_child=substitute.left_child;
						substitute.left_child.parent=substitute.parent;
						substitute=null;
						size--;
					}else if(substitute.left_child==null && substitute.right_child!=null){
						substitute.parent.left_child=substitute.right_child;
						substitute.right_child.parent=substitute.parent;
						substitute=null;
						size--;
					}
				}else if(substitute.parent.id<substitute.id){
					if(substitute.left_child!=null && substitute.right_child==null){
						substitute.parent.right_child=substitute.left_child;
						substitute.left_child.parent=substitute.parent;
						substitute=null;
						size--;
					}else if(substitute.left_child==null && substitute.right_child!=null){
						substitute.parent.right_child=substitute.right_child;
						substitute.right_child.parent=substitute.parent;
						substitute=null;
						size--;
					}
				}
			}
			
		}
		
		while(parent.id!= root.id){

			balancing(parent);
			if(parent.parent!=null)
				parent=parent.parent;
		}
		balancing(root);
		//  inorder(root);



	}
 	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("Tree is empty");
	}
	Node node = searchId(root, id);
	Node boss = node.boss;
	Node parent = node.parent;
	Node manager = searchId(root, manageid);
	if(node==null||manager==null||manager.boss==null||boss==null){
		throw new IllegalIDException("Illegal id frand");
	}
	Vector<Node> employees = node.children;
	//deleting employee from boss's vector of Nodes
	for(int i=0;i<boss.children.size();i++){
		if(boss.children.get(i).id==node.id){
			Collections.swap(boss.children,boss.children.size()-1,i);
			break;
		}
	}
	
	boss.children.remove(boss.children.size()-1);
	// for(int i=0;i<boss.children.size();i++){
	// 	System.out.println(boss.children.get(i).id );
	// }

	//deleting Node node of  id =id 

	//If node has no children
	if (node.left_child == null && node.right_child == null) {
		
		if(node.id<node.parent.id){
			node.parent.left_child=null;
		}else{
			node.parent.right_child=null;
		}
		node = null;
		size--;
		
	}

	//If node has exactly one child
	else if(node.left_child!=null && node.right_child==null||node.left_child==null && node.right_child!=null)
	{
		if(node.parent.id>node.id){
			if(node.left_child!=null && node.right_child==null){
				node.parent.left_child=node.left_child;
				node.left_child.parent=node.parent;
				
				node=null;
				size--;
			}else if(node.left_child==null && node.right_child!=null){
				node.parent.left_child=node.right_child;
				node.right_child.parent=node.parent;
				node=null;
				size--;
			}
		}else if(node.parent.id<node.id){
			if(node.left_child!=null && node.right_child==null){
				node.parent.right_child=node.left_child;
				node.left_child.parent=node.parent;
				node=null;
				size--;
			}else if(node.left_child==null && node.right_child!=null){
				node.parent.right_child=node.right_child;
				node.right_child.parent=node.parent;
				node=null;
				size--;
			}
		}
	}

	//if both children of node are present
	else if(node.left_child!=null && node.right_child!=null){
		Node substitute= minimum(node.right_child);
		node.id = substitute.id;
		node.level=substitute.level;
		node.children=substitute.children;
		// if substitute has no children
		if (substitute.left_child == null && substitute.right_child == null) {
			if(substitute.id<substitute.parent.id){
				substitute.parent.left_child=null;
			}else{
				substitute.parent.right_child=null;
			}
			
			substitute = null;
			size--;
		}
		// if substitute has one child
		else if(substitute.left_child!=null && substitute.right_child==null||substitute.left_child==null && substitute.right_child!=null)
		{
			if(substitute.parent.id>substitute.id){
				if(substitute.left_child!=null && substitute.right_child==null){
					substitute.parent.left_child=substitute.left_child;
					substitute.left_child.parent=substitute.parent;
					substitute=null;
					size--;
				}else if(substitute.left_child==null && substitute.right_child!=null){
					substitute.parent.left_child=substitute.right_child;
					substitute.right_child.parent=substitute.parent;
					substitute=null;
					size--;
				}
			}else if(substitute.parent.id<substitute.id){
				if(substitute.left_child!=null && substitute.right_child==null){
					substitute.parent.right_child=substitute.left_child;
					substitute.left_child.parent=substitute.parent;
					substitute=null;
					size--;
				}else if(substitute.left_child==null && substitute.right_child!=null){
					substitute.parent.right_child=substitute.right_child;
					substitute.right_child.parent=substitute.parent;
					substitute=null;
					size--;
				}
			}
		}
		
	}
	//changing boss for employees of deleted node to person with managerid
	for(int i=0;i<employees.size();i++){
		employees.get(i).boss=manager;
	}
	//adding employees of deleted node under managerid person;
	for(int i=0;i<employees.size();i++){
		manager.children.add(employees.get(i));
	}

	while(parent.id!= root.id){

		balancing(parent);
		if(parent.parent!=null)
			parent=parent.parent;
	}
	balancing(root);



	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(isEmpty()){
		throw new EmptyTreeException("Tree is empty");
	}
	Node res = searchId(root, id);
	if(res==null){
		throw new IllegalIDException("id not present");
	}
	if(res.boss==null)
	{
		return -1;
	}
	return res.boss.id;
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("Tree is empty");
	}
	Node node1 = searchId(root, id1), node2 = searchId(root, id2);
	if(node1==null||node2==null){
		throw new IllegalIDException("ids are not valid");
	}
	if(node1.boss==null|| node2.boss==null){
		return -1;
	}
	int l1 = node1.level, l2=node2.level;
	int difference = l1-l2;
	if(l1>l2){
		while(difference>0)
		{
			difference--;
			node1=node1.boss;
		}
	}else if(l2>l1){
		while(difference<0){
			difference++;
			node2=node2.boss;
		}
	}
	while(node1.boss!=null && node2.boss!=null){
		if(node1.boss==node2.boss){
			break;
		}else{
			node1=node1.boss;
			node2=node2.boss;
		}
	}
	return node1.boss.id;
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public String level_order(Node node){
	if(node == null){
		return "";
	}
	String res = "";
	Vector<Node> que= new Vector<Node>();
	que.add(node);
	
	while(que.isEmpty()==false){
		int n= que.size();
		Vector<Integer> v =new Vector<Integer>();
		while(n-1>0){
			Node temp = que.get(n-1);
			int id = temp.id;
			// res += String.valueOf(id) + " ";
			v.add(id);
			que.remove(n-1);

			for(int i=0;i<temp.children.size();i++){
				que.add(temp.children.get(i));
			}
			n--;
		}
			Node temp = que.get(n-1);
			int id = temp.id;
			// res += String.valueOf(id) ;
			v.add(id);
			que.remove(n-1);

			for(int i=0;i<temp.children.size();i++){
				que.add(temp.children.get(i));
			}
			n--;
			Collections.sort(v);
			for(int i=0;i<v.size()-1;i++){
				res+= String.valueOf(v.get(i))+ " ";
			}
			res+=String.valueOf(v.get(v.size()-1));
			res += ",";

		
	}
	return res.substring(0, res.length()-1);
}
public String toString(int id) throws IllegalIDException, EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("tree is empty");
	}
	Node node =  searchId(root, id);
	if(node==null){
		throw new IllegalIDException("id not present");
	}
	String res = "";
	// preorder(node,res);
	// Vector<Integer> que;
	res = level_order(node);
	return res;
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

}
