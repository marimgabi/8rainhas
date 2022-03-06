package eightqueens;

public class Tree<T> {
    TreeNode<T> root;
    int size;

    public Tree(T data)  {
        root = new TreeNode<T>(data, 1, 1);
        size = 1;
    }

    @Override
    public String toString() {
        printTree(root, 0);
        return " ";
    }

    public void printTree(TreeNode<T> node, int tabNumber){
        String aux = "";
        for(int i=0; i<tabNumber; i++){
            aux=aux+"\t";
        }
        aux=aux+"|\n";
        for(int i=0; i<tabNumber; i++){
            aux=aux+"\t";
        }
        //System.out.println(node.data.toString());
        aux=aux+"|_\n"+node.data.toString();
        System.out.println(aux);
        tabNumber++;
        if(!node.children.isEmpty()){
            for(TreeNode<T> child : node.children){
                printTree(child, tabNumber);
            }
        }else
            return ;
    }

    public TreeNode<T> find(T data) {
        boolean[] isVisited = new boolean[size];
        return traversePreOrder(root, isVisited, data);
    }

    public TreeNode<T> traversePreOrder(TreeNode<T> node, boolean[] isVisited, T data) {
        if(node.data==data){
            return node;
        }
        if (node != null) {
            isVisited[node.id-1] = true;
            for (TreeNode<T> n: node.children) {
                if (n.data == data) return n;
                else traversePreOrder(n, isVisited, data);
            }
        }
        return null;
    }

    public void dfs(TreeNode<T> start) {
        boolean[] isVisited = new boolean[size];
        dfsRecursive(start, isVisited);
    }

    private void dfsRecursive(TreeNode<T> current, boolean[] isVisited) {
        isVisited[current.id] = true;
        for (TreeNode<T> dest : current.children) {
            if (!isVisited[dest.id])
                dfsRecursive(dest, isVisited);
        }
    }

    public int getNodeHeight(T data){
        boolean[] isVisited = new boolean[size];
        return getHeightAux(root, isVisited, data, 0);
    }

    private int getHeightAux(TreeNode<T> node, boolean[] isVisited, T data, int height) {
        if(node.data==data){
            return height;
        }
        if (node != null) {
            isVisited[node.id-1] = true;
            for (TreeNode<T> n: node.children) {
                if (n.data == data) return height+1;
                else getHeightAux(n, isVisited, data, height+1);
            }
        }
        return -1;
    }

    public TreeNode<T> addChild(TreeNode<T> parent, T data) {
        size++;
        TreeNode<T> node = parent.addChild(data, size);
        return node;

    }

    public TreeNode<T> getRoot() {
        return root;
    }


}

