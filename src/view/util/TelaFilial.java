package view.util;

import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

import model.util.Filial;
import model.util.Patrimonio;
import controller.util.Database;

public class TelaFilial extends JFrame {
    private ArrayList<Filial> filiais;
    private Database database;
    private JTextArea filialListArea; // Added list area
    private TelaPatrimonio telaPatrimonio;
    private TelaPrincipal telaPrincipal;
    private static TelaFilial instance;

    public static TelaFilial getInstance(Database database, TelaPatrimonio telaPatrimonio, TelaPrincipal telaPrincipal) {
        if (instance == null) {
            instance = new TelaFilial(database, telaPatrimonio, telaPrincipal);
        } else {
            instance.setVisible(true);
        }
        return instance;
    }

    public TelaFilial(Database database, TelaPatrimonio telaPatrimonio, TelaPrincipal telaPrincipal) {
        this.database = database;
        this.telaPatrimonio = telaPatrimonio; // Assign the instance of the patrimônio screen
        this.telaPrincipal = telaPrincipal;
        this.filiais = new ArrayList<>();
        
        TelaPatrimonio telaPatrimonio1 = new TelaPatrimonio(telaPrincipal, database, filiais);

        setTitle("Tela de Filiais");
        setSize(1920, 1080);

        // Create the filialListArea and add it to the patrimonioPanel
        filialListArea = new JTextArea();
        filialListArea.setEditable(false);
        Font textAreaFont = new Font("Arial", Font.BOLD, 16);
        filialListArea.setFont(textAreaFont);
        Color backgroundColor = new Color(255, 255, 255);
        filialListArea.setBackground(backgroundColor);

        JPanel patrimonioPanel = new JPanel(new FlowLayout());
        patrimonioPanel.setBackground(Color.WHITE);
        patrimonioPanel.add(filialListArea);
        
        // Create the scroll pane and add the patrimonioPanel to it
        JScrollPane scrollPane = new JScrollPane(patrimonioPanel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        // Create the customized background panel with BorderLayout layout
        BackgroundPanel backgroundPanel = new BackgroundPanel("Imagens/telaSecundaria.png");
        backgroundPanel.setLayout(new BorderLayout());
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        // Create a button to return to TelaPrincipal
        JButton backButton = new JButton();
        backButton.setIcon(new ImageIcon("Imagens/iconeRetorno.png"));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Retornando a TelaPrincipal");
                telaPrincipal.setVisible(true); // Make the TelaPrincipal frame visible
                setVisible(false); // Makes the current frame (TelaFilial) invisible
            }
        });

        // Create a panel for the button in the top right corner
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);

        // Add the button panel to the background panel
        backgroundPanel.add(buttonPanel, BorderLayout.NORTH);

        // Create the option buttons
        JButton adicionarButton = new JButton(new ImageIcon("Imagens/Adicionar_button.png"));
        JButton editarButton = new JButton(new ImageIcon("Imagens/Editar_button.png"));
        JButton buscarButton = new JButton(new ImageIcon("Imagens/Buscar_button.png"));
        JButton removerButton = new JButton(new ImageIcon("Imagens/Excluir_button.png"));
        JButton listarButton = new JButton(new ImageIcon("Imagens/Listar_button.png"));
        JButton patrimoniosButton = new JButton(new ImageIcon("Imagens/patrimoniosButton.png"));

        // Set the buttons' sizes
        adicionarButton.setPreferredSize(new Dimension(200, 60));
        editarButton.setPreferredSize(new Dimension(200, 60));
        buscarButton.setPreferredSize(new Dimension(200, 60));
        removerButton.setPreferredSize(new Dimension(200, 60));
        listarButton.setPreferredSize(new Dimension(200, 60));
        patrimoniosButton.setPreferredSize(new Dimension(200, 60));

        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == adicionarButton) {
                    System.out.println("Opção selecionada: Adicionar");

                    // Ask for the filial's address and CNPJ
                    String endereco = JOptionPane.showInputDialog("Digite o endereço da filial:");
                    String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da filial:");

                    // Create a new filial and add it to the list of filiais
                    Filial novaFilial = new Filial(endereco, cnpj);
                    filiais.add(novaFilial);

                } else if (e.getSource() == editarButton) {
                    System.out.println("Opção selecionada: Editar");

                    // Ask for the CNPJ and new address of the filial to be edited
                    String cnpjAtual = JOptionPane.showInputDialog("Digite o CNPJ atual da filial:");
                    String novoEndereco = JOptionPane.showInputDialog("Digite o novo endereço da filial:");

                    for (Filial filial : filiais) {
                        if (String.valueOf(filial.getCnpj()).equalsIgnoreCase(cnpjAtual)) {
                            filial.setEndereco(novoEndereco);
                            break;
                        }
                    }

                } else if (e.getSource() == buscarButton) {
                    System.out.println("Opção selecionada: Buscar");
                    String searchCnpj = JOptionPane.showInputDialog("Digite o CNPJ da filial a ser buscada:");
                    double totalValue = 0.0; // Variable to store the total value of patrimônios

                    StringBuilder filialList = new StringBuilder(); // Declare and initialize the StringBuilder

                    for (Filial filial : filiais) {
                        if (String.valueOf(filial.getCnpj()).equalsIgnoreCase(searchCnpj)) {
                            filialList.append("Filial encontrada:\n");
                            filialList.append("Endereço: " + filial.getEndereco() + "\n");
                            filialList.append("CNPJ: " + filial.getCnpj() + "\n");

                            ArrayList<Patrimonio> patrimonios = filial.getPatrimonio();
                            if (patrimonios != null) {
                                for (Patrimonio patrimonio : patrimonios) {
                                    totalValue += patrimonio.getValor(); // Accumulate the value of patrimônios
                                }
                            }

                            filialList.append("Valor total dos patrimônios: R$" + totalValue + "\n");
                            break;
                        }
                    }

                    filialListArea.setText(filialList.toString());
                } else if (e.getSource() == removerButton) {
                    System.out.println("Opção selecionada: Remover");
                    String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da filial a ser removida:");

                    for (Filial filial : filiais) {
                        if (String.valueOf(filial.getCnpj()).equalsIgnoreCase(cnpj)) {
                            filiais.remove(filial);
                            break;
                        }
                    }

                } else if (e.getSource() == listarButton) {
                    StringBuilder filialList = new StringBuilder();

                    for (Filial filial : filiais) {
                        filialList.append("Endereço: ").append(filial.getEndereco()).append("\n");
                        filialList.append("CNPJ: ").append(filial.getCnpj()).append("\n");
                        filialList.append("\n");
                    }

                    // Debug statement to check the number of filiais
                    System.out.println("Number of filiais: " + filiais.size());

                    if (filiais.isEmpty()) {
                        filialList.append("Nenhuma filial cadastrada.");
                    }

                    filialListArea.setText(filialList.toString());
                    telaPatrimonio1.setFiliais(filiais);
                }
            }
        };
        
        patrimoniosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Abrindo TelaPatrimonio");
                telaPatrimonio1.setVisible(true);
            }
        });

        adicionarButton.addActionListener(buttonListener);
        editarButton.addActionListener(buttonListener);
        buscarButton.addActionListener(buttonListener);
        removerButton.addActionListener(buttonListener);
        listarButton.addActionListener(buttonListener);
        patrimoniosButton.addActionListener(buttonListener);

        // Center the buttons in the background panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        centerPanel.add(adicionarButton, gbc);

        gbc.gridx = 1;
        centerPanel.add(editarButton, gbc);

        gbc.gridx = 2;
        centerPanel.add(buscarButton, gbc);

        gbc.gridx = 3;
        centerPanel.add(removerButton, gbc);

        gbc.gridx = 4;
        centerPanel.add(listarButton, gbc);
        
        gbc.gridx = 5;
        centerPanel.add(patrimoniosButton, gbc);

     // Add the scroll pane to the center panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10);
        centerPanel.add(scrollPane, gbc);

        // Add the center panel to the background panel
        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
    }

    class BackgroundPanel extends JPanel implements Serializable {
        private static final long serialVersionUID = 1L;
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
            this.setVisible(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1920, 1080);
        }
    }
}
