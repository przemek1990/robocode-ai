package robots;

import robocode.*;

public class JRuleRobot extends AdvancedRobot{
	 
	enum RobotsEvent {
		ON_SCANNED_ROBOT, ON_HIT_ROBOT,ON_HIT_BY_BULLET,ON_HIT_WALL,ON_BULLET_HIT,
		ON_BULLET_MISSED,ON_BULLET_HIT_BULLET,NONE
	}
	 
    private RobotsEvent event = RobotsEvent.NONE;
    private RobotsEvent lastEvent = RobotsEvent.NONE;
    private int bulletPower = 1;
    private RulesDispatcher dispatcher = new RulesDispatcher();

    @Override
	public void run() {
		while (true)
			dispatcher.handelEvent(this);
	}

    @Override
	public void onScannedRobot(ScannedRobotEvent e) {
		processEvent(RobotsEvent.ON_SCANNED_ROBOT,e);
	}

	@Override
	public void onHitRobot(HitRobotEvent e) {
		processEvent(RobotsEvent.ON_HIT_ROBOT,e);
	}

    @Override
	public void onHitByBullet(HitByBulletEvent e) {
		processEvent(RobotsEvent.ON_HIT_BY_BULLET,e);
	}

    @Override
	public void onHitWall(HitWallEvent e) {
		processEvent(RobotsEvent.ON_HIT_WALL,e);
	}

	@Override
	public void onBulletHit(BulletHitEvent e) {
		this.event = RobotsEvent.ON_BULLET_HIT;
		dispatcher.handelEvent(this, e);
	}

	@Override
	public void onBulletMissed(BulletMissedEvent e) {
		processEvent(RobotsEvent.ON_BULLET_MISSED,e);
	}

	private void processEvent(RobotsEvent e,robocode.Event robocodeEvent){
		this.event = e;
		dispatcher.handelEvent(this,robocodeEvent);
		reset(e);
	}
	
	public int getBulletPower() {
		return bulletPower;
	}

	public void setBulletPower(int bulletPower) {
		this.bulletPower = bulletPower;
	}

	public String getEvent() {
		return event.name();
	}

	public void increasePower(int n) {
		bulletPower += n;
	}

	public void decreasePower(int n) {
		bulletPower -= n;
	}
	
	public void reset(RobotsEvent lastEvent) {
		this.lastEvent = lastEvent;
		this.event = RobotsEvent.NONE;
	}

	public String getLastEvent() {
		return lastEvent.name();
	}

	public void setLastEvent(String lastEvent) {
		this.lastEvent = RobotsEvent.valueOf(lastEvent);
	}
}
