package com.palazares.hackerrank.datastructures;

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void add(int value) {

    }

    public void remove(int value) {
        if (this.value == left.value) {

        }
    }
}

public class BinarySearchTree {
    public Node root;
    public int size;

    public BinarySearchTree(int value) {
        root = new Node(value);
    }

    public void add(int value) {
        root.add(value);
        size++;
    }

    public void remove(int value) {
        root.remove(value);
        size--;
    }

    public double getMedian() {
        if (size % 2 == 1) {
            return root.value;
        }
        return ((double) root.value + root.right.value) / 2.0;
    }
}
