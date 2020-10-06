/**
 * Sample Skeleton for 'Client_FTP.fxml' Controller Class
 */

package controleurs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.Socket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import ressources.Dossier;
import ressources.Fichier_Local;
import ressources.Fichier_Serveur;

/**
 * Classe controlleur principale, servant a initialiser et a attribuer les differentes actions
 * sur l'IHM
 * @author Clement Stoliaroff
 */
public class ControleurPrincipal {

    /**
     * 
     */
    @FXML // fx:id="hote"
    private TextField hote; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="identifiant"
    private TextField identifiant; // Value injected by FXMLLoader
    
    /**
     * 
     */
    @FXML
    private PasswordField password;

    /**
     * 
     */
    @FXML // fx:id="port"
    private TextField port; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="connexion"
    private Button connexion; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="deconnexion"
    private Button deconnexion; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="flow"
    private TextFlow flow; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="site_local"
    private ComboBox<String> site_local; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="local_tree"
    private TreeView<Dossier> local_tree; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="local_files"
    private TableView<Fichier_Local> local_files; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="filename_client"
    private TableColumn<?, ?> filename_client; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="filesize_client"
    private TableColumn<?, ?> filesize_client; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="filetype_client"
    private TableColumn<?, ?> filetype_client; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="lastmodified_client"
    private TableColumn<?, ?> lastmodified_client; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="description_local_files"
    private Label description_local_files; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="site_distant"
    private ComboBox<String> site_distant; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="server_tree"
    private TreeView<?> server_tree; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="server_files"
    private TableView<Fichier_Serveur> server_files; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="filename_server"
    private TableColumn<?, ?> filename_server; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="filestypes_server"
    private TableColumn<?, ?> filestypes_server; // Value injected by FXMLLoader
    
    /**
     * 
     */
    @FXML // fx:id="filesize_server"
    private TableColumn<?, ?> filesize_server; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="lastmodified_server"
    private TableColumn<?, ?> lastmodified_server; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="filesaccess_server"
    private TableColumn<?, ?> filesaccess_server; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="owner_server"
    private TableColumn<?, ?> owner_server; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="description_serveur_files"
    private Label description_serveur_files; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="tab_pane"
    private TabPane tab_pane; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="waiting_tab"
    private TableView<?> waiting_tab; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="waiting_files"
    private TableColumn<?, ?> waiting_files; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="waiting_direction"
    private TableColumn<?, ?> waiting_direction; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="waiting_distantfile"
    private TableColumn<?, ?> waiting_distantfile; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="waiting_size"
    private TableColumn<?, ?> waiting_size; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="waiting_priority"
    private TableColumn<?, ?> waiting_priority; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="waiting_status"
    private TableColumn<?, ?> waiting_status; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_success"
    private TableView<?> failed_success; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_file"
    private TableColumn<?, ?> failed_file; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_direction"
    private TableColumn<?, ?> failed_direction; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_distant"
    private TableColumn<?, ?> failed_distant; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_size"
    private TableColumn<?, ?> failed_size; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_priority"
    private TableColumn<?, ?> failed_priority; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_time"
    private TableColumn<?, ?> failed_time; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="failed_reason"
    private TableColumn<?, ?> failed_reason; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="success_tab"
    private TableView<?> success_tab; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="success_files"
    private TableColumn<?, ?> success_files; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="success_direction"
    private TableColumn<?, ?> success_direction; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="success_distant"
    private TableColumn<?, ?> success_distant; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="success_size"
    private TableColumn<?, ?> success_size; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="success_priority"
    private TableColumn<?, ?> success_priority; // Value injected by FXMLLoader

    /**
     * 
     */
    @FXML // fx:id="success_time"
    private TableColumn<?, ?> success_time; // Value injected by FXMLLoader
    
    /**
     * 
     */
    @FXML // fx:id="scrollpane"
    private ScrollPane scrollpane; // Value injected by FXMLLoader
    
    /**
     * 
     */
    private Socket socket;
    
    /**
     * 
     */
    private PrintStream ps;
    
    /**
     * 
     */
    private BufferedReader br;
    
    /**
     * 
     */
    private ContextMenu contextMenuLocal;
    
    /**
     * 
     */
    private ContextMenu contextMenuDistant;

    /**
     * @return
     */
    public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return
	 */
	public PrintStream getPs() {
		return ps;
	}

	/**
	 * @param ps
	 */
	public void setPs(PrintStream ps) {
		this.ps = ps;
	}

	/**
	 * @return
	 */
	public BufferedReader getBr() {
		return br;
	}

	/**
	 * @param br
	 */
	public void setBr(BufferedReader br) {
		this.br = br;
	}

	/**
	 * @return
	 */
	public TextField getHote() {
		return hote;
	}

	/**
	 * @return
	 */
	public TextField getIdentifiant() {
		return identifiant;
	}

	/**
	 * @return
	 */
	public PasswordField getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public TextField getPort() {
		return port;
	}

	/**
	 * @return
	 */
	public Button getConnexion() {
		return connexion;
	}

	/**
	 * @return
	 */
	public Button getDeconnexion() {
		return deconnexion;
	}

	/**
	 * @return
	 */
	public TextFlow getFlow() {
		return flow;
	}

	/**
	 * @return
	 */
	public ComboBox<String> getSite_local() {
		return site_local;
	}

	/**
	 * @return
	 */
	public TreeView<Dossier> getLocal_tree() {
		return local_tree;
	}

