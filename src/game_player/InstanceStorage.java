package game_player;

public class InstanceStorage {
	
	ViewManager viewManager;
	Menu menu;
	DataManager dataManager;
	PulldownFactory pullDownFactory;
	PlayerView playerView;
	ButtonMaker buttonMaker;
	
	public void setViewManager(ViewManager vm) {
		this.viewManager = vm;
	}
	public void setMenu(Menu m) {
		this.menu = m;
	}
	public void setDataManager(DataManager dm) {
		this.dataManager = dm;
	}
	public void setPullDownFactory(PulldownFactory pdf) {
		this.pullDownFactory = pdf;
	}
	public void setPlayerView(PlayerView pv) {
		this.playerView = pv;
	}
	public void setButtonMaker(ButtonMaker bm) {
		this.buttonMaker = bm;
	}
	public ViewManager getViewManager() {
		return this.viewManager;
	}
	public Menu getMenu() {
		return this.menu;
	}
	public DataManager getDataManager() {
		return this.dataManager;
	}
	public PulldownFactory getPullDownFactory() {
		return this.pullDownFactory;
	}
	public PlayerView getPlayerView() {
		return this.playerView;
	}
	public ButtonMaker getButtonMaker() {
		return this.buttonMaker;
	}

}
