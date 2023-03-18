package $_020_serial.fastjson;

import com.alibaba.fastjson.JSONArray;
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

        // 序列化
        String jsonString = JSONObject.toJSONString(book);
        System.out.println(jsonString);

        // 反序列化
        // 方式一：
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        String title = jsonObject.getString("title");
        String authors = jsonObject.getString("authors");

        JSONArray jsonArray = jsonObject.getJSONArray("authors");
        // ["Delores","William","Jack"]
        System.out.println(title);
        System.out.println(authors);

        Book book1 = new Book();
        // set

        //反序列化方式二
        Book book2 = JSONObject.parseObject(jsonString, Book.class);
        System.out.println(book2);


    }
}