	/**
	 * @return
	 */
	public TableView<Fichier_Local> getLocal_files() {
		return local_files;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFilename_client() {
		return filename_client;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFilesize_client() {
		return filesize_client;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFiletype_client() {
		return filetype_client;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getLastmodified_client() {
		return lastmodified_client;
	}

	/**
	 * @return
	 */
	public Label getDescription_local_files() {
		return description_local_files;
	}

	/**
	 * @return
	 */
	public ComboBox<String> getSite_distant() {
		return site_distant;
	}

	/**
	 * @return
	 */
	public TreeView<?> getServer_tree() {
		return server_tree;
	}

	/**
	 * @return
	 */
	public TableView<Fichier_Serveur> getServer_files() {
		return server_files;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFilename_server() {
		return filename_server;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFilestypes_server() {
		return filestypes_server;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFilesize_server() {
		return filesize_server;
	}
	
	/**
	 * @return
	 */
	public TableColumn<?, ?> getLastmodified_server() {
		return lastmodified_server;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFilesaccess_server() {
		return filesaccess_server;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getOwner_server() {
		return owner_server;
	}

	/**
	 * @return
	 */
	public Label getDescription_serveur_files() {
		return description_serveur_files;
	}

	/**
	 * @return
	 */
	public TabPane getTab_pane() {
		return tab_pane;
	}

	/**
	 * @return
	 */
	public TableView<?> getWaiting_tab() {
		return waiting_tab;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getWaiting_files() {
		return waiting_files;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getWaiting_direction() {
		return waiting_direction;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getWaiting_distantfile() {
		return waiting_distantfile;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getWaiting_size() {
		return waiting_size;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getWaiting_priority() {
		return waiting_priority;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getWaiting_status() {
		return waiting_status;
	}

	/**
	 * @return
	 */
	public TableView<?> getFailed_success() {
		return failed_success;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFailed_file() {
		return failed_file;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFailed_direction() {
		return failed_direction;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFailed_distant() {
		return failed_distant;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFailed_size() {
		return failed_size;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFailed_priority() {
		return failed_priority;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFailed_time() {
		return failed_time;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getFailed_reason() {
		return failed_reason;
	}

	/**
	 * @return
	 */
	public TableView<?> getSuccess_tab() {
		return success_tab;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getSuccess_files() {
		return success_files;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getSuccess_direction() {
		return success_direction;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getSuccess_distant() {
		return success_distant;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getSuccess_size() {
		return success_size;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getSuccess_priority() {
		return success_priority;
	}

	/**
	 * @return
	 */
	public TableColumn<?, ?> getSuccess_time() {
		return success_time;
	}
	
	/**
	 * 
	 * @return
	 */
	public ContextMenu getContextMenuLocal() {
		return contextMenuLocal;
	}

	/**
	 * 
	 * @return
	 */
	public ContextMenu getContextMenuDistant() {
		return contextMenuDistant;
	}
	
	

	/**
	 * @param contextMenuLocal
	 */
	public void setContextMenuLocal(ContextMenu contextMenuLocal) {
		this.contextMenuLocal = contextMenuLocal;
	}

	/**
	 * @param contextMenuDistant
	 */
	public void setContextMenuDistant(ContextMenu contextMenuDistant) {
		this.contextMenuDistant = contextMenuDistant;
	}
	
	/**
	 * @return the scrollpane
	 */
	public ScrollPane getScrollpane() {
		return scrollpane;
	}

	/**
	 * Initialise les elements de l'IHM
	 */
	public void initialize()
    {
		// Initialise le tableau de fichiers locaux
    	new InitLocalFiles().exec(this);
    	
    	// Initialise le chemin courant
    	new InitCurrentDirectory().exec(this);
    	
    	//Initialise le treeview local
    	new InitLocalTree().exec(this);
    	
    	// Initialise le tableau de fichiers distants
    	new InitServerFiles().exec(this);
    	
    	// Initialise le ContextMenu des fichiers locaux
    	new InitContextMenuLocal().exec(this);
    	
    	// Initialise le ContextMenu des fichiers serveurs
    	new InitContextMenuDistant().exec(this);
    	
    	// Desactive le bouton de deconnexion
    	this.deconnexion.setDisable(true);
	}
    
    /**
     * Change le repertoire local a l'aide du treeview
     */
    @FXML
    public void treeLocalCD()
    {
    	new LocalTreeCD().exec(this);
    }
    
    /**
     * Etablis la connexion avec le serveur
     */
    @FXML
    public void connexion()
    {
    	new Connexion().exec(this);
    }
    
    /**
     * Permet de se deconnecter du serveur
     */
    @FXML
    public void deconnexion()
    {
    	new Deconnexion().exec(this);
    }
    
    /**
     * Change le repertoire local en double cliquant sur un element du tableau
     * @param event
     */
    @FXML
    public void tabLocalCD(MouseEvent event)
    {
    	new TabLocalCD().exec(this, event);
    }
    
    /**
     * Change le repertoire distant en double cliquant sur un element du tableau
     * @param event
     */
    @FXML
    public void tabDistantCD(MouseEvent event)
    {
    	new TabDistantCD().exec(this, event);
    }
    
   /**
    * Affichage de la boite d'information a propos
    * @param event
    * @throws FileNotFoundException 
    */
    @FXML
    void ActionAbout(ActionEvent event) throws FileNotFoundException
    {
        new About().exec(this);
    }
    
    /**
     * Ouvre le ContextMenu des fichiers locaux
     * @param event
     */
    @FXML
    public void openContextMenuLocal(ContextMenuEvent event)
    {
    	this.contextMenuLocal.show(this.local_files, event.getScreenX(), event.getScreenY());
    }
    
    /**
     * Ouvre le ContextMenu des fichiers serveur
     * @param event
     */
    @FXML
    public void openContextMenuDistant(ContextMenuEvent event)
    {
    	this.contextMenuDistant.show(this.local_files, event.getScreenX(), event.getScreenY());
    }
}