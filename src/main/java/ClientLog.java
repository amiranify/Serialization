import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientLog {
    StringBuilder builder = new StringBuilder("productNum, amount\n");

    public void log(int productNum, int amount) {
        builder.append(String.format("%d,%d\n", productNum + 1, amount));
    }

    public void exportAsCSV(File txtFile) throws IOException {
        try(PrintWriter writer = new PrintWriter(txtFile)) {
            writer.println(String.valueOf(builder));
        }
    }
}