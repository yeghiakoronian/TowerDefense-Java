package ui.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.CanvaObject;
import ui.Constants;
import core.applicationservice.mapservices.MapManager;
import core.applicationservice.mapservices.MapUtility;
import core.applicationservice.mapservices.connectivity.imp.ConnectivityService;
import core.applicationservice.mapservices.connectivity.imp.StartEndChecker;
import core.contract.MapConstants;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;
import core.domain.maps.VisualGrid;
import core.domain.waves.Position;

/**
 * @author Team5
 *
 */
public class MapEditorPanel extends JPanel implements ActionListener,
		MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;

	JButton scenery;
	JButton path;
	JButton ep;
	JButton exp;

	Color colorToDrawGrid;
	GridCellContentType cellContent;

	Grid grid;

	MapManager mapManager;
	CanvaObject canvas;
	JPanel mapContainer;

	JPanel toolBoxContainer = new JPanel();

	@SuppressWarnings("unused")
	private MapEditorPanel() {
	}

	/**
	 * <b>Initializes the main content of the map editor</b>
	 * @param width map width
	 * @param height map height
	 */
	public MapEditorPanel(int width, int height) {

		initialize(width, height);
		setLayout(new BorderLayout());

		scenery.setBackground(MapConstants.SCENERY_COLOR);
		path.setBackground(MapConstants.PATH_COLOR);
		ep.setBackground(MapConstants.ENTRANCE_COLOR);
		exp.setBackground(MapConstants.EXIT_COLOR);
		toolBoxContainer.setSize(10, 500);
		toolBoxContainer.setLayout(new FlowLayout());
		toolBoxContainer.add(scenery);
		toolBoxContainer.add(path);
		toolBoxContainer.add(exp);
		toolBoxContainer.add(ep);

		mapManager = new MapManager();

		scenery.addActionListener(this);
		path.addActionListener(this);
		ep.addActionListener(this);
		exp.addActionListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);

		int mapPixelWidth = grid.getWidth() * grid.getUnitSize();
		int mapPixelHeight = grid.getHeight() * grid.getUnitSize();
		canvas.setSize(mapPixelWidth, mapPixelHeight);
		mapContainer.setPreferredSize(new Dimension(mapPixelWidth,
				mapPixelHeight));
		mapContainer.add(canvas);

		add(new JPanel(), BorderLayout.NORTH);
		add(toolBoxContainer, BorderLayout.EAST);
		add(mapContainer, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.SOUTH);
		setVisible(true);

	}

	/**
	 *is used to initialize  user interface tools when user needs to design a new map 
	 * @param width width of an initial map
	 * @param height heihgt of an initial map
	 */
	private void initialize(int width, int height) {
		this.width = width;
		this.height = height;

		scenery = new JButton(Constants.SCENERY);
		path = new JButton(Constants.PATH);
		ep = new JButton(Constants.ENTRANCE);
		exp = new JButton(Constants.EXIT);

		colorToDrawGrid = Color.green;
		cellContent = GridCellContentType.PATH;

		grid = new VisualGrid(width, height, GridCellContentType.SCENERY);

		canvas = new CanvaObject(grid);
		mapContainer = new JPanel();

		toolBoxContainer = new JPanel();

	}

	/**
	 * this class is used to create a specific point as part of a map 
	 * @author team5
	 *
	 */
	
	@SuppressWarnings("serial")
	public class CanvasCoordinate extends Point {
		public CanvasCoordinate(int x, int y) {
			super(x, y);
		}
	}

	/**
	 * <b>Converts point to a coordinate that is actionable by the canvas.</b>
	 * @param point target location on the grid (canvas)
	 * @return canvas coordinate
	 */
	public CanvasCoordinate toCanvasCoordinates(Point point) {
		Point canvasLocation = canvas.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / grid.getUnitSize();
		int j = relativeY / grid.getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	/**
	 * <b>Sets the grid size either to design a new map or to load a map.</b>
	 * @param width map width
	 * @param height map height
	 */
	public void setGridSize(int width, int height) {
		grid.setSize(width, height);
		canvas.setGrid(grid);
	}

	/**
	 * <b>prepares for map design.</b>
	 * @param width map width
	 * @param height map height
	 */
	public void design(int width, int height) {
		try {
			grid.setSize(width, height);
			canvas.setGrid(grid);

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * <b>Prepares to draw path.</b>
	 * @param backgroundColor path color 
	 */
	public void drawPath(Color backgroundColor) {
		try {

			colorToDrawGrid = backgroundColor;
			cellContent = GridCellContentType.PATH;
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		draw(event.getX(), event.getY());

	}

	private void draw(int x, int y) {
		int i = x / grid.getUnitSize();
		int j = y / grid.getUnitSize();
		boolean drawAllowed = true;
		// if entrance or exit check for isSingle
		if (cellContent == GridCellContentType.ENTRANCE
				|| cellContent == GridCellContentType.EXIT) {
			if (!isSingle(cellContent)) {
				drawAllowed = false;
			}
		}
		if (drawAllowed) {
			if ((i < grid.getWidth()) && (j < grid.getHeight())
					&& (grid.getCell(i, j) != cellContent)) {
				grid.setCell(i, j, cellContent);
				canvas.repaint();

			}

		}
	}

	@Override
	public void mouseMoved(MouseEvent event) {

	}

	
	/** <b>Draws the selected content on the map.</b>
	 * @param event MouseEvent passed to the method.
	 * 
	 */
	public void mouseClicked(MouseEvent event) {
		draw(event.getX(), event.getY());

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	
	/** 
	 * <b>Depending on which button user clicks on, a scenery, entrance, exit, or path will be selected to draw on map.</b> 
	 * @param event the ActionEvent passed to this method.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case Constants.SCENERY:
			scenery();
			break;
		case Constants.ENTRANCE:
			entrance();
			break;
		case Constants.EXIT:
			exit();
			break;
		case Constants.PATH:
			path();
			break;
		}
	}

	/**
	 * 
	 */
	private void path() {
		try {

			colorToDrawGrid = path.getBackground();
			cellContent = GridCellContentType.PATH; // black for path
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	/**
	 * 
	 * @param width
	 * @param height
	 */
	protected void setMapSize(int width, int height) {
		try {
			// validation part
			StartEndChecker checker = new StartEndChecker();
			if (!checker.isCorrectSize(height, width))
				throw new java.lang.Exception(
						"Error size max size: ....., min size: ....");
			// end of validation

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * gets the cellContent of for an exit point
	 */
	private void exit() {
		try {
			cellContent = GridCellContentType.EXIT;
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());

		}

	}

	/**
	 * gets cell content for an entry point
	 */
	private void entrance() {
		try {

			cellContent = GridCellContentType.ENTRANCE;
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * this method checks whether a given cell type exists in a grid or not
	 * @param cellContent
	 * @return returns true if a given cell exists in a grid
	 */
	private boolean isSingle(GridCellContentType cellContent) {
		for (int x = 0; x < grid.getWidth(); x++) {
			for (int y = 0; y < grid.getHeight(); y++) {
				if (grid.getCell(x, y) == cellContent) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 */
	private void scenery() {
		try {

			colorToDrawGrid = scenery.getBackground();
			cellContent = GridCellContentType.SCENERY;

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * this method is used to save map into the file. 
	 */
	protected void saveMap() {
		try {

			if (!isValid(grid.getContent())) {
				throw new Exception("Map is not valid!!");
			} else {
				JFileChooser saveFile = new JFileChooser();

				if (saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					String fileName = saveFile.getSelectedFile()
							.getAbsolutePath();

					mapManager.saveMapIntoFle(grid, fileName);

				}
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * checks whether a given grid is a valid map or not
	 * @param matrix
	 * @return returns true if it is a valid map
	 */
	private boolean isValid(GridCellContentType[][] matrix) {
		StartEndChecker startChecker = new StartEndChecker();
		ConnectivityService conn = new ConnectivityService();
		MapUtility utility = new MapUtility();
		Position start = utility.getEnter(matrix);
		Position end = utility.getExit(matrix);
		if (!startChecker.hasEnd(matrix)) {
			return false;
		}
		if(!startChecker.hasStart(matrix)){
			return false;
		}
		if (!conn.isTherePath(start, end, matrix)) {
			return false;
		}
		if(startChecker.isNeighbor(start, end)){
			return false;
		}
		return true;
	}

	/**
	 * leads map from a given file
	 */
	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				grid = mapManager.loadMapFromFile(openFile.getSelectedFile()
						.getAbsolutePath());
				canvas.setGrid(grid);
				width = grid.getWidth();
				height = grid.getHeight();
				mapContainer.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				canvas.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	/**
	 * 
	 */
	protected void designMap() {
		try {
			canvas.setGrid(grid);
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

}
