package Meiro;

public class Main {

	public static int getRandomNum(int num) {
		return new java.util.Random().nextInt(num);
	}


	public static void main(String[] args) {
		int meiroSize = 45;
		int[] start = {meiroSize / 2, meiroSize / 2};
		Meiro meiro = new Meiro(meiroSize, meiroSize, start);
		Digger digger = new Digger(start[0], start[1]);
		int maxRoadLength = 30;

		while(true){

			int[] point = {getRandomNum(meiro.getHight()), getRandomNum(meiro.getWidth())};
			digger.moveCanExtendRoad(meiro, point);
			if(digger.getPositionY() == 0 && digger.getPositionX() == 0) {
				System.out.println("終了");
				return;
			}

			for (int i = 0; i < maxRoadLength; i++) {

				digger.moveCanDigWall(meiro);
				if(meiro.getMap()[digger.getPositionY()][digger.getPositionX()] == 1) {
					digger.dig(meiro);
					//0.1秒待つ
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					meiro.displayMap();
					System.out.println("");
				}

			}
		}

	}

}
