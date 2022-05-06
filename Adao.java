package Adao;
import robocode.*;

public class Adao extends Robot
{
	public void run() {
		while(true) {

			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
        mira(e.getBearing());
        fogo(e.getDistance());
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		if(pertoParede()) {
            back(100);
        } else {
            ahead(100);
        }
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {

		back(20);
	}	
	
    public void mira(double Adv) {
        double A=getHeading()+Adv-getGunHeading();
        if (!(A > -180 && A <= 180)) {
            while (A <= -180) {
                A += 360;
            }
            while (A > 180) {
                A -= 360;
            }
        }
        turnGunRight(A);
    }

    public void fogo(double Distancia) {
        if (Distancia > 200 || getEnergy() < 15) {
            fire(1);
        } else if (Distancia > 50) {
            fire(2);
        } else if (getEnergy() < 12) {
            tiroFatal(getEnergy());
        } else {
            fire(3);
        }
    }

    public void tiroFatal(double EnergiaIni) {
        double Tiro = (EnergiaIni / 4) + .1;
        fire(Tiro);
    }

    public boolean pertoParede() {
        return (getX() < 50 || getX() > getBattleFieldWidth() - 50 ||
            getY() < 50 || getY() > getBattleFieldHeight() - 50);
    }
}
