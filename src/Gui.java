import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Gui extends JFrame implements ActionListener{
    Map map;
    final static int Length =40, Width =40; //���Ϳ�
    JButton[][] buttons; //һ����ť��ʾһ��ϸ��
    boolean[][] select;  //��ť�Ƿ�ѡ��
    JButton clearmap, start, stop, exit;
    boolean isRunning = false;

    public Gui(String name){
        super(name);
        initGui();
    }
    public void initGui() {
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new GridLayout(Width, Length));
        JPanel panel3 = new JPanel();
        this.setContentPane(panel1);
        panel1.add(panel2, "Center");
        panel1.add(panel3, "South");
        buttons = new JButton[Width][Length];
        select = new boolean[Width][Length];
        map = new Map(Length, Width);
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Length; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setBackground(Color.WHITE);
                map.getMap()[i][j].isLiving = 0;
                panel2.add(buttons[i][j]);
            }
        }
        // ���ô���
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clearmap = new JButton("���ϸ��");
        start = new JButton("��ʼ��Ϸ");
        stop = new JButton("��ͣ");
        exit = new JButton("�˳�");

        panel3.add(start);
        panel3.add(stop);
        panel3.add(clearmap);
        panel3.add(exit);

        // ע�������
        this.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });
        clearmap.addActionListener(this);
        exit.addActionListener(this);
        start.addActionListener(this);
        stop.addActionListener(this);
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Length; j++) {
                buttons[i][j].addActionListener(this);
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== clearmap){
            map.clearMap();
            Show();
        }else if (e.getSource() == start) { //��ʼ
            isRunning = true;
            new Thread(new Runnable() {
                public void run() {
                    while (isRunning) {
                        Cell[][] next = map.update();
                        Show();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }


                        int flag = 1;
                        for(int i =0;i<Width;i++) {
                            for (int j = 0; j < Length; j++) {
                                if ( next[i][j].isLiving!=0) {
                                    flag = 0;
                                    break;
                                }
                            }
                            if(flag==0){
                                break;
                            }
                        }
                        if(flag==1){
                            isRunning=false;
                        }

                    }
                }
            }).start();

        }else if (e.getSource() == stop) { //��ͣ
            isRunning = false;
        }else if (e.getSource() == exit) { //�˳�
            System.exit(0);
        }else {
            Cell[][] cells = map.getMap();
            for (int i = 0; i < Width; i++) {
                for (int j = 0; j < Length; j++) {
                    if (e.getSource() == buttons[i][j]) {
                            buttons[i][j].setBackground(Color.BLACK);
                            cells[i][j].isLiving = 1;
                        break;
                    }
                }
            }
            map.setMap(cells);
        }
    }

    public void Show(){
        Cell[][] map2 = map.getMap();
        for (int i = 0; i < Width; i++) {
            for (int j = 0; j < Length; j++) {
                if (map2[i][j].isLiving == 1) {
                    buttons[i][j].setBackground(Color.BLACK); //����ʾ��ɫ
                } else {
                    buttons[i][j].setBackground(Color.WHITE); //������ʾ��ɫ
                }
            }
        }
    }
}

