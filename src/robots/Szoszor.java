package robots;

import java.awt.Color;

import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import rule.RulesProcessor;

public class Szoszor extends Robot {

	private RulesProcessor processor = new RulesProcessor();
	private int power = 1;
	private String event = Event.NONE;
	private String lastEvent = Event.NONE;

	public void run() {
		setColors(Color.red, Color.blue, Color.green);
		while (true) {
			processor.process(this);
		}
	}

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

	public void onHitByBullet(HitByBulletEvent e) {
		this.event = Event.ON_HIT_BY_BULLET;
		processor.process(this, e);
		reset(Event.ON_HIT_BY_BULLET);
	}

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

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public void incPower(int n) {
		power += n;
	}

	public void decPower(int n) {
		power -= n;
	}

	public void reset() {
		reset(Event.NONE);
	}
	
	public void reset(String lastEvent) {
		this.setLastEvent(lastEvent);
		this.event = Event.NONE;
	}

	public String getLastEvent() {
		return lastEvent;
	}

	public void setLastEvent(String lastEvent) {
		this.lastEvent = lastEvent;
	}
}
