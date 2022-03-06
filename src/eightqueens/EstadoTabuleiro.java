package eightqueens;

public class EstadoTabuleiro {
    char tabuleiro[][];
    int numeroRainhas;

    public EstadoTabuleiro(){
        tabuleiro = new char[8][8];
        numeroRainhas=0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                tabuleiro[i][j] = '.';
            }
        }
    }

    public void adicionaRainha(int m, int n){
        tabuleiro[m][n] = 'Q';
        numeroRainhas++;
        marcaAtaques(m,n);
    }

    public void marcaAtaques(int m, int n){
        char marcador='+';
        //linha
        for(int j=0; j<8; j++){
            if(j!=n){
                tabuleiro[m][j]=marcador;
            }
        }
        //coluna
        for(int i=0; i<8; i++){
            if(m!=i){
                tabuleiro[i][n]=marcador;
            }
        }
        //diagonais
        int j=n;
        int k=n;
        for(int i=m-1; i>=0; i--){
            j--;
            k++;
            if(j>=0){
                tabuleiro[i][j]=marcador;
            }
            if(k<8){
                tabuleiro[i][k]=marcador;
            }
        }
        j=n;
        k=n;
        for(int i=m+1; i<8; i++){
            j--;
            k++;
            if(j>=0){
                tabuleiro[i][j]=marcador;
            }
            if(k<8){
                tabuleiro[i][k]=marcador;
            }
        }
    }

    public int conflitosGerados(int m, int n){
        int conflitos=0;

        //linha
        for(int j=0; j<8; j++){
            if(j!=n){
                if(tabuleiro[m][j]=='Q'){
                    conflitos++;
                }
            }
        }
        //coluna
        for(int i=0; i<8; i++){
            if(m!=i){
                if(tabuleiro[i][n]=='Q'){
                    conflitos++;
                }
            }
        }
        //diagonais
        int j=n;
        int k=n;
        for(int i=m-1; i>=0; i--){
            j--;
            k++;
            if(j>=0){
                if(tabuleiro[i][j]=='Q'){
                    conflitos++;
                }
            }
            if(k<8){
                if(tabuleiro[i][k]=='Q'){
                    conflitos++;
                }
            }
        }
        j=n;
        k=n;
        for(int i=m+1; i<8; i++){
            j--;
            k++;
            if(j>=0){
                if(tabuleiro[i][j]=='Q'){
                    conflitos++;
                }
            }
            if(k<8){
                if(tabuleiro[i][k]=='Q'){
                    conflitos++;
                }
            }
        }
        return conflitos;
    }

    public boolean isSolution(){
        if(numeroRainhas==8) return true;
        else return false;
    }

    public void setTabuleiro(char tabuleiro2[][]){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                this.tabuleiro[i][j]=tabuleiro2[i][j];
            }
        }
    }

    public char[][] getTabuleiro(){
        return this.tabuleiro;
    }

    public String toString(){
        String print="";

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                print=print+tabuleiro[i][j]+" ";
            }
            print=print+"\n";
        }

        return print;
    }

    public boolean equals(Object o){
        return (((EstadoTabuleiro)o).getTabuleiro()).equals(this.tabuleiro);
    }
}
