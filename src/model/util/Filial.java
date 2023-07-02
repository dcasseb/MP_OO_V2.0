package model.util;

import java.util.ArrayList;
import java.util.Iterator;

import controller.util.Database;
import view.util.TelaPatrimonio;

public class Filial {
	private TelaPatrimonio telaPatrimonio;
    private String endereco;
    private String cnpj;
    private ArrayList<Patrimonio> patrimonio;
    private Database database; // Referência para o Database
    
    public void setTelaPatrimonio(TelaPatrimonio telaPatrimonio) {
        this.telaPatrimonio = telaPatrimonio;
    }

    public Filial() {
        this.patrimonio = new ArrayList<>();
        this.database = new Database();
    }

    public Filial(String endereco, String cnpj) {
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.patrimonio = new ArrayList<>();
        this.database = null;  // Assuming database initialization is not necessary here
    }

    
    public boolean existePatrimonio(String nomePatrimonio) {
        for (Patrimonio patrimonio : patrimonio) {
            if (patrimonio.getNome().equalsIgnoreCase(nomePatrimonio)) {
                return true; // Patrimônio encontrado
            }
        }
        return false; // Patrimônio não encontrado
    }

	public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public ArrayList<Patrimonio> getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(ArrayList<Patrimonio> patrimonio) {
        this.patrimonio = patrimonio;
    }
    
    public double somarPatrimonios() {
        double soma = 0.0;
        for (Patrimonio patrimonio : patrimonio) {
            soma += patrimonio.getValor();
        }
        return soma;
    }

    public void adicionarPatrimonio(Patrimonio novoPatrimonio) {
        patrimonio.add(novoPatrimonio);
        novoPatrimonio.setFilial(this); // Define a Filial para o objeto Patrimonio
        if (database != null) {
            database.adicionarPatrimonio(novoPatrimonio, this); // Passa o objeto Filial para o método do Database
        }
    }

    public void editarPatrimonio(String nomeAntigo, String novoNome) {
        for (Patrimonio patrimonio : patrimonio) {
            if (patrimonio.getNome().equals(nomeAntigo)) {
                patrimonio.setNome(novoNome);
                if (database != null) {
                    database.editarPatrimonio(this, nomeAntigo, novoNome);
                }
                break;
            }
        }
    }

    public Patrimonio buscarPatrimonio(String nome) {
        for (Patrimonio patrimonio : patrimonio) {
            if (patrimonio.getNome().equals(nome)) {
                return patrimonio;
            }
        }
        return null;
    }

    public void excluirPatrimonio(String nome) {
        Iterator<Patrimonio> iterator = patrimonio.iterator();
        while (iterator.hasNext()) {
            Patrimonio patrimonioItem = iterator.next();
            if (patrimonioItem.getNome().equals(nome)) {
                iterator.remove();
                if (database != null) {
                    database.excluirPatrimonio(patrimonioItem);
                }
                break;
            }
        }
    }

    public String[] getNomesPatrimonios() {
        String[] nomes = new String[patrimonio.size()];
        for (int i = 0; i < patrimonio.size(); i++) {
            nomes[i] = patrimonio.get(i).getNome();
        }
        return nomes;
    }

    @Override
    public String toString() {
        return "Filial [endereco=" + endereco + ", cnpj=" + cnpj + "]";
    }

	public Object getImovel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getMovel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setImovel(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void setMovel(Object object) {
		// TODO Auto-generated method stub
		
	}
}
