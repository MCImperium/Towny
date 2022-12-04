package com.palmergames.bukkit.towny.invites;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import org.bukkit.command.Player;

/**
 * An object that represents an invitation.
 * 
 * @author Articdive
 */
public interface Invite {
	/**
	 * Gets the sender of the invitation
	 * 
	 * @return - The {@link Player} of the invitation.
	 */
	Player getDirectSender();

	/**
	 * Gets the receiver of the invitation.
	 * 
	 * @return - The {@link InviteReceiver} object receiving the invite.
	 */
	InviteReceiver getReceiver();

	/**
	 * Gets the sender of the invitation.
	 * 
	 * @return - The {@link InviteSender} object that sent the invite.
	 */
	InviteSender getSender();

	/**
	 * Runs the accept code for the given invitation.
	 * 
	 * @throws TownyException - Sends errors back up to be processed by the caller.
	 */
	void accept() throws TownyException;

	/**
	 * Runs the reject code for the given invitation.
	 * 
	 * @param fromSender - Tells if invite was revoked (true) or declined (false).
	 */
	void decline(boolean fromSender);
}
