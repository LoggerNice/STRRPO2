package Practic_2;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    public static void main(String[] args) throws RemoteException, NamingException, InterruptedException, MalformedURLException, AlreadyBoundException {
        LocateRegistry.createRegistry(1999);
        IEmployee iEmployee = new IEmployeeImp();
        Context context = new InitialContext();
        context.bind("rmi://localhost:1999/employee", iEmployee);
    }
}

interface IEmployee extends Remote {
    String getData() throws RemoteException;
}

class IEmployeeImp extends UnicastRemoteObject implements IEmployee {

    public IEmployeeImp() throws RemoteException { }

    @Override
    public String getData() throws RemoteException {
        Connection conn = new Connection();
        return conn.getData();
    }
}
