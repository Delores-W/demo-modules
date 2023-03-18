package $_020_serial.gson;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * @author William
 * @date 4/25/21 1:04 PM
 * @description
 */
public class Test {

    public static void main(String[] args) {

//        {
//            "title": "Java Puzzlers: Traps, Pitfalls, and Corner Cases",
//                "isbn-10": "032133678X",
//                "isbn-13": "978-0321336781",
//                "authors": [
//                    "Joshua Bloch",
//                    "Neal Gafter"
//                ]
//        }

        Book book = new Book();
        book.setTitle("Hello World");
        book.setAuthors(new String[] {"Delores", "William", "Jack"});
        book.setIsbn10("ISBN10!!!");
        book.setIsbn13("ISBN13!!!");

        // config
        Gson gson = new GsonBuilder().create();
        // 序列化
        final String jsonString = gson.toJson(book);
        System.out.println(jsonString);
        // {"authors":["Delores","William","Jack"],"isbn10":"ISBN10!!!","isbn13":"ISBN13!!!","title":"Hello World"}

        // 反序列化
        Book book1 = gson.fromJson(jsonString, Book.class);
        System.out.println(book1);
    }
}
