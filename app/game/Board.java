package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import game.Command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

abstract public class Board {
	
	protected List<Command> history = new ArrayList<Command>();
	
	protected Random rng; 
	
	public void seedRandom(int seed) {
		rng = new Random(seed);
	}
	
	/**
	 * Runs a command on the board to modify it in some way
	 * 
	 * @param command String
	 * @throws GameError
	 */
	public void runCommand(Command command) throws GameError {
		history.add(command);
		command.execute();
	}
	
	/**
	 * Given a command from the user, it finds an actual Command token object
	 * 
	 * @param string Command
	 * @return Command object
	 */
	public abstract Command getCommand(String string) throws GameError;
	
	/**
	 * Do various things before displaying that we normally don't want
	 * to do between every turn.
	 * 
	 * For example, determine which actions are disabled and which are
	 * usable by the player. This could be time-consuming, and we don't
	 * really care to do this when playing back moves already made,
	 * because they've already been made. 
	 */
	public void preDisplay() {
		
	}
	
	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Return a set of active players. This should be their color or race or faction,
	 * and not their username, because the game should have no knowledge of the user
	 * database.
	 * 
	 * @return Set<String> list of players with actions pending.
	 */
	abstract public Set<String> getActivePlayer();
	
}
