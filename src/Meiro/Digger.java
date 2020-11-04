package Meiro;

public class Digger {
	private int positionY;
	private int positionX;

	public Digger(int positionY, int positionX) {
		this.positionY = positionY;
		this.positionX = positionX;
	}



	public int getPositionY() {
		return positionY;
	}



	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}



	public int getPositionX() {
		return positionX;
	}



	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}



	// diggerの現在地を座標を道にする
	public void dig(Meiro meiro) {
		int[][] newMap = meiro.getMap();
		newMap[getPositionY()][getPositionX()] = 0;
		meiro.setMap(newMap);
	}


	// diggerの現在地から道を伸ばせる方向に移動
	public void moveCanDigWall(Meiro meiro) {
		int[] diggerPosition = {getPositionY(), getPositionX()};


		// diggerのいる座標から掘ることのできる壁の座標に移動
		if(this._chackCanMakeRoadPoint(meiro, diggerPosition)){
			int direction = 0;

			do {
				direction = new java.util.Random().nextInt(4);
			}while(!this._checkCanMakeRoadDirection(meiro, diggerPosition)[direction]);

			// digger移動
			switch (direction) {
			case 0://up
				diggerPosition[0] -= 1;
				break;

			case 1://right
				diggerPosition[1] += 1;
				break;

			case 2://down
				diggerPosition[0] += 1;
				break;

			case 3://left
				diggerPosition[1] -= 1;
				break;
			}

			setPositionY(diggerPosition[0]);
			setPositionX(diggerPosition[1]);

		}else {

			return;
		}

	}


	// 引数で渡された座標の各方向が道が伸ばせるかどうかの確認
	// 戻り値：[上、右、下、左]のboolean配列
	public boolean[] _checkCanMakeRoadDirection(Meiro meiro,int[] point) {
		int[][] map = meiro.getMap();

		//上・右・下・左に道を伸ばせるかの真偽値
		boolean[] canMakeRoadDirections ={false,false,false,false};

		// 引数で与えられた座標を「12」とした周辺地形を格納する配列
		//  0, 1, 2, 3, 4,
		//  5, 6, 7, 8, 9
		// 10,11,12,13,14,
		// 15,16,17,18,19,
		// 20,21,22,23,24
		int[] checkBlocks = new int[25];
		int index = 0;
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				int y = point[0] + i;
				int x = point[1] + j;

				if(y < 0 || y >= meiro.getHight() || x < 0 || x >= meiro.getWidth()) {
					checkBlocks[index] = 0;
				} else {
					checkBlocks[index] = map[y][x];
				}
				index++;
			}
		}


		// 進行方向ごとの確認すべきブロック
		int[] upCheckIndex = {1,2,3,6,7,8};
		int[] rightCheckIndex = {8,9,13,14,18,19};
		int[] downCheckIndex = {16,17,18,21,22,23};
		int[] leftCheckIndex = {5,6,10,11,15,16};
		int[][] checkIndexes = {upCheckIndex, rightCheckIndex, downCheckIndex,leftCheckIndex};
		boolean canMakeRoad;

		for(int i = 0; i < checkIndexes.length; i++) {
			canMakeRoad = true;
			for(int j = 0; j < checkIndexes[i].length; j++){
				if(checkBlocks[checkIndexes[i][j]] == 0 ) {
					canMakeRoad = false;
					break;
				}
			}
			canMakeRoadDirections[i] = canMakeRoad;
		}

		return canMakeRoadDirections;
	}

	// 引数で渡された座標が道を伸ばせるか確認する
	public boolean _chackCanMakeRoadPoint(Meiro meiro, int[] point) {
		boolean[] canMakeRoadDirection = this._checkCanMakeRoadDirection(meiro, point);
		boolean canMakeRoadPoint = false;
		for (int i = 0; i < canMakeRoadDirection.length; i++) {
			if(canMakeRoadDirection[i] == true) {
				canMakeRoadPoint = true;
				break;
			}
		}
		return canMakeRoadPoint;

	}

	// 渡された座標から順に道を伸ばせる道を探し、見つけた最初の道に移動
	public void moveCanExtendRoad(Meiro meiro, int[] point) {
		int[][] copyMap = meiro.getMap();
		for (int i = 0; i < copyMap.length; i++) {
			for (int j = 0; j < copyMap[i].length; j++) {
				int y = (i + point[1]) % copyMap.length;
				int x = (j + point[1]) % copyMap[i].length;
				int[] checkPoint = {y, x};

				if(copyMap[y][x] == 0 && this._chackCanMakeRoadPoint(meiro, checkPoint)) {
					setPositionY(y);
					setPositionX(x);

					return;
				}
			}
		}
		setPositionY(0);
		setPositionX(0);
	}


}
