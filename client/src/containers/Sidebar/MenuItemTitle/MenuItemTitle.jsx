import React from 'react';
import { textColor } from '../../../util/style';

const containerStyle = {
  display: 'flex',
  cursor: 'pointer',
  alignItems: 'center',
  paddingLeft: '20px',
};

const arrowStyle = {
  border: `solid ${textColor}`,
  borderWidth: '0 1.5px 1.5px 0',
  display: 'inline-block',
  padding: '3px',
  marginRight: '15px',
};

const menuTitleStyle = {
  letterSpacing: '2px',
  color: textColor,
};
/**
 * Title cards for each subsection of the sidebar
 * @param {text} title what the dropdown menu title will display
 * @author: kyhorne
 */
const MenuItemTitle = ({ title }) => (
  <div style={{ ...containerStyle }}>
    <i style={{ ...arrowStyle }} />
    <p style={{ ...menuTitleStyle }}>{title}</p>
  </div>
);

export default MenuItemTitle;
