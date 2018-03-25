#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

enum color{RED,BLACK};
int numberOfvalues;
struct node
{
	int key;
	int count;
	enum color colour;
	struct node *left,*right,*parent;

};
struct node *nil;
struct node *root;
struct node* newNode(int key){
	struct node *temp= (struct node *)malloc(sizeof(struct node));
	temp->key=key;
	temp->count=0; //for duplicates
	temp->left=temp->right=temp->parent=nil;
	temp->colour=RED;

}

void inorder(struct node *root){
	if(root!=nil){
		inorder(root->left);
		printf("%d %d \n",root->key,root->colour);
		inorder(root->right);
	}
}
void leftRotate(struct node* temp){
	struct node *y=temp->right;
	if(temp->parent==nil){
		root=y;
	}//root imp
	else if(temp==temp->parent->left){
		temp->parent->left=y;
	}
	else{
		temp->parent->right=y;
	}
	y->parent=temp->parent;
	temp->parent=y;
	temp->right=y->left;

	if(y->left!=nil){
		y->left->parent=temp;
	}//imp
	y->left=temp;//rotation from cormen
}
void rightRotate(struct node *temp){
	struct node *y=temp->left;
	if(temp->parent==nil){
		root=temp->left;
	}//root imp
	else if(temp==temp->parent->left){
		temp->parent->left=temp->left;
	}
	else{
		temp->parent->right=temp->left;
	}

	y->parent=temp->parent;
	temp->parent=y;
	temp->left=y->right;

	if(y->right!=nil){
		y->right->parent=temp;
	}//imp
	y->right=temp;//rotation from cormen


}
void insertFixUp(struct node* temp){
	struct node* uncle;
	struct node* curr=temp;
	while(curr->parent->colour==RED){
		root->colour=BLACK;//exception at the root  rule 2
		if(curr->parent==curr->parent->parent->left){
			uncle=curr->parent->parent->right;
			if(uncle->colour==RED){
				uncle->colour=BLACK;
				curr->parent->colour=BLACK;
				curr->parent->parent->colour=RED;
				curr=curr->parent->parent;

			}// end of caase 1
			else{
					if(curr==curr->parent->right){
							curr=curr->parent;
							leftRotate(curr);

					}
					rightRotate(curr->parent->parent);
					curr->parent->right->colour=RED;
					curr->parent->colour=BLACK;
			}//case 2 and 3
		}//end of if parent is the left child
		else{
			uncle=curr->parent->parent->left;
			if(uncle->colour==RED){
				uncle->colour=BLACK;
				curr->parent->colour=BLACK;
				curr->parent->parent->colour=RED;
				curr=curr->parent->parent;

			}// end of caase 1
			else{
				if(curr==curr->parent->left){
							curr=curr->parent;
							rightRotate(curr);


					}
					//printf("%dhere ",curr->parent->parent->key);
					leftRotate(curr->parent->parent);
					curr->parent->left->colour=RED;
					curr->parent->colour=BLACK;
			}//case2 and 3

		}//end of else

	}
	root->colour=BLACK;//violation of property 2
}
void inserttree(struct node *curr){
	int flag=0;
	struct node *temp=root;
	struct node *parent=nil;
	if(root==nil){
		root=curr;
	}
	else{
	while(temp!=nil){
		parent=temp;
		if(curr->key==temp->key){
			temp->count++;
			temp=nil;
			flag=1;
			//return curr;
		}
		else if(curr->key<temp->key){
			temp=temp->left;
		}
		else{
			temp=temp->right;
		}

	}// end of while
	if(flag!=1){
	curr->parent=parent;
	if(curr->key<parent->key){
		parent->left=curr;
	}
	else{
		parent->right=curr;
	}}}
	if(flag!=1){
	insertFixUp(curr);}
	//root->colour=BLACK;
	//return curr;

}
struct node* findInTree(int key,struct node *temproot){
	if(temproot!=nil){
		if(temproot->key==key){
			return temproot;
		}
		else if(temproot->key<key){
			findInTree(key,temproot->right);
		}
		else{
			findInTree(key,temproot->left);
		}
	}
	else{
		return nil;
	}
}
/*struct node* modifiedfindInTree(int key,struct node *temproot){
	if(temproot==nil){
		printf("invalid key");
		return nil;
	}
	else{
		if(temproot->key<key){
			temproot->left
		}
	}
}*/
struct node *findInorderSuccesor(struct node*temp){
	while(temp->left!=nil){
		temp=temp->left;
	}
	return temp;
}
//for replacing a node changing the father-son relationship only 
void rb_transplant(struct node* keyNode,struct node *replaceNode){
	if(keyNode->parent==nil){
		root=replaceNode;

	}
	if(keyNode==keyNode->parent->left){
		keyNode->parent->left=replaceNode;
	
	}
	else{
		keyNode->parent->right=replaceNode;
	}
		replaceNode->parent=keyNode->parent;
		
}
void rb_Delete(struct node *temp){
	struct node *y=temp;
	struct node*x;
	enum color y_origcolour=temp->colour;//vvi
	//if node is a leaf or has just one children
	if(temp->left==nil){
		x=temp->right;
		rb_transplant(temp,temp->right);

	}
	else if(temp->right==nil){
		x=temp->left;
		rb_transplant(temp,temp->left);
	}
	else{
		//if temp has both of its children

		y=findInorderSuccesor(temp->right);
		y_origcolour=y->colour;//vvi
		x=y->right;
		if(y->parent!=temp){
			rb_transplant(y,y->right);
			y->right=temp->right;//the inorder successor is the right child
			y->right->parent=y;

		}//if the inordersuccessor has right child
		else{
			x->parent=y;
		}
		rb_transplant(temp,y);//changing the parents
		y->left=temp->left;
		y->left->parent=y;
		y->colour=temp->colour;


	}
	if (y_origcolour == 1){
		rb_delete_fixup(x);
	}


	free(temp);

}
rb_delete_fixup(struct node *x){
	struct node* w;
	while ((x!=root)&& x->colour==1){
			root->colour=1;
		if (x==x->parent->left){
			root->colour=1;
			w=x->parent->right;
			if(w->colour==0){
				w->colour=1;
				x->parent->colour=0;
				leftRotate(x->parent);
				w=x->parent->right;
				root->colour=1;
			}//if w
			if((w->left->colour == 1) && (w->right->colour ==1)){
				w->colour=0;
				x=x->parent;
				root->colour=1;
			}// if w
			else if (w->right->colour == 1){
					w->left->colour=1;
					w->colour=0;
					rightRotate(w);
					w=x->parent->right;
					root->colour=1;}
			else{
				w->colour=x->parent->colour;
				x->parent->colour = 1;
				w->right->colour = 1;
				leftRotate(x->parent);
				root->colour=1;
				x=root;
				root->colour=1;}


		}//if x=x->parent
		else{
				root->colour=1;
		if (x==x->parent->right){
			root->colour=1;
			w=x->parent->left;
			if(w->colour==0){
				w->colour=1;
				x->parent->colour=0;
				rightRotate(x->parent);
				w=x->parent->left;
				root->colour=1;
			}//if w
			if((w->right->colour == 1) && (w->left->colour ==1)){
				w->colour=0;
				x=x->parent;
				root->colour=1;
			}// if w
			else if (w->left->colour == 1){
				w->right->colour=1;
				w->colour=0;
				leftRotate(w);
				w=x->parent->left;
				root->colour=1;}
			else{
				w->colour=x->parent->colour;
				x->parent->colour = 1;
				w->left->colour = 1;
				rightRotate(x->parent);
				root->colour=1;
				x=root;
				root->colour=1;}
			}
		}//else

	}//while
	x->colour=1;
}//sunction

void deletetree(int key){
	struct node *temp=findInTree(key,root);
	if(temp==nil){
		printf("invalid key or all the nodes already deleted");
	}
	else{
		rb_Delete(temp);
	}

}
int main(){
	int i=0,next,exit=0,opt;
	nil=newNode(-111111);
	nil->colour=BLACK;
	root= nil;
	printf("enter the number of key values");
	scanf("%d",&numberOfvalues);
	for(i=1;i<=numberOfvalues;i++){
		printf("enter the %dth key value",i);
		scanf("%d",&next);
		inserttree(newNode(next));
		inorder(root);
		printf("\n\n\n");
	}
	while(exit==0){
		int key;
		printf("1.)insert a node\n2.)delete a node\n3.exit\n");
		scanf("%d",&opt);
		switch(opt){
			case 1:
					printf("enter the key value of the node:\n");
					scanf("%d",&key);
					inserttree(newNode(key));
					inorder(root);
					break;
			case 2: 
					printf("enter the key value of the node to be deleted\n");
					scanf("%d",&key);
					deletetree(key);
					inorder(root);
					break;
			case 3:
					exit=1;
					break;
			default:
					printf("wrong input,try again");
					break;
		}
	}
	
	return 0;
}