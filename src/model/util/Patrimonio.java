package model.util;

public class Patrimonio {
    private Filial filial; // Reference to a single Filial
    private String nome;
    private double valor;

    public Patrimonio(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Patrimonio(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Patrimonio [nome = " + nome + ", valor = " + valor + "]";
    }

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
}
