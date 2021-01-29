/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 *
 * @author User
 */
public class chatclient {

    BufferedReader in;
    PrintWriter out;
    JFrame jp=new JFrame("Chat APP");
    JTextField tx =new JTextField(50);
    JTextArea ta =new JTextArea(8,50);


    public chatclient(){
        tx.setEditable(false);
        ta.setEditable(false);
        tx.setBounds(140, 70, 300,30);
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        tx.setFont(font1);
        jp.getContentPane().add(tx,"North");
        jp.getContentPane().add(new JScrollPane(ta),"Center");
        jp.pack();

        tx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println(tx.getText());
                tx.setText("");

            }
        });

    }
    private String getseverIP(){

        return JOptionPane .showInputDialog(
                jp,
                "Enter IP Adress Of server ",
                "weclome to chatter app",
                JOptionPane .QUESTION_MESSAGE
        );

    }

    private String getname(){

        return JOptionPane .showInputDialog(
                jp,
                "Enter Chat name ",
                "Chat name selection",
                JOptionPane .PLAIN_MESSAGE
        );
    }
    void run1() throws IOException {
       // Scanner sc =new Scanner(System.in);
       // String getm;
       // String getip;
       // String getname1;
        //getip=sc.next();

        String sIP=getseverIP();
        Socket s =new Socket(sIP,9861);
        in =new BufferedReader(new InputStreamReader(s.getInputStream()));
        out =new PrintWriter(s.getOutputStream(),true);


        while (true){
            System.out.println("before line");
            String line=in.readLine();
            System.out.println("affter line");
            System.out.println(line);

            if (line.startsWith("SUBMITTENAME")){
               // getname1 =sc.next();

                out.println(getname());

            }else if (line.startsWith("NAMEACEPETED")){
                tx.setEditable(true);
            }else if (line.startsWith("MESSAGE")){
               ta.append(line.substring(7)+"\n");
            }
        }

    }

    


}

