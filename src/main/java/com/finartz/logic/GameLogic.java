package com.finartz.logic;

import org.springframework.stereotype.Service;

import com.finartz.WEB.model.GameRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.finartz.WEB.model.MapPopulator.build;

/**
 * GameLogic
 */
@Service
public class GameLogic {
	
	
	public String getPlayerMove(){
		
		GameRequest request=new GameRequest();
		String playerMove="";
		String[] moves={"paper","scissors","rock"};
		
		Random rand=new Random();
		int playerIndex=rand.nextInt(3);
		
		playerMove=moves[playerIndex];
		
		return playerMove;
	}
	
	

    private Map<String, Boolean> firstWins;

    public synchronized boolean isSame(String firstPlayerMove, String secondPlayerMove){
        if (firstWins == null) {
            firstWins = new HashMap<>();
            firstWins.put("paper-scissors", false);
            firstWins.put("paper-rock", true);
            firstWins.put("rock-scissors", true);
            firstWins.put("rock-paper", false);
            firstWins.put("scissors-paper", true);
            firstWins.put("scissors-rock", false);
        }



        if  (  firstPlayerMove.equals(secondPlayerMove)   ) {
            return true;
        }




        return false;
    }

    public synchronized boolean firstWins(String firstPlayerMove, String secondPlayerMove){



        if (firstWins == null) {
            firstWins = build();
        }



        return  firstWins.get(firstPlayerMove + "-" + secondPlayerMove) ;
            }



}
