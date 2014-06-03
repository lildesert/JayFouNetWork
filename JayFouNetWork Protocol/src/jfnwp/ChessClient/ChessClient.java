package jfnwp.ChessClient;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChessClient {

	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException e) {
        } catch (ClassNotFoundException e) {
        } catch (UnsupportedLookAndFeelException e) {
        } catch (IllegalAccessException e) {
        }
        ChessMenu menu = new ChessMenu();
	}
	
}
