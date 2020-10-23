/* eslint-disable jsx-a11y/click-events-have-key-events */
import React from 'react';
import {
  textColor,
  buttonFillColor,
  buttonWidth,
  buttonHeight,
} from '../../util/style';

/**
 * A button template for Header elements that allow users to perform various actions.
 * @param {text} text what the button will display when rendered.
 * @param {event} onClick what action shall be taken when the button is pressed.
 * @author:kyhorne
 */
const Button = ({ text, onClick }) => (
  <div
    onClick={() => onClick()}
    role="button"
    tabIndex={0}
    style={{
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      backgroundColor: buttonFillColor,
      width: buttonWidth,
      height: buttonHeight,
      marginLeft: 'auto',
      marginRight: '10px',
      borderRadius: '5px',
      outline: 'none',
      cursor: 'pointer',
    }}
  >
    <p style={{ color: textColor }}>{text}</p>
  </div>
);

export default Button;
