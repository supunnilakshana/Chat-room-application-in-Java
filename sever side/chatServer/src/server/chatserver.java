/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.PrintWriter;
    import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 *
 * @author User
 */


public class chatserver {

    private static final int PORT =9861;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writters = new HashSet<PrintWriter>();


     public static void main(String[] args) throws Exception {

        System.out.println("Chat sever is stared");
         ServerSocket ss = new ServerSocket(PORT);
         try{
            while (true){
                Socket s= ss.accept();
                System.out.println("Connected ...........");

                Thread thread =new Thread(new Handler(s));
                thread.start();;
            }

         }catch(IOException e){
                System.out.println(e);
         }finally {
             ss.close();
         }

    }

    private static class Handler implements Runnable{// handle with individual client msgs
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket){
            this.socket=socket;
        }
        public void run() {

            try {
                in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out= new PrintWriter(socket.getOutputStream(),true);
                System.out.println("affter line");
                out.println("SUBMITTENAME");
                name=in.readLine();
                while(true){
                    if (name == null) {

                        return;
                    }
                    if (!names.contains(names)){

                            names.add(name);
                            break;
                    }
                }
                out.println("NAMEACEPETED");
                writters.add(out);

                while (true){
                    String input;
                    input=in.readLine();
                    for (PrintWriter writer:writters){
                        writer.println("MESSAGE"+  name+" : "+input);
                    }
                }

            }catch (IOException e){
                System.out.println(e);
            }finally {
                if (names != null) {
                    names.remove(name);


                }
                if (writters != null) {
                    writters.remove(out);

                }
                try {
                    socket.close();
                }catch (IOException e){
                    System.out.println(e);
                }

            }
        }


    }


}


