package game_engine.event.actions;

import game_engine.event.Action;
import game_player.PlayerView;

public class GameOverAction implements Action {
	private PlayerView playerView;
	
	public GameOverAction(PlayerView p) {
		playerView = p;
	}
	
	@Override
	public void execute() {
		//TODO: implement loadGameOverScreen() in Player
		playerView.loadGameOverScreen(); 
		
	}

}
