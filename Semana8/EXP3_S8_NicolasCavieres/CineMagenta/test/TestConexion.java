import com.magenta.db.ConexionDB;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        try {
            Connection conn = ConexionDB.conectar();
            if (conn != null && !conn.isClosed()) {
                System.out.println("[TEST] Conexión exitosa a la base de datos.");
                conn.close();
            } else {
                System.out.println("[TEST] No se pudo establecer la conexión.");
            }
        } catch (Exception e) {
            System.err.println("[TEST] Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
