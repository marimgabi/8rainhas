package eightqueens;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class TreeNode<T> implements Iterable<TreeNode<T>> {

    T data;
    int id, height;
    TreeNode<T> parent;
    List<TreeNode<T>> children;

    public TreeNode(T data, int _id, int height) {
        this.data = data;
        this.id = _id;
        this.children = new LinkedList<TreeNode<T>>();
        this.height = height;
    }

    public TreeNode(T data, int _id) {
        this.data = data;
        this.id = _id;
        this.children = new LinkedList<TreeNode<T>>();
        this.height = 0;
    }

    public T getData() {
        return this.data;
    }

    public TreeNode<T> addChild(T data, int id) {
        TreeNode<T> childNode = new TreeNode<T>(data, id);
        childNode.parent = this;
        childNode.height = this.height + 1;
        this.children.add(childNode);
        return childNode;
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super TreeNode<T>> consumer) {
        Iterable.super.forEach(consumer);
    }

    @Override
    public Spliterator<TreeNode<T>> spliterator() {
        return Iterable.super.spliterator();
    }

}
