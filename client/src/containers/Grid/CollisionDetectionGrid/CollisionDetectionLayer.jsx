import React from 'react';
import { Layer } from 'react-konva';
import Konva from 'konva';

/**
 * Wrapper around Konva Layer that detects collisions between items on the layer.
 * @param {children} children
 * @author:csworthington
 */
const CollisionDetectionLayer = ({children}) => {

  /**
   * Test if there is a collision between two rectangles
   * @param {Konva.Rect} r1 : Bounding box rectangle for component one
   * @param {Konva.Rect} r2 : Bounding box rectangle for component two
   */
  function haveIntersection(r1, r2) {
    return !(
      r2.x > r1.x + r1.width ||
      r2.x + r2.width < r1.x ||
      r2.y > r1.y + r1.height||
      r2.y + r2.height < r1.y
    );
  }


  /**
   * On drag start, store the original position of the component
   * @param {Event} e Drag start event
   */
  const storeOriginalPosition = (e) => {
    e.target.setAttr('originalPosition', e.target.absolutePosition())
  }


  /**
   * Detect a collision between two different Konva elements
   * @param {Event} e The event for which the collision detection was triggered
   */
  const checkForCollisions = (e) => {
    const target = e.target;
    const targetId = e.target._id;
    const targetRect = e.target.getClientRect();
    const layer = e.currentTarget;

    let collisionFlag = false;

    // If target ignores collisions, return
    if (target.getAttr('ignoreCollisions') === true) {
      console.log('target ' + target.name() + ' ignores collisions');
      return false;
    }


    layer.children.each(function (group) {
      // do not check intersection with self
      if (group._id === targetId) {
        return;
      }
      // Do not check collisions with parent group
      if (target.parent === group) {
        return;
      }

      // Do not check collisions with objects that allow clipping
      if (group.getAttr("ignoreCollisions") === true) {
        return;
      }

      if (haveIntersection(group.getClientRect(), targetRect)) {
        collisionFlag = true;
        // console.log(target)
        // target.parent.add(getCollisionRect(target, targetRect))
        
        console.log('collision with object id=' + group._id);
        console.log(group);
        console.log('colliding with ' + group.name());

        console.log('target is ')
        console.log(target)
        // target.stopDrag()
      } else {
        return;
      }
    });
    return collisionFlag;
  }

  /**
   * Check for component overlap on end of drag and drop. Send component back to
   * its original position if there is an overlap between components that 
   * detect collisions.
   * @param {Event} e 
   */
  const checkForDropOverlap = (e) => {
    // console.log('drag ended');
    // console.log(e.target.getAttr('originalPosition'))
    // console.log(checkForCollisions(e))
    if (checkForCollisions(e)) {
      console.log('collision detected');
      e.target.absolutePosition(e.target.getAttr('originalPosition'))
    }
  }

  return (
    <Layer 
        onDragStart={(e) => storeOriginalPosition(e)}
        // onDragMove={(e) => checkForCollisions(e)} 
        onDragEnd={(e) => checkForDropOverlap(e)}>
      {children}
    </Layer>
  );

};

export default CollisionDetectionLayer;
