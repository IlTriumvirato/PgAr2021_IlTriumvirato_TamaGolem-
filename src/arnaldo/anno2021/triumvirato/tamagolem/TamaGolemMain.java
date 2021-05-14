package arnaldo.anno2021.triumvirato.tamagolem;

public class TamaGolemMain {

	private static void defineDefaultElements() {
		GlobalValues.generateElementsList(Constants.DEFAULT_ELEMENTS_NAMES);
	}
	
	private static void activateMenuScreen() {
		MenuScreen main_menu=new MenuScreen(Constants.GAME_NAME,Constants.MENU_SELECTION_OPTIONS);
		main_menu.loopMenu();
	}
	
	public static void main(String args[]) {
		defineDefaultElements();
		
		activateMenuScreen();
	}
}
