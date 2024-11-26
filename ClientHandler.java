import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.Instant;
import java.util.Date;

public class ClientHandler implements Runnable{

    private Socket cs;

    public ClientHandler(Socket cs) {
        this.cs = cs;
    }

    @Override
    public void run() {
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            DataOutputStream out = new DataOutputStream(cs.getOutputStream());
            String msg = "";
            while((msg = in.readLine()) != null){
                switch(msg){
                    case "Data":
                        out.writeBytes("La data di oggi è " + Date.from(Instant.now()) + "\n");
                        break;
                    case "Meteo":
                        out.writeBytes("Oggi è soleggiato" + "\n");
                        break;
                    case "Saluto":
                        out.writeBytes("Ciao! Come posso aiutarti?" + "\n");
                        break;
                    default:
                        out.writeBytes("Comando non riconosciuto" + "\n");
                        break;

                }
                System.out.println("Messaggio ricevuto da " + cs + " : " + msg);
                out.flush();
            }

            in.close();
            out.close();
            cs.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
