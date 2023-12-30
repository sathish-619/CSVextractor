package dhoni;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Mainfunction {
    public static void main(String[] args) {
        try {
            Connection connection = Jdbcexe.getConnection();
            String db_table = "yourtable";
            List<String> data = Jdbcexe.getDataFromTable(connection, db_table);
            List<String> header = Jdbcexe.getHeader(connection, db_table);
            String op_path = "C:\\Users\\User\\Desktop\\" + db_table + ".csv";
            generateCSV(header, data, op_path);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void generateCSV(List<String> header, List<String> data, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            
            writeLine(writer, header);

            
            for (String row : data) {
                writeLine(writer, Arrays.asList(row.split(",")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLine(FileWriter writer, List<String> values) throws IOException {
        StringBuilder line = new StringBuilder();
        for (String value : values) {
            if (line.length() > 0) {
                line.append(",");
            }
            line.append(value);
        }
        line.append("\n");
        writer.append(line.toString());
    }
}
