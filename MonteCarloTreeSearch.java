import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * Monte Carlo Tree Search (MCTS) is a heuristic search algorithm used for
 * decision-making problems, especially in games.
 *
 * See more: https://en.wikipedia.org/wiki/Monte_Carlo_tree_search,
 * https://www.baeldung.com/java-monte-carlo-tree-search
 */
public class MonteCarloTreeSearch {

    public class TreeNode {
        TreeNode parentNode;
        ArrayList<TreeNode> childNodes;
        boolean isPlayerTurn;
        boolean hasPlayerWon;
        int score;
        int visitCount;

        public TreeNode() {
        }

        public TreeNode(TreeNode parentNode, boolean isPlayerTurn) {
            this.parentNode = parentNode;
            childNodes = new ArrayList<>();
            this.isPlayerTurn = isPlayerTurn;
            hasPlayerWon = false;
            score = 0;
            visitCount = 0;
        }
    }

    static final int WIN_SCORE = 10;
    static final int TIME_LIMIT = 500; // Time the algorithm runs (in milliseconds).

    public static void main(String[] args) {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of child nodes to expand from the root node:");
        int childCount = scanner.nextInt();

        mcts.monteCarloTreeSearch(mcts.new TreeNode(null, true), childCount);

        scanner.close();
    }

    /**
     * Explores a game tree using Monte Carlo Tree Search (MCTS) and returns the most promising node.
     *
     * @param rootNode The root node of the game tree.
     * @param childCount The number of child nodes to expand from each node.
     * @return The most promising child of the root node.
     */
    public TreeNode monteCarloTreeSearch(TreeNode rootNode, int childCount) {
        TreeNode optimalNode;
        double timeLimit = System.currentTimeMillis() + TIME_LIMIT;

        // Expand the root node by adding child nodes.
        expandChildNodes(rootNode, childCount);

        // Explore the tree until the time limit is reached.
        while (System.currentTimeMillis() < timeLimit) {
            TreeNode promisingNode = selectPromisingNode(rootNode);

            // Expand the promising node.
            if (promisingNode.childNodes.size() == 0) {
                expandChildNodes(promisingNode, childCount);
            }

            simulatePlaythrough(promisingNode);
        }

        optimalNode = selectOptimalNode(rootNode);
        displayNodeScores(rootNode);
        System.out.format("%nThe optimal node is: %02d%n", rootNode.childNodes.indexOf(optimalNode) + 1);

        return optimalNode;
    }

    /**
     * Expands a node by adding a specified number of child nodes.
     *
     * @param node The node to expand.
     * @param childCount The number of child nodes to add.
     */
    public void expandChildNodes(TreeNode node, int childCount) {
        for (int i = 0; i < childCount; i++) {
            node.childNodes.add(new TreeNode(node, !node.isPlayerTurn));
        }
    }

    /**
     * Uses UCT (Upper Confidence bounds applied to Trees) to select a promising child node.
     *
     * @param rootNode The root node of the tree.
     * @return The most promising node according to UCT.
     */
    public TreeNode selectPromisingNode(TreeNode rootNode) {
        TreeNode promisingNode = rootNode;

        // Iterate until an unexpanded node is found.
        while (promisingNode.childNodes.size() != 0) {
            double highestUctValue = Double.MIN_VALUE;
            int selectedIndex = 0;

            // Iterate through child nodes and pick the one with the highest UCT value.
            for (int i = 0; i < promisingNode.childNodes.size(); i++) {
                TreeNode childNode = promisingNode.childNodes.get(i);
                double uctValue;

                // If child node has never been visited, it has the highest UCT value.
                if (childNode.visitCount == 0) {
                    selectedIndex = i;
                    break;
                }

                uctValue = ((double) childNode.score / childNode.visitCount) +
                        1.41 * Math.sqrt(Math.log(promisingNode.visitCount) / (double) childNode.visitCount);

                if (uctValue > highestUctValue) {
                    highestUctValue = uctValue;
                    selectedIndex = i;
                }
            }

            promisingNode = promisingNode.childNodes.get(selectedIndex);
        }

        return promisingNode;
    }

    /**
     * Simulates a random playthrough from the current state and backpropagates the result.
     *
     * @param promisingNode The node to simulate.
     */
    public void simulatePlaythrough(TreeNode promisingNode) {
        Random random = new Random();
        TreeNode currentNode = promisingNode;
        boolean playerWins;

        // Randomly determine if the simulated play is a win or loss.
        promisingNode.hasPlayerWon = (random.nextInt(6) == 0);
        playerWins = promisingNode.hasPlayerWon;

        // Backpropagate the result of the simulated play.
        while (currentNode != null) {
            currentNode.visitCount++;

            // Add winning scores to both player and opponent depending on the turn.
            if ((currentNode.isPlayerTurn && playerWins) || (!currentNode.isPlayerTurn && !playerWins)) {
                currentNode.score += WIN_SCORE;
            }

            currentNode = currentNode.parentNode;
        }
    }

    /**
     * Selects the optimal node from the child nodes of the root node.
     *
     * @param rootNode The root node.
     * @return The child node with the highest score.
     */
    public TreeNode selectOptimalNode(TreeNode rootNode) {
        return Collections.max(rootNode.childNodes, Comparator.comparing(node -> node.score));
    }

    /**
     * Displays the scores and visit counts of the child nodes of the root node.
     *
     * @param rootNode The root node.
     */
    public void displayNodeScores(TreeNode rootNode) {
        System.out.println("Node\tScore\t\tVisits");

        for (int i = 0; i < rootNode.childNodes.size(); i++) {
            System.out.printf("%02d\t%d\t\t%d%n", i + 1, rootNode.childNodes.get(i).score, rootNode.childNodes.get(i).visitCount);
        }
    }
}
