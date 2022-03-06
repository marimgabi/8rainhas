package eightqueens;

import java.util.*;

import static java.lang.Integer.MAX_VALUE;

public class Main {

    public static void profundidadeLimitada(Tree<EstadoTabuleiro> arvore, int limite) {
        int counter = 0;
        Stack<TreeNode<EstadoTabuleiro>> borda = new Stack<>();
        borda.push(arvore.getRoot());
        while (true) {
            if (borda.isEmpty()) {
                System.out.println("Não foi encontrada solução");
                return;
            }
            TreeNode<EstadoTabuleiro> noTopo = borda.pop();
            counter++;
            if (noTopo.getData().isSolution()) {
                System.out.println("Profundidade total: " + noTopo.height);
                System.out.println(noTopo.data);
                System.out.printf("Encontrou em %d iterações\n", counter);
                return;
            }
            if (noTopo.height < limite) {
                //expandir o no
                List<TreeNode<EstadoTabuleiro>> nosFilhos = new LinkedList<>();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (noTopo.getData().tabuleiro[i][j] == '.') {
                            EstadoTabuleiro estadoFilho = new EstadoTabuleiro();
                            estadoFilho.setTabuleiro(noTopo.getData().tabuleiro);
                            estadoFilho.adicionaRainha(i, j);
                            TreeNode<EstadoTabuleiro> aux = arvore.addChild(noTopo, estadoFilho);
                            nosFilhos.add(aux);
                        }
                    }
                }
                //empilhar os filhos
                for (TreeNode<EstadoTabuleiro> a : nosFilhos) {
                    borda.push(a);
                }

            } else if (!borda.isEmpty()) borda.pop();

        }
    }

    public static void aEstrela(Tree<EstadoTabuleiro> arvore) {
        int counter = 0;
        List<TreeNode<EstadoTabuleiro>> open = new LinkedList<>();
        open.add(arvore.getRoot());
        TreeNode<EstadoTabuleiro> node = open.get(0);

        while (!open.isEmpty()) {
            counter++;
            // encontra o nó com o mínimo de conflitos
            node = minConflicts(open, node);
            open.remove(node);

            if (node.data.isSolution()) {
                System.out.println("Solução:");
                System.out.println(node.getData());
                System.out.printf("Encontrou em %d iterações\n", counter);
                return;
            }

            // expande os filhos do nó
            for (int j = 0; j < 8; j++) {
                if (node.getData().tabuleiro[node.height - 1][j] == '.') {
                    EstadoTabuleiro estadoFilho = new EstadoTabuleiro();
                    estadoFilho.setTabuleiro(node.getData().tabuleiro);
                    estadoFilho.adicionaRainha(node.height - 1, j);
                    TreeNode<EstadoTabuleiro> filho = arvore.addChild(node, estadoFilho);
                    open.add(filho);
                }
            }
        }
    }

    // retorna o nó com o mínimo de conflitos
    public static TreeNode<EstadoTabuleiro> minConflicts(List<TreeNode<EstadoTabuleiro>> open, TreeNode<EstadoTabuleiro> current) {
        if (open.size() == 1) return open.get(0);
        int minConflicts = MAX_VALUE;

        TreeNode<EstadoTabuleiro> min = new TreeNode<>(null, 0);
        for (TreeNode<EstadoTabuleiro> possivelNo : open) {
            int conflitcs;
            if (possivelNo.data.ultimaColuna == -1 && possivelNo.data.ultimaLinha == -1) conflitcs = 0; // root
            else conflitcs = current.data.conflitosGerados(possivelNo.data.ultimaColuna, possivelNo.data.ultimaLinha);
            if (conflitcs < minConflicts) {
                minConflicts = conflitcs;
                min = possivelNo;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        EstadoTabuleiro inicial = new EstadoTabuleiro();
        Tree<EstadoTabuleiro> arvore = new Tree<>(inicial);
        System.out.println("------Profundidade Limitada------");
        //Limite mínimo = número de rainhas+1;
        profundidadeLimitada(arvore,10);
        System.out.println("----------------A*---------------");
        aEstrela(arvore);
    }
}
