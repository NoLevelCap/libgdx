/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.badlogic.gdx.physics.bullet;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

public class btRigidBody extends btCollisionObject {
	private long swigCPtr;
	
	protected btRigidBody(final String className, long cPtr, boolean cMemoryOwn) {
		super(className, gdxBulletJNI.btRigidBody_SWIGUpcast(cPtr), cMemoryOwn);
		swigCPtr = cPtr;
	}
	
	protected btRigidBody(long cPtr, boolean cMemoryOwn) {
		this("btRigidBody", cPtr, cMemoryOwn);
		construct();
	}
	
	@Override
	protected void reset(long cPtr, boolean cMemoryOwn) {
		if (!destroyed)
			destroy();
		super.reset(gdxBulletJNI.btRigidBody_SWIGUpcast(swigCPtr = cPtr), cMemoryOwn);
	}
	
	public static long getCPtr(btRigidBody obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	@Override
	protected void finalize() throws Throwable {
		if (!destroyed)
			destroy();
		super.finalize();
	}

  @Override protected synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				gdxBulletJNI.delete_btRigidBody(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

	protected btMotionState motionState;
	
	public btRigidBody(btRigidBodyConstructionInfo constructionInfo) {
		this(false, constructionInfo);
		refCollisionShape(constructionInfo.getCollisionShape());
		refMotionState(constructionInfo.getMotionState());
	}
	
	public btRigidBody(float mass, btMotionState motionState, btCollisionShape collisionShape, Vector3 localInertia) {
		this(false, mass, motionState, collisionShape, localInertia);
		refCollisionShape(collisionShape);
		refMotionState(motionState);
	}
	
	public btRigidBody(float mass, btMotionState motionState, btCollisionShape collisionShape) {
		this(false, mass, motionState, collisionShape);
		refCollisionShape(collisionShape);
		refMotionState(motionState);
	}
  
	public void setMotionState(btMotionState motionState) {
		refMotionState(motionState);
		internalSetMotionState(motionState);
	}
	
	protected void refMotionState(btMotionState motionState) {
		if (this.motionState == motionState)
			return;
		if (this.motionState != null)
			this.motionState.release();
		this.motionState = motionState;
		if (this.motionState != null)
			this.motionState.obtain();
	}
	
	public btMotionState getMotionState() {
		return motionState;
	}
	
	@Override
	public void dispose() {
		if (motionState != null)
			motionState.release();
		motionState = null;
		super.dispose();
	}

  public void proceedToTransform(Matrix4 newTrans) {
    gdxBulletJNI.btRigidBody_proceedToTransform(swigCPtr, this, newTrans);
  }

  public void predictIntegratedTransform(float step, Matrix4 predictedTransform) {
    gdxBulletJNI.btRigidBody_predictIntegratedTransform(swigCPtr, this, step, predictedTransform);
  }

  public void saveKinematicState(float step) {
    gdxBulletJNI.btRigidBody_saveKinematicState(swigCPtr, this, step);
  }

  public void applyGravity() {
    gdxBulletJNI.btRigidBody_applyGravity(swigCPtr, this);
  }

  public void setGravity(Vector3 acceleration) {
    gdxBulletJNI.btRigidBody_setGravity(swigCPtr, this, acceleration);
  }

  public Vector3 getGravity() {
	return gdxBulletJNI.btRigidBody_getGravity(swigCPtr, this);
}

  public void setDamping(float lin_damping, float ang_damping) {
    gdxBulletJNI.btRigidBody_setDamping(swigCPtr, this, lin_damping, ang_damping);
  }

  public float getLinearDamping() {
    return gdxBulletJNI.btRigidBody_getLinearDamping(swigCPtr, this);
  }

  public float getAngularDamping() {
    return gdxBulletJNI.btRigidBody_getAngularDamping(swigCPtr, this);
  }

  public float getLinearSleepingThreshold() {
    return gdxBulletJNI.btRigidBody_getLinearSleepingThreshold(swigCPtr, this);
  }

  public float getAngularSleepingThreshold() {
    return gdxBulletJNI.btRigidBody_getAngularSleepingThreshold(swigCPtr, this);
  }

  public void applyDamping(float timeStep) {
    gdxBulletJNI.btRigidBody_applyDamping(swigCPtr, this, timeStep);
  }

  public void setMassProps(float mass, Vector3 inertia) {
    gdxBulletJNI.btRigidBody_setMassProps(swigCPtr, this, mass, inertia);
  }

  public Vector3 getLinearFactor() {
	return gdxBulletJNI.btRigidBody_getLinearFactor(swigCPtr, this);
}

  public void setLinearFactor(Vector3 linearFactor) {
    gdxBulletJNI.btRigidBody_setLinearFactor(swigCPtr, this, linearFactor);
  }

  public float getInvMass() {
    return gdxBulletJNI.btRigidBody_getInvMass(swigCPtr, this);
  }

  public Matrix3 getInvInertiaTensorWorld() {
	return gdxBulletJNI.btRigidBody_getInvInertiaTensorWorld(swigCPtr, this);
}

  public void integrateVelocities(float step) {
    gdxBulletJNI.btRigidBody_integrateVelocities(swigCPtr, this, step);
  }

  public void setCenterOfMassTransform(Matrix4 xform) {
    gdxBulletJNI.btRigidBody_setCenterOfMassTransform(swigCPtr, this, xform);
  }

  public void applyCentralForce(Vector3 force) {
    gdxBulletJNI.btRigidBody_applyCentralForce(swigCPtr, this, force);
  }

  public Vector3 getTotalForce() {
	return gdxBulletJNI.btRigidBody_getTotalForce(swigCPtr, this);
}

  public Vector3 getTotalTorque() {
	return gdxBulletJNI.btRigidBody_getTotalTorque(swigCPtr, this);
}

  public Vector3 getInvInertiaDiagLocal() {
	return gdxBulletJNI.btRigidBody_getInvInertiaDiagLocal(swigCPtr, this);
}

  public void setInvInertiaDiagLocal(Vector3 diagInvInertia) {
    gdxBulletJNI.btRigidBody_setInvInertiaDiagLocal(swigCPtr, this, diagInvInertia);
  }

  public void setSleepingThresholds(float linear, float angular) {
    gdxBulletJNI.btRigidBody_setSleepingThresholds(swigCPtr, this, linear, angular);
  }

  public void applyTorque(Vector3 torque) {
    gdxBulletJNI.btRigidBody_applyTorque(swigCPtr, this, torque);
  }

  public void applyForce(Vector3 force, Vector3 rel_pos) {
    gdxBulletJNI.btRigidBody_applyForce(swigCPtr, this, force, rel_pos);
  }

  public void applyCentralImpulse(Vector3 impulse) {
    gdxBulletJNI.btRigidBody_applyCentralImpulse(swigCPtr, this, impulse);
  }

  public void applyTorqueImpulse(Vector3 torque) {
    gdxBulletJNI.btRigidBody_applyTorqueImpulse(swigCPtr, this, torque);
  }

  public void applyImpulse(Vector3 impulse, Vector3 rel_pos) {
    gdxBulletJNI.btRigidBody_applyImpulse(swigCPtr, this, impulse, rel_pos);
  }

  public void clearForces() {
    gdxBulletJNI.btRigidBody_clearForces(swigCPtr, this);
  }

  public void updateInertiaTensor() {
    gdxBulletJNI.btRigidBody_updateInertiaTensor(swigCPtr, this);
  }

  public Vector3 getCenterOfMassPosition() {
	return gdxBulletJNI.btRigidBody_getCenterOfMassPosition(swigCPtr, this);
}

  public Quaternion getOrientation() {
	return gdxBulletJNI.btRigidBody_getOrientation(swigCPtr, this);
}

  public Matrix4 getCenterOfMassTransform() {
	return gdxBulletJNI.btRigidBody_getCenterOfMassTransform(swigCPtr, this);
}

  public Vector3 getLinearVelocity() {
	return gdxBulletJNI.btRigidBody_getLinearVelocity(swigCPtr, this);
}

  public Vector3 getAngularVelocity() {
	return gdxBulletJNI.btRigidBody_getAngularVelocity(swigCPtr, this);
}

  public void setLinearVelocity(Vector3 lin_vel) {
    gdxBulletJNI.btRigidBody_setLinearVelocity(swigCPtr, this, lin_vel);
  }

  public void setAngularVelocity(Vector3 ang_vel) {
    gdxBulletJNI.btRigidBody_setAngularVelocity(swigCPtr, this, ang_vel);
  }

  public Vector3 getVelocityInLocalPoint(Vector3 rel_pos) {
	return gdxBulletJNI.btRigidBody_getVelocityInLocalPoint(swigCPtr, this, rel_pos);
}

  public void translate(Vector3 v) {
    gdxBulletJNI.btRigidBody_translate(swigCPtr, this, v);
  }

  public void getAabb(Vector3 aabbMin, Vector3 aabbMax) {
    gdxBulletJNI.btRigidBody_getAabb(swigCPtr, this, aabbMin, aabbMax);
  }

  public float computeImpulseDenominator(Vector3 pos, Vector3 normal) {
    return gdxBulletJNI.btRigidBody_computeImpulseDenominator(swigCPtr, this, pos, normal);
  }

  public float computeAngularImpulseDenominator(Vector3 axis) {
    return gdxBulletJNI.btRigidBody_computeAngularImpulseDenominator(swigCPtr, this, axis);
  }

  public void updateDeactivation(float timeStep) {
    gdxBulletJNI.btRigidBody_updateDeactivation(swigCPtr, this, timeStep);
  }

  public boolean wantsSleeping() {
    return gdxBulletJNI.btRigidBody_wantsSleeping(swigCPtr, this);
  }

  public btBroadphaseProxy getBroadphaseProxy() {
    long cPtr = gdxBulletJNI.btRigidBody_getBroadphaseProxy__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : new btBroadphaseProxy(cPtr, false);
  }

  public void setNewBroadphaseProxy(btBroadphaseProxy broadphaseProxy) {
    gdxBulletJNI.btRigidBody_setNewBroadphaseProxy(swigCPtr, this, btBroadphaseProxy.getCPtr(broadphaseProxy), broadphaseProxy);
  }

  private btMotionState internalGetMotionState() {
    long cPtr = gdxBulletJNI.btRigidBody_internalGetMotionState__SWIG_0(swigCPtr, this);
    return (cPtr == 0) ? null : new btMotionState(cPtr, false);
  }

  private void internalSetMotionState(btMotionState motionState) {
    gdxBulletJNI.btRigidBody_internalSetMotionState(swigCPtr, this, btMotionState.getCPtr(motionState), motionState);
  }

  public void setContactSolverType(int value) {
    gdxBulletJNI.btRigidBody_contactSolverType_set(swigCPtr, this, value);
  }

  public int getContactSolverType() {
    return gdxBulletJNI.btRigidBody_contactSolverType_get(swigCPtr, this);
  }

  public void setFrictionSolverType(int value) {
    gdxBulletJNI.btRigidBody_frictionSolverType_set(swigCPtr, this, value);
  }

  public int getFrictionSolverType() {
    return gdxBulletJNI.btRigidBody_frictionSolverType_get(swigCPtr, this);
  }

  public void setAngularFactor(Vector3 angFac) {
    gdxBulletJNI.btRigidBody_setAngularFactor__SWIG_0(swigCPtr, this, angFac);
  }

  public void setAngularFactor(float angFac) {
    gdxBulletJNI.btRigidBody_setAngularFactor__SWIG_1(swigCPtr, this, angFac);
  }

  public Vector3 getAngularFactor() {
	return gdxBulletJNI.btRigidBody_getAngularFactor(swigCPtr, this);
}

  public boolean isInWorld() {
    return gdxBulletJNI.btRigidBody_isInWorld(swigCPtr, this);
  }

  public boolean checkCollideWithOverride(btCollisionObject co) {
    return gdxBulletJNI.btRigidBody_checkCollideWithOverride(swigCPtr, this, btCollisionObject.getCPtr(co), co);
  }

  public void addConstraintRef(btTypedConstraint c) {
    gdxBulletJNI.btRigidBody_addConstraintRef(swigCPtr, this, btTypedConstraint.getCPtr(c), c);
  }

  public void removeConstraintRef(btTypedConstraint c) {
    gdxBulletJNI.btRigidBody_removeConstraintRef(swigCPtr, this, btTypedConstraint.getCPtr(c), c);
  }

  public btTypedConstraint getConstraintRef(int index) {
    long cPtr = gdxBulletJNI.btRigidBody_getConstraintRef(swigCPtr, this, index);
    return (cPtr == 0) ? null : new btTypedConstraint(cPtr, false);
  }

  public int getNumConstraintRefs() {
    return gdxBulletJNI.btRigidBody_getNumConstraintRefs(swigCPtr, this);
  }

  public void setFlags(int flags) {
    gdxBulletJNI.btRigidBody_setFlags(swigCPtr, this, flags);
  }

  public int getFlags() {
    return gdxBulletJNI.btRigidBody_getFlags(swigCPtr, this);
  }

  public Vector3 computeGyroscopicForce(float maxGyroscopicForce) {
	return gdxBulletJNI.btRigidBody_computeGyroscopicForce(swigCPtr, this, maxGyroscopicForce);
}

  private btRigidBody(boolean dummy, btRigidBodyConstructionInfo constructionInfo) {
    this(gdxBulletJNI.new_btRigidBody__SWIG_0(dummy, btRigidBodyConstructionInfo.getCPtr(constructionInfo), constructionInfo), true);
  }

  private btRigidBody(boolean dummy, float mass, btMotionState motionState, btCollisionShape collisionShape, Vector3 localInertia) {
    this(gdxBulletJNI.new_btRigidBody__SWIG_1(dummy, mass, btMotionState.getCPtr(motionState), motionState, btCollisionShape.getCPtr(collisionShape), collisionShape, localInertia), true);
  }

  private btRigidBody(boolean dummy, float mass, btMotionState motionState, btCollisionShape collisionShape) {
    this(gdxBulletJNI.new_btRigidBody__SWIG_2(dummy, mass, btMotionState.getCPtr(motionState), motionState, btCollisionShape.getCPtr(collisionShape), collisionShape), true);
  }

}
