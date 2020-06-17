import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Document;
import org.junit.Test;
import site.dunhanson.redis.utils.ObjectUtils;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtilsTest {

    @Test
    public void stringTest() {
        // map对象
        String str = "hello,world";
        // 对象转二进制
        byte[] bytes = ObjectUtils.toByteArray(str);
        // 二进制转对象
        str = ObjectUtils.toEntity(bytes, String.class);
        System.out.println(str);
    }

    @Test
    public void listTest() {
        // map对象
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        // 对象转二进制
        byte[] bytes = ObjectUtils.toByteArray(list);
        // 二进制转对象
        Type type = new TypeToken<List<String>>(){}.getType();
        list = ObjectUtils.toEntity(bytes, type);
        System.out.println(list);
    }

    @Test
    public void documentTest() {
        List<Document> list = new ArrayList<>();
        list.add(Document.builder().id("1").build());
        list.add(Document.builder().id("2").build());
        list.add(Document.builder().id("3").build());
        System.out.println(ObjectUtils.toByteArray(list).length);
    }

}
