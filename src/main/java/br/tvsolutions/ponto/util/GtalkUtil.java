//package br.tvsolutions.ponto.util;
//
//import java.util.Collection;
//import java.util.List;
//
//import org.jivesoftware.smack.Chat;
//import org.jivesoftware.smack.ChatManagerListener;
//import org.jivesoftware.smack.ConnectionConfiguration;
//import org.jivesoftware.smack.MessageListener;
//import org.jivesoftware.smack.Roster;
//import org.jivesoftware.smack.RosterListener;
//import org.jivesoftware.smack.XMPPConnection;
//import org.jivesoftware.smack.XMPPException;
//import org.jivesoftware.smack.packet.Message;
//import org.jivesoftware.smack.packet.Presence;
//import org.jivesoftware.smack.packet.Presence.Mode;
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import br.tvsolutions.ponto.entities.Ponto;
//import br.tvsolutions.ponto.entities.Usuario;
//import br.tvsolutions.ponto.mediators.TPontoMediatorScala;
//
//public class GtalkUtil {
//
//	private XMPPConnection connection;
//	private Roster roster;
//
//	@Autowired
//	private TPontoMediatorScala pontoMediatorScala;
//
//	public boolean verificarSeUsuarioEstaCadastrado(String userEmailGtalk) {
//		return roster.contains(userEmailGtalk);
//	}
//
//	/*
//	 * Verifica a Lista de Usuario, ver quem quer o sistema Gtalk ativo, e
//	 * cadastra no usuario configurado.
//	 */
//	public void cadastrarUsuario() {
//
//	}
//
//	public void enviarMsg(String userEmailGtalk, String msgString) {
//		if (!roster.contains(userEmailGtalk)) {
//			try {
//				roster.createEntry(userEmailGtalk, userEmailGtalk, null);
//			} catch (XMPPException e) {
//				e.printStackTrace();
//			}
//		}
//		 Message msg = new Message(userEmailGtalk, Message.Type.chat);
//		 msg.setBody(msgString);
//		 connection.sendPacket(msg);
//		 System.out.println("Foi enviada a msg para: " + userEmailGtalk);
//
//	}
//
//	public GtalkUtil() {
//		ConnectionConfiguration config = new ConnectionConfiguration(
//				"talk.google.com", 5222, "gmail.com");
//		config.setSASLAuthenticationEnabled(true);
//		config.setReconnectionAllowed(true);
//		connection = new XMPPConnection(config);
//		try {
//			System.out.println("Conectando ao Gtalk...");
//			connection.connect();
//			connection.login("email@gmail.com", "senha");
//			Presence presence = new Presence(Presence.Type.available);
//			connection.sendPacket(presence);
//			roster = connection.getRoster();
//		} catch (XMPPException e) {
//			e.printStackTrace();
//		}
//
//		roster.addRosterListener(new RosterListener() {
//			public void entriesAdded(Collection<String> addresses) {
//				System.out.println("Foi Adicionado: " + addresses);
//			}
//
//			public void entriesDeleted(Collection<String> addresses) {
//				System.out.println("Foi Deletado: " + addresses);
//			}
//
//			public void entriesUpdated(Collection<String> addresses) {
//				System.out.println("Foi feito Update: " + addresses);
//			}
//
//			public void presenceChanged(Presence presence) {
//				System.out.println("Teve Mudança de Estado: "+ presence.getFrom() + " - " + presence);
//				if(presence.getMode() != Mode.away && presence.getMode() != Mode.dnd){
//					enviarMsg(presence.getFrom().split("/")[0],"Vai Bater o Ponto ?");
//				}
//			}
//		});
//
//		connection.getChatManager().addChatListener(new ChatManagerListener() {
//			public void chatCreated(Chat chat, boolean arg1) {
//				chat.addMessageListener(new MessageListener() {
//					public void processMessage(Chat chat, Message msg) {
//						try {
//							if (msg.getBody().trim().equalsIgnoreCase("help")) {
//								chat.sendMessage("Help do Sistema de Ponto");
//							} else if (msg.getBody().trim().equalsIgnoreCase(
//									"list")) {
//								listaUsuarioOnLine(chat);
//							} else {
//								chat.sendMessage("Comando: " + msg.getBody()
//										+ " não identificado.");
//							}
//						} catch (XMPPException e) {
//							e.printStackTrace();
//						}
//					}
//
//				});
//			}
//		});
//
//		Chat chat2 = connection.getChatManager().createChat(
//				"tassiovirginio@gmail.com", "Teste", new MessageListener() {
//					public void processMessage(Chat chat, Message msg) {
//						System.out
//								.println(msg.getFrom() + ": " + msg.getBody());
//						try {
//							chat.sendMessage(msg.getBody());
//						} catch (XMPPException e) {
//							e.printStackTrace();
//						}
//					}
//				});
//		try {
//			chat2.sendMessage("Ponto Ligado");
//		} catch (XMPPException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void listaUsuarioOnLine(Chat chat) throws XMPPException {
//		chat.sendMessage("Lista Todos os Usuario OnLine");
//		List<Ponto> listaPonto2 = pontoMediatorScala.listaPontoUsuario(new DateTime());
//		String listaUsuario = "";
//		for (Ponto ponto : listaPonto2) {
//			Usuario user = ponto.getUsuario();
//			listaUsuario += user.getNome() + " - ";
//		}
//		chat.sendMessage("(" + listaPonto2.size() + ")" + listaUsuario);
//	}
//
//}
