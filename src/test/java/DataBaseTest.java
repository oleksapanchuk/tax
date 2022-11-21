import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.controller.TaxController;
import com.panchuk.tax.dao.DAOFactory;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import com.panchuk.tax.model.tax.TaxTypeIncome;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataBaseTest {
    private static final String CONNECTION_URL = "jdbc:derby:memory:tax;create=true";

    private static final String SHUTDOWN_URL = "jdbc:derby:;shutdown=true";

    private static final String APP_PROPS_FILE = "app.properties";

    private static final String APP_CONTENT = "connection.url=" + CONNECTION_URL;

    private static final String CREATE_SCHEMA = "CREATE SCHEMA taxes";
    private static final String DROP_SCHEMA = "DROP SCHEMA taxes RESTRICT";
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE users (id INT NOT NULL," +
                    "  first_name VARCHAR(45) NOT NULL," +
                    "  last_name VARCHAR(45) NOT NULL," +
                    "  sex VARCHAR(20) NOT NULL," +
                    "  email VARCHAR(100)," +
                    "  date_of_birth DATE," +
                    "  PRIMARY KEY (id))";

    private static final String CREATE_TAX_TYPE_TABLE =
            "CREATE TABLE tax_type (" +
                    "  id INT NOT NULL," +
                    "  tax_name VARCHAR(255) NOT NULL," +
                    "  tax_multiplier DECIMAL(3,3) NOT NULL," +
                    "  tax_description VARCHAR(1000)," +
                    "  PRIMARY KEY (id))";

    private static final String CREATE_USER_TAX_TABLE =
            "CREATE TABLE user_tax (" +
                    "  id_payment INT NOT NULL," +
                    "  user_id INT NOT NULL," +
                    "  tax_id INT NOT NULL," +
                    "  value DECIMAL(10,5) NOT NULL," +
                    "  tax_amount DECIMAL(10,5) NOT NULL," +
                    "  payment_date DATE NOT NULL," +
                    "  PRIMARY KEY (id_payment)," +
                    "  CONSTRAINT fk_user_tax_user" +
                    "    FOREIGN KEY (user_id)" +
                    "    REFERENCES users (id)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT fk_user_tax_tax1" +
                    "    FOREIGN KEY (tax_id)" +
                    "    REFERENCES tax_type (id)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION)";

    private static final String DROP_USER_TAX_TABLE = "DROP TABLE  user_tax";

    private static final String DROP_TAX_TYPE_TABLE = "DROP TABLE tax_type";

    private static final String DROP_USER_TABLE = "DROP TABLE users";

    private static final String DERBY_LOG_FILE = "derby.log";

    private static Connection con;

    private static String userDefinedAppContent;


    @BeforeAll
    static void globalSetUp() throws SQLException, IOException {
        Path path = Path.of(APP_PROPS_FILE);
        userDefinedAppContent = Files.readString(path);
        Files.write(path, APP_CONTENT.getBytes());

        con = DriverManager.getConnection(CONNECTION_URL);
    }

    @AfterAll
    static void globalTearDown() throws SQLException, IOException {
        con.close();
        try {
            DriverManager.getConnection(SHUTDOWN_URL);
        } catch (SQLException ex) {
            System.err.println("Derby shutdown");
        }
        Files.delete(Path.of(DERBY_LOG_FILE));

        try (PrintWriter out = new PrintWriter(APP_PROPS_FILE)) {
            out.print(userDefinedAppContent);
        }
    }

    private DAOFactory daoFactory;

    @BeforeEach
    void setUp() throws SQLException {
        DAOFactory.setDAOFactoryFQN(ProjectConstant.DAO_FACTORY_FQN);
        daoFactory = DAOFactory.getInstance();

        con.createStatement().executeUpdate(CREATE_SCHEMA);
        con.createStatement().executeUpdate(CREATE_USER_TABLE);
        con.createStatement().executeUpdate(CREATE_TAX_TYPE_TABLE);
        con.createStatement().executeUpdate(CREATE_USER_TAX_TABLE);
    }

    @AfterEach
    void tearDown() throws SQLException {
        con.createStatement().executeUpdate(DROP_USER_TAX_TABLE);
        con.createStatement().executeUpdate(DROP_USER_TABLE);
        con.createStatement().executeUpdate(DROP_TAX_TYPE_TABLE);
        con.createStatement().executeUpdate(DROP_SCHEMA);
    }

    @Test
    void testCompliance() throws IOException {
        Path path = Path.of("sql_scripts/create_db.sql");
        assertTrue(Files.exists(path), "No db-create.sql file was found in a sql directory");

        List<String> lines = Files.readAllLines(path);

        assertFalse(lines.size() < 8, "Too small count of lines in a db-create.sql file");
    }


    @Test
    void testDemo() throws DAOException, SQLException {
        User ivanov = new User(
                1234,
                "Ivan",
                "Ivanov",
                User.Sex.male,
                "ivaonvich@gmail.com",
                "2004-09-08");
        User petrov = new User(
                1234,
                "Petro",
                "Petrov",
                User.Sex.male,
                "petrovich@gmail.com",
                "2000-12-10");

        daoFactory.getTaxDAO().insertUser(ivanov);
        daoFactory.getTaxDAO().insertUser(petrov);

        TaxType taxA = TaxController.createTax(
                31243,
                2,
                5353.312,
                "2022-09-04");
        TaxType taxB = TaxController.createTax(
                54656,
                1,
                9876.34,
                "2021-10-16");
        TaxType taxC = TaxController.createTax(
                86786,
                6,
                12043.321,
                "2006-12-30");

        List<TaxType> taxListA = new ArrayList<>();
        taxListA.add(taxA);
        taxListA.add(taxB);
        List<TaxType> taxListB = new ArrayList<>();
        taxListB.add(taxC);
        daoFactory.getTaxDAO().addTaxForUser(ivanov, taxListA);
        daoFactory.getTaxDAO().addTaxForUser(petrov, taxListB);

    }


}
