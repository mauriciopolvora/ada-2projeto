import UnionFind.UnionFind;
import UnionFind.UnionFindInArray;
import java.util.*;

public class Problem {
    private int counter;
    private Map<Integer, Integer> coder;
    private List<Pair> points;

    private UnionFind partition;

    public Problem(int nSeg) {
        this.counter = 0;
        this.coder = new HashMap<>();
        this.points = new LinkedList<>();
        this.partition = new UnionFindInArray(nSeg * 2); // worst case
    }

    public void addNode(int x, int y) {
        Pair p = new Pair(x, y);
        // Adicionar ao coder caso ainda não existe codificação
        // Temos que utilizar aqui o hash porque é o hash(p)
        // que fica guardado como chave no nosso coder!
        // TODO
        points.add(p);

    }

    private int hash(Pair p) {
        return p.getX() * 1000 + p.getY();
    }

    public int paint() {
        int nPainted = 0;
        ListIterator<Pair> it = this.points.listIterator();
        while (it.hasNext()) {
            Pair p1 = it.next();
            Pair p2 = it.next();
            // Buscar no coder o correspondente dum ponto
            // na nossa partição, realizar a operação de find
            // e verificar se os representantes são diferentes.
            // Caso sejam diferentes, então aumentamos o counter
            // de segmentos pintados, e realizar a uniao dos dois
            // representantes.
            // TODO
        }
        return nPainted;
    }

}
