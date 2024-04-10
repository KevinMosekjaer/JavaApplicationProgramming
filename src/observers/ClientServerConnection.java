package observers;

/**
 * Interface for Client/Server connection
 * 
 * @author Kevin Mosekjaer
 */
public interface ClientServerConnection {

	public void clientConnect(String name, String address, int port);

	public void serverConnect(String name, int port);

	public void clientDisconnect();

	public void serverDisconnect();

}
