package test.core.applicationservice.mapservices;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import core.domain.maps.GridCellContentType;

public class MatrixUtility {
	public int[][] matrixReadre(String file, int matrixHeight, int matrixWidth) {
		int[][] matrix = new int[matrixHeight][matrixWidth];
		try (InputStream input = getClass().getResourceAsStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));) {
			String line;
			int index = 0;
			String[] strs;
			while ((line = br.readLine()) != null) {
				strs = line.split(" ");
				for (int i = 0; i < strs.length; i++) {
					matrix[index][i] = Integer.parseInt(strs[i]);
				}
				index++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return matrix;
	}

	public GridCellContentType[][] matrixCellType(String file,
			int matrixHeight, int matrixWidth) {
		GridCellContentType[][] matrix = new GridCellContentType[matrixHeight][matrixWidth];
		try (InputStream input = getClass().getResourceAsStream(file);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						input));) {
			String line;
			int index = 0;
			String[] strs;
			while ((line = br.readLine()) != null) {
				strs = line.split(" ");
				for (int i = 0; i < strs.length; i++) {
					int cell = Integer.parseInt(strs[i]);
					switch (cell) {
					case 0:
						matrix[index][i] = GridCellContentType.SCENERY;
						break;
					case 1:
						matrix[index][i] = GridCellContentType.PATH;
						break;

					case 2:
						matrix[index][i] = GridCellContentType.ENTRANCE;
						break;

					case 3:
						matrix[index][i] = GridCellContentType.EXIT;
						break;
					}
				}
				index++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return matrix;
	}
}