package com.example.Rest_CRUD_JPA;//package com.example.Rest_CRUD_JPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Used to execute SQL queries

    @Override
    public void run(String... args) throws Exception {
        loadDataFromSQLFile();
    }

    private void loadDataFromSQLFile() throws IOException, SQLException {
        // Specify the path to your SQL file
        String sqlFilePath = "src/main/resources/data.sql";  // Ensure the path is correct

        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath))) {
            StringBuilder sqlQuery = new StringBuilder();
            String line;

            // Read each line of the SQL file
            while ((line = reader.readLine()) != null) {
                sqlQuery.append(line).append("\n");
            }

            // Execute the SQL queries
            String[] queries = sqlQuery.toString().split(";");
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    jdbcTemplate.execute(query.trim());
                }
            }
        }
    }
}
