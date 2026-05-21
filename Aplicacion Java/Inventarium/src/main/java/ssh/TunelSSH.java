/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 *
 * @author Equipo1
 */

public class TunelSSH {
    
    private static final String SSH_USER     = "equipo1_mv2";
    private static final String SSH_HOST     = "10.0.10.39";
    private static final int    SSH_PORT     = 22;
    private static final String SSH_PASSWORD = "equipo1";

    private static final String DB_HOST      = "192.168.56.10";
    private static final int    DB_PORT      = 3306;  // 3306 MySQL
    private static final int    LOCAL_PORT   = 5433;  // Puerto local para JDBC
    // --------------------------------

    private Session sshSession;

    public void abrirTunel() throws Exception {
        JSch jsch = new JSch();

        sshSession = jsch.getSession(SSH_USER, SSH_HOST, SSH_PORT);
        sshSession.setPassword(SSH_PASSWORD);
        sshSession.setConfig("StrictHostKeyChecking", "no");
        sshSession.connect(5000); // timeout 5 segundos

        sshSession.setPortForwardingL(LOCAL_PORT, DB_HOST, DB_PORT);
        System.out.println("Tunel SSH abierto: localhost:" + LOCAL_PORT + " -> " + DB_HOST + ":" + DB_PORT);
    }

    public void cerrarTunel() {
        if (sshSession != null && sshSession.isConnected()) {
            sshSession.disconnect();
            System.out.println("Tunel SSH cerrado.");
        }
    }

    public boolean estaConectado() {
        return sshSession != null && sshSession.isConnected();
    }

}
