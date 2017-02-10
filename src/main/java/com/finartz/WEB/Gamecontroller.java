package com.finartz.WEB;

import com.finartz.WEB.model.GameRequest;
import com.finartz.WEB.model.GameResponse;
import com.finartz.logic.GameLogic;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * GameController
 */
@RestController
@RequestMapping("/game")
public class Gamecontroller implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	private String firstPlayerMove=null;
	private String secondPlayerMove=null;

	
	
	@RequestMapping(value = "/play/firstPlayerName/{playerName1}/secondPlayerName/{playerName2}", method = {RequestMethod.POST,RequestMethod.GET})
	public GameResponse play(@PathVariable ("playerName1") String playerName1,@PathVariable ("playerName2") String playerName2,GameRequest request) {

		GameLogic gameLogic = applicationContext.getBean(GameLogic.class);

		
		firstPlayerMove=gameLogic.getPlayerMove();
		secondPlayerMove=gameLogic.getPlayerMove();
		
		request.setFirstPlayerName(playerName1);
		request.setSecondPlayerName(playerName2);
		
		request.setFirstPlayerMove(firstPlayerMove);
		request.setSecondPlayerMove(secondPlayerMove);
		
		
		boolean same = gameLogic.isSame(request.getFirstPlayerMove(), request.getSecondPlayerMove());
		if (same) {
			GameResponse gameResponse = new GameResponse();
			gameResponse.setFirstPlayerWins(false); gameResponse.setSecondPlayerWins(false); gameResponse.setWinner("");
			gameResponse.setMessage(request.getFirstPlayerName() + " and " + request.getSecondPlayerName() + " made same move.");
			return gameResponse;
		}


		boolean firstPlayerWin = gameLogic.firstWins(request.getFirstPlayerMove(), request.getSecondPlayerMove());

		GameResponse gameResponse = new GameResponse();
		if (firstPlayerWin) {
			gameResponse.setFirstPlayerWins(true);
			gameResponse.setSecondPlayerWins(false);
		} else {
			gameResponse.setFirstPlayerWins(false);
			gameResponse.setSecondPlayerWins(true);
		}

		gameResponse.setWinner(firstPlayerWin ? request.getFirstPlayerName() : request.getSecondPlayerName());

		prepareWinningMessage(request, firstPlayerWin, gameResponse);

		return gameResponse;
	}

	
	
	
	private void prepareWinningMessage(GameRequest request, boolean firstPlayerWin, GameResponse gameResponse) {
		String winningMessage = " won the game";
		gameResponse.setMessage(firstPlayerWin ? request.getFirstPlayerName() + winningMessage: request.getSecondPlayerName() + winningMessage);
	}

	
	
	
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		this.applicationContext = applicationContext;
	}
}
