package core.domain.maps;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import core.contract.MapConstants;
import core.domain.waves.Position;

/**
 * @author Team5
 *
 */

@SuppressWarnings("serial")
public class Grid implements Serializable {

	private int width;
	private int height;
	private int unitSize = MapConstants.UNIT_SIZE;
	private GridCellContentType[][] content;

	private String creationTime;
	private ArrayList<String> modificationTime = new ArrayList<String>();
	private ArrayList<PlayLog> playLog = new ArrayList<PlayLog>();

	/**
	 * This is a dummy constructor
	 */
	public Grid() {
		this.width = 1;
		this.height = 1;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	/**
	 * <b>Creates a Grid using width and height and initializes the content as
	 * Scenery</b>
	 * 
	 * @param width
	 *            width of grid
	 * @param height
	 *            height of grid
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	/**
	 * <b>Creates a Grid with width, height, and content type</b>
	 * 
	 * @param width
	 *            width of grid
	 * @param height
	 *            height of grid
	 * @param cellType
	 *            type of grid
	 */
	public Grid(int width, int height, GridCellContentType cellType) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(cellType);
	}

	/**
	 * <b>Constructs a Grid by another grid.</b>
	 * 
	 * @param grid
	 *            grid object
	 */
	public Grid(Grid grid) {
		this.width = grid.getWidth();
		this.height = grid.getHeight();
		this.content = grid.getContent();
		this.creationTime = grid.getCreationTime();
		this.modificationTime.addAll(grid.getModificationTime());
		this.playLog.addAll(grid.getPlayLog());
	}

	/**
	 * <b>initializes grid content to cellType</b>
	 * 
	 * @param cellType
	 *            type of cell
	 */
	private void initializeCellContents(GridCellContentType cellType) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				setCell(x, y, cellType);
			}
		}
	}

	/**
	 * <b>This method can not be used directly. It allows drawing the grid through VisualGrid</b>
	 * 
	 * @param g
	 *            draw graphics after iteration
	 */
	public void draw(Graphics g) {}

	/**
	 * @return height of map
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param value grid height
	 */
	public void setHeight(int value) {
		height = value;
	}

	/**
	 * @return width of map
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param value grid width
	 */
	public void setWidth(int value) {
		width = value;
	}

	/**
	 * @return type return content of the grid cell
	 */
	public GridCellContentType[][] getContent() {
		return content;
	}

	/**
	 * @param value grid content
	 */
	public void setContent(GridCellContentType[][] value) {
		content = value;
	}

	/**
	 * @return size of the unit
	 */
	public int getUnitSize() {
		return unitSize;
	}

	/**
	 * <b>Sets the size of the grid and resets its content to scenery.</b>
	 * 
	 * @param width
	 *            width of the content to set
	 * @param height
	 *            height of the content to set
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);

	};

	/**
	 * <b>Sets the content of a cell to type</b>
	 * 
	 * @param x
	 *            location of cell
	 * @param y
	 *            location of cell
	 * @param type
	 *            type of cell
	 */
	public void setCell(int x, int y, GridCellContentType type) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			content[x][y] = type;
		}
	}

	/**
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 * @return content type
	 */
	public GridCellContentType getCell(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return content[x][y];
		}
		return null;
	}

	/**
	 * @return position of the grid entry point  
	 */
	public Position getEntranceLocation() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (content[x][y] == GridCellContentType.ENTRANCE) {
					return new Position(x, y);
				}
			}
		}
		return null;
	}

	/**
	 * @return position of the grid exit point  
	 */
	public Position getExitLocation() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (content[x][y] == GridCellContentType.EXIT) {
					return new Position(x, y);
				}
			}
		}
		return null;
	}

	/**
	 * @return grid creation time 
	 */
	public String getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime grid creation time 
	 */
	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return grid modification time
	 */
	public ArrayList<String> getModificationTime() {
		return modificationTime;
	}

	/**
	 * @param modificationTime grid modification time
	 */
	public void setModificationTime(ArrayList<String> modificationTime) {
		this.modificationTime = modificationTime;
	}

	/**
	 * @param modificationTime modification time
	 */
	public void addModificationTime(String modificationTime) {
		this.modificationTime.add(modificationTime);
	}

	/**
	 * @return play log of the grid
	 */
	public ArrayList<PlayLog> getPlayLog() {
		return playLog;
	}

	/**
	 * This method is only for testing pueposes
	 * @param toCompare another grid to compare its play log
	 * @return true if toCompare has the same play log 
	 */
	public boolean comparePlayLog(Grid toCompare) {
		if (playLog.size() != toCompare.getPlayLog().size()) {
			return false;
		}
		for (int i = 0; i < playLog.size(); i++) {
			if (playLog.get(i).toString()
					.compareToIgnoreCase(toCompare.getPlayLog().get(i).toString()) != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param playLog play log
	 */
	public void setPlayLog(ArrayList<PlayLog> playLog) {
		this.playLog = playLog;
	}

	/**
	 * @param time time of play
	 * @param score score of play
	 */
	public void addPlayLog(String time, long score) {
		if (playLog == null) {
			playLog = new ArrayList<PlayLog>();
		}
		this.playLog.add(new PlayLog(time, score));

	}

	/**
	 * Returns the highest scores to display in log viewer.
	 * 
	 * @param size number of highest scores to return
	 * @return the highest scores in string format
	 */
	public String getHighestScores(int size) {
		if (size > playLog.size()) {
			size = playLog.size();
		}
		ArrayList<PlayLog> scores = new ArrayList<PlayLog>();
		ArrayList<PlayLog> highScores = new ArrayList<PlayLog>();
		scores.addAll(playLog);
		PlayLog currentHighest = new PlayLog();
		for (int c = 0; c < size; c++) {
			int indx = 0;
			for (int i = 0; i < scores.size(); i++) {
				if (scores.get(i).score > currentHighest.score) {
					currentHighest = scores.get(i);
					indx = i;
				}
			}
			highScores.add(currentHighest);
			currentHighest = new PlayLog();
			scores.set(indx, new PlayLog());
		}
		String str = "";
		for (PlayLog entry : highScores) {
			str += entry + "\n";
		}
		return str;
	}

	/**
	 * This is an internal class for PlayLog object
	 * @author Team 5
	 *
	 */
	class PlayLog implements Serializable {
		String time;
		long score;

		PlayLog() {
			time = "";
			score = -1;
		}

		/**
		 * constructor with two params
		 * 
		 * @param time time of play
		 * @param score score of play
		 */
		PlayLog(String time, long score) {
			this.time = time;
			this.score = score;
		}

		/**
		 * returns the PlayLog as "Time: time - Score: score"
		 */
		public String toString() {
			return "Time: " + time + " - Score: " + score;
		}
	}

}
