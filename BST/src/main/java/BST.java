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

    //向二分搜索树种添加新的元素e
    public void add(E e){
        if(root == null){
            root = new Node(e);
             size++;
        }else
            add(root,e);
    }

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

}//end class BST
