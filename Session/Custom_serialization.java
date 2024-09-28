
//自定义序列化策略
import java.io.*;

public class CustomSerialization implements Serializable {
    private String data;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
