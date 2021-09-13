import java.sql.*;
import java.util.Scanner;

public class DB {

    public static void main(String[] args) {

        try {

            String dbUrl = "jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db";
            Connection conn = DriverManager.getConnection(dbUrl);

            if( conn != null ) {
                DatabaseMetaData databaseMetadata = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Connected to " + databaseMetadata.getDatabaseProductName() + " " + databaseMetadata.getDatabaseProductVersion());

                // CREATING categories TABLE
                Statement statement = conn.createStatement();
                String sqlStatement =
                        "CREATE TABLE IF NOT EXISTS categories"+
                                "(category_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "category_name TEXT NOT NULL)";
                statement.execute(sqlStatement);
            }

        } catch( SQLException exception ) {
            System.out.println("An error has occurred" + exception.toString());
        }
    }
    public void createProject(Project project) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();
        String sqlStatement =
                "CREATE TABLE IF NOT EXISTS projects"+
                        " (project_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "project_name TEXT NOT NULL," +
                        "description TEXT NOT NULL," +
                        "totalFinances INT NOT NULL)";
        statement.execute(sqlStatement);

        statement.execute("INSERT INTO projects (project_name, description, totalFinances) VALUES ('" +  project.getProjectName() + "' , '" +  project.getDescription() + "' , '" +  project.getTotalFinances() + "')");

    }

    public static void selectProjects() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();
        String sqlStatement = "SELECT * FROM projects";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while ( resultSet.next() ) {
            String projectID = resultSet.getString("project_id");
            String projectName = resultSet.getString("project_name");
            String description = resultSet.getString("description");
            int totalFinances = resultSet.getInt("totalFinances");

            System.out.println("Project ID:" + projectID + " | Project name: " + projectName + " | Description: " + description + " | Budget:" + totalFinances);
        }

    }

    public static void createEvent() throws SQLException {
        Scanner scan = new Scanner(System.in);

        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();
        String sqlStatement = "CREATE TABLE IF NOT EXISTS events" +
                " (events_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "project_event TEXT NOT NULL," +
                "events_name TEXT NOT NULL," +
                "totalFinancesForEvent TEXT NOT NULL)";
        statement.execute(sqlStatement);

        System.out.println("Enter the Project name: ");
        String projectName = scan.next();
        System.out.println("Enter the Event name: ");
        String eventName = scan.next();
        System.out.println("Enter the total Finances for Event: ");
        int totalFinancesForEvent = scan.nextInt();
        statement.execute("INSERT INTO events (project_event, events_name, totalFinancesForEvent) VALUES ('" +  projectName + "' , '" +  eventName + "' , '" +  totalFinancesForEvent + "')");

    }

    public void selectEvents() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the Project name to find all Events:");
        String projectNameUserInput = scan.nextLine();
        String sqlStatement = "SELECT * FROM events WHERE project_event = '" + projectNameUserInput + "'";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while ( resultSet.next() ) {
            String eventsID = resultSet.getString("events_id");
            String projectName = resultSet.getString("project_event");
            String eventName = resultSet.getString("events_name");
            int totalFinancesForEvent = resultSet.getInt("totalFinancesForEvent");

            System.out.println("Event ID:" + eventsID + " | Project name: " + projectName + " | Event name: " + eventName + " | Budget for Event:" + totalFinancesForEvent);
        }
    }

    public static void createBill(Bill bill) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();
        String sqlStatement =
                "CREATE TABLE IF NOT EXISTS bills"+
                        " (bill_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "bill_number INTEGER NOT NULL," +
                        "company TEXT NOT NULL," +
                        "sum FLOAT NOT NULL," +
                        "project TEXT NOT NULL," +
                        "event TEXT NOT NULL,"  +
                        "category TEXT NOT NULL)";
        statement.execute(sqlStatement);

        statement.execute("INSERT INTO bills (bill_number, company, sum, project, event, category) VALUES ('" +  bill.getBillNumber() + "' , '" +  bill.getCompany() + "' , '" +  bill.getSum() + "' , '" + bill.getProjectName() + "' , '" +  bill.getEventName() + "' , '" +  bill.getCategory() + "')");

    }

    public static void selectBills() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();
        String sqlStatement = "SELECT * FROM bills";
        ResultSet resultSet = statement.executeQuery(sqlStatement);

        while ( resultSet.next() ) {
            String billID = resultSet.getString("bill_id");
            String billNumber = resultSet.getString("bill_number");
            String company = resultSet.getString("company");
            String eventName = resultSet.getString("event");
            float sum = resultSet.getFloat("sum");
            String category = resultSet.getString("category");

            System.out.println("Bill ID:" + billID + " | Bill number:" + billNumber + " | Company: " + company + " | Event name: " + eventName + " | Category: " + category + " | Bills sum:" + sum + " EUR");
        }
    }

    public void deleteBill() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Bills ID to delete: ");
        int billIDdelete = scan.nextInt();
        scan.nextLine();

        String sqlStatement = "DELETE FROM bills " +
                " WHERE bill_id = '" + billIDdelete + "'";
        statement.execute(sqlStatement);
    }

    public static void sumOfBills() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\IdeaProjects\\ccappFinal\\ccapp.db");
        Statement statement = conn.createStatement();

        String sqlStatement = "SELECT SUM(sum) AS totalspent FROM bills";
        PreparedStatement pst = conn.prepareStatement(sqlStatement);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String SUM = rs.getString("totalspent");
            System.out.println("Total Budget is 1000 EUR | Total amount of bills: " + SUM + " EUR");

        }
    }

}