package gameLogic;
import libraryClasses.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
/** This class contains all the logic for transfers
 * 
 * @author Menno
 *
 */
public abstract class TransferLogic {

	/** This method returns the answer for a transfer request for a player that you have already bid for before
	 * 
	 * @param player The player you want to buy
	 * @param bid The bid you place for this player
	 * @param library The library of all teams and players
	 * @param existingTransfers the list with transfers that already exist
	 * @param returnedprice the price that was returned last time you tried to buy this player
	 * @return true if the team has accepted your offer, false if the team has denied your offer
	 */
	public static boolean getAnswerForExistingTransfer(Player player, double bid, Library library, TransferList existingTransfers, double returnedprice) {
		if (bid<returnedprice) {
			return false;
		} else {
			return TransferLogic.getAnswer(player, bid, library, existingTransfers);
		}
	}
	/** This method returns the answer for a transfer request
	 * 
	 * @param player The player you want to buy
	 * @param bid The bid you place for this player
	 * @param library The library of all teams and players
	 * @param existingTransfers The list with transfers that already exist
	 * @return true if the team has accepted your offer, false if the team has denied your offer
	 */
	public static boolean getAnswer(Player player, double bid, Library library, TransferList existingTransfers) {
		Team team = library.getTeamForName(player.getTeam());
		ArrayList<Player> playerlist = new ArrayList<Player>();
		for (int i=0;i<team.getTeam().size();i++) {
			if (player.getPlayerType().equals(team.getTeam().get(i).getPlayerType())) {
				playerlist.add(team.getTeam().get(i));
			}
		}
		int[] ratings = new int[playerlist.size()];
		if(player.getPlayerType().equals("Attacker")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]= (int) (0.6*p.getFinishingValue() + 0.2*p.getDribblingValue() + 0.2*p.getStaminaValue());
			}
		} if (player.getPlayerType().equals("Midfielder")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]=(int) (0.15*p.getFinishingValue() + 0.4*p.getDribblingValue() + 0.3*p.getStaminaValue() + 0.15*p.getDefenseValue());
				
			}
		} if (player.getPlayerType().equals("Defender")) {
			for (int i=0;i<playerlist.size();i++) {
				FieldPlayer p = (FieldPlayer)playerlist.get(i);
				ratings[i]=(int) (0.05*p.getDribblingValue() + 0.25*p.getStaminaValue() + 0.7*p.getDefenseValue());
				
			}
		} if (player.getPlayerType().equals("Goalkeeper")) {
			for (int i=0;i<playerlist.size();i++) {
				Goalkeeper p = (Goalkeeper)playerlist.get(i);
				ratings[i]=p.getGoalkeeperValue();
			}
		}
		
		if (playerlist.size()==1) {
			
			return false;
		}
		
		int playerid=playerlist.indexOf(player);
		int typecounter=1;
		for (int i=0;i<ratings.length;i++) {
			if (i==playerid) {
				
			} else {
				if (ratings[playerid]<ratings[i]) {
					typecounter++;
				}
			}
		}
		int random = GameLogic.randomGenerator(1, 100);
		double percentage = bid/player.getPrice().doubleValue()*100-100;
		if (typecounter==1||typecounter==2) {
			if(percentage<0) {
				return false;
				
			} if (percentage>=0 && percentage<5) {
				if (random<=10) {
					return true;
				} else return false;
				
				
			} if (percentage>=5 && percentage<10) {
				if (random<=20) {
					return true;
				} else return false;
			} if (percentage>=10 && percentage<15) {
				if (random<=30) {
					return true;
				} else return false;
			} if (percentage>=15 && percentage<20) {
				if (random<=40) {
					return true;
				} else return false;
			} if (percentage>=20 && percentage<25) {
				if (random<=50) {
					return true;
				} else return false;
			} if (percentage>=25 && percentage<30) {
				if (random<=75) {
					return true;
				} else return false;
			} else return true;
		} if (typecounter==3 || typecounter==4 || typecounter==5) {
			if (percentage<-10) {
				return false;
			} if (percentage>=-10 && percentage<0) {
				if (random<=5) {
					return true;
				} else return false;
			} if (percentage>=0 && percentage <5) {
				if (random<=15) {
					return true;
				} else return false;
			} if (percentage>=5 && percentage<10) {
				if (random<=30) {
					return true;
				} else return false;
			} if (percentage>=10 && percentage<15) {
				if (random<=45) {
					return true;
				} else return false;
			} if (percentage>=15 && percentage<20) {
				if (random<=60) {
					return true;
				} else return false;
			} if (percentage>=20 && percentage<25) {
				if (random<=80) {
					return true;
				} else return false;
			} else return true;
		} else {
			if (percentage<-10) {
				return false;
			} if (percentage>=-10 && percentage<0) {
				if (random<=20) {
					return true;
				} else return false;
			} if (percentage>=0 && percentage<5) {
				if (random<=45) {
					return true;
				} else return false;
			} if (percentage>=5 && percentage<10) {
				if (random<=70) {
					return true;
				} else return false;
			} if (percentage>=10 && percentage<15) {
				if (random<=85) {
					return true;
				} else return false;
			} else return true;
		}
		
		
	}
	/** Calling this method represents requesting a transfer
	 * 	
	 * @param player The player you want to buy
	 * @param playersTeam The team you are currently managing
	 * @param bid The bid you want to place for this player
	 * @param library The library of all teams and players
	 * @param existingTransfers The list with transfers that already exist
	 * @return a String with a meaningful message (to show to the user), about the acceptance or denial of his/her bid for that player
	 */
	public static String requestTransfer(Player player, Team playersTeam, double bid, Library library, TransferList existingTransfers) {
		if (playersTeam.isMax()) {
			return "You already have 30 players in your team, this is the maximum. Sell some players before trying to buy any new!";
		} else {
		if (existingTransfers.getTransfer(player)==null) {
			boolean answer = TransferLogic.getAnswer(player, bid, library, existingTransfers);
			if (answer) {
				Team opponentsTeam = library.getTeamForName(player.getTeam());
				opponentsTeam.deleteIfInCurrentTeam(player);
//				if (opponentsTeam.getPositions().contains(player)) {
//					
//					boolean itsdone=false;
//					for (int i=0;i<opponentsTeam.getTeam().size();i++) {
//						if (!itsdone) {
//							Player p = opponentsTeam.getTeam().get(i);
//							if (p.getPlayerType().equals(player.getPlayerType()) && !(opponentsTeam.getPositions().contains(p))) {
//								opponentsTeam.replacePlayerInCurrentTeam(player, p);
//								itsdone=true;
//							}
//						}
//					}
//					if (!itsdone) {
//						for (int i=0;i<opponentsTeam.getTeam().size();i++) {
//							if (!itsdone) {
//								Player p = opponentsTeam.getTeam().get(i);
//								if (((p instanceof FieldPlayer && player instanceof FieldPlayer) || (p instanceof Goalkeeper && player instanceof Goalkeeper)) && !(opponentsTeam.getPositions().contains(p))) {
//									opponentsTeam.replacePlayerInCurrentTeam(player, p);
//									itsdone=true;
//								}
//							}
//						}
//					}
//					
//					
//				}
				
				playersTeam.add(player);
				opponentsTeam.getTeam().remove(player);
				player.setTeam(playersTeam.getTeamName());
				playersTeam.setBudget(playersTeam.getBudget()-bid);
				opponentsTeam.setBudget(opponentsTeam.getBudget()+bid);
				BigDecimal bbid = new BigDecimal(bid);
				
				//price string
				
				DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
				bbid = bbid.setScale(2, BigDecimal.ROUND_DOWN);
				DecimalFormat df = new DecimalFormat();
				df.setGroupingUsed(false);		
				String priceString = formatter.format(bbid.longValue());
				
				
				return "Congratulations! Your bid of " + priceString + " got accepted and " + player.getName() + " is now part of your team";
			} else {
				double percentage = bid/player.getPrice().doubleValue()*100-100;
				double returnedprice=0;
				double price = player.getPrice().doubleValue();
				if (percentage<-10) {
					returnedprice=0.9*price;
				} if (percentage>=-10 && percentage<0) {
					returnedprice=price;
				} if (percentage>=0 && percentage<5) {
					returnedprice=1.05*price;
				} if (percentage>=5 && percentage<10) {
					returnedprice=1.1*price;
				} if (percentage>=10 && percentage<15) {
					returnedprice=1.15*price;
				} if (percentage>=15 && percentage<20) {
					returnedprice=1.2*price;
				} if (percentage>=20 && percentage<25) {
					returnedprice=1.25*price;
				} if (percentage>=25 && percentage<30) {
					returnedprice=1.3*price;
				} 
				
				TransferInProgress tp = new TransferInProgress(player, returnedprice, bid);
				existingTransfers.addTransfer(tp);
				BigDecimal bbid = new BigDecimal(bid);
				BigDecimal breturnedprice = new BigDecimal(returnedprice);
				//price string
				
				DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
				bbid = bbid.setScale(2, BigDecimal.ROUND_DOWN);
				breturnedprice = breturnedprice.setScale(2, BigDecimal.ROUND_DOWN);
				DecimalFormat df = new DecimalFormat();
				df.setGroupingUsed(false);		
				String priceString = formatter.format(bbid.longValue());
				String priceString2 = formatter.format(breturnedprice.longValue());
				return player.getTeam() + " did not accept your offer of " + priceString + " for " + player.getName() + ". They have indicated they want at least " + priceString2 + " for this player";
				
			}
		} else {
			TransferInProgress tp = existingTransfers.getTransfer(player);
			boolean answer = TransferLogic.getAnswerForExistingTransfer(player, bid, library, existingTransfers, tp.getPriceReturned());
			if (answer) {
				Team opponentsTeam = library.getTeamForName(player.getTeam());
				opponentsTeam.deleteIfInCurrentTeam(player);
//				if (opponentsTeam.getPositions().contains(player)) {
//					boolean itsdone=false;
//					for (int i=0;i<opponentsTeam.getTeam().size();i++) {
//						if (!itsdone) {
//							Player p = opponentsTeam.getTeam().get(i);
//							if (p.getPlayerType().equals(player.getPlayerType()) && !(opponentsTeam.getPositions().contains(p))) {
//								opponentsTeam.replacePlayerInCurrentTeam(player, p);
//								itsdone=true;
//							}
//						}
//					}
//					if (!itsdone) {
//						for (int i=0;i<opponentsTeam.getTeam().size();i++) {
//							if (!itsdone) {
//								Player p = opponentsTeam.getTeam().get(i);
//								if (((p instanceof FieldPlayer && player instanceof FieldPlayer) || (p instanceof Goalkeeper && player instanceof Goalkeeper)) && !(opponentsTeam.getPositions().contains(p))) {
//									opponentsTeam.replacePlayerInCurrentTeam(player, p);
//									itsdone=true;
//								}
//							}
//						}
//					}
//					
//					
//				}
				
				playersTeam.add(player);
				opponentsTeam.getTeam().remove(player);
				player.setTeam(playersTeam.getTeamName());
				existingTransfers.getTransfers().remove(tp);
				playersTeam.setBudget(playersTeam.getBudget()-bid);
				opponentsTeam.setBudget(opponentsTeam.getBudget()+bid);
				BigDecimal bbid = new BigDecimal(bid);
				//price string
				
				DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
				bbid = bbid.setScale(2, BigDecimal.ROUND_DOWN);
				DecimalFormat df = new DecimalFormat();
				df.setGroupingUsed(false);		
				String priceString = formatter.format(bbid.longValue());
				return "Congratulations! Your bid of " + priceString + " got accepted and " + player.getName() + " is now part of your team";
				
			} else {
				double pricereturned = existingTransfers.getTransfer(player).getPriceReturned();
				
				if (bid>pricereturned) {
					double percentage = bid/player.getPrice().doubleValue()*100-100;
					double returnedprice=0;
					double price = player.getPrice().doubleValue();
					if (percentage<-10) {
						returnedprice=0.9*price;
					} if (percentage>=-10 && percentage<0) {
						returnedprice=price;
					} if (percentage>=0 && percentage<5) {
						returnedprice=1.05*price;
					} if (percentage>=5 && percentage<10) {
						returnedprice=1.1*price;
					} if (percentage>=10 && percentage<15) {
						returnedprice=1.15*price;
					} if (percentage>=15 && percentage<20) {
						returnedprice=1.2*price;
					} if (percentage>=20 && percentage<25) {
						returnedprice=1.25*price;
					} if (percentage>=25 && percentage<30) {
						returnedprice=1.3*price;
					}
					existingTransfers.getTransfer(player).setPriceReturned(returnedprice);
				}
				
				existingTransfers.getTransfer(player).setBid(bid);
				BigDecimal bbid = new BigDecimal (bid);
				BigDecimal bpriceReturned = new BigDecimal(existingTransfers.getTransfer(player).getPriceReturned());
				//price string
				
				DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
				bbid = bbid.setScale(2, BigDecimal.ROUND_DOWN);
				bpriceReturned = bpriceReturned.setScale(2, BigDecimal.ROUND_DOWN);
				DecimalFormat df = new DecimalFormat();
				df.setGroupingUsed(false);		
				String priceString = formatter.format(bbid.longValue());
				String priceString2 = formatter.format(bpriceReturned.longValue());
				return player.getTeam() + " did not accept your offer of " + priceString + " for " + player.getName() + ". They have indicated they want at least " + priceString2 + " for this player";
			}
			
		}
		
	}}
	/** With this method you can request selling a player from your team
	 * 	
	 * @param player the player you want to sell
	 * @param playersTeam the team you are currently managing
	 * @param askingPrice the price you ask for the player
	 * @param library the library containing all the teams and players
	 * @return a String with information on succession/failure of selling the player
	 */
	public static String requestSell(Player player, Team playersTeam, double askingPrice, Library library) {
		if (!player.isCanBeSold()) {
			return "You can't try to sell this player until after " + player.getDaysNotForSale() + " more rounds";
		}
		
		ArrayList<Team> teamswithbudget = new ArrayList<Team>();
		for (int i=0;i<library.getLibrary().size();i++) {
			Team t = library.getLibrary().get(i);
			if (t!=playersTeam) {
				if (t.getBudget()>=askingPrice) {
					teamswithbudget.add(t);
				}
			}
			
		}
		if (teamswithbudget.size()==0) {
			player.triedToSell();
			return "Your player was not bought by any team, due to the fact that none of the teams has got enough money to fulfill your asking price";
		}
		
		double percentage = askingPrice/player.getPrice().doubleValue()*100-100;
		boolean sell = false;
		int random = GameLogic.randomGenerator(1, 100);
		if (percentage<-10) {
			sell=true;
		} if (percentage >=-10 && percentage<0) {
			if (random<=95) {
				sell=true;
			}
		} if (percentage>=0 && percentage <5) {
			if (random<=75) {
				sell=true;
			}
		} if (percentage>=5 && percentage <10) {
			if (random<=50) {
				sell=true;
			}
		} if (percentage>=10 && percentage <15) {
			if (random<=30) {
				sell=true;
			}
		} if (percentage>=15 && percentage <20) {
			if (random<=20) {
				sell=true;
			}
		} if (percentage>=20 && percentage<25) {
			if (random<=10) {
				sell=true;
			}
		} if (percentage>=25 && percentage<30) {
			if (random==1) {
				sell=true;
			}
		}
		
		if (sell) {
			if (playersTeam.isMax()) {
				playersTeam.setMax(false);
			}
			Team buyingTeam = teamswithbudget.get(GameLogic.randomGenerator(0, teamswithbudget.size()-1));
			playersTeam.setBudget(playersTeam.getBudget()+askingPrice);
			//System.out.println(playersTeam.getBudget());
			buyingTeam.setBudget(buyingTeam.getBudget()-askingPrice);
			buyingTeam.add(player);
			player.setTeam(buyingTeam.getTeamName());
			playersTeam.getTeam().remove(player);
			player.setNumber(buyingTeam.getTeam().size());
			if (playersTeam.getPositions().contains(player)) {
				playersTeam.getPositions().remove(player);
			}
			BigDecimal baskingPrice = new BigDecimal(askingPrice);
			//price string
			
			DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
			baskingPrice = baskingPrice.setScale(2, BigDecimal.ROUND_DOWN);
			DecimalFormat df = new DecimalFormat();
			df.setGroupingUsed(false);		
			String priceString = formatter.format(baskingPrice.longValue());
			
			return "Congratulations! " + player.getName() + " got bought by " + buyingTeam.getTeamName() + " for the price of " + priceString;
		} else {
			player.triedToSell();
			return "Unfortunately your player didn't get bought. Lowering the asking price might increase the chances for a team buying your player.";
		}
		
		
		
		
	}
	/** This method is called for an automatic transfer - it will initiate an attempt to buy a random player for three different teams
	 * 
	 * 
	 * @param playersTeam the team the user is currently managing
	 * @param library the library of the current competition
	 */
	public static void AutoTransfer(Team playersTeam, Library library) {
		
		int playersteam = library.getLibrary().indexOf(playersTeam);
		
		int team1, team2, team3;
		do {
			team1 = GameLogic.randomGenerator(0, 19);
			team2=team1;
			while (team1==team2) {
				team2=GameLogic.randomGenerator(0, 19);
			}
			team3=team1;
			do {
				team3=GameLogic.randomGenerator(0,19);
				
			} while (team3==team1 && team3==team2); 
		} while (!(playersteam!=team1 && playersteam!=team2 && playersteam!=team3));
		
		TransferLogic.AutoTransferForTeam(library.getLibrary().get(team1), playersTeam, library);
		TransferLogic.AutoTransferForTeam(library.getLibrary().get(team2), playersTeam, library);
		TransferLogic.AutoTransferForTeam(library.getLibrary().get(team3), playersTeam, library);
		
		
		
		
	}
	/** This class makes an automatic attempt to buy a random player for the team t
	 * 
	 * @param t the team that is attempting to buy a player
	 * @param playersTeam the team the user is currently managing
	 * @param library the library of the current competition
	 */
	public static void AutoTransferForTeam(Team t, Team playersTeam, Library library) {
		
		ArrayList<Player> allplayers = new ArrayList<Player>();
		for (int i=0;i<library.getLibrary().size();i++) {
			Team team = library.getLibrary().get(i);
			for (int j=0;j<team.getTeam().size();j++) {
				allplayers.add(team.getTeam().get(j));
			}
		}
		
		String type="";
		TeamRating tr = TeamRating.calculateTeamRating(t);
		if (tr.getFinishing()<=tr.getDribbling() && tr.getFinishing()<=tr.getDefending() && tr.getFinishing()<=tr.getStamina() && tr.getFinishing() <=tr.getGoalkeeping()) {
			type="Attacker";
		}
		else if (tr.getDefending()<=tr.getFinishing() && tr.getDefending()<=tr.getStamina() && tr.getDefending()<=tr.getDribbling() && tr.getDefending()<=tr.getGoalkeeping()) {
			type="Defender";
		}
		else if (tr.getGoalkeeping()<=tr.getFinishing() && tr.getGoalkeeping()<=tr.getDribbling() && tr.getGoalkeeping()<=tr.getStamina() && tr.getGoalkeeping()<=tr.getDefending()) {
			type="Goalkeeper";
		} else {
			type = "Midfielder";
		}
		
		ArrayList<Player> options = new ArrayList<Player>();
		for (Player p:allplayers) {
			if ((!(playersTeam.getTeam().contains(p) || t.getTeam().contains(p)) && p.getPlayerType().equals(type) && p.getPrice().doubleValue()*1.1<=t.getBudget())) {
				options.add(p);
			}
		}
		
		Player[] players = new Player[options.size()];
		for (int i=0;i<players.length;i++) {
			players[i]=options.get(i);
		}
		
		Player temp;
		if(players.length==0) {
			
		}
		for (int j=0;j<players.length;j++) {
			for (int i=1;i<players.length;i++) {
				if (players[i-1].getPrice().doubleValue()>players[i].getPrice().doubleValue())  {
					temp=players[i];
					players[i]=players[i-1];
					players[i-1]=temp;
				}
			}
		}
		
		Player[] players2 = new Player[players.length];
		for (int i=0;i<players2.length;i++) {
			players2[i]=players[players.length-1-i];
		}
		
		if (players2.length!=0) {
		
			Player theplayer = null;
			int random = GameLogic.randomGenerator(1, 1000);
			int random2=0;
			if (random<=500) {
				random2=GameLogic.randomGenerator(0,(int) (players2.length*0.2));
				if (random2<0) {
					random2=0;
				}
				if (random2>=players2.length){
					random2=players2.length-1;
				}
				theplayer = players2[random2];
			} if (random >500 && random <=750) {
				random2=GameLogic.randomGenerator((int) (players2.length*0.2), (int) (players2.length*0.4));
				if (random2<0) {
					random2=0;
				}
				if (random2>=players2.length){
					random2=players2.length-1;
				}
				theplayer=players2[random2];
			} if (random>750 && random<=875) {
				random2=GameLogic.randomGenerator((int) (players2.length*0.4), (int) (players2.length*0.7));
				if (random2<0) {
					random2=0;
				}
				if (random2>=players2.length){
					random2=players2.length-1;
				}
				
				theplayer=players2[random2];
			} if (random>875) {
				random2=GameLogic.randomGenerator((int) (players2.length*0.7), (int) (players2.length-1));
				if (random2<0) {
					random2=0;
				}
				if (random2>=players2.length){
					random2=players2.length-1;
				}
				theplayer=players2[random2];
			}
			
			Team t2 = library.getTeamForName(theplayer.getTeam());
			TransferLogic.requestTransfer(theplayer, t, 1.1*theplayer.getPrice().doubleValue(), library, new TransferList());
			
		}
	}
	
}
