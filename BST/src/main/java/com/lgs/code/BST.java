package com.lgs.code;

/**
 * @author ：李先生
 * @date ：Created in 2019/8/18 11:37
 * @description：二分搜索树的实现类
 * @modified By：
 * @version: $
 */
//这里使用泛型 因为二分搜索树必须是可比较的 所以E继承Comparable<E>（可比较接口)
public class BST<E extends Comparable<E>> {

    //内部类 实现结点
     class Node{

        public E e;//
        public Node left,right;//左右孩子
        //构造函数 创建结点
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }//end class Node

    private Node root;//根节点
    private int size;//用来记录当前树的个数
    //构造函数 创建树的根节点（相当于初始化）
    public BST(){
        root = null;
        size = 0;
    }
    //获取元素个数
    public int getSize(){
        return size;
    }
    //判断是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    /* 1.0版
    //向二分搜索树种添加新的元素e
    public void add(E e){
        if(root == null){
            root = new Node(e);
             size++;
        }else
            add(root,e);
    }
    */

    /* 1.0版
    //向以root为根的二分搜索树中添加新的元素e，递归算法
    private void add(Node node,E e){
        //先对root（根节点）进行一系列判断和相应操作
        if(node.e == e)
            return;
        else if(e.compareTo(node.e)<0 && node.left==null){//因为元素的e类型不是基础类型 所以不能直接进行比较
            //因为我们该类的E继承自Comparable接口 所以可以直接调用compareTo方法进行比较大小
            node.left = new Node(e);
            size++;
            return;
        }
        else if(e.compareTo(node.e)>0 && node.right==null){
            node.right = new Node(e);
            size++;
            return;
        }
        //调用递归函数
        if(e.compareTo(node.e)<0)
            add(node.left,e);
        else //e.compareTo(node.e)>0
            add(node.right,e);
    }
    */

    //向二分搜索树种添加新的元素e 升级版
    public void add(E e){
        root = add(root,e);
    }

    //递归算法 升级版
    public Node add(Node node ,E e){
        //判传入的节点是否为空 空则创建新的节点
        if(node == null){
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e)<0)
            node.left = add(node.left,e);
        else if(e.compareTo(node.e)>0)
            node.right = add(node.right,e);

        return node;
    }

    //看二分搜索树中是否包含元素e
    public boolean contains(E e){
        return contains(root,e);
    }

    //看二分搜索树中是否包含元素e, 递归算法
    public boolean contains(Node node,E e){
        if(node == null)
            return false;

        if(e.compareTo(node.e)==0)
            return true;
        else if(e.compareTo(node.e)<0)
            return contains(node.left,e);
        else
            return contains(node.right,e);
    }

    //二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }
    //二分搜索树的前序遍历具体实现，递归调用
    private void preOrder(Node node){
        if(node == null)
            return;
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //二分搜索树的中序遍历
    public void inOrder(){
        inOrder(root);
    }
    //二分搜索树的中序遍历具体实现，递归调用
    private void inOrder(Node node){
        if(node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //二分搜索树的后序遍历
    public void postOrder(){
        postOrder(root);
    }
    //二分搜索树的后序遍历具体实现，递归调用
    private void postOrder(Node node){
        if(node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    //生成以node为根节点，depth为深度的二分搜索树的描述
    private void generateBSTString(Node node,int depth,StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth)+"NULL\n");
            return;
        }

        res.append(generateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);//递归遍历其结点的左子树
        generateBSTString(node.right,depth+1,res);//递归遍历其结点的右子树
    }

    //遍历其深度，并打印输出
    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

}//end class com.lgs.code.BST
