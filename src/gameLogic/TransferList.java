package gameLogic;
import java.util.*;

import libraryClasses.*;
/** This class represents a list of transfers in progress
 * 
 * @author Menno
 *
 */
public class TransferList {
	
	private ArrayList<TransferInProgress> transfers;
	/**
	 * Initialize the ArrayList that will contain the transfers
	 */
	public TransferList() {
		transfers = new ArrayList<TransferInProgress>();
	}
	/** Add a transfer
	 * 
	 * @param transferIn the transfer to add
	 */
	public void addTransfer(TransferInProgress transferIn) {
		transfers.add(transferIn);
	}
	/** Get the transfer at index index
	 * 
	 * @param index the index at which the wanted transfer is
	 * @return the transfer at index index, or null if the index is too large
	 */
	public TransferInProgress getTransfer(int index) {
		if (index<transfers.size() && index>=0) {
			
			return transfers.get(index);
		} return null;
	}
	/** Get the transfer for a player called name
	 * 
	 * @param name the name of the player you want the transfer for
	 * @return the transfer of the player called name, or null if there is no transfer for this player
	 */
	public TransferInProgress getTransfer(String name) {
		for (int i=0;i<transfers.size();i++) {
			TransferInProgress t = transfers.get(i);
			if (name.equals(t.getPlayer().getName())) {
				return t;
			}
		} return null;
	}
	/** Get the transfer for player
	 * 
	 * @param player the player you want to get the transfer for
	 * @return the transfer for player, or null if there is no transfer for this player
	 */
	public TransferInProgress getTransfer(Player player) {
		for (int i=0;i<transfers.size();i++) {
			TransferInProgress t = transfers.get(i);
			if (player.equals(t.getPlayer())) {
				return t;
			}
		} return null;
	}
	/** Get all the transfers
	 * 
	 * @return an ArrayList with all the transfers
	 */
	public ArrayList<TransferInProgress> getTransfers() {
		return transfers;
	}
	/** Check if there is a transfer for a given player
	 * 
	 * @param player the player you want to check for
	 * @return true if there is a transfer for player, false if there's not
	 */
	public boolean transferExists(Player player) {
		for (int i=0;i<transfers.size();i++) {
			if(player.equals(transfers.get(i).getPlayer())) {
				return true;
			}
		} return false;
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
		TransferList other = (TransferList) obj;
		if (transfers == null) {
			if (other.transfers != null)
				return false;
		} else if (!transfers.equals(other.transfers))
			return false;
		return true;
	}
	/** 
	 * 
	 * @param transfers the transfers to set
	 */
	public void setTransfers(ArrayList<TransferInProgress> transfers) {
		this.transfers=transfers;
	}
	
	
	

}
