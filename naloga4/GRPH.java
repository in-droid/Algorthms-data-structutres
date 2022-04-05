package naloga4;

import java.util.*;

import java.util.Arrays;

public class GRPH {

    private int[][] matrix;

    public GRPH(int vertices) {
        matrix = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public void addEdge(int from, int to, int cost) {
        matrix[from][to] = cost;
    }

    public void printShortestDistsFrom(int from) {
        int[] d = matrix[from];
        for (int h = 1; h <= matrix.length - 1; h++) {

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] != Integer.MAX_VALUE && d[i] != Integer.MAX_VALUE && d[j] > d[i] + matrix[i][j]) {
                        d[j] = d[i] + matrix[i][j];
                    }
                }
            }
        }
        System.out.println("V .. Cena");
        for (int i = 0; i < d.length; i++) {
            if (d[i] != Integer.MAX_VALUE) {
                System.out.println(i + " .. " + d[i]);
            }
            else {
                System.out.println(i + " .. " + "None");
            }
        }
        //System.out.println(Arrays.toString(d));
    }

    public static void main(String[] args) {
        GRPH g = new GRPH(7);
        g.addEdge(1,3,5);
        g.addEdge(1,6,11);
        g.addEdge(2,1,-1);
        g.addEdge(3,4,3);
        g.addEdge(3,5,4);
        g.addEdge(4,2,4);
        g.addEdge(4,6,5);
        g.addEdge(5,4,-2);
        g.addEdge(5,2,-2);
        g.addEdge(6,5,3);
        g.printShortestDistsFrom(1);

    }
}

class LBR {
    private int[][] cells;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point(int n) {

        }
    }

    static class QueueNode {
        Point p;
        int dist;

        public QueueNode(Point p, int dist) {
            this.p = p;
            this.dist = dist;
        }
    }

    private boolean validMove(int row, int column) {
        return (row >= 0) && (row < cells[0].length) &&
                (column >=0) && (column < cells.length);
    }


    public LBR(int[][] cells) {
        this.cells = cells;

    }

    private int[] move(int i) {
        switch (i) {
            case 0: return new int[] {-1, 0};
            case 1: return new int[] {0, -1};
            case 2: return new int[] {1, 0};
            case 3: return new int[] {0, 1};

            default:
                throw new IllegalArgumentException("Invalid move");
        }
    }

    private Point toPoint(int n) {
        int row = (n - 1) / cells[0].length;
        int column = (n - 1) % cells[0].length;
        return new Point(row, column);
    }

    private int toPointNumber(Point point) {
        return point.x * cells[0].length + (point.y + 1);

    }



    private int findShortestPath(int from, int to) {
        Point fromPoint = toPoint(from);
        Point toPoint = toPoint(to);

        if (cells[fromPoint.x][fromPoint.y] != 0 ||
            cells[toPoint.x][toPoint.y] != 0) {
            return -1;
        }

        boolean[][] visited = new boolean[cells[0].length][cells.length];

        visited[fromPoint.x][fromPoint.y] = true;
        Queue<QueueNode> queue = new ArrayDeque<>();
        QueueNode start = new QueueNode(fromPoint, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            QueueNode current = queue.peek();
            Point point = current.p;

            if (point.x == toPoint.x && point.y == toPoint.y) {
                //reconstrurePath(to));
                return current.dist;
            }
            queue.remove();

            for (int i = 0; i < 4; i++) {
                int[] movement = move(i);
                int row = point.x + movement[0];
                int column = point.y + movement[1];

                if (validMove(row, column) && !visited[row][column]
                        && cells[row][column] == 0) {
                    visited[row][column] = true;
                    cells[row][column] = toPointNumber(current.p);
                    QueueNode newNode = new QueueNode(
                            new Point(row, column), current.dist + 1);

                    queue.add(newNode);
                }
            }
        }
        return -1;
    }

    private ArrayList<Integer> reconstrurePath(int to) {
        ArrayList<Integer> result = new ArrayList<>();
        Point point = toPoint(to);
        while (cells[point.x][point.y] != 0) {
            int pointNumber = toPointNumber(point);
            result.add(pointNumber);
            point = toPoint(cells[point.x][point.y]);
        }
        result.add(toPointNumber(point)); ///FIX THIS
        return result;
    }

    public void printPath(int from, int to) {
        int pathSize = findShortestPath(from, to);
        if (pathSize == -1) {
            System.out.println("None");
        }
        else {
            System.out.println("Length " + pathSize + ":");
            ArrayList<Integer> path = reconstrurePath(to);
            for (int i = path.size() - 1; i > -1; i--) {
                System.out.println(path.get(i));
            }
        }

    }

    public static void main(String[] args) {
        int cells[][] = new int[][] { { 0, 0, 0, 1 },
                { 0, 0, 1, 0 },
                { 0, 1, 0, 0 },
                { 1, 0, 0, 0 } };

        LBR l = new LBR(cells);
        l.printPath(1, 16);
    }
}
