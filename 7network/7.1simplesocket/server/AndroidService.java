import java.io.IOException;  
import java.net.ServerSocket;  
import java.net.Socket;  
import java.util.ArrayList;  
import java.util.List;  

public class AndroidService {  


    public static void main(String[] args) throws IOException {  
        ServerSocket serivce = new ServerSocket(5580);  
        while (true) {  
            //�ȴ��ͻ�������  
            Socket socket = serivce.accept();  
            new Thread(new SocketRunable(socket)).start();  
        }  
    }  

}  
