package eightqueens;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static java.lang.Integer.MAX_VALUE;

public class Main {

    public static void aEstrela(Tree<EstadoTabuleiro> arvore){
        List<TreeNode> aberta = new LinkedList<>();
        List<TreeNode> fechada = new LinkedList<>();

        aberta.add(arvore.getRoot());
        TreeNode<EstadoTabuleiro> noAtual = arvore.getRoot();
        while(true){
            if(aberta.isEmpty()){
                System.out.println("Não foi encontrada solução");
                return;
            }
            if(noAtual.getData().isSolution()){
                System.out.println("Solução:");
                System.out.println(noAtual.getData());
                return;
            }
            

        }
    }

    public static TreeNode<EstadoTabuleiro> menorHeurística(List<TreeNode> aberta, TreeNode<EstadoTabuleiro> noAtual){
        if(aberta.size()==1) return aberta.get(0);

        TreeNode<EstadoTabuleiro> menorNo = new TreeNode<EstadoTabuleiro>(null,0);
        int menor= MAX_VALUE, h=0;
        for(TreeNode<EstadoTabuleiro> possivelNo : aberta){
            h=noAtual.height+1+(noAtual.getData().conflitosGerados(possivelNo.getData().ultimaLinha, possivelNo.getData().ultimaColuna));
            if(h<menor){
                menor=h;
                menorNo=possivelNo;
            }
        }
        return menorNo;
    }

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
//            System.out.println(noTopo.getData().isSolution());
            if(noTopo.getData().isSolution()){
                System.out.println("Profundidade total: "+noTopo.height);
                System.out.println(noTopo.data);
                return;
            }
//            System.out.println("altura do no "+noTopo.height+" -- limite "+limite);
//            System.out.println("No no topo "+noTopo);
//            System.out.println(noTopo.data);
            if(noTopo.height<limite){
//                System.out.println("entrou na expansão");
                //expandir o no
                List<TreeNode> nosFilhos = new LinkedList<>();

                for(int i=0; i<8; i++){
                    for(int j=0; j<8; j++){
                        if(noTopo.getData().tabuleiro[i][j]=='.'){
                            EstadoTabuleiro estadoFilho = new EstadoTabuleiro();
                            estadoFilho.setTabuleiro(noTopo.getData().tabuleiro);
                            estadoFilho.adicionaRainha(i,j);
//                            System.out.println("criação da rainha");
//                            System.out.println(estadoFilho);
                            TreeNode<EstadoTabuleiro> aux = arvore.addChild(noTopo,estadoFilho);
//                            System.out.println(aux);
//                            System.out.println(arvore.find(aux.getData()));
                            nosFilhos.add(aux);
                        }
                    }
                }
//                System.out.println("filhos criados: "+nosFilhos.size());
                //bagunçar os filhos
                //empilhar os filhos
                for(TreeNode<EstadoTabuleiro> a : nosFilhos){
                    borda.push(a);
                }
//                System.out.println("qtd elementos pilha "+borda.size());

            }else{
//                System.out.println("deu pop na pilha no else");
//                System.out.println(borda.size());
                if(!borda.isEmpty()){
                    borda.pop();
                }

            }
        }
    }

    public static void main(String[] args) {
        EstadoTabuleiro inicial = new EstadoTabuleiro();
        Tree<EstadoTabuleiro> arvore = new Tree<>(inicial);
        System.out.println("------Profundidade Limitada------");
        //Limite mínimo = número de rainhas+1;
//        profundidadeLimitada(arvore,9);
        System.out.println("----------------A*---------------");

    }
}
