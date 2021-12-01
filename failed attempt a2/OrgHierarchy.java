import java.io.*; 
import java.util.*; 

class  ListNode{

};
// Tree node
class Node {
  int id; 
  int depth; // level of node
  Node parent;// boss node of curr node
  Vector<Node> children;
  
  Node(int id){
	this.parent=null;
	this.children = new Vector<Node>();
	this.id=id;
	this.depth=1;
  }
  
};


public class OrgHierarchy implements OrgHierarchyInterface{

//root node
Node root=null;
int size = 0;

public boolean isEmpty(){
	//your implementation
	return size==0;

	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
} 

public int size(){
	//your implementation
	return size;
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public int level(int id) throws IllegalIDException, EmptyTreeException{
	//your implementation
	
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public void hireOwner(int id) throws NotEmptyException{
	//your implementation
	if(root==null){
		root = new Node(id);
		root.depth = 1;
		root.children = null;
	}else{
		throw new NotEmptyException("Tree not empty");
	}
	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
	//your implementation
	//search id 
	//if present throw new IllegalIDException("ID already present");
	//else if(root == null){ throw new EmptyTreeException("Tree is empty");}
	//else insert

	//  throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	if(isEmpty()){
		throw new EmptyTreeException("Tress is Empty"); 
	}
	Node p= root;
	while(p!=null){
		
		if(p.ids.get(0)>bossid){
			p=p.children.get(0);
		}
		if(p.ids.get(p.n-1)<bossid){
			p=p.children.get(p.n);
		}
		if(p.ids.get(p.n-1)==bossid){
			if(id<bossid){
				p.children.get(p.n).ids.add(id);
				p.children.get(p.n).level=p.level+1;
				p.children.get(p.n).n += 1;
				size++;
			}
			else if(id>bossid){
				p.children.get(p.n).ids.add(id);
				p.children.get(p.n).level=p.level+1;
				p.children.get(p.n).n += 1;
				size++;
			}
			Collections.sort(p.children.get(p.n).ids);
			break;
		}
		for(Integer i=0;i<p.n-1;i++){
			if(p.ids.get(i)==bossid){
				if(id<bossid){
					p.children.get(i).ids.add(id);
					p.children.get(i).level=p.level+1;
					p.children.get(i).n += 1;
					size++;
				}
				else if(id>bossid){
					p.children.get(i+1).ids.add(id);
					p.children.get(i+1).level=p.level+1;
					p.children.get(i+1).n += 1;
					size++;
				}
				Collections.sort(p.children.get(i).ids);
				break;
			}
			if(p.ids.get(i)<bossid && bossid<p.ids.get(i+1)){
				p=p.children.get(i+1);
			}
		}
	}
	if(p == null){
		throw new IllegalIDException("boss id not present");
	} 

} 

public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(isEmpty()){
		throw new EmptyTreeException("Tress is Empty"); 
	}
	Node p= root;
	while(p!=null){
		
		if(p.ids.get(0)>id){
			p=p.children.get(0);
		}
		if(p.ids.get(p.n-1)<id){
			p=p.children.get(p.n);
		}
		if(p.ids.get(p.n-1)==id){
			p.ids.remove(p.n-1);
			break;
		}
		for(Integer i=0;i<p.n-1;i++){
			if(p.ids.get(i)==id){
				Collections.swap(p.ids,0,p.n-1);

				break;
			}
			if(p.ids.get(i)<id && id<p.ids.get(i+1)){
				p=p.children.get(i+1);
			}
		}
	}
	if(p == null){
		throw new IllegalIDException("boss id not present");
	} 
 	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	//your implementation
	 throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	 throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
	//your implementation
	 throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public String toString(int id) throws IllegalIDException, EmptyTreeException{
	//your implementation
	 throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

}
