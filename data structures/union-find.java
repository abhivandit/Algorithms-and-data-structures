/**
*	Name: Abhivandit (abhivandit@gmail.com)
*	Institute: Indian Institute of Information Technology Guwahati 
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class TestClass{
	int numberOfNodes;
	int numofedges;
    int i;
	static int parent[];
	static int rank[];
	static int setsize[];
	static ArrayList<Integer> setofroots=new ArrayList<Integer>();
 ArrayList<Integer> setofrootstemp=new ArrayList<Integer>();
	TestClass(int numberOfNodes,int numofedges){
		this.numofedges=numofedges;
		this.numberOfNodes=numberOfNodes;
        i=0;
	}
	public int find(int x){
		int a=x;
		//finding the root of x
		while(parent[a]!=a)a=parent[a];
		int b=x,c;
		//path compression
		while(parent[b]!=b){
			c=parent[b];
			parent[b]=a;//path getting compressed
			b=c;
		}
		return a;

	}
	public void unionofelements(int next1,int next2){
		int root1=find(next1);
		int root2=find(next2);
       // System.out.println(root1+" "+root2);
		if(root1!=root2){
			if(rank[root1]>rank[root2]){
				parent[root2]=root1;
				setsize[root1]+=setsize[root2];
				setofroots.set(root2,(-1));
				setofroots.set(root1,setsize[root1]);
				setofrootstemp.set(root2,(-1));
				setofrootstemp.set(root1,setsize[root1]);
			}
			else if(rank[root2]==rank[root1]){
				parent[root2]=root1;
				setsize[root1]+=setsize[root2];//union by rank
				rank[root1]++;
				setofroots.set(root2,(-1));
				setofroots.set(root1,setsize[root1]);
				setofrootstemp.set(root2,(-1));
				setofrootstemp.set(root1,setsize[root1]);
			}
			else{
				parent[root1]=root2;
				
				setsize[root2]+=setsize[root1];
					setofroots.set(root1,(-1));
				setofroots.set(root2,setsize[root2]);
				setofrootstemp.set(root1,(-1));
				setofrootstemp.set(root2,setsize[root2]);
			}

		}
        //System.out.println(setofroots.get(root1)+" "+setofrootstemp.get(root2));

	}
	public void printconnected(int next1,int next2){
        //System.out.println(next1+" "+next2);
		for(i=0;i<=numberOfNodes;i++){
			setofrootstemp.add(setofroots.get(i));
		}
        /*for(i=0;i<parent.length;i++){
			System.out.println(setofrootstemp.get(i));
		}*/
       
        
			unionofelements(next1,next2);
        /* for(i=0;i<=numberOfNodes;i++){
			System.out.println(setofrootstemp.get(i));
		}*/
			Collections.sort(setofrootstemp);
		for(i=0;i<=numberOfNodes;i++){
			int current=setofrootstemp.get(i);
			if(current>=1){
				System.out.print(current+" ");
			}
		}
		System.out.println();
        setofrootstemp.clear();




	}
	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	int numberOfNodes=sc.nextInt();
	 int numofedges=sc.nextInt();
	parent=new int[numberOfNodes+1];
	rank=new int[numberOfNodes+1];
	setsize=new int[numberOfNodes+1];
      parent[0]=(-1);
        setsize[0]=(-1);
        setofroots.add((-1));
        rank[0]=(-1);
	for(int i=1;i<=numberOfNodes;i++){
        
			parent[i]=i;rank[i]=0;
			setsize[i]=1;
			setofroots.add(1);
			
	}
	TestClass sets=new TestClass(numberOfNodes,numofedges);
	for(int j=0;j<numofedges;j++){
		int next1=sc.nextInt();
		int next2=sc.nextInt();
		sets.printconnected(next1,next2);
	}

	
	}
}
