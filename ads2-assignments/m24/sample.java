import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
class sample {
public static void main(String[] args) throws IOException {
    String content = new String(Files.readAllBytes(Paths.get("tale.txt")));
    System.out.println(content.contains("It is a far, far better thing that I do, than I have ever done"));
	}
}