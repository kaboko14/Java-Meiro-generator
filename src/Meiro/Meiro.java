package Meiro;

public class Meiro extends Thread {
	private int hight;
	private int width;
	private int[] start;
	private int[][] map;

	public Meiro(int hight, int width, int[] start) {
		this.hight = hight;
		this.width = width;
		this.start = start;
		this.initMap();
	}



	public int getHight() {
		return hight;
	}


	public int getWidth() {
		return width;
	}

	public int[] getStart() {
		int[] copyStart = {this.start[0], this.start[1]};
				return copyStart;
	}

	// getterではmapのコピーを渡す
	public int[][] getMap() {
		int[][] copyMap = new int[this.getHight()][this.getWidth()];
		for(int i = 0; i < copyMap.length; i++) {
			for(int j = 0; j < copyMap[i].length; j++) {
				copyMap[i][j] = this.map[i][j];
			}
		}
		return copyMap;
	}


	public void setMap(int[][] map) {
		this.map = map;
	}


	// map初期化
	public void initMap() {
		int[][] map = new int[this.getHight()][this.getWidth()];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(this.getStart()[0] == i && this.getStart()[1] == j) {
					map[i][j] = 0;
				}else {
					map[i][j] = 1;
				}
			}
		}
		this.setMap(map);
	}

	// map表示
	public void displayMap() {
		String[] blocks = {"　", "■"};
		for(int i = 0; i < this.getHight(); i++) {
			for(int j = 0; j <this.getWidth(); j++) {
				System.out.print(blocks[this.getMap()[i][j]]);
			}
			System.out.println("");
		}

	}
}
