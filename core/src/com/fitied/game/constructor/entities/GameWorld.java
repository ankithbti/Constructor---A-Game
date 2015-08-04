package com.fitied.game.constructor.entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.fitied.game.constructor.common.Constants;

public class GameWorld {

	private World _world;
	private boolean _debug;
	private Box2DDebugRenderer _renderer;
	private OrthographicCamera _worldCamera;
	private static GameWorld _instance;

	private GameWorld(Vector2 gravity, boolean debug, boolean doSleep) {
		_world = new World(gravity, doSleep);
		_debug = debug;
		_renderer = new Box2DDebugRenderer();
		_worldCamera = new OrthographicCamera();
		_worldCamera.setToOrtho(false, Constants.SCREEN_WIDTH * Constants.P2M, Constants.SCREEN_HEIGHT * Constants.P2M);
	}

	// No-MultiThreading....So call only in main thread
	public static GameWorld getInstance() {
		if (_instance == null) {
			_instance = new GameWorld(Constants.GRAVITY, Constants.WORLD_DEBUG, Constants.WORLD_DO_SLEEP);
		}
		return _instance;
	}

	public void update() {
		_world.step(Constants.WORLD_STEP, Constants.VELOCITY_ITERATION, Constants.POSITION_ITERATION);
	}

	public void render() {
		if (_debug) {
			_renderer.render(_world, _worldCamera.combined);
		}
	}

	public void dispose() {
		_world.dispose();
	}

	public Body createRectBody(float x, float y, float w, float h, Vector2 center, float angle, BodyType bodyType,
			float density, float friction, float restitution, boolean fixedRotation, boolean isSensor, short categoryBits, short maskBits,
			short groupIndex, Object bodyUserData, Object fixtureUserData) {

		Body body;

		BodyDef def = new BodyDef();
		def.type = bodyType;
		def.fixedRotation = fixedRotation;
		def.position.set(x * Constants.P2M, y * Constants.P2M);

		body = _world.createBody(def);
		body.setUserData(bodyUserData);

		PolygonShape box = new PolygonShape();
		// box.setAsBox(w/2 * Constants.P2M, h/2* Constants.P2M, center, angle);
		box.setAsBox(w / 2 * Constants.P2M, h / 2 * Constants.P2M);

		addFixture(body, box, density, friction, restitution, isSensor, categoryBits, maskBits, groupIndex, fixtureUserData);

		return body;

	}

	public Body createCircularBody(float x, float y, float radius, BodyType bodyType, float density, float friction,
			float restitution, boolean fixedRotation, boolean isSensor, short categoryBits, short maskBits, short groupIndex,
			Object bodyUserData, Object fixtureUserData) {
		Body body;

		BodyDef def = new BodyDef();
		def.type = bodyType;
		def.fixedRotation = fixedRotation;
		def.position.set(x * Constants.P2M, y * Constants.P2M);

		body = _world.createBody(def);
		body.setUserData(bodyUserData);

		CircleShape shape = new CircleShape();
		shape.setRadius(radius);

		addFixture(body, shape, density, friction, restitution, isSensor, categoryBits, maskBits, groupIndex, fixtureUserData);

		return body;
	}

	public void addFixture(Body body, Shape shape, float density, float friction, float restitution, boolean isSensor, short categoryBits,
			short maskBits, short groupIndex, Object fixtureUserData) {
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = density;
		fixtureDef.friction = friction;
		fixtureDef.isSensor = isSensor;
		fixtureDef.restitution = restitution;
		if (categoryBits != 0) {
			fixtureDef.filter.categoryBits = categoryBits;
			fixtureDef.filter.maskBits = maskBits;
			fixtureDef.filter.groupIndex = groupIndex;
		}

		body.createFixture(fixtureDef).setUserData(fixtureUserData);
	}

}
