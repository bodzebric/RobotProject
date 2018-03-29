package nl.hva.miw.robot.controllers;

import nl.hva.miw.robot.models.LineFollower;

public class LineFollowerController {
	
	private LineFollower lineFollower;
	
	public LineFollowerController(LineFollower lineFollower) {
		this.lineFollower = lineFollower;
	}
	
	public void runLineFollower() {
		this.lineFollower.go();
	}

}
