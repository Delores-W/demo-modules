package socket;

import java.io.*;
import java.net.Socket;

/**
 * @author William
 * @date 2022/4/6 2:46 PM
 * @description
 */
public class DeloresClient {
    public static void main(String[] args) {
        String serverName = "localhost";
        int port = 8888;

        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//
//    int binarySearch(int arr[], int l, int r, int target) {
//        if (l > r) return -1;
//        int mid = l + (r - l) / 2;
//        if (arr[mid] == target) return mid;
//        else if (arr[mid] > target)
//            return binarySearch(arr, l, mid - 1, target);    // 左边
//        else return binarySearch(arr, mid + 1, r, target);   // 右边
//    }
//
//    int sum(int n) {
//        if (n == 0) return 0;
//        return n + sum(n - 1);
//    }
//
//    //递归深度：logn
//    // 时间复杂度：O(logn)
//    double pow(double x, int n) {
//        if (n == 0)
//            return 1.0;
//        double t = pow(x, n / 2);
//        if (n % 2)
//            return x * t * t;
//        return t * t;
//    }

}
