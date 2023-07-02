package view.util;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import controller.util.Database;
import model.util.Filial;
import model.util.Imovel;
import model.util.Movel;
import model.util.Patrimonio;

public class TelaPatrimonio extends JFrame {
    private String nome;
    private double valor;
    private Filial filial;
    private int peso;
	private String cor;
	private int dimensao;
	private int cep;
	private double tamanho;
    private ArrayList<Patrimonio> patrimonio;
    private ArrayList<Filial> filiais;
    private TelaPrincipal telaPrincipal;
    private Database database;

    
    public void setVisible(boolean visible) {
        super.setVisible(visible);
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

    public Filial getFilial() {
        return filial;
    }

    public void setFilial(Filial filial) {
        this.filial = filial;
    }
    
    public void setFiliais(ArrayList<Filial> filiais) {
        this.filiais = filiais;
    }

    public TelaPatrimonio(TelaPrincipal telaPrincipal, Database database, ArrayList<Filial> filiais) {
        // Initialize the instance variables
        this.database = database;
        this.filiais = filiais;
        this.telaPrincipal = telaPrincipal;
        this.patrimonio = new ArrayList<>();
        setTitle("Tela de Patrimônio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        

        JTextArea patrimonioListArea = new JTextArea();
        patrimonioListArea.setEditable(false); // To make it read-only
        Font textAreaFont = new Font("Arial", Font.BOLD, 16);
        patrimonioListArea.setFont(textAreaFont);
        Color backgroundColor = new Color(255, 255, 255);
        patrimonioListArea.setBackground(backgroundColor);

        // Cria o painel de fundo customizado com o layout BorderLayout
        BackgroundPanel backgroundPanel = new BackgroundPanel("Imagens/telaSecundaria.png");
        backgroundPanel.setLayout(new BorderLayout());
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        // Cria um botao para retornar a TelaPrincipal
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("Imagens/iconeRetorno.png"));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (telaPrincipal != null) {
                    telaPrincipal.setVisible(true);
                }
                setVisible(false);
            }
        });

        // Cria um painel para o botao no canto superior direito
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);

        JPanel patrimonioPanel = new JPanel(new BorderLayout());
        patrimonioPanel.setBackground(Color.BLACK);
        patrimonioPanel.add(patrimonioListArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(patrimonioPanel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        //Adiciona o painel de botoes ao painel de fundo
        backgroundPanel.add(buttonPanel, BorderLayout.NORTH);

        // Cria os botoes de opcoes
        JButton adicionarButton = new JButton("Adicionar");
        JButton removerButton = new JButton("Remover");

        // Adiciona os botoes ao painel de opcoes
        JPanel optionsPanel = new JPanel(new FlowLayout());
        optionsPanel.add(adicionarButton);
        optionsPanel.add(removerButton);

        // Adiciona o painel de opcoes ao painel de fundo
        backgroundPanel.add(optionsPanel, BorderLayout.SOUTH);

        // Adiciona o painel de rolagem ao painel de fundo
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        
        // ActionListener para o botao "Adicionar"
        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = JOptionPane.showInputDialog("Digite o nome do patrimônio:");
                String valorStr = JOptionPane.showInputDialog("Digite o valor do patrimônio:");
                double valor = Double.parseDouble(valorStr);
                
                // Verifica se a filial está selecionada
                if (filial != null) {
                    if (filial.getImovel() == null && filial.getMovel() == null) {
                        // Cria uma opção de diálogo para escolher o tipo de patrimônio
                        String[] options = { "Imóvel", "Móvel" };
                        int selectedOption = JOptionPane.showOptionDialog(null, "Escolha o tipo de patrimônio:", "Tipo de Patrimônio",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                        if (selectedOption == 0) {
                            // Cria um objeto Imovel e adiciona à lista de patrimônios
                            Imovel imovel = new Imovel(nome, cep, tamanho, valor);
                            filial.setImovel(imovel);
                            patrimonio.add(imovel);
                        } else if (selectedOption == 1) {
                            // Cria um objeto Movel e adiciona à lista de patrimônios
                            Movel movel = new Movel(nome, valor, peso, cor, dimensao);
                            filial.setMovel(movel);
                            patrimonio.add(movel);
                        }
                        
                        // Atualiza o texto da JTextArea com os patrimônios
                        patrimonioListArea.setText(getPatrimonioText());
                    } else {
                        JOptionPane.showMessageDialog(null, "Já existe um patrimônio cadastrado para esta filial!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma filial selecionada!");
                }
            }
        });

        // ActionListener para o botao "Remover"
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (filial != null) {
                    if (filial.getImovel() != null || filial.getMovel() != null) {
                        // Remove o patrimônio da lista e atualiza a filial
                        if (filial.getImovel() != null) {
                            patrimonio.remove(filial.getImovel());
                            filial.setImovel(null);
                        }
                        
                        if (filial.getMovel() != null) {
                            patrimonio.remove(filial.getMovel());
                            filial.setMovel(null);
                        }
                        
                        // Atualiza o texto da JTextArea com os patrimônios
                        patrimonioListArea.setText(getPatrimonioText());
                    } else {
                        JOptionPane.showMessageDialog(null, "Não há patrimônios cadastrados para esta filial!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma filial selecionada!");
                }
            }
        });
    }

    private String getPatrimonioText() {
        StringBuilder sb = new StringBuilder();
        for (Patrimonio p : patrimonio) {
            sb.append(p.getNome());
            sb.append(" - ");
            sb.append(p.getValor());
            sb.append("\n");
        }
        return sb.toString();
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            // Carrega a imagem de fundo
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Desenha a imagem de fundo
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
