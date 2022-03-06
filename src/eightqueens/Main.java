package eightqueens;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void profundidadeLimitada(Tree<EstadoTabuleiro> arvore, int limite){
        Stack<TreeNode<EstadoTabuleiro>> borda = new Stack<>();
        borda.push(arvore.getRoot());
        while(true){
            if(borda.isEmpty()){
                System.out.println("Não foi encontrada solução");
                return;
            }
//            System.out.println(borda.peek().data);
            TreeNode<EstadoTabuleiro> noTopo = borda.pop();
            if(noTopo.getData().isSolution()){
                System.out.println("Profundidade total: "+noTopo.height);
                System.out.println(noTopo.data);
                return;
            }
            System.out.println("altura do no "+noTopo.height+" -- limite "+limite);
            System.out.println("No no topo "+noTopo);
            System.out.println(noTopo.data);
            if(noTopo.height<limite){
                System.out.println("entrou na expansão");
                //expandir o no
                List<TreeNode> nosFilhos = new LinkedList<>();
                for(int i=0; i<8; i++){
                    for(int j=0; j<8; j++){
                        if(noTopo.getData().tabuleiro[i][j]=='.'){
                            EstadoTabuleiro estadoFilho = new EstadoTabuleiro();
                            estadoFilho.setTabuleiro(noTopo.getData().tabuleiro);
                            estadoFilho.adicionaRainha(i,j);
                            System.out.println("criação da rainha");
                            System.out.println(estadoFilho);
                            TreeNode<EstadoTabuleiro> aux = arvore.addChild(noTopo,estadoFilho);
                            System.out.println(aux);
                            System.out.println(arvore.find(aux.getData()));
                            nosFilhos.add(aux);
                        }
                    }
                }
                System.out.println("filhos criados: "+nosFilhos.size());
                //bagunçar os filhos
                //empilhar os filhos
                for(TreeNode<EstadoTabuleiro> a : nosFilhos){
                    borda.push(a);
                }
                System.out.println("qtd elementos pilha "+borda.size());
            }else{
                System.out.println("deu pop na pilha no else");
                borda.pop();
            }
        }
    }

    public static void main(String[] args) {
        EstadoTabuleiro inicial = new EstadoTabuleiro();
        Tree<EstadoTabuleiro> arvore = new Tree<>(inicial);
        profundidadeLimitada(arvore,4);

    }
}
