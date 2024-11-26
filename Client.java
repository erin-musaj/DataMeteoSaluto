import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args){
        try(Socket socket = new Socket("localhost",6969)){
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while(true){
                System.out.println("Effettua una richiesta: ");
                String msg = ui.readLine();
                out.writeBytes(msg + "\n");
                out.flush();
                System.out.println("Risposta dal Server: " + in.readLine());
            }

        }catch(IOException e){
            System.out.println("Errore di connessione");
        }
    }
}
