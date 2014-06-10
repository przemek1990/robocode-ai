package robots;

import robocode.*;
import rule.RulesProcessor;

public class JRuleRobot extends AdvancedRobot{

    private Event event = Event.NONE;
    private Event lastEvent = Event.NONE;
    private int bulletPower = 1;
    private RulesProcessor processor = new RulesProcessor();

    @Override
	public void run() {
		while (true)
			processor.process(this);
	}

    @Override
	public void onScannedRobot(ScannedRobotEvent e) {
		this.event = Event.ON_SCANNED_ROBOT;
		processor.process(this, e);
		reset(Event.ON_SCANNED_ROBOT);
	}

	@Override
	public void onHitRobot(HitRobotEvent e) {
		this.event = Event.ON_HIT_ROBOT;
		processor.process(this, e);
		reset(Event.ON_HIT_ROBOT);
	}

    @Override
	public void onHitByBullet(HitByBulletEvent e) {
		this.event = Event.ON_HIT_BY_BULLET;
		processor.process(this, e);
		reset(Event.ON_HIT_BY_BULLET);
	}

    @Override
	public void onHitWall(HitWallEvent e) {
		this.event = Event.ON_HIT_WALL;
		processor.process(this, e);
		reset(Event.ON_HIT_WALL);

	}

	@Override
	public void onBulletHit(BulletHitEvent e) {
		this.event = Event.ON_BULLET_HIT;
		processor.process(this, e);
	}

	@Override
	public void onBulletMissed(BulletMissedEvent e) {
		this.event = Event.ON_BULLET_MISSED;
		processor.process(this, e);
		reset(Event.ON_BULLET_MISSED);
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
	
	public void reset(Event lastEvent) {
		this.lastEvent = lastEvent;
		this.event = Event.NONE;
	}

	public String getLastEvent() {
		return lastEvent.name();
	}

	public void setLastEvent(String lastEvent) {
		this.lastEvent = Event.valueOf(lastEvent);
	}
}
