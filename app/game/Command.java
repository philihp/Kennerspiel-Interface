package game;

public interface Command {
	void execute() throws GameError;
}