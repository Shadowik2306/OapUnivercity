import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Front extends JFrame implements ActionListener {
    private JPanel Data;
    private JButton addElementButton;
    private JButton deleteElementButton;
    private JButton removeElementsButton;
    private JTextField WeightField;
    private JTextField PriceField;
    private JTextField idOfItem;
    private JButton playAnimationButton;
    private JButton pauseAnimationButton;
    private JRadioButton speedOn;
    private JRadioButton speedOff;
    private JButton nextStepButton;
    private JSlider slider;
    private JButton clearAnimationButton;
    private JTable mainTable;
    private JPanel mainPanel;
    private JList<String> lst;
    private JTextField spaceOfBackpack;
    private JCheckBox lock;
    private JTable weightTable;
    private JLabel errorField;
    private JLabel Answer;
    private JTextArea answer;

    Timer timer = new Timer(20, this);
    double seconds = 0;
    int countOfItems = 0;

    boolean isPlaying = false;
    boolean isOnPause = false;
    boolean wasPlaying = false;

    Back backPack;
    ArrayDeque<SaveTableEncoding> deque;

    SaveTableEncoding STV = new SaveTableEncoding();

    Front() {
        super("Dynamic Backpack");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setVisible(true);
        setMinimumSize(new Dimension(700, 500));
        timer.start();
        lst.setModel(new DefaultListModel<String>());

        weightTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column <= STV.indexReal[0] && column != 0) {
                    c.setBackground(Color.YELLOW);
                    c.setForeground(Color.BLACK);
                    System.out.print(STV.indexReal[1]);
                }
                else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        mainTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (STV.forward) {
                    if (row == 0 && column == 0) {
                        c.setBackground(Color.ORANGE);
                        c.setForeground(Color.black);
                    } else if (row == 0) {
                        c.setBackground(Color.GREEN);
                        c.setForeground(Color.black);
                    } else if (column == 0) {
                        c.setBackground(Color.MAGENTA);
                        c.setForeground(Color.black);

                    } else if (STV.indexReal[0] + 1 == row && STV.indexReal[1] + 1 == column) {
                        c.setBackground(Color.YELLOW);
                        c.setForeground(Color.black);
                    } else if (STV.indexLast[0] + 1 == row && STV.indexLast[1] + 1 == column && row != 0) {
                        c.setBackground(Color.BLUE);
                        c.setForeground(Color.WHITE);
                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.black);
                    }
                    if (column >= STV.weightOfItem - (row + 1)  && row != 0) {
                        if (STV.posWeightItem != null) {
                            if (STV.posWeightItem[0] + 1 == row && STV.posWeightItem[1] + 1 == column) {
                                c.setBackground(Color.RED);
                                c.setForeground(Color.BLACK);

                            }
                        }
                        else {

                        }
                    }
                } else {
                    if (row == 0 && column == 0) {
                        c.setBackground(Color.ORANGE);
                        c.setForeground(Color.black);
                    } else if (row == 0) {
                        c.setBackground(Color.GREEN);
                        c.setForeground(Color.black);
                    } else if (column == 0) {
                        c.setBackground(Color.MAGENTA);
                        c.setForeground(Color.black);
                    } else if (STV.indexReal[0] + 1 == row && STV.indexReal[1] + 1 == column) {
                        c.setBackground(Color.YELLOW);
                        c.setForeground(Color.black);
                    }
                    else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.black);
                    }
                }
                return c;
            }
        });


        addElementButton.addActionListener(e -> {
            if (isNumeric(WeightField.getText()) && isNumeric(PriceField.getText())) {
                if (Integer.parseInt(WeightField.getText()) > 0 && Integer.parseInt(PriceField.getText()) > 0) {
                    addListElem(WeightField.getText(), PriceField.getText());
                }
            }
            else {
                errorField.setText("Not Number");
            }
            WeightField.setText("");
            PriceField.setText("");
        });

        deleteElementButton.addActionListener(e -> {
            removeListElem(idOfItem.getText());
            idOfItem.setText("");
        });

        lst.addListSelectionListener(e -> {
            if (lst.getSelectedIndex() >= 0) {
                idOfItem.setText(Integer.toString(lst.getSelectedIndex()));
            }
        });

        lock.addActionListener(e -> {
            removeElementsButton.setEnabled(lock.isSelected());
        });

        removeElementsButton.addActionListener(e -> {
            lst.setModel(new DefaultListModel<String>());
            lock.setSelected(false);
            removeElementsButton.setEnabled(false);
            countOfItems = 0;
        });

        playAnimationButton.addActionListener(e -> {
            if (wasPlaying) {
                isPlaying = true;
                isOnPause = false;
                pauseAnimationButton.setEnabled(true);
                playAnimationButton.setEnabled(false);
            }
            else {
                fillTable();

            }
        });

        pauseAnimationButton.addActionListener(e -> {
            if (isPlaying) {
                isPlaying = false;
                isOnPause = true;
                pauseAnimationButton.setEnabled(false);
                playAnimationButton.setEnabled(true);
            }
        });

        nextStepButton.addActionListener(e -> {
            if (isPlaying && !isOnPause) {
                nextValueOfTable();
            }
            repaint();
        });

        clearAnimationButton.addActionListener(e -> {
            if (wasPlaying) {
                wasPlaying = false;
                isPlaying = false;
                isOnPause = false;
                mainTable.setModel(new DefaultTableModel());
                weightTable.setModel(new DefaultTableModel());
                playAnimationButton.setEnabled(true);
                clearAnimationButton.setEnabled(false);
                pauseAnimationButton.setEnabled(false);
                nextStepButton.setEnabled(false);
                spaceOfBackpack.setEnabled(true);
                answer.setText("");
            }
        });
    }

    private void fillTable() {
        if (countOfItems < 1) {
            errorText("No Items");
            return;
        }
        if (!isNumeric(spaceOfBackpack.getText())) {
            errorText("Space must be a number.");
            return;
        }
        if (Integer.parseInt(spaceOfBackpack.getText()) <= 0) {
            errorText("Space must be more then 0.");
            return;
        }
        spaceOfBackpack.setEnabled(false);
        backPack = new Back(Integer.parseInt(spaceOfBackpack.getText()), countOfItems, getWight(), getPrices());
        fillWeightTable();
        baseOfMaintable();
        deque = backPack.getResult();
        isPlaying = true;
        wasPlaying = true;
        playAnimationButton.setEnabled(false);
        clearAnimationButton.setEnabled(true);
        pauseAnimationButton.setEnabled(true);
        nextStepButton.setEnabled(true);
    }

    private int[] getWight() {
        int[][] par = parseLst();
        int[] res = new int[par[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i] = par[0][i];
        }
        return res;
    }

    private int[] getPrices() {
        int[][] par = parseLst();
        int[] res = new int[par[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i] = par[1][i];
        }
        return res;
    }

    private void baseOfMaintable() {
        String[] column = new String[Integer.parseInt(spaceOfBackpack.getText()) + 2];
        String[][] data = new String[countOfItems + 2][Integer.parseInt(spaceOfBackpack.getText()) + 2];
        data[0][0] = "-";
        for (int j = 1; j < data[0].length; j++) {
            data[0][j] = Integer.toString(j - 1);
        }
        for (int i = 1; i < data.length; i++) {
            for (int j = 1; j < data[0].length; j++) {
                data[i][j] = "-";
            }
            data[i][0] = Integer.toString(i - 1);
        }
        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        mainTable.setModel(model);
    }

    private void fillWeightTable() {
        String[] column = new String[countOfItems + 1];
        String[][] data = new String[3][countOfItems + 1];
        int[][] WAndP = parseLst();
        data[0][0] = "Id";
        data[1][0] = "Weight";
        data[2][0] = "Price";
        for (int i = 1; i < countOfItems + 1; i++) {
            data[0][i] = Integer.toString(i);
            data[1][i] = Integer.toString(WAndP[0][i - 1]);
            data[2][i] = Integer.toString(WAndP[1][i - 1]);
        }
        DefaultTableModel model = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        weightTable.setModel(model);
    }

    private int[][] parseLst() {
        int[][] result = new int[2][countOfItems];
        DefaultListModel<String> model = (DefaultListModel<String>) lst.getModel();
        for (int i = 0; i < countOfItems; i++) {
            String s = model.getElementAt(i);
            result[0][i] = Integer.parseInt(s.split(" ")[1]);
            result[1][i] = Integer.parseInt(s.split(" ")[4]);
        }
        return result;
    }

    private void addListElem(String weigh, String price) {
        if (!(isNumeric(weigh) && isNumeric(price))) {
            errorText("Character must be a number.");
            return;
        }
        DefaultListModel<String> model = (DefaultListModel<String>) lst.getModel();
        model.add(countOfItems, "Weigh: " + weigh + " - Price: " + price);
        countOfItems++;
    }

    private void removeListElem(String id) {
        if (!isNumeric(id)) {
            errorText("Character must be a number.");
            return;
        }
        if (!(Integer.parseInt(id) < countOfItems && Integer.parseInt(id) >= 0)) {
            errorText("Element does not exist");
            return;
        }
        DefaultListModel<String> model = (DefaultListModel<String>) lst.getModel();
        model.removeElementAt(Integer.parseInt(id));
        countOfItems--;
    }

    private void errorText(String e) {
        errorField.setText(e);
    }

    public static void main(String[] args) {
        new Front();
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (speedOn.isSelected() && !isOnPause) {
            seconds += slider.getValue();
            if (seconds >= 500) {
                if (isPlaying) {
                    nextValueOfTable();
                }
                repaint();
                seconds -= 500;
            }

        }


    }

    public void nextValueOfTable() {
        if (!deque.isEmpty()) {
            SaveTableEncoding save = deque.getLast();
            DefaultTableModel model = (DefaultTableModel) mainTable.getModel();
            model.setValueAt(save.value, save.indexReal[0] + 1, save.indexReal[1] + 1);
            STV = save;
            deque.removeLast();
        }
        else {
            STV = new SaveTableEncoding();
            StringBuilder result = new StringBuilder("Final price: " + SaveTableEncoding.finalPrice + " items: ");
            for (int i = 0; i < SaveTableEncoding.countOfItems; i++) {
                result.append(SaveTableEncoding.finalItems[i]);
                result.append(", ");
            }
            answer.setText(result.toString());
        }



    }

}
