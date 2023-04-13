/**
 * Authors: Edson Ricardo da Costa & Gabriel Fanto Stundner.
 */

package view;

import controller.CipherControllerImpl;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Vigenere extends javax.swing.JFrame {

    CipherControllerImpl impl = new CipherControllerImpl();
    private String originalCipherText = "";
    
    public Vigenere() {
        initComponents();
        newProcess();
    }

    public void search() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Search File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
        fileChooser.setCurrentDirectory(new File("./src/files"));
        int result = fileChooser.showOpenDialog(fileChooser);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getPath();
            txtPath.setText(path);
        }
    }

    public String formatOutput(String input, int lineBreak){
        String output = "";
        for(int i = 0; i < input.length(); i++){
            if(i % lineBreak != 0){
                output += input.charAt(i);
            }else{
                output += "\n";
            }
        }
        return output;
    }

    public void importCipherFile() throws IOException {
        try {
            String cipherText = impl.readCipherText(txtPath.getText());
            originalCipherText = cipherText;
            txtCipher.setText(formatOutput(cipherText, 63));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select a valid file path", "Invalid file path",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void decipher() {
        String editCipherText = originalCipherText.replaceAll("\\s", "").toUpperCase();
        if (!editCipherText.isEmpty()) {
            impl.createSequences(editCipherText);
            int keySize = Integer.parseInt(cb_keySize.getSelectedItem().toString());
            String key = impl.findKeyBySize(editCipherText, keySize);
            txtKeyGuessed.setText(key);
            String decipherText = impl.decipherByKey(editCipherText, key);
            txtDecipher.setText(formatOutput(decipherText, 50));
        } else {
            JOptionPane.showMessageDialog(null, "Import the ciphertext file", "Ciphertext not imported", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void newProcess() {
        this.setTitle("Vigenere Decipher");
        this.setLocationRelativeTo(null);
        txtPath.setEditable(false);
        txtPath.setText("");
        txtCipher.setEditable(false);
        txtCipher.setText("");
        cb_keySize.setSelectedIndex(0);
        txtDecipher.setEditable(false);
        txtDecipher.setText("");
        txtKeyGuessed.setEditable(false);
        txtKeyGuessed.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pn_dataInput = new javax.swing.JPanel();
        lbPath = new javax.swing.JLabel();
        txtPath = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        pn_cipherText = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCipher = new javax.swing.JTextPane();
        btn_import = new javax.swing.JButton();
        pn_dataOutput = new javax.swing.JPanel();
        lb_keySize = new javax.swing.JLabel();
        cb_keySize = new javax.swing.JComboBox<>();
        btnDecipher = new javax.swing.JButton();
        pn_clearText = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDecipher = new javax.swing.JTextPane();
        lb_keyGuessed = new javax.swing.JLabel();
        txtKeyGuessed = new javax.swing.JTextField();
        mn = new javax.swing.JMenuBar();
        mn1 = new javax.swing.JMenu();
        mn_new = new javax.swing.JMenuItem();
        mn_exit = new javax.swing.JMenuItem();
        mn2 = new javax.swing.JMenu();
        mn_about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pn_dataInput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Input", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lbPath.setText("File Path");

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        pn_cipherText.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cipher Text", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane1.setViewportView(txtCipher);

        javax.swing.GroupLayout pn_cipherTextLayout = new javax.swing.GroupLayout(pn_cipherText);
        pn_cipherText.setLayout(pn_cipherTextLayout);
        pn_cipherTextLayout.setHorizontalGroup(
            pn_cipherTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_cipherTextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pn_cipherTextLayout.setVerticalGroup(
            pn_cipherTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_cipherTextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_import.setText("Import");
        btn_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_importActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_dataInputLayout = new javax.swing.GroupLayout(pn_dataInput);
        pn_dataInput.setLayout(pn_dataInputLayout);
        pn_dataInputLayout.setHorizontalGroup(
            pn_dataInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_dataInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_cipherText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_dataInputLayout.createSequentialGroup()
                        .addComponent(lbPath)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSearch))
                    .addComponent(btn_import, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pn_dataInputLayout.setVerticalGroup(
            pn_dataInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataInputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_dataInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPath)
                    .addComponent(txtPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_import)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pn_cipherText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn_dataOutput.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Output", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lb_keySize.setText("Key Size");

        cb_keySize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" }));

        btnDecipher.setText("Decipher");
        btnDecipher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecipherActionPerformed(evt);
            }
        });

        pn_clearText.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clear Text", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jScrollPane2.setViewportView(txtDecipher);

        javax.swing.GroupLayout pn_clearTextLayout = new javax.swing.GroupLayout(pn_clearText);
        pn_clearText.setLayout(pn_clearTextLayout);
        pn_clearTextLayout.setHorizontalGroup(
            pn_clearTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_clearTextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        pn_clearTextLayout.setVerticalGroup(
            pn_clearTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_clearTextLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_keyGuessed.setText("Key Guessed");

        javax.swing.GroupLayout pn_dataOutputLayout = new javax.swing.GroupLayout(pn_dataOutput);
        pn_dataOutput.setLayout(pn_dataOutputLayout);
        pn_dataOutputLayout.setHorizontalGroup(
            pn_dataOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataOutputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_dataOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_clearText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_dataOutputLayout.createSequentialGroup()
                        .addComponent(lb_keySize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_keySize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDecipher, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE))
                    .addGroup(pn_dataOutputLayout.createSequentialGroup()
                        .addComponent(lb_keyGuessed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKeyGuessed)))
                .addContainerGap())
        );
        pn_dataOutputLayout.setVerticalGroup(
            pn_dataOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_dataOutputLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn_dataOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_keySize)
                    .addComponent(cb_keySize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDecipher))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pn_dataOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_keyGuessed)
                    .addComponent(txtKeyGuessed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_clearText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_dataInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pn_dataOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_dataInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_dataOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mn1.setText("File");

        mn_new.setText("New Process");
        mn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_newActionPerformed(evt);
            }
        });
        mn1.add(mn_new);

        mn_exit.setText("Exit");
        mn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_exitActionPerformed(evt);
            }
        });
        mn1.add(mn_exit);

        mn.add(mn1);

        mn2.setText("Help");

        mn_about.setText("About");
        mn_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mn_aboutActionPerformed(evt);
            }
        });
        mn2.add(mn_about);

        mn.add(mn2);

        setJMenuBar(mn);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    private void mn_exitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void mn_newActionPerformed(java.awt.event.ActionEvent evt) {
        newProcess();
    }

    private void mn_aboutActionPerformed(java.awt.event.ActionEvent evt) {
        About window = new About();
        window.setVisible(true);
    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {
        search();
    }

    private void btn_importActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            importCipherFile();
        } catch (IOException ex) {
            System.err.println("IO Error");
        }
    }

    private void btnDecipherActionPerformed(java.awt.event.ActionEvent evt) {
        decipher();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Vigenere().setVisible(true);
        });
    }

    private javax.swing.JButton btnDecipher;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btn_import;
    private javax.swing.JComboBox<String> cb_keySize;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbPath;
    private javax.swing.JLabel lb_keyGuessed;
    private javax.swing.JLabel lb_keySize;
    private javax.swing.JMenuBar mn;
    private javax.swing.JMenu mn1;
    private javax.swing.JMenu mn2;
    private javax.swing.JMenuItem mn_about;
    private javax.swing.JMenuItem mn_exit;
    private javax.swing.JMenuItem mn_new;
    private javax.swing.JPanel pn_cipherText;
    private javax.swing.JPanel pn_clearText;
    private javax.swing.JPanel pn_dataInput;
    private javax.swing.JPanel pn_dataOutput;
    private javax.swing.JTextPane txtCipher;
    private javax.swing.JTextPane txtDecipher;
    private javax.swing.JTextField txtKeyGuessed;
    private javax.swing.JTextField txtPath;
}
