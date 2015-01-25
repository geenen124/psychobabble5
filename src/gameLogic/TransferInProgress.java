package gameLogic;

import libraryClasses.*;
/** This class represents a transfer in progress, that is, a transfer that got rejected first and on which a new bid can be done
 * 
 * @author Menno
 *
 */
public class TransferInProgress {
	
	private Player player;
	private double priceReturned;
	private double bid;
	
	/** The constructor
	 * 
	 * @param playerIn the Player that the bid was made on
	 * @param priceReturnedIn the price that was returned by the team that currently has the player
	 */
	public TransferInProgress (Player playerIn, double priceReturnedIn, double bidIn) {
		this.setPlayer(playerIn);
		this.setPriceReturned(priceReturnedIn);
		this.setBid(bidIn);
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the priceReturned
	 */
	public double getPriceReturned() {
		return priceReturned;
	}

	/**
	 * @param priceReturned the priceReturned to set
	 */
	public void setPriceReturned(double priceReturned) {
		this.priceReturned = priceReturned;
	}

	/**
	 * @return the bid
	 */
	public double getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(double bid) {
		this.bid = bid;
	}

	

	/** Checks if this and obj are equal to each other or have equal attribute values
	 * @param obj the obj to compare this with
	 * @return true if obj and this are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransferInProgress other = (TransferInProgress) obj;
		if (Double.doubleToLongBits(bid) != Double.doubleToLongBits(other.bid))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (Double.doubleToLongBits(priceReturned) != Double
				.doubleToLongBits(other.priceReturned))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}
