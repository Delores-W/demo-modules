package $_020_serial.gson;

import lombok.Data;

/**
 * @author William
 * @date 4/25/21 12:59 PM
 * @description
 */
@Data
public class Book {
    private String[] authors;
    private String isbn10;
    private String isbn13;
    private String title;
}
