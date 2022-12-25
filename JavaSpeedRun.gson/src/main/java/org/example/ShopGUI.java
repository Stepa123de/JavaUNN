package org.example;

import java.awt.*;
import  java.awt.event.*;
import java.util.Set;
import javax.swing.*;

import static java.lang.Integer.parseInt;

public class ShopGUI  extends JFrame {

    private Shop sh = new Shop();

    private Set<Clock> Clocks;

    private Container container;

    private myFileWright mfw;



    public void AddElems()
    {

        container = new Container();
        container = this.getContentPane();
        container.setLayout(new GridLayout(3,0,20,25));
        JButton add = new JButton("ADD Clock");
        add.addActionListener(new ButtonAddListener(sh,this));

        container.removeAll();
        container.add(add);

        Clocks = sh.ShowClock();
        System.out.println(Clocks);
        for (Clock clock: Clocks)
        {

            JButton button = new JButton(clock.GetName());
            button.setIcon(new ImageIcon(clock.GetImage()));

            container.add(button);
            button.addActionListener(new ButtonEventListener(clock));
        }
        revalidate();
        repaint();
        mfw.myWrite(sh);

    }

    public  ShopGUI(String savePath)
    {
        super("Watch's Shop");
        this.setBounds(100,100,1000,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mfw = new myFileWright(savePath);
        sh = mfw.myRead();

        AddElems();
    }

    class ButtonEventListener implements ActionListener
    {
        Clock clock;

        public ButtonEventListener(Clock cl)
        {
            this.clock = cl;
        }

        public void actionPerformed(ActionEvent e)
        {

            String message = "";
            message += "Name: "+clock.GetName()+"\n";
            message += "Cost: "+clock.GetCost()+"$\n"  + "\n";

            //JOptionPane.showMessageDialog(null,message,"Output",JOptionPane.PLAIN_MESSAGE);
            int flag =JOptionPane.showConfirmDialog(null, message,"Buy Clock =>"+clock.GetName(), JOptionPane.YES_NO_OPTION);
            if (flag == 0)
            {
                sh.buy(clock);
                JButton jButton =(JButton) e.getSource();

                AddElems();

            }
        }
    }

    class ButtonAddListener implements ActionListener
    {
        private JFrame jFrame;
        private Container container;
        private JTextField name,cost;
        private JCheckBox sport;
        private JButton add,close;
        private boolean WindowIsOpen=false;
        private Shop sh;
        private JFrame mainFrame;
        ButtonAddListener(Shop sh,JFrame mainFrame)
        {
            this.sh = sh;
            this.mainFrame = mainFrame;
        }
        public void AddElements(Container container)
        {
            JLabel jLabel = new JLabel("Name");
            name = new JTextField();
            container.add(jLabel);
            container.add(name);

            jLabel = new JLabel("Cost");
            cost = new JTextField();
            container.add(jLabel);
            container.add(cost);

            jLabel = new JLabel("SportModel");
            sport = new JCheckBox();
            container.add(jLabel);
            container.add(sport);

            add = new JButton("Add");
            close = new JButton("Close");
            add.addActionListener(new NewListener(sh,name,cost,sport));
            close.addActionListener(new NewListener(sh,name,cost,sport));
            container.add(add);
            container.add(close);

        }

        public void actionPerformed(ActionEvent e)
        {
            if (WindowIsOpen == false)
            {
                WindowIsOpen =true;
                jFrame = new JFrame();
                jFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        WindowIsOpen =false;
                        AddElems();
                    }});
                container = jFrame.getContentPane();
                container.setLayout(new GridLayout(4,2));
                AddElements(container);
                jFrame.setBounds(100,100,150,200);
                jFrame.setVisible(true);

            }
        }


        class NewListener implements ActionListener
        {
            private  Shop shop;
            private  Clock clock;
            NewListener(Shop sh,JTextField name, JTextField cost, JCheckBox sport)
            {
                this.clock = clock;
                this.shop = sh;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jb =(JButton) e.getSource();
                if(jb.getText()=="Add")
                {
                    int i = (int) (Math.random()*7+1);
                    System.out.println(i);
                    if (sport.isSelected())
                    {
                        SportClock sc = new SportClock(0,0,parseInt(cost.getText()),name.getText());

                        sh.Append(sc);
                    }
                    else
                    {
                        BaseClock bc = new BaseClock(0,0,parseInt(cost.getText()),name.getText());

                        sh.Append(bc);
                    }
                    AddElems();
                }
                else if(jb.getText()=="Close")
                {

                }
                WindowIsOpen = false;
                jFrame.dispose();
            }


        }


    }
}
