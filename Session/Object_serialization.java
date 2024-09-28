
//Java对象序列化
import java.io.*;

public class SerializableUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    // 其他字段和方法...
}

    public void serializeUser(SerializableUser user) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.dat"))) {
            out.writeObject(user);
        }
    }

    public SerializableUser deserializeUser() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.dat"))) {
            return (SerializableUser) in.readObject();
        }
    }

