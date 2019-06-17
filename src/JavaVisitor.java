import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int sum = 0;

    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
        //do nothing
    }

    public void visitLeaf(TreeLeaf leaf) {
        sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private long product = 1;

    public int getResult() {
        return (int) product;
    }

    public void visitNode(TreeNode node) {
        if(node.getColor() == Color.RED) {
            int value = node.getValue();
            if(value != 0){
                product = (product * value) % 1000000007;
            }
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.RED) {
            int value = leaf.getValue();
            if(value != 0){
                product = (product * value) % 1000000007;
            }
        }
    }
}

class FancyVisitor extends TreeVis {
    private int sum = 0;

    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
        if(node.getDepth() % 2 == 0) {
            sum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.GREEN) {
            sum -= leaf.getValue();
        }
    }
}

public class JavaVisitor {

    public static Tree solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        TreeNode[] nodes = new TreeNode[n];
        int[] values = new int[n];
        Color[] colors = new Color[n];

        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            values[i] = val;
        }

        for (int i = 0; i < n; i++) {
            int color = scanner.nextInt();
            colors[i] = colorFromInt(color);
        }

        nodes[0] = new TreeNode(values[0], colors[0], 0);

        while(scanner.hasNextInt()) {
            int first = scanner.nextInt() - 1;
            int second = scanner.nextInt() - 1;
            TreeNode firstNode = nodes[first];
            if(nodes[second] == null){
                nodes[second] =
                        new TreeNode(values[second], colors[second], firstNode.getDepth() + 1);
            }
            firstNode.addChild(nodes[second]);
        }
        scanner.close();

        return nodes[0];
    }

    static Color colorFromInt(int color){
        if (color == 0) return Color.RED;
        return Color.GREEN;
    }


    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}