import React from 'react';
import { Group } from 'react-konva';

/**
 * Enables the categories of components in the Sidebar to be hidden/shown
 * if a user so desires.
 * @param {boolean} isHidden whether the menu bar is collapsed or not.
 * @author:kyhorne
 */
const Hideable = ({ isHidden, children }) => {
  if (isHidden) {
    return null;
  }
  return <Group name='HideableGroup' ignoreCollisions={true}>{children}</Group>;
};

export default Hideable;
