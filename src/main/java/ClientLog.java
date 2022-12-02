import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientLog {
    StringBuilder builder = new StringBuilder("productNum, amount\n");

    public void log(int productNum, int amount) {
        builder.append(String.format("%d,%d\n", productNum + 1, amount));
    }

    public void exportAsCSV(File txtFile) throws IOException {
        FileWriter writer = new FileWriter(txtFile);
        writer.write(String.valueOf(builder));
        writer.close();

    }

}


