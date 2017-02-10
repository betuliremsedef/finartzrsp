package com.finartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.finartz.logic.GameLogic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RockscissorspaperApplicationTests {

	private String param1=null;
	private String param2=null;
	@Test
	public void contextLoads() {
		GameLogic gamelogic=new GameLogic();
		
	//Playing game with player parameters:
		
		
		
	//localhost:8080/rockscissorspaper/game/play?firstPlayerName="{param1}"&secondPlayerName="{param2}"
		
		
		
	//Getting player move :
		param1= gamelogic.getPlayerMove();
		System.out.println("Player move for player1:"+param1);
		
		param2=gamelogic.getPlayerMove();
		System.out.println("Player move for player2:"+param2);
		
		
		
		
	}

}
